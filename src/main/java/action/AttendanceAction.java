package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Attendance;
import entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.AttendanceService;
import service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller("attendanceAction")
@Scope("prototype")
public class AttendanceAction extends ActionSupport {
    @Resource
    AttendanceService attendanceService;
    @Resource
    StudentService studentService;

    private Attendance attendance;

    private String date;
    private String arriveTime;
    private String leaveTime;
    private String scholar;

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getScholar() {
        return scholar;
    }

    public void setScholar(String scholar) {
        this.scholar = scholar;
    }

    public String addAttendance() throws ParseException {
        java.util.Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        attendance.setDate(new java.sql.Date(d.getTime()));
        java.util.Date t1 = new SimpleDateFormat("hh:mm").parse(arriveTime);
        attendance.setArriveTime(new java.sql.Time(t1.getTime()));
        java.util.Date t2 = new SimpleDateFormat("hh:mm").parse(leaveTime);
        attendance.setLeaveTime(new java.sql.Time(t2.getTime()));
        Map session = ActionContext.getContext().getSession();
        System.out.println(scholar);
        Student student =  studentService.getStudentByScholar(scholar);
        if (student == null) {
            System.out.println("not student");
            session.put("error", "没有此学生");
            return ERROR;
        }
        attendance.setStudentByScholar(student);
        if (attendanceService.addAttendance(attendance)) {
            return SUCCESS;
        } else {
            System.out.println("fail");
            session.put("error", "添加失败！");
            return ERROR;
        }
    }

    public String deleteAttendance() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        String date = request.getParameter("date");
        Map session = ActionContext.getContext().getSession();
        if (attendanceService.deleteAttendance(scholar, date)) {
            return SUCCESS;
        } else {
            session.put("error", "删除失败");
            return ERROR;
        }
    }

    public String listAttendance() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List attendances = attendanceService.getAttendancesByDate(startDate, endDate);
        Map session = ActionContext.getContext().getSession();
        session.put("startDate", startDate);
        session.put("endDate", endDate);
        session.put("attendances", attendances);
        return SUCCESS;
    }

    public String listStudentAttendances() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        List attendances = attendanceService.getAttendancesByStudentAndDate(scholar, startDate, endDate);
        Map session = ActionContext.getContext().getSession();
        session.put("scholar", scholar);
        session.put("startDate", startDate);
        session.put("endDate", endDate);
        session.put("attendances", attendances);
        return SUCCESS;
    }

    public String viewAttendances() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Map session = ActionContext.getContext().getSession();
        Student student = (Student) session.get("student");
        List attendances = attendanceService.getAttendancesByStudentAndDate(student.getScholar(), startDate, endDate);
        //session.put("student", student);
        session.put("startDate", startDate);
        session.put("endDate", endDate);
        session.put("attendances", attendances);
        return SUCCESS;
    }
}
