package com.matrix.ecommerce.payment.repository;

import com.matrix.ecommerce.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {
}
