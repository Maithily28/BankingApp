package com.main.repository;
//The UserRepository (which extends JpaRepository) interacts with the database

import com.main.entity.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UserRepository extends JpaRepository<UserAccount, Long >
{
     UserAccount findByAccountNo(long accountNo);
     //Page<UserAccount> findByCity(String city, Pageable pageable);

}
