package service.impl;

import dao.StudentDao;
import entity.Student;
import service.StudentService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Override
    public boolean addStudent(Student stu) {
        return this.studentDao.add(stu);
    }

    @Override
    public boolean login(Student stu) {
        return this.studentDao.login(stu);
    }

    @Override
    public List getAllStudent() {
        return this.studentDao.getStudents();
    }

    @Override
    public Student getStudentByScholar(String scholar) {
        return this.studentDao.getStudent(scholar);
    }

    @Override
    public boolean updateStudent(Student stu) {
        this.studentDao.update(stu);
        return true;
    }

    @Override
    public boolean deleteStudent(String scholar) {
        this.studentDao.delete(scholar);
        return true;
    }

    @Override
    public boolean updatePassword(String scholar, String OldPassword, String NewPassword) {
        return this.studentDao.updatePassword(scholar, OldPassword, NewPassword);
    }
}
