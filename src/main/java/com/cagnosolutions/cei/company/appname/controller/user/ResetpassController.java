package com.cagnosolutions.cei.company.appname.controller.user;

import com.cagnosolutions.cei.company.appname.domain.User;
import com.cagnosolutions.cei.company.appname.service.FlashService;
import com.cagnosolutions.cei.company.appname.service.MailService;
import com.cagnosolutions.cei.company.appname.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("resetPassController")
public class ResetpassController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private FlashService flashService;

    @RequestMapping(value = "/resetpass", method = RequestMethod.GET)
    public String resetpassForm() {
        return "resetpass";
    }

    @RequestMapping(value = "/resetpass", method = RequestMethod.POST)
    public String resetpassForm(@RequestParam("username") String username, @RequestParam("email") String email, RedirectAttributes attr) {
        User user = userService.findById(username);
        if (user != null) {
            if (email.equals(user.getEmail())) {
                StringBuilder body = new StringBuilder();
                body.append("Click below to reset your password:\n\n");
                body.append("http://localhost:8080/changepass/");
                body.append(user.getUsername());
                body.append("/");
                body.append(user.getPassword().substring(10, 20));
                mailService.sendSimpleEmail("noreply@exampledomain.com", "Reset Password", body.toString(), user.getEmail());
            }
        }
        flashService.flash(attr, "resetpass.success");
        return "redirect:/home";
    }

    @RequestMapping(value = "/changepass/{username}/{password}", method = RequestMethod.GET)
    public String changepassForm(Model model, @PathVariable("username") String username, @PathVariable("password") String password, RedirectAttributes attr) {
        User user = userService.findById(username);
        if (user != null) {
            if (user.getPassword().substring(10, 20).equals(password)) {
                model.addAttribute("username", user.getUsername());
                flashService.flashAlert(attr, "Please change your password", "info", true);
                return "changepass";
            }
        }
        flashService.flash(attr, "resetpass.error");
        return "redirect:/home";
    }

    @RequestMapping(value = "/changepass", method = RequestMethod.POST)
    public String changePass(@RequestParam("password") String password, @RequestParam("confirm") String confirm, @RequestParam("username") String username, RedirectAttributes attr) {
        User user = userService.findById(username);
        if (user == null) {
            flashService.flash(attr, "user.error");
            return "redirect:/home";
        }
        if (password == null || confirm == null || !password.equals(confirm)) {
            flashService.flash(attr, "resetpass.error");
            return "redirect:/changepass/" + user.getUsername() + "/" + user.getPassword().substring(10, 20);
        }
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userService.update(user);
        flashService.flash(attr, "password.success");
        return "redirect:/home";
    }
}
