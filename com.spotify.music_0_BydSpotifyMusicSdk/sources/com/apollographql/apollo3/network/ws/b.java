package com.apollographql.apollo3.network.ws;

import p.cz2;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
public final class b implements cz2 {
    public final long a;
    public final ry0 b;
    public final WsFrameType c;

    public b() {
        SubscriptionWsProtocol$Factory$1 subscriptionWsProtocol$Factory$1 = new SubscriptionWsProtocol$Factory$1(1, null);
        WsFrameType wsFrameType = WsFrameType.c;
        this.a = 10000L;
        this.b = subscriptionWsProtocol$Factory$1;
        this.c = wsFrameType;
    }
}
