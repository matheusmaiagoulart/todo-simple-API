package com.matheusmaia.todosimple.services;


import com.matheusmaia.todosimple.model.User;
import com.matheusmaia.todosimple.repositores.TaskRepository;
import com.matheusmaia.todosimple.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Eh um jeito de instanciar a classe e usar dos metodos dela

    @Autowired
    private TaskRepository taskRepository;



    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow /*Retorna se tiver preenchido, com infos*/(() -> new RuntimeException(
                "Usuário não encontrado! Id" + id  + ", Tipo " + User.class.getName()
        ));
    }

    @Transactional
    public User create (User obj){
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;
    }
    @Transactional
    public User update (User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword((obj.getPassword()));
        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não é possível excluir pois há tarefas relacionadas!");
        }

    }
}

