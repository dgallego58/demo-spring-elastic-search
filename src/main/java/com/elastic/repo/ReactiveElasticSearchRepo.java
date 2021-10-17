package com.elastic.repo;

import com.elastic.docs.Company;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveElasticSearchRepo extends ReactiveElasticsearchRepository<Company, String> {



}
