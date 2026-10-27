package com.apollographql.apollo3.exception;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class JsonEncodingException extends ApolloException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonEncodingException(String str) {
        super(str, null);
        v03.j(str, "message");
    }
}
