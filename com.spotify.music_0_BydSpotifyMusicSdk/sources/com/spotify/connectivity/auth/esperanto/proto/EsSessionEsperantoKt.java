package com.spotify.connectivity.auth.esperanto.proto;

import com.spotify.esperanto.esperanto.Transport;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EsSessionEsperantoKt {
    public static final SessionClient createSessionClient(Transport transport) {
        v03.j(transport, "transport");
        return new SessionClientImpl(transport);
    }
}
