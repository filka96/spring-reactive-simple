package com.example.reactive.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * User entity.
 *
 * @author Filipp Semenkov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users")
public class User {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

}
