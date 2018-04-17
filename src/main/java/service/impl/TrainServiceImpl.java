package service.impl;

import dao.TrainDao;
import entity.Student;
import entity.Train;
import org.springframework.stereotype.Service;
import service.TrainService;

import javax.annotation.Resource;
import java.util.List;

@Service("trainService")
public class TrainServiceImpl implements TrainService {

    @Resource
    private TrainDao trainDao;

    @Override
    public boolean addTrain(Train train) {
        return this.trainDao.add(train);
    }

    @Override
    public List getTrains() {
        return this.trainDao.getTrains();
    }

    @Override
    public List getStudentTrains(Student student) {
        return trainDao.getStudentTrains(student);
    }
}
