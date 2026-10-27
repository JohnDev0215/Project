package com.android.wifitrackerlib;

import android.net.wifi.WifiConfiguration;
import java.util.function.Function;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class X implements Function {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ X f1449a = new X();

    private /* synthetic */ X() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return StandardWifiEntry.h0((WifiConfiguration) obj);
    }
}
