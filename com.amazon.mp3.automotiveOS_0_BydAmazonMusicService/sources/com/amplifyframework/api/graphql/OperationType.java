package com.amplifyframework.api.graphql;

import com.google.android.gms.actions.SearchIntents;

/* JADX INFO: loaded from: classes5.dex */
public enum OperationType {
    QUERY(SearchIntents.EXTRA_QUERY),
    MUTATION("mutation"),
    SUBSCRIPTION("subscription");

    private final String name;

    OperationType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
