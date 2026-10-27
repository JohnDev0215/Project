package org.bouncycastle.jcajce.spec;

import g.a.b.j0.i;
import g.a.b.j0.n;
import java.math.BigInteger;
import javax.crypto.spec.DHParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class DHDomainParameterSpec extends DHParameterSpec {
    private final BigInteger j;
    private final int m;
    private final BigInteger q;
    private n validationParameters;

    public DHDomainParameterSpec(i iVar) {
        this(iVar.f(), iVar.g(), iVar.b(), iVar.c(), iVar.e(), iVar.d());
        this.validationParameters = iVar.h();
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, null, 0);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, null, i2);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i2) {
        this(bigInteger, bigInteger2, bigInteger3, bigInteger4, 0, i2);
    }

    public DHDomainParameterSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, int i2, int i3) {
        super(bigInteger, bigInteger3, i3);
        this.q = bigInteger2;
        this.j = bigInteger4;
        this.m = i2;
    }

    public i getDomainParameters() {
        return new i(getP(), getG(), this.q, this.m, getL(), this.j, this.validationParameters);
    }

    public BigInteger getJ() {
        return this.j;
    }

    public int getM() {
        return this.m;
    }

    public BigInteger getQ() {
        return this.q;
    }
}
