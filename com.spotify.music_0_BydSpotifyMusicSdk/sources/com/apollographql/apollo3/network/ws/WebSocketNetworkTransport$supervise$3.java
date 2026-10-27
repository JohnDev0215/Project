package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$supervise$3", f = "WebSocketNetworkTransport.kt", l = {213}, m = "invokeSuspend")
final class WebSocketNetworkTransport$supervise$3 extends SuspendLambda implements fz0 {
    final /* synthetic */ Ref$ObjectRef<g> $protocol;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$supervise$3(Ref$ObjectRef ref$ObjectRef, c00 c00Var) {
        super(2, c00Var);
        this.$protocol = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new WebSocketNetworkTransport$supervise$3(this.$protocol, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((WebSocketNetworkTransport$supervise$3) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            g gVar = this.$protocol.element;
            v03.h(gVar);
            this.label = 1;
            if (g.c(gVar, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
