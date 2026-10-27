package org.bouncycastle.jcajce.provider.asymmetric.dh;

import g.a.a.m;
import g.a.a.p2.d;
import g.a.a.p2.n;
import g.a.a.p2.p;
import g.a.a.r;
import g.a.a.x2.a;
import g.a.a.x2.c;
import g.a.b.j0.i;
import g.a.b.j0.j;
import g.a.c.c.e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPrivateKeySpec;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jcajce.spec.DHDomainParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class BCDHPrivateKey implements DHPrivateKey, e {
    public static final long serialVersionUID = 311058815616901812L;
    private transient PKCS12BagAttributeCarrierImpl attrCarrier = new PKCS12BagAttributeCarrierImpl();
    private transient j dhPrivateKey;
    private transient DHParameterSpec dhSpec;
    private transient p info;
    private BigInteger x;

    public BCDHPrivateKey() {
    }

    public BCDHPrivateKey(p pVar) throws IOException {
        j jVar;
        r rVarN = r.n(pVar.h().j());
        g.a.a.j jVar2 = (g.a.a.j) pVar.i();
        m mVarG = pVar.h().g();
        this.info = pVar;
        this.x = jVar2.q();
        if (mVarG.equals(n.w)) {
            d dVarH = d.h(rVarN);
            if (dVarH.i() != null) {
                this.dhSpec = new DHParameterSpec(dVarH.j(), dVarH.g(), dVarH.i().intValue());
                jVar = new j(this.x, new i(dVarH.j(), dVarH.g(), null, dVarH.i().intValue()));
            } else {
                this.dhSpec = new DHParameterSpec(dVarH.j(), dVarH.g());
                jVar = new j(this.x, new i(dVarH.j(), dVarH.g()));
            }
        } else {
            if (!mVarG.equals(g.a.a.x2.m.q3)) {
                throw new IllegalArgumentException("unknown algorithm type: " + mVarG);
            }
            a aVarH = a.h(rVarN);
            this.dhSpec = new DHDomainParameterSpec(aVarH.k(), aVarH.l(), aVarH.g(), aVarH.i(), 0);
            jVar = new j(this.x, new i(aVarH.k(), aVarH.g(), aVarH.l(), aVarH.i(), null));
        }
        this.dhPrivateKey = jVar;
    }

    public BCDHPrivateKey(j jVar) {
        this.x = jVar.c();
        this.dhSpec = new DHDomainParameterSpec(jVar.b());
    }

    public BCDHPrivateKey(DHPrivateKey dHPrivateKey) {
        this.x = dHPrivateKey.getX();
        this.dhSpec = dHPrivateKey.getParams();
    }

    public BCDHPrivateKey(DHPrivateKeySpec dHPrivateKeySpec) {
        this.x = dHPrivateKeySpec.getX();
        this.dhSpec = new DHParameterSpec(dHPrivateKeySpec.getP(), dHPrivateKeySpec.getG());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.dhSpec = new DHParameterSpec((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), objectInputStream.readInt());
        this.info = null;
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.dhSpec.getP());
        objectOutputStream.writeObject(this.dhSpec.getG());
        objectOutputStream.writeInt(this.dhSpec.getL());
    }

    public j engineGetKeyParameters() {
        j jVar = this.dhPrivateKey;
        if (jVar != null) {
            return jVar;
        }
        DHParameterSpec dHParameterSpec = this.dhSpec;
        return dHParameterSpec instanceof DHDomainParameterSpec ? new j(this.x, ((DHDomainParameterSpec) dHParameterSpec).getDomainParameters()) : new j(this.x, new i(dHParameterSpec.getP(), this.dhSpec.getG(), null, this.dhSpec.getL()));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DHPrivateKey)) {
            return false;
        }
        DHPrivateKey dHPrivateKey = (DHPrivateKey) obj;
        return getX().equals(dHPrivateKey.getX()) && getParams().getG().equals(dHPrivateKey.getParams().getG()) && getParams().getP().equals(dHPrivateKey.getParams().getP()) && getParams().getL() == dHPrivateKey.getParams().getL();
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "DH";
    }

    @Override // g.a.c.c.e
    public g.a.a.e getBagAttribute(m mVar) {
        return this.attrCarrier.getBagAttribute(mVar);
    }

    @Override // g.a.c.c.e
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        p pVar;
        try {
            p pVar2 = this.info;
            if (pVar2 != null) {
                return pVar2.f("DER");
            }
            DHParameterSpec dHParameterSpec = this.dhSpec;
            if (!(dHParameterSpec instanceof DHDomainParameterSpec) || ((DHDomainParameterSpec) dHParameterSpec).getQ() == null) {
                pVar = new p(new g.a.a.w2.a(n.w, new d(this.dhSpec.getP(), this.dhSpec.getG(), this.dhSpec.getL()).c()), new g.a.a.j(getX()));
            } else {
                i domainParameters = ((DHDomainParameterSpec) this.dhSpec).getDomainParameters();
                g.a.b.j0.n nVarH = domainParameters.h();
                pVar = new p(new g.a.a.w2.a(g.a.a.x2.m.q3, new a(domainParameters.f(), domainParameters.b(), domainParameters.g(), domainParameters.c(), nVarH == null ? new c(nVarH.b(), nVarH.a()) : null).c()), new g.a.a.j(getX()));
            }
            return pVar.f("DER");
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // javax.crypto.interfaces.DHKey
    public DHParameterSpec getParams() {
        return this.dhSpec;
    }

    @Override // javax.crypto.interfaces.DHPrivateKey
    public BigInteger getX() {
        return this.x;
    }

    public int hashCode() {
        return ((getX().hashCode() ^ getParams().getG().hashCode()) ^ getParams().getP().hashCode()) ^ getParams().getL();
    }

    @Override // g.a.c.c.e
    public void setBagAttribute(m mVar, g.a.a.e eVar) {
        this.attrCarrier.setBagAttribute(mVar, eVar);
    }
}
