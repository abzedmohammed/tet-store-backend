package com.tet_store.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsrEmail(String email);

    User findByUsrCid(String id);
}
