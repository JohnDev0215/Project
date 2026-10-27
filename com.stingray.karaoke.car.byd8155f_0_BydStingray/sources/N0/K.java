package N0;

import a.AbstractC0904a;

/* JADX INFO: loaded from: classes.dex */
public final class K {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f4356b = AbstractC0904a.q(0, 0);
    public static final /* synthetic */ int c = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f4357a;

    public /* synthetic */ K(long j3) {
        this.f4357a = j3;
    }

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static final boolean b(long j3) {
        return ((int) (j3 >> 32)) == ((int) (j3 & 4294967295L));
    }

    public static final int c(long j3) {
        return d(j3) - e(j3);
    }

    public static final int d(long j3) {
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        return i7 > i10 ? i7 : i10;
    }

    public static final int e(long j3) {
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        return i7 > i10 ? i10 : i7;
    }

    public static final boolean f(long j3) {
        return ((int) (j3 >> 32)) > ((int) (j3 & 4294967295L));
    }

    public static String g(long j3) {
        StringBuilder sb = new StringBuilder("TextRange(");
        sb.append((int) (j3 >> 32));
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, (int) (j3 & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof K) {
            return this.f4357a == ((K) obj).f4357a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f4357a);
    }

    public final String toString() {
        return g(this.f4357a);
    }
}
