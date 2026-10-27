package com.spotify.connectivity.auth.login5.esperanto.proto;

import com.spotify.esperanto.esperanto.Transport;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EsLogin5EsperantoKt {
    public static final Login5Client createLogin5Client(Transport transport) {
        v03.j(transport, "transport");
        return new Login5ClientImpl(transport);
    }
}
