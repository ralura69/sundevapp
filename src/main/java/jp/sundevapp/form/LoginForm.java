package jp.sundevapp.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class LoginForm implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String userId;
	@NotEmpty
	private String password;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
