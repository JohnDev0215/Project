package io.objectbox.query;

import io.objectbox.query.LogicQueryCondition;

/* JADX INFO: loaded from: classes5.dex */
abstract class QueryConditionImpl<T> implements QueryCondition<T> {
    abstract void apply(QueryBuilder<T> queryBuilder);

    QueryConditionImpl() {
    }

    @Override // io.objectbox.query.QueryCondition
    public QueryCondition<T> and(QueryCondition<T> queryCondition) {
        return new LogicQueryCondition.AndCondition(this, (QueryConditionImpl) queryCondition);
    }

    @Override // io.objectbox.query.QueryCondition
    public QueryCondition<T> or(QueryCondition<T> queryCondition) {
        return new LogicQueryCondition.OrCondition(this, (QueryConditionImpl) queryCondition);
    }
}
