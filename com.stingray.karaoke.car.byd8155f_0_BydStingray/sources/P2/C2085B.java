package p2;

import h2.AbstractC1343A;
import java.nio.ByteBuffer;

/* JADX INFO: renamed from: p2.B, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2085B extends f2.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f20688i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f20689j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f20690k;
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public byte[] f20691m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20692n;
    public long o;

    @Override // f2.e, f2.d
    public final ByteBuffer a() {
        int i7;
        if (super.f() && (i7 = this.f20692n) > 0) {
            l(i7).put(this.f20691m, 0, this.f20692n).flip();
            this.f20692n = 0;
        }
        return super.a();
    }

    @Override // f2.e, f2.d
    public final boolean f() {
        return super.f() && this.f20692n == 0;
    }

    @Override // f2.d
    public final void g(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i7 = iLimit - iPosition;
        if (i7 == 0) {
            return;
        }
        int iMin = Math.min(i7, this.l);
        this.o += (long) (iMin / this.f14551b.d);
        this.l -= iMin;
        byteBuffer.position(iPosition + iMin);
        if (this.l > 0) {
            return;
        }
        int i10 = i7 - iMin;
        int length = (this.f20692n + i10) - this.f20691m.length;
        ByteBuffer byteBufferL = l(length);
        int i11 = AbstractC1343A.i(length, 0, this.f20692n);
        byteBufferL.put(this.f20691m, 0, i11);
        int i12 = AbstractC1343A.i(length - i11, 0, i10);
        byteBuffer.limit(byteBuffer.position() + i12);
        byteBufferL.put(byteBuffer);
        byteBuffer.limit(iLimit);
        int i13 = i10 - i12;
        int i14 = this.f20692n - i11;
        this.f20692n = i14;
        byte[] bArr = this.f20691m;
        System.arraycopy(bArr, i11, bArr, 0, i14);
        byteBuffer.get(this.f20691m, this.f20692n, i13);
        this.f20692n += i13;
        byteBufferL.flip();
    }

    @Override // f2.e
    public final f2.b h(f2.b bVar) throws f2.c {
        if (bVar.c != 2) {
            throw new f2.c(bVar);
        }
        this.f20690k = true;
        return (this.f20688i == 0 && this.f20689j == 0) ? f2.b.f14547e : bVar;
    }

    @Override // f2.e
    public final void i() {
        if (this.f20690k) {
            this.f20690k = false;
            int i7 = this.f20689j;
            int i10 = this.f14551b.d;
            this.f20691m = new byte[i7 * i10];
            this.l = this.f20688i * i10;
        }
        this.f20692n = 0;
    }

    @Override // f2.e
    public final void j() {
        if (this.f20690k) {
            int i7 = this.f20692n;
            if (i7 > 0) {
                this.o += (long) (i7 / this.f14551b.d);
            }
            this.f20692n = 0;
        }
    }

    @Override // f2.e
    public final void k() {
        this.f20691m = AbstractC1343A.f15198f;
    }
}
