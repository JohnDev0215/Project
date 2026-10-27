package com.spotify.connectivity.product_state.esperanto.proto;

import com.spotify.esperanto.esperanto.Transport;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateEsperantoKt {
    public static final ProductStateClient createProductStateClient(Transport transport) {
        v03.j(transport, "transport");
        return new ProductStateClientImpl(transport);
    }
}
