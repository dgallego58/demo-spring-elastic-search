package com.elastic.docs;


import org.springframework.data.annotation.TypeAlias;

@TypeAlias(value = "user")
public class User {

    private String id;
    private String docNum;
    private String docType;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getDocNum() {
        return docNum;
    }

    public User setDocNum(String docNum) {
        this.docNum = docNum;
        return this;
    }

    public String getDocType() {
        return docType;
    }

    public User setDocType(String docType) {
        this.docType = docType;
        return this;
    }
}
