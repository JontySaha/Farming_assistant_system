package com.cg.farming.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.farming.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer>{
	Optional<User> findByUsername(String username);
	boolean existsByUsername(String username);
}
