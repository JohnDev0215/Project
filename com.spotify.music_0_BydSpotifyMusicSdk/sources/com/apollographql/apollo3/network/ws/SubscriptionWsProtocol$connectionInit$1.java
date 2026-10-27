package com.apollographql.apollo3.network.ws;

import com.spotify.connect.esperanto.proto.ConnectMessages$StateResponse;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import p.c00;
import p.e60;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "com.apollographql.apollo3.network.ws.SubscriptionWsProtocol", f = "SubscriptionWsProtocol.kt", l = {31, ConnectMessages$StateResponse.Device.AUDIO_OUTPUT_DEVICE_NAME_FIELD_NUMBER}, m = "connectionInit")
final class SubscriptionWsProtocol$connectionInit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ c this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubscriptionWsProtocol$connectionInit$1(c cVar, c00 c00Var) {
        super(c00Var);
        this.this$0 = cVar;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.e(this);
    }
}
