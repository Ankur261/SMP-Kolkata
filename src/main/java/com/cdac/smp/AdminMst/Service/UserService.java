package com.cdac.smp.AdminMst.Service;

import com.cdac.smp.AdminMst.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);
    Optional<User> getUserById(String id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(String id);
    Optional<User> login(String loginId, String password);
	User getUserByLoginId(String loginId);
	void setFlagByOne(String loginId);
	int getflagValue(String loginId);
	void setFlagByZero(String loginId);
	
}
