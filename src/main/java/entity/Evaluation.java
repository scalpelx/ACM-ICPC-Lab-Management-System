package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    private int id;
    private Date date;
    private String content;
    private Student studentByScholar;
    private String selfEva;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 65535)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluation that = (Evaluation) o;
        return id == that.id &&
                Objects.equals(date, that.date) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, date, content);
    }

    @ManyToOne
    @JoinColumn(name = "scholar", referencedColumnName = "scholar", nullable = false)
    public Student getStudentByScholar() {
        return studentByScholar;
    }

    public void setStudentByScholar(Student studentByScholar) {
        this.studentByScholar = studentByScholar;
    }

    @Basic
    @Column(name = "self_eva", nullable = true, length = 65535)
    public String getSelfEva() {
        return selfEva;
    }

    public void setSelfEva(String selfEva) {
        this.selfEva = selfEva;
    }
}
