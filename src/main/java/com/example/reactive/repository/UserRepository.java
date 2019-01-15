package com.example.reactive.repository;

import com.example.reactive.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Repository interface to manage {@link User} instances.
 *
 * @author Filipp Semenkov
 */
public interface UserRepository extends ReactiveCrudRepository<User, Long> {
}
