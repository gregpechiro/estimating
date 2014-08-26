package com.cagnosolutions.cei.company.appname.controller.room;

import com.cagnosolutions.cei.company.appname.domain.Job;
import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/app/customer/{customerId}/job/{jobId}/room")
@Controller(value = "roomController")
public class RoomController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JobService jobService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private GlobalService globalService;

	@Autowired
	private FlashService flashService;

    // add/edit room post
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String add(@PathVariable Long customerId, @PathVariable Long jobId, Room room, RedirectAttributes attr) {
		if (room.getId() == null) {
			//add
			jobService.addRoomToJob(jobId, room);
			flashService.flash(attr, "add.success");
			return "redirect:/app/customer/" + customerId + "/job/" + jobId;
		}
		// edit
		Room existingRoom = roomService.findById(room.getId());
		existingRoom.setName(room.getName());
		roomService.update(existingRoom);
		flashService.flash(attr, "update.success");
		return "redirect:/app/customer/" + customerId + "/job/" + jobId + "/room" + room.getId();
    }

    // view room get
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public String view(@PathVariable Long customerId, @PathVariable int jobId, @PathVariable int roomId, Model model, RedirectAttributes attr) {
		//Room room = globalService.getCustomersJobsRoom(customerId, jobId, roomId);
		Room room = (Room)((Job)customerService.findById(customerId).getJobs().toArray()[jobId]).getRooms().toArray()[roomId];
		if (room == null) {
			flashService.flash(attr, "error");
			return "redirect:/";
		}
		model.addAttribute("room", room);
		return "test/room";
    }

    // edit/delete room post
    @RequestMapping(value = "/{roomId}", method = RequestMethod.POST)
	@ResponseBody
    public String delete(@PathVariable Long customerId, @PathVariable Long jobId, @PathVariable Long roomId, RedirectAttributes attr) {
        Room room = globalService.getCustomersJobsRoom(customerId, jobId, roomId);
		if (room == null) {
			flashService.flash(attr, "delete.error");
			return "redirect:/";
		}
		roomService.delete(room);
		flashService.flash(attr, "delete.success");
		return "redirect:/app/customer/" + customerId + "/job/" + jobId;

    }
}
