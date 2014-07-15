package com.cagnosolutions.cei.company.appname.controller;

import com.cagnosolutions.cei.company.appname.domain.User;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import com.cagnosolutions.cei.company.appname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("mainController")
public class MainController {

    @Autowired
    private UserService userService;

	@Autowired
	private CustomerService customerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/app/home";
    }


	// all customers view
    @RequestMapping(value = "/app/home", method = RequestMethod.GET)
    public String home(Model model) {
		model.addAttribute("customers", customerService.findAll());
        return "test/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(User user, @RequestParam("confirm") String confirm) {
        if (!userService.exists(user.getUsername()) &&
                userService.usernameIsValid(user.getUsername()) &&
                confirm.equals(user.getPassword())) {
            user.setActive(true);
            user.setRole("ROLE_USER");
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.insert(user);
            return "redirect:/home?success";
        }
        return "redirect:/register?error";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView errorHandler(Exception e) {
        ModelAndView view = new ModelAndView("main/exception");
        view.addObject("msg", e.getLocalizedMessage());
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement frame : e.getStackTrace())
            sb.append(frame.toString()).append("\n");
        view.addObject("ex", sb.toString());
        return view;
    }
}
