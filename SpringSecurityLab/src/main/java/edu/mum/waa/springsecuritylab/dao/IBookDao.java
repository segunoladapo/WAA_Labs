package edu.mum.waa.springsecuritylab.dao;

import edu.mum.waa.springsecuritylab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookDao {

	public abstract List<Book> getAll();

	public abstract void add(Book book);

	public abstract Book get(int id);

	public abstract void update(int bookId, Book book);

	public abstract void delete(int bookId);

}