package com.amplifyframework.api.graphql;

/* JADX INFO: loaded from: classes5.dex */
public enum SubscriptionType implements Operation {
    ON_CREATE,
    ON_UPDATE,
    ON_DELETE;

    @Override // com.amplifyframework.api.graphql.Operation
    public OperationType getOperationType() {
        return OperationType.SUBSCRIPTION;
    }
}
