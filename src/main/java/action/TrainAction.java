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
    private String stuScholars;

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getStuScholars() {
        return stuScholars;
    }

    public void setStuScholars(String stuScholars) {
        this.stuScholars = stuScholars;
    }

    public String addTrain() {
        List<Student> students = new ArrayList();
        System.out.println(stuScholars);
        if (studentService.getStudentByScholar(stuScholars) == null)
            System.out.println("none");
        students.add(studentService.getStudentByScholar(stuScholars));
        students.add(studentService.getStudentByScholar("20141222171"));
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
}
