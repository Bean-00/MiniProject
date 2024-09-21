package net.study.domain.todo.dao.impl;

import net.study.domain.todo.dao.TodoDao;
import net.study.domain.todo.vo.TodoItem;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("todoDao")
public class TodoDaoJdbc implements TodoDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<TodoItem> findAll() {
        return sqlSession.selectList("getList");
    }

    @Override
    public void add(TodoItem todoItem) {
        sqlSession.insert("addItem", todoItem);
    }

    @Override
    public void update(TodoItem todoItem) {
        sqlSession.update("updateItem", todoItem);
    }

    @Override
    public void deleteById(int id) {
        sqlSession.delete("deleteItemById", id);
    }
}