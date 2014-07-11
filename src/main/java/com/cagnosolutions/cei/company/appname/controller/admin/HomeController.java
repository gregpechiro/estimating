package com.cagnosolutions.cei.company.appname.controller.admin;

import com.cagnosolutions.cei.company.appname.domain.Item;
import com.cagnosolutions.cei.company.appname.domain.Settings;
import com.cagnosolutions.cei.company.appname.service.FlashService;
import com.cagnosolutions.cei.company.appname.service.ItemService;
import com.cagnosolutions.cei.company.appname.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private FlashService flashService;

	@RequestMapping(method=RequestMethod.GET)
	public String index(Model model, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "order", required = false) String order) {
        if(category == null) {
            model.addAttribute("items", itemService.findAllSorted(sort, order));
        } else {
            model.addAttribute("items", itemService.findAllByCategory(category));
        }
        model.addAttribute("categories", itemService.getUniqueItemsByCategory());
        Settings settings = settingsService.findById(1L);
        model.addAttribute("settings", settings);
        return "admin/home";
	}

    @RequestMapping(value="/edit/settings", method=RequestMethod.POST)
    public String postSettings(Settings settings, RedirectAttributes attr) {
        settings.setId(1L);
        settingsService.update(settings);
        flashService.flash(attr, "update.success");
        return "redirect:/admin";
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
    }

    @RequestMapping(value = "/upload/logo", method = RequestMethod.POST)
    public String uploadLogo(@RequestParam(value="logo") MultipartFile logo, RedirectAttributes attr) {
        if (!logo.isEmpty()) {
            try {
                byte[] rawLogo = logo.getBytes();
                Settings settings = settingsService.findById(1L);
                settings.getCompany().setLogo(rawLogo);
                settingsService.update(settings);
                flashService.flashAlert(attr, "Logo successfully uploaded", "success", true);
            } catch (Exception ex) {
                flashService.flashAlert(attr, "Failed to upload '" + logo.getOriginalFilename() + ".'", "danger", true);
            }
        } else {
            flashService.flashAlert(attr, "Failed to upload '" + logo.getOriginalFilename() + ".' It appears the file is empty.", "danger", true);
        }
        return "redirect:/admin";
    }
}
