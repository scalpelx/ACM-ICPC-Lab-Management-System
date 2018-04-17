package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Student;
import entity.Train;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentService;
import service.TrainService;
import sun.security.krb5.SCDynamicStoreConfig;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("trainAction")
@Scope("prototype")
public class TrainAction extends ActionSupport {

    @Resource
    private TrainService trainService;
    @Resource
    private StudentService studentService;

    private Train train;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String pre() {
        Map session = ActionContext.getContext().getSession();
        List students = studentService.getAllStudent();
        session.put("students", students);
        return SUCCESS;
    }

    public String addTrain() {
        List<Student> students = new ArrayList();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String[] scholars = request.getParameterValues("scholars");
        for (String scholar : scholars) {
            System.out.println(scholar);
            students.add(studentService.getStudentByScholar(scholar));
        }
        train.setStudents(students);
        trainService.addTrain(train);
        return SUCCESS;
    }

    public String getTrains() {
        Map session = ActionContext.getContext().getSession();
        List trains = trainService.getTrains();
        session.put("trains", trains);
        return SUCCESS;
    }

    public String listStudentTrains() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String scholar = request.getParameter("scholar");
        Student student = studentService.getStudentByScholar(scholar);
        Map session = ActionContext.getContext().getSession();
        List trains = trainService.getStudentTrains(student);
        if (trains.isEmpty())
            System.out.println("trains empty");
        session.put("trains", trains);
        return SUCCESS;
    }

    public String deleteTrain() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String id = request.getParameter("id");
        trainService.deleteTrain(id);
        List<Train> trains = trainService.getTrains();
        Map session = ActionContext.getContext().getSession();
        session.put("trains", trains);
        return SUCCESS;
    }
}
