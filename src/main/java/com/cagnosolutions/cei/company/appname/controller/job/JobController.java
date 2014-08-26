package com.cagnosolutions.cei.company.appname.controller.job;

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.service.CustomerService;
import com.cagnosolutions.cei.company.appname.service.FlashService;
import com.cagnosolutions.cei.company.appname.service.GlobalService;
import com.cagnosolutions.cei.company.appname.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/app/customer/{customerId}/job")
@Controller(value = "jobController")
public class JobController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JobService jobService;

	@Autowired
	private GlobalService globalService;

	@Autowired
	private FlashService flashService;

    // add/edit job post
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(@PathVariable Long customerId, Job job, RedirectAttributes attr) {
		if (job.getId() == null) {
			// add
			flashService.flash(attr, "add.success");
			customerService.addJobToCustomer(customerId, job);
			return "redirect:/app/customer/" + customerId;
		}
		//edit
		Job existingJob = globalService.getCustomersJob(customerId, job.getId());
		if (existingJob == null) {
			flashService.flash(attr, "error");
			return "redirect:/";
		}
		existingJob.setName(job.getName());
		jobService.update(existingJob);
		flashService.flash(attr, "update.success");
		return "redirect:/app/customer/" + customerId + "/job/" + job.getId();
    }

    // view job get
    @RequestMapping(value = "/{jobId}", method = RequestMethod.GET)
    public String view(@PathVariable Long customerId, @PathVariable Long jobId, Model model, RedirectAttributes attr) {
		Job job = globalService.getCustomersJob(customerId, jobId);
		if (job == null) {
			flashService.flash(attr, "error");
			return "redirect:/";
		}
		model.addAttribute("job", job);
		return "test/job";
    }

    // delete job post
    @RequestMapping(value = "/{jobId}", method = RequestMethod.POST)
    public String delete(@PathVariable Long customerId, @PathVariable Long jobId, RedirectAttributes attr) {
		Job job = globalService.getCustomersJob(customerId, jobId);
		if (job == null) {
			flashService.flash(attr, "delete.error");
			return "redirect:/";
		}
		jobService.delete(job);
		flashService.flash(attr, "delete.success");
		return "redirect:/app/customer/" + customerId;
	}
}
