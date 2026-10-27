package com.apollographql.apollo3.exception;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class JsonDataException extends ApolloException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonDataException(String str) {
        super(str, null);
        v03.j(str, "message");
    }
}
