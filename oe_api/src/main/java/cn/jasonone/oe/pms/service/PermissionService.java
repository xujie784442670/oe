package cn.jasonone.oe.pms.service;

import cn.jasonone.oe.pms.domain.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xujie
 * @description 针对表【pms_permission(权限表)】的数据库操作Service
 * @createDate 2023-05-02 22:10:27
 */
public interface PermissionService extends IService<Permission> {
	/**
	 * 根据账号id查询权限
	 *
	 * @param accountId 账号id
	 * @return 权限列表
	 */
	List<Permission> findByAccountId(Integer accountId);
}
