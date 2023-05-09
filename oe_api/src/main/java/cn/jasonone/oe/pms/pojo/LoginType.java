package cn.jasonone.oe.pms.pojo;

/**
 * 登录类型
 */
public enum LoginType {
	/**
	 * 账号密码登录
	 */
	PASSWORD,
	/**
	 * 手机号验证码登录
	 */
	PHONE,
	/**
	 * 邮箱验证码登录
	 */
	EMAIL,
	/**
	 * 微信登录
	 */
	WX,
	/**
	 * QQ登录
	 */
	QQ,
	/**
	 * 微博登录
	 */
	WB,
	/**
	 * github登录
	 */
	GITHUB,
	/**
	 * 钉钉登录
	 */
	DD,
	/**
	 * 企业微信登录
	 */
	QYWX,
	/**
	 * 支付宝登录
	 */
	ALIPAY;
}
