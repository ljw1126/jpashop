package com.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Period {
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    public Period() {}

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Period period = (Period) other;
        return Objects.equals(getStartDate(), period.getStartDate()) && Objects.equals(getEndDate(), period.getEndDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate());
    }
}
