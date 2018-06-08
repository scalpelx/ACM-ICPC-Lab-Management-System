package dao.impl;

import dao.StudentDao;
import entity.Student;
import entity.Train;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("studentDao") //进行注入
public class StudentDaoImpl implements StudentDao {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Student stu) {
        String hsql = "FROM Student stu where stu.scholar=?";
        System.out.println(hsql);
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, stu.getScholar());
        Iterator<Student> it = query.iterate();
        if (it.hasNext()) {
            System.out.println("学生已存在" + stu.getScholar());
            return false;
        }
        sessionFactory.getCurrentSession().save(stu);
        return true;
    }

    @Override
    public boolean login(Student stu) {
        String hsql = "FROM Student stu where stu.scholar=? and stu.passwd=?";
        System.out.println(hsql);
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, stu.getScholar());
        query.setString(1, stu.getPasswd());
        System.out.println(stu.getScholar());
        Iterator<Student> it = query.iterate();
        if (it.hasNext()) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    @Override
    public List getStudents() {
        return sessionFactory.getCurrentSession().createQuery("FROM Student ").list();
    }

    @Override
    public Student getStudent(String scholar) {
        return (Student) sessionFactory.getCurrentSession().get(Student.class, scholar);
    }

    @Override
    public void update(Student stu) {
        sessionFactory.getCurrentSession().update(stu);
    }

    @Override
    public void delete(String scholar) {
        Student student = (Student) sessionFactory.getCurrentSession().get(Student.class, scholar);
        List<Train> trains =  student.getTrains();
        for (Train train : trains) {
            train.getStudents().remove(student);
        }
        student.setTrains(null);
        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    public boolean updatePassword(String scholar, String OldPassword, String NewPassword) {
        String hsql = "update Student student set student.passwd = ? where student.scholar = ? and student.passwd = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hsql);
        query.setString(0, NewPassword);
        query.setString(1, scholar);
        query.setString(2, OldPassword);
        if (query.executeUpdate() == 0)
            return false;
        else
            return true;
    }
}
