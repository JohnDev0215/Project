package io.objectbox.query;

import io.objectbox.relation.RelationInfo;

/* JADX INFO: loaded from: classes5.dex */
public class RelationCountCondition<T> extends QueryConditionImpl<T> {
    private final int relationCount;
    private final RelationInfo<T, ?> relationInfo;

    @Override // io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
    public /* bridge */ /* synthetic */ QueryCondition and(QueryCondition queryCondition) {
        return super.and(queryCondition);
    }

    @Override // io.objectbox.query.QueryConditionImpl, io.objectbox.query.QueryCondition
    public /* bridge */ /* synthetic */ QueryCondition or(QueryCondition queryCondition) {
        return super.or(queryCondition);
    }

    public RelationCountCondition(RelationInfo<T, ?> relationInfo, int i) {
        this.relationInfo = relationInfo;
        this.relationCount = i;
    }

    @Override // io.objectbox.query.QueryConditionImpl
    void apply(QueryBuilder<T> queryBuilder) {
        queryBuilder.relationCount(this.relationInfo, this.relationCount);
    }
}
