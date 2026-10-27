package org.bouncycastle.jcajce.provider.asymmetric.gost;

import g.a.a.d2.f;
import g.a.a.m;
import g.a.a.r;
import g.a.a.w2.a;
import g.a.a.w2.u;
import g.a.a.y0;
import g.a.b.j0.m0;
import g.a.c.c.d;
import g.a.c.e.l;
import g.a.c.e.n;
import g.a.c.e.o;
import g.a.f.i;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import org.bouncycastle.jcajce.provider.asymmetric.util.KeyUtil;
import org.bouncycastle.jce.interfaces.GOST3410PublicKey;

/* JADX INFO: loaded from: classes.dex */
public class BCGOST3410PublicKey implements GOST3410PublicKey {
    public static final long serialVersionUID = -6251023343619275990L;
    private transient d gost3410Spec;
    private BigInteger y;

    public BCGOST3410PublicKey(u uVar) {
        f fVar = new f((r) uVar.h().j());
        try {
            byte[] bArrP = ((y0) uVar.k()).p();
            byte[] bArr = new byte[bArrP.length];
            for (int i2 = 0; i2 != bArrP.length; i2++) {
                bArr[i2] = bArrP[(bArrP.length - 1) - i2];
            }
            this.y = new BigInteger(1, bArr);
            this.gost3410Spec = l.e(fVar);
        } catch (IOException unused) {
            throw new IllegalArgumentException("invalid info structure in GOST3410 public key");
        }
    }

    public BCGOST3410PublicKey(m0 m0Var, l lVar) {
        this.y = m0Var.c();
        this.gost3410Spec = lVar;
    }

    public BCGOST3410PublicKey(o oVar) {
        this.y = oVar.d();
        this.gost3410Spec = new l(new n(oVar.b(), oVar.c(), oVar.a()));
    }

    public BCGOST3410PublicKey(BigInteger bigInteger, l lVar) {
        this.y = bigInteger;
        this.gost3410Spec = lVar;
    }

    public BCGOST3410PublicKey(GOST3410PublicKey gOST3410PublicKey) {
        this.y = gOST3410PublicKey.getY();
        this.gost3410Spec = gOST3410PublicKey.getParameters();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.gost3410Spec = new l(str, (String) objectInputStream.readObject(), (String) objectInputStream.readObject());
            return;
        }
        this.gost3410Spec = new l(new n((BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject(), (BigInteger) objectInputStream.readObject()));
        objectInputStream.readObject();
        objectInputStream.readObject();
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
        if (!(obj instanceof BCGOST3410PublicKey)) {
            return false;
        }
        BCGOST3410PublicKey bCGOST3410PublicKey = (BCGOST3410PublicKey) obj;
        return this.y.equals(bCGOST3410PublicKey.y) && this.gost3410Spec.equals(bCGOST3410PublicKey.gost3410Spec);
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return "GOST3410";
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        byte[] byteArray = getY().toByteArray();
        byte[] bArr = new byte[byteArray[0] == 0 ? byteArray.length - 1 : byteArray.length];
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = byteArray[(byteArray.length - 1) - i2];
        }
        try {
            d dVar = this.gost3410Spec;
            return KeyUtil.getEncodedSubjectPublicKeyInfo(dVar instanceof l ? dVar.d() != null ? new u(new a(g.a.a.d2.a.l, new f(new m(this.gost3410Spec.b()), new m(this.gost3410Spec.c()), new m(this.gost3410Spec.d()))), new y0(bArr)) : new u(new a(g.a.a.d2.a.l, new f(new m(this.gost3410Spec.b()), new m(this.gost3410Spec.c()))), new y0(bArr)) : new u(new a(g.a.a.d2.a.l), new y0(bArr)));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.Key
    public String getFormat() {
        return "X.509";
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PublicKey
    public d getParameters() {
        return this.gost3410Spec;
    }

    @Override // org.bouncycastle.jce.interfaces.GOST3410PublicKey
    public BigInteger getY() {
        return this.y;
    }

    public int hashCode() {
        return this.y.hashCode() ^ this.gost3410Spec.hashCode();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String strD = i.d();
        stringBuffer.append("GOST3410 Public Key");
        stringBuffer.append(strD);
        stringBuffer.append("            y: ");
        stringBuffer.append(getY().toString(16));
        stringBuffer.append(strD);
        return stringBuffer.toString();
    }
}
