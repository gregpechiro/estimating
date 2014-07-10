package com.cagnosolutions.cei.company.appname.controller.job;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Customer;
import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import com.cagnosolutions.cei.company.appname.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller(value = "jobController")
public class JobController {

    @Autowired
    private JobService jobService;

	@Autowired
	private CustomerService customerService;

    // list get
    @RequestMapping(value = "/list/job", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        model.addAttribute("jobs", jobService.findAllSorted(sort, order));
        return "job/list";
    }

    // add post
    @RequestMapping(value = "/add/job", method = RequestMethod.POST)
    public String add(Model model, @RequestParam(value="customerId") Long customerId) {
		Customer customer = customerService.findById(customerId);
		Collection<Job> jobs = customer.getJobs();
		Job newJob = new Job();
		newJob.setStatus((short) 1);
		jobs.add(newJob);
		customer.setJobs(jobs);
		customer = customerService.update(customer);
		Long newJobId = ((Job) customer.getJobs().toArray()[customer.getJobCount() -1]).getId();
        return "redirect:/view/job/" + newJobId;
    }

    // view get
    @RequestMapping(value = "/view/job/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("job", jobService.findById(id));
		return "job/view";
    }

    // delete post
    @RequestMapping(value = "/del/job/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model,  @RequestParam(value="customerId") Long customerId) {
        jobService.delete(jobService.findById(id));
        return "redirect:/view/customer/" + customerId;
    }

    // edit post
    @RequestMapping(value = "/edit/job/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Job job) {
		Job existingJob = jobService.findById(id);
		existingJob.setDescription(job.getDescription());
		existingJob.setNotes(job.getNotes());
    	jobService.update(existingJob);
        return "redirect:/view/job/" + id;
    }

	// next status
	@RequestMapping(value="/next/status/{id}", method=RequestMethod.GET)
	public String next(@PathVariable("id") Long id) {
		Job job = jobService.findById(id);
		job.nextStatus();
		jobService.update(job);
		return "redirect:/view/job/" + id;
	}
}
