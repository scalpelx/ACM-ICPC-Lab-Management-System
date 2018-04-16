package service;

import entity.Attendance;

import java.util.List;

public interface AttendanceService {

    public boolean addAttendance(Attendance attendance);
    public boolean deleteAttendance(String scholar, String date);
    public List getAttendancesByDate(String startDate, String endDate);
    public List getAttendancesByStudentAndDate(String scholar, String startDate, String endDate);
}
