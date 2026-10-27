package p2;

import java.nio.ByteBuffer;

/* JADX INFO: renamed from: p2.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2100o extends f2.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f20755i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f20756j;

    @Override // f2.d
    public final void g(ByteBuffer byteBuffer) {
        int[] iArr = this.f20756j;
        iArr.getClass();
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        ByteBuffer byteBufferL = l(((iLimit - iPosition) / this.f14551b.d) * this.c.d);
        while (iPosition < iLimit) {
            for (int i7 : iArr) {
                byteBufferL.putShort(byteBuffer.getShort((i7 * 2) + iPosition));
            }
            iPosition += this.f14551b.d;
        }
        byteBuffer.position(iLimit);
        byteBufferL.flip();
    }

    @Override // f2.e
    public final f2.b h(f2.b bVar) throws f2.c {
        int[] iArr = this.f20755i;
        if (iArr == null) {
            return f2.b.f14547e;
        }
        if (bVar.c != 2) {
            throw new f2.c(bVar);
        }
        int length = iArr.length;
        int i7 = bVar.f14549b;
        boolean z6 = i7 != length;
        int i10 = 0;
        while (i10 < iArr.length) {
            int i11 = iArr[i10];
            if (i11 >= i7) {
                throw new f2.c(bVar);
            }
            z6 |= i11 != i10;
            i10++;
        }
        return z6 ? new f2.b(bVar.f14548a, iArr.length, 2) : f2.b.f14547e;
    }

    @Override // f2.e
    public final void i() {
        this.f20756j = this.f20755i;
    }

    @Override // f2.e
    public final void k() {
        this.f20756j = null;
        this.f20755i = null;
    }
}
