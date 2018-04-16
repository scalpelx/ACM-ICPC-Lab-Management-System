package dao;

import entity.Admin;

public interface AdminDao {
    /**
     * 进行登录
     */
    public boolean login(Admin admin);
    /**
     * 更新信息
     * @param admin
     */
    public void update(Admin admin);

    /**
     * 根据账号获取账户信息
     * @param id
     * @return
     */
    public Admin getAdmin(String id);

    public boolean updatePassword(String id, String OldPassword, String NewPassword);
}
