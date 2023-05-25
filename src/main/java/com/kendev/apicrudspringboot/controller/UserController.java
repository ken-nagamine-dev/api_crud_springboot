package com.kendev.apicrudspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kendev.apicrudspringboot.model.User;
import com.kendev.apicrudspringboot.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

   @Autowired
   private UserRepository userRepository;

   @GetMapping
   public List<User> allList() {
      return userRepository.findAll();
   }

   @PostMapping
   public User addUser(@RequestBody User user) {
      return userRepository.save(user);
   }

   @PutMapping("/{id}")
   public User editUser(@RequestBody User user, @PathVariable Long id) {
      return userRepository.findById(id).map(editUser -> {
         editUser.setName(user.getName());
         editUser.setEmail(user.getEmail());
         editUser.setPassword(user.getPassword());
         return userRepository.save(editUser);
      }).orElseGet(() -> {
         user.setId(id);
         return userRepository.save(user);
      });
   }

   @DeleteMapping("/{id}")
   public void deleteUser(@PathVariable Long id) {
      userRepository.deleteById(id);
   }

}
