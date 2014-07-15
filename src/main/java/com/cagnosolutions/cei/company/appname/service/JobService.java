package com.cagnosolutions.cei.company.appname.service;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.repository.JobRepository;
import com.cagnosolutions.cei.company.appname.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("jobService")
public class JobService {

    @Autowired
    private JobRepository dao;

	@Autowired
	private RoomRepository roomDao;

    public Job insert(Job job) {
        return dao.saveAndFlush(job);
    }

    public void update(Job job) {
        dao.save(job);
    }

    public void delete(Job job) {
        dao.delete(job);
    }

    public List<Job> findAll() {
        return dao.findAll();
    }

    public Job findById(Long id) {
        return dao.findOne(id);
    }

    public boolean exists(Long id) {
        return dao.exists(id);
    }

    public List<Job> findAllSorted(String sort, String order) {
        if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
            return dao.findAll();
        if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
            return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
        return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
    }

    private static boolean isEmpty(String string) {
        return (string == null || string.equals(""));
    }


	public void addRoomToJob(Long jobId, Room room) {
		roomDao.save(room);
		Job job = dao.findOne(jobId);
		job.addRoom(room);
		dao.save(job);
	}

	public Map<String, String> getHtml(Long customerId, Long jobId) {
		Map<String, String> map = new HashMap<>();
		map.put("url", String.format("/app/customer/%d/job/%d", customerId, jobId));
		map.put("breadcrumb", String.format(
			"<ol class=\"breadcrumb\">"+
				"<li><a href=\"/\">Customers</a></li>"+
				"<li><a href=\"/app/customer/%d\">Customer #%d</a></li>"+
				"<li class=\"active\">Job #%d</li>"+
			"</ol>",
			customerId, customerId,
			jobId
			)
		);
		return map;
	}
}
