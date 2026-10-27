package h3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f15370f = {0, 0, 1};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f15371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15372b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f15373e;

    public final void a(int i7, int i10, byte[] bArr) {
        if (this.f15371a) {
            int i11 = i10 - i7;
            byte[] bArr2 = this.f15373e;
            int length = bArr2.length;
            int i12 = this.c + i11;
            if (length < i12) {
                this.f15373e = Arrays.copyOf(bArr2, i12 * 2);
            }
            System.arraycopy(bArr, i7, this.f15373e, this.c, i11);
            this.c += i11;
        }
    }
}
