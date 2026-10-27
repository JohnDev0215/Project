package com.spotify.esperanto.esperanto;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import p.v03;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public class ClientBase {
    private final Transport transport;

    public ClientBase(Transport transport) {
        v03.j(transport, "transport");
        this.transport = transport;
    }

    public final Single<byte[]> callSingle(String str, String str2, wj1 wj1Var) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(wj1Var, "payload");
        Transport transport = this.transport;
        byte[] byteArray = wj1Var.toByteArray();
        v03.i(byteArray, "payload.toByteArray()");
        return transport.callSingle(str, str2, byteArray);
    }

    public final Observable<byte[]> callStream(String str, String str2, wj1 wj1Var) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(wj1Var, "payload");
        Transport transport = this.transport;
        byte[] byteArray = wj1Var.toByteArray();
        v03.i(byteArray, "payload.toByteArray()");
        return transport.callStream(str, str2, byteArray);
    }

    public final byte[] callSync(String str, String str2, wj1 wj1Var) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(wj1Var, "payload");
        Transport transport = this.transport;
        byte[] byteArray = wj1Var.toByteArray();
        v03.i(byteArray, "payload.toByteArray()");
        return transport.callSync(str, str2, byteArray);
    }
}
