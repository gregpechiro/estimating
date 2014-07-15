/*
package com.cagnosolutions.cei.company.appname.controller.lineItem;*/
/**
 * Created by greg on 7/10/14.
 *//*


import com.cagnosolutions.cei.company.appname.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

		return "redirect:/app/list/item/" + roomId;
	}

	// delete post
	@RequestMapping(value = "/del/lineItem/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Long id, @RequestParam(value="roomId") Long roomId) {

		return "redirect:/app/view/room/"+roomId;
	}
}
*/
