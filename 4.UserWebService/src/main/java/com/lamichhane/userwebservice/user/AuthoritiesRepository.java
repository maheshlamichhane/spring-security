package com.lamichhane.userwebservice.user;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<AuthoritiesEntity,Integer>
{
	Set<AuthoritiesEntity> findByRole(String role);
}