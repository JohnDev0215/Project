package b.a.h;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.byd.util.w;
import java.io.InputStream;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
public class e {
    private Bitmap A;
    private int I;
    private short[] J;
    private byte[] K;
    private byte[] L;
    private byte[] M;
    private Vector<b> N;
    private int O;
    private a P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f480a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private InputStream f481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f482c;
    private int d;
    private boolean e;
    private int f;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private int y;
    private Bitmap z;
    private int g = 1;
    private int B = 0;
    private byte[] C = new byte[256];
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private boolean G = false;
    private int H = 0;

    public interface a {
        boolean canDecodeContent(int i, int i2);
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Bitmap f483a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f484b;

        public b(Bitmap bitmap, int i) {
            this.f483a = bitmap;
            this.f484b = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v25, types: [short] */
    /* JADX WARN: Type inference failed for: r2v27 */
    private void a() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        short s;
        int i6 = this.t * this.u;
        byte[] bArr = this.M;
        if (bArr == null || bArr.length < i6) {
            this.M = new byte[i6];
        }
        if (this.J == null) {
            this.J = new short[4096];
        }
        if (this.K == null) {
            this.K = new byte[4096];
        }
        if (this.L == null) {
            this.L = new byte[4097];
        }
        int i7 = i();
        int i8 = 1 << i7;
        int i9 = i8 + 1;
        int i10 = i8 + 2;
        int i11 = i7 + 1;
        int i12 = (1 << i11) - 1;
        for (int i13 = 0; i13 < i8; i13++) {
            this.J[i13] = 0;
            this.K[i13] = (byte) i13;
        }
        int i14 = i11;
        int i15 = i12;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int iK = 0;
        int i20 = 0;
        int i21 = 0;
        int i22 = 0;
        int i23 = -1;
        int i24 = i10;
        while (i16 < i6) {
            if (i17 != 0) {
                i = i11;
                i2 = i9;
                int i25 = i21;
                i3 = i8;
                i4 = i25;
            } else if (i18 < i14) {
                if (iK == 0) {
                    iK = k();
                    if (iK <= 0) {
                        break;
                    } else {
                        i20 = 0;
                    }
                }
                i19 += (this.C[i20] & 255) << i18;
                i18 += 8;
                i20++;
                iK--;
            } else {
                int i26 = i19 & i15;
                i19 >>= i14;
                i18 -= i14;
                if (i26 > i24 || i26 == i9) {
                    break;
                }
                if (i26 == i8) {
                    i14 = i11;
                    i24 = i10;
                    i15 = i12;
                    i23 = -1;
                } else if (i23 == -1) {
                    this.L[i17] = this.K[i26];
                    i23 = i26;
                    i21 = i23;
                    i17++;
                    i11 = i11;
                } else {
                    i = i11;
                    if (i26 == i24) {
                        i5 = i26;
                        this.L[i17] = (byte) i21;
                        s = i23;
                        i17++;
                    } else {
                        i5 = i26;
                        s = i5;
                    }
                    while (s > i8) {
                        this.L[i17] = this.K[s];
                        s = this.J[s];
                        i17++;
                        i8 = i8;
                    }
                    i3 = i8;
                    byte[] bArr2 = this.K;
                    i4 = bArr2[s] & 255;
                    if (i24 >= 4096) {
                        break;
                    }
                    int i27 = i17 + 1;
                    i2 = i9;
                    byte b2 = (byte) i4;
                    this.L[i17] = b2;
                    this.J[i24] = (short) i23;
                    bArr2[i24] = b2;
                    i24++;
                    if ((i24 & i15) == 0 && i24 < 4096) {
                        i14++;
                        i15 += i24;
                    }
                    i17 = i27;
                    i23 = i5;
                }
            }
            i17--;
            this.M[i22] = this.L[i17];
            i16++;
            i22++;
            i8 = i3;
            i9 = i2;
            i21 = i4;
            i11 = i;
        }
        for (int i28 = i22; i28 < i6; i28++) {
            this.M[i28] = 0;
        }
    }

    private boolean b() {
        return this.f480a != 0;
    }

    private void h() {
        this.f480a = 0;
        this.O = 0;
        this.N = new Vector<>();
        this.h = null;
        this.i = null;
    }

