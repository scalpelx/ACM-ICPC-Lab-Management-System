package service.impl;

import dao.AttendanceDao;
import entity.Attendance;
import org.springframework.stereotype.Service;
import service.AttendanceService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Resource
    private AttendanceDao attendanceDao;

    @Override
    public boolean addAttendance(Attendance attendance) {
        if (this.attendanceDao.listByStudentAndDate(attendance.getStudentByScholar().getScholar(),
                new SimpleDateFormat("yyyy-MM-dd").format(attendance.getDate()),
                new SimpleDateFormat("yyyy-MM-dd").format(attendance.getDate())).isEmpty())
            return this.attendanceDao.add(attendance);
        else
            return this.attendanceDao.update(attendance);
    }

    @Override
    public boolean deleteAttendance(String scholar, String date) {
        return this.attendanceDao.delete(scholar, date);
    }

    @Override
    public List getAttendancesByDate(String startDate, String endDate) {
        return this.attendanceDao.listByDate(startDate, endDate);
    }

    @Override
    public List getAttendancesByStudentAndDate(String scholar, String startDate, String endDate) {
        return this.attendanceDao.listByStudentAndDate(scholar, startDate, endDate);
    }
}
