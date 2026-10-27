package com.amplifyframework.core.model.query.predicate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
final class MatchNoneQueryPredicate implements QueryPredicate {
    @Override // com.amplifyframework.core.model.query.predicate.QueryPredicate
    public QueryPredicate and(QueryPredicate queryPredicate) {
        return this;
    }

    @Override // com.amplifyframework.core.model.query.predicate.Evaluable
    public boolean evaluate(@Nullable Object obj) {
        return false;
    }

    @Override // com.amplifyframework.core.model.query.predicate.QueryPredicate
    public QueryPredicate or(QueryPredicate queryPredicate) {
        return queryPredicate;
    }

    private MatchNoneQueryPredicate() {
    }

    @NonNull
    public static MatchNoneQueryPredicate instance() {
        return new MatchNoneQueryPredicate();
    }

    public int hashCode() {
        return MatchNoneQueryPredicate.class.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        return obj instanceof MatchNoneQueryPredicate;
    }

    @NonNull
    public String toString() {
        return MatchNoneQueryPredicate.class.getSimpleName();
    }
}
