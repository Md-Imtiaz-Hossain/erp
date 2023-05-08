package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.model.domain.Revenue;
import com.brainstation23.erp.model.dto.request.CreateRevenueRequest;
import com.brainstation23.erp.model.dto.request.UpdateRevenueRequest;
import com.brainstation23.erp.model.dto.request.UpdateRoleRequest;
import com.brainstation23.erp.service.RevenueService;
import com.brainstation23.erp.service.RoleService;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/revenue")
public class RevenueController {

    private final RoleService roleService;
    private final UserService userService;
    private final RevenueService revenueService;

    @GetMapping
    public ModelAndView getAssets(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("revenue/list");
        Page<Revenue> revenues = revenueService.getAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("pageTitle", "Revenue List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("revenues", revenues);
        modelAndView.addObject("pagesForPagination", revenues);
        modelAndView.addObject("url", "/revenue");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createRevenuePage(Principal principal) {
        var modelAndView = new ModelAndView("revenue/new-revenue");
        var createRevenueRequest = new CreateRevenueRequest();
        modelAndView.addObject("pageTitle", "Add Revenue");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("revenue", createRevenueRequest);
        return modelAndView;
    }

    @PostMapping
    public String createRevenue(@Valid @ModelAttribute("revenue") CreateRevenueRequest request, BindingResult bindingResult,
                                Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Add Revenue");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("revenue", request);
                return "revenue/new-revenue";
            }
            revenueService.createOne(request);
            return "redirect:/revenue";
        } catch (Exception e) {
            return "redirect:/revenue/create";
        }
    }


    @GetMapping("/{id}/update")
    public ModelAndView updateRevenuePage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("revenue/update-revenue");
        var revenueServiceOne = revenueService.getOne(id);
        modelAndView.addObject("pageTitle", "Update Revenue");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("revenue", revenueServiceOne);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateRole(@Valid @ModelAttribute("role") UpdateRevenueRequest request, @PathVariable UUID id, BindingResult bindingResult,
                             Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Update Revenue");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("revenue", request);
                return "revenue/update-revenue";
            }
            revenueService.updateOne(id, request);
            return "redirect:/revenue";
        } catch (Exception e) {
            return "redirect:/revenue";
        }
    }

}
