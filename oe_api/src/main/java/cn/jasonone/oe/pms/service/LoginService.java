package cn.jasonone.oe.pms.service;

import cn.jasonone.oe.pms.dto.AccountDTO;
import cn.jasonone.oe.pms.vo.AccountVO;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

public interface LoginService {
	/**
	 * 登录
	 * @param accountDTO 账号信息
	 * @return 账号信息
	 */
	AccountVO login(AccountDTO accountDTO);
}
