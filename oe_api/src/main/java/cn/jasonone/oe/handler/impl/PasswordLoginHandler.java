package cn.jasonone.oe.handler.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.jasonone.oe.config.properties.LoginConfigProperties;
import cn.jasonone.oe.constants.OeErrorCode;
import cn.jasonone.oe.handler.LoginHandler;
import cn.jasonone.oe.handler.PasswordEncoder;
import cn.jasonone.oe.pms.domain.Account;
import cn.jasonone.oe.pms.dto.AccountDTO;
import cn.jasonone.oe.pms.exceptions.LoginException;
import cn.jasonone.oe.pms.pojo.LoginType;
import cn.jasonone.oe.pms.service.AccountService;
import cn.jasonone.oe.pms.vo.AccountVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 密码登录处理器
 * @author jason
 */
@Service
public class PasswordLoginHandler implements LoginHandler {
	@Resource
	private PasswordLoginCacheHandler passwordLoginCacheHandler;
	@Resource
	private AccountService accountService;
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private LoginConfigProperties loginConfigProperties;
	
	@Override
	public LoginType getLoginType() {
		return LoginType.PASSWORD;
	}
	
	@Override
	public AccountVO login(AccountDTO accountDTO) {
		// 检查账号是否锁定
		if (passwordLoginCacheHandler.isLock(accountDTO.getUsername())) {
			Duration lockTime = passwordLoginCacheHandler.getLockTime(accountDTO.getUsername());
			long seconds = lockTime.getSeconds();
			int minutes = (int) (seconds / 60);
			if(seconds % 60 > 0){
				minutes++;
			}
			throw new LoginException(OeErrorCode.Login.E6, minutes+"分钟").setData(lockTime.getSeconds());
		}
		
		
		
		// 检查账号密码是否正确
		Account account = accountService.findByUsername(accountDTO.getUsername());
		if (account == null) {
			// 尝试登录次数+1
			passwordLoginCacheHandler.incrTryLoginCount(accountDTO.getUsername());
			int tryLoginCount = passwordLoginCacheHandler.getTryLoginCount(accountDTO.getUsername());
			if (tryLoginCount >= loginConfigProperties.getMaxTryCount() - 3) {
				Duration duration = Duration.ofSeconds(loginConfigProperties.getLockTime());
				throw new LoginException(OeErrorCode.Login.E7, tryLoginCount, loginConfigProperties.getMaxTryCount() - tryLoginCount, duration.toMinutes()+"分钟");
			}
			throw new LoginException(OeErrorCode.Login.E1);
		}
		// 检查密码是否正确
		if (!passwordEncoder.matches(accountDTO.getPassword(), account.getPassword(),account.getSalt())) {
			// 尝试登录次数+1
			passwordLoginCacheHandler.incrTryLoginCount(accountDTO.getUsername());
			throw new LoginException(OeErrorCode.Login.E1);
		}
		// 尝试登录次数清零
		passwordLoginCacheHandler.resetTryLoginCount(accountDTO.getUsername());
		return BeanUtil.copyProperties(account, AccountVO.class);
	}
}
