package dao.impl;

import dao.AttendanceDao;
import entity.Attendance;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("attendanceDao") //进行注入
public class AttendanceDaoImpl implements AttendanceDao {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Attendance attendance) {
        sessionFactory.getCurrentSession().save(attendance);
        return true;
    }

    @Override
    public boolean update(Attendance attendance) {
        String hsql = "update from Attendance a set a.arriveTime = ?, a.leaveTime = ? where a.studentByScholar.scholar = ? and a.date = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, attendance.getArriveTime().toString());
        query.setString(1, attendance.getArriveTime().toString());
        query.setString(2, attendance.getStudentByScholar().getScholar());
        query.setString(3, attendance.getDate().toString());
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean delete(String scholar, String date) {
        String hsql = "delete from Attendance a where a.studentByScholar.scholar = ? and a.date = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, scholar);
        query.setString(1, date);
        query.executeUpdate();
        return true;
    }

    @Override
    public List listByDate(String startDate, String endDate) {
        String hsql = "FROM Attendance a where a.date between ? and ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        query.setString(0, startDate);
        query.setString(1, endDate);
        return query.list();
    }

    @Override
    public List listByStudentAndDate(String scholar, String startDate, String endDate) {
        String hsql = "FROM Attendance a where a.studentByScholar.scholar = ? and a.date between ? and ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, scholar);
        query.setString(1, startDate);
        query.setString(2, endDate);
        return query.list();
    }
}
