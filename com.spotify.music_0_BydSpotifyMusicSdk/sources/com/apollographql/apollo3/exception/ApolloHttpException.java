package com.apollographql.apollo3.exception;

import java.util.List;
import p.g21;
import p.ko;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloHttpException extends ApolloException {
    private final ko body;
    private final List<g21> headers;
    private final int statusCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApolloHttpException(int i, List list, ko koVar, String str) {
        super(str, null);
        v03.j(list, "headers");
        v03.j(str, "message");
        this.statusCode = i;
        this.headers = list;
        this.body = koVar;
    }
}
