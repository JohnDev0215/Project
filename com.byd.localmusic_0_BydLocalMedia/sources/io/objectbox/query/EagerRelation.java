package io.objectbox.query;

import io.objectbox.relation.RelationInfo;

/* JADX INFO: loaded from: classes5.dex */
class EagerRelation<S, T> {
    public final int limit;
    public final RelationInfo<S, T> relationInfo;

    EagerRelation(int i, RelationInfo<S, T> relationInfo) {
        this.limit = i;
        this.relationInfo = relationInfo;
    }
}
