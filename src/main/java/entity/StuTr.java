package entity;

import javax.persistence.*;

@Entity
@Table(name = "stu_tr", schema = "ACM", catalog = "")
public class StuTr {
    private int id;
    private Student studentByScholar;
    private Train trainByTrainId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuTr stuTr = (StuTr) o;

        if (id != stuTr.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "scholar", referencedColumnName = "scholar")
    public Student getStudentByScholar() {
        return studentByScholar;
    }

    public void setStudentByScholar(Student studentByScholar) {
        this.studentByScholar = studentByScholar;
    }

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "id")
    public Train getTrainByTrainId() {
        return trainByTrainId;
    }

    public void setTrainByTrainId(Train trainByTrainId) {
        this.trainByTrainId = trainByTrainId;
    }
}
