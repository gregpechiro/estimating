package com.cagnosolutions.cei.company.appname.controller.job;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "jobController")
public class JobController {

    @Autowired
    private JobService jobService;

    // list get
    @RequestMapping(value = "/list/job", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        model.addAttribute("jobs", jobService.findAllSorted(sort, order));
        return "job/list";
    }

    // add get
    @RequestMapping(value = "/add/job", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("job", new Job());
        return "job/add";
    }

    // add post
    @RequestMapping(value = "/add/job", method = RequestMethod.POST)
    public String add(Job job) {
        jobService.insert(job);
        return "redirect:/add/job?added";
    }

    // view get
    @RequestMapping(value = "/view/job/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.findById(id));
        return "job/view";
    }

    // delete post
    @RequestMapping(value = "/del/job/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        jobService.delete(jobService.findById(id));
        return "redirect:/list/job?removed";
    }

    // edit get
    @RequestMapping(value = "/edit/job/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.findById(id));
        return "job/edit";
    }

    // edit post
    @RequestMapping(value = "/edit/job/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Job job) {
    
	    /*
	     *  Implement edit/update
	     */

        return "redirect:/edit/job/" + id + "?status";
    }
}
