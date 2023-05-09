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
	private AccountService accountService;
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Override
	public LoginType getLoginType() {
		return LoginType.PASSWORD;
	}
	
	@Override
	public AccountVO login(AccountDTO accountDTO) {
		// 检查账号密码是否正确
		Account account = accountService.findByUsername(accountDTO.getUsername());
		if (account == null) {
			return null;
		}
		// 检查密码是否正确
		if (!passwordEncoder.matches(accountDTO.getPassword(), account.getPassword(),account.getSalt())) {
			return null;
		}
		return BeanUtil.copyProperties(account, AccountVO.class);
	}
}
