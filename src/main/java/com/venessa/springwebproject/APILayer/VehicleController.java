package com.venessa.springwebproject.APILayer;

import com.venessa.springwebproject.Model.Lorry;
import com.venessa.springwebproject.Model.Vehicle;
import com.venessa.springwebproject.Model.Van;
import com.venessa.springwebproject.ServiceLayer.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VehicleController
{
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicle-info")
    public String getVehicleInfo(@Param("keyword") String keyword, Model model)
    {
        List<Vehicle> vehicles = vehicleService.getAll(keyword);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("keyword", keyword);
        return "vehicle-info";
    }

    @GetMapping("/vehicle-addVan")
    public String goToAddVanPage(Model model)
    {
        model.addAttribute("van", new Van());
        return "add-van";
    }

    @PostMapping("/vehicle-addVan")
    public String addVan(Van van, Model model, RedirectAttributes redirectAttributes)
    {
        Vehicle vehicle = van;
        if(vehicleService.addVehicle(vehicle))
        {
            redirectAttributes.addFlashAttribute("success","Vehicle added successfully");
            return "redirect:/vehicle-info";
        } else
        {
            model.addAttribute("error","Not able to add vehicle");
            return "add-van";
        }
    }

    @GetMapping("/vehicle-addLorry")
    public String goToAddLorryPage(Model model)
    {
        model.addAttribute("lorry", new Lorry());
        return "add-lorry";
    }

    @PostMapping("/vehicle-addLorry")
    public String addLorry(Lorry lorry, Model model, RedirectAttributes redirectAttributes)
    {
        Vehicle vehicle = lorry;
        if(vehicleService.addVehicle(vehicle))
        {
            redirectAttributes.addFlashAttribute("success","Vehicle added successfully");
            return "redirect:/vehicle-info";
        } else
        {
            model.addAttribute("error","Not able to add vehicle");
            return "add-lorry";
        }
    }
}
