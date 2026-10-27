package T2;

import B2.C0073m;
import java.io.EOFException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public static final long[] d = {128, 64, 32, 16, 8, 4, 2, 1};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8641a = new byte[8];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8642b;
    public int c;

    public static long a(int i7, boolean z6, byte[] bArr) {
        long j3 = ((long) bArr[0]) & 255;
        if (z6) {
            j3 &= ~d[i7 - 1];
        }
        for (int i10 = 1; i10 < i7; i10++) {
            j3 = (j3 << 8) | (((long) bArr[i10]) & 255);
        }
        return j3;
    }

    public static int b(int i7) {
        for (int i10 = 0; i10 < 8; i10++) {
            if ((d[i10] & ((long) i7)) != 0) {
                return i10 + 1;
            }
        }
        return -1;
    }

    public final long c(C0073m c0073m, boolean z6, boolean z10, int i7) throws EOFException, InterruptedIOException {
        int i10 = this.f8642b;
        byte[] bArr = this.f8641a;
        if (i10 == 0) {
            if (!c0073m.c(bArr, 0, 1, z6)) {
                return -1L;
            }
            int iB = b(bArr[0] & 255);
            this.c = iB;
            if (iB == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.f8642b = 1;
        }
        int i11 = this.c;
        if (i11 > i7) {
            this.f8642b = 0;
            return -2L;
        }
        if (i11 != 1) {
            c0073m.c(bArr, 1, i11 - 1, false);
        }
        this.f8642b = 0;
        return a(this.c, z10, bArr);
    }
}
