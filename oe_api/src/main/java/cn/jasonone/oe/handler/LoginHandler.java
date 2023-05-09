package cn.jasonone.oe.handler;

import cn.jasonone.oe.pms.pojo.LoginType;
import cn.jasonone.oe.pms.service.LoginService;

public interface LoginHandler extends LoginService {
	
	LoginType getLoginType();

}
