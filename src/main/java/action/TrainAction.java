package action;

import com.google.common.collect.Sets;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Student;
import entity.Train;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentService;
import service.TrainService;
import util.Problems;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    public String modifyTrain() {
        List<Student> students = new ArrayList();
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String[] scholars = request.getParameterValues("scholars");
        for (String scholar : scholars) {
            students.add(studentService.getStudentByScholar(scholar));
        }
        train.setStudents(students);
        trainService.updateTrain(train);
        List trains = trainService.getTrains();
        Map session = ActionContext.getContext().getSession();
        session.put("trains", trains);
        return SUCCESS;
    }

    public String viewStudentTrain() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String id = request.getParameter("id");
        Train train = trainService.getTrainById(Integer.parseInt(id));
        String problems = train.getProblems();
        String s[] = problems.split(" ");
        Set<String> problemSet = new TreeSet();
        for (String i : s)
            if (!i.isEmpty()) {
                //System.out.println(i);
                problemSet.add(i);
            }
        Map session = ActionContext.getContext().getSession();
        session.put("train", train);
        Student student = (Student) session.get("student");
        Problems p = new Problems();
        Set<String> acProblem = p.getACProblems(student.getHdu());

        Set<String> done = Sets.intersection(problemSet, acProblem);
        session.put("done", done);
        Set<String> none = Sets.difference(problemSet, acProblem);
        session.put("none", none);
        return SUCCESS;
    }

    public String listTrainDetail() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        String id = request.getParameter("id");
        Train train = trainService.getTrainById(Integer.parseInt(id));
        String problems = train.getProblems();
        String s[] = problems.split(" ");
        Set<String> problemSet = new HashSet<String>();
        for (String i : s)
            if (!i.isEmpty()) {
                //System.out.println(i);
                problemSet.add(i);
            }
        Map session = ActionContext.getContext().getSession();
        session.put("train", train);
        List<Student> students = train.getStudents();
        session.put("students", students);
        List<Set<String>> dones = new ArrayList<>();
        List<Set<String>> nones = new ArrayList<>();
        for (Student student : students) {
            Problems p = new Problems();
            Set<String> acProblem = p.getACProblems(student.getHdu());
            dones.add(Sets.intersection(problemSet, acProblem));
            nones.add(Sets.difference(problemSet, acProblem));
        }
        session.put("dones", dones);
        session.put("nones", nones);
        return SUCCESS;
    }
}
