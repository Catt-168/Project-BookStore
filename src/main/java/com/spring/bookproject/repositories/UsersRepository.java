package com.spring.bookproject.repositories;

import com.spring.bookproject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Users findByUsername(String username);
    public boolean existsByUsername(String username);

}
