package qld.mock.vaccination.dto;

import javax.validation.constraints.NotEmpty;

public class UserDto {
	
	@NotEmpty(message ="this field cannot be empty.")
	private String userName;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String password;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String email;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String phone;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String passwordConfirm;
	
	@NotEmpty(message ="this field cannot be empty.")
	private String captcha;
	

	private String hiddenCaptcha;
	

	private String realCaptcha;

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getHiddenCaptcha() {
		return hiddenCaptcha;
	}

	public void setHiddenCaptcha(String hiddenCaptcha) {
		this.hiddenCaptcha = hiddenCaptcha;
	}

	public String getRealCaptcha() {
		return realCaptcha;
	}

	public void setRealCaptcha(String realCaptcha) {
		this.realCaptcha = realCaptcha;
	}
	
	
	
	

}
