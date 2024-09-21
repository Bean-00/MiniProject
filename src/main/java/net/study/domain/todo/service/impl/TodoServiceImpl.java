package net.study.domain.todo.service.impl;

import net.study.domain.todo.dao.TodoDao;
import net.study.domain.todo.service.TodoService;
import net.study.domain.todo.vo.TodoItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    private void validateCheck(TodoItem todoItem) {
        if (StringUtils.isEmpty(todoItem.getContent())) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
    }

    @Override
    public List<TodoItem> findAll() {
        return todoDao.findAll();
    }

    @Override
    public void add(TodoItem todoItem) {
        validateCheck(todoItem);
            //TODO: 400 BAD REQUEST: HTTP status code

        todoDao.add(todoItem);
    }

    @Override
    public void update(TodoItem todoItem) {
        todoDao.update(todoItem);
    }

    @Override
    public void deleteById(int id) {
        todoDao.deleteById(id);
    }
}
