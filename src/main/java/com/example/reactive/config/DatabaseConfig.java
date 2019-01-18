package com.example.reactive.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.function.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
@RequiredArgsConstructor
@EnableR2dbcRepositories(
        basePackages = {"com.example.reactive.repository"}
)
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    private final DbProps db;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host(db.getHost())
                        .port(db.getPort())
                        .database(db.getName())
                        .username(db.getUser())
                        .password(db.getPass())
                        .build()
        );
    }

    @Bean
    public DatabaseClient databaseClient(PostgresqlConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }

    @Bean
    public R2dbcRepositoryFactory factory(DatabaseClient client) {
        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client, context, new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE));
    }

}
