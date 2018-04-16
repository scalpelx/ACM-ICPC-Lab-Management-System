package dao.impl;

import dao.AdminDao;
import entity.Admin;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("adminDao") //进行注入
public class AdminDaoImpl implements AdminDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean login(Admin admin) {
        String hsql = "FROM Admin admin where admin.id=? and admin.password=?";
        //System.out.println(hsql);
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, admin.getId());
        query.setString(1, admin.getPassword());
        //System.out.println(admin.getId());
        Iterator<Admin> it = query.iterate();
        if (it.hasNext()) {
            System.out.println("AdminTrue");
            return true;
        } else {
            System.out.println("AdminFalse");
            return false;
        }
    }

    @Override
    public void update(Admin admin) {
        sessionFactory.getCurrentSession().update(admin);
    }

    @Override
    public Admin getAdmin(String id) {
        return (Admin) sessionFactory.getCurrentSession().get(Admin.class, id);
    }

    @Override
    public boolean updatePassword(String id, String OldPassword, String NewPassword) {
        String hsql = "update Admin admin set admin.password = ? where admin.id = ? and admin.password = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, NewPassword);
        query.setString(1, id);
        query.setString(2, OldPassword);
        if (query.executeUpdate() == 0)
            return false;
        else
            return true;
    }
}
