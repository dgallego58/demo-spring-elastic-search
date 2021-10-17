package com.elastic.service;

import com.elastic.docs.Company;

public interface DocIndexer {

    boolean index(Company company);

}
