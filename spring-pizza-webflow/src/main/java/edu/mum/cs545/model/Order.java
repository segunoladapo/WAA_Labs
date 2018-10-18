package edu.mum.cs545.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order implements Serializable {
	private static final long serialVersionUID = 7187645190789626221L;
	
	private Customer cust;
    private List<Pizza> pizzaList = new ArrayList<Pizza>();
	public Customer getCust() {
		return cust;
	}
	public void setCust(Customer cust) {
		this.cust = cust;
	}
	public List<Pizza> getPizzaList() {
		return Collections.unmodifiableList(pizzaList);
	}
	
	public void addPizza(Pizza pizza) {
		pizzaList.add(pizza);
	}

	public void removePizza(Pizza pizza) {
		pizzaList.remove(pizza);
	}

	public void clearPizzas() {
		pizzaList.clear();
		
	}
    
    
}
