package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.m;
import g.a.a.q;
import g.a.a.v2.c;
import g.a.a.w2.g;
import g.a.a.w2.h;
import g.a.a.w2.n;
import g.a.a.w2.p;
import g.a.a.w2.v;
import g.a.c.b;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jcajce.util.JcaJceHelper;

/* JADX INFO: loaded from: classes.dex */
public class X509CRLObject extends X509CRL {
    private JcaJceHelper bcHelper;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private h f5952c;
    private int hashCodeValue;
    private boolean isHashCodeSet = false;
    private boolean isIndirect;
    private String sigAlgName;
    private byte[] sigAlgParams;

    public X509CRLObject(JcaJceHelper jcaJceHelper, h hVar) throws CRLException {
        this.bcHelper = jcaJceHelper;
        this.f5952c = hVar;
        try {
            this.sigAlgName = X509SignatureUtil.getSignatureName(hVar.l());
            if (hVar.l().j() != null) {
                this.sigAlgParams = hVar.l().j().c().f("DER");
            } else {
                this.sigAlgParams = null;
            }
            this.isIndirect = isIndirectCRL(this);
        } catch (Exception e2) {
            throw new CRLException("CRL contents invalid: " + e2);
        }
    }

    private void doVerify(PublicKey publicKey, Signature signature) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, CRLException {
        if (!this.f5952c.l().equals(this.f5952c.m().l())) {
            throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
        }
        byte[] bArr = this.sigAlgParams;
        if (bArr != null) {
            try {
                X509SignatureUtil.setSignatureParameters(signature, q.j(bArr));
            } catch (IOException e2) {
                throw new SignatureException("cannot decode signature parameters: " + e2.getMessage());
            }
        }
        signature.initVerify(publicKey);
        signature.update(getTBSCertList());
        if (!signature.verify(getSignature())) {
            throw new SignatureException("CRL does not verify with supplied public key.");
        }
    }

