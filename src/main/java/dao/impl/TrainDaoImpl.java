package dao.impl;

import dao.TrainDao;
import entity.Student;
import entity.Train;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("trainDao") //进行注入
public class TrainDaoImpl implements TrainDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Train train) {
        sessionFactory.getCurrentSession().save(train);
        return true;
    }

    @Override
    public boolean delete(String id) {
        String hsql = "delete from Train t where t.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean update(Train train) {
        sessionFactory.getCurrentSession().update(train);
        return true;
    }

    @Override
    public Train getTrain(int id) {
        return (Train) sessionFactory.getCurrentSession().get(Train.class, id);
    }

    @Override
    public List getTrains() {
        return sessionFactory.getCurrentSession().createQuery("FROM Train").list();
    }

    @Override
    public List getStudentTrains(Student student) {
        return student.getTrains();
    }
}
