package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    private String scholar;
    private String name;
    private String stdclass;
    private String passwd;
    private String phone;
    private int sex;
    private String hdu;
    private List<Train> trains;

    @Id
    @Column(name = "scholar", nullable = false, length = 15)
    public String getScholar() {
        return scholar;
    }

    public void setScholar(String scholar) {
        this.scholar = scholar;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "stdclass", nullable = false, length = 20)
    public String getStdclass() {
        return stdclass;
    }

    public void setStdclass(String stdclass) {
        this.stdclass = stdclass;
    }

    @Basic
    @Column(name = "passwd", nullable = false, length = 20)
    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Basic
    @Column(name = "phone", nullable = false, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sex", nullable = false)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "hdu", nullable = false, length = 20)
    public String getHdu() {
        return hdu;
    }

    public void setHdu(String hdu) {
        this.hdu = hdu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (sex != student.sex) return false;
        if (scholar != null ? !scholar.equals(student.scholar) : student.scholar != null) return false;
        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (stdclass != null ? !stdclass.equals(student.stdclass) : student.stdclass != null) return false;
        if (passwd != null ? !passwd.equals(student.passwd) : student.passwd != null) return false;
        if (phone != null ? !phone.equals(student.phone) : student.phone != null) return false;
        if (hdu != null ? !hdu.equals(student.hdu) : student.hdu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = scholar != null ? scholar.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (stdclass != null ? stdclass.hashCode() : 0);
        result = 31 * result + (passwd != null ? passwd.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + sex;
        result = 31 * result + (hdu != null ? hdu.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }
}
