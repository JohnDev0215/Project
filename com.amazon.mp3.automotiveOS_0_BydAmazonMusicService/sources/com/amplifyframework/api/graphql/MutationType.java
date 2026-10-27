package com.amplifyframework.api.graphql;

/* JADX INFO: loaded from: classes5.dex */
public enum MutationType implements Operation {
    CREATE,
    UPDATE,
    DELETE;

    @Override // com.amplifyframework.api.graphql.Operation
    public OperationType getOperationType() {
        return OperationType.MUTATION;
    }
}
