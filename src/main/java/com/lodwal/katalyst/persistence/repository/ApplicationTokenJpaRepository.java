package com.lodwal.katalyst.persistence.repository;

import com.lodwal.katalyst.persistence.objects.DBApplicationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationTokenJpaRepository extends JpaRepository<DBApplicationToken, String> {
  List<DBApplicationToken> findByUserId(String userId);
}