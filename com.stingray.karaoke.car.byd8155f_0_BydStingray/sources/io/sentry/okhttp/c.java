package io.sentry.okhttp;

import A7.m;
import A7.o;
import java.net.InetAddress;
import java.net.Proxy;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f16638n = new c(1, 0);
    public static final c o = new c(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16639m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(int i7, int i10) {
        super(i7);
        this.f16639m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f16639m) {
            case 0:
                InetAddress inetAddress = (InetAddress) obj;
                m.f(inetAddress, "address");
                String string = inetAddress.toString();
                m.e(string, "address.toString()");
                return string;
            default:
                Proxy proxy = (Proxy) obj;
                m.f(proxy, "proxy");
                String string2 = proxy.toString();
                m.e(string2, "proxy.toString()");
                return string2;
        }
    }
}
