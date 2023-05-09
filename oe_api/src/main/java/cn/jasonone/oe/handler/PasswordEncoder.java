package cn.jasonone.oe.handler;

public interface PasswordEncoder {
	/**
	 * 创建盐
	 * @return
	 */
	default String createSalt(){return "";}
	
	/**
	 * 密码加盐
	 * @param rawPassword
	 * @param salt
	 */
	default String addSalt(CharSequence rawPassword, String salt){
		return rawPassword.toString() + salt;
	}
	
	/**
	 * 加密
	 * @param rawPassword
	 * @return
	 */
	String encode(CharSequence rawPassword);
	
	/**
	 * 加密
	 * @param rawPassword   原始密码
	 * @param salt  	  盐
	 * @return
	 */
	default String encode(CharSequence rawPassword, String salt){
		return encode(addSalt(rawPassword, salt));
	}
	
	/**
	 * 验证
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 */
	boolean matches(CharSequence rawPassword, String encodedPassword);
	
	/**
	 * 验证
	 * @param rawPassword
	 * @param encodedPassword
	 * @param salt
	 * @return
	 */
	default boolean matches(CharSequence rawPassword, String encodedPassword, String salt){
		return matches(addSalt(rawPassword, salt), encodedPassword);
	}
}
