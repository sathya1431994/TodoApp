package com.example.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {
    private static final Long TODO_ID = (long) 1;
    private static final Todo EXISTING_TODO = new TodoBuilder().id(TODO_ID).name("DAILY REPORT").status("Completed")
            .priority("High").isCompleted(true).build();
    private static final Todo ANOTHER_TODO = new TodoBuilder().name("WEEKLY REPORT").status("Active").priority("Medium")
            .isCompleted(false).build();
    private static final Todo NEW_TODO = new TodoBuilder().name("Monthly REPORT").status("Active").priority("Low")
            .isCompleted(false).build();

    @InjectMocks
    private TodoController todocontroller;
    @Mock
    private TodoRepository todorepository;

    @Test
    public void whenCreatingTodoItShouldReturnTheSavedTodo() {
        given(todorepository.saveAndFlush(NEW_TODO)).willReturn((EXISTING_TODO));
        assertThat(todocontroller.create((NEW_TODO))).isSameAs(EXISTING_TODO);
    }

    @Test
    public void whenReadingdingTodoItShouldReturnAllTodo() {
        given(todorepository.findAll()).willReturn(Arrays.asList(EXISTING_TODO, ANOTHER_TODO));
        assertThat(todocontroller.showall()).containsOnly(EXISTING_TODO, ANOTHER_TODO);
    }

    @Test
    public void whenDeletingATodoItShouldUseTheRepository() {
        todocontroller.delete(TODO_ID);
        verify(todorepository).delete(TODO_ID);
    }
}