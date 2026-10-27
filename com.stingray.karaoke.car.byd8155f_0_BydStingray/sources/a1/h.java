package a1;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f11033a;

    public /* synthetic */ h(long j3) {
        this.f11033a = j3;
    }

    public static long a(int i7, int i10, int i11, long j3) {
        if ((i11 & 1) != 0) {
            i7 = (int) (j3 >> 32);
        }
        if ((i11 & 2) != 0) {
            i10 = (int) (j3 & 4294967295L);
        }
        return (((long) i10) & 4294967295L) | (((long) i7) << 32);
    }

    public static final boolean b(long j3, long j10) {
        return j3 == j10;
    }

    public static final long c(long j3, long j10) {
        return (((long) (((int) (j3 >> 32)) - ((int) (j10 >> 32)))) << 32) | (((long) (((int) (j3 & 4294967295L)) - ((int) (j10 & 4294967295L)))) & 4294967295L);
    }

    public static final long d(long j3, long j10) {
        return (((long) (((int) (j3 >> 32)) + ((int) (j10 >> 32)))) << 32) | (((long) (((int) (j3 & 4294967295L)) + ((int) (j10 & 4294967295L)))) & 4294967295L);
    }

    public static String e(long j3) {
        StringBuilder sb = new StringBuilder("(");
        sb.append((int) (j3 >> 32));
        sb.append(", ");
        return android.hardware.bydauto.ac.tsimpl.a.j(sb, (int) (j3 & 4294967295L), ')');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof h) {
            return this.f11033a == ((h) obj).f11033a;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.f11033a);
    }

    public final String toString() {
        return e(this.f11033a);
    }
}
