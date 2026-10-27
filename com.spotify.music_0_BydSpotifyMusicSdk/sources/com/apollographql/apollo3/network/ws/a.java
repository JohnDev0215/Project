package com.apollographql.apollo3.network.ws;

import okhttp3.WebSocket;
import p.cw2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class a implements cw2 {
    public final WebSocket.Factory a;

    public a(WebSocket.Factory factory) {
        v03.j(factory, "webSocketFactory");
        this.a = factory;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(java.lang.String r7, java.util.List r8, p.c00 r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1
            if (r0 == 0) goto L13
            r0 = r9
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = (com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1 r0 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L38
            if (r2 != r3) goto L30
            java.lang.Object r7 = r0.L$1
            okhttp3.WebSocket r7 = (okhttp3.WebSocket) r7
            java.lang.Object r8 = r0.L$0
            p.fr r8 = (p.fr) r8
            kotlin.a.d(r9)
            goto Lb8
        L30:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L38:
            kotlin.a.d(r9)
            p.fr r9 = new p.fr
            r2 = 2147483647(0x7fffffff, float:NaN)
            r4 = 0
            r5 = 6
            kotlinx.coroutines.channels.a r2 = p.q30.a(r2, r4, r5)
            r9.<init>(r2)
            p.bv r2 = new p.bv
            r2.<init>(r3)
            r2.L(r4)
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder
            r4.<init>()
            r4.h(r7)
            okhttp3.Headers r7 = p.he1.U(r8)
            r4.e(r7)
            okhttp3.Request r7 = r4.b()
            p.f80 r8 = new p.f80
            r8.<init>(r2, r9)
            okhttp3.WebSocket$Factory r4 = r6.a
            okhttp3.internal.ws.RealWebSocket r7 = r4.a(r7, r8)
            r0.L$0 = r9
            r0.L$1 = r7
            r0.label = r3
        L75:
            java.lang.Object r8 = r2.I()
            boolean r4 = r8 instanceof p.e41
            if (r4 != 0) goto L8b
            boolean r0 = r8 instanceof p.hv
            if (r0 != 0) goto L86
            java.lang.Object r8 = p.y61.a(r8)
            goto Lb4
        L86:
            p.hv r8 = (p.hv) r8
            java.lang.Throwable r7 = r8.a
            throw r7
        L8b:
            int r8 = r2.V(r8)
            if (r8 < 0) goto L75
            p.u61 r8 = new p.u61
            p.c00 r0 = p.vr2.k(r0)
            r8.<init>(r0, r2)
            r8.r()
            p.qa0 r0 = new p.qa0
            r4 = 2
            r0.<init>(r4, r8)
            r4 = 0
            p.oa0 r0 = r2.M(r4, r3, r0)
            p.rp r2 = new p.rp
            r2.<init>(r3, r0)
            r8.t(r2)
            java.lang.Object r8 = r8.q()
        Lb4:
            if (r8 != r1) goto Lb7
            return r1
        Lb7:
            r8 = r9
        Lb8:
            com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2 r9 = new com.apollographql.apollo3.network.ws.DefaultWebSocketEngine$open$2
            r9.<init>()
            r8.getClass()
            r8.e = r9
            p.e80 r9 = new p.e80
            r9.<init>(r8, r7)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.a.a(java.lang.String, java.util.List, p.c00):java.lang.Object");
    }
}
