package io.sentry.clientreport;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import n7.AbstractC1985B;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC1485m0 {
    public final Date l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f16497m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f16498n;

    public b(Date date, ArrayList arrayList) {
        this.l = date;
        this.f16497m = arrayList;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("timestamp");
        c1489n1.L(AbstractC1985B.m(this.l));
        c1489n1.w("discarded_events");
        c1489n1.I(k10, this.f16497m);
        HashMap map = this.f16498n;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16498n, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
