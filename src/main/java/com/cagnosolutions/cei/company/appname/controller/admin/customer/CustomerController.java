package com.cagnosolutions.cei.company.appname.controller.admin.customer;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Customer;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "adminCustomerController")
@RequestMapping("/admin")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // list get
    @RequestMapping(value = "/list/customer", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        model.addAttribute("customer", customerService.findAllSorted(sort, order));
        return "admin/customer/list";
    }

    // add get
    @RequestMapping(value = "/add/customer", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "admin/customer/add";
    }

    // add post
    @RequestMapping(value = "/add/customer", method = RequestMethod.POST)
    public String add(Customer customer) {
        customerService.insert(customer);
        return "redirect:/admin/add/customer?added";
    }

    // view get
    @RequestMapping(value = "/view/customer/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "admin/customer/view";
    }

    // delete post
    @RequestMapping(value = "/del/customer/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        customerService.delete(customerService.findById(id));
        return "redirect:/admin/list/customer?removed";
    }

    // edit get
    @RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "admin/customer/edit";
    }

    // edit post
    @RequestMapping(value = "/edit/customer/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Customer customer) {
    
	    /*
	     *  Implement edit/update
	     */

        return "redirect:/admin/edit/customer/" + id + "?status";
    }
}
