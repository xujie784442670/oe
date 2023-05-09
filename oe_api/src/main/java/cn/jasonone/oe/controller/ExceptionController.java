package cn.jasonone.oe.controller;

import cn.dev33.satoken.util.SaResult;
import cn.jasonone.oe.constants.OeErrorCode;
import cn.jasonone.oe.exception.OeException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(BindException.class)
	public SaResult handleException(BindException e) {
		List<FieldError> errors = e.getFieldErrors();
		SaResult rs = SaResult.code(OeErrorCode.ILLEGAL_PARAMETER.getCode());
		rs.setMsg(OeErrorCode.ILLEGAL_PARAMETER.getMsg());
		Map<String,List<String>> data = new HashMap<>();
		for (FieldError error : errors) {
			List<String> list = data.get(error.getField());
			if(list == null){
				list = new java.util.ArrayList<>();
				data.put(error.getField(),list);
			}
			list.add(error.getDefaultMessage());
		}
		rs.set("error",data);
		return rs;
	}
	
	@ExceptionHandler(OeException.class)
	public SaResult handleException(OeException e) {
		return SaResult.code(e.getCode()).setMsg(e.getMessage()).setData(e.getData());
	}
}
