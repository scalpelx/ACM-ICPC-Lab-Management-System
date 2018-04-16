package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Student;
import entity.Train;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.StudentService;
import service.TrainService;

import javax.annotation.Resource;
import java.util.List;

@Controller("trainAction")
@Scope("prototype")
public class TrainAction extends ActionSupport {

    @Resource
    private TrainService trainService;
    @Resource
    StudentService studentService;

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
        List<Student> students = null;
        students.add(studentService.getStudentByScholar(stuScholars));
        students.add(studentService.getStudentByScholar("20141222171"));
        train.setStudents(students);
        trainService.addTrain(train);
        return SUCCESS;
    }
}
