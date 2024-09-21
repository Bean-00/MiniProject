package net.study.domain.todo.controller;

import net.study.domain.todo.service.TodoService;
import net.study.domain.todo.vo.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/todo-items")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping({"", "/"})
    public ResponseEntity<List<TodoItem>> getTodoList() {

       List<TodoItem> todoItems = todoService.findAll();

        return ResponseEntity.ok().body(todoItems);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Void> addTodoItem(@RequestBody TodoItem todoItem) {
        todoService.add(todoItem);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTodoItem(@PathVariable int id,
                                 @RequestBody TodoItem todoItem) {
        todoItem.setId(id);
        todoService.update(todoItem);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable int id) {
        todoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItem> getTodoItem(@PathVariable int id) {
        TodoItem todoItem =  todoService.findItemById(id);

        return ResponseEntity.ok().body(todoItem);
    }
}
