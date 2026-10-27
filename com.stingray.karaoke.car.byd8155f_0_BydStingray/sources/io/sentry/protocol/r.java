package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes.dex */
public final class r implements InterfaceC1485m0 {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16766m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CopyOnWriteArraySet f16767n;
    public CopyOnWriteArraySet o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f16768p;

    public r(String str, String str2) {
        this.l = str;
        this.f16766m = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || r.class != obj.getClass()) {
            return false;
        }
        r rVar = (r) obj;
        return this.l.equals(rVar.l) && this.f16766m.equals(rVar.f16766m);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.l, this.f16766m});
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("name");
        c1489n1.L(this.l);
        c1489n1.w("version");
        c1489n1.L(this.f16766m);
        CopyOnWriteArraySet copyOnWriteArraySet = this.f16767n;
        if (copyOnWriteArraySet == null) {
            copyOnWriteArraySet = (CopyOnWriteArraySet) C1489n1.s().f16625n;
        }
        CopyOnWriteArraySet copyOnWriteArraySet2 = this.o;
        if (copyOnWriteArraySet2 == null) {
            copyOnWriteArraySet2 = (CopyOnWriteArraySet) C1489n1.s().f16624m;
        }
        if (!copyOnWriteArraySet.isEmpty()) {
            c1489n1.w("packages");
            c1489n1.I(k10, copyOnWriteArraySet);
        }
        if (!copyOnWriteArraySet2.isEmpty()) {
            c1489n1.w("integrations");
            c1489n1.I(k10, copyOnWriteArraySet2);
        }
        HashMap map = this.f16768p;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16768p, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
