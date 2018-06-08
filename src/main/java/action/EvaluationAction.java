package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Evaluation;
import entity.Student;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.EvaluationService;
import service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@Controller("evaluationAction")
@Scope("prototype")
public class EvaluationAction extends ActionSupport {
    @Resource
    EvaluationService evaluationService;

    @Resource
    StudentService studentService;

    private Evaluation evaluation;

    private String scholar;
    private String date;
    private String content;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getScholar() {
        return scholar;
    }

    public void setScholar(String scholar) {
        this.scholar = scholar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String addEvaluation() throws ParseException {
        Student student =  studentService.getStudentByScholar(scholar);
        java.util.Date d = new SimpleDateFormat("yyyy").parse(date);
        evaluation.setDate(new java.sql.Date(d.getTime()));
        evaluation.setStudentByScholar(student);
        if (evaluationService.addEvaluation(evaluation))
            return SUCCESS;
        else
            return ERROR;
    }

    public String modifyEvaluation() {
        Map session = ActionContext.getContext().getSession();
        evaluation = (Evaluation) session.get("evaluation");
        evaluation.setContent(content);
        if (evaluationService.updateEvaluation(evaluation)) {
            session.put("evaluation", evaluation);
            return SUCCESS;
        } else
            return ERROR;
    }

    public String listEvaluation() {
        Map session = ActionContext.getContext().getSession();
        Student student =  (Student)session.get("student");
        String data = new SimpleDateFormat("yyyy").format(new Date()) + "-01-01";
        System.out.println(student.getScholar() + " " + data);
        Evaluation evaluation =  evaluationService.getEvaluation(student.getScholar(), data);
        if (evaluation == null) {
            session.put("error", "教练尚未作出评价");
            return ERROR;
        } else {
            session.put("evaluation", evaluation);
            return SUCCESS;
        }
    }

    public String preEvaluation() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        String date = new SimpleDateFormat("yyyy").format(new Date()) + "-01-01";
        System.out.println(scholar + " " + date);
        Evaluation evaluation =  evaluationService.getEvaluation(scholar, date);
        Map session = ActionContext.getContext().getSession();
        if (evaluation == null) {
            session.put("scholar", scholar);
            return "addEvaluation";
        } else {
            System.out.println(evaluation.getStudentByScholar().getName());
            session.put("evaluation", evaluation);
            return "listEvaluation";
        }
    }
}
