package ru.job4j.web_cars_storage.service;

import java.util.Objects;

public final class CriteriaForFilter {
    private final boolean isLastDay;
    private final boolean isPhoto;
    private final boolean isBrand;

    public CriteriaForFilter(boolean isLastDay, boolean isPhoto, boolean isBrand) {
        this.isLastDay = isLastDay;
        this.isPhoto = isPhoto;
        this.isBrand = isBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CriteriaForFilter criteria = (CriteriaForFilter) o;
        return isLastDay == criteria.isLastDay &&
                isPhoto == criteria.isPhoto &&
                isBrand == criteria.isBrand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLastDay, isPhoto, isBrand);
    }
}
