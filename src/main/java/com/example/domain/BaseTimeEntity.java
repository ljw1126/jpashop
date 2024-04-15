package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseTimeEntity {

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "created_date")
    protected LocalDateTime createdDate;

    @Column(name = "last_modified_by")
    protected String lastModifiedBy;

    @Column(name = "last_modified_date")
    protected String lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }
}
