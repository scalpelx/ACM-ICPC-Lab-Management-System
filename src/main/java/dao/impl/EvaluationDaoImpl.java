package dao.impl;

import dao.EvaluationDao;
import entity.Evaluation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Iterator;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("evaluationDao") //进行注入
public class EvaluationDaoImpl implements EvaluationDao {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Evaluation evaluation) {
        String hsql = "FROM Evaluation eva where eva.studentByScholar=? and eva.date=?";
        System.out.println(hsql);
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setParameter(0, evaluation.getStudentByScholar());
        query.setDate(1, evaluation.getDate());
        Iterator<Evaluation> it = query.iterate();
        if (it.hasNext()) {
            sessionFactory.getCurrentSession().update(evaluation);
        } else
            sessionFactory.getCurrentSession().save(evaluation);
        return true;
    }

    @Override
    public boolean update(Evaluation evaluation) {
        sessionFactory.getCurrentSession().update(evaluation);
        return true;
    }

    @Override
    public boolean delete(Evaluation evaluation) {
        sessionFactory.getCurrentSession().delete(evaluation);
        return true;
    }

    @Override
    public Evaluation list(String scholar, String date) {
        String hsql = "FROM Evaluation eva where eva.studentByScholar.scholar=? and eva.date=?";
        System.out.println(hsql);
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, scholar);
        query.setString(1, date);
        Iterator<Evaluation> it = query.iterate();
        if (it.hasNext()) {
            return it.next();
        } else
            return null;
    }
}