    private int i() {
        try {
            return this.f481b.read();
        } catch (Exception unused) {
            this.f480a = 1;
            return 0;
        }
    }

    private int k() {
        int i = i();
        this.D = i;
        int i2 = 0;
        if (i > 0) {
            while (true) {
                try {
                    int i3 = this.D;
                    if (i2 >= i3) {
                        break;
                    }
                    int i4 = this.f481b.read(this.C, i2, i3 - i2);
                    if (i4 == -1) {
                        break;
                    }
                    i2 += i4;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i2 < this.D) {
                this.f480a = 1;
            }
        }
        return i2;
    }

    private int[] l(int i) {
        int i2;
        int i3 = i * 3;
        byte[] bArr = new byte[i3];
        try {
            i2 = this.f481b.read(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            i2 = 0;
        }
        if (i2 < i3) {
            this.f480a = 1;
            return null;
        }
        int[] iArr = new int[256];
        int i4 = 0;
        for (int i5 = 0; i5 < i; i5++) {
            int i6 = i4 + 1;
            int i7 = i6 + 1;
            iArr[i5] = ((bArr[i4] & 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((bArr[i6] & 255) << 8) | (bArr[i7] & 255);
            i4 = i7 + 1;
        }
        return iArr;
    }

    private void m() {
        boolean z = false;
        while (!z && !b()) {
            int i = i();
            if (i != 0) {
                if (i == 33) {
                    int i2 = i();
                    if (i2 != 249) {
                        if (i2 == 255) {
                            k();
                            String str = "";
                            for (int i3 = 0; i3 < 11; i3++) {
                                str = str + ((char) this.C[i3]);
                            }
                            if (str.equals("NETSCAPE2.0")) {
                                r();
                            }
                        }
                        w();
                    } else {
                        n();
                    }
                } else if (i == 44) {
                    p();
                } else if (i != 59) {
                    this.f480a = 1;
                } else {
                    z = true;
                }
            }
        }
    }

    private void n() {
        i();
        int i = i();
        int i2 = (i & 28) >> 2;
        this.E = i2;
        if (i2 == 0) {
            this.E = 1;
        }
        this.G = (i & 1) != 0;
        this.H = s() * 10;
        this.I = i();
        i();
    }

    private void o() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) i());
        }
        w.c("===>" + str);
        if (!str.toUpperCase().startsWith("GIF")) {
            this.f480a = 1;
            return;
        }
        q();
        if (!this.e || b()) {
            return;
        }
        int[] iArrL = l(this.f);
        this.h = iArrL;
        this.l = iArrL[this.k];
    }

    private void p() {
        this.r = s();
        this.s = s();
        this.t = s();
        this.u = s();
        int i = i();
        int i2 = 0;
        boolean z = (i & 128) != 0;
        this.o = z;
        this.p = (i & 64) != 0;
        int i3 = 2 << (i & 7);
        this.q = i3;
        if (z) {
            int[] iArrL = l(i3);
            this.i = iArrL;
            this.j = iArrL;
        } else {
            this.j = this.h;
            if (this.k == this.I) {
                this.l = 0;
            }
        }
        if (this.G) {
            int[] iArr = this.j;
            int i4 = this.I;
            int i5 = iArr[i4];
            iArr[i4] = 0;
            i2 = i5;
        }
        if (this.j == null) {
            this.f480a = 1;
        }
        if (b()) {
            return;
        }
        a();
        w();
        if (b()) {
            return;
        }
        this.O++;
        this.z = Bitmap.createBitmap(this.f482c, this.d, Bitmap.Config.ARGB_8888);
        v();
        this.N.addElement(new b(this.z, this.H));
        if (this.G) {
            this.j[this.I] = i2;
        }
        t();
    }

    private void q() {
        this.f482c = s();
        this.d = s();
        int i = i();
        this.e = (i & 128) != 0;
        this.f = 2 << (i & 7);
        this.k = i();
        this.n = i();
        a aVar = this.P;
        if (aVar == null || aVar.canDecodeContent(this.f482c, this.d)) {
            return;
        }
        this.f480a = 3;
        w.c("=====STATUS_CONTENT_TOO_LARGE=====" + this.f482c + " " + this.d + " " + this.e + " " + this.f);
    }

