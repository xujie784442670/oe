package cn.jasonone.oe.config;

import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Configuration
public class ResponseResultConfig {
	@Bean
	public ResponseBodyAdvice responseBodyAdvice() {
		return new ResponseBodyAdvice() {
			
			@Override
			public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
				if(body instanceof SaResult){
					return body;
				}
				return SaResult.data(body);
			}
			
			@Override
			public boolean supports(MethodParameter returnType, Class converterType) {
				return true;
			}
		};
	}
}
