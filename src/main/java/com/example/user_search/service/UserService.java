package com.example.user_search.service;

import com.example.user_search.model.User;
import com.example.user_search.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            System.out.println("Юсер с ID " + id + " успешно удален.");
        } else {
            System.out.println("Юзер с ID " + id + " не найден.");
        }
    }

    public void updateUserName(Long id, String newName) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(newName);
            userRepository.save(user);
            System.out.println("Имя успешно сохранено!");
        } else {
            System.out.println("Пользователь с таким ID не найден!");
        }
    }

    public void saveUser(String name) {
        User newUser = new User();
        // Проверь, как называется поле в твоем Entity (User.java).
        // Судя по методу updateUserName, оно называется username
        newUser.setUsername(name);

        userRepository.save(newUser);
    }
}
