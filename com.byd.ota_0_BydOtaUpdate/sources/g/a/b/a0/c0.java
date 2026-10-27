package g.a.b.a0;

import g.a.b.d0.z0;
import g.a.b.j0.g1;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class c0 implements g.a.f.e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Hashtable f4236a = new Hashtable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z0 f4237b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4238c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long[] f4239d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long[] f4240e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f4241f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b[] f4242g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public b[] f4243h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final c f4244i;
    public final byte[] j;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f4245a;

        public a(long j) {
            byte[] bArr = new byte[32];
            this.f4245a = bArr;
            bArr[0] = 83;
            bArr[1] = 72;
            bArr[2] = 65;
            bArr[3] = 51;
            bArr[4] = 1;
            bArr[5] = 0;
            z0.m(j, bArr, 8);
        }

        public byte[] a() {
            return this.f4245a;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f4246a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte[] f4247b;

        public b(int i2, byte[] bArr) {
            this.f4246a = i2;
            this.f4247b = bArr;
        }

        public int a() {
            return this.f4246a;
        }

        public byte[] b() {
            return this.f4247b;
        }
    }

    public class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f4248a = new d();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public byte[] f4249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f4250c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long[] f4251d;

        public c(int i2) {
            byte[] bArr = new byte[i2];
            this.f4249b = bArr;
            this.f4251d = new long[bArr.length / 8];
        }

        public void a(long[] jArr) {
            int i2 = this.f4250c;
            while (true) {
                byte[] bArr = this.f4249b;
                if (i2 >= bArr.length) {
                    this.f4248a.h(true);
                    b(jArr);
                    return;
                } else {
                    bArr[i2] = 0;
                    i2++;
                }
            }
        }

        public final void b(long[] jArr) {
            long[] jArr2;
            c0 c0Var = c0.this;
            c0Var.f4237b.h(true, c0Var.f4239d, this.f4248a.c());
            int i2 = 0;
            while (true) {
                jArr2 = this.f4251d;
                if (i2 >= jArr2.length) {
                    break;
                }
                jArr2[i2] = z0.g(this.f4249b, i2 * 8);
                i2++;
            }
            c0.this.f4237b.i(jArr2, jArr);
            for (int i3 = 0; i3 < jArr.length; i3++) {
                jArr[i3] = jArr[i3] ^ this.f4251d[i3];
            }
        }

        public void c(int i2) {
            this.f4248a.f();
            this.f4248a.j(i2);
            this.f4250c = 0;
        }

        public void d(c cVar) {
            this.f4249b = g.a.f.a.g(cVar.f4249b, this.f4249b);
            this.f4250c = cVar.f4250c;
            this.f4251d = g.a.f.a.k(cVar.f4251d, this.f4251d);
            this.f4248a.g(cVar.f4248a);
        }

        public void e(byte[] bArr, int i2, int i3, long[] jArr) {
            int i4 = 0;
            while (i3 > i4) {
                if (this.f4250c == this.f4249b.length) {
                    b(jArr);
                    this.f4248a.i(false);
                    this.f4250c = 0;
                }
                int iMin = Math.min(i3 - i4, this.f4249b.length - this.f4250c);
                System.arraycopy(bArr, i2 + i4, this.f4249b, this.f4250c, iMin);
                i4 += iMin;
                this.f4250c += iMin;
                this.f4248a.a(iMin);
            }
        }
    }

    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long[] f4253a = new long[2];

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f4254b;

        public d() {
            f();
        }

        public void a(int i2) {
            if (!this.f4254b) {
                long[] jArr = this.f4253a;
                long j = jArr[0] + ((long) i2);
                jArr[0] = j;
                if (j > 9223372034707292160L) {
                    this.f4254b = true;
                    return;
                }
                return;
            }
            long[] jArr2 = new long[3];
            long[] jArr3 = this.f4253a;
            jArr2[0] = jArr3[0] & 4294967295L;
            jArr2[1] = (jArr3[0] >>> 32) & 4294967295L;
            jArr2[2] = jArr3[1] & 4294967295L;
            long j2 = i2;
            for (int i3 = 0; i3 < 3; i3++) {
                long j3 = j2 + jArr2[i3];
                jArr2[i3] = j3;
                j2 = j3 >>> 32;
            }
            long[] jArr4 = this.f4253a;
            jArr4[0] = ((jArr2[1] & 4294967295L) << 32) | (jArr2[0] & 4294967295L);
            jArr4[1] = (jArr2[2] & 4294967295L) | (jArr4[1] & (-4294967296L));
        }

        public int b() {
            return (int) ((this.f4253a[1] >>> 56) & 63);
        }

        public long[] c() {
            return this.f4253a;
        }

        public boolean d() {
            return (this.f4253a[1] & Long.MIN_VALUE) != 0;
        }

        public boolean e() {
            return (this.f4253a[1] & 4611686018427387904L) != 0;
        }

        public void f() {
            long[] jArr = this.f4253a;
            jArr[0] = 0;
            jArr[1] = 0;
            this.f4254b = false;
            i(true);
        }

        public void g(d dVar) {
            this.f4253a = g.a.f.a.k(dVar.f4253a, this.f4253a);
            this.f4254b = dVar.f4254b;
        }

        public void h(boolean z) {
            if (z) {
                long[] jArr = this.f4253a;
                jArr[1] = jArr[1] | Long.MIN_VALUE;
            } else {
                long[] jArr2 = this.f4253a;
                jArr2[1] = jArr2[1] & Long.MAX_VALUE;
            }
        }

        public void i(boolean z) {
            if (z) {
                long[] jArr = this.f4253a;
                jArr[1] = jArr[1] | 4611686018427387904L;
            } else {
                long[] jArr2 = this.f4253a;
                jArr2[1] = jArr2[1] & (-4611686018427387905L);
            }
        }

        public void j(int i2) {
            long[] jArr = this.f4253a;
            jArr[1] = (jArr[1] & (-274877906944L)) | ((((long) i2) & 63) << 56);
        }

        public String toString() {
            return b() + " first: " + e() + ", final: " + d();
        }
    }

    static {
        l(256, 128, new long[]{-2228972824489528736L, -8629553674646093540L, 1155188648486244218L, -3677226592081559102L});
        l(256, 160, new long[]{1450197650740764312L, 3081844928540042640L, -3136097061834271170L, 3301952811952417661L});
        l(256, 224, new long[]{-4176654842910610933L, -8688192972455077604L, -7364642305011795836L, 4056579644589979102L});
        l(256, 256, new long[]{-243853671043386295L, 3443677322885453875L, -5531612722399640561L, 7662005193972177513L});
        l(512, 128, new long[]{-6288014694233956526L, 2204638249859346602L, 3502419045458743507L, -4829063503441264548L, 983504137758028059L, 1880512238245786339L, -6715892782214108542L, 7602827311880509485L});
        l(512, 160, new long[]{2934123928682216849L, -4399710721982728305L, 1684584802963255058L, 5744138295201861711L, 2444857010922934358L, -2807833639722848072L, -5121587834665610502L, 118355523173251694L});
        l(512, 224, new long[]{-3688341020067007964L, -3772225436291745297L, -8300862168937575580L, 4146387520469897396L, 1106145742801415120L, 7455425944880474941L, -7351063101234211863L, -7048981346965512457L});
        l(512, 384, new long[]{-6631894876634615969L, -5692838220127733084L, -7099962856338682626L, -2911352911530754598L, 2000907093792408677L, 9140007292425499655L, 6093301768906360022L, 2769176472213098488L});
        l(512, 512, new long[]{5261240102383538638L, 978932832955457283L, -8083517948103779378L, -7339365279355032399L, 6752626034097301424L, -1531723821829733388L, -7417126464950782685L, -5901786942805128141L});
    }

    public c0(int i2, int i3) {
        this.j = new byte[1];
        if (i3 % 8 != 0) {
            throw new IllegalArgumentException("Output size must be a multiple of 8 bits. :" + i3);
        }
        this.f4238c = i3 / 8;
        z0 z0Var = new z0(i2);
        this.f4237b = z0Var;
        this.f4244i = new c(z0Var.b());
    }

    public c0(c0 c0Var) {
        this(c0Var.h() * 8, c0Var.i() * 8);
        e(c0Var);
    }

    public static b[] d(b[] bVarArr, b[] bVarArr2) {
        if (bVarArr == null) {
            return null;
        }
        if (bVarArr2 == null || bVarArr2.length != bVarArr.length) {
            bVarArr2 = new b[bVarArr.length];
        }
        System.arraycopy(bVarArr, 0, bVarArr2, 0, bVarArr2.length);
        return bVarArr2;
    }

    public static void l(int i2, int i3, long[] jArr) {
        f4236a.put(u(i2 / 8, i3 / 8), jArr);
    }

    public static void o(b[] bVarArr) {
        if (bVarArr == null) {
            return;
        }
        for (int i2 = 1; i2 < bVarArr.length; i2++) {
            b bVar = bVarArr[i2];
            int i3 = i2;
            while (i3 > 0) {
                int i4 = i3 - 1;
                if (bVar.a() < bVarArr[i4].a()) {
                    bVarArr[i3] = bVarArr[i4];
                    i3 = i4;
                }
            }
            bVarArr[i3] = bVar;
        }
    }

    public static Integer u(int i2, int i3) {
        return new Integer(i2 | (i3 << 16));
    }

    @Override // g.a.f.e
    public void a(g.a.f.e eVar) {
        c0 c0Var = (c0) eVar;
        if (h() != c0Var.h() || this.f4238c != c0Var.f4238c) {
            throw new IllegalArgumentException("Incompatible parameters in provided SkeinEngine.");
        }
        e(c0Var);
    }

    @Override // g.a.f.e
    public g.a.f.e b() {
        return new c0(this);
    }

    public final void c() {
        if (this.f4244i == null) {
            throw new IllegalArgumentException("Skein engine is not initialised.");
        }
    }

    public final void e(c0 c0Var) {
        this.f4244i.d(c0Var.f4244i);
        this.f4239d = g.a.f.a.k(c0Var.f4239d, this.f4239d);
        this.f4240e = g.a.f.a.k(c0Var.f4240e, this.f4240e);
        this.f4241f = g.a.f.a.g(c0Var.f4241f, this.f4241f);
        this.f4242g = d(c0Var.f4242g, this.f4242g);
        this.f4243h = d(c0Var.f4243h, this.f4243h);
    }

    public final void f() {
        long[] jArr = (long[]) f4236a.get(u(h(), i()));
        int i2 = 0;
        if (this.f4241f != null || jArr == null) {
            this.f4239d = new long[h() / 8];
            byte[] bArr = this.f4241f;
            if (bArr != null) {
                p(0, bArr);
            }
            p(4, new a(this.f4238c * 8).a());
        } else {
            this.f4239d = g.a.f.a.j(jArr);
        }
        if (this.f4242g != null) {
            while (true) {
                b[] bVarArr = this.f4242g;
                if (i2 >= bVarArr.length) {
                    break;
                }
                b bVar = bVarArr[i2];
                p(bVar.a(), bVar.b());
                i2++;
            }
        }
        this.f4240e = g.a.f.a.j(this.f4239d);
    }

    public int g(byte[] bArr, int i2) {
        c();
        if (bArr.length < this.f4238c + i2) {
            throw new OutputLengthException("Output buffer is too short to hold output");
        }
        q();
        if (this.f4243h != null) {
            int i3 = 0;
            while (true) {
                b[] bVarArr = this.f4243h;
                if (i3 >= bVarArr.length) {
                    break;
                }
                b bVar = bVarArr[i3];
                p(bVar.a(), bVar.b());
                i3++;
            }
        }
        int iH = h();
        int i4 = ((this.f4238c + iH) - 1) / iH;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * iH;
            m(i5, bArr, i2 + i6, Math.min(iH, this.f4238c - i6));
        }
        n();
        return this.f4238c;
    }

    public int h() {
        return this.f4237b.b();
    }

    public int i() {
        return this.f4238c;
    }

    public void j(g1 g1Var) {
        this.f4239d = null;
        this.f4241f = null;
        this.f4242g = null;
        this.f4243h = null;
        if (g1Var != null) {
            if (g1Var.a().length < 16) {
                throw new IllegalArgumentException("Skein key must be at least 128 bits.");
            }
            k(g1Var.b());
        }
        f();
        r(48);
    }

    public final void k(Hashtable hashtable) {
        Enumeration enumerationKeys = hashtable.keys();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        while (enumerationKeys.hasMoreElements()) {
            Integer num = (Integer) enumerationKeys.nextElement();
            byte[] bArr = (byte[]) hashtable.get(num);
            if (num.intValue() == 0) {
                this.f4241f = bArr;
            } else if (num.intValue() < 48) {
                vector.addElement(new b(num.intValue(), bArr));
            } else {
                vector2.addElement(new b(num.intValue(), bArr));
            }
        }
        b[] bVarArr = new b[vector.size()];
        this.f4242g = bVarArr;
        vector.copyInto(bVarArr);
        o(this.f4242g);
        b[] bVarArr2 = new b[vector2.size()];
        this.f4243h = bVarArr2;
        vector2.copyInto(bVarArr2);
        o(this.f4243h);
    }

    public final void m(long j, byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[8];
        z0.m(j, bArr2, 0);
        long[] jArr = new long[this.f4239d.length];
        r(63);
        this.f4244i.e(bArr2, 0, 8, jArr);
        this.f4244i.a(jArr);
        int i4 = ((i3 + 8) - 1) / 8;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = i5 * 8;
            int iMin = Math.min(8, i3 - i6);
            if (iMin == 8) {
                z0.m(jArr[i5], bArr, i6 + i2);
            } else {
                z0.m(jArr[i5], bArr2, 0);
                System.arraycopy(bArr2, 0, bArr, i6 + i2, iMin);
            }
        }
    }

    public void n() {
        long[] jArr = this.f4240e;
        long[] jArr2 = this.f4239d;
        System.arraycopy(jArr, 0, jArr2, 0, jArr2.length);
        r(48);
    }

    public final void p(int i2, byte[] bArr) {
        r(i2);
        this.f4244i.e(bArr, 0, bArr.length, this.f4239d);
        q();
    }

    public final void q() {
        this.f4244i.a(this.f4239d);
    }

    public final void r(int i2) {
        this.f4244i.c(i2);
    }

    public void s(byte b2) {
        byte[] bArr = this.j;
        bArr[0] = b2;
        t(bArr, 0, 1);
    }

    public void t(byte[] bArr, int i2, int i3) {
        c();
        this.f4244i.e(bArr, i2, i3, this.f4239d);
    }
}
