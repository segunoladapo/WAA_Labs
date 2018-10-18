package edu.mum.waa.springsecuritylab.controller;

import edu.mum.waa.springsecuritylab.dao.BookDao;
import edu.mum.waa.springsecuritylab.dao.CarDao;
import edu.mum.waa.springsecuritylab.dao.UserDao;
import edu.mum.waa.springsecuritylab.model.User;
import edu.mum.waa.springsecuritylab.security.AuthenticationWithToken;
import edu.mum.waa.springsecuritylab.service.EhTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private EhTokenService ehTokenService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CarDao carDao;

    @Autowired
    private BookDao bookDao;

    @RequestMapping("/")
    public String index() {
        return "welcome";
    }

    @RequestMapping("/login")
    //@PreAuthorize("hasRole('ANYONE')")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticate(User request, Model model) {
        User user = userDao.findUser(request.getUsername());
        if (user == null) {
            return "redirect:/login";
        }

        if (!user.getPassword().equals(request.getPassword())) {
            return "redirect:/login";
        }

        String token = ehTokenService.setToken(new AuthenticationWithToken(user,
                null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + user.getRole())));
        return "redirect:/dashboard?X-Auth-Token=" + token;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACCOUNT','ROLE_SALES')")
    public String viewDashBoard(Model model, HttpServletRequest request) {
        String token = request.getParameter("X-Auth-Token");
        model.addAttribute("cars", carDao.getAll());
        model.addAttribute("books", bookDao.getAll());
        model.addAttribute("token", token);
        return "dashboard";
    }
}
