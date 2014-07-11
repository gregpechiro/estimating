package com.cagnosolutions.cei.company.appname.controller.lineItem;/**
 * Created by greg on 7/10/14.
 */

import com.cagnosolutions.cei.company.appname.domain.LineItem;
import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
@RequestMapping("/app")
public class LineItemController {

	@Autowired
	private RoomService roomService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private LineItemService lineItemService;

	@Autowired
	private JobService jobService;

	@Autowired
	private SettingsService settingsService;

	@RequestMapping(value="/add/lineItem", method = RequestMethod.POST)
	public String addLineItem(@RequestParam(value="roomId") Long roomId, @RequestParam(value="itemId") Long itemId, @RequestParam(value="qty") Integer qty) {
		Room room = roomService.findById(roomId);
		Collection<LineItem> lineItems = room.getItems();
		//lineItems.add(new LineItem(itemId, qty));
		LineItem lineItem = new LineItem(itemService.findById(itemId), qty);
		lineItems.add(lineItem);
		room.setItems(lineItems);
		roomService.update(room);
		room.getJob().setJobTotal(settingsService.findById(1L).getHourlyRate());
		jobService.update(room.getJob());
		return "redirect:/app/list/item/" + roomId;
	}

	// delete post
	@RequestMapping(value = "/del/lineItem/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, @RequestParam(value="roomId") Long roomId) {
		lineItemService.delete(lineItemService.findById(id));
		Room room = roomService.findById(roomId);
		room.getJob().setJobTotal(settingsService.findById(1L).getHourlyRate());
		jobService.update(room.getJob());
		return "redirect:/app/view/room/"+roomId;
	}
}
