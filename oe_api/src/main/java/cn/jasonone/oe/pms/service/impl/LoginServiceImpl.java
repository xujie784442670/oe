package cn.jasonone.oe.pms.service.impl;

import cn.hutool.core.util.ClassUtil;
import cn.jasonone.oe.constants.OeErrorCode;
import cn.jasonone.oe.handler.LoginHandler;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service("loginService")
@Slf4j
public class LoginServiceImpl implements LoginService, InitializingBean {
	@Resource
	private ApplicationContext applicationContext;
	private static final Map<LoginType, LoginHandler> LOGIN_HANDLER_MAP = new HashMap<>();
	
	@Override
	public AccountVO login(AccountDTO accountDTO) {
		LoginHandler loginHandler = LOGIN_HANDLER_MAP.get(accountDTO.getLoginType());
		if(loginHandler == null){
			throw new LoginException(OeErrorCode.Login.E3);
		}
		AccountVO login = loginHandler.login(accountDTO);
		if (login == null) {
			throw new LoginException(OeErrorCode.Login.E4);
		}
		return login;
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
