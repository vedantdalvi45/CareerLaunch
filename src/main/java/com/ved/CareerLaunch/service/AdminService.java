package com.ved.CareerLaunch.service;

import com.ved.CareerLaunch.model.User;
import java.util.List;

public interface AdminService {
    List<User> getAllUsers();
    void deleteUser(String userId);
    void deleteJob(String jobId); // Admin-level job deletion
}
