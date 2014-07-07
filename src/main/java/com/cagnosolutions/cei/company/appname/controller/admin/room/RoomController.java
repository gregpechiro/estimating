package com.cagnosolutions.cei.company.appname.controller.admin.room;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "adminRoomController")
@RequestMapping("/admin")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // list get
    @RequestMapping(value = "/list/room", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        model.addAttribute("room", roomService.findAllSorted(sort, order));
        return "admin/room/list";
    }

    // add get
    @RequestMapping(value = "/add/room", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("room", new Room());
        return "admin/room/add";
    }

    // add post
    @RequestMapping(value = "/add/room", method = RequestMethod.POST)
    public String add(Room room) {
        roomService.insert(room);
        return "redirect:/admin/add/room?added";
    }

    // view get
    @RequestMapping(value = "/view/room/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "admin/room/view";
    }

    // delete post
    @RequestMapping(value = "/del/room/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        roomService.delete(roomService.findById(id));
        return "redirect:/admin/list/room?removed";
    }

    // edit get
    @RequestMapping(value = "/edit/room/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("room", roomService.findById(id));
        return "admin/room/edit";
    }

    // edit post
    @RequestMapping(value = "/edit/room/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Room room) {
    
	    /*
	     *  Implement edit/update
	     */

        return "redirect:/admin/edit/room/" + id + "?status";
    }
}
