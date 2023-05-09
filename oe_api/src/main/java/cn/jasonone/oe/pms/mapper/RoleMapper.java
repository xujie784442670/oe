package cn.jasonone.oe.pms.mapper;

import cn.jasonone.oe.pms.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author xujie
* @description 针对表【pms_role(角色表)】的数据库操作Mapper
* @createDate 2023-05-02 22:10:27
* @Entity cn.jasonone.oe.pms.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
	/**
	 * 根据账号id查询角色
	 * @param accountId 账号id
	 * @return  角色列表
	 */
	List<Role> findByAccountId(Integer accountId);
}




