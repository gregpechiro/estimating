package com.cagnosolutions.cei.company.appname.controller.room;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.domain.LineItem;
import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.service.ItemService;
import com.cagnosolutions.cei.company.appname.service.JobService;
import com.cagnosolutions.cei.company.appname.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@RequestMapping("/app")
@Controller(value = "roomController")
public class RoomController {

    @Autowired
    private RoomService roomService;

	@Autowired
	private JobService jobService;

	@Autowired
	private ItemService itemService;

    // add post
    @RequestMapping(value = "/add/room", method = RequestMethod.POST)
    public String add(Room room, @RequestParam(value="jobId") Long jobId) {
		Job job = jobService.findById(jobId);
		Collection<Room> rooms = job.getRooms();
		rooms.add(room);
		job.setRooms(rooms);
		jobService.update(job);
        return "redirect:/app/view/job/"+job.getId();
    }

    // view get
    @RequestMapping(value = "/view/room/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "room/view";
    }

    // delete post
    @RequestMapping(value = "/del/room/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model, @RequestParam(value="jobId") Long jobId) {
        roomService.delete(roomService.findById(id));
        return "redirect:/app/view/job/" + jobId;
    }

    // edit post
    @RequestMapping(value = "/edit/room/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Room room) {
		Room existingRoom = roomService.findById(id);
		existingRoom.setName(room.getName());
		existingRoom.setNotes(room.getNotes());
  		roomService.update(existingRoom);
        return "redirect:/app/view/room/" + id;
    }

	@RequestMapping(value="/add/item/room", method = RequestMethod.POST)
	public String addLineItem(@RequestParam(value="roomId") Long roomId, @RequestParam(value="itemId") Long itemId, @RequestParam(value="qty") Integer qty) {
		Room room = roomService.findById(roomId);
		Collection<LineItem> lineItems = room.getItems();
		lineItems.add(new LineItem(itemService.findById(itemId), qty));
		room.setItems(lineItems);
		roomService.update(room);
		return "redirect:/app/list/item/" + roomId;
	}


}
