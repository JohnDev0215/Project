package com.apollographql.apollo3.network.ws;

import com.apollographql.apollo3.exception.ApolloNetworkException;
import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import com.spotify.connectivity.productstate.RxProductState;
import java.util.Map;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.c00;
import p.e20;
import p.e60;
import p.fz0;
import p.lq2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol$connectionInit$2", f = "SubscriptionWsProtocol.kt", l = {ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_TYPE_FIELD_NUMBER}, m = "invokeSuspend")
final class SubscriptionWsProtocol$connectionInit$2 extends SuspendLambda implements fz0 {
    int label;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubscriptionWsProtocol$connectionInit$2(c cVar, c00 c00Var) {
        super(2, c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final c00 a(Object obj, c00 c00Var) {
        return new SubscriptionWsProtocol$connectionInit$2(this.this$0, c00Var);
    }

    @Override // p.fz0
    public final Object g(Object obj, Object obj2) {
        return ((SubscriptionWsProtocol$connectionInit$2) a((e20) obj, (c00) obj2)).i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            c cVar = this.this$0;
            this.label = 1;
            obj = cVar.b(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        Map map = (Map) obj;
        Object obj2 = map.get(RxProductState.Keys.KEY_TYPE);
        boolean zB = v03.b(obj2, "connection_ack");
        lq2 lq2Var = lq2.a;
        if (zB) {
            return lq2Var;
        }
        if (v03.b(obj2, "connection_error")) {
            throw new ApolloNetworkException(null, "Connection error:\n" + map);
        }
        System.out.println((Object) ("unknown message while waiting for connection_ack: '" + obj2));
        return lq2Var;
    }
}
