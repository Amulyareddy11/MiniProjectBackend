package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Account;

public interface AccountRepo extends JpaRepository<Account, Long> {
}
