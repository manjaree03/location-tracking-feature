package com.location_tracking.manjaree_project.serviceImpl;

import com.location_tracking.manjaree_project.Repository.UserRepository;
import com.location_tracking.manjaree_project.model.User;
import com.location_tracking.manjaree_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUserList() {
        List<User> users=new ArrayList<>();
        users=userRepository.findAll();
        return users;
    }
}
