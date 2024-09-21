package net.study.domain.todo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@Alias("todoItem")
public class TodoItem {

    private int id;
    private String content;
    private boolean completed;
    private int seq;
}
