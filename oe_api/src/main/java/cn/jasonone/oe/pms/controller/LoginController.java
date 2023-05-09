package cn.jasonone.oe.pms.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.jasonone.oe.pms.dto.AccountDTO;
import cn.jasonone.oe.pms.exceptions.LoginException;
import cn.jasonone.oe.pms.pojo.LoginType;
import cn.jasonone.oe.pms.service.BannedService;
import cn.jasonone.oe.pms.service.LoginService;
import cn.jasonone.oe.pms.vo.AccountVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 登录控制器
 *
 * @Author Jason
 */
@RestController
public class LoginController {
	@Resource
	private LoginService loginService;
	@Resource
	private BannedService bannedService;
	@PostMapping("/login")
	AccountVO login(@Valid AccountDTO accountDTO) {
		if (accountDTO.getLoginType() == null) {
			accountDTO.setLoginType(LoginType.PASSWORD);
		}
		AccountVO login = loginService.login(accountDTO);
		bannedService.checkAndUpdate(login.getId());
		StpUtil.login(login.getId());
		return login;
	}
}
