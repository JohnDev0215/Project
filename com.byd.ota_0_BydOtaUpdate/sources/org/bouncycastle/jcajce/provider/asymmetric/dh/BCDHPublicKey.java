package org.bouncycastle.jcajce.provider.asymmetric.dh;

import g.a.a.j;
import g.a.a.m;
import g.a.a.p2.d;
import g.a.a.p2.n;
import g.a.a.r;
import g.a.a.w2.u;
import g.a.a.x2.a;
import g.a.a.x2.c;
import g.a.b.j0.i;
import g.a.b.j0.k;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BCDHPublicKey implements DHPublicKey {
    public static final long serialVersionUID = -216691575254424324L;
    private transient k dhPublicKey;
    private transient DHParameterSpec dhSpec;
    private transient u info;
    private BigInteger y;

    public BCDHPublicKey(u uVar) {
        this.info = uVar;
        try {
            this.y = ((j) uVar.k()).q();
            r rVarN = r.n(uVar.g().j());
            m mVarG = uVar.g().g();
            if (mVarG.equals(n.w) || isPKCSParam(rVarN)) {
                d dVarH = d.h(rVarN);
                this.dhSpec = dVarH.i() != null ? new DHParameterSpec(dVarH.j(), dVarH.g(), dVarH.i().intValue()) : new DHParameterSpec(dVarH.j(), dVarH.g());
                this.dhPublicKey = new k(this.y, new i(this.dhSpec.getP(), this.dhSpec.getG()));
            } else {
                if (!mVarG.equals(g.a.a.x2.m.q3)) {
                    throw new IllegalArgumentException("unknown algorithm type: " + mVarG);
                }
                a aVarH = a.h(rVarN);
                c cVarM = aVarH.m();
                if (cVarM != null) {
                    this.dhPublicKey = new k(this.y, new i(aVarH.k(), aVarH.g(), aVarH.l(), aVarH.i(), new g.a.b.j0.n(cVarM.i(), cVarM.h().intValue())));
                } else {
                    this.dhPublicKey = new k(this.y, new i(aVarH.k(), aVarH.g(), aVarH.l(), aVarH.i(), null));
                }
                this.dhSpec = new DHDomainParameterSpec(this.dhPublicKey.b());
            }
        } catch (IOException unused) {
            throw new IllegalArgumentException("invalid info structure in DH public key");
        }
    }

    public BCDHPublicKey(k kVar) {
        this.y = kVar.c();
        this.dhSpec = new DHDomainParameterSpec(kVar.b());
        this.dhPublicKey = kVar;
    }

    public BCDHPublicKey(BigInteger bigInteger, DHParameterSpec dHParameterSpec) {
        this.y = bigInteger;
        this.dhSpec = dHParameterSpec;
        this.dhPublicKey = dHParameterSpec instanceof DHDomainParameterSpec ? new k(bigInteger, ((DHDomainParameterSpec) dHParameterSpec).getDomainParameters()) : new k(bigInteger, new i(dHParameterSpec.getP(), dHParameterSpec.getG()));
    }

    public BCDHPublicKey(DHPublicKey dHPublicKey) {
        this.y = dHPublicKey.getY();
        this.dhSpec = dHPublicKey.getParams();
        this.dhPublicKey = new k(this.y, new i(this.dhSpec.getP(), this.dhSpec.getG()));
    }

    public BCDHPublicKey(DHPublicKeySpec dHPublicKeySpec) {
        this.y = dHPublicKeySpec.getY();
        this.dhSpec = new DHParameterSpec(dHPublicKeySpec.getP(), dHPublicKeySpec.getG());
        this.dhPublicKey = new k(this.y, new i(dHPublicKeySpec.getP(), dHPublicKeySpec.getG()));
    }

    private boolean isPKCSParam(r rVar) {
        if (rVar.size() == 2) {
            return true;
        }
        if (rVar.size() > 3) {
            return false;
        }
        return j.n(rVar.q(2)).q().compareTo(BigInteger.valueOf((long) j.n(rVar.q(0)).q().bitLength())) <= 0;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.dhSpec = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
        this.info = null;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.dhSpec.getP());
        objectOutputStream.writeObject(this.dhSpec.getG());
        objectOutputStream.writeInt(this.dhSpec.getL());
    }

    public k engineGetKeyParameters() {
        return this.dhPublicKey;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPublicKey)) {
            return false;
        }
        DHPublicKey dHPublicKey = (DHPublicKey) obj;
        return getY().equals(dHPublicKey.getY()) && getParams().getG().equals(dHPublicKey.getParams().getG()) && getParams().getP().equals(dHPublicKey.getParams().getP()) && getParams().getL() == dHPublicKey.getParams().getL();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DH";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        u uVar = this.info;
        if (uVar != null) {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(uVar);
        }
        DHParameterSpec dHParameterSpec = this.dhSpec;
        if (!(dHParameterSpec instanceof DHDomainParameterSpec) || ((DHDomainParameterSpec) dHParameterSpec).getQ() == null) {
            return KeyUtil.getEncodedSubjectPublicKeyInfo(new g.a.a.w2.a(n.w, new d(this.dhSpec.getP(), this.dhSpec.getG(), this.dhSpec.getL()).c()), new j(this.y));
        }
        i domainParameters = ((DHDomainParameterSpec) this.dhSpec).getDomainParameters();
        g.a.b.j0.n nVarH = domainParameters.h();
        return KeyUtil.getEncodedSubjectPublicKeyInfo(new g.a.a.w2.a(g.a.a.x2.m.q3, new a(domainParameters.f(), domainParameters.b(), domainParameters.g(), domainParameters.c(), nVarH == null ? new c(nVarH.b(), nVarH.a()) : null).c()), new j(this.y));
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        return this.dhSpec;
    }

    @Override // javax.crypto.interfaces.DHPublicKey
    public BigInteger getY() {
        return this.y;
    }

    public int hashCode() {
        return ((getY().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }
}
