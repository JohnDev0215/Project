package p2;

import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class z extends f2.e {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20858n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20859p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f20860q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public byte[] f20862s;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public byte[] f20865v;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f20861r = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f20863t = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f20864u = 0;
    public final long l = 100000;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f20854i = 0.2f;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f20857m = 2000000;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f20856k = 10;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final short f20855j = 1024;

    public z() {
        byte[] bArr = AbstractC1343A.f15198f;
        this.f20862s = bArr;
        this.f20865v = bArr;
    }

    @Override // f2.e, f2.d
    public final boolean b() {
        return super.b() && this.o;
    }

    @Override // f2.d
    public final void g(ByteBuffer byteBuffer) {
        int iLimit;
        int iPosition;
        while (byteBuffer.hasRemaining() && !this.f14554g.hasRemaining()) {
            int i7 = this.f20859p;
            short s6 = this.f20855j;
            if (i7 == 0) {
                int iLimit2 = byteBuffer.limit();
                byteBuffer.limit(Math.min(iLimit2, byteBuffer.position() + this.f20862s.length));
                int iLimit3 = byteBuffer.limit() - 1;
                while (true) {
                    if (iLimit3 < byteBuffer.position()) {
                        iPosition = byteBuffer.position();
                        break;
                    }
                    if (Math.abs((byteBuffer.get(iLimit3) << 8) | (byteBuffer.get(iLimit3 - 1) & 255)) > s6) {
                        int i10 = this.f20858n;
                        iPosition = ((iLimit3 / i10) * i10) + i10;
                        break;
                    }
                    iLimit3 -= 2;
                }
                if (iPosition == byteBuffer.position()) {
                    this.f20859p = 1;
                } else {
                    byteBuffer.limit(Math.min(iPosition, byteBuffer.capacity()));
                    l(byteBuffer.remaining()).put(byteBuffer).flip();
                }
                byteBuffer.limit(iLimit2);
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException();
                }
                AbstractC1346c.j(this.f20863t < this.f20862s.length);
                int iLimit4 = byteBuffer.limit();
                int iPosition2 = byteBuffer.position() + 1;
                while (true) {
                    if (iPosition2 >= byteBuffer.limit()) {
                        iLimit = byteBuffer.limit();
                        break;
                    }
                    if (Math.abs((byteBuffer.get(iPosition2) << 8) | (byteBuffer.get(iPosition2 - 1) & 255)) > s6) {
                        int i11 = this.f20858n;
                        iLimit = (iPosition2 / i11) * i11;
                        break;
                    }
                    iPosition2 += 2;
                }
                int iPosition3 = iLimit - byteBuffer.position();
                int length = this.f20863t;
                int i12 = this.f20864u;
                int length2 = length + i12;
                byte[] bArr = this.f20862s;
                if (length2 < bArr.length) {
                    length = bArr.length;
                } else {
                    length2 = i12 - (bArr.length - length);
                }
                int i13 = length - length2;
                boolean z6 = iLimit < iLimit4;
                int iMin = Math.min(iPosition3, i13);
                byteBuffer.limit(byteBuffer.position() + iMin);
                byteBuffer.get(this.f20862s, length2, iMin);
                int i14 = this.f20864u + iMin;
                this.f20864u = i14;
                AbstractC1346c.j(i14 <= this.f20862s.length);
                boolean z10 = z6 && iPosition3 < i13;
                n(z10);
                if (z10) {
                    this.f20859p = 0;
                    this.f20861r = 0;
                }
                byteBuffer.limit(iLimit4);
            }
        }
    }

    @Override // f2.e
    public final f2.b h(f2.b bVar) throws f2.c {
        if (bVar.c == 2) {
            return bVar.f14548a == -1 ? f2.b.f14547e : bVar;
        }
        throw new f2.c(bVar);
    }

    @Override // f2.e
    public final void i() {
        if (b()) {
            f2.b bVar = this.f14551b;
            int i7 = bVar.f14549b * 2;
            this.f20858n = i7;
            int i10 = ((((int) ((this.l * ((long) bVar.f14548a)) / 1000000)) / 2) / i7) * i7 * 2;
            if (this.f20862s.length != i10) {
                this.f20862s = new byte[i10];
                this.f20865v = new byte[i10];
            }
        }
        this.f20859p = 0;
        this.f20860q = 0L;
        this.f20861r = 0;
        this.f20863t = 0;
        this.f20864u = 0;
    }

    @Override // f2.e
    public final void j() {
        if (this.f20864u > 0) {
            n(true);
            this.f20861r = 0;
        }
    }

    @Override // f2.e
    public final void k() {
        this.o = false;
        byte[] bArr = AbstractC1343A.f15198f;
        this.f20862s = bArr;
        this.f20865v = bArr;
    }

    public final int m(int i7) {
        int length = ((((int) ((this.f20857m * ((long) this.f14551b.f14548a)) / 1000000)) - this.f20861r) * this.f20858n) - (this.f20862s.length / 2);
        AbstractC1346c.j(length >= 0);
        int iMin = (int) Math.min((i7 * this.f20854i) + 0.5f, length);
        int i10 = this.f20858n;
        return (iMin / i10) * i10;
    }

    public final void n(boolean z6) {
        int length;
        int iM;
        int i7 = this.f20864u;
        byte[] bArr = this.f20862s;
        if (i7 == bArr.length || z6) {
            if (this.f20861r == 0) {
                if (z6) {
                    o(i7, 3);
                    length = i7;
                } else {
                    AbstractC1346c.j(i7 >= bArr.length / 2);
                    length = this.f20862s.length / 2;
                    o(length, 0);
                }
                iM = length;
            } else if (z6) {
                int length2 = i7 - (bArr.length / 2);
                int length3 = (bArr.length / 2) + length2;
                int iM2 = m(length2) + (this.f20862s.length / 2);
                o(iM2, 2);
                iM = iM2;
                length = length3;
            } else {
                length = i7 - (bArr.length / 2);
                iM = m(length);
                o(iM, 1);
            }
            AbstractC1346c.i("bytesConsumed is not aligned to frame size: %s" + length, length % this.f20858n == 0);
            AbstractC1346c.j(i7 >= iM);
            this.f20864u -= length;
            int i10 = this.f20863t + length;
            this.f20863t = i10;
            this.f20863t = i10 % this.f20862s.length;
            int i11 = this.f20861r;
            int i12 = this.f20858n;
            this.f20861r = (iM / i12) + i11;
            this.f20860q += (long) ((length - iM) / i12);
        }
    }

    public final void o(int i7, int i10) {
        if (i7 == 0) {
            return;
        }
        AbstractC1346c.c(this.f20864u >= i7);
        if (i10 == 2) {
            int i11 = this.f20863t;
            int i12 = this.f20864u;
            int i13 = i11 + i12;
            byte[] bArr = this.f20862s;
            if (i13 <= bArr.length) {
                System.arraycopy(bArr, i13 - i7, this.f20865v, 0, i7);
            } else {
                int length = i12 - (bArr.length - i11);
                if (length >= i7) {
                    System.arraycopy(bArr, length - i7, this.f20865v, 0, i7);
                } else {
                    int i14 = i7 - length;
                    System.arraycopy(bArr, bArr.length - i14, this.f20865v, 0, i14);
                    System.arraycopy(this.f20862s, 0, this.f20865v, i14, length);
                }
            }
        } else {
            int i15 = this.f20863t;
            int i16 = i15 + i7;
            byte[] bArr2 = this.f20862s;
            if (i16 <= bArr2.length) {
                System.arraycopy(bArr2, i15, this.f20865v, 0, i7);
            } else {
                int length2 = bArr2.length - i15;
                System.arraycopy(bArr2, i15, this.f20865v, 0, length2);
                System.arraycopy(this.f20862s, 0, this.f20865v, length2, i7 - length2);
            }
        }
        AbstractC1346c.b("sizeToOutput is not aligned to frame size: " + i7, i7 % this.f20858n == 0);
        AbstractC1346c.j(this.f20863t < this.f20862s.length);
        byte[] bArr3 = this.f20865v;
        AbstractC1346c.b("byteOutput size is not aligned to frame size " + i7, i7 % this.f20858n == 0);
        if (i10 != 3) {
            for (int i17 = 0; i17 < i7; i17 += 2) {
                int i18 = i17 + 1;
                int i19 = (bArr3[i18] << 8) | (bArr3[i17] & 255);
                int i20 = this.f20856k;
                if (i10 == 0) {
                    i20 = ((((i17 * 1000) / (i7 - 1)) * (i20 - 100)) / 1000) + 100;
                } else if (i10 == 2) {
                    i20 += (((i17 * 1000) * (100 - i20)) / (i7 - 1)) / 1000;
                }
                int i21 = (i19 * i20) / 100;
                if (i21 >= 32767) {
                    bArr3[i17] = -1;
                    bArr3[i18] = 127;
                } else if (i21 <= -32768) {
                    bArr3[i17] = 0;
                    bArr3[i18] = -128;
                } else {
                    bArr3[i17] = (byte) (i21 & 255);
                    bArr3[i18] = (byte) (i21 >> 8);
                }
            }
        }
        l(i7).put(bArr3, 0, i7).flip();
    }
}
