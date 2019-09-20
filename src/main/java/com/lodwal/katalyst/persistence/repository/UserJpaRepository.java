package com.lodwal.katalyst.persistence.repository;

import com.lodwal.katalyst.persistence.objects.DBUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<DBUser, String> {
  DBUser findByEmailId(String emailId);
}