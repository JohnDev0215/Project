package A2;

import B5.G;
import B5.I;
import B5.v;
import E5.s;
import E5.z;
import V2.q;
import h2.AbstractC1346c;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.Locale;
import n7.AbstractC1996k;
import p8.AbstractC2126a;
import p8.AbstractC2129d;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class g implements v {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f319m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f320n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f321p;

    public g(CharSequence charSequence, int i7, Locale locale) {
        this.l = 3;
        this.o = charSequence;
        if (charSequence.length() < 0) {
            throw new IllegalArgumentException("input start index is outside the CharSequence");
        }
        if (i7 < 0 || i7 > charSequence.length()) {
            throw new IllegalArgumentException("input end index is outside the CharSequence");
        }
        BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
        this.f321p = wordInstance;
        this.f319m = Math.max(0, -50);
        this.f320n = Math.min(charSequence.length(), i7 + 50);
        wordInstance.setText(new O0.j(i7, charSequence));
    }

    public static int e(int i7, int i10) {
        return g(i10) + l(i7);
    }

    public static int f(int i7, int i10) {
        return g(i10) + l(i7);
    }

    public static int g(int i7) {
        if (i7 >= 0) {
            return j(i7);
        }
        return 10;
    }

    public static int h(int i7, AbstractC2126a abstractC2126a) {
        return i(abstractC2126a) + l(i7);
    }

    public static int i(AbstractC2126a abstractC2126a) {
        int iC = abstractC2126a.c();
        return j(iC) + iC;
    }

    public static int j(int i7) {
        if ((i7 & (-128)) == 0) {
            return 1;
        }
        if ((i7 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i7) == 0) {
            return 3;
        }
        return (i7 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int k(long j3) {
        if (((-128) & j3) == 0) {
            return 1;
        }
        if (((-16384) & j3) == 0) {
            return 2;
        }
        if (((-2097152) & j3) == 0) {
            return 3;
        }
        if (((-268435456) & j3) == 0) {
            return 4;
        }
        if (((-34359738368L) & j3) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j3) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j3) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j3) == 0) {
            return 8;
        }
        return (j3 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int l(int i7) {
        return j(i7 << 3);
    }

    public static g t(OutputStream outputStream, int i7) {
        return new g(outputStream, new byte[i7]);
    }

    public synchronized int A() {
        return this.f320n;
    }

    public void B(int i7, int i10) throws IOException {
        N(i7, 0);
        D(i10);
    }

    public void C(int i7, int i10) throws IOException {
        N(i7, 0);
        D(i10);
    }

    public void D(int i7) throws IOException {
        if (i7 >= 0) {
            L(i7);
        } else {
            M(i7);
        }
    }

    public void E(int i7, AbstractC2126a abstractC2126a) throws IOException {
        N(i7, 2);
        F(abstractC2126a);
    }

    public void F(AbstractC2126a abstractC2126a) throws IOException {
        L(abstractC2126a.c());
        abstractC2126a.f(this);
    }

    public void G(int i7) throws IOException {
        byte b10 = (byte) i7;
        if (this.f320n == this.f319m) {
            y();
        }
        int i10 = this.f320n;
        this.f320n = i10 + 1;
        ((byte[]) this.o)[i10] = b10;
    }

    public void H(AbstractC2129d abstractC2129d) throws IOException {
        int size = abstractC2129d.size();
        int i7 = this.f320n;
        int i10 = this.f319m;
        int i11 = i10 - i7;
        byte[] bArr = (byte[]) this.o;
        if (i11 >= size) {
            abstractC2129d.l(0, i7, size, bArr);
            this.f320n += size;
            return;
        }
        abstractC2129d.l(0, i7, i11, bArr);
        int i12 = size - i11;
        this.f320n = i10;
        y();
        if (i12 <= i10) {
            abstractC2129d.l(i11, 0, i12, bArr);
            this.f320n = i12;
            return;
        }
        if (i11 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i11);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i12 < 0) {
            StringBuilder sb2 = new StringBuilder(23);
            sb2.append("Length < 0: ");
            sb2.append(i12);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        int i13 = i11 + i12;
        if (i13 <= abstractC2129d.size()) {
            if (i12 > 0) {
                abstractC2129d.z((OutputStream) this.f321p, i11, i12);
            }
        } else {
            StringBuilder sb3 = new StringBuilder(39);
            sb3.append("Source end offset exceeded: ");
            sb3.append(i13);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public void I(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i7 = this.f320n;
        int i10 = this.f319m;
        int i11 = i10 - i7;
        byte[] bArr2 = (byte[]) this.o;
        if (i11 >= length) {
            System.arraycopy(bArr, 0, bArr2, i7, length);
            this.f320n += length;
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i7, i11);
        int i12 = length - i11;
        this.f320n = i10;
        y();
        if (i12 > i10) {
            ((OutputStream) this.f321p).write(bArr, i11, i12);
        } else {
            System.arraycopy(bArr, i11, bArr2, 0, i12);
            this.f320n = i12;
        }
    }

    public void J(int i7) throws IOException {
        G(i7 & 255);
        G((i7 >> 8) & 255);
        G((i7 >> 16) & 255);
        G((i7 >> 24) & 255);
    }

    public void K(long j3) throws IOException {
        G(((int) j3) & 255);
        G(((int) (j3 >> 8)) & 255);
        G(((int) (j3 >> 16)) & 255);
        G(((int) (j3 >> 24)) & 255);
        G(((int) (j3 >> 32)) & 255);
        G(((int) (j3 >> 40)) & 255);
        G(((int) (j3 >> 48)) & 255);
        G(((int) (j3 >> 56)) & 255);
    }

    public void L(int i7) throws IOException {
        while ((i7 & (-128)) != 0) {
            G((i7 & 127) | 128);
            i7 >>>= 7;
        }
        G(i7);
    }

    public void M(long j3) throws IOException {
        while (((-128) & j3) != 0) {
            G((((int) j3) & 127) | 128);
            j3 >>>= 7;
        }
        G((int) j3);
    }

    public void N(int i7, int i10) throws IOException {
        L((i7 << 3) | i10);
    }

    @Override // B5.v
    public boolean a(InputStream inputStream, int i7) throws IOException {
        ((B5.m) this.f321p).getClass();
        int i10 = this.f319m + i7;
        if (i10 > 475000) {
            return false;
        }
        this.f319m = i10;
        byte[] bArr = new byte[i7];
        inputStream.read(bArr, 0, i7);
        String strTrim = new String(bArr, I.o).trim();
        G g7 = (G) this.o;
        boolean z6 = g7.f654m;
        BufferedWriter bufferedWriter = (BufferedWriter) g7.o;
        if (z6) {
            bufferedWriter.write(44);
        } else {
            g7.f654m = true;
        }
        bufferedWriter.write(strTrim);
        this.f320n++;
        return true;
    }

    public synchronized void b(long j3, Object obj) {
        if (this.f320n > 0) {
            if (j3 <= ((long[]) this.o)[((this.f319m + r0) - 1) % ((Object[]) this.f321p).length]) {
                d();
            }
        }
        m();
        int i7 = this.f319m;
        int i10 = this.f320n;
        Object[] objArr = (Object[]) this.f321p;
        int length = (i7 + i10) % objArr.length;
        ((long[]) this.o)[length] = j3;
        objArr[length] = obj;
        this.f320n = i10 + 1;
    }

    public void c(int i7) {
        int i10 = this.f319m;
        int i11 = this.f320n;
        if (i7 > i11 || i10 > i7) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.j(M5.b.l("Invalid offset: ", ". Valid range is [", i7, i10, " , "), i11, ']').toString());
        }
    }

    public synchronized void d() {
        this.f319m = 0;
        this.f320n = 0;
        Arrays.fill((Object[]) this.f321p, (Object) null);
    }

    public void m() {
        int length = ((Object[]) this.f321p).length;
        if (this.f320n < length) {
            return;
        }
        int i7 = length * 2;
        long[] jArr = new long[i7];
        Object[] objArr = new Object[i7];
        int i10 = this.f319m;
        int i11 = length - i10;
        System.arraycopy((long[]) this.o, i10, jArr, 0, i11);
        System.arraycopy((Object[]) this.f321p, this.f319m, objArr, 0, i11);
        int i12 = this.f319m;
        if (i12 > 0) {
            System.arraycopy((long[]) this.o, 0, jArr, i11, i12);
            System.arraycopy((Object[]) this.f321p, 0, objArr, i11, this.f319m);
        }
        this.o = jArr;
        this.f321p = objArr;
        this.f319m = 0;
    }

    public void n() throws IOException {
        if (((OutputStream) this.f321p) != null) {
            y();
        }
    }

    public int o() {
        z zVar = (z) this.f321p;
        if (zVar == null) {
            return ((String) this.o).length();
        }
        return (zVar.f1841b - zVar.d()) + (((String) this.o).length() - (this.f320n - this.f319m));
    }

    public boolean p(int i7) {
        return i7 <= this.f320n && this.f319m + 1 <= i7 && Character.isLetterOrDigit(Character.codePointBefore((CharSequence) this.o, i7));
    }

    public boolean q(int i7) {
        int i10 = this.f319m + 1;
        if (i7 > this.f320n || i10 > i7) {
            return false;
        }
        return AbstractC2673a.N(Character.codePointBefore((CharSequence) this.o, i7));
    }

    public boolean r(int i7) {
        return i7 < this.f320n && this.f319m <= i7 && Character.isLetterOrDigit(Character.codePointAt((CharSequence) this.o, i7));
    }

    public boolean s(int i7) {
        if (i7 >= this.f320n || this.f319m > i7) {
            return false;
        }
        return AbstractC2673a.N(Character.codePointAt((CharSequence) this.o, i7));
    }

    public String toString() {
        switch (this.l) {
            case 4:
                z zVar = (z) this.f321p;
                if (zVar == null) {
                    return (String) this.o;
                }
                StringBuilder sb = new StringBuilder();
                sb.append((CharSequence) this.o, 0, this.f319m);
                sb.append((char[]) zVar.f1842e, 0, zVar.c);
                char[] cArr = (char[]) zVar.f1842e;
                int i7 = zVar.d;
                sb.append(cArr, i7, zVar.f1841b - i7);
                String str = (String) this.o;
                sb.append((CharSequence) str, this.f320n, str.length());
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public Object u(long j3, boolean z6) {
        Object objX = null;
        long j10 = Long.MAX_VALUE;
        while (this.f320n > 0) {
            long j11 = j3 - ((long[]) this.o)[this.f319m];
            if (j11 < 0 && (z6 || (-j11) >= j10)) {
                break;
            }
            objX = x();
            j10 = j11;
        }
        return objX;
    }

    public synchronized Object v() {
        return this.f320n == 0 ? null : x();
    }

    public synchronized Object w(long j3) {
        return u(j3, true);
    }

    public Object x() {
        AbstractC1346c.j(this.f320n > 0);
        Object[] objArr = (Object[]) this.f321p;
        int i7 = this.f319m;
        Object obj = objArr[i7];
        objArr[i7] = null;
        this.f319m = (i7 + 1) % objArr.length;
        this.f320n--;
        return obj;
    }

    public void y() throws IOException {
        OutputStream outputStream = (OutputStream) this.f321p;
        if (outputStream == null) {
            throw new s("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
        outputStream.write((byte[]) this.o, 0, this.f320n);
        this.f320n = 0;
    }

    public void z(String str, int i7, int i10) {
        if (i7 > i10) {
            throw new IllegalArgumentException(A7.k.i("start index must be less than or equal to end index: ", " > ", i7, i10).toString());
        }
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "start must be non-negative, but was ").toString());
        }
        z zVar = (z) this.f321p;
        if (zVar == null) {
            int iMax = Math.max(255, str.length() + 128);
            char[] cArr = new char[iMax];
            int iMin = Math.min(i7, 64);
            int iMin2 = Math.min(((String) this.o).length() - i10, 64);
            String str2 = (String) this.o;
            int i11 = i7 - iMin;
            A7.m.d(str2, "null cannot be cast to non-null type java.lang.String");
            str2.getChars(i11, i7, cArr, 0);
            String str3 = (String) this.o;
            int i12 = iMax - iMin2;
            int i13 = iMin2 + i10;
            A7.m.d(str3, "null cannot be cast to non-null type java.lang.String");
            str3.getChars(i10, i13, cArr, i12);
            str.getChars(0, str.length(), cArr, iMin);
            int length = str.length() + iMin;
            z zVar2 = new z(3, (byte) 0);
            zVar2.f1841b = iMax;
            zVar2.f1842e = cArr;
            zVar2.c = length;
            zVar2.d = i12;
            this.f321p = zVar2;
            this.f319m = i11;
            this.f320n = i13;
            return;
        }
        int i14 = this.f319m;
        int i15 = i7 - i14;
        int i16 = i10 - i14;
        if (i15 < 0 || i16 > zVar.f1841b - zVar.d()) {
            this.o = toString();
            this.f321p = null;
            this.f319m = -1;
            this.f320n = -1;
            z(str, i7, i10);
            return;
        }
        int length2 = str.length() - (i16 - i15);
        if (length2 > zVar.d()) {
            int iD = length2 - zVar.d();
            int i17 = zVar.f1841b;
            do {
                i17 *= 2;
            } while (i17 - zVar.f1841b < iD);
            char[] cArr2 = new char[i17];
            AbstractC1996k.E((char[]) zVar.f1842e, cArr2, 0, 0, zVar.c);
            int i18 = zVar.f1841b;
            int i19 = zVar.d;
            int i20 = i18 - i19;
            int i21 = i17 - i20;
            AbstractC1996k.E((char[]) zVar.f1842e, cArr2, i21, i19, i20 + i19);
            zVar.f1842e = cArr2;
            zVar.f1841b = i17;
            zVar.d = i21;
        }
        int i22 = zVar.c;
        if (i15 < i22 && i16 <= i22) {
            int i23 = i22 - i16;
            char[] cArr3 = (char[]) zVar.f1842e;
            AbstractC1996k.E(cArr3, cArr3, zVar.d - i23, i16, i22);
            zVar.c = i15;
            zVar.d -= i23;
        } else if (i15 >= i22 || i16 < i22) {
            int iD2 = zVar.d() + i15;
            int iD3 = zVar.d() + i16;
            int i24 = zVar.d;
            char[] cArr4 = (char[]) zVar.f1842e;
            AbstractC1996k.E(cArr4, cArr4, zVar.c, i24, iD2);
            zVar.c += iD2 - i24;
            zVar.d = iD3;
        } else {
            zVar.d = zVar.d() + i16;
            zVar.c = i15;
        }
        str.getChars(0, str.length(), (char[]) zVar.f1842e, zVar.c);
        zVar.c = str.length() + zVar.c;
    }

    public g(int i7, byte b10) {
        this.l = i7;
        switch (i7) {
            case 6:
                this.o = new long[10];
                this.f321p = new Object[10];
                break;
        }
    }

    public g(OutputStream outputStream, byte[] bArr) {
        this.l = 7;
        this.f321p = outputStream;
        this.o = bArr;
        this.f320n = 0;
        this.f319m = bArr.length;
    }

    public g(int i7, int i10, float[] fArr, float[] fArr2) {
        this.l = 0;
        this.f319m = i7;
        AbstractC1346c.c(((long) fArr.length) * 2 == ((long) fArr2.length) * 3);
        this.o = fArr;
        this.f321p = fArr2;
        this.f320n = i10;
    }

    public g(g gVar) {
        this.l = 1;
        float[] fArr = (float[]) gVar.o;
        this.f319m = fArr.length / 3;
        this.o = AbstractC1346c.m(fArr);
        this.f321p = AbstractC1346c.m((float[]) gVar.f321p);
        int i7 = gVar.f320n;
        if (i7 == 1) {
            this.f320n = 5;
        } else if (i7 != 2) {
            this.f320n = 4;
        } else {
            this.f320n = 6;
        }
    }

    public g(G g7, B5.m mVar) {
        this.l = 2;
        this.o = g7;
        this.f321p = mVar;
    }

    public g(int i7) {
        this.l = 5;
        this.o = new q[i7];
        this.f320n = 0;
    }
}
