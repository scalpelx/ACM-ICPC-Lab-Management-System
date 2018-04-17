package service;

import entity.Student;
import entity.Train;

import java.util.List;

public interface TrainService {

    public boolean addTrain(Train train);
    public List getTrains();
    public List getStudentTrains(Student student);
}
