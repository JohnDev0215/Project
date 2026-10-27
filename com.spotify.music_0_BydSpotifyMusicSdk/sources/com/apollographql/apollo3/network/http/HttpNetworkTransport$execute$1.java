package com.apollographql.apollo3.network.http;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.fv0;
import p.fz0;
import p.k21;
import p.lq2;
import p.m7;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1", f = "HttpNetworkTransport.kt", l = {64, 85, 84, 90, 89}, m = "invokeSuspend")
final class HttpNetworkTransport$execute$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ com.apollographql.apollo3.api.c $customScalarAdapters;
    final /* synthetic */ k21 $httpRequest;
    final /* synthetic */ m7 $request;
    long J$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HttpNetworkTransport$execute$1(c cVar, k21 k21Var, m7 m7Var, com.apollographql.apollo3.api.c cVar2, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
        this.$httpRequest = k21Var;
        this.$request = m7Var;
        this.$customScalarAdapters = cVar2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        HttpNetworkTransport$execute$1 httpNetworkTransport$execute$1 = new HttpNetworkTransport$execute$1(this.this$0, this.$httpRequest, this.$request, this.$customScalarAdapters, c00Var);
        httpNetworkTransport$execute$1.L$0 = obj;
        return httpNetworkTransport$execute$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((HttpNetworkTransport$execute$1) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0222 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0223 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 615
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.HttpNetworkTransport$execute$1.i(java.lang.Object):java.lang.Object");
    }
}
