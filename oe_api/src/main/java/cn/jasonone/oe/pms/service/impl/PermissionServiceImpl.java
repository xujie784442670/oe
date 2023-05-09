package cn.jasonone.oe.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.pms.domain.Permission;
import cn.jasonone.oe.pms.service.PermissionService;
import cn.jasonone.oe.pms.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author xujie
* @description 针对表【pms_permission(权限表)】的数据库操作Service实现
* @createDate 2023-05-02 22:10:27
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{
	
	@Override
	public List<Permission> findByAccountId(Integer accountId) {
		return getBaseMapper().findByAccountId(accountId);
	}
}




