package com.wedoogift.challenge.infrapersistence.repository.user;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<UserEntity, Integer> {

}
