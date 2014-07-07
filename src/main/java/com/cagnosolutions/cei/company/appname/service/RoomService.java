package com.cagnosolutions.cei.company.appname.service;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("roomService")
public class RoomService {

    @Autowired
    private RoomRepository dao;

    public Room insert(Room room) {
        return dao.saveAndFlush(room);
    }

    public void update(Room room) {
        dao.save(room);
    }

    public void delete(Room room) {
        dao.delete(room);
    }

    public List<Room> findAll() {
        return dao.findAll();
    }

    public Room findById(Long id) {
        return dao.findOne(id);
    }

    public boolean exists(Long id) {
        return dao.exists(id);
    }

    public List<Room> findAllSorted(String sort, String order) {
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
