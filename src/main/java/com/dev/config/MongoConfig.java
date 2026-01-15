package com.dev.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final ConnectionString connectionString;

    public MongoConfig(@Value("${SPRING_DATA_MONGODB_URI}") String mongoUri) {
        this.connectionString = new ConnectionString(mongoUri);
    }

    @Override
    protected String getDatabaseName() {
        return connectionString.getDatabase();
    }

    @Bean
    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();

        return MongoClients.create(settings);
    }
}