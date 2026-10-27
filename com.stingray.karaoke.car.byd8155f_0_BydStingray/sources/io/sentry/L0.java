package io.sentry;

import h2.AbstractC1347d;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class L0 implements InterfaceC1485m0 {
    public Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f15890m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f15891n;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || L0.class != obj.getClass()) {
            return false;
        }
        L0 l02 = (L0) obj;
        return h6.e.i(this.l, l02.l) && h6.e.i(this.f15890m, l02.f15890m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f15890m});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("segment_id");
            c1489n1.K(this.l);
        }
        HashMap map = this.f15891n;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f15891n, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
        io.sentry.vendor.gson.stream.c cVar = (io.sentry.vendor.gson.stream.c) c1489n1.f16624m;
        cVar.f16976q = true;
        if (this.l != null) {
            cVar.o();
            cVar.b();
            cVar.l.append((CharSequence) "\n");
        }
        List list = this.f15890m;
        if (list != null) {
            c1489n1.I(k10, list);
        }
        cVar.f16976q = false;
    }
}
