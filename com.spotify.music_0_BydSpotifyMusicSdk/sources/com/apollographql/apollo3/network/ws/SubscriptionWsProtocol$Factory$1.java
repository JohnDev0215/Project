package com.apollographql.apollo3.network.ws;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e60;
import p.lq2;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$Factory$1", f = "SubscriptionWsProtocol.kt", l = {}, m = "invokeSuspend")
final class SubscriptionWsProtocol$Factory$1 extends SuspendLambda implements ry0 {
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 c(c00 c00Var) {
        return new SubscriptionWsProtocol$Factory$1(1, c00Var);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        kotlin.a.d(obj);
        return null;
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        ((SubscriptionWsProtocol$Factory$1) c((c00) obj)).i(lq2.a);
        return null;
    }
}
