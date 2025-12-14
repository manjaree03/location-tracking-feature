package com.location_tracking.manjaree_project.service;

import com.location_tracking.manjaree_project.Repository.UserRepository;
import com.location_tracking.manjaree_project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService
{

    List<User> getUserList();


}
