package io.sentry.protocol;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class B implements InterfaceC1485m0 {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ConcurrentHashMap f16654m;

    public B(String str) {
        this.l = str;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        String str = this.l;
        if (str != null) {
            c1489n1.w(BYDAutoMultimediaDevice.PARAM_SOURCE);
            c1489n1.I(k10, str);
        }
        ConcurrentHashMap concurrentHashMap = this.f16654m;
        if (concurrentHashMap != null) {
            for (String str2 : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16654m, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
    }
}
