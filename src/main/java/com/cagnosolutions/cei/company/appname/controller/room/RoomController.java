package com.cagnosolutions.cei.company.appname.controller.room;

import com.cagnosolutions.cei.company.appname.domain.Room;
import com.cagnosolutions.cei.company.appname.service.GlobalService;
import com.cagnosolutions.cei.company.appname.service.JobService;
import com.cagnosolutions.cei.company.appname.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/app/customer/{customerId}/job/{jobId}/room")
@Controller(value = "roomController")
public class RoomController {

	@Autowired
	private JobService jobService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private GlobalService globalService;

    // add room post
    @RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
    public String add(@PathVariable Long jobId, Room room) {
		jobService.addRoomToJob(jobId, room);
		return "room added";
    }

    // view room get
    @RequestMapping(value = "/{roomId}", method = RequestMethod.GET)
    public String view(@PathVariable Long customerId, @PathVariable Long jobId, @PathVariable Long roomId, Model model) {
		Room room = globalService.getCustomersJobsRoom(customerId, jobId, roomId);
		if (room == null) {
			return "redirect:/";
		}
		model.addAttribute("room", room);
		return "test/room";
    }

    // edit/delete room post
    @RequestMapping(value = "/{roomId}", method = RequestMethod.POST)
	@ResponseBody
    public String delete(@RequestParam(value = "action") String action,
						 @PathVariable Long customerId,
						 @PathVariable Long jobId,
						 @PathVariable Long roomId,
						 Room room) {
        Room existingRoom = globalService.getCustomersJobsRoom(customerId, jobId, roomId);
		if (existingRoom == null) {
			return "redirect:/";
		}
		if (action.equals("delete")) {
			roomService.delete(existingRoom);
			return "redirect:/app/customer/" + customerId + "/job/" + jobId;
		}
		existingRoom.setName(room.getName());
		roomService.update(existingRoom);
		return "/app/customer/" + customerId + "/job/" + jobId + "/room/" + roomId;
    }
}
