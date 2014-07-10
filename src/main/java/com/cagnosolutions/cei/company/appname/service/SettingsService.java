package com.cagnosolutions.cei.company.appname.service;
/**
 * Created by Scott Cagno.
 * Copyright Cagno Solutions. All rights reserved.
 */


import com.cagnosolutions.cei.company.appname.domain.Settings;
import com.cagnosolutions.cei.company.appname.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("settingsService")
public class SettingsService {

    @Autowired
    private SettingsRepository dao;

    public Settings insert(Settings settings) {
        return dao.saveAndFlush(settings);
    }

    public void update(Settings settings) {
        dao.save(settings);
    }

    public void delete(Settings settings) {
        dao.delete(settings);
    }

    public List<Settings> findAll() {
        return dao.findAll();
    }

    public Settings findById(Long id) {
        return dao.findOne(id);
    }

    public boolean exists(Long id) {
        return dao.exists(id);
    }

    public List<Settings> findAllSorted(String sort, String order) {
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
