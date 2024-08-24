package org.user.app.service;

import org.user.app.repository.UserRepository;
import org.user.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserRepository repository;

    public UserService(UserRepository userRepository)
    {
        this.repository = userRepository;
    }
    public void save(User user)
    {
        try
        {
            repository.save(user);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
