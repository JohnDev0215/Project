package e1;

import h2.AbstractC1347d;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f13809a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f13810b = true;
    public final boolean c = true;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof t)) {
            return false;
        }
        t tVar = (t) obj;
        return this.f13809a == tVar.f13809a && this.f13810b == tVar.f13810b && this.c == tVar.c;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + AbstractC1347d.d((AbstractC2641j.c(1) + AbstractC1347d.d(Boolean.hashCode(this.f13809a) * 31, 31, this.f13810b)) * 31, 31, this.c);
    }
}
