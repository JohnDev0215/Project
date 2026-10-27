package spi;

import java.net.Proxy;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class dg {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[Proxy.Type.values().length];
        a = iArr;
        iArr[Proxy.Type.DIRECT.ordinal()] = 1;
    }
}
