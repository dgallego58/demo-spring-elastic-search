package com.elastic.config;

import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableReactiveElasticsearchRepositories(basePackages = {"com.elastic.repo"})
public class ElasticRestHighLevelClientConfig extends AbstractReactiveElasticsearchConfiguration {

    private final ElasticSearchProperties properties;

    public ElasticRestHighLevelClientConfig(ElasticSearchProperties properties) {
        this.properties = properties;
    }

    @Override
    @Bean
    public ReactiveElasticsearchClient reactiveElasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(properties.getHost())
                //.usingSsl(Objects.requireNonNull(sslContext(), "Context Null"), NoopHostnameVerifier.INSTANCE) //i tried this too :c
                .build();
        return ReactiveRestClients.create(clientConfiguration);
    }

    private SSLContext sslContext() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        return SSLContexts.custom()
                .loadTrustMaterial(null, (x509Certificates, s) -> true).build();
    }

    @Bean
    public ReactiveElasticsearchOperations elasticsearchOperations(ReactiveElasticsearchClient reactiveElasticsearchClient) {
        return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient);
    }
}
