package cn.jasonone.oe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.jasonone.oe.*.mapper")
public class OELauncher {
	
	public static void main(String[] args) {
		SpringApplication.run(OELauncher.class, args);
	}
}
