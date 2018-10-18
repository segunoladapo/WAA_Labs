package cs545.bank.domain;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;



@ManagedBean
@RequestScoped
public class Customer {

	public Customer(){
		this.name  = "Customer Class initialized here";
		System.out.println(name);
	}

	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
