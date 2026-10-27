package e2;

/* JADX INFO: compiled from: Label.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4555b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4556c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int[] f4557d;

    public final void a(int i8, int i9, int i10) {
        if (this.f4557d == null) {
            this.f4557d = new int[6];
        }
        int i11 = this.f4556c;
        int[] iArr = this.f4557d;
        if (i11 >= iArr.length) {
            int[] iArr2 = new int[iArr.length + 6];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.f4557d = iArr2;
        }
        int[] iArr3 = this.f4557d;
        int i12 = this.f4556c;
        int i13 = i12 + 1;
        this.f4556c = i13;
        iArr3[i12] = i8;
        this.f4556c = i13 + 1;
        iArr3[i13] = i9 | i10;
    }

    public void b(i iVar, a aVar, int i8, boolean z7) {
        if ((this.f4554a & 2) != 0) {
            if (z7) {
                aVar.f(this.f4555b - i8);
                return;
            } else {
                aVar.g(this.f4555b - i8);
                return;
            }
        }
        if (z7) {
            a(i8, aVar.f4516b, 536870912);
            aVar.f(-1);
        } else {
            a(i8, aVar.f4516b, 268435456);
            aVar.g(-1);
        }
    }

    public void c(i iVar, int i8, byte[] bArr) {
        this.f4554a |= 2;
        this.f4555b = i8;
        int i9 = 0;
        while (i9 < this.f4556c) {
            int[] iArr = this.f4557d;
            int i10 = i9 + 1;
            int i11 = iArr[i9];
            int i12 = i10 + 1;
            int i13 = iArr[i10];
            int i14 = 268435455 & i13;
            int i15 = i8 - i11;
            if ((i13 & (-268435456)) == 268435456) {
                bArr[i14] = (byte) (i15 >>> 8);
                bArr[i14 + 1] = (byte) i15;
            } else {
                int i16 = i14 + 1;
                bArr[i14] = (byte) (i15 >>> 24);
                int i17 = i16 + 1;
                bArr[i16] = (byte) (i15 >>> 16);
                bArr[i17] = (byte) (i15 >>> 8);
                bArr[i17 + 1] = (byte) i15;
            }
            i9 = i12;
        }
    }
}
