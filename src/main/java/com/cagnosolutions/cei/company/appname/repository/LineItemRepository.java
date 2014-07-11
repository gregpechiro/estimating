package com.cagnosolutions.cei.company.appname.repository;
/**
 * Created by greg on 7/11/14.
 */

import com.cagnosolutions.cei.company.appname.domain.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

}