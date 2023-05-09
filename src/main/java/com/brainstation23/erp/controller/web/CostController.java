package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.model.domain.Cost;
import com.brainstation23.erp.model.domain.Revenue;
import com.brainstation23.erp.model.dto.request.CreateCostRequest;
import com.brainstation23.erp.model.dto.request.CreateRevenueRequest;
import com.brainstation23.erp.model.dto.request.UpdateCostRequest;
import com.brainstation23.erp.model.dto.request.UpdateRevenueRequest;
import com.brainstation23.erp.service.CostService;
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
@RequestMapping(value = "/cost")
public class CostController {

    private final RoleService roleService;
    private final UserService userService;
    private final RevenueService revenueService;
    private final CostService costService;


    @GetMapping
    public ModelAndView getCosts(@RequestParam(defaultValue = "0") int page, Principal principal) {
        var modelAndView = new ModelAndView("cost/list");
        Page<Cost> costs = costService.getAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("pageTitle", "Cost List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("costs", costs);
        modelAndView.addObject("pagesForPagination", costs);
        modelAndView.addObject("url", "/costs");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCostPage(Principal principal) {
        var modelAndView = new ModelAndView("cost/new-cost");
        var createCostRequest = new CreateCostRequest();
        modelAndView.addObject("pageTitle", "Add Cost");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("cost", createCostRequest);
        return modelAndView;
    }

    @PostMapping
    public String saveCost(@Valid @ModelAttribute("cost") CreateCostRequest request,
                           BindingResult bindingResult,
                           Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Add Cost");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("revenue", request);
                return "cost/new-cost";
            }
            costService.createOne(request);
            return "redirect:/cost";
        } catch (Exception e) {
            return "redirect:/cost/create";
        }
    }


    @GetMapping("/{id}/update")
    public ModelAndView updateCostPage(@PathVariable UUID id, Principal principal) {
        var modelAndView = new ModelAndView("cost/update-cost");
        var costServiceOne = costService.getOne(id);
        modelAndView.addObject("pageTitle", "Update Cost");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("cost", costServiceOne);
        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public String updateCost(@Valid @ModelAttribute("role") UpdateCostRequest request,
                             @PathVariable UUID id, BindingResult bindingResult,
                             Model model, Principal principal) {
        try {
            if (bindingResult.hasErrors()) {
                model.addAttribute("pageTitle", "Update Cost");
                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
                model.addAttribute("cost", request);
                return "cost/update-cost";
            }
            costService.updateOne(id, request);
            return "redirect:/cost";
        } catch (Exception e) {
            return "redirect:/cost";
        }
    }

}
