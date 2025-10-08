package com.cdac.smp.AdminMst.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cdac.smp.AdminMst.Dao.LoginEntryDao;

@Service
public class LoginEntryService {

    private final LoginEntryDao loginEntryDao;

    public LoginEntryService(LoginEntryDao loginEntryDao) {
        this.loginEntryDao = loginEntryDao;
    }

    public void recordFailedLogin(String loginId) {
        loginEntryDao.saveFailedLogin(loginId);
    }

    public LocalDateTime getLastLoginTime(String loginId) {
        return loginEntryDao.getTime(loginId);
    }
    public void clearLoginEntries(String loginId) {
        loginEntryDao.deleteLoginEntries(loginId);
    }

}
