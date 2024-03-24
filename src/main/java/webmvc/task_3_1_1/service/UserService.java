package webmvc.task_3_1_1.service;

import webmvc.task_3_1_1.model.User;

import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> getUserList();

    public User getUserById(Long id) throws Exception;

    public void updateUserById(Long id, String firstName, String lastName, String email);

    public void deleteUserById(Long id);
}