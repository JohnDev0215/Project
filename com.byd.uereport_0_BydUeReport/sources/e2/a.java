package e2;

/* JADX INFO: compiled from: ByteVector.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f4515a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4516b;

    public a() {
        this.f4515a = new byte[64];
    }

    public final void a(int i8) {
        byte[] bArr = this.f4515a;
        int length = bArr.length * 2;
        int i9 = this.f4516b;
        int i10 = i8 + i9;
        if (length <= i10) {
            length = i10;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, i9);
        this.f4515a = bArr2;
    }

    public a b(int i8, int i9) {
        int i10 = this.f4516b;
        if (i10 + 2 > this.f4515a.length) {
            a(2);
        }
        byte[] bArr = this.f4515a;
        int i11 = i10 + 1;
        bArr[i10] = (byte) i8;
        bArr[i11] = (byte) i9;
        this.f4516b = i11 + 1;
        return this;
    }

    public a c(int i8, int i9) {
        int i10 = this.f4516b;
        if (i10 + 3 > this.f4515a.length) {
            a(3);
        }
        byte[] bArr = this.f4515a;
        int i11 = i10 + 1;
        bArr[i10] = (byte) i8;
        int i12 = i11 + 1;
        bArr[i11] = (byte) (i9 >>> 8);
        bArr[i12] = (byte) i9;
        this.f4516b = i12 + 1;
        return this;
    }

    public a d(int i8) {
        int i9 = this.f4516b;
        int i10 = i9 + 1;
        if (i10 > this.f4515a.length) {
            a(1);
        }
        this.f4515a[i9] = (byte) i8;
        this.f4516b = i10;
        return this;
    }

    public a e(byte[] bArr, int i8, int i9) {
        if (this.f4516b + i9 > this.f4515a.length) {
            a(i9);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i8, this.f4515a, this.f4516b, i9);
        }
        this.f4516b += i9;
        return this;
    }

    public a f(int i8) {
        int i9 = this.f4516b;
        if (i9 + 4 > this.f4515a.length) {
            a(4);
        }
        byte[] bArr = this.f4515a;
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >>> 24);
        int i11 = i10 + 1;
        bArr[i10] = (byte) (i8 >>> 16);
        int i12 = i11 + 1;
        bArr[i11] = (byte) (i8 >>> 8);
        bArr[i12] = (byte) i8;
        this.f4516b = i12 + 1;
        return this;
    }

    public a g(int i8) {
        int i9 = this.f4516b;
        if (i9 + 2 > this.f4515a.length) {
            a(2);
        }
        byte[] bArr = this.f4515a;
        int i10 = i9 + 1;
        bArr[i9] = (byte) (i8 >>> 8);
        bArr[i10] = (byte) i8;
        this.f4516b = i10 + 1;
        return this;
    }

    public a h(String str) {
        int length = str.length();
        int i8 = this.f4516b;
        if (i8 + 2 + length > this.f4515a.length) {
            a(length + 2);
        }
        byte[] bArr = this.f4515a;
        int i9 = i8 + 1;
        bArr[i8] = (byte) (length >>> 8);
        int i10 = i9 + 1;
        bArr[i9] = (byte) length;
        int i11 = 0;
        while (i11 < length) {
            char cCharAt = str.charAt(i11);
            if ((cCharAt < 1 || cCharAt > 127) && (cCharAt < 19968 || cCharAt > 40959)) {
                throw new UnsupportedOperationException();
            }
            bArr[i10] = (byte) cCharAt;
            i11++;
            i10++;
        }
        this.f4516b = i10;
        return this;
    }

    public a(int i8) {
        this.f4515a = new byte[i8];
    }
}
