package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.bi0;
import p.c00;
import p.e01;
import p.e60;
import p.fv0;
import p.hz0;
import p.lq2;
import p.m7;
import p.vq1;
import p.xm1;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.WebSocketNetworkTransport$execute$3", f = "WebSocketNetworkTransport.kt", l = {261, 272}, m = "invokeSuspend")
final class WebSocketNetworkTransport$execute$3 extends SuspendLambda implements hz0 {
    final /* synthetic */ m7 $request;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebSocketNetworkTransport$execute$3(m7 m7Var, c00 c00Var) {
        super(3, c00Var);
        this.$request = m7Var;
    }

    @Override // p.hz0
    public final Object e(Object obj, Object obj2, Object obj3) {
        WebSocketNetworkTransport$execute$3 webSocketNetworkTransport$execute$3 = new WebSocketNetworkTransport$execute$3(this.$request, (c00) obj3);
        webSocketNetworkTransport$execute$3.L$0 = (fv0) obj;
        webSocketNetworkTransport$execute$3.L$1 = (bi0) obj2;
        return webSocketNetworkTransport$execute$3.i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        boolean z = false;
        if (i == 0) {
            kotlin.a.d(obj);
            fv0 fv0Var = (fv0) this.L$0;
            bi0 bi0Var = (bi0) this.L$1;
            if (!(bi0Var instanceof vq1)) {
                if (bi0Var instanceof xm1) {
                    this.L$0 = null;
                    this.label = 1;
                    if (fv0Var.b(bi0Var, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (bi0Var instanceof e01) {
                        System.out.println((Object) ("Received general error while executing operation " + this.$request.a.name() + ": " + ((e01) bi0Var).a));
                    } else {
                        this.L$0 = null;
                        this.label = 2;
                        if (fv0Var.b(bi0Var, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                    }
                    z = true;
                }
            }
        } else if (i == 1) {
            kotlin.a.d(obj);
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
