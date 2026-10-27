package com.apollographql.apollo3.network.http;

import p.fv0;
import p.tq1;
import p.wc0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements fv0 {
    public final /* synthetic */ fv0 c;
    public final /* synthetic */ wc0 e;
    public final /* synthetic */ c k;
    public final /* synthetic */ tq1 l;
    public final /* synthetic */ com.apollographql.apollo3.api.c m;

    public b(fv0 fv0Var, wc0 wc0Var, c cVar, tq1 tq1Var, com.apollographql.apollo3.api.c cVar2) {
        this.c = fv0Var;
        this.e = wc0Var;
        this.k = cVar;
        this.l = tq1Var;
        this.m = cVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    @Override // p.fv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.Object r14, p.c00 r15) {
        /*
            r13 = this;
            p.wc0 r0 = r13.e
            boolean r1 = r15 instanceof com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$map$1$2$1
            if (r1 == 0) goto L15
            r1 = r15
            com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$map$1$2$1 r1 = (com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$map$1$2$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$map$1$2$1 r1 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$$inlined$map$1$2$1
            r1.<init>(r13, r15)
        L1a:
            java.lang.Object r15 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r3 = r1.label
            r4 = 2
            r5 = 1
            if (r3 == 0) goto L3e
            if (r3 == r5) goto L34
            if (r3 != r4) goto L2c
            kotlin.a.d(r15)
            goto L94
        L2c:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L34:
            java.lang.Object r14 = r1.L$0
            p.fv0 r14 = (p.fv0) r14
            kotlin.a.d(r15)     // Catch: java.lang.Exception -> L3c
            goto L86
        L3c:
            r14 = move-exception
            goto L9f
        L3e:
            kotlin.a.d(r15)
            p.fv0 r15 = r13.c
            p.ko r14 = (p.ko) r14
            r0.getClass()     // Catch: java.lang.Exception -> L3c
            java.lang.String r3 = "payload"
            p.v03.j(r14, r3)     // Catch: java.lang.Exception -> L3c
            p.lo r3 = new p.lo     // Catch: java.lang.Exception -> L3c
            r3.<init>(r14)     // Catch: java.lang.Exception -> L3c
            java.lang.Object r14 = p.he1.J(r3)     // Catch: java.lang.Exception -> L3c
            if (r14 == 0) goto L97
            java.util.Map r14 = (java.util.Map) r14     // Catch: java.lang.Exception -> L3c
            java.util.Map r8 = r0.f(r14)     // Catch: java.lang.Exception -> L3c
            java.lang.Object r14 = r0.e     // Catch: java.lang.Exception -> L3c
            r10 = r14
            java.util.Set r10 = (java.util.Set) r10     // Catch: java.lang.Exception -> L3c
            boolean r14 = r0.a     // Catch: java.lang.Exception -> L3c
            r11 = r14 ^ 1
            com.apollographql.apollo3.network.http.c r14 = r13.k     // Catch: java.lang.Exception -> L3c
            p.z90 r14 = r14.e     // Catch: java.lang.Exception -> L3c
            com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$2$1 r0 = new com.apollographql.apollo3.network.http.HttpNetworkTransport$multipleResponses$2$1     // Catch: java.lang.Exception -> L3c
            p.tq1 r7 = r13.l     // Catch: java.lang.Exception -> L3c
            com.apollographql.apollo3.api.c r9 = r13.m     // Catch: java.lang.Exception -> L3c
            r6 = r0
            r6.<init>()     // Catch: java.lang.Exception -> L3c
            r1.L$0 = r15     // Catch: java.lang.Exception -> L3c
            r1.label = r5     // Catch: java.lang.Exception -> L3c
            r14.getClass()     // Catch: java.lang.Exception -> L3c
            java.lang.Object r14 = r0.invoke()     // Catch: java.lang.Exception -> L3c
            if (r14 != r2) goto L83
            return r2
        L83:
            r12 = r15
            r15 = r14
            r14 = r12
        L86:
            p.o7 r15 = (p.o7) r15     // Catch: java.lang.Exception -> L3c
            r0 = 0
            r1.L$0 = r0
            r1.label = r4
            java.lang.Object r14 = r14.b(r15, r1)
            if (r14 != r2) goto L94
            return r2
        L94:
            p.lq2 r14 = p.lq2.a
            return r14
        L97:
            java.lang.NullPointerException r14 = new java.lang.NullPointerException     // Catch: java.lang.Exception -> L3c
            java.lang.String r15 = "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>"
            r14.<init>(r15)     // Catch: java.lang.Exception -> L3c
            throw r14     // Catch: java.lang.Exception -> L3c
        L9f:
            int r15 = com.apollographql.apollo3.network.http.c.g
            boolean r15 = r14 instanceof com.apollographql.apollo3.exception.ApolloException
            if (r15 == 0) goto La8
            com.apollographql.apollo3.exception.ApolloException r14 = (com.apollographql.apollo3.exception.ApolloException) r14
            goto Lb0
        La8:
            com.apollographql.apollo3.exception.ApolloParseException r15 = new com.apollographql.apollo3.exception.ApolloParseException
            java.lang.String r0 = "Failed to parse GraphQL http network response"
            r15.<init>(r0, r14)
            r14 = r15
        Lb0:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.http.b.b(java.lang.Object, p.c00):java.lang.Object");
    }
}
