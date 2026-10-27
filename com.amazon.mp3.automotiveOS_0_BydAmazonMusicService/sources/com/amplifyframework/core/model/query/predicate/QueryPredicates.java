package com.amplifyframework.core.model.query.predicate;

/* JADX INFO: loaded from: classes5.dex */
public final class QueryPredicates {
    private QueryPredicates() {
    }

    public static QueryPredicate all() {
        return MatchAllQueryPredicate.instance();
    }

    public static QueryPredicate none() {
        return MatchNoneQueryPredicate.instance();
    }
}
