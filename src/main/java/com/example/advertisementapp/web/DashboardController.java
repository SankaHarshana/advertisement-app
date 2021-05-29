package com.example.advertisementapp.web;

import com.example.advertisementapp.Exception.AdvertisementException;
import com.example.advertisementapp.dto.AdvertisementDto;
import com.example.advertisementapp.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final AdvertisementService advertisementService;

    @Autowired
    public DashboardController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("userDto") AdvertisementDto advertisementDto){
        try {
            advertisementService.createAdd(advertisementDto);
        } catch (AdvertisementException e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard";
    }

    @GetMapping("")
    public String getList(Model model){
        List<AdvertisementDto> list = new ArrayList<>();
        try {
            list =  advertisementService.getList();
        } catch (AdvertisementException e) {
            e.printStackTrace();
        }
        model.addAttribute("addList", list);
        return "dashboard";
    }

    @GetMapping("/get-by-id/{id}")
    public String getById(@PathVariable("id") long id, Model model){
        AdvertisementDto advertisement = new AdvertisementDto();
        try {
            advertisement =  advertisementService.getAdd(id);
        } catch (AdvertisementException e) {
            e.printStackTrace();
        }
        model.addAttribute("advertisementDto", advertisement);
        return "advertisement";
    }

    @PostMapping("/update-by-id/{id}")
    public String updateById(@PathVariable("id") long id, Model model,
                             @ModelAttribute("advertisementDto") AdvertisementDto advertisementDto){
        AdvertisementDto advertisement = new AdvertisementDto();
        try {
            advertisement =  advertisementService.updateAdd(id, advertisementDto);
        } catch (AdvertisementException e) {
            return "redirect:/dashboard/get-by-id/" + id;
        }
        return "redirect:/dashboard";
    }


    @GetMapping("/delete-by-id/{id}")
    public String deleteById(@PathVariable("id") long id){
        try {
            Boolean result  =  advertisementService.deleteAdd(id);
        } catch (AdvertisementException e) {
            e.printStackTrace();
        }
        return "redirect:/dashboard";
    }






}
