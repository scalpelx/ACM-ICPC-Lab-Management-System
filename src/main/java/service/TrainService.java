package service;

import entity.Student;
import entity.Train;

import java.util.List;

public interface TrainService {

    public boolean addTrain(Train train);
    public boolean deleteTrain(String id);
    public boolean updateTrain(Train train);
    public Train getTrainById(int id);
    public List getTrains();
    public List getStudentTrains(Student student);
}
