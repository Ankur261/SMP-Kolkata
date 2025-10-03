package com.cdac.smp.AdminMst.Service;

import com.cdac.smp.AdminMst.Dao.UserDao;
import com.cdac.smp.AdminMst.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(String id) {
        userDao.softDeleteUser(id);
    }

    public Optional<User> login(String loginId, String password) {
        return userDao.login(loginId, password);
    }

	@Override
	public User getUserByLoginId(String loginId) {
		return userDao.getUserByLoginId(loginId);
	}

	@Override
	public void setFlagByOne(String loginId) {
		userDao.setFlagByOne(loginId);
		
	}

	@Override
	public int getflagValue(String loginId) {
		return userDao.getFlagValue(loginId);
	}

	@Override
	public void setFlagByZero(String loginId) {
		userDao.setFlagToZero(loginId);
	}
	
	public boolean existsByLoginId(String loginId) {
	    return userDao.existsByLoginId(loginId);
	}

}
