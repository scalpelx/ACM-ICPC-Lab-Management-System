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
import util.Problems;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller("studentAction")
@Scope("prototype")
public class StudentAction extends ActionSupport{
    @Resource
    private StudentService studentService;

    @Resource
    private AttendanceService attendanceService;

    private Student student;

    private String oldPassword;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void validateLogin() {
        System.out.println("validateLogin");
        if (student.getScholar().isEmpty())
            this.addFieldError("scholar", "请输入学号");
        if (student.getPasswd().isEmpty())
            this.addFieldError("passwd", "请输入密码");
    }

    public String login() {
        Map session = ActionContext.getContext().getSession();
        if (studentService.login(student)) {
            student =  studentService.getStudentByScholar(student.getScholar());
            session.put("student", student);
            return SUCCESS;
        } else {
            session.put("loginError", "用户名或者密码不正确！");
            return ERROR;
        }
    }

    public void validateRegister() {
        System.out.println("validateRegister");
        if (student.getScholar().isEmpty())
            this.addFieldError("scholar", "请输入学号");
        if (student.getPasswd().isEmpty())
            this.addFieldError("passwd", "请输入密码");
        if (student.getStdclass().isEmpty())
            this.addFieldError("stdclass", "请输入班级");
        if (student.getPhone().isEmpty())
            this.addFieldError("phone", "请输入手机号");
        if (student.getHdu().isEmpty())
            this.addFieldError("hdu", "请输入杭电OJ账号");
        if (student.getName().isEmpty())
            this.addFieldError("name", "请输入姓名");
    }

    public String register() {
        Map session = ActionContext.getContext().getSession();
        if (studentService.addStudent(student)) {
            return SUCCESS;
        } else {
            session.put("registerError", "注册失败！");
            return ERROR;
        }
    }

    public String modifyInfo() {
        Map session = ActionContext.getContext().getSession();
        Student stu = studentService.getStudentByScholar(student.getScholar());
        if (student.getPasswd() == null || student.getPasswd().isEmpty())
            student.setPasswd(stu.getPasswd());
        if (student.getScholar().isEmpty())
            student.setScholar(stu.getScholar());
        if (student.getHdu().isEmpty())
            student.setHdu(stu.getHdu());
        if (student.getName().isEmpty())
            student.setName(stu.getName());
        if (student.getPhone().isEmpty())
            student.setPhone(stu.getPhone());
        if (student.getStdclass().isEmpty())
            student.setStdclass(stu.getStdclass());
        if (studentService.updateStudent(student)) {
            session.put("student", student);
            return SUCCESS;
        } else {
            session.put("modifyError", "修改失败！");
            return ERROR;
        }
    }

    public String modifyPassword() {
        Map session = ActionContext.getContext().getSession();
        System.out.println(student.getScholar() + " " + student.getPasswd());
        if (studentService.updatePassword(student.getScholar(), oldPassword, student.getPasswd())) {
            student = studentService.getStudentByScholar(student.getScholar());
            session.put("student", student);
            return SUCCESS;
        } else {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("error", "密码错误");
            return ERROR;
        }
    }

    public String viewAllStudents() {
        Map session = ActionContext.getContext().getSession();
        List students = studentService.getAllStudent();
        if (!students.isEmpty()) {
            session.put("students", students);
            return SUCCESS;
        } else {
            session.put("error", "学生列表空");
            return ERROR;
        }
    }

    public String deleteStudent() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        System.out.println(scholar);
        if (studentService.deleteStudent(scholar))
            return viewAllStudents();
        else {
            Map session = ActionContext.getContext().getSession();
            session.put("error", "删除失败");
            return ERROR;
        }
    }

    public String viewPerformance() {
        Map session = ActionContext.getContext().getSession();
        Student student = (Student) session.get("student");
        Problems p = new Problems();
        Set<String> acProblem = p.getACProblems(student.getHdu());
        session.put("ac", acProblem);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH ) + 1;
        String startDate = null, endDate = null;
        if (month > 8) {
            startDate = String.valueOf(year) + "-09-01";
            endDate = String.valueOf(year + 1) + "-08-31";
        } else {
            startDate = String.valueOf(year - 1) + "-09-01";
            endDate = String.valueOf(year) + "-08-31";
        }
        List<Attendance> attendances = attendanceService.getAttendancesByStudentAndDate(student.getScholar(), startDate, endDate);
        int days = attendances.size();
        double totalTime = 0;
        for (Attendance attendance : attendances) {
            totalTime += attendance.getLeaveTime().getTime() - attendance.getArriveTime().getTime();
        }
        totalTime /= days;
        session.put("days", days);
        session.put("averageTime", String.format("%02d:%02d:%02d", (int) totalTime / 3600000 % 24, (int) totalTime / 60000  % 60, (int) totalTime / 1000 % 60));
        return SUCCESS;
    }
}
