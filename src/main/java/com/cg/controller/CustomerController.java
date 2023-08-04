package com.cg.controller;

import com.cg.model.Customer;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/cp/customers")
public class CustomerController {
    private ICustomerService customerService = new CustomerServiceImpl();

    @GetMapping()
    public ModelAndView showListPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/cp/customer/list");

        List<Customer> customers = customerService.getAll();

        if (customers != null) {
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        }
        mv.addObject("customers", customers);
        return mv;
    }

    @GetMapping("/create")
    public String showFormCreateCustomer(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer",customer);

        return "/cp/customer/create";
    }
    @PostMapping("/create")
    public String doCreate(@ModelAttribute Customer customer, Model model) {
        customerService.add(customer);

        model.addAttribute("customer",new Customer());

        return "cp/customer/create";
    }
    @GetMapping("/{id}")
    public String showEditPage(@PathVariable String id, Model model) {

            Long customerId = Long.parseLong(id);
            Customer customer = customerService.getById(customerId);

            model.addAttribute("customer", customer);

            return "cp/customer/edit";

    }
    @PostMapping("/{id}")
    public String doUpdate(@PathVariable Long id, @ModelAttribute Customer customer, Model model) {

        customer.setId(id);
        customerService.update(customer);

        List<Customer> customers = customerService.getAll();

        model.addAttribute("customers", customers);

        return "redirect:/cp/customers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        List<Customer> customers = customerService.getAll();
        Customer customer1 = customerService.getById(id);

        customers.remove(customer1);

        model.addAttribute("customers", customers);

        return "redirect:/cp/customers";
    }

}
