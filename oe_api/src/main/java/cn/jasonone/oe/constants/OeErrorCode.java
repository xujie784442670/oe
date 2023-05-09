package cn.jasonone.oe.constants;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

public interface OeErrorCode {
	/**
	 * 成功
	 */
	ErrorCode SUCCESS = new ErrorCode(200, "成功");
	/**
	 * 非法参数
	 */
	ErrorCode ILLEGAL_PARAMETER = new ErrorCode(400, "非法的参数");
	
	/**
	 * 公共错误码: 20100-20199
	 */
	interface Common{
		int MIN_CODE = 20100;
		int MAX_CODE = 20199;
		/**
		 * ErrorCode不能为空
		 */
		ErrorCode E1 = new ErrorCode(20101, "ErrorCode不能为空");
	}
	/**
	 * 登录相关错误码:20200-20299
	 */
	interface Login{
		int MIN_CODE = 20200;
		int MAX_CODE = 20299;
		/**
		 * 异常错误码属于登录相关错误码
		 */
		ErrorCode E0 = new ErrorCode(20200, "异常错误码属于登录相关错误码");
		/**
		 * 密码错误
		 */
		ErrorCode E1 = new ErrorCode(20201, "账号或密码错误");
		/**
		 * 不支持的登录方式
		 */
		ErrorCode E3 = new ErrorCode(20202, "不支持的登录方式");
		/**
		 * 返回的对象不能为空
		 */
		ErrorCode E4 = new ErrorCode(20203, "返回的对象不能为空");
		/**
		 * 登录处理器重复:{}
		 */
		ErrorCode E5 = new ErrorCode(20204, "登录处理器重复:{}");
		/**
		 * 账号以达到最大登录次数,已被锁定,请{}后再试
		 */
		ErrorCode E6 = new ErrorCode(20205, "账号以达到最大登录次数,已被锁定,请{}后再试");
		/**
		 * 账号已登陆失败{}次,还有{}次机会,如果全部失败将会被锁定{}
		 */
		ErrorCode E7 = new ErrorCode(20206, "账号已登陆失败{}次,还有{}次机会,如果全部失败将会被锁定{}");
	}
	@Data
	class ErrorCode{
		private final int code;
		private final String msg;
		public String getMessage(Object ... args) {
			return StrUtil.format(msg, args);
		}
	}
}
