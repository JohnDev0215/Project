package J0;

import A7.m;
import s0.C2337d;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C2337d f3042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f3043b;

    public a(C2337d c2337d, int i7) {
        this.f3042a = c2337d;
        this.f3043b = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return m.a(this.f3042a, aVar.f3042a) && this.f3043b == aVar.f3043b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.f3043b) + (this.f3042a.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ImageVectorEntry(imageVector=");
        sb.append(this.f3042a);
        sb.append(", configFlags=");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.f3043b, ')');
    }
}
