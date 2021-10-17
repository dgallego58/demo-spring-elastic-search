package com.elastic.service;

import com.elastic.docs.Company;
import com.elastic.repo.ReactiveElasticSearchRepo;
import org.springframework.stereotype.Service;

@Service
public class ElasticRepositoryService implements DocIndexer {

    private final ReactiveElasticSearchRepo repo;

    public ElasticRepositoryService(ReactiveElasticSearchRepo repo) {
        this.repo = repo;
    }

    @Override
    public boolean index(Company company) {
        var response = repo.save(company);
        return Boolean.TRUE.equals(response.hasElement().block());
    }
}
