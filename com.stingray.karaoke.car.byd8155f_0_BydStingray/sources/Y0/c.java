package Y0;

import n0.AbstractC1887p;
import n0.C1891t;

/* JADX INFO: loaded from: classes.dex */
public final class c implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f10608a;

    public c(long j3) {
        this.f10608a = j3;
        if (j3 == 16) {
            throw new IllegalArgumentException("ColorStyle value must be specified, use TextForegroundStyle.Unspecified instead.");
        }
    }

    @Override // Y0.m
    public final long a() {
        return this.f10608a;
    }

    @Override // Y0.m
    public final AbstractC1887p b() {
        return null;
    }

    @Override // Y0.m
    public final float c() {
        return C1891t.d(this.f10608a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof c) && C1891t.c(this.f10608a, ((c) obj).f10608a);
    }

    public final int hashCode() {
        int i7 = C1891t.l;
        return Long.hashCode(this.f10608a);
    }

    public final String toString() {
        return "ColorStyle(value=" + ((Object) C1891t.i(this.f10608a)) + ')';
    }
}
