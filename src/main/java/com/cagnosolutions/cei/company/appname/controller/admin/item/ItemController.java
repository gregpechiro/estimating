package com.cagnosolutions.cei.company.appname.controller.admin.item;
/**
 * Created by Scott Cagno on 7/7/14.
 * Copyright Cagno Solutions. All rights reserved.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "adminItemController")
@RequestMapping("/admin")
public class ItemController {

    /*@Autowired
    private ItemService itemService;

    @Autowired
    private FlashService flashService;

    // list get
    @RequestMapping(value = "/list/item", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        if(category == null) {
            model.addAttribute("items", itemService.findAllSorted(sort, order));
        } else {
            model.addAttribute("items", itemService.findAllByCategory(category));
        }
        model.addAttribute("categories", itemService.getUniqueItemsByCategory());
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
    public String add(Item item, RedirectAttributes attr) {
        itemService.insert(item);
        flashService.flash(attr, "add.success");
        return "redirect:/admin/add/item";
    }

    // view get
    @RequestMapping(value = "/view/item/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "admin/item/view";
    }

    // delete post
    @RequestMapping(value = "/del/item/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        itemService.delete(itemService.findById(id));
        flashService.flash(attr, "delete.success");
        return "redirect:/admin/list/item";
    }

    // edit get
    @RequestMapping(value = "/edit/item/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("item", itemService.findById(id));
        return "admin/item/edit";
    }

    // edit post
    @RequestMapping(value = "/edit/item/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable("id") Long id, Item item, RedirectAttributes attr) {
        item.setId(id);
		itemService.update(item);
    	flashService.flash(attr, "update.success");
        return "redirect:/admin/edit/item/" + id;
    }*/
}
