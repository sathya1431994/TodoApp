package com.example.todo;

public class TodoBuilder {
    private Todo todo = new Todo();

    public TodoBuilder id(Long id) {
        todo.setId(id);
        return this;
    }

    public TodoBuilder name(String name) {
        todo.setName(name);
        return this;
    }

    public TodoBuilder status(String status) {
        todo.setStatus(status);
        return this;
    }

    public TodoBuilder priority(String priority) {
        todo.setPriority(priority);
        return this;
    }

    public TodoBuilder isCompleted(Boolean isCompleted) {
        todo.setIsCompleted(isCompleted);
        return this;
    }

    public Todo build() {
        return todo;
    }
}