package com.zhy.model;

import lombok.Data;

/**
 *  
 * @Date: 2018/6/25 21:56
 * Describe:
 */
@Data
public class Password {

    private int id;

    private String password;

    private String username;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}
