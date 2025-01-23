package com.main.repository;
//The UserRepository (which extends JpaRepository) interacts with the database

import com.main.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserAccount, Long >
{
     UserAccount findByAccountNo(long accountNo);

}
