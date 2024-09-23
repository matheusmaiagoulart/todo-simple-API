package com.matheusmaia.todosimple.services;

import com.matheusmaia.todosimple.model.Task;
import com.matheusmaia.todosimple.model.User;
import com.matheusmaia.todosimple.repositores.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskRepository taskRepository;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id); //Buscando a task pelo id
        return task.orElseThrow(() -> new RuntimeException(
                "Task não encontrada! Id: " + id + " Tipo: " + Task.class.getName()));
    }

    public Task create(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        newObj = this.taskRepository.save(newObj);
        return newObj;
    }

    public void delete(Long id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Não é possível deletar pois há entidadades relacionadas");

        }
    }
}
