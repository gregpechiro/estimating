package com.cagnosolutions.cei.company.appname.controller.admin;

import com.cagnosolutions.cei.company.appname.service.FlashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private FlashService flashService;

	@RequestMapping(method=RequestMethod.GET)
	public String home(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
        return "admin/home";
	}

    @RequestMapping(value="/edit/settings", method=RequestMethod.POST)
    public String editPricing(RedirectAttributes attr) {
        flashService.flash(attr, "update.success");
        return "redirect:/admin";
    }
}
