package com.cagnosolutions.cei.company.appname.service;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Item;
import com.cagnosolutions.cei.company.appname.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service("itemService")
public class ItemService {

    @Autowired
    private ItemRepository dao;

    public Item insert(Item item) {
        return dao.saveAndFlush(item);
    }

    public Item update(Item item) {
        return dao.save(item);
    }

    public void delete(Item item) {
        dao.delete(item);
    }

    public List<Item> findAll() {
        return dao.findAll();
    }

    public Item findById(Long id) {
        return dao.findOne(id);
    }

    public boolean exists(Long id) {
        return dao.exists(id);
    }

    public List<Item> findAllSorted(String sort, String order) {
        if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
            return dao.findAll();
        if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
            return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
        return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
    }

    private static boolean isEmpty(String string) {
        return (string == null || string.equals(""));
    }

    public List<Item> findAllByCategory(String category) {
        return dao.findAllByCategory(category);
    }

    public Set<String> getUniqueItemsByCategory() {
        List<String> categories = new ArrayList<>();
        List<Item> allItems = dao.findAll();
        for(Item item : allItems) {
            categories.add(item.getCategory());
        }
        return new HashSet(categories);
    }
}
