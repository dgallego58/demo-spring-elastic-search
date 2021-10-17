package com.elastic.service;

import com.elastic.config.ElasticSearchProperties;
import com.elastic.docs.Company;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class DataIndexerService implements DocIndexer {

    private final ReactiveElasticsearchClient client;
    private final ReactiveElasticsearchOperations ops;
    private final ElasticSearchProperties properties;

    public DataIndexerService(ReactiveElasticsearchClient client, ReactiveElasticsearchOperations ops, ElasticSearchProperties properties) {
        this.client = client;
        this.ops = ops;
        this.properties = properties;
    }

    @Override
    public boolean index(Company company) {
        IndexRequest indexRequest = new IndexRequest(properties.getCompanyIndexName()).source(company);
        return Boolean.TRUE.equals(client.index(indexRequest).map(IndexResponse::status)
                .map(restStatus -> restStatus.getStatus() / 100 != 2).block());

    }

    public Mono<Boolean> createIndex(String indexName) {
        Map<String, Object> settings = Map.of("number_of_shards", 2,
                "number_of_replicas", 1);
        return ops.indexOps(IndexCoordinates.of(indexName)).create(settings);
    }
}
