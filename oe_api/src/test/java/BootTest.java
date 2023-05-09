import cn.jasonone.oe.OELauncher;
import cn.jasonone.oe.handler.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试文件
 */
@SpringBootTest(classes= {OELauncher.class})
public class BootTest {
	@Resource
	private PasswordEncoder passwordEncoder;
	@Test
	public void test() {
		String salt = passwordEncoder.createSalt();
		String encode = passwordEncoder.encode("123", salt);
		System.out.println(encode);
		System.out.println(salt);
	}

}
