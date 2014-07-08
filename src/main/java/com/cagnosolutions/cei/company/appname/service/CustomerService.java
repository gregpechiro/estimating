package com.cagnosolutions.cei.company.appname.service;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Customer;
import com.cagnosolutions.cei.company.appname.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("customerService")
public class CustomerService {

    @Autowired
    private CustomerRepository dao;

    public Customer insert(Customer customer) {
        return dao.saveAndFlush(customer);
    }

    public Customer update(Customer customer) {
        return dao.save(customer);
    }

    public void delete(Customer customer) {
        dao.delete(customer);
    }

    public List<Customer> findAll() {
        return dao.findAll();
    }

    public Customer findById(Long id) {
        return dao.findOne(id);
    }

    public boolean exists(Long id) {
        return dao.exists(id);
    }

    public List<Customer> findAllSorted(String sort, String order) {
        if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
            return dao.findAll();
        if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
            return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
        return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
    }

    private static boolean isEmpty(String string) {
        return (string == null || string.equals(""));
    }

}
