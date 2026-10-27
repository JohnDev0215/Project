package T0;

import N0.C0411f;
import N0.K;
import a.AbstractC0904a;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0411f f8481a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f8482b;
    public final K c;

    static {
        io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
    }

    public y(C0411f c0411f, long j3, K k10) {
        K k11;
        this.f8481a = c0411f;
        this.f8482b = AbstractC0904a.w(j3, c0411f.l.length());
        if (k10 != null) {
            k11 = new K(AbstractC0904a.w(k10.f4357a, c0411f.l.length()));
        } else {
            k11 = null;
        }
        this.c = k11;
    }

    public static y a(y yVar, C0411f c0411f, long j3, int i7) {
        if ((i7 & 1) != 0) {
            c0411f = yVar.f8481a;
        }
        if ((i7 & 2) != 0) {
            j3 = yVar.f8482b;
        }
        K k10 = (i7 & 4) != 0 ? yVar.c : null;
        yVar.getClass();
        return new y(c0411f, j3, k10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return K.a(this.f8482b, yVar.f8482b) && A7.m.a(this.c, yVar.c) && A7.m.a(this.f8481a, yVar.f8481a);
    }

    public final int hashCode() {
        int iHashCode = this.f8481a.hashCode() * 31;
        int i7 = K.c;
        int iC = AbstractC1347d.c(iHashCode, 31, this.f8482b);
        K k10 = this.c;
        return iC + (k10 != null ? Long.hashCode(k10.f4357a) : 0);
    }

    public final String toString() {
        return "TextFieldValue(text='" + ((Object) this.f8481a) + "', selection=" + ((Object) K.g(this.f8482b)) + ", composition=" + this.c + ')';
    }

    public y(String str, long j3, int i7) {
        this(new C0411f((i7 & 1) != 0 ? "" : str, null, 6), (i7 & 2) != 0 ? K.f4356b : j3, (K) null);
    }
}
