package cn.jasonone.oe.pms.service;

import cn.jasonone.oe.pms.domain.Banned;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BannedService extends IService<Banned> {
	/**
	 * 检查并更新账号封禁状态
	 * @param accountId 账号ID
	 */
	void checkAndUpdate(Integer accountId);
	
	/**
	 * 永久封禁账号
	 *
	 * @param accountId 账号id
	 */
	void disable(Integer accountId, String reason);
	
	/**
	 * 解封账号
	 *
	 * @param accountId 账号id
	 */
	void enable(Integer accountId);
	
	/**
	 * 解封指定服务
	 *
	 * @param accountId   账号id
	 * @param serviceName 服务名称
	 */
	void enable(Integer accountId, String...serviceNames);
	
	/**
	 * 封禁账号
	 *
	 * @param accountId 账号id
	 * @param seconds   封禁时长，单位秒
	 * @param reason    封禁原因
	 */
	void disable(Integer accountId, Integer seconds, String reason);
	
	/**
	 * 封禁账号
	 *
	 * @param accountId   账号id
	 * @param serviceName 服务名称
	 * @param seconds     封禁时长，单位秒
	 * @param reason      封禁原因
	 */
	void disable(Integer accountId, String serviceName, Integer seconds, String reason);
	
	/**
	 * 根据账号id查询封禁信息
	 * @param accountId 账号id
	 * @return  封禁信息
	 */
	List<Banned> findByAccountId(Integer accountId);
}
