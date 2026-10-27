package h6;

import java.net.Proxy;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6735a;

    static {
        int[] iArr = new int[Proxy.Type.values().length];
        f6735a = iArr;
        iArr[Proxy.Type.DIRECT.ordinal()] = 1;
        iArr[Proxy.Type.HTTP.ordinal()] = 2;
    }
}
