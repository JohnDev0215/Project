package defpackage;

import defpackage.y11;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.List;

/* JADX INFO: compiled from: Decoder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e70 {
    public final gq0 a;
    public final int b;
    public final a c;

    /* JADX INFO: compiled from: Decoder.java */
    public static final class a {
        public int a;
        public long b;
        public long c = 0;
        public final long d;
        public final long e;

        public a(int i, long j) {
            this.d = j;
            this.e = i;
        }

        public final void a(long j, long j2) {
            if (j % 8 != 0) {
                throw new q80("Incorrect starting alignment: " + j + ".");
            }
            if (j < this.b) {
                throw new q80("Trying to access memory out of order.");
            }
            if (j2 < j) {
                throw new q80("Incorrect memory range.");
            }
            if (j2 > this.d) {
                throw new q80("Trying to access out of range memory.");
            }
            this.b = (-8) & ((j2 + 8) - 1);
        }
    }

    public e70(gq0 gq0Var) {
        this(gq0Var, new a(((List) gq0Var.b).size(), ((ByteBuffer) gq0Var.a).limit()), 0);
    }

    public final void a() {
        this.c.c--;
    }

    public final void b() {
        a aVar = this.c;
        long j = aVar.c + 1;
        aVar.c = j;
        if (j >= 100) {
            throw new q80("Recursion depth limit exceeded.");
        }
    }

    public final t50 c(t50[] t50VarArr) {
        t50 t50Var;
        t50 t50VarG = g();
        int length = t50VarArr.length - 1;
        t50 t50Var2 = t50VarArr[length];
        int i = t50Var2.b;
        int i2 = t50VarG.a;
        int i3 = t50VarG.b;
        if (i3 <= i) {
            while (true) {
                if (length < 0) {
                    t50Var = null;
                    break;
                }
                t50Var = t50VarArr[length];
                if (i3 >= t50Var.b) {
                    break;
                }
                length--;
            }
            if (t50Var == null || t50Var.a != i2) {
                throw new q80("Header doesn't correspond to any known version.");
            }
        } else if (i2 < t50Var2.a) {
            throw new q80("Message newer than the last known version cannot be shorter than required by the last known version.");
        }
        return t50VarG;
    }

    public final boolean d(int i, int i2) {
        y(i, 1);
        return (e(i) & (1 << i2)) != 0;
    }

    public final byte e(int i) {
        y(i, 1);
        return ((ByteBuffer) this.a.a).get(this.b + i);
    }

    public final byte[] f(int i, int i2, int i3) {
        e70 e70VarT = t(i, n3.F0(i2));
        if (e70VarT == null) {
            return null;
        }
        byte[] bArr = new byte[e70VarT.i(i3, 1L).b];
        gq0 gq0Var = e70VarT.a;
        ((ByteBuffer) gq0Var.a).position(e70VarT.b + 8);
        ((ByteBuffer) gq0Var.a).get(bArr);
        return bArr;
    }

    public final t50 g() {
        int i = this.b;
        a aVar = this.c;
        aVar.a(i, i + 8);
        t50 t50VarH = h(0, false);
        aVar.a(i + 8, i + t50VarH.a);
        return t50VarH;
    }

    public final t50 h(int i, boolean z) {
        int iP = p(i + 0);
        int iP2 = p(i + 4);
        if (iP < 0) {
            throw new q80("Negative size. Unsigned integers are not valid for java.");
        }
        if (iP2 >= 0 || (z && iP2 == -1)) {
            return new t50(iP, iP2);
        }
        throw new q80("Negative elements or version. Unsigned integers are not valid for java.");
    }

    public final t50 i(int i, long j) {
        t50 t50VarG = g();
        long j2 = t50VarG.a;
        int i2 = t50VarG.b;
        if (j2 < (j * ((long) i2)) + 8) {
            throw new q80("Array header is incorrect.");
        }
        if (i == -1 || i2 == i) {
            return t50VarG;
        }
        throw new q80("Incorrect array length. Expected: " + i + ", but got: " + i2 + ".");
    }

    public final void j() {
        t50 t50VarG = g();
        if (t50VarG.a != 24) {
            throw new q80("Incorrect header for map. The size is incorrect.");
        }
        if (t50VarG.b != 0) {
            throw new q80("Incorrect header for map. The version is incorrect.");
        }
    }

    public final t50 k(int i) {
        return i(i, 8L);
    }

    public final t50 l(int i) {
        t50 t50VarH = h(i, true);
        int i2 = t50VarH.a;
        if (i2 == 0) {
            int i3 = t50VarH.b;
            if (i3 != 0) {
                throw new q80(gq2.e("Unexpected version tag for a null union. Expecting 0, found: ", i3));
            }
        } else if (i2 != 16) {
            throw new q80("Unexpected size of an union. The size must be 0 for a null union, or 16 for a non-null union.");
        }
        return t50VarH;
    }

    public final double m(int i) {
        y(i, 8);
        return ((ByteBuffer) this.a.a).getDouble(this.b + i);
    }

    public final float n(int i) {
        y(i, 4);
        return ((ByteBuffer) this.a.a).getFloat(this.b + i);
    }

    public final float[] o(int i, int i2) {
        e70 e70VarT = t(i, n3.F0(1));
        if (e70VarT == null) {
            return null;
        }
        float[] fArr = new float[e70VarT.i(i2, 4L).b];
        gq0 gq0Var = e70VarT.a;
        ((ByteBuffer) gq0Var.a).position(e70VarT.b + 8);
        ((ByteBuffer) gq0Var.a).asFloatBuffer().get(fArr);
        return fArr;
    }

    public final int p(int i) {
        y(i, 4);
        return ((ByteBuffer) this.a.a).getInt(this.b + i);
    }

    public final f21 q(int i) {
        ch1 ch1VarS = x(i, false).S();
        if (ch1VarS == null) {
            return null;
        }
        return new f21(ch1VarS);
    }

    public final int[] r(int i, int i2) {
        e70 e70VarT = t(i, n3.F0(i2));
        if (e70VarT == null) {
            return null;
        }
        int[] iArr = new int[e70VarT.i(-1, 4L).b];
        gq0 gq0Var = e70VarT.a;
        ((ByteBuffer) gq0Var.a).position(e70VarT.b + 8);
        ((ByteBuffer) gq0Var.a).asIntBuffer().get(iArr);
        return iArr;
    }

    public final long s(int i) {
        y(i, 8);
        return ((ByteBuffer) this.a.a).getLong(this.b + i);
    }

    public final e70 t(int i, boolean z) {
        int i2 = this.b + i;
        long jS = s(i);
        if (jS != 0) {
            return new e70(this.a, this.c, (int) (((long) i2) + jS));
        }
        if (z) {
            return null;
        }
        throw new q80("Trying to decode null pointer for a non-nullable type.");
    }

    public final <P extends y11.c> P u(int i, boolean z, y11.b<?, P> bVar) {
        ch1 ch1VarS = x(i, z).S();
        if (ch1VarS.isValid()) {
            return (P) bVar.a(ch1VarS, p(i + 4));
        }
        return null;
    }

    public final short v(int i) {
        y(i, 2);
        return ((ByteBuffer) this.a.a).getShort(this.b + i);
    }

    public final String w(int i, boolean z) {
        byte[] bArrF = f(i, z ? 1 : 0, -1);
        if (bArrF == null) {
            return null;
        }
        return new String(bArrF, Charset.forName("utf8"));
    }

    public final ej3 x(int i, boolean z) {
        tt0 tt0Var;
        int iP = p(i);
        if (iP != -1) {
            a aVar = this.c;
            if (iP < aVar.a) {
                throw new q80("Trying to access handle out of order.");
            }
            if (iP >= aVar.e) {
                throw new q80("Trying to access non present handle.");
            }
            aVar.a = iP + 1;
            tt0Var = (tt0) ((List) this.a.b).get(iP);
        } else {
            if (!z) {
                throw new q80("Trying to decode an invalid handle for a non-nullable type.");
            }
            tt0Var = l21.e;
        }
        return tt0Var.A0();
    }

    public final void y(int i, int i2) {
        if (((ByteBuffer) this.a.a).limit() < i + i2) {
            throw new q80("Buffer is smaller than expected.");
        }
    }

    public e70(gq0 gq0Var, a aVar, int i) {
        this.a = gq0Var;
        ((ByteBuffer) gq0Var.a).order(ByteOrder.LITTLE_ENDIAN);
        this.b = i;
        this.c = aVar;
    }
}