    private Set getExtensionOIDs(boolean z) {
        n nVarG;
        if (getVersion() != 2 || (nVarG = this.f5952c.m().g()) == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration enumerationI = nVarG.i();
        while (enumerationI.hasMoreElements()) {
            m mVar = (m) enumerationI.nextElement();
            if (z == nVarG.g(mVar).l()) {
                hashSet.add(mVar.r());
            }
        }
        return hashSet;
    }

    public static boolean isIndirectCRL(X509CRL x509crl) throws CRLException {
        try {
            byte[] extensionValue = x509crl.getExtensionValue(g.a.a.w2.m.L3.r());
            if (extensionValue != null) {
                if (g.a.a.w2.q.i(g.a.a.n.n(extensionValue).p()).j()) {
                    return true;
                }
            }
            return false;
        } catch (Exception e2) {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", e2);
        }
    }

    private Set loadCRLEntries() {
        g.a.a.w2.m mVarG;
        HashSet hashSet = new HashSet();
        Enumeration enumerationJ = this.f5952c.j();
        c cVarG = null;
        while (enumerationJ.hasMoreElements()) {
            v.b bVar = (v.b) enumerationJ.nextElement();
            hashSet.add(new X509CRLEntryObject(bVar, this.isIndirect, cVarG));
            if (this.isIndirect && bVar.k() && (mVarG = bVar.g().g(g.a.a.w2.m.M3)) != null) {
                cVarG = c.g(p.g(mVarG.k()).i()[0].h());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509CRL
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X509CRL)) {
            return false;
        }
        if (!(obj instanceof X509CRLObject)) {
            return super.equals(obj);
        }
        X509CRLObject x509CRLObject = (X509CRLObject) obj;
        if (this.isHashCodeSet && x509CRLObject.isHashCodeSet && x509CRLObject.hashCodeValue != this.hashCodeValue) {
            return false;
        }
        return this.f5952c.equals(x509CRLObject.f5952c);
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    @Override // java.security.cert.X509CRL
    public byte[] getEncoded() throws CRLException {
        try {
            return this.f5952c.f("DER");
        } catch (IOException e2) {
            throw new CRLException(e2.toString());
        }
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        g.a.a.w2.m mVarG;
        n nVarG = this.f5952c.m().g();
        if (nVarG == null || (mVarG = nVarG.g(new m(str))) == null) {
            return null;
        }
        try {
            return mVarG.i().e();
        } catch (Exception e2) {
            throw new IllegalStateException("error parsing " + e2.toString());
        }
    }

    @Override // java.security.cert.X509CRL
    public Principal getIssuerDN() {
        return new b(c.g(this.f5952c.h().c()));
    }

    @Override // java.security.cert.X509CRL
    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.f5952c.h().e());
        } catch (IOException unused) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    @Override // java.security.cert.X509CRL
    public Date getNextUpdate() {
        if (this.f5952c.i() != null) {
            return this.f5952c.i().g();
        }
        return null;
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    @Override // java.security.cert.X509CRL
    public X509CRLEntry getRevokedCertificate(BigInteger bigInteger) {
        g.a.a.w2.m mVarG;
        Enumeration enumerationJ = this.f5952c.j();
        c cVarG = null;
        while (enumerationJ.hasMoreElements()) {
            v.b bVar = (v.b) enumerationJ.nextElement();
            if (bigInteger.equals(bVar.j().q())) {
                return new X509CRLEntryObject(bVar, this.isIndirect, cVarG);
            }
            if (this.isIndirect && bVar.k() && (mVarG = bVar.g().g(g.a.a.w2.m.M3)) != null) {
                cVarG = c.g(p.g(mVarG.k()).i()[0].h());
            }
        }
        return null;
    }

    @Override // java.security.cert.X509CRL
    public Set getRevokedCertificates() {
        Set setLoadCRLEntries = loadCRLEntries();
        if (setLoadCRLEntries.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableSet(setLoadCRLEntries);
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgName() {
        return this.sigAlgName;
    }

    @Override // java.security.cert.X509CRL
    public String getSigAlgOID() {
        return this.f5952c.l().g().r();
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSigAlgParams() {
        byte[] bArr = this.sigAlgParams;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    @Override // java.security.cert.X509CRL
    public byte[] getSignature() {
        return this.f5952c.k().q();
    }

    @Override // java.security.cert.X509CRL
    public byte[] getTBSCertList() throws CRLException {
        try {
            return this.f5952c.m().f("DER");
        } catch (IOException e2) {
            throw new CRLException(e2.toString());
        }
    }

    @Override // java.security.cert.X509CRL
    public Date getThisUpdate() {
        return this.f5952c.n().g();
    }

    @Override // java.security.cert.X509CRL
    public int getVersion() {
        return this.f5952c.o();
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        if (criticalExtensionOIDs == null) {
            return false;
        }
        criticalExtensionOIDs.remove(g.a.a.w2.m.L3.r());
        criticalExtensionOIDs.remove(g.a.a.w2.m.K3.r());
        return !criticalExtensionOIDs.isEmpty();
    }

    @Override // java.security.cert.X509CRL
    public int hashCode() {
        if (!this.isHashCodeSet) {
            this.isHashCodeSet = true;
            this.hashCodeValue = super.hashCode();
        }
        return this.hashCodeValue;
    }

    @Override // java.security.cert.CRL
    public boolean isRevoked(Certificate certificate) {
        c cVarI;
        g.a.a.w2.m mVarG;
        if (!certificate.getType().equals("X.509")) {
            throw new IllegalArgumentException("X.509 CRL used with non X.509 Cert");
        }
        Enumeration enumerationJ = this.f5952c.j();
        c cVarH = this.f5952c.h();
        if (enumerationJ.hasMoreElements()) {
            X509Certificate x509Certificate = (X509Certificate) certificate;
            BigInteger serialNumber = x509Certificate.getSerialNumber();
            while (enumerationJ.hasMoreElements()) {
                v.b bVarH = v.b.h(enumerationJ.nextElement());
                if (this.isIndirect && bVarH.k() && (mVarG = bVarH.g().g(g.a.a.w2.m.M3)) != null) {
                    cVarH = c.g(p.g(mVarG.k()).i()[0].h());
                }
                if (bVarH.j().q().equals(serialNumber)) {
                    if (certificate instanceof X509Certificate) {
                        cVarI = c.g(x509Certificate.getIssuerX500Principal().getEncoded());
                    } else {
                        try {
                            cVarI = g.h(certificate.getEncoded()).i();
                        } catch (CertificateEncodingException e2) {
                            throw new IllegalArgumentException("Cannot process certificate: " + e2.getMessage());
                        }
                    }
                    return cVarH.equals(cVarI);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00c0  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:42:0x0182
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    @Override // java.security.cert.CRL
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.asymmetric.x509.X509CRLObject.toString():java.lang.String");
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, CRLException, NoSuchProviderException {
        Signature signature;
        try {
            signature = this.bcHelper.createSignature(getSigAlgName());
        } catch (Exception unused) {
            signature = Signature.getInstance(getSigAlgName());
        }
        doVerify(publicKey, signature);
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey, String str) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, CRLException, NoSuchProviderException {
        doVerify(publicKey, str != null ? Signature.getInstance(getSigAlgName(), str) : Signature.getInstance(getSigAlgName()));
    }

    @Override // java.security.cert.X509CRL
    public void verify(PublicKey publicKey, Provider provider) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, CRLException {
        doVerify(publicKey, provider != null ? Signature.getInstance(getSigAlgName(), provider) : Signature.getInstance(getSigAlgName()));
    }
}
