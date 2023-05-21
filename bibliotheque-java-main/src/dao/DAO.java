package dao;

import java.util.List;

// import java.util.List;

public abstract class DAO<T> {
	public abstract List<T> findAll();
	public abstract T findById(int id);
//    public abstract T update();
}
