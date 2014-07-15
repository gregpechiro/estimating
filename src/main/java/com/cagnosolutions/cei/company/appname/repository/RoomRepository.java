package com.cagnosolutions.cei.company.appname.repository;

/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	@Query(nativeQuery = true, value = "select room.* from estimating.job, estimating.room where job.customer_id=:customerId and job.id=:jobId and room.id=:roomId and room.job_id=:jobId")
	public Room findCustomersJobsRoom(@Param("customerId")Long customerId, @Param("jobId")Long jobId, @Param("roomId")Long roomId);

}