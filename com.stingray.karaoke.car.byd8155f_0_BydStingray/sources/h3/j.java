package h3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f15354e = {0, 0, 1};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f15355a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15356b;
    public int c;
    public byte[] d;

    public final void a(int i7, int i10, byte[] bArr) {
        if (this.f15355a) {
            int i11 = i10 - i7;
            byte[] bArr2 = this.d;
            int length = bArr2.length;
            int i12 = this.f15356b + i11;
            if (length < i12) {
                this.d = Arrays.copyOf(bArr2, i12 * 2);
            }
            System.arraycopy(bArr, i7, this.d, this.f15356b, i11);
            this.f15356b += i11;
        }
    }
}
