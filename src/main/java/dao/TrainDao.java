package dao;

import entity.Student;
import entity.Train;

import java.util.List;

public interface TrainDao {
    public boolean add(Train train);
    public boolean delete(String id);
    public boolean update(Train train);
    public Train getTrain(int id);
    public List getStudents(int id);
    public List getTrains();
    public List getStudentTrains(Student student);
}
