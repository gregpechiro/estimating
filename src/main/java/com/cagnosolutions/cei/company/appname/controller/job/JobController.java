package com.cagnosolutions.cei.company.appname.controller.job;

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import com.cagnosolutions.cei.company.appname.service.GlobalService;
import com.cagnosolutions.cei.company.appname.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/customer/{customerId}/job")
@Controller(value = "jobController")
public class JobController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JobService jobService;

	@Autowired
	private GlobalService globalService;

    // add job post
    @RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
    public String add(@PathVariable Long customerId, Job job) {
		customerService.addJobToCustomer(customerId, job);
		return "job added";
    }

    // view job get
    @RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
    public String view(@PathVariable Long customerId, @PathVariable Long jobId, Model model) {
		Job job = globalService.getCustomersJob(customerId, jobId);
		if (job == null) {
			return "redirect:/";
		}
		model.addAttribute("job", job);
		return "test/job";
    }

    // edit/delete job post
    @RequestMapping(value = "/{jobId}", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "action") String action, @PathVariable Long customerId, @PathVariable Long jobId, Job job) {
		Job existingJob = globalService.getCustomersJob(customerId, jobId);
		if (existingJob == null) {
			return "redirect:/";
		}
		if (action.equals("delete")) {
			jobService.delete(existingJob);
			return "redirect:/app/customer/" + customerId;
		}
		existingJob.setName(job.getName());
        return "redirect:/app/customer/" + customerId + "/job/" + jobId;
    }

}
