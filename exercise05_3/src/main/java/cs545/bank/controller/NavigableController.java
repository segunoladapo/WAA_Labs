package cs545.bank.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

@ManagedBean(eager = true)
@ApplicationScoped
public class NavigableController implements Serializable {
    static final long serialVersionUID = -687991492884005033L;

    public String getCreateAccountPage(){
        return "createAccount.xhtml";
    }


}
