package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* JADX INFO: compiled from: CriticalPersistedTabDataFlatBuffer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y10 {
    public int a;
    public ByteBuffer b;
    public int c;
    public int d;
    public final rk3 e;

    public y10() {
        if (rk3.a == null) {
            rk3.a = new tk3();
        }
        this.e = rk3.a;
    }

    public static int b(nn0 nn0Var) {
        int i;
        if (nn0Var.d == null || !nn0Var.f) {
            throw new AssertionError("FlatBuffers: endTable called without startTable");
        }
        nn0Var.e(4, 0);
        ByteBuffer byteBuffer = nn0Var.a;
        int i2 = nn0Var.b - 4;
        nn0Var.b = i2;
        byteBuffer.putInt(i2, 0);
        int iD = nn0Var.d();
        int i3 = nn0Var.e;
        do {
            i3--;
            if (i3 < 0) {
                break;
            }
        } while (nn0Var.d[i3] == 0);
        int i4 = i3 + 1;
        while (i3 >= 0) {
            int i5 = nn0Var.d[i3];
            nn0Var.b((short) (i5 != 0 ? iD - i5 : 0));
            i3--;
        }
        nn0Var.b((short) (iD - nn0Var.g));
        nn0Var.b((short) ((i4 + 2) * 2));
        int i6 = 0;
        loop2: while (true) {
            if (i6 >= nn0Var.i) {
                i = 0;
                break;
            }
            int iCapacity = nn0Var.a.capacity() - nn0Var.h[i6];
            int i7 = nn0Var.b;
            short s = nn0Var.a.getShort(iCapacity);
            if (s == nn0Var.a.getShort(i7)) {
                for (int i8 = 2; i8 < s; i8 += 2) {
                    if (nn0Var.a.getShort(iCapacity + i8) != nn0Var.a.getShort(i7 + i8)) {
                        break;
                    }
                }
                i = nn0Var.h[i6];
                break loop2;
            }
            i6++;
        }
        if (i != 0) {
            int iCapacity2 = nn0Var.a.capacity() - iD;
            nn0Var.b = iCapacity2;
            nn0Var.a.putInt(iCapacity2, i - iD);
        } else {
            int i9 = nn0Var.i;
            int[] iArr = nn0Var.h;
            if (i9 == iArr.length) {
                nn0Var.h = Arrays.copyOf(iArr, i9 * 2);
            }
            int[] iArr2 = nn0Var.h;
            int i10 = nn0Var.i;
            nn0Var.i = i10 + 1;
            iArr2[i10] = nn0Var.d();
            ByteBuffer byteBuffer2 = nn0Var.a;
            byteBuffer2.putInt(byteBuffer2.capacity() - iD, nn0Var.d() - iD);
        }
        nn0Var.f = false;
        return iD;
    }

    public static y10 c(ByteBuffer byteBuffer) {
        y10 y10Var = new y10();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        int iPosition = byteBuffer.position() + byteBuffer.getInt(byteBuffer.position());
        y10Var.b = byteBuffer;
        y10Var.a = iPosition;
        int i = iPosition - byteBuffer.getInt(iPosition);
        y10Var.c = i;
        y10Var.d = y10Var.b.getShort(i);
        return y10Var;
    }

    public final int a(int i) {
        if (i < this.d) {
            return this.b.getShort(this.c + i);
        }
        return 0;
    }

    public final String d() {
        int iA = a(14);
        if (iA == 0) {
            return null;
        }
        int i = iA + this.a;
        ByteBuffer byteBuffer = this.b;
        int i2 = byteBuffer.getInt(i) + i;
        int i3 = byteBuffer.getInt(i2);
        return this.e.a(byteBuffer, i2 + 4, i3);
    }

    public final ByteBuffer e() {
        int iA = a(10);
        if (iA == 0) {
            return null;
        }
        ByteBuffer byteBufferOrder = this.b.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int i = this.a + iA;
        int i2 = this.b.getInt(i) + i + 4;
        int i3 = iA + this.a;
        return byteBufferOrder;
    }
}
