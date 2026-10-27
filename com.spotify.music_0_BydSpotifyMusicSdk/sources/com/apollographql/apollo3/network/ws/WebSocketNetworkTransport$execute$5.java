package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.ag2;
import p.c00;
import p.e60;
import p.hz0;
import p.lq2;
import p.m7;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$5", f = "WebSocketNetworkTransport.kt", l = {304}, m = "invokeSuspend")
final class WebSocketNetworkTransport$execute$5 extends SuspendLambda implements hz0 {
    final /* synthetic */ m7 $request;
    int label;
    final /* synthetic */ f this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$5(f fVar, m7 m7Var, c00 c00Var) {
        super(3, c00Var);
        this.this$0 = fVar;
        this.$request = m7Var;
    }

    @Override // p.hz0
    public final Object e(Object obj, Object obj2, Object obj3) {
        return new WebSocketNetworkTransport$execute$5(this.this$0, this.$request, (c00) obj3).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            kotlinx.coroutines.channels.a aVar = this.this$0.g;
            ag2 ag2Var = new ag2(this.$request);
            this.label = 1;
            if (aVar.l(ag2Var, this) == coroutineSingletons) {
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
