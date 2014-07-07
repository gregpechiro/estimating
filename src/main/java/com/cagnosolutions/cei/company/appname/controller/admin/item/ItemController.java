package com.cagnosolutions.cei.company.appname.controller.admin.item;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import com.cagnosolutions.cei.company.appname.domain.Item;
import com.cagnosolutions.cei.company.appname.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller(value = "adminItemController")
@RequestMapping("/admin")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // list get
    @RequestMapping(value = "/list/item", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        model.addAttribute("item", itemService.findAllSorted(sort, order));
        return "admin/item/list";
    }

    // add get
    @RequestMapping(value = "/add/item", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "admin/item/add";
    }

    // add post
    @RequestMapping(value = "/add/item", method = RequestMethod.POST)
    public String add(Item item) {
        itemService.insert(item);
        return "redirect:/admin/add/item?added";
    }

    // view get
    @RequestMapping(value = "/view/item/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "admin/item/view";
    }

    // delete post
    @RequestMapping(value = "/del/item/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, Model model) {
        itemService.delete(itemService.findById(id));
        return "redirect:/admin/list/item?removed";
    }

    // edit get
    @RequestMapping(value = "/edit/item/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "admin/item/edit";
    }

    // edit post
    @RequestMapping(value = "/edit/item/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Item item) {
    
	    /*
	     *  Implement edit/update
	     */

        return "redirect:/admin/edit/item/" + id + "?status";
    }
}
