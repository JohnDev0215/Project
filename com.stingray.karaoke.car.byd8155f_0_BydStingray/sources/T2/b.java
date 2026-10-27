package T2;

import B2.C0073m;
import N1.C0420a;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f8532a = new byte[8];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque f8533b = new ArrayDeque();
    public final e c = new e();
    public C0420a d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8534e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8535f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f8536g;

    public final long a(C0073m c0073m, int i7) throws EOFException, InterruptedIOException {
        byte[] bArr = this.f8532a;
        c0073m.c(bArr, 0, i7, false);
        long j3 = 0;
        for (int i10 = 0; i10 < i7; i10++) {
            j3 = (j3 << 8) | ((long) (bArr[i10] & 255));
        }
        return j3;
    }
}
