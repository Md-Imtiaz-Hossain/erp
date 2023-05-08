package com.brainstation23.erp.controller.web;

import com.brainstation23.erp.model.domain.Revenue;
import com.brainstation23.erp.service.RevenueService;
import com.brainstation23.erp.service.RoleService;
import com.brainstation23.erp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
        Page<Revenue> assets = revenueService.getAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        modelAndView.addObject("pageTitle", "Revenue List");
        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
        modelAndView.addObject("assets", assets);
        modelAndView.addObject("pagesForPagination", assets);
        modelAndView.addObject("url", "/revenue");
        return modelAndView;
    }

//
//    @GetMapping("/{id}/")
//    public ModelAndView getRole(@PathVariable UUID id, Principal principal) {
//        var modelAndView = new ModelAndView("role/single");
//        Role role = roleService.getRole(id);
//        modelAndView.addObject("pageTitle", "Role Details");
//        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
//        modelAndView.addObject("role", role);
//        return modelAndView;
//    }
//
//    @GetMapping("/create")
//    public ModelAndView createRolePage(Principal principal) {
//        var modelAndView = new ModelAndView("role/new-role");
//        var createRoleRequest = new CreateRoleRequest();
//        modelAndView.addObject("pageTitle", "Add Role");
//        modelAndView.addObject("loggedInUser", userService.getLoggedInUser(principal));
//        modelAndView.addObject("role", createRoleRequest);
//        return modelAndView;
//    }
//
//    @PostMapping
//    public String createRole(@Valid @ModelAttribute("role") CreateRoleRequest request, BindingResult bindingResult, Model model, Principal principal) {
//        try {
//            if (bindingResult.hasErrors()) {
//                model.addAttribute("pageTitle", "Add Role");
//                model.addAttribute("loggedInUser", userService.getLoggedInUser(principal));
//                model.addAttribute("role", request);
//                return "role/new-role";
//            }
//            roleService.createRole(request);
//            return "redirect:/roles";
//        } catch (Exception e) {
//            return "redirect:/roles/create";
//        }
//    }




}
