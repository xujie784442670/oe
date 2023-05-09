package cn.jasonone.oe.pms.service.impl;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.jasonone.oe.config.properties.LoginConfigProperties;
import cn.jasonone.oe.constants.OeErrorCode;
import cn.jasonone.oe.handler.LoginHandler;
import cn.jasonone.oe.handler.impl.PasswordLoginCacheHandler;
import cn.jasonone.oe.pms.dto.AccountDTO;
import cn.jasonone.oe.pms.exceptions.LoginException;
import cn.jasonone.oe.pms.pojo.LoginType;
import cn.jasonone.oe.pms.service.LoginService;
import cn.jasonone.oe.pms.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
@Slf4j
public class LoginServiceImpl implements LoginService, InitializingBean {
	@Resource
	private ApplicationContext applicationContext;
	private static final Map<LoginType, LoginHandler> LOGIN_HANDLER_MAP = new HashMap<>();
	@Resource
	private PasswordLoginCacheHandler passwordLoginCacheHandler;
	@Resource
	private LoginConfigProperties loginConfigProperties;
	@Override
	public AccountVO login(AccountDTO accountDTO) {
		LoginHandler loginHandler = LOGIN_HANDLER_MAP.get(accountDTO.getLoginType());
		if(loginHandler == null){
			throw new LoginException(OeErrorCode.Login.E3);
		}
		String key = accountDTO.getUsername();
		if(loginConfigProperties.isFingerprint()){
			key = accountDTO.getFingerprint();
		}else if(StrUtil.isNotBlank(accountDTO.getFingerprint())){
			key = accountDTO.getFingerprint();
		}
		if(StrUtil.isBlank(key)){
			throw new LoginException(OeErrorCode.Login.E8);
		}
		// 检查账号是否锁定
		if (passwordLoginCacheHandler.isLock(key)) {
			Duration lockTime = passwordLoginCacheHandler.getLockTime(key);
			long seconds = lockTime.getSeconds();
			int minutes = (int) (seconds / 60);
			if(seconds % 60 > 0){
				minutes++;
			}
			throw new LoginException(OeErrorCode.Login.E6, minutes+"分钟").setData(lockTime.getSeconds());
		}
		AccountVO login = loginHandler.login(accountDTO);
		if (login == null) {
			// 尝试登录次数+1
			passwordLoginCacheHandler.incrTryLoginCount(key);
			checkTryCount(key);
			throw new LoginException(OeErrorCode.Login.E1);
		}
		passwordLoginCacheHandler.resetTryLoginCount(key);
		return login;
	}
	
	/**
	 * 检查尝试登录次数
	 * @param username
	 */
	public void checkTryCount(String username){
		int tryLoginCount = passwordLoginCacheHandler.getTryLoginCount(username);
		if (tryLoginCount >= loginConfigProperties.getMaxTryCount() - 3) {
			Duration duration = Duration.ofSeconds(loginConfigProperties.getLockTime());
			throw new LoginException(OeErrorCode.Login.E7, tryLoginCount, loginConfigProperties.getMaxTryCount() - tryLoginCount, duration.toMinutes()+"分钟");
		}
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		Map<String, LoginHandler> loginHandlers = applicationContext.getBeansOfType(LoginHandler.class);
		LOGIN_HANDLER_MAP.clear();
		Collection<LoginHandler> values = loginHandlers.values();
		for (LoginHandler handler : values) {
			if(LOGIN_HANDLER_MAP.containsKey(handler.getLoginType())){
				throw new LoginException(OeErrorCode.Login.E4,handler.getLoginType());
			}
			LOGIN_HANDLER_MAP.put(handler.getLoginType(), handler);
			log.info("加载登录处理器：[{}]=> {}", handler.getClass().getName(), handler.getLoginType());
		}
	}
}
