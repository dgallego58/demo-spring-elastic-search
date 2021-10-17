package com.elastic.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "demo-es")
public class ElasticSearchProperties {

    private String companyIndexName;
    private String host;

    public ElasticSearchProperties() {
        //creator
    }

    public String getCompanyIndexName() {
        return companyIndexName;
    }

    public ElasticSearchProperties setCompanyIndexName(String companyIndexName) {
        this.companyIndexName = companyIndexName;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ElasticSearchProperties setHost(String host) {
        this.host = host;
        return this;
    }
}
