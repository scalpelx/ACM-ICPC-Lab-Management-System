package service.impl;

import dao.AdminDao;
import entity.Admin;
import org.springframework.stereotype.Service;
import service.AdminService;
import javax.annotation.Resource;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public boolean login(Admin admin) {
        return this.adminDao.login(admin);
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        this.adminDao.update(admin);
        return true;
    }

    @Override
    public Admin getAdminById(String id) {
        return this.adminDao.getAdmin(id);
    }

    @Override
    public boolean updatePassword(String id, String OldPassword, String NewPassword) {
        return this.adminDao.updatePassword(id, OldPassword, NewPassword);
    }
}
