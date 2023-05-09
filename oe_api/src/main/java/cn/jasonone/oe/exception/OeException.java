package cn.jasonone.oe.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.jasonone.oe.constants.OeErrorCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 全局异常基类
 */
public class OeException extends RuntimeException {
	protected final OeErrorCode.ErrorCode errorCode;
	@Setter
	@Getter
	@Accessors(chain = true)
	private Object data;
	
	public OeException(OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args));
		this.errorCode = errorCode;
		checkErrorCode();
	}
	
	public OeException(Throwable cause, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args), cause);
		this.errorCode = errorCode;
		checkErrorCode();
	}
	
	public OeException(Throwable cause, boolean enableSuppression, boolean writableStackTrace, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args), cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
		checkErrorCode();
	}
	public OeException(Object data,OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args));
		this.errorCode = errorCode;
		checkErrorCode();
		this.data = data;
	}
	
	public OeException(Object data,Throwable cause, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args), cause);
		this.errorCode = errorCode;
		checkErrorCode();
		this.data = data;
	}
	
	public OeException(Object data,Throwable cause, boolean enableSuppression, boolean writableStackTrace, OeErrorCode.ErrorCode errorCode, Object... args) {
		super(errorCode.getMessage(args), cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
		checkErrorCode();
		this.data = data;
	}
	
	public int getCode() {
		return this.errorCode.getCode();
	}
	
	protected void checkErrorCode() {
		if (errorCode == null) {
			throw new OeException(OeErrorCode.Common.E1);
		}
	}
}
