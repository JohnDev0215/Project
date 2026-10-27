package com.amplifyframework.core.model.query;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes5.dex */
public final class QueryPaginationInput {
    public static final int DEFAULT_LIMIT = 100;
    private final int limit;
    private final int page;

    QueryPaginationInput(int i, int i2) {
        this.page = i;
        this.limit = i2;
    }

    public QueryPaginationInput withLimit(@NonNull Integer num) {
        return new QueryPaginationInput(this.page, num.intValue());
    }

    public int getPage() {
        return this.page;
    }

    public int getLimit() {
        return this.limit;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QueryPaginationInput)) {
            return false;
        }
        QueryPaginationInput queryPaginationInput = (QueryPaginationInput) obj;
        return this.page == queryPaginationInput.page && this.limit == queryPaginationInput.limit;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(this.page), Integer.valueOf(this.limit));
    }

    public String toString() {
        return "QueryPaginationInput{page=" + this.page + ", limit=" + this.limit + '}';
    }
}
