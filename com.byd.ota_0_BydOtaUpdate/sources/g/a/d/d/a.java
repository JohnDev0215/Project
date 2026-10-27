package g.a.d.d;

/* JADX INFO: loaded from: classes.dex */
public class a {
    public static int a(int i2) {
        int i3 = i2 & 65535;
        int i4 = (i3 | (i3 << 8)) & 16711935;
        int i5 = (i4 | (i4 << 4)) & 252645135;
        int i6 = (i5 | (i5 << 2)) & 858993459;
        return (i6 | (i6 << 1)) & 1431655765;
    }

    public static long b(int i2) {
        int i3 = ((i2 >>> 8) ^ i2) & 65280;
        int i4 = i2 ^ (i3 ^ (i3 << 8));
        int i5 = ((i4 >>> 4) ^ i4) & 15728880;
        int i6 = i4 ^ (i5 ^ (i5 << 4));
        int i7 = ((i6 >>> 2) ^ i6) & 202116108;
        int i8 = i6 ^ (i7 ^ (i7 << 2));
        int i9 = ((i8 >>> 1) ^ i8) & 572662306;
        int i10 = i8 ^ (i9 ^ (i9 << 1));
        return ((((long) (i10 >>> 1)) & 1431655765) << 32) | (1431655765 & ((long) i10));
    }

    public static void c(long j, long[] jArr, int i2) {
        long j2 = ((j >>> 16) ^ j) & 4294901760L;
        long j3 = j ^ (j2 ^ (j2 << 16));
        long j4 = ((j3 >>> 8) ^ j3) & 280375465148160L;
        long j5 = j3 ^ (j4 ^ (j4 << 8));
        long j6 = ((j5 >>> 4) ^ j5) & 67555025218437360L;
        long j7 = j5 ^ (j6 ^ (j6 << 4));
        long j8 = ((j7 >>> 2) ^ j7) & 868082074056920076L;
        long j9 = j7 ^ (j8 ^ (j8 << 2));
        long j10 = ((j9 >>> 1) ^ j9) & 2459565876494606882L;
        long j11 = j9 ^ (j10 ^ (j10 << 1));
        jArr[i2] = j11 & 6148914691236517205L;
        jArr[i2 + 1] = (j11 >>> 1) & 6148914691236517205L;
    }

    public static void d(long j, long[] jArr, int i2) {
        long j2 = ((j >>> 16) ^ j) & 4294901760L;
        long j3 = j ^ (j2 ^ (j2 << 16));
        long j4 = ((j3 >>> 8) ^ j3) & 280375465148160L;
        long j5 = j3 ^ (j4 ^ (j4 << 8));
        long j6 = ((j5 >>> 4) ^ j5) & 67555025218437360L;
        long j7 = j5 ^ (j6 ^ (j6 << 4));
        long j8 = ((j7 >>> 2) ^ j7) & 868082074056920076L;
        long j9 = j7 ^ (j8 ^ (j8 << 2));
        long j10 = ((j9 >>> 1) ^ j9) & 2459565876494606882L;
        long j11 = j9 ^ (j10 ^ (j10 << 1));
        jArr[i2] = j11 & (-6148914691236517206L);
        jArr[i2 + 1] = (j11 << 1) & (-6148914691236517206L);
    }

    public static int e(int i2) {
        int i3 = i2 & 255;
        int i4 = (i3 | (i3 << 4)) & 3855;
        int i5 = (i4 | (i4 << 2)) & 13107;
        return (i5 | (i5 << 1)) & 21845;
    }

    public static long f(long j) {
        long j2 = ((j >>> 1) ^ j) & 2459565876494606882L;
        long j3 = j ^ (j2 ^ (j2 << 1));
        long j4 = ((j3 >>> 2) ^ j3) & 868082074056920076L;
        long j5 = j3 ^ (j4 ^ (j4 << 2));
        long j6 = ((j5 >>> 4) ^ j5) & 67555025218437360L;
        long j7 = j5 ^ (j6 ^ (j6 << 4));
        long j8 = ((j7 >>> 8) ^ j7) & 280375465148160L;
        long j9 = j7 ^ (j8 ^ (j8 << 8));
        long j10 = ((j9 >>> 16) ^ j9) & 4294901760L;
        return j9 ^ (j10 ^ (j10 << 16));
    }
}
