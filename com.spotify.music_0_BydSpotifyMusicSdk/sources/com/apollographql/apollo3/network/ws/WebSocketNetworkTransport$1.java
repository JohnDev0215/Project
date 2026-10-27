package com.apollographql.apollo3.network.ws;

import java.io.IOException;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.k52;
import p.lq2;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$1", f = "WebSocketNetworkTransport.kt", l = {86}, m = "invokeSuspend")
final class WebSocketNetworkTransport$1 extends SuspendLambda implements fz0 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ f this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$1(f fVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = fVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        WebSocketNetworkTransport$1 webSocketNetworkTransport$1 = new WebSocketNetworkTransport$1(this.this$0, c00Var);
        webSocketNetworkTransport$1.L$0 = obj;
        return webSocketNetworkTransport$1;
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((WebSocketNetworkTransport$1) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) throws IOException {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            e20 e20Var = (e20) this.L$0;
            f fVar = this.this$0;
            this.label = 1;
            if (f.b(fVar, e20Var, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        k52 k52Var = this.this$0.j;
        if (!k52Var.a) {
            ((kotlinx.coroutines.f) k52Var.b).close();
        }
        return lq2.a;
    }
}
