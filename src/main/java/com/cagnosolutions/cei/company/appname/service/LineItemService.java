package com.cagnosolutions.cei.company.appname.service;/**
 * Created by greg on 7/11/14.
 */

import com.cagnosolutions.cei.company.appname.domain.LineItem;
import com.cagnosolutions.cei.company.appname.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("lineitemService")
public class LineItemService {

	@Autowired
	private LineItemRepository dao;

	public LineItem insert(LineItem lineitem) {
		return dao.saveAndFlush(lineitem);
	}

	public void update(LineItem lineitem) {
		dao.save(lineitem);
	}

	public void delete(LineItem lineitem) {
		dao.delete(lineitem);
	}

	public List<LineItem> findAll() {
		return dao.findAll();
	}

	public LineItem findById(Long id) {
		return dao.findOne(id);
	}

	public boolean exists(Long id) {
		return dao.exists(id);
	}

	public List<LineItem> findAllSorted(String sort, String order) {
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
