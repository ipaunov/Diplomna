package com.diploma.ivan.model;

import com.google.common.collect.ImmutableMap;

public enum StorageFormat {
    Ki("Ki"),
    Mi("Mi"),
    Gi("Gi"),
    Ti("Ti"),
    Pi("Pi"),
    Ei("Ei");

    private final String unit;
    StorageFormat(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public StorageFormat convertFromTechnicalKey(String technicalKey) {
        return StorageFormat.valueOf(technicalKey);
    }

    public static final ImmutableMap<StorageFormat, Integer> SizeMap = ImmutableMap.<StorageFormat, Integer>builder()
            .put(Ki, 0)
            .put(Mi, 1)
            .put(Gi, 2)
            .put(Ti, 3)
            .put(Pi, 4)
            .put(Ei, 5)
            .build();
}
