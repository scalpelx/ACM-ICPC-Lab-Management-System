package service;

import entity.Student;

import java.util.List;

public interface StudentService {
    public boolean addStudent(Student stu);

    public boolean login(Student stu);

    public List getAllStudent();

    public Student getStudentByScholar(String scholar);

    public boolean updateStudent(Student stu);

    public boolean deleteStudent(String scholar);

    public boolean updatePassword(String scholar, String OldPassword, String NewPassword);
}
