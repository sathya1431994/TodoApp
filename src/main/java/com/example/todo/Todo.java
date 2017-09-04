package com.example.todo;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 2017000)
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "TODO_ID")
	private Long id;
	@Column(name = "TODO_NAME")
	private String name;
	@Column(name = "TODO_STATUS")
	private String status;
	@Column(name = "TODO_PRIORITY")
	private String priority;
	@Column(name = "TODO_COMPLETD")
	private Boolean isCompleted;

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(String name, String status, String priority, Boolean isCompleted) {
		super();
		this.name = name;
		this.status = status;
		this.priority = priority;
		this.isCompleted = isCompleted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

}