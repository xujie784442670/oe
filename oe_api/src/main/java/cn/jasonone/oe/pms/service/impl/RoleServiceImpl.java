package cn.jasonone.oe.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.pms.domain.Role;
import cn.jasonone.oe.pms.service.RoleService;
import cn.jasonone.oe.pms.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujie
 * @description 针对表【pms_role(角色表)】的数据库操作Service实现
 * @createDate 2023-05-02 22:10:27
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
		implements RoleService {
	
	@Override
	public List<Role> findByAccountId(Integer accountId) {
		return getBaseMapper().findByAccountId(accountId);
	}
}




