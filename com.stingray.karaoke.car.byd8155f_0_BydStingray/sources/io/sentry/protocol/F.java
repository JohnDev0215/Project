package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class F implements InterfaceC1485m0 {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final List f16663m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f16664n;

    public F(String str, List list) {
        this.l = str;
        this.f16663m = list;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        String str = this.l;
        if (str != null) {
            c1489n1.w("rendering_system");
            c1489n1.L(str);
        }
        List list = this.f16663m;
        if (list != null) {
            c1489n1.w("windows");
            c1489n1.I(k10, list);
        }
        HashMap map = this.f16664n;
        if (map != null) {
            for (String str2 : map.keySet()) {
                AbstractC1347d.s(this.f16664n, str2, c1489n1, str2, k10);
            }
        }
        c1489n1.n();
    }
}
