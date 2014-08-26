package com.cagnosolutions.cei.company.appname.controller.customer;

import com.cagnosolutions.cei.company.appname.domain.Customer;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import com.cagnosolutions.cei.company.appname.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/customer")
@Controller(value = "customerController")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private GlobalService globalService;

    // add customer post
    @RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
    public String add(Customer customer) {
		customerService.update(customer);
        return "customer added";
    }

    // view customer get
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public String view(@PathVariable Long customerId, Model model) {
		model.addAttribute("customer", globalService.getCustomer(customerId));
        return "test/customer";
    }

    // edit/delete customer post
    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
	@ResponseBody
    public String delete(@RequestParam(value = "action") String action) {
        return "";
    }
}
