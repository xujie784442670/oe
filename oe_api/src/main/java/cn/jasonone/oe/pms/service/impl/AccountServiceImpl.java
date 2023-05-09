package cn.jasonone.oe.pms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.pms.domain.Account;
import cn.jasonone.oe.pms.service.AccountService;
import cn.jasonone.oe.pms.mapper.AccountMapper;
import org.springframework.stereotype.Service;

/**
* @author xujie
* @description 针对表【pms_account(账号表)】的数据库操作Service实现
* @createDate 2023-05-02 23:44:51
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService{
	
	@Override
	public Account findByUsername(String username) {
		return getOne(new QueryWrapper<Account>().eq("username", username));
	}
}




