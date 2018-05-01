package dao;

import entity.Evaluation;

public interface EvaluationDao {
    public boolean add(Evaluation evaluation);
    public boolean update(Evaluation evaluation);
    public boolean delete(Evaluation evaluation);
    public Evaluation list(String scholar, String date);
}
