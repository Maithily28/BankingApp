package com.main.repository;
//The UserRepository (which extends JpaRepository) interacts with the database

import com.main.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAccount, Long >
{
     UserAccount findByAccountNo(long accountNo);

}
