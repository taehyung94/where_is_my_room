package com.org.team4.dto;

<<<<<<< HEAD
import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String password;
	private String name;
	private String nickname;
	private String email;
	private String gender;


//	public void setUsr_pw(String usr_pw) {
//		this.usr_pw = DigestUtils.sha512Hex(usr_pw);
//	}
	
=======

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO implements Serializable {

	long id;
	String password;
	String name;
	String nickname;
	String email;
	String gender;

	public UserDTO(long id, String password, String name, String nickname, String email, String gender) {
		this.id = id;
		setPassword(password);
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.gender = gender;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.sha512Hex(password);
	}
>>>>>>> 7a5a54682726f07d8cee10e7d9ee1e94318f7a75
}