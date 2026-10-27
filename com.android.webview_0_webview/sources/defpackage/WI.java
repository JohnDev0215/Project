package defpackage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WI {
    public int a;
    public final VI b;

    public WI(LJ lj, int i) {
        VI vi = new VI(lj, i, null);
        this.b = vi;
        this.a = vi.d;
    }

    public void a(double d, int i) {
        this.b.b.putDouble(this.a + i, d);
    }

    public void b(float f, int i) {
        this.b.b.putFloat(this.a + i, f);
    }

    public void c(int i, int i2) {
        this.b.b.putInt(this.a + i2, i);
    }

    public void d(long j, int i) {
        this.b.b.putLong(this.a + i, j);
    }

    public void e(String str, int i, boolean z) {
        if (str == null) {
            q(i, z);
        } else {
            m(str.getBytes(Charset.forName("utf8")), i, z ? 1 : 0, -1);
        }
    }

    public void f(PI pi) {
        VI vi = this.b;
        vi.d += ((pi.a + 8) - 1) & (-8);
        if (vi.b.capacity() < vi.d) {
            int iCapacity = vi.b.capacity();
            do {
                iCapacity *= 2;
            } while (iCapacity < vi.d);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iCapacity);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            vi.b.position(0);
            ByteBuffer byteBuffer = vi.b;
            byteBuffer.limit(byteBuffer.capacity());
            byteBufferAllocateDirect.put(vi.b);
            vi.b = byteBufferAllocateDirect;
        }
        c(pi.a, 0);
        c(pi.b, 4);
    }

    public void g(InterfaceC0822gJ interfaceC0822gJ, int i, boolean z, AbstractC0599cJ abstractC0599cJ) {
        if (interfaceC0822gJ == null) {
            p(i, z);
            c(0, i + 4);
            return;
        }
        LJ lj = this.b.a;
        if (lj == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
        }
        if (interfaceC0822gJ instanceof InterfaceC0710eJ) {
            InterfaceC0655dJ interfaceC0655dJW = ((InterfaceC0710eJ) interfaceC0822gJ).w();
            k((VJ) ((ZI) ((C0487aJ) interfaceC0655dJW).t).e(), i, z);
            c(((C0487aJ) interfaceC0655dJW).v, i + 4);
        } else {
            XJ xjB = ((CoreImpl) lj).b(null);
            abstractC0599cJ.c(interfaceC0822gJ, (VJ) xjB.a);
            k((OJ) xjB.b, i, z);
            c(abstractC0599cJ.g(), i + 4);
        }
    }

    public void h(C0934iJ c0934iJ, int i, boolean z) {
        if (c0934iJ == null) {
            p(i, z);
        } else {
            if (this.b.a == null) {
                throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
            }
            k(c0934iJ.e(), i, z);
        }
    }

    public void i(AbstractC1651vJ abstractC1651vJ, int i, boolean z) {
        if (abstractC1651vJ == null) {
            q(i, z);
        } else {
            d(((long) this.b.d) - ((long) (this.a + i)), i);
            abstractC1651vJ.a(this);
        }
    }

    public void j(AbstractC1706wJ abstractC1706wJ, int i, boolean z) {
        if (abstractC1706wJ == null && !z) {
            throw new C1486sJ("Trying to encode a null pointer for a non-nullable type.");
        }
        if (abstractC1706wJ != null) {
            abstractC1706wJ.a(this, i);
        } else {
            d(0L, i);
            d(0L, i + 8);
        }
    }

    public void k(OJ oj, int i, boolean z) {
        if (oj == null || !oj.a()) {
            p(i, z);
        } else {
            c(this.b.c.size(), i);
            this.b.c.add(oj);
        }
    }

    public void l(boolean z, int i, int i2) {
        if (z) {
            this.b.b.put(this.a + i, (byte) (this.b.b.get(this.a + i) | ((byte) (1 << i2))));
        }
    }

    public void m(byte[] bArr, int i, int i2, int i3) {
        if (bArr == null) {
            q(i, JI.b(i2));
            return;
        }
        if (i3 != -1 && i3 != bArr.length) {
            throw new C1486sJ("Trying to encode a fixed array of incorrect length.");
        }
        WI wiT = t(bArr.length, bArr.length, i);
        wiT.b.b.position(wiT.a + 8);
        wiT.b.b.put(bArr);
    }

    public void n(int[] iArr, int i, int i2, int i3) {
        if (iArr == null) {
            q(i, JI.b(i2));
            return;
        }
        WI wiS = s(4, iArr.length, i, i3);
        wiS.b.b.position(wiS.a + 8);
        wiS.b.b.asIntBuffer().put(iArr);
    }

    public void o(OJ[] ojArr, int i, int i2, int i3) {
        if (ojArr == null) {
            q(i, JI.b(i2));
            return;
        }
        WI wiS = s(4, ojArr.length, i, i3);
        for (int i4 = 0; i4 < ojArr.length; i4++) {
            wiS.k(ojArr[i4], (i4 * 4) + 8, JI.c(i2));
        }
    }

    public void p(int i, boolean z) {
        if (!z) {
            throw new C1486sJ("Trying to encode an invalid handle for a non-nullable type.");
        }
        this.b.b.putInt(this.a + i, -1);
    }

    public void q(int i, boolean z) {
        if (!z) {
            throw new C1486sJ("Trying to encode a null pointer for a non-nullable type.");
        }
        this.b.b.putLong(this.a + i, 0L);
    }

    public WI r(int i, int i2, int i3) {
        return s(8, i, i2, i3);
    }

    public final WI s(int i, int i2, int i3, int i4) {
        if (i4 == -1 || i4 == i2) {
            return t(i * i2, i2, i3);
        }
        throw new C1486sJ("Trying to encode a fixed array of incorrect length.");
    }

    public final WI t(int i, int i2, int i3) {
        d(((long) this.b.d) - ((long) (this.a + i3)), i3);
        int i4 = i + 8;
        WI wi = new WI(this.b);
        VI vi = wi.b;
        vi.d += ((i4 + 8) - 1) & (-8);
        if (vi.b.capacity() < vi.d) {
            int iCapacity = vi.b.capacity();
            do {
                iCapacity *= 2;
            } while (iCapacity < vi.d);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iCapacity);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            vi.b.position(0);
            ByteBuffer byteBuffer = vi.b;
            byteBuffer.limit(byteBuffer.capacity());
            byteBufferAllocateDirect.put(vi.b);
            vi.b = byteBufferAllocateDirect;
        }
        wi.c(i4, 0);
        wi.b.b.putInt(wi.a + 4, i2);
        return wi;
    }

    public WI u(PI pi) {
        WI wi = new WI(this.b);
        wi.f(pi);
        return wi;
    }

    public C0989jJ v() {
        this.b.b.position(0);
        VI vi = this.b;
        vi.b.limit(vi.d);
        VI vi2 = this.b;
        return new C0989jJ(vi2.b, vi2.c);
    }

    public WI(VI vi) {
        this.b = vi;
        this.a = vi.d;
    }
}
