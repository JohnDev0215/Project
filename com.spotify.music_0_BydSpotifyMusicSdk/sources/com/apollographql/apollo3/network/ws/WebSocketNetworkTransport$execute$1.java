package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.fv0;
import p.fz0;
import p.lq2;
import p.m7;
import p.wf2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$1", f = "WebSocketNetworkTransport.kt", l = {252}, m = "invokeSuspend")
final class WebSocketNetworkTransport$execute$1 extends SuspendLambda implements fz0 {
    final /* synthetic */ m7 $request;
    int label;
    final /* synthetic */ f this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$1(f fVar, m7 m7Var, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = fVar;
        this.$request = m7Var;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new WebSocketNetworkTransport$execute$1(this.this$0, this.$request, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((WebSocketNetworkTransport$execute$1) a((fv0) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            kotlinx.coroutines.channels.a aVar = this.this$0.g;
            wf2 wf2Var = new wf2(this.$request);
            this.label = 1;
            if (aVar.l(wf2Var, this) == coroutineSingletons) {
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
