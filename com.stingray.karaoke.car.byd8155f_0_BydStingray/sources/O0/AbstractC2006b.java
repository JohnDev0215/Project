package o0;

/* JADX INFO: renamed from: o0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2006b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f20175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long f20176b;
    public static final long c;
    public static final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f20177e = 0;

    static {
        long j3 = 3;
        long j10 = j3 << 32;
        f20175a = (((long) 0) & 4294967295L) | j10;
        f20176b = (((long) 1) & 4294967295L) | j10;
        c = j10 | (((long) 2) & 4294967295L);
        d = (j3 & 4294967295L) | (((long) 4) << 32);
    }

    public static final boolean a(long j3, long j10) {
        return j3 == j10;
    }

    public static String b(long j3) {
        return a(j3, f20175a) ? "Rgb" : a(j3, f20176b) ? "Xyz" : a(j3, c) ? "Lab" : a(j3, d) ? "Cmyk" : "Unknown";
    }
}
