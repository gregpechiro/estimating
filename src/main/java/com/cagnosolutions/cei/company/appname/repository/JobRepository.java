package com.cagnosolutions.cei.company.appname.repository;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(nativeQuery = true, value = "select * from estimating.job where job.customer_id=:customerId and job.id=:jobId")
	public Job findCustomersJob(@Param("customerId")Long customerId, @Param("jobId")Long jobId);
}