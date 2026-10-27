package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.p61;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4", f = "WebSocketNetworkTransport.kt", l = {233}, m = "invokeSuspend")
final class WebSocketNetworkTransport$supervise$4 extends SuspendLambda implements fz0 {
    final /* synthetic */ Ref$ObjectRef<p61> $connectionJob;
    final /* synthetic */ Ref$ObjectRef<p61> $idleJob;
    final /* synthetic */ Ref$ObjectRef<g> $protocol;
    int label;
    final /* synthetic */ f this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$supervise$4(f fVar, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, Ref$ObjectRef ref$ObjectRef3, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = fVar;
        this.$protocol = ref$ObjectRef;
        this.$connectionJob = ref$ObjectRef2;
        this.$idleJob = ref$ObjectRef3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new WebSocketNetworkTransport$supervise$4(this.this$0, this.$protocol, this.$connectionJob, this.$idleJob, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((WebSocketNetworkTransport$supervise$4) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(java.lang.Object r9) {
        /*
            r8 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.c
            int r1 = r8.label
            p.lq2 r2 = p.lq2.a
            r3 = 1
            if (r1 == 0) goto L17
            if (r1 != r3) goto Lf
            kotlin.a.d(r9)
            goto L4f
        Lf:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L17:
            kotlin.a.d(r9)
            com.apollographql.apollo3.network.ws.f r9 = r8.this$0
            long r4 = r9.d
            r8.label = r3
            r6 = 0
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 > 0) goto L28
        L26:
            r9 = r2
            goto L4c
        L28:
            p.zp r9 = new p.zp
            p.c00 r1 = p.vr2.k(r8)
            r9.<init>(r3, r1)
            r9.r()
            r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L46
            p.y10 r1 = r9.m
            p.o80 r1 = p.bk0.t(r1)
            r1.X(r4, r9)
        L46:
            java.lang.Object r9 = r9.q()
            if (r9 != r0) goto L26
        L4c:
            if (r9 != r0) goto L4f
            return r0
        L4f:
            kotlin.jvm.internal.Ref$ObjectRef<com.apollographql.apollo3.network.ws.g> r9 = r8.$protocol
            kotlin.jvm.internal.Ref$ObjectRef<p.p61> r0 = r8.$connectionJob
            kotlin.jvm.internal.Ref$ObjectRef<p.p61> r1 = r8.$idleJob
            com.apollographql.apollo3.network.ws.f.c(r9, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$4.i(java.lang.Object):java.lang.Object");
    }
}
