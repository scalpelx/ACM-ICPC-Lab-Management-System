package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Admin;
import entity.Attendance;
import entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.AdminService;
import service.AttendanceService;
import service.StudentService;
import util.Problems;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport {
    @Resource
    private AdminService adminService;

    @Resource
    private StudentService studentService;

    @Resource
    private AttendanceService attendanceService;

    private Admin admin;

    private String oldPassword;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void validateLogin() {
        System.out.println("validateLogin");
        if (admin.getId().isEmpty())
            this.addFieldError("id", "请输入账号");
        if (admin.getPassword().isEmpty())
            this.addFieldError("password", "请输入密码");
    }

    public String login() {
        Map session = ActionContext.getContext().getSession();
        if (adminService.login(admin)) {
            admin = adminService.getAdminById(admin.getId());
            session.put("admin", admin);
            return SUCCESS;
        } else {
            session.put("AdminLoginError", "用户名或者密码不正确！");
            return ERROR;
        }
    }

    public String modifyInfo() {
        Map session = ActionContext.getContext().getSession();
        Admin ad = (Admin) session.get("admin");
        admin.setPassword(ad.getPassword());
        if (adminService.updateAdmin(admin)) {
            session.put("admin", admin);
            return SUCCESS;
        } else {
            session.put("error", "修改失败！");
            return ERROR;
        }
    }

    public String modifyPassword() {
        Map session = ActionContext.getContext().getSession();
        System.out.println(admin.getId() + " " + admin.getPassword());
        if (adminService.updatePassword(admin.getId(), oldPassword, admin.getPassword())) {
            admin = adminService.getAdminById(admin.getId());
            session.put("admin", admin);
            return SUCCESS;
        } else {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            request.setAttribute("error", "密码错误");
            return ERROR;
        }
    }

    public String viewStudentPerformance() {
        Map session = ActionContext.getContext().getSession();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        Student student = studentService.getStudentByScholar(scholar);
        Problems p = new Problems();
        Set<String> acProblem = p.getACProblems(student.getHdu());
        session.put("ac", acProblem);
        session.put("studentName", student.getName());

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
