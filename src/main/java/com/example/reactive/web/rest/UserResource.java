package com.example.reactive.web.rest;

import com.example.reactive.domain.User;
import com.example.reactive.service.UserService;
import com.example.reactive.web.rest.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * REST controller for managing Users {@link User}.
 *
 * @author Filipp Semenkov
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

    private final UserService service;

    /**
     * GET  /users : get all the users.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of users in body
     */
    @GetMapping(value = {"/v1/users", "/v2/users"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Flux<User> getAllUsers() {
        log.debug("REST request to get all Users");
        return service.findAll();
    }

    /**
     * POST  /users : Create a new user.
     *
     * @param user the user to create
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the user has already an ID
     */
    @PostMapping(value = {"/v1/users", "/v2/users"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ResponseEntity<User>> createUser(
            @Valid @RequestBody User user) {
        log.debug("REST request to save User : {}", user);
        return service.save(user).map(ResponseEntity::ok);
    }

    /**
     * GET  /users/:id : get the "id" user.
     *
     * @param id the id of the user to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the user, or with status 404 (Not Found)
     */
    @GetMapping(value = {"/v2/users/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ResponseEntity<User>> getUser(
            @PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        return service.findOne(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseUtil.notFound());
    }

    /**
     * PUT  /users : Updates an existing user.
     *
     * @param user the user to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated user,
     * or with status 400 (Bad Request) if the user is not valid,
     * or with status 500 (Internal Server Error) if the user couldn't be updated
     */
    @PutMapping(value = {"/v2/users/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable(value = "id") Long id,
            @RequestBody User user) {
        log.debug("REST request to update User : {}", user);
        return service.findOne(id)
                .flatMap(e -> service.save(e).map(ResponseEntity::ok))
                .defaultIfEmpty(ResponseUtil.notFound());
    }

    /**
     * DELETE  /users/:id : delete the "id" user.
     *
     * @param id the id of the user to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping(value = {"/v2/users/{id}"}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Mono<ResponseEntity<Void>> deleteUser(
            @PathVariable Long id) {
        log.debug("REST request to delete User : {}", id);
        return service.findOne(id)
                .flatMap(e -> service.delete(e).map(ResponseEntity::ok))
                .defaultIfEmpty(ResponseUtil.notFound());
    }

}