    private void r() {
        do {
            k();
            byte[] bArr = this.C;
            if (bArr[0] == 1) {
                this.g = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
            }
            if (this.D <= 0) {
                return;
            }
        } while (!b());
    }

    private int s() {
        return i() | (i() << 8);
    }

    private void t() {
        this.F = this.E;
        this.v = this.r;
        this.w = this.s;
        this.x = this.t;
        this.y = this.u;
        this.A = this.z;
        this.m = this.l;
        this.E = 0;
        this.G = false;
        this.H = 0;
        this.i = null;
    }

    private void v() {
        int i;
        int[] iArr = new int[this.f482c * this.d];
        int i2 = this.F;
        int i3 = 0;
        if (i2 > 0) {
            if (i2 == 3) {
                int i4 = this.O - 2;
                this.A = i4 > 0 ? c(i4 - 1) : null;
            }
            Bitmap bitmap = this.A;
            if (bitmap != null) {
                int i5 = this.f482c;
                bitmap.getPixels(iArr, 0, i5, 0, 0, i5, this.d);
                if (this.F == 2) {
                    int i6 = !this.G ? this.m : 0;
                    for (int i7 = 0; i7 < this.y; i7++) {
                        int i8 = ((this.w + i7) * this.f482c) + this.v;
                        int i9 = this.x + i8;
                        while (i8 < i9) {
                            iArr[i8] = i6;
                            i8++;
                        }
                    }
                }
            }
        }
        int i10 = 8;
        int i11 = 0;
        int i12 = 1;
        while (true) {
            int i13 = this.u;
            if (i3 >= i13) {
                this.z = Bitmap.createBitmap(iArr, this.f482c, this.d, Bitmap.Config.ARGB_8888);
                return;
            }
            if (this.p) {
                if (i11 >= i13) {
                    i12++;
                    if (i12 == 2) {
                        i11 = 4;
                    } else if (i12 == 3) {
                        i10 = 4;
                        i11 = 2;
                    } else if (i12 == 4) {
                        i10 = 2;
                        i11 = 1;
                    }
                }
                i = i11 + i10;
            } else {
                i = i11;
                i11 = i3;
            }
            int i14 = i11 + this.s;
            if (i14 < this.d) {
                int i15 = this.f482c;
                int i16 = i14 * i15;
                int i17 = this.r + i16;
                int i18 = this.t;
                int i19 = i17 + i18;
                if (i16 + i15 < i19) {
                    i19 = i16 + i15;
                }
                int i20 = i18 * i3;
                while (i17 < i19) {
                    int i21 = i20 + 1;
                    int i22 = this.j[this.M[i20] & 255];
                    if (i22 != 0) {
                        iArr[i17] = i22;
                    }
                    i17++;
                    i20 = i21;
                }
            }
            i3++;
            i11 = i;
        }
    }

    private void w() {
        do {
            k();
            if (this.D <= 0) {
                return;
            }
        } while (!b());
    }

    public Bitmap c(int i) {
        if (i < 0 || i >= this.O) {
            return null;
        }
        return this.N.elementAt(i).f483a;
    }

    public int d() {
        return this.O;
    }

    public b[] e() {
        Vector<b> vector = this.N;
        if (vector != null) {
            return (b[]) vector.toArray(new b[0]);
        }
        return null;
    }

    public int f() {
        return this.d;
    }

    public int g() {
        return this.f482c;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(8:0|2|(6:4|(2:6|(1:8))|17|11|15|16)(1:9)|10|17|11|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
    
        r2.printStackTrace();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int j(java.io.InputStream r2) {
        /*
            r1 = this;
            r1.h()
            if (r2 == 0) goto L19
            r1.f481b = r2
            r1.o()
            boolean r0 = r1.b()
            if (r0 != 0) goto L1c
            r1.m()
            int r0 = r1.O
            if (r0 >= 0) goto L1c
            r0 = 1
            goto L1a
        L19:
            r0 = 2
        L1a:
            r1.f480a = r0
        L1c:
            r2.close()     // Catch: java.lang.Exception -> L20
            goto L24
        L20:
            r2 = move-exception
            r2.printStackTrace()
        L24:
            int r2 = r1.f480a
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.h.e.j(java.io.InputStream):int");
    }

    public void u(a aVar) {
        this.P = aVar;
    }
}
