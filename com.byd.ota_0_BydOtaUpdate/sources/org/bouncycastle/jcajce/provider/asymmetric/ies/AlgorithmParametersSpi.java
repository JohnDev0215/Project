package org.bouncycastle.jcajce.provider.asymmetric.ies;

import g.a.a.c1;
import g.a.a.f;
import g.a.a.h1;
import g.a.a.j;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.x;
import g.a.a.y0;
import g.a.c.e.p;
import java.io.IOException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class AlgorithmParametersSpi extends java.security.AlgorithmParametersSpi {
    public p currentSpec;

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded() {
        try {
            f fVar = new f();
            if (this.currentSpec.b() != null) {
                fVar.a(new h1(false, 0, new y0(this.currentSpec.b())));
            }
            if (this.currentSpec.c() != null) {
                fVar.a(new h1(false, 1, new y0(this.currentSpec.c())));
            }
            fVar.a(new j(this.currentSpec.d()));
            if (this.currentSpec.e() != null) {
                f fVar2 = new f();
                fVar2.a(new j(this.currentSpec.a()));
                fVar2.a(new j(this.currentSpec.e()));
                fVar.a(new c1(fVar2));
            }
            return new c1(fVar).f("DER");
        } catch (IOException unused) {
            throw new RuntimeException("Error encoding IESParameters");
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public byte[] engineGetEncoded(String str) {
        if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
            return engineGetEncoded();
        }
        return null;
    }

    @Override // java.security.AlgorithmParametersSpi
    public AlgorithmParameterSpec engineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        Objects.requireNonNull(cls, "argument to getParameterSpec must not be null");
        return localEngineGetParameterSpec(cls);
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(AlgorithmParameterSpec algorithmParameterSpec) throws InvalidParameterSpecException {
        if (!(algorithmParameterSpec instanceof p)) {
            throw new InvalidParameterSpecException("IESParameterSpec required to initialise a IES algorithm parameters object");
        }
        this.currentSpec = (p) algorithmParameterSpec;
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr) throws IOException {
        try {
            r rVar = (r) q.j(bArr);
            if (rVar.size() == 1) {
                this.currentSpec = new p(null, null, j.n(rVar.q(0)).q().intValue());
                return;
            }
            if (rVar.size() == 2) {
                x xVarN = x.n(rVar.q(0));
                this.currentSpec = xVarN.q() == 0 ? new p(n.o(xVarN, false).p(), null, j.n(rVar.q(1)).q().intValue()) : new p(null, n.o(xVarN, false).p(), j.n(rVar.q(1)).q().intValue());
            } else if (rVar.size() == 3) {
                this.currentSpec = new p(n.o(x.n(rVar.q(0)), false).p(), n.o(x.n(rVar.q(1)), false).p(), j.n(rVar.q(2)).q().intValue());
            } else if (rVar.size() == 4) {
                x xVarN2 = x.n(rVar.q(0));
                x xVarN3 = x.n(rVar.q(1));
                r rVarN = r.n(rVar.q(3));
                this.currentSpec = new p(n.o(xVarN2, false).p(), n.o(xVarN3, false).p(), j.n(rVar.q(2)).q().intValue(), j.n(rVarN.q(0)).q().intValue(), n.n(rVarN.q(1)).p());
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new IOException("Not a valid IES Parameter encoding.");
        } catch (ClassCastException unused2) {
            throw new IOException("Not a valid IES Parameter encoding.");
        }
    }

    @Override // java.security.AlgorithmParametersSpi
    public void engineInit(byte[] bArr, String str) throws IOException {
        if (isASN1FormatString(str) || str.equalsIgnoreCase("X.509")) {
            engineInit(bArr);
            return;
        }
        throw new IOException("Unknown parameter format " + str);
    }

    @Override // java.security.AlgorithmParametersSpi
    public String engineToString() {
        return "IES Parameters";
    }

    public boolean isASN1FormatString(String str) {
        return str == null || str.equals("ASN.1");
    }

    public AlgorithmParameterSpec localEngineGetParameterSpec(Class cls) throws InvalidParameterSpecException {
        if (cls == p.class || cls == AlgorithmParameterSpec.class) {
            return this.currentSpec;
        }
        throw new InvalidParameterSpecException("unknown parameter spec passed to ElGamal parameters object.");
    }
}
