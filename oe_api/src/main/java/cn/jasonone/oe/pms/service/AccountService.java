package cn.jasonone.oe.pms.service;

import cn.jasonone.oe.pms.domain.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author xujie
* @description 针对表【pms_account(账号表)】的数据库操作Service
* @createDate 2023-05-02 23:44:51
*/
public interface AccountService extends IService<Account> {
	/**
	 * 根据用户名查询账号
	 * @param username
	 * @return
	 */
	Account findByUsername(String username);
}
