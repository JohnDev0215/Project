package com.apollographql.apollo3.network.ws;

import p.fv0;
import p.m7;

/* JADX INFO: loaded from: classes.dex */
public final class d implements fv0 {
    public final /* synthetic */ fv0 c;
    public final /* synthetic */ m7 e;

    public d(fv0 fv0Var, m7 m7Var) {
        this.c = fv0Var;
        this.e = m7Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @Override // p.fv0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.Object r6, p.c00 r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1
            if (r0 == 0) goto L13
            r0 = r7
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1 r0 = (com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1 r0 = new com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$$inlined$filter$1$2$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            kotlin.a.d(r7)
            goto L58
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            kotlin.a.d(r7)
            r7 = r6
            p.bi0 r7 = (p.bi0) r7
            java.lang.String r2 = r7.getId()
            p.m7 r4 = r5.e
            java.util.UUID r4 = r4.b
            java.lang.String r4 = r4.toString()
            boolean r2 = p.v03.b(r2, r4)
            if (r2 != 0) goto L4d
            java.lang.String r7 = r7.getId()
            if (r7 != 0) goto L58
        L4d:
            r0.label = r3
            p.fv0 r7 = r5.c
            java.lang.Object r6 = r7.b(r6, r0)
            if (r6 != r1) goto L58
            return r1
        L58:
            p.lq2 r6 = p.lq2.a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.d.b(java.lang.Object, p.c00):java.lang.Object");
    }
}
