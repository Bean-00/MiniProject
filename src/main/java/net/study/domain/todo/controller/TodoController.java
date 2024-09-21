package net.study.domain.todo.controller;

import net.study.domain.todo.service.TodoService;
import net.study.domain.todo.vo.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/list")
    public String getTodoList(Model model) {

        model.addAttribute("todoList", todoService.findAll());

        return "todo";
    }

    @PostMapping("/addItem")
    public String addTodoItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        todoService.add(todoItem);
        return "redirect:/todo/list";
    }

    @PostMapping("/updateItem")
    public String updateTodoItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        todoService.update(todoItem);
        return "redirect:/todo/list";
    }

    @PostMapping("/deleteItem")
    public String deleteTodoItem(@ModelAttribute("todoItem") TodoItem todoItem) {
        todoService.deleteById(todoItem.getId());
        return "redirect:/todo/list";
    }
}
