package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.i;
import g.a.a.m;
import g.a.a.n;
import g.a.a.o0;
import g.a.a.p;
import g.a.a.q;
import g.a.a.r;
import g.a.a.v0;
import g.a.a.w;
import g.a.a.w0;
import g.a.a.w2.a;
import g.a.a.w2.c;
import g.a.a.w2.g;
import g.a.a.w2.o;
import g.a.c.b;
import g.a.c.c.e;
import g.a.f.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jcajce.provider.asymmetric.util.PKCS12BagAttributeCarrierImpl;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class X509CertificateObject extends X509Certificate implements e {
    private e attrCarrier = new PKCS12BagAttributeCarrierImpl();
    private c basicConstraints;
    private JcaJceHelper bcHelper;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private g f5953c;
    private int hashValue;
    private boolean hashValueSet;
    private boolean[] keyUsage;

    public X509CertificateObject(JcaJceHelper jcaJceHelper, g gVar) throws CertificateParsingException {
        this.bcHelper = jcaJceHelper;
        this.f5953c = gVar;
        try {
            byte[] extensionBytes = getExtensionBytes("2.5.29.19");
            if (extensionBytes != null) {
                this.basicConstraints = c.g(q.j(extensionBytes));
            }
            try {
                byte[] extensionBytes2 = getExtensionBytes("2.5.29.15");
                if (extensionBytes2 == null) {
                    this.keyUsage = null;
                    return;
                }
                o0 o0VarT = o0.t(q.j(extensionBytes2));
                byte[] bArrP = o0VarT.p();
                int length = (bArrP.length * 8) - o0VarT.r();
                int i2 = 9;
                if (length >= 9) {
                    i2 = length;
                }
                this.keyUsage = new boolean[i2];
                for (int i3 = 0; i3 != length; i3++) {
                    this.keyUsage[i3] = (bArrP[i3 / 8] & (128 >>> (i3 % 8))) != 0;
                }
            } catch (Exception e2) {
                throw new CertificateParsingException("cannot construct KeyUsage: " + e2);
            }
        } catch (Exception e3) {
            throw new CertificateParsingException("cannot construct BasicConstraints: " + e3);
        }
    }

    private void checkSignature(PublicKey publicKey, Signature signature) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException {
        if (!isAlgIdEqual(this.f5953c.l(), this.f5953c.p().m())) {
            throw new CertificateException("signature algorithm in TBS cert not same as outer cert");
        }
        X509SignatureUtil.setSignatureParameters(signature, this.f5953c.l().j());
        signature.initVerify(publicKey);
        signature.update(getTBSCertificate());
        if (!signature.verify(getSignature())) {
            throw new SignatureException("certificate does not verify with supplied key");
        }
    }

    private static Collection getAlternativeNames(byte[] bArr) throws CertificateParsingException {
        String strD;
        if (bArr == null) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            Enumeration enumerationR = r.n(bArr).r();
            while (enumerationR.hasMoreElements()) {
                o oVarG = o.g(enumerationR.nextElement());
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(d.b(oVarG.i()));
                switch (oVarG.i()) {
                    case 0:
                    case 3:
                    case 5:
                        arrayList2.add(oVarG.e());
                        arrayList.add(Collections.unmodifiableList(arrayList2));
                        break;
                    case 1:
                    case 2:
                    case 6:
                        strD = ((w) oVarG.h()).d();
                        arrayList2.add(strD);
                        arrayList.add(Collections.unmodifiableList(arrayList2));
                        break;
                    case 4:
                        strD = g.a.a.v2.c.i(g.a.a.v2.e.d.T, oVarG.h()).toString();
                        arrayList2.add(strD);
                        arrayList.add(Collections.unmodifiableList(arrayList2));
                        break;
                    case 7:
                        try {
                            strD = InetAddress.getByAddress(n.n(oVarG.h()).p()).getHostAddress();
                            arrayList2.add(strD);
                            arrayList.add(Collections.unmodifiableList(arrayList2));
                        } catch (UnknownHostException unused) {
                        }
                        break;
                    case 8:
                        strD = m.s(oVarG.h()).r();
                        arrayList2.add(strD);
                        arrayList.add(Collections.unmodifiableList(arrayList2));
                        break;
                    default:
                        throw new IOException("Bad tag number: " + oVarG.i());
                }
            }
            if (arrayList.size() == 0) {
                return null;
            }
            return Collections.unmodifiableCollection(arrayList);
        } catch (Exception e2) {
            throw new CertificateParsingException(e2.getMessage());
        }
    }

    private byte[] getExtensionBytes(String str) {
        g.a.a.w2.m mVarG;
        g.a.a.w2.n nVarH = this.f5953c.p().h();
        if (nVarH == null || (mVarG = nVarH.g(new m(str))) == null) {
            return null;
        }
        return mVarG.i().p();
    }

    private boolean isAlgIdEqual(a aVar, a aVar2) {
        if (aVar.g().equals(aVar2.g())) {
            return aVar.j() == null ? aVar2.j() == null || aVar2.j().equals(w0.f4105c) : aVar2.j() == null ? aVar.j() == null || aVar.j().equals(w0.f4105c) : aVar.j().equals(aVar2.j());
        }
        return false;
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity() throws CertificateNotYetValidException, CertificateExpiredException {
        checkValidity(new Date());
    }

    @Override // java.security.cert.X509Certificate
    public void checkValidity(Date date) throws CertificateNotYetValidException, CertificateExpiredException {
        if (date.getTime() > getNotAfter().getTime()) {
            throw new CertificateExpiredException("certificate expired on " + this.f5953c.g().i());
        }
        if (date.getTime() >= getNotBefore().getTime()) {
            return;
        }
        throw new CertificateNotYetValidException("certificate not valid till " + this.f5953c.m().i());
    }

    @Override // java.security.cert.Certificate
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509CertificateObject)) {
            return super.equals(obj);
        }
        X509CertificateObject x509CertificateObject = (X509CertificateObject) obj;
        if (this.hashValueSet && x509CertificateObject.hashValueSet && this.hashValue != x509CertificateObject.hashValue) {
            return false;
        }
        return this.f5953c.equals(x509CertificateObject.f5953c);
    }

    @Override // g.a.c.c.e
    public g.a.a.e getBagAttribute(m mVar) {
        return this.attrCarrier.getBagAttribute(mVar);
    }

    @Override // g.a.c.c.e
    public Enumeration getBagAttributeKeys() {
        return this.attrCarrier.getBagAttributeKeys();
    }

    @Override // java.security.cert.X509Certificate
    public int getBasicConstraints() {
        c cVar = this.basicConstraints;
        if (cVar == null || !cVar.i()) {
            return -1;
        }
        if (this.basicConstraints.h() == null) {
            return Integer.MAX_VALUE;
        }
        return this.basicConstraints.h().intValue();
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        if (getVersion() != 3) {
            return null;
        }
        HashSet hashSet = new HashSet();
        g.a.a.w2.n nVarH = this.f5953c.p().h();
        if (nVarH == null) {
            return null;
        }
        Enumeration enumerationI = nVarH.i();
        while (enumerationI.hasMoreElements()) {
            m mVar = (m) enumerationI.nextElement();
            if (nVarH.g(mVar).l()) {
                hashSet.add(mVar.r());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.Certificate
    public byte[] getEncoded() throws CertificateEncodingException {
        try {
            return this.f5953c.f("DER");
        } catch (IOException e2) {
            throw new CertificateEncodingException(e2.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public List getExtendedKeyUsage() throws CertificateParsingException {
        byte[] extensionBytes = getExtensionBytes("2.5.29.37");
        if (extensionBytes == null) {
            return null;
        }
        try {
            r rVar = (r) new i(extensionBytes).M();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 != rVar.size(); i2++) {
                arrayList.add(((m) rVar.q(i2)).r());
            }
            return Collections.unmodifiableList(arrayList);
        } catch (Exception unused) {
            throw new CertificateParsingException("error processing extended key usage extension");
        }
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        g.a.a.w2.m mVarG;
        g.a.a.w2.n nVarH = this.f5953c.p().h();
        if (nVarH == null || (mVarG = nVarH.g(new m(str))) == null) {
            return null;
        }
        try {
            return mVarG.i().e();
        } catch (Exception e2) {
            throw new IllegalStateException("error parsing " + e2.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public Collection getIssuerAlternativeNames() throws CertificateParsingException {
        return getAlternativeNames(getExtensionBytes(g.a.a.w2.m.E3.r()));
    }

    @Override // java.security.cert.X509Certificate
    public Principal getIssuerDN() {
        try {
            return new b(g.a.a.v2.c.g(this.f5953c.i().e()));
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getIssuerUniqueID() {
        o0 o0VarK = this.f5953c.p().k();
        if (o0VarK == null) {
            return null;
        }
        byte[] bArrP = o0VarK.p();
        int length = (bArrP.length * 8) - o0VarK.r();
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 != length; i2++) {
            zArr[i2] = (bArrP[i2 / 8] & (128 >>> (i2 % 8))) != 0;
        }
        return zArr;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getIssuerX500Principal() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new p(byteArrayOutputStream).j(this.f5953c.i());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getKeyUsage() {
        return this.keyUsage;
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        if (getVersion() != 3) {
            return null;
        }
        HashSet hashSet = new HashSet();
        g.a.a.w2.n nVarH = this.f5953c.p().h();
        if (nVarH == null) {
            return null;
        }
        Enumeration enumerationI = nVarH.i();
        while (enumerationI.hasMoreElements()) {
            m mVar = (m) enumerationI.nextElement();
            if (!nVarH.g(mVar).l()) {
                hashSet.add(mVar.r());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotAfter() {
        return this.f5953c.g().g();
    }

    @Override // java.security.cert.X509Certificate
    public Date getNotBefore() {
        return this.f5953c.m().g();
    }

    @Override // java.security.cert.Certificate
    public PublicKey getPublicKey() {
        try {
            return BouncyCastleProvider.getPublicKey(this.f5953c.o());
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Certificate
    public BigInteger getSerialNumber() {
        return this.f5953c.j().q();
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgName() {
        return X509SignatureUtil.getSignatureName(this.f5953c.l());
    }

    @Override // java.security.cert.X509Certificate
    public String getSigAlgOID() {
        return this.f5953c.l().g().r();
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSigAlgParams() {
        if (this.f5953c.l().j() != null) {
            try {
                return this.f5953c.l().j().c().f("DER");
            } catch (IOException unused) {
            }
        }
        return null;
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getSignature() {
        return this.f5953c.k().q();
    }

    @Override // java.security.cert.X509Certificate
    public Collection getSubjectAlternativeNames() throws CertificateParsingException {
        return getAlternativeNames(getExtensionBytes(g.a.a.w2.m.y.r()));
    }

    @Override // java.security.cert.X509Certificate
    public Principal getSubjectDN() {
        return new b(g.a.a.v2.c.g(this.f5953c.n().c()));
    }

    @Override // java.security.cert.X509Certificate
    public boolean[] getSubjectUniqueID() {
        o0 o0VarQ = this.f5953c.p().q();
        if (o0VarQ == null) {
            return null;
        }
        byte[] bArrP = o0VarQ.p();
        int length = (bArrP.length * 8) - o0VarQ.r();
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 != length; i2++) {
            zArr[i2] = (bArrP[i2 / 8] & (128 >>> (i2 % 8))) != 0;
        }
        return zArr;
    }

    @Override // java.security.cert.X509Certificate
    public X500Principal getSubjectX500Principal() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new p(byteArrayOutputStream).j(this.f5953c.n());
            return new X500Principal(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509Certificate
    public byte[] getTBSCertificate() throws CertificateEncodingException {
        try {
            return this.f5953c.p().f("DER");
        } catch (IOException e2) {
            throw new CertificateEncodingException(e2.toString());
        }
    }

    @Override // java.security.cert.X509Certificate
    public int getVersion() {
        return this.f5953c.q();
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        g.a.a.w2.n nVarH;
        if (getVersion() != 3 || (nVarH = this.f5953c.p().h()) == null) {
            return false;
        }
        Enumeration enumerationI = nVarH.i();
        while (enumerationI.hasMoreElements()) {
            m mVar = (m) enumerationI.nextElement();
            if (!mVar.equals(g.a.a.w2.m.s) && !mVar.equals(g.a.a.w2.m.P3) && !mVar.equals(g.a.a.w2.m.Q3) && !mVar.equals(g.a.a.w2.m.V3) && !mVar.equals(g.a.a.w2.m.O3) && !mVar.equals(g.a.a.w2.m.L3) && !mVar.equals(g.a.a.w2.m.K3) && !mVar.equals(g.a.a.w2.m.S3) && !mVar.equals(g.a.a.w2.m.F3) && !mVar.equals(g.a.a.w2.m.y) && !mVar.equals(g.a.a.w2.m.N3) && nVarH.g(mVar).l()) {
                return true;
            }
        }
        return false;
    }

    @Override // java.security.cert.Certificate
    public synchronized int hashCode() {
        if (!this.hashValueSet) {
            this.hashValue = super.hashCode();
            this.hashValueSet = true;
        }
        return this.hashValue;
    }

    public int originalHashCode() {
        try {
            byte[] encoded = getEncoded();
            int i2 = 0;
            for (int i3 = 1; i3 < encoded.length; i3++) {
                i2 += encoded[i3] * i3;
            }
            return i2;
        } catch (CertificateEncodingException unused) {
            return 0;
        }
    }

    @Override // g.a.c.c.e
    public void setBagAttribute(m mVar, g.a.a.e eVar) {
        this.attrCarrier.setBagAttribute(mVar, eVar);
    }

    @Override // java.security.cert.Certificate
    public String toString() {
        Object gVar;
        StringBuffer stringBuffer = new StringBuffer();
        String strD = g.a.f.i.d();
        stringBuffer.append("  [0]         Version: ");
        stringBuffer.append(getVersion());
        stringBuffer.append(strD);
        stringBuffer.append("         SerialNumber: ");
        stringBuffer.append(getSerialNumber());
        stringBuffer.append(strD);
        stringBuffer.append("             IssuerDN: ");
        stringBuffer.append(getIssuerDN());
        stringBuffer.append(strD);
        stringBuffer.append("           Start Date: ");
        stringBuffer.append(getNotBefore());
        stringBuffer.append(strD);
        stringBuffer.append("           Final Date: ");
        stringBuffer.append(getNotAfter());
        stringBuffer.append(strD);
        stringBuffer.append("            SubjectDN: ");
        stringBuffer.append(getSubjectDN());
        stringBuffer.append(strD);
        stringBuffer.append("           Public Key: ");
        stringBuffer.append(getPublicKey());
        stringBuffer.append(strD);
        stringBuffer.append("  Signature Algorithm: ");
        stringBuffer.append(getSigAlgName());
        stringBuffer.append(strD);
        byte[] signature = getSignature();
        stringBuffer.append("            Signature: ");
        stringBuffer.append(new String(g.a.f.j.d.c(signature, 0, 20)));
        stringBuffer.append(strD);
        int i2 = 20;
        while (i2 < signature.length) {
            int length = signature.length - 20;
            stringBuffer.append("                       ");
            stringBuffer.append(i2 < length ? new String(g.a.f.j.d.c(signature, i2, 20)) : new String(g.a.f.j.d.c(signature, i2, signature.length - i2)));
            stringBuffer.append(strD);
            i2 += 20;
        }
        g.a.a.w2.n nVarH = this.f5953c.p().h();
        if (nVarH != null) {
            Enumeration enumerationI = nVarH.i();
            if (enumerationI.hasMoreElements()) {
                stringBuffer.append("       Extensions: \n");
            }
            while (enumerationI.hasMoreElements()) {
                m mVar = (m) enumerationI.nextElement();
                g.a.a.w2.m mVarG = nVarH.g(mVar);
                if (mVarG.i() != null) {
                    i iVar = new i(mVarG.i().p());
                    stringBuffer.append("                       critical(");
                    stringBuffer.append(mVarG.l());
                    stringBuffer.append(") ");
                    try {
                    } catch (Exception unused) {
                        stringBuffer.append(mVar.r());
                        stringBuffer.append(" value = ");
                        stringBuffer.append("*****");
                        stringBuffer.append(strD);
                    }
                    if (mVar.equals(g.a.a.w2.m.F3)) {
                        gVar = c.g(iVar.M());
                    } else if (mVar.equals(g.a.a.w2.m.s)) {
                        gVar = g.a.a.w2.r.g(iVar.M());
                    } else if (mVar.equals(g.a.a.k2.c.f3892b)) {
                        gVar = new g.a.a.k2.d((o0) iVar.M());
                    } else if (mVar.equals(g.a.a.k2.c.f3894d)) {
                        gVar = new g.a.a.k2.e((v0) iVar.M());
                    } else if (mVar.equals(g.a.a.k2.c.k)) {
                        gVar = new g.a.a.k2.g((v0) iVar.M());
                    } else {
                        stringBuffer.append(mVar.r());
                        stringBuffer.append(" value = ");
                        stringBuffer.append(g.a.a.u2.a.c(iVar.M()));
                        stringBuffer.append(strD);
                    }
                    stringBuffer.append(gVar);
                    stringBuffer.append(strD);
                }
                stringBuffer.append(strD);
            }
        }
        return stringBuffer.toString();
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        Signature signature;
        String signatureName = X509SignatureUtil.getSignatureName(this.f5953c.l());
        try {
            signature = this.bcHelper.createSignature(signatureName);
        } catch (Exception unused) {
            signature = Signature.getInstance(signatureName);
        }
        checkSignature(publicKey, signature);
    }

    @Override // java.security.cert.Certificate
    public final void verify(PublicKey publicKey, String str) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException, NoSuchProviderException {
        String signatureName = X509SignatureUtil.getSignatureName(this.f5953c.l());
        checkSignature(publicKey, str != null ? Signature.getInstance(signatureName, str) : Signature.getInstance(signatureName));
    }

    @Override // java.security.cert.X509Certificate, java.security.cert.Certificate
    public final void verify(PublicKey publicKey, Provider provider) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, CertificateException {
        String signatureName = X509SignatureUtil.getSignatureName(this.f5953c.l());
        checkSignature(publicKey, provider != null ? Signature.getInstance(signatureName, provider) : Signature.getInstance(signatureName));
    }
}
