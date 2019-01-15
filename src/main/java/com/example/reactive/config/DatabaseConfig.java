package com.example.reactive.config;

import com.example.reactive.repository.UserRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
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
@EnableR2dbcRepositories
public class DatabaseConfig extends AbstractR2dbcConfiguration {

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .database("reactive")
                        .username("reactive")
                        .password("reactive")
                        .build()
        );
    }

    @Bean
    public DatabaseClient databaseClient(PostgresqlConnectionFactory connectionFactory) {
        return DatabaseClient.create(connectionFactory);
    }

    @Bean
    public UserRepository repository(R2dbcRepositoryFactory factory) {
        return factory.getRepository(UserRepository.class);
    }

    @Bean
    public R2dbcRepositoryFactory factory(DatabaseClient client) {
        RelationalMappingContext context = new RelationalMappingContext();
        context.afterPropertiesSet();
        return new R2dbcRepositoryFactory(client, context, new DefaultReactiveDataAccessStrategy(PostgresDialect.INSTANCE) );
    }

}
