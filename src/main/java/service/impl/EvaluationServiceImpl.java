package service.impl;

import dao.EvaluationDao;
import entity.Evaluation;
import org.springframework.stereotype.Service;
import service.EvaluationService;

import javax.annotation.Resource;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService {
    @Resource
    private EvaluationDao evaluationDao;

    @Override
    public boolean addEvaluation(Evaluation evaluation) {
        return evaluationDao.add(evaluation);
    }

    @Override
    public boolean updateEvaluation(Evaluation evaluation) {
        return evaluationDao.update(evaluation);
    }

    @Override
    public boolean deleteEvaluation(Evaluation evaluation) {
        return evaluationDao.delete(evaluation);
    }

    @Override
    public Evaluation getEvaluation(String scholar, String date) {
        return evaluationDao.list(scholar, date);
    }
}
