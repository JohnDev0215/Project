package com.apollographql.apollo3.network.http;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.c00;
import p.e60;
import p.pq1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport", f = "HttpNetworkTransport.kt", l = {103}, m = "singleResponse")
final class HttpNetworkTransport$singleResponse$1<D extends pq1> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpNetworkTransport$singleResponse$1(c cVar, c00 c00Var) {
        super(c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return c.b(this.this$0, null, null, null, this);
    }
}
