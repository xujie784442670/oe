package cn.jasonone.oe.pms.service;

import cn.jasonone.oe.pms.domain.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author xujie
 * @description 针对表【pms_role(角色表)】的数据库操作Service
 * @createDate 2023-05-02 22:10:27
 */
public interface RoleService extends IService<Role> {
	/**
	 * 根据账号id查询角色
	 *
	 * @param accountId 账号id
	 * @return 角色列表
	 */
	List<Role> findByAccountId(Integer accountId);
}
