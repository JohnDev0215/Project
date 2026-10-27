package g.a.a;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Exception;

/* JADX INFO: loaded from: classes.dex */
public class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InputStream f4077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4078b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte[][] f4079c;

    public v(InputStream inputStream) {
        this(inputStream, y1.c(inputStream));
    }

    public v(InputStream inputStream, int i2) {
        this.f4077a = inputStream;
        this.f4078b = i2;
        this.f4079c = new byte[11][];
    }

    public e a(int i2) throws IOException {
        if (i2 == 4) {
            return new d0(this);
        }
        if (i2 == 8) {
            return new q0(this);
        }
        if (i2 == 16) {
            return new g0(this);
        }
        if (i2 == 17) {
            return new i0(this);
        }
        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i2));
    }

    public e b() throws IOException {
        int i2 = this.f4077a.read();
        if (i2 == -1) {
            return null;
        }
        e(false);
        int iN = i.N(this.f4077a, i2);
        boolean z = (i2 & 32) != 0;
        int iL = i.L(this.f4077a, this.f4078b);
        if (iL < 0) {
            if (!z) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            v vVar = new v(new t1(this.f4077a, this.f4078b), this.f4078b);
            return (i2 & 64) != 0 ? new a0(iN, vVar) : (i2 & 128) != 0 ? new k0(true, iN, vVar) : vVar.a(iN);
        }
        r1 r1Var = new r1(this.f4077a, iL);
        if ((i2 & 64) != 0) {
            return new m0(z, iN, r1Var.s());
        }
        if ((i2 & 128) != 0) {
            return new k0(z, iN, new v(r1Var));
        }
        if (!z) {
            if (iN == 4) {
                return new z0(r1Var);
            }
            try {
                return i.v(iN, r1Var, this.f4079c);
            } catch (IllegalArgumentException e2) {
                throw new ASN1Exception("corrupted stream detected", e2);
            }
        }
        if (iN == 4) {
            return new d0(new v(r1Var));
        }
        if (iN == 8) {
            return new q0(new v(r1Var));
        }
        if (iN == 16) {
            return new d1(new v(r1Var));
        }
        if (iN == 17) {
            return new f1(new v(r1Var));
        }
        throw new IOException("unknown tag " + iN + " encountered");
    }

    public q c(boolean z, int i2) throws IOException {
        if (!z) {
            return new h1(false, i2, new y0(((r1) this.f4077a).s()));
        }
        f fVarD = d();
        return this.f4077a instanceof t1 ? fVarD.c() == 1 ? new j0(true, i2, fVarD.b(0)) : new j0(false, i2, b0.a(fVarD)) : fVarD.c() == 1 ? new h1(true, i2, fVarD.b(0)) : new h1(false, i2, r0.a(fVarD));
    }

    public f d() throws IOException {
        f fVar = new f();
        while (true) {
            e eVarB = b();
            if (eVarB == null) {
                return fVar;
            }
            fVar.a(eVarB instanceof s1 ? ((s1) eVarB).a() : eVarB.c());
        }
    }

    public final void e(boolean z) {
        InputStream inputStream = this.f4077a;
        if (inputStream instanceof t1) {
            ((t1) inputStream).v(z);
        }
    }
}
