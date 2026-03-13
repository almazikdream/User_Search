package com.example.user_search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.user_search.model.User;
import com.example.user_search.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.Scanner;


@SpringBootApplication
public class UserSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSearchApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(UserRepository userRepository){
        return args -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n=== Приложение готово к работе");

            while (true){
                System.out.println("\nВыберите действие: 1-Добавить юзера, 2-Показать всех, 3-Удалить юзера,  0-Выход");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите имя: ");
                        String name = scanner.nextLine();
                        userRepository.save(new User(name));
                        System.out.println("Успешно сохранено!");
                    }
                    case "2" -> {
                        System.out.println("Список пользователей из БД");
                        userRepository.findAll().forEach(user -> {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getUsername());
                        });
                    }
                    case "3" -> {
                        while (true) {
                            userRepository.findAll().forEach(user -> {
                                System.out.println("ID: " + user.getId() + ", Name: " + user.getUsername());
                            });
                            System.out.println("Выберите ID имени который хотели бы удалить");
                            Long idDeleteUsername = Long.valueOf(scanner.nextLine());

                            if (userRepository.existsById(idDeleteUsername)) {
                                userRepository.deleteById(idDeleteUsername);
                                System.out.println("Юзер с ID: " + idDeleteUsername + " успешно удален!");
                            } else {
                                System.out.println("Юзер с ID: " + idDeleteUsername + " не существует!");
                            }
                            System.out.println("Хотите продолжить процесс удаления юсеров?");
                            System.out.println(" 1 - Да, 2 - Нет");
                            String answer = scanner.nextLine();
                            if (Objects.equals(answer, "2")) {
                                System.out.println("Так точно!");
                                break;
                            }
                        }
                    }
                    case "4" -> {
                        userRepository.findAll().forEach(user -> {
                            System.out.println("ID: " + user.getId() + ", Name: " + user.getUsername());
                        });
                        System.out.println("Выберите ID имени который хотели бы изменить");

                        Long idOfName = Long.valueOf(scanner.nextLine());

                        userRepository.findById(idOfName).ifPresentOrElse(user -> {
                            System.out.println("Текущее имя: " + user.getUsername());
                            System.out.print("Введите новое имя: ");
                            String newName = scanner.nextLine();

                            user.setUsername(newName);

                            userRepository.save(user);
                            System.out.println("Имя успешно сохранено");
                        }, () -> {
                            System.out.println("Пользователь с таким ID не найден.");
                        });
                    }
                    case "0" -> {
                        System.out.println("Выход...");
                        System.exit(0);
                    }
                }
            }
        };
    }



}
