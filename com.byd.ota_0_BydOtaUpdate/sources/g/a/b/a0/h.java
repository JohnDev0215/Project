package g.a.b.a0;

/* JADX INFO: loaded from: classes.dex */
public abstract class h implements g.a.b.p, g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f4291a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4292b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f4293c;

    public h() {
        this.f4291a = new byte[4];
        this.f4292b = 0;
    }

    public h(h hVar) {
        this.f4291a = new byte[4];
        d(hVar);
    }

    public void d(h hVar) {
        byte[] bArr = hVar.f4291a;
        System.arraycopy(bArr, 0, this.f4291a, 0, bArr.length);
        this.f4292b = hVar.f4292b;
        this.f4293c = hVar.f4293c;
    }

    public void e() {
        long j = this.f4293c << 3;
        byte b2 = -128;
        while (true) {
            update(b2);
            if (this.f4292b == 0) {
                g(j);
                f();
                return;
            }
            b2 = 0;
        }
    }

    public abstract void f();

    public abstract void g(long j);

    @Override // g.a.b.p
    public int getByteLength() {
        return 64;
    }

    public abstract void h(byte[] bArr, int i2);

    @Override // g.a.b.n
    public void reset() {
        this.f4293c = 0L;
        this.f4292b = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f4291a;
            if (i2 >= bArr.length) {
                return;
            }
            bArr[i2] = 0;
            i2++;
        }
    }

    @Override // g.a.b.n
    public void update(byte b2) {
        byte[] bArr = this.f4291a;
        int i2 = this.f4292b;
        int i3 = i2 + 1;
        this.f4292b = i3;
        bArr[i2] = b2;
        if (i3 == bArr.length) {
            h(bArr, 0);
            this.f4292b = 0;
        }
        this.f4293c++;
    }

    @Override // g.a.b.n
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = 0;
        int iMax = Math.max(0, i3);
        if (this.f4292b != 0) {
            int i5 = 0;
            while (true) {
                if (i5 >= iMax) {
                    i4 = i5;
                    break;
                }
                byte[] bArr2 = this.f4291a;
                int i6 = this.f4292b;
                int i7 = i6 + 1;
                this.f4292b = i7;
                int i8 = i5 + 1;
                bArr2[i6] = bArr[i5 + i2];
                if (i7 == 4) {
                    h(bArr2, 0);
                    this.f4292b = 0;
                    i4 = i8;
                    break;
                }
                i5 = i8;
            }
        }
        int i9 = ((iMax - i4) & (-4)) + i4;
        while (i4 < i9) {
            h(bArr, i2 + i4);
            i4 += 4;
        }
        while (i4 < iMax) {
            byte[] bArr3 = this.f4291a;
            int i10 = this.f4292b;
            this.f4292b = i10 + 1;
            bArr3[i10] = bArr[i4 + i2];
            i4++;
        }
        this.f4293c += (long) iMax;
    }
}
