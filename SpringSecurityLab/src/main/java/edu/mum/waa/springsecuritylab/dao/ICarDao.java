package edu.mum.waa.springsecuritylab.dao;


import edu.mum.waa.springsecuritylab.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICarDao {

	public abstract List<Car> getAll();

	public abstract void add(Car car);

	public abstract Car get(int id);

	public abstract void update(int carId, Car car);

	public abstract void delete(int carId);

}