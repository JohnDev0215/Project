package g.a.a;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Exception;

/* JADX INFO: loaded from: classes.dex */
public class i extends FilterInputStream {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f3865c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f3866d;
    public final byte[][] s;

    public i(InputStream inputStream) {
        this(inputStream, y1.c(inputStream));
    }

    public i(InputStream inputStream, int i2) {
        this(inputStream, i2, false);
    }

    public i(InputStream inputStream, int i2, boolean z) {
        super(inputStream);
        this.f3865c = i2;
        this.f3866d = z;
        this.s = new byte[11][];
    }

    public i(InputStream inputStream, boolean z) {
        this(inputStream, y1.c(inputStream), z);
    }

    public i(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public i(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    public static char[] H(r1 r1Var) throws IOException {
        int i2;
        int iC = r1Var.c() / 2;
        char[] cArr = new char[iC];
        for (int i3 = 0; i3 < iC; i3++) {
            int i4 = r1Var.read();
            if (i4 < 0 || (i2 = r1Var.read()) < 0) {
                break;
            }
            cArr[i3] = (char) ((i4 << 8) | (i2 & 255));
        }
        return cArr;
    }

    public static byte[] I(r1 r1Var, byte[][] bArr) throws IOException {
        int iC = r1Var.c();
        if (r1Var.c() >= bArr.length) {
            return r1Var.s();
        }
        byte[] bArr2 = bArr[iC];
        if (bArr2 == null) {
            bArr2 = new byte[iC];
            bArr[iC] = bArr2;
        }
        g.a.f.k.a.c(r1Var, bArr2);
        return bArr2;
    }

    public static int L(InputStream inputStream, int i2) throws IOException {
        int i3 = inputStream.read();
        if (i3 < 0) {
            throw new EOFException("EOF found when length expected");
        }
        if (i3 == 128) {
            return -1;
        }
        if (i3 <= 127) {
            return i3;
        }
        int i4 = i3 & 127;
        if (i4 > 4) {
            throw new IOException("DER length more than 4 bytes: " + i4);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = inputStream.read();
            if (i7 < 0) {
                throw new EOFException("EOF found reading length");
            }
            i5 = (i5 << 8) + i7;
        }
        if (i5 < 0) {
            throw new IOException("corrupted stream - negative length found");
        }
        if (i5 < i2) {
            return i5;
        }
        throw new IOException("corrupted stream - out of bounds length found");
    }

    public static int N(InputStream inputStream, int i2) throws IOException {
        int i3 = i2 & 31;
        if (i3 != 31) {
            return i3;
        }
        int i4 = 0;
        int i5 = inputStream.read();
        if ((i5 & 127) == 0) {
            throw new IOException("corrupted stream - invalid high tag number found");
        }
        while (i5 >= 0 && (i5 & 128) != 0) {
            i4 = (i4 | (i5 & 127)) << 7;
            i5 = inputStream.read();
        }
        if (i5 >= 0) {
            return i4 | (i5 & 127);
        }
        throw new EOFException("EOF found inside tag value.");
    }

    public static q v(int i2, r1 r1Var, byte[][] bArr) throws IOException {
        if (i2 == 10) {
            return g.n(I(r1Var, bArr));
        }
        if (i2 == 12) {
            return new i1(r1Var.s());
        }
        if (i2 == 30) {
            return new n0(H(r1Var));
        }
        switch (i2) {
            case 1:
                return c.n(I(r1Var, bArr));
            case 2:
                return new j(r1Var.s(), false);
            case 3:
                return b.o(r1Var.c(), r1Var);
            case 4:
                return new y0(r1Var.s());
            case 5:
                return w0.f4105c;
            case 6:
                return m.p(I(r1Var, bArr));
            default:
                switch (i2) {
                    case 18:
                        return new x0(r1Var.s());
                    case 19:
                        return new b1(r1Var.s());
                    case 20:
                        return new g1(r1Var.s());
                    case 21:
                        return new k1(r1Var.s());
                    case 22:
                        return new v0(r1Var.s());
                    case 23:
                        return new y(r1Var.s());
                    case 24:
                        return new h(r1Var.s());
                    case 25:
                        return new u0(r1Var.s());
                    case 26:
                        return new l1(r1Var.s());
                    case 27:
                        return new s0(r1Var.s());
                    case 28:
                        return new j1(r1Var.s());
                    default:
                        throw new IOException("unknown tag " + i2 + " encountered");
                }
        }
    }

    public int J() {
        return this.f3865c;
    }

    public int K() throws IOException {
        return L(this, this.f3865c);
    }

    public q M() throws IOException {
        int i2 = read();
        if (i2 <= 0) {
            if (i2 != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int iN = N(this, i2);
        boolean z = (i2 & 32) != 0;
        int iK = K();
        if (iK >= 0) {
            try {
                return s(i2, iN, iK);
            } catch (IllegalArgumentException e2) {
                throw new ASN1Exception("corrupted stream detected", e2);
            }
        }
        if (!z) {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        v vVar = new v(new t1(this, this.f3865c), this.f3865c);
        if ((i2 & 64) != 0) {
            return new a0(iN, vVar).a();
        }
        if ((i2 & 128) != 0) {
            return new k0(true, iN, vVar).a();
        }
        if (iN == 4) {
            return new d0(vVar).a();
        }
        if (iN == 8) {
            return new q0(vVar).a();
        }
        if (iN == 16) {
            return new g0(vVar).a();
        }
        if (iN == 17) {
            return new i0(vVar).a();
        }
        throw new IOException("unknown BER object encountered");
    }

    public f c(r1 r1Var) throws IOException {
        return new i(r1Var).d();
    }

    public f d() throws IOException {
        f fVar = new f();
        while (true) {
            q qVarM = M();
            if (qVarM == null) {
                return fVar;
            }
            fVar.a(qVarM);
        }
    }

    public q s(int i2, int i3, int i4) throws IOException {
        boolean z = (i2 & 32) != 0;
        r1 r1Var = new r1(this, i4);
        if ((i2 & 64) != 0) {
            return new m0(z, i3, r1Var.s());
        }
        if ((i2 & 128) != 0) {
            return new v(r1Var).c(z, i3);
        }
        if (!z) {
            return v(i3, r1Var, this.s);
        }
        if (i3 == 4) {
            f fVarC = c(r1Var);
            int iC = fVarC.c();
            n[] nVarArr = new n[iC];
            for (int i5 = 0; i5 != iC; i5++) {
                nVarArr[i5] = (n) fVarC.b(i5);
            }
            return new c0(nVarArr);
        }
        if (i3 == 8) {
            return new p0(c(r1Var));
        }
        if (i3 == 16) {
            return this.f3866d ? new v1(r1Var.s()) : r0.a(c(r1Var));
        }
        if (i3 == 17) {
            return r0.b(c(r1Var));
        }
        throw new IOException("unknown tag " + i3 + " encountered");
    }
}
