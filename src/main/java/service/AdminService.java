package service;

import entity.Admin;

public interface AdminService {
    public boolean login(Admin admin);
    public boolean updateAdmin(Admin admin);
    public Admin getAdminById(String id);
    public boolean updatePassword(String id, String OldPassword, String NewPassword);
}
