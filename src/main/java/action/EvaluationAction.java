package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Evaluation;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.EvaluationService;
import service.StudentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    private String content;

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String addEvaluation() {
        evaluation.setContent(content);
        if (evaluationService.addEvaluation(evaluation))
            return SUCCESS;
        else
            return ERROR;
    }

    public String deleteEvaluation() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("id"));
        if (evaluationService.deleteEvaluation(evaluation))
            return SUCCESS;
        else
            return ERROR;
    }

    public String modifyEvaluation() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        int id = Integer.parseInt(request.getParameter("id"));
        if (evaluationService.updateEvaluation(evaluation))
            return SUCCESS;
        else
            return ERROR;
    }

    public String listEvaluation() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        String data = new SimpleDateFormat("yyyy").format(new Date());
        Evaluation evaluation =  evaluationService.getEvaluation(scholar, data);
        if (evaluation == null) {
            evaluation.setStudentByScholar(studentService.getStudentByScholar(scholar));
            evaluation.setDate(new java.sql.Date(new Date().getTime()));
        }
        Map session = ActionContext.getContext().getSession();
        session.put("evaluation", evaluation);
        return SUCCESS;
    }

}
