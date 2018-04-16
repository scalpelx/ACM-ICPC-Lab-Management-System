package dao;

import entity.Student;

import java.util.List;

public interface StudentDao {
    /**
     * 添加并保存学生
     * @param stu
     */
    public boolean add(Student stu);

    /**
     * 进行登录
     */
    public boolean login(Student stu);

    /**
     * 获取学生列表
     */
    public List getStudents();

    /**
     * 根据学生学号获取用户信息
     * @param scholar
     * @return
     */
    public Student getStudent(String scholar);

    /**
     * 更新学生信息
     * @param stu
     */
    public void update(Student stu);

    /**
     * 根据学生学号删除学生信息
     * @param scholar
     */
    public void delete(String scholar);

    public boolean updatePassword(String scholar, String OldPassword, String NewPassword);
}
