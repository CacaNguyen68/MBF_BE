package com.nguyenanhthu.ptm.user.repository;

import com.nguyenanhthu.ptm.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);
}
