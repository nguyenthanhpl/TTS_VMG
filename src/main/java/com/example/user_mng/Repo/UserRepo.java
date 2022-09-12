package com.example.user_mng.Repo;

import com.example.user_mng.Model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Users,Long> {
}
