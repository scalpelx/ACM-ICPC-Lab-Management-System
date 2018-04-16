package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "train")
public class Train {
    private int id;
    private String name;
    private Timestamp beginDate;
    private Timestamp endDate;
    private String problems;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "beginDate", nullable = false)
    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    @Basic
    @Column(name = "endDate", nullable = false)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "problems", nullable = false, length = 100)
    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (id != train.id) return false;
        if (name != null ? !name.equals(train.name) : train.name != null) return false;
        if (beginDate != null ? !beginDate.equals(train.beginDate) : train.beginDate != null) return false;
        if (endDate != null ? !endDate.equals(train.endDate) : train.endDate != null) return false;
        if (problems != null ? !problems.equals(train.problems) : train.problems != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (beginDate != null ? beginDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (problems != null ? problems.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "trains")
    private List<Student> students;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
