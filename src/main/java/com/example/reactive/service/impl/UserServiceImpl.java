package com.example.reactive.service.impl;

import com.example.reactive.domain.User;
import com.example.reactive.repository.UserRepository;
import com.example.reactive.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for {@link User} managing.
 *
 * @author Filipp Semenkov
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    /**
     * Get all the countries.
     *
     * @return the list of entities
     */
    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    /**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    @Override
    public Mono<User> save(User user) {
        return repository.save(user);
    }

    /**
     * Get the "id" User.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Mono<User> findOne(Long id) {
        return repository.findById(id);
    }

    /**
     * Delete the "id" User.
     *
     * @param user the entity to delete
     */
    @Override
    public Mono<Void> delete(User user) {
        return repository.delete(user);
    }

}
