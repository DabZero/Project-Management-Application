package com.jrp.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

}
