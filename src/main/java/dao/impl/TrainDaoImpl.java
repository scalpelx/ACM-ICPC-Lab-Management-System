package dao.impl;

import dao.TrainDao;
import entity.Student;
import entity.Train;
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
    public List getTrains() {
        return sessionFactory.getCurrentSession().createQuery("FROM Train").list();
    }

    @Override
    public List getStudentTrains(Student student) {
        return student.getTrains();
    }
}
