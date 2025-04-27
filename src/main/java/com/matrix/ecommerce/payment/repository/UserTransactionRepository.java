package com.matrix.ecommerce.payment.repository;

import com.matrix.ecommerce.payment.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {
}
