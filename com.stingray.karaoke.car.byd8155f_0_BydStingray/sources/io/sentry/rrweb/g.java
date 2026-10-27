package io.sentry.rrweb;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g extends e implements InterfaceC1485m0 {
    public f o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f16838p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f16839q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f16840r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f16841s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f16842t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public HashMap f16843u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public HashMap f16844v;

    public g() {
        super(d.MouseInteraction);
        this.f16841s = 2;
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
        c1489n1.w("type");
        c1489n1.I(k10, this.o);
        c1489n1.w("id");
        c1489n1.H(this.f16838p);
        c1489n1.w("x");
        c1489n1.G(this.f16839q);
        c1489n1.w("y");
        c1489n1.G(this.f16840r);
        c1489n1.w("pointerType");
        c1489n1.H(this.f16841s);
        c1489n1.w("pointerId");
        c1489n1.H(this.f16842t);
        HashMap map = this.f16844v;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16844v, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        HashMap map2 = this.f16843u;
        if (map2 != null) {
            for (String str2 : map2.keySet()) {
                AbstractC1347d.s(this.f16843u, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
    }
}
