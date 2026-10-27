package com.amplifyframework.api.graphql.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelPagination {
    private static final int DEFAULT_LIMIT = 1000;
    private int limit;

    private ModelPagination(int i) {
        this.limit = i;
    }

    @NonNull
    public static ModelPagination firstPage() {
        return limit(1000);
    }

    @NonNull
    public static ModelPagination limit(@Nullable int i) {
        return new ModelPagination(i);
    }

    @NonNull
    public ModelPagination withLimit(int i) {
        this.limit = i;
        return this;
    }

    public int getLimit() {
        return this.limit;
    }
}
