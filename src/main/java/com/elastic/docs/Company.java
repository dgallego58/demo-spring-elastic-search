package com.elastic.docs;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.util.List;

@Document(indexName = "#{@elasticSearchProperties.companyIndexName}")
public class Company {

    @Id
    private String id;
    @MultiField(mainField = @Field(type = FieldType.Text, fielddata = true),
            otherFields = {@InnerField(suffix = "verbatim", type = FieldType.Text)})
    private String name;
    private String docNum;
    @Field(type = FieldType.Text)
    private String docType;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<User> users;

    public String getId() {
        return id;
    }

    public Company setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Company setName(String name) {
        this.name = name;
        return this;
    }

    public String getDocNum() {
        return docNum;
    }

    public Company setDocNum(String docNum) {
        this.docNum = docNum;
        return this;
    }

    public String getDocType() {
        return docType;
    }

    public Company setDocType(String docType) {
        this.docType = docType;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Company setUsers(List<User> users) {
        this.users = users;
        return this;
    }
}
