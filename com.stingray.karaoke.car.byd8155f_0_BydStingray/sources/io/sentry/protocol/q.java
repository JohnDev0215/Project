package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Integer f16763m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f16764n;
    public Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f16765p;

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("sdk_name");
            c1489n1.L(this.l);
        }
        if (this.f16763m != null) {
            c1489n1.w("version_major");
            c1489n1.K(this.f16763m);
        }
        if (this.f16764n != null) {
            c1489n1.w("version_minor");
            c1489n1.K(this.f16764n);
        }
        if (this.o != null) {
            c1489n1.w("version_patchlevel");
            c1489n1.K(this.o);
        }
        HashMap map = this.f16765p;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16765p, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
