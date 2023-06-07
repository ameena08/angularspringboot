package com.UST.LoginSignup.Repository;

import com.UST.LoginSignup.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,java.lang.String> {
}
