package com.amplifyframework.core.category;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes5.dex */
public enum CategoryType {
    ANALYTICS("analytics"),
    API("api"),
    AUTH("auth"),
    DATASTORE("dataStore"),
    HUB("hub"),
    LOGGING("logging"),
    PREDICTIONS("predictions"),
    STORAGE("storage"),
    GEO("geo");

    private final String configurationKey;

    CategoryType(String str) {
        this.configurationKey = str;
    }

    public String getConfigurationKey() {
        return this.configurationKey;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.configurationKey;
    }
}
