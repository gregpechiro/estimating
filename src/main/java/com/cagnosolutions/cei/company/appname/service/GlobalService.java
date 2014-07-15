package com.cagnosolutions.cei.company.appname.service;

import com.cagnosolutions.cei.company.appname.domain.Customer;
import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.repository.CustomerRepository;
import com.cagnosolutions.cei.company.appname.repository.JobRepository;
import com.cagnosolutions.cei.company.appname.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("globalService")
public class GlobalService {

	@Autowired
	private CustomerRepository customerDoa;

	@Autowired
	private JobRepository jobDao;

	@Autowired
	private RoomRepository roomDao;

	public Customer getCustomer(Long customerId) {
		return customerDoa.findOne(customerId);
	}

	public Job getCustomersJob(Long customerId, Long jobId) {
		return jobDao.findCustomersJob(customerId, jobId);
	}

	public Room getCustomersJobsRoom(Long customerId, Long jobId, Long roomId) {
		return roomDao.findCustomersJobsRoom(customerId, jobId, roomId);
	}
}
