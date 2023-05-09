package cn.jasonone.oe.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.convert.Convert;
import cn.jasonone.oe.pms.domain.Permission;
import cn.jasonone.oe.pms.mapper.PermissionMapper;
import cn.jasonone.oe.pms.mapper.RoleMapper;
import cn.jasonone.oe.pms.service.PermissionService;
import cn.jasonone.oe.pms.service.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class SaTokenConfiguration implements WebMvcConfigurer{
	@Resource
	private PermissionService permissionService;
	@Resource
	private RoleService roleService;
	// 注册 Sa-Token 拦截器，打开注解式鉴权功能
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册 Sa-Token 拦截器，打开注解式鉴权功能
		registry.addInterceptor(new SaInterceptor())
				.addPathPatterns("/**");
	}
	@Bean
	public StpInterface stpInterface(){
		return new StpInterface() {
			@Override
			public List<String> getPermissionList(Object loginId, String loginType) {
				List<Permission> permissions = permissionService.findByAccountId(Convert.toInt(loginId));
				return permissions.stream().map(Permission::getCode).distinct().collect(Collectors.toList());
			}
			
			@Override
			public List<String> getRoleList(Object loginId, String loginType) {
				return roleService.findByAccountId(Convert.toInt(loginId)).stream().map(role -> role.getCode()).distinct().collect(Collectors.toList());
			}
		};
	}
}
