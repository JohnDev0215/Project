package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.esperanto.esperanto.Transport;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EsAccessTokenClientServiceEsperantoKt {
    public static final AccessTokenClientClient createAccessTokenClientClient(Transport transport) {
        v03.j(transport, "transport");
        return new AccessTokenClientClientImpl(transport);
    }
}
