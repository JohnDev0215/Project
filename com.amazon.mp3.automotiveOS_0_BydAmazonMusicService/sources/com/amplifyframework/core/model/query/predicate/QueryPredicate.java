package com.amplifyframework.core.model.query.predicate;

import com.amplifyframework.core.model.query.predicate.QueryPredicateGroup;
import java.util.Collections;

/* JADX INFO: loaded from: classes5.dex */
public interface QueryPredicate extends Evaluable<Object> {
    QueryPredicate and(QueryPredicate queryPredicate);

    QueryPredicate or(QueryPredicate queryPredicate);

    static QueryPredicate not(QueryPredicate queryPredicate) {
        return new QueryPredicateGroup(QueryPredicateGroup.Type.NOT, Collections.singletonList(queryPredicate));
    }
}
