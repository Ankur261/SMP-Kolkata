package com.cdac.smp.section.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.cdac.smp.section.model.Section;
import com.cdac.smp.section.service.SectionService;

import jakarta.validation.Valid;

@Controller
public class SectionController {

    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/section")
    public String homePage(Model model) {
        model.addAttribute("section", sectionService.getAllSection());
        return "sectionView/index"; 
    }

    @GetMapping("/section/add")
    public String addSection(Model model) {
        model.addAttribute("section", new Section());
        return "sectionView/addSection"; 
    }

    @PostMapping("/section/add")
    public String saveSection(@Valid @ModelAttribute("section") Section section,
                              BindingResult result,
                              Model model) {
        if (result.hasErrors()) {
            // if validation fails, return back to form
            return "sectionView/addSection";
        }

        int dbResult = sectionService.createSection(section);
        System.out.println("Saving section: " + section.getSectionName() + " | Result: " + dbResult);
        return "redirect:/section"; 
    }

    @PostMapping("/section/delete/{code}")
    public String deleteSection(@PathVariable("code") String sectionCode) {
        int result = sectionService.deleteSection(sectionCode);
        System.out.println("Deleted section: " + sectionCode + " | Result: " + result);
        return "redirect:/section";
    }

    @GetMapping("/section/update/{code}")
    public String updateSection(@PathVariable String code, Model model) {
        Section section = sectionService.getSectionByCode(code);
        model.addAttribute("sec", section);
        return "sectionView/updateSection"; 
    }

    @PostMapping("/section/update")
    public String updateSection(@Valid @ModelAttribute("sec") Section section, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sectionView/updateSection";
        }

        int dbResult = sectionService.updateSection(section);
        System.out.println("Updated section: " + section.getSectionName() + " | Result: " + dbResult);
        return "redirect:/section";
    }
}
