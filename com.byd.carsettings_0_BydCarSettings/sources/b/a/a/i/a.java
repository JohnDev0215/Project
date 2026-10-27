package b.a.a.i;

/* JADX INFO: compiled from: ByteVector.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte[] f184a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f185b;

    public a() {
        this.f184a = new byte[64];
    }

    public final void a(int i) {
        byte[] bArr = this.f184a;
        int length = bArr.length * 2;
        int i2 = this.f185b;
        int i3 = i + i2;
        if (length <= i3) {
            length = i3;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        this.f184a = bArr2;
    }

    public a b(int i, int i2) {
        int i3 = this.f185b;
        if (i3 + 2 > this.f184a.length) {
            a(2);
        }
        byte[] bArr = this.f184a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        bArr[i4] = (byte) i2;
        this.f185b = i4 + 1;
        return this;
    }

    public a c(int i, int i2) {
        int i3 = this.f185b;
        if (i3 + 3 > this.f184a.length) {
            a(3);
        }
        byte[] bArr = this.f184a;
        int i4 = i3 + 1;
        bArr[i3] = (byte) i;
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i2 >>> 8);
        bArr[i5] = (byte) i2;
        this.f185b = i5 + 1;
        return this;
    }

    public a d(int i) {
        int i2 = this.f185b;
        int i3 = i2 + 1;
        if (i3 > this.f184a.length) {
            a(1);
        }
        this.f184a[i2] = (byte) i;
        this.f185b = i3;
        return this;
    }

    public a e(byte[] bArr, int i, int i2) {
        if (this.f185b + i2 > this.f184a.length) {
            a(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.f184a, this.f185b, i2);
        }
        this.f185b += i2;
        return this;
    }

    public a f(int i) {
        int i2 = this.f185b;
        if (i2 + 4 > this.f184a.length) {
            a(4);
        }
        byte[] bArr = this.f184a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        int i5 = i4 + 1;
        bArr[i4] = (byte) (i >>> 8);
        bArr[i5] = (byte) i;
        this.f185b = i5 + 1;
        return this;
    }

    public a g(int i) {
        int i2 = this.f185b;
        if (i2 + 2 > this.f184a.length) {
            a(2);
        }
        byte[] bArr = this.f184a;
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i3] = (byte) i;
        this.f185b = i3 + 1;
        return this;
    }

    public a h(String str) {
        int length = str.length();
        int i = this.f185b;
        if (i + 2 + length > this.f184a.length) {
            a(length + 2);
        }
        byte[] bArr = this.f184a;
        int i2 = i + 1;
        bArr[i] = (byte) (length >>> 8);
        int i3 = i2 + 1;
        bArr[i2] = (byte) length;
        int i4 = 0;
        while (i4 < length) {
            char cCharAt = str.charAt(i4);
            if ((cCharAt < 1 || cCharAt > 127) && (cCharAt < 19968 || cCharAt > 40959)) {
                throw new UnsupportedOperationException();
            }
            bArr[i3] = (byte) cCharAt;
            i4++;
            i3++;
        }
        this.f185b = i3;
        return this;
    }

    public a(int i) {
        this.f184a = new byte[i];
    }
}
