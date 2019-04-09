package com.beyondsoft.itsm.modules.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beyondsoft.itsm.modules.user.domain.User;

public interface UserRespository extends JpaRepository<User, Integer>{

}
