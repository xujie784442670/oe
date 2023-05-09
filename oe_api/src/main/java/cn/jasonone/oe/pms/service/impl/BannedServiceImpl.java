package cn.jasonone.oe.pms.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.jasonone.oe.constants.BannedServiceName;
import cn.jasonone.oe.pms.domain.Account;
import cn.jasonone.oe.pms.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.jasonone.oe.pms.domain.Banned;
import cn.jasonone.oe.pms.service.BannedService;
import cn.jasonone.oe.pms.mapper.BannedMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author xujie
 * @description 针对表【pms_banned(封禁表)】的数据库操作Service实现
 * @createDate 2023-05-02 23:44:51
 */
@Service
public class BannedServiceImpl extends ServiceImpl<BannedMapper, Banned>
		implements BannedService {
	@Override
	public void checkAndUpdate(Integer accountId) {
		List<Banned> banners = this.findByAccountId(accountId);
		for (Banned banned : banners) {
			if (banned != null) {
				LocalDateTime endTime = banned.getStartTime().plusSeconds(banned.getTime());
				if (endTime.isBefore(LocalDateTime.now())) {
					// 解封
					enable(accountId, banned.getServiceName());
				} else {
					// 获得剩余封禁时间
					long seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), endTime);
					if (StpUtil.isDisable(accountId)) {
						long disableTime = StpUtil.getDisableTime(accountId);
						if (disableTime < seconds && disableTime > 0) {
							// 更新封禁时间
							disable(accountId, banned.getServiceName(), (int) seconds, banned.getReason());
						}
					} else {
						// 封禁
						disable(accountId, banned.getServiceName(), (int) seconds, banned.getReason());
					}
				}
			}
		}
	}
	
	@Override
	public void disable(Integer accountId, String reason) {
		disable(accountId, BannedServiceName.ALL, -1, reason);
	}
	
	@Override
	public void enable(Integer accountId) {
		enable(accountId, BannedServiceName.ALL);
	}
	
	@Override
	public void enable(Integer accountId, String... serviceNames) {
		StpUtil.untieDisable(accountId, serviceNames);
		QueryWrapper<Banned> qw = new QueryWrapper<Banned>();
		qw.eq("account_id", accountId);
		qw.in("service_name", serviceNames);
		remove(qw);
	}
	
	@Override
	public void disable(Integer accountId, Integer seconds, String reason) {
		disable(accountId, BannedServiceName.ALL, seconds, reason);
	}
	
	@Override
	public void disable(Integer accountId, String serviceName, Integer seconds, String reason) {
		StpUtil.disable(accountId,serviceName,seconds);
		QueryWrapper<Banned> qw = new QueryWrapper<Banned>();
		qw.eq("account_id", accountId);
		qw.eq("service_name", serviceName);
		Banned banned = getOne(qw);
		if (banned == null) {
			banned = new Banned();
			banned.setAccountId(accountId);
			banned.setServiceName(serviceName);
			banned.setReason(reason);
			banned.setStartTime(LocalDateTime.now());
			banned.setTime(seconds.longValue());
			save(banned);
		}
	}
	
	@Override
	public List<Banned> findByAccountId(Integer accountId) {
		QueryWrapper<Banned> qw = new QueryWrapper<Banned>();
		qw.eq("account_id", accountId);
		return list(qw);
	}
}




