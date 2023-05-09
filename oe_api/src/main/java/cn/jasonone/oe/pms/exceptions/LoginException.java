package cn.jasonone.oe.pms.exceptions;

import cn.dev33.satoken.exception.SaTokenException;
import cn.jasonone.oe.constants.OeErrorCode;
import cn.jasonone.oe.exception.OeException;

/**
 * 登录异常
 * @author Jason
 */
public class LoginException extends OeException {
	public LoginException(OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode, args);
	}
	
	public LoginException(Throwable cause, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(cause, errorCode, args);
	}
	
	public LoginException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(cause, enableSuppression, writableStackTrace, errorCode, args);
	}
	
	@Override
	protected void checkErrorCode() {
		super.checkErrorCode();
		if (errorCode.getCode() < OeErrorCode.Login.MIN_CODE || errorCode.getCode() > OeErrorCode.Login.MAX_CODE) {
			throw new OeException(OeErrorCode.Login.E0);
		}
	}
}
