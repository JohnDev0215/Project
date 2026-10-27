package io.sentry.android.replay.capture;

import io.sentry.C1523x;
import io.sentry.H1;
import io.sentry.J;
import io.sentry.L0;

/* JADX INFO: loaded from: classes.dex */
public final class i extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H1 f16376a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final L0 f16377b;

    public i(H1 h12, L0 l02) {
        this.f16376a = h12;
        this.f16377b = l02;
    }

    public static void a(i iVar, J j3) {
        C1523x c1523x = new C1523x();
        iVar.getClass();
        if (j3 != null) {
            c1523x.f16985f = iVar.f16377b;
            j3.n(iVar.f16376a, c1523x);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof i)) {
            return false;
        }
        i iVar = (i) obj;
        return A7.m.a(this.f16376a, iVar.f16376a) && A7.m.a(this.f16377b, iVar.f16377b);
    }

    public final int hashCode() {
        return this.f16377b.hashCode() + (this.f16376a.hashCode() * 31);
    }

    public final String toString() {
        return "Created(replay=" + this.f16376a + ", recording=" + this.f16377b + ')';
    }
}
