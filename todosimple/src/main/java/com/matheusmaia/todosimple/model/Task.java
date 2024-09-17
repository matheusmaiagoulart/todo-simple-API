package com.matheusmaia.todosimple.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {

    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne //Many{Task}toOne{usuario}
    @JoinColumn(name = "user_id", nullable = false, updatable = false )
    private User user;

    @Column(name = "description", nullable = false, length = 256)
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String description;

    public Task() {
    }

    public Task(Long id, User user, String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public @NotEmpty @NotNull @Size(min = 1, max = 255) String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty @NotNull @Size(min = 1, max = 255) String description) {
        this.description = description;
    }
}
