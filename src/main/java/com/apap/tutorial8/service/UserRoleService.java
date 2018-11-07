package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser (UserRoleModel user);
	UserRoleModel getUserByUsername (String username);
	void updatePassword (String username, String newPassword);
	public String encrypt (String password);
}
