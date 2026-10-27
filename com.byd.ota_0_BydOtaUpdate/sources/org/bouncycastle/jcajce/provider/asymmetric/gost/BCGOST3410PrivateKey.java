package org.bouncycastle.jcajce.provider.asymmetric.gost;

import g.a.a.d2.f;
import g.a.a.j;
import g.a.a.n;
import g.a.a.p2.p;
import g.a.a.w2.a;
import g.a.a.y0;
import g.a.b.j0.l0;
import g.a.c.c.d;
import g.a.c.c.e;
import g.a.c.e.l;
import g.a.c.e.m;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jce.interfaces.GOST3410PrivateKey;

/* JADX INFO: loaded from: classes.dex */
public class BCGOST3410PrivateKey implements GOST3410PrivateKey, e {
    public static final long serialVersionUID = 8581661527592305464L;
    private transient e attrCarrier = new PKCS12BagAttributeCarrierImpl();
    private transient d gost3410Spec;
    private BigInteger x;

    public BCGOST3410PrivateKey() {
    }

    public BCGOST3410PrivateKey(p pVar) throws IOException {
        BigInteger bigInteger;
        f fVarI = f.i(pVar.h().j());
        g.a.a.e eVarI = pVar.i();
        if (eVarI instanceof j) {
            bigInteger = j.n(eVarI).p();
        } else {
            byte[] bArrP = n.n(pVar.i()).p();
            byte[] bArr = new byte[bArrP.length];
            for (int i2 = 0; i2 != bArrP.length; i2++) {
                bArr[i2] = bArrP[(bArrP.length - 1) - i2];
            }
            bigInteger = new BigInteger(1, bArr);
        }
        this.x = bigInteger;
        this.gost3410Spec = l.e(fVarI);
    }

    public BCGOST3410PrivateKey(l0 l0Var, l lVar) {
        this.x = l0Var.c();
        this.gost3410Spec = lVar;
        if (lVar == null) {
            throw new IllegalArgumentException("spec is null");
        }
    }

    public BCGOST3410PrivateKey(m mVar) {
        this.x = mVar.d();
        this.gost3410Spec = new l(new g.a.c.e.n(mVar.b(), mVar.c(), mVar.a()));
    }

    public BCGOST3410PrivateKey(GOST3410PrivateKey gOST3410PrivateKey) {
        this.x = gOST3410PrivateKey.getX();
        this.gost3410Spec = gOST3410PrivateKey.getParameters();
    }

    private boolean compareObj(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.gost3410Spec = new l(str, (String) objectInputStream.readObject(), (String) objectInputStream.readObject());
        } else {
            this.gost3410Spec = new l(new g.a.c.e.n((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject()));
            objectInputStream.readObject();
            objectInputStream.readObject();
        }
        this.attrCarrier = new PKCS12BagAttributeCarrierImpl();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        Object objA;
        objectOutputStream.defaultWriteObject();
        if (this.gost3410Spec.b() != null) {
            objA = this.gost3410Spec.b();
        } else {
            objectOutputStream.writeObject(null);
            objectOutputStream.writeObject(this.gost3410Spec.a().b());
            objectOutputStream.writeObject(this.gost3410Spec.a().c());
            objA = this.gost3410Spec.a().a();
        }
        objectOutputStream.writeObject(objA);
        objectOutputStream.writeObject(this.gost3410Spec.c());
        objectOutputStream.writeObject(this.gost3410Spec.d());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410PrivateKey)) {
            return false;
        }
        GOST3410PrivateKey gOST3410PrivateKey = (GOST3410PrivateKey) obj;
        return getX().equals(gOST3410PrivateKey.getX()) && getParameters().a().equals(gOST3410PrivateKey.getParameters().a()) && getParameters().c().equals(gOST3410PrivateKey.getParameters().c()) && compareObj(getParameters().d(), gOST3410PrivateKey.getParameters().d());
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GOST3410";
    }

    @Override // g.a.c.c.e
    public g.a.a.e getBagAttribute(g.a.a.m mVar) {
        return this.attrCarrier.getBagAttribute(mVar);
    }

    @Override // g.a.c.c.e
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] byteArray = getX().toByteArray();
        byte[] bArr = new byte[byteArray[0] == 0 ? byteArray.length - 1 : byteArray.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = byteArray[(byteArray.length - 1) - i2];
        }
        try {
            return (this.gost3410Spec instanceof l ? new p(new a(g.a.a.d2.a.l, new f(new g.a.a.m(this.gost3410Spec.b()), new g.a.a.m(this.gost3410Spec.c()))), new y0(bArr)) : new p(new a(g.a.a.d2.a.l), new y0(bArr))).f("DER");
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "PKCS#8";
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PrivateKey
    public d getParameters() {
        return this.gost3410Spec;
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PrivateKey
    public BigInteger getX() {
        return this.x;
    }

    public int hashCode() {
        return getX().hashCode() ^ this.gost3410Spec.hashCode();
    }

    @Override // g.a.c.c.e
    public void setBagAttribute(g.a.a.m mVar, g.a.a.e eVar) {
        this.attrCarrier.setBagAttribute(mVar, eVar);
    }
}
