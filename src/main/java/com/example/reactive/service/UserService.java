package com.example.reactive.service;

import com.example.reactive.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Interface for {@link User} managing.
 *
 * @author Filipp Semenkov
 */
@Service
public interface UserService {

    /**
     * Get all the countries.
     *
     * @return the list of entities
     */
    Flux<User> findAll();

    /**
     * Get the "id" User.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Mono<User> findOne(Long id);

    /**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    Mono<User> save(User user);

    /**
     * Delete the "id" User.
     *
     * @param user the entity to delete
     */
    Mono<Void> delete(User user);

}
