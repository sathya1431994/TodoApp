package com.example.todo;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.when;
import com.jayway.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class) // 1
@SpringApplicationConfiguration(classes = TodoApplication.class) // 2
@WebAppConfiguration // 3
@IntegrationTest("server.port:0") // 4

public class TodoControllerIT {

    private static final String NAME_FIELD = "name";
    private static final String TODOS_RESOURCE = "/todolist";
    private static final String TODO_RESOURCE = "/todolist/{id}";

    private static final String FIRST_TODO_NAME = "DAILY REPORT";
    private static final String SECOND_TODO_NAME = "WEEKLY REPORT";
    private static final String THIRD_TODO_NAME = "MONTHLY REPORT";

    private static final Todo FIRST_TODO = new TodoBuilder().name(FIRST_TODO_NAME).build();
    private static final Todo SECOND_TODO = new TodoBuilder().name(SECOND_TODO_NAME).build();
    private static final Todo THIRD_TODO = new TodoBuilder().name(THIRD_TODO_NAME).build();

    @Autowired
    private TodoRepository todorepository;

    @Value("${local.server.port}")
    private int serverPort;
    private Todo firstTodo;
    private Todo secondTodo;

    @Test
    public void addItemShouldReturnSavedItem() {
        given().body(THIRD_TODO).contentType(ContentType.JSON).when().post(TODOS_RESOURCE).then()
                .statusCode(HttpStatus.SC_OK).body(NAME_FIELD, is(THIRD_TODO_NAME));
        System.out.println("addItemShouldReturnSavedItem completed !!!");
    }

    @Test
    public void getItemsShouldReturnBothItems() {
        when().get(TODOS_RESOURCE).then().statusCode(HttpStatus.SC_OK).body(NAME_FIELD,
                hasItems(FIRST_TODO_NAME, FIRST_TODO_NAME));
    }

    @Test
    public void deleteItemShouldReturnNoContent() {
        when().delete(TODO_RESOURCE, secondTodo.getId()).then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}