package com.handevcoder.auth_service.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.handevcoder.auth_service.entity.Users;

public interface UserRepository  extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);
}
