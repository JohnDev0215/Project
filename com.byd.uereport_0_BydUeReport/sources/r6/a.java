package r6;

import java.util.Arrays;

/* JADX INFO: compiled from: BitArray.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements Cloneable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f7844d = new int[0];

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f7845e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f7846f;

    public a() {
        this.f7846f = 0;
        this.f7845e = f7844d;
    }

    public static int[] i(int i8) {
        return new int[(i8 + 31) / 32];
    }

    public void a(boolean z7) {
        e(this.f7846f + 1);
        if (z7) {
            int[] iArr = this.f7845e;
            int i8 = this.f7846f;
            int i9 = i8 / 32;
            iArr[i9] = (1 << (i8 & 31)) | iArr[i9];
        }
        this.f7846f++;
    }

    public void b(a aVar) {
        int i8 = aVar.f7846f;
        e(this.f7846f + i8);
        for (int i9 = 0; i9 < i8; i9++) {
            a(aVar.f(i9));
        }
    }

    public void c(int i8, int i9) {
        if (i9 < 0 || i9 > 32) {
            throw new IllegalArgumentException("Num bits must be between 0 and 32");
        }
        int i10 = this.f7846f;
        e(i10 + i9);
        for (int i11 = i9 - 1; i11 >= 0; i11--) {
            if (((1 << i11) & i8) != 0) {
                int[] iArr = this.f7845e;
                int i12 = i10 / 32;
                iArr[i12] = iArr[i12] | (1 << (i10 & 31));
            }
            i10++;
        }
        this.f7846f = i10;
    }

    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public a clone() {
        return new a((int[]) this.f7845e.clone(), this.f7846f);
    }

    public final void e(int i8) {
        if (i8 > this.f7845e.length * 32) {
            int[] iArrI = i((int) Math.ceil(i8 / 0.75f));
            int[] iArr = this.f7845e;
            System.arraycopy(iArr, 0, iArrI, 0, iArr.length);
            this.f7845e = iArrI;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f7846f == aVar.f7846f && Arrays.equals(this.f7845e, aVar.f7845e);
    }

    public boolean f(int i8) {
        return ((1 << (i8 & 31)) & this.f7845e[i8 / 32]) != 0;
    }

    public int g() {
        return this.f7846f;
    }

    public int h() {
        return (this.f7846f + 7) / 8;
    }

    public int hashCode() {
        return (this.f7846f * 31) + Arrays.hashCode(this.f7845e);
    }

    public void j(int i8, byte[] bArr, int i9, int i10) {
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = 0;
            for (int i13 = 0; i13 < 8; i13++) {
                if (f(i8)) {
                    i12 |= 1 << (7 - i13);
                }
                i8++;
            }
            bArr[i9 + i11] = (byte) i12;
        }
    }

    public void k(a aVar) {
        if (this.f7846f != aVar.f7846f) {
            throw new IllegalArgumentException("Sizes don't match");
        }
        int i8 = 0;
        while (true) {
            int[] iArr = this.f7845e;
            if (i8 >= iArr.length) {
                return;
            }
            iArr[i8] = iArr[i8] ^ aVar.f7845e[i8];
            i8++;
        }
    }

    public String toString() {
        int i8 = this.f7846f;
        StringBuilder sb = new StringBuilder(i8 + (i8 / 8) + 1);
        for (int i9 = 0; i9 < this.f7846f; i9++) {
            if ((i9 & 7) == 0) {
                sb.append(' ');
            }
            sb.append(f(i9) ? 'X' : '.');
        }
        return sb.toString();
    }

    public a(int[] iArr, int i8) {
        this.f7845e = iArr;
        this.f7846f = i8;
    }
}
