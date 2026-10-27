package io.sentry;

import h2.AbstractC1347d;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class f2 implements InterfaceC1485m0 {
    public final io.sentry.protocol.t l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f16536m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f16537n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap f16538p;

    public f2(io.sentry.protocol.t tVar, String str, String str2, String str3) {
        this.l = tVar;
        this.f16536m = str;
        this.f16537n = str2;
        this.o = str3;
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        c1489n1.w("event_id");
        this.l.serialize(c1489n1, k10);
        String str = this.f16536m;
        if (str != null) {
            c1489n1.w("name");
            c1489n1.L(str);
        }
        String str2 = this.f16537n;
        if (str2 != null) {
            c1489n1.w("email");
            c1489n1.L(str2);
        }
        String str3 = this.o;
        if (str3 != null) {
            c1489n1.w("comments");
            c1489n1.L(str3);
        }
        HashMap map = this.f16538p;
        if (map != null) {
            for (String str4 : map.keySet()) {
                AbstractC1347d.s(this.f16538p, str4, c1489n1, str4, k10);
            }
        }
        c1489n1.n();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("UserFeedback{eventId=");
        sb.append(this.l);
        sb.append(", name='");
        sb.append(this.f16536m);
        sb.append("', email='");
        sb.append(this.f16537n);
        sb.append("', comments='");
        return android.hardware.bydauto.ac.tsimpl.a.l(sb, this.o, "'}");
    }
}
