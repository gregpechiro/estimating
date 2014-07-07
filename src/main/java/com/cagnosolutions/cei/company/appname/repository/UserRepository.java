package com.cagnosolutions.cei.company.appname.repository;

import com.cagnosolutions.cei.company.appname.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
