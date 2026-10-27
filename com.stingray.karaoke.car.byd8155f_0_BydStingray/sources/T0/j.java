package T0;

import N0.C0411f;
import N0.K;
import a.AbstractC0904a;

/* JADX INFO: loaded from: classes.dex */
public final class j implements V2.b {
    public final /* synthetic */ int l = 2;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8454m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8455n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f8456p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f8457q;

    public j(C0411f c0411f, long j3) {
        String str = c0411f.l;
        A2.g gVar = new A2.g(4, (byte) 0);
        gVar.o = str;
        gVar.f319m = -1;
        gVar.f320n = -1;
        this.f8457q = gVar;
        this.f8454m = K.e(j3);
        this.f8455n = K.d(j3);
        this.o = -1;
        this.f8456p = -1;
        int iE = K.e(j3);
        int iD = K.d(j3);
        String str2 = c0411f.l;
        if (iE < 0 || iE > str2.length()) {
            StringBuilder sbJ = M5.b.j("start (", iE, ") offset is outside of text region ");
            sbJ.append(str2.length());
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        if (iD < 0 || iD > str2.length()) {
            StringBuilder sbJ2 = M5.b.j("end (", iD, ") offset is outside of text region ");
            sbJ2.append(str2.length());
            throw new IndexOutOfBoundsException(sbJ2.toString());
        }
        if (iE > iD) {
            throw new IllegalArgumentException(A7.k.i("Do not set reversed range: ", " > ", iE, iD));
        }
    }

    public void a(int i7, int i10) {
        long jQ = AbstractC0904a.q(i7, i10);
        ((A2.g) this.f8457q).z("", i7, i10);
        long jU = AbstractC0904a.U(AbstractC0904a.q(this.f8454m, this.f8455n), jQ);
        k(K.e(jU));
        j(K.d(jU));
        int i11 = this.o;
        if (i11 != -1) {
            long jU2 = AbstractC0904a.U(AbstractC0904a.q(i11, this.f8456p), jQ);
            if (K.b(jU2)) {
                this.o = -1;
                this.f8456p = -1;
            } else {
                this.o = K.e(jU2);
                this.f8456p = K.d(jU2);
            }
        }
    }

    public char b(int i7) {
        A2.g gVar = (A2.g) this.f8457q;
        E5.z zVar = (E5.z) gVar.f321p;
        if (zVar != null && i7 >= gVar.f319m) {
            int iD = zVar.f1841b - zVar.d();
            int i10 = gVar.f319m;
            if (i7 >= iD + i10) {
                return ((String) gVar.o).charAt(i7 - ((iD - gVar.f320n) + i10));
            }
            int i11 = i7 - i10;
            int i12 = zVar.c;
            return i11 < i12 ? ((char[]) zVar.f1842e)[i11] : ((char[]) zVar.f1842e)[(i11 - i12) + zVar.d];
        }
        return ((String) gVar.o).charAt(i7);
    }

    public K c() {
        int i7 = this.o;
        if (i7 != -1) {
            return new K(AbstractC0904a.q(i7, this.f8456p));
        }
        return null;
    }

    @Override // V2.b
    public int d() {
        return -1;
    }

    @Override // V2.b
    public int e() {
        return this.f8454m;
    }

    @Override // V2.b
    public int f() {
        h2.t tVar = (h2.t) this.f8457q;
        int i7 = this.f8455n;
        if (i7 == 8) {
            return tVar.v();
        }
        if (i7 == 16) {
            return tVar.B();
        }
        int i10 = this.o;
        this.o = i10 + 1;
        if (i10 % 2 != 0) {
            return this.f8456p & 15;
        }
        int iV = tVar.v();
        this.f8456p = iV;
        return (iV & 240) >> 4;
    }

    public void g(String str, int i7, int i10) {
        A2.g gVar = (A2.g) this.f8457q;
        if (i7 < 0 || i7 > gVar.o()) {
            StringBuilder sbJ = M5.b.j("start (", i7, ") offset is outside of text region ");
            sbJ.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        if (i10 < 0 || i10 > gVar.o()) {
            StringBuilder sbJ2 = M5.b.j("end (", i10, ") offset is outside of text region ");
            sbJ2.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ2.toString());
        }
        if (i7 > i10) {
            throw new IllegalArgumentException(A7.k.i("Do not set reversed range: ", " > ", i7, i10));
        }
        gVar.z(str, i7, i10);
        k(str.length() + i7);
        j(str.length() + i7);
        this.o = -1;
        this.f8456p = -1;
    }

    public void h(int i7, int i10) {
        A2.g gVar = (A2.g) this.f8457q;
        if (i7 < 0 || i7 > gVar.o()) {
            StringBuilder sbJ = M5.b.j("start (", i7, ") offset is outside of text region ");
            sbJ.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        if (i10 < 0 || i10 > gVar.o()) {
            StringBuilder sbJ2 = M5.b.j("end (", i10, ") offset is outside of text region ");
            sbJ2.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ2.toString());
        }
        if (i7 >= i10) {
            throw new IllegalArgumentException(A7.k.i("Do not set reversed or empty range: ", " > ", i7, i10));
        }
        this.o = i7;
        this.f8456p = i10;
    }

    public void i(int i7, int i10) {
        A2.g gVar = (A2.g) this.f8457q;
        if (i7 < 0 || i7 > gVar.o()) {
            StringBuilder sbJ = M5.b.j("start (", i7, ") offset is outside of text region ");
            sbJ.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        if (i10 < 0 || i10 > gVar.o()) {
            StringBuilder sbJ2 = M5.b.j("end (", i10, ") offset is outside of text region ");
            sbJ2.append(gVar.o());
            throw new IndexOutOfBoundsException(sbJ2.toString());
        }
        if (i7 > i10) {
            throw new IllegalArgumentException(A7.k.i("Do not set reversed range: ", " > ", i7, i10));
        }
        k(i7);
        j(i10);
    }

    public void j(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Cannot set selectionEnd to a negative value: ").toString());
        }
        this.f8455n = i7;
    }

    public void k(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Cannot set selectionStart to a negative value: ").toString());
        }
        this.f8454m = i7;
    }

    public String toString() {
        switch (this.l) {
            case 0:
                return ((A2.g) this.f8457q).toString();
            default:
                return super.toString();
        }
    }

    public j(int i7, int i10, int i11, int i12, int i13, byte[] bArr) {
        this.f8454m = i10;
        this.f8455n = i11;
        this.o = i12;
        this.f8456p = i13;
        this.f8457q = bArr;
    }

    public j(i2.c cVar) {
        h2.t tVar = cVar.f15672n;
        this.f8457q = tVar;
        tVar.H(12);
        this.f8455n = tVar.z() & 255;
        this.f8454m = tVar.z();
    }
}
