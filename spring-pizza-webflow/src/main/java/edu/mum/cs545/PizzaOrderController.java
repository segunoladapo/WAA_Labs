package edu.mum.cs545;

import java.util.ArrayList;
import java.util.List;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.action.EventFactorySupport;
import org.springframework.webflow.execution.Event;

import edu.mum.cs545.model.Customer;
import edu.mum.cs545.model.Order;
import edu.mum.cs545.model.Pizza;

/**
 * Handles requests for the application home page.
 */
@Controller
public class PizzaOrderController {

	public Event validateCust(Order order, MessageContext messageContext) {

		if (order.getCust().getFirstName() == null || order.getCust().getFirstName().trim() == "") {
			MessageBuilder errorMessageBuilder = new MessageBuilder().error();
			errorMessageBuilder.source("cust.firstName");
			errorMessageBuilder.code("firstName.req");
			messageContext.addMessage(errorMessageBuilder.build());
			return new EventFactorySupport().error(this);
		}
		return new EventFactorySupport().success(this);
	}

	public Order createOrder() {
		Order order = new Order();
		Customer cust = new Customer();
		order.setCust(cust);
		return order;
	}

	public List<String> initPizzaList() {
		List<String> pizzaList = new ArrayList<String>();
		pizzaList.add("NY Cheese Pizza");
		pizzaList.add("Indian Spicy Pizza");
		pizzaList.add("Italian Special Pizza");

		return pizzaList;
	}

	public Pizza createPizza() {
		return new Pizza();

	}

	public void addPizza(Order order, Pizza pizza) {
		order.addPizza(pizza);
	}

	public void clearPizzas(Order order) {
		order.clearPizzas();
	}

	public String hasComplementaryDrinks(Order order) {
		String outcome = "no";
		for (Pizza pizza : order.getPizzaList()) {
			if ("Indian Spicy Pizza".equals(pizza.getName())) {
				outcome = "yes";
				break;
			}
		}
		return outcome;
	}

	public void submitOrder(Order order) {

		try {
			System.out.println("Customer =" + order.getCust());
			for (Pizza p : order.getPizzaList()) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
