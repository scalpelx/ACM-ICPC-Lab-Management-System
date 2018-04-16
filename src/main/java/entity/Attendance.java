package entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "attendance")
public class Attendance {
    private Date date;
    private Time arriveTime;
    private Time leaveTime;
    private Student studentByScholar;

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "arriveTime", nullable = false)
    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Basic
    @Column(name = "leaveTime", nullable = false)
    public Time getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Time leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attendance that = (Attendance) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (arriveTime != null ? !arriveTime.equals(that.arriveTime) : that.arriveTime != null) return false;
        if (leaveTime != null ? !leaveTime.equals(that.leaveTime) : that.leaveTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (arriveTime != null ? arriveTime.hashCode() : 0);
        result = 31 * result + (leaveTime != null ? leaveTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "scholar", referencedColumnName = "scholar", nullable = false)

    public Student getStudentByScholar() {
        return studentByScholar;
    }

    public void setStudentByScholar(Student studentByScholar) {
        this.studentByScholar = studentByScholar;
    }
}
