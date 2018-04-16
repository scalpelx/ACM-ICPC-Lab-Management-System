package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Admin;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.AdminService;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller("adminAction")
@Scope("prototype")
public class AdminAction extends ActionSupport {
    @Resource
    private AdminService adminService;

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
            session.put("AdminModifyError", "修改失败！");
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
            return ERROR;
        }
    }
}
