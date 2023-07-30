package com.grzzlyb.expensetracker2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/list")
    public String showExpenseList(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "home";
    }

    @GetMapping("/add")
    public String showAddExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "add-expense";
    }

    @PostMapping("/save")
    public String saveExpense(@ModelAttribute("expense") Expense theExpense) {
        expenseService.saveExpense(theExpense);
        return "redirect:/expense/list";
    }

    @GetMapping("/delete")
    public String deleteExpense(@RequestParam("expenseId") long theId) {
        expenseService.deleteExpenseById(theId);
        return "redirect:/expense/list";
    }
}