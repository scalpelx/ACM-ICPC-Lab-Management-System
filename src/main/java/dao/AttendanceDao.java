package dao;

import entity.Attendance;

import java.util.List;

public interface AttendanceDao {
    public boolean add(Attendance attendance);
    public boolean update(Attendance attendance);
    public boolean delete(String scholar, String date);
    public List listByDate(String startDate, String endDate);
    public List listByStudentAndDate(String scholar, String startDate, String endDate);
}
