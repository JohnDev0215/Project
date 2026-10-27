package p2;

import java.nio.ByteBuffer;

/* JADX INFO: renamed from: p2.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2084A extends f2.e {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final int f20687i = Float.floatToIntBits(Float.NaN);

    public static void m(int i7, ByteBuffer byteBuffer) {
        int iFloatToIntBits = Float.floatToIntBits((float) (((double) i7) * 4.656612875245797E-10d));
        if (iFloatToIntBits == f20687i) {
            iFloatToIntBits = Float.floatToIntBits(0.0f);
        }
        byteBuffer.putInt(iFloatToIntBits);
    }

    @Override // f2.d
    public final void g(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferL;
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i7 = iLimit - iPosition;
        int i10 = this.f14551b.c;
        if (i10 == 21) {
            byteBufferL = l((i7 / 3) * 4);
            while (iPosition < iLimit) {
                m(((byteBuffer.get(iPosition) & 255) << 8) | ((byteBuffer.get(iPosition + 1) & 255) << 16) | ((byteBuffer.get(iPosition + 2) & 255) << 24), byteBufferL);
                iPosition += 3;
            }
        } else if (i10 == 22) {
            byteBufferL = l(i7);
            while (iPosition < iLimit) {
                m((byteBuffer.get(iPosition) & 255) | ((byteBuffer.get(iPosition + 1) & 255) << 8) | ((byteBuffer.get(iPosition + 2) & 255) << 16) | ((byteBuffer.get(iPosition + 3) & 255) << 24), byteBufferL);
                iPosition += 4;
            }
        } else if (i10 == 1342177280) {
            byteBufferL = l((i7 / 3) * 4);
            while (iPosition < iLimit) {
                m(((byteBuffer.get(iPosition + 2) & 255) << 8) | ((byteBuffer.get(iPosition + 1) & 255) << 16) | ((byteBuffer.get(iPosition) & 255) << 24), byteBufferL);
                iPosition += 3;
            }
        } else {
            if (i10 != 1610612736) {
                throw new IllegalStateException();
            }
            byteBufferL = l(i7);
            while (iPosition < iLimit) {
                m((byteBuffer.get(iPosition + 3) & 255) | ((byteBuffer.get(iPosition + 2) & 255) << 8) | ((byteBuffer.get(iPosition + 1) & 255) << 16) | ((byteBuffer.get(iPosition) & 255) << 24), byteBufferL);
                iPosition += 4;
            }
        }
        byteBuffer.position(byteBuffer.limit());
        byteBufferL.flip();
    }

    @Override // f2.e
    public final f2.b h(f2.b bVar) throws f2.c {
        int i7 = bVar.c;
        if (i7 == 21 || i7 == 1342177280 || i7 == 22 || i7 == 1610612736 || i7 == 4) {
            return i7 != 4 ? new f2.b(bVar.f14548a, bVar.f14549b, 4) : f2.b.f14547e;
        }
        throw new f2.c(bVar);
    }
}
