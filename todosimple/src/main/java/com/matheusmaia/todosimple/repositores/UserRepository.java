package com.matheusmaia.todosimple.repositores;

import com.matheusmaia.todosimple.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
