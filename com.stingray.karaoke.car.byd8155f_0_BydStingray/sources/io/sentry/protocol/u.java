package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class u implements InterfaceC1485m0 {
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16775m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HashMap f16776n;

    public u(String str, String str2) {
        this.l = str;
        this.f16775m = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || u.class != obj.getClass()) {
            return false;
        }
        u uVar = (u) obj;
        return Objects.equals(this.l, uVar.l) && Objects.equals(this.f16775m, uVar.f16775m);
    }

    public final int hashCode() {
        return Objects.hash(this.l, this.f16775m);
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("name");
        c1489n1.L(this.l);
        c1489n1.w("version");
        c1489n1.L(this.f16775m);
        HashMap map = this.f16776n;
        if (map != null) {
            for (String str : map.keySet()) {
                AbstractC1347d.s(this.f16776n, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
