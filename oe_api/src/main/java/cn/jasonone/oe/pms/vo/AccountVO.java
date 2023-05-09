package cn.jasonone.oe.pms.vo;

import cn.jasonone.oe.pms.domain.Permission;
import cn.jasonone.oe.pms.domain.Role;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class AccountVO {
	/**
	 * 账号id
	 */
	private Integer id;
	/**
	 * 账号
	 */
	private String username;
	/**
	 * 封禁时长(单位：秒): -1 永久封禁 0 未封禁 >0 封禁时长
	 */
	private Integer banned;
	/**
	 * 封禁开始时间
	 */
	private LocalDateTime bannedTime;
	/**
	 * 账号创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 账号最后更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 角色列表
	 */
	private List<Role> roles;
	/**
	 * 权限列表
	 */
	private List<Permission> permissions;
}
