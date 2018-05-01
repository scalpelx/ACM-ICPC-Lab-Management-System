package service;

import entity.Evaluation;

public interface EvaluationService {

    public boolean addEvaluation(Evaluation evaluation);
    public boolean updateEvaluation(Evaluation evaluation);
    public boolean deleteEvaluation(Evaluation evaluation);
    public Evaluation getEvaluation(String scholar, String date);
}
