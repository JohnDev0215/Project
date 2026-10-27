package defpackage;

import defpackage.y11;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: Encoder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class qh0 {
    public final int a;
    public final a b;

    /* JADX INFO: compiled from: Encoder.java */
    public static class a {
        public final k00 a;
        public ByteBuffer b;
        public final ArrayList c = new ArrayList();
        public int d;

        public a(k00 k00Var, int i) {
            this.a = k00Var;
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i <= 0 ? 1024 : i);
            this.b = byteBufferAllocateDirect;
            byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
            this.d = 0;
        }
    }

    public qh0(k00 k00Var, int i) {
        this(new a(k00Var, i));
    }

    public final void a(float f, int i) {
        this.b.b.putFloat(this.a + i, f);
    }

    public final void b(int i, int i2) {
        this.b.b.putInt(this.a + i2, i);
    }

    public final void c(int i, int i2, int i3, byte[] bArr) {
        if (bArr == null) {
            q(i, n3.F0(i2));
            return;
        }
        if (i3 != -1 && i3 != bArr.length) {
            throw new uv("Trying to encode a fixed array of incorrect length.");
        }
        int length = bArr.length;
        int length2 = bArr.length;
        f(i, ((long) this.b.d) - ((long) (this.a + i)));
        qh0 qh0VarT = t(new t50(length2 + 8, length));
        a aVar = qh0VarT.b;
        aVar.b.position(qh0VarT.a + 8);
        aVar.b.put(bArr);
    }

    public final void d(int i, int i2, boolean z) {
        if (z) {
            a aVar = this.b;
            ByteBuffer byteBuffer = aVar.b;
            int i3 = this.a;
            aVar.b.put(i3 + i, (byte) (((byte) (1 << i2)) | byteBuffer.get(i3 + i)));
        }
    }

    public final void e(int i, int i2, float[] fArr) {
        if (fArr == null) {
            q(i, n3.F0(1));
            return;
        }
        qh0 qh0VarS = s(4, fArr.length, i, i2);
        a aVar = qh0VarS.b;
        aVar.b.position(qh0VarS.a + 8);
        aVar.b.asFloatBuffer().put(fArr);
    }

    public final void f(int i, long j) {
        this.b.b.putLong(this.a + i, j);
    }

    public final void g(t50 t50Var) {
        a aVar = this.b;
        aVar.d += ((t50Var.a + 8) - 1) & (-8);
        if (aVar.b.capacity() < aVar.d) {
            int iCapacity = aVar.b.capacity();
            do {
                iCapacity *= 2;
            } while (iCapacity < aVar.d);
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(iCapacity);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            aVar.b.position(0);
            ByteBuffer byteBuffer = aVar.b;
            byteBuffer.limit(byteBuffer.capacity());
            byteBufferAllocateDirect.put(aVar.b);
            aVar.b = byteBufferAllocateDirect;
        }
        b(t50Var.a, 0);
        b(t50Var.b, 4);
    }

    public final void h(tt0 tt0Var, int i, boolean z) {
        if (tt0Var == null || !tt0Var.isValid()) {
            p(i, z);
            return;
        }
        a aVar = this.b;
        b(aVar.c.size(), i);
        aVar.c.add(tt0Var);
    }

    public final <T extends y11> void i(T t, int i, boolean z, y11.b<T, ?> bVar) {
        if (t == null) {
            p(i, z);
            b(0, i + 4);
            return;
        }
        k00 k00Var = this.b.a;
        if (k00Var == null) {
            throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
        }
        if (t instanceof y11.c) {
            y11.a.C0171a c0171aU0 = ((y11.c) t).u0();
            h(((vt0) c0171aU0.f).f0(), i, z);
            b(c0171aU0.g, i + 4);
        } else {
            pv1<ch1, ch1> pv1VarB = ((CoreImpl) k00Var).b(null);
            bVar.b(t, pv1VarB.a);
            h(pv1VarB.b, i, z);
            bVar.g();
            b(0, i + 4);
        }
    }

    public final void j(f21 f21Var, int i) {
        if (f21Var == null) {
            p(i, false);
        } else {
            if (this.b.a == null) {
                throw new UnsupportedOperationException("The encoder has been created without a Core. It can't encode an interface.");
            }
            h(f21Var.f0(), i, false);
        }
    }

    public final void k(hv2 hv2Var, int i, boolean z) {
        if (hv2Var == null) {
            q(i, z);
        } else {
            f(i, ((long) this.b.d) - ((long) (this.a + i)));
            hv2Var.a(this);
        }
    }

    public final void l(ui3 ui3Var, int i, boolean z) {
        if (ui3Var == null && !z) {
            throw new uv("Trying to encode a null pointer for a non-nullable type.");
        }
        if (ui3Var != null) {
            ui3Var.a(this, i);
        } else {
            f(i, 0L);
            f(i + 8, 0L);
        }
    }

    public final void m(String str, int i, boolean z) {
        if (str == null) {
            q(i, z);
        } else {
            c(i, z ? 1 : 0, -1, str.getBytes(Charset.forName("utf8")));
        }
    }

    public final void n(short s, int i) {
        this.b.b.putShort(this.a + i, s);
    }

    public final void o(int[] iArr, int i, int i2) {
        if (iArr == null) {
            q(i, n3.F0(i2));
            return;
        }
        qh0 qh0VarS = s(4, iArr.length, i, -1);
        a aVar = qh0VarS.b;
        aVar.b.position(qh0VarS.a + 8);
        aVar.b.asIntBuffer().put(iArr);
    }

    public final void p(int i, boolean z) {
        if (!z) {
            throw new uv("Trying to encode an invalid handle for a non-nullable type.");
        }
        this.b.b.putInt(this.a + i, -1);
    }

    public final void q(int i, boolean z) {
        if (!z) {
            throw new uv("Trying to encode a null pointer for a non-nullable type.");
        }
        this.b.b.putLong(this.a + i, 0L);
    }

    public final qh0 r(int i, int i2) {
        return s(8, i, i2, -1);
    }

    public final qh0 s(int i, int i2, int i3, int i4) {
        if (i4 != -1 && i4 != i2) {
            throw new uv("Trying to encode a fixed array of incorrect length.");
        }
        f(i3, ((long) this.b.d) - ((long) (this.a + i3)));
        return t(new t50((i * i2) + 8, i2));
    }

    public final qh0 t(t50 t50Var) {
        qh0 qh0Var = new qh0(this.b);
        qh0Var.g(t50Var);
        return qh0Var;
    }

    public qh0(a aVar) {
        this.b = aVar;
        this.a = aVar.d;
    }
}
