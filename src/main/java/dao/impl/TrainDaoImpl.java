package dao.impl;

import dao.TrainDao;
import entity.Train;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
}
