package com.user.dto;

public class ResetPassword {
	
	private String email;
	
	private String newPassword;

	public ResetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ResetPassword(String email, String newPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ResetPassword [email=" + email + ", newPassword=" + newPassword + "]";
	}


}
