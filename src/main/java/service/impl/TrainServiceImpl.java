package service.impl;

import dao.TrainDao;
import entity.Train;
import org.springframework.stereotype.Service;
import service.TrainService;

import javax.annotation.Resource;

@Service("trainService")
public class TrainServiceImpl implements TrainService {

    @Resource
    private TrainDao trainDao;

    @Override
    public boolean addTrain(Train train) {
        return this.trainDao.add(train);
    }
}
