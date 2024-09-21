package net.study.domain.todo.dao;

import net.study.domain.todo.vo.TodoItem;

import java.util.List;

public interface TodoDao {
    List<TodoItem> findAll();

    void add(TodoItem todoItem);

    void update(TodoItem todoItem);

    void deleteById(int id);
}
