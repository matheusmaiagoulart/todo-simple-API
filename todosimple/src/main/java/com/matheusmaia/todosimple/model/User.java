package com.matheusmaia.todosimple.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity //Identifica que essa classe tem uma tabela no Banco de Dados
@Table(name =  User.TABLE_NAME) //Passa o nome da tabela. Se n tiver o @Table, ele vai pegar o nome da Classe como nome
@Getter
@Setter
public class User {

    public interface CreateUser {}
    public interface UpdateUser {}
    //Configurações do Banco de Dados
    public static final String TABLE_NAME = "users"; //Nome da tabela

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera Id's em auto incremente
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull (groups = CreateUser.class) //Nao pode ser nulo
    @NotEmpty (groups = CreateUser.class)// Nao pode ser uma string vazia ""
    //@NotBlank eh os dois acima
    @Size(groups = CreateUser.class, min = 2, max = 100) //Minimo e maximo das strings para serem criadas
    private String username;


    @Column(name = "password", length = 60, nullable = false)
    @NotNull (groups = {CreateUser.class, UpdateUser.class}) //Entre chaves eh array
    @NotEmpty (groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = CreateUser.class, min = 8, max = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public User() {

    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    @Override
    public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
       return result;
    }
}
