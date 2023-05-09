package cn.jasonone.oe.pms.dto;

import cn.jasonone.oe.pms.pojo.LoginType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class AccountDTO {
	/**
	 * 账号
	 */
	@NotBlank(message = "账号不能为空")
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 验证码
	 */
	private String verifyCode;
	/**
	 * 登录设备类型
	 */
	private String deviceType;
	/**
	 * 登录类型
	 */
	private LoginType loginType;
}
