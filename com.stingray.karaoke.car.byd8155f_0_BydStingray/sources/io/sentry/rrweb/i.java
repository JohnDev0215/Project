package io.sentry.rrweb;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i extends e implements InterfaceC1485m0 {
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public List f16848p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public HashMap f16849q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public HashMap f16850r;

    public i() {
        super(d.TouchMove);
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("type");
        c1489n1.I(k10, this.l);
        c1489n1.w("timestamp");
        c1489n1.H(this.f16836m);
        c1489n1.w("data");
        c1489n1.i();
        c1489n1.w(BYDAutoMultimediaDevice.PARAM_SOURCE);
        c1489n1.I(k10, this.f16837n);
        List list = this.f16848p;
        if (list != null && !list.isEmpty()) {
            c1489n1.w("positions");
            c1489n1.I(k10, this.f16848p);
        }
        c1489n1.w("pointerId");
        c1489n1.H(this.o);
        HashMap map = this.f16850r;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16850r, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        HashMap map2 = this.f16849q;
        if (map2 != null) {
            for (String str2 : map2.keySet()) {
                AbstractC1347d.s(this.f16849q, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
    }
}
