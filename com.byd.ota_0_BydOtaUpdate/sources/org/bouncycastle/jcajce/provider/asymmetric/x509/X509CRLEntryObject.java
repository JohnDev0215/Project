package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.g;
import g.a.a.u2.a;
import g.a.a.v2.c;
import g.a.a.w2.f;
import g.a.a.w2.m;
import g.a.a.w2.n;
import g.a.a.w2.o;
import g.a.a.w2.p;
import g.a.a.w2.v;
import g.a.f.i;
import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public class X509CRLEntryObject extends X509CRLEntry {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private v.b f5951c;
    private c certificateIssuer;
    private int hashValue;
    private boolean isHashValueSet;

    public X509CRLEntryObject(v.b bVar) {
        this.f5951c = bVar;
        this.certificateIssuer = null;
    }

    public X509CRLEntryObject(v.b bVar, boolean z, c cVar) {
        this.f5951c = bVar;
        this.certificateIssuer = loadCertificateIssuer(z, cVar);
    }

    private m getExtension(g.a.a.m mVar) {
        n nVarG = this.f5951c.g();
        if (nVarG != null) {
            return nVarG.g(mVar);
        }
        return null;
    }

    private Set getExtensionOIDs(boolean z) {
        n nVarG = this.f5951c.g();
        if (nVarG == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration enumerationI = nVarG.i();
        while (enumerationI.hasMoreElements()) {
            g.a.a.m mVar = (g.a.a.m) enumerationI.nextElement();
            if (z == nVarG.g(mVar).l()) {
                hashSet.add(mVar.r());
            }
        }
        return hashSet;
    }

    private c loadCertificateIssuer(boolean z, c cVar) {
        o[] oVarArrI;
        int i2;
        if (!z) {
            return null;
        }
        m extension = getExtension(m.M3);
        if (extension == null) {
            return cVar;
        }
        try {
            oVarArrI = p.g(extension.k()).i();
        } catch (Exception unused) {
        }
        for (i2 = 0; i2 < oVarArrI.length; i2++) {
            if (oVarArrI[i2].i() == 4) {
                return c.g(oVarArrI[i2].h());
            }
            return null;
        }
        return null;
    }

    @Override // java.security.cert.X509CRLEntry
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof X509CRLEntryObject ? this.f5951c.equals(((X509CRLEntryObject) obj).f5951c) : super.equals(this);
    }

    @Override // java.security.cert.X509CRLEntry
    public X500Principal getCertificateIssuer() {
        if (this.certificateIssuer == null) {
            return null;
        }
        try {
            return new X500Principal(this.certificateIssuer.e());
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    @Override // java.security.cert.X509CRLEntry
    public byte[] getEncoded() throws CRLException {
        try {
            return this.f5951c.f("DER");
        } catch (IOException e2) {
            throw new CRLException(e2.toString());
        }
    }

    @Override // java.security.cert.X509Extension
    public byte[] getExtensionValue(String str) {
        m extension = getExtension(new g.a.a.m(str));
        if (extension == null) {
            return null;
        }
        try {
            return extension.i().e();
        } catch (Exception e2) {
            throw new IllegalStateException("Exception encoding: " + e2.toString());
        }
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    @Override // java.security.cert.X509CRLEntry
    public Date getRevocationDate() {
        return this.f5951c.i().g();
    }

    @Override // java.security.cert.X509CRLEntry
    public BigInteger getSerialNumber() {
        return this.f5951c.j().q();
    }

    @Override // java.security.cert.X509CRLEntry
    public boolean hasExtensions() {
        return this.f5951c.g() != null;
    }

    @Override // java.security.cert.X509Extension
    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return (criticalExtensionOIDs == null || criticalExtensionOIDs.isEmpty()) ? false : true;
    }

    @Override // java.security.cert.X509CRLEntry
    public int hashCode() {
        if (!this.isHashValueSet) {
            this.hashValue = super.hashCode();
            this.isHashValueSet = true;
        }
        return this.hashValue;
    }

    @Override // java.security.cert.X509CRLEntry
    public String toString() {
        Object objG;
        StringBuffer stringBuffer = new StringBuffer();
        String strD = i.d();
        stringBuffer.append("      userCertificate: ");
        stringBuffer.append(getSerialNumber());
        stringBuffer.append(strD);
        stringBuffer.append("       revocationDate: ");
        stringBuffer.append(getRevocationDate());
        stringBuffer.append(strD);
        stringBuffer.append("       certificateIssuer: ");
        stringBuffer.append(getCertificateIssuer());
        stringBuffer.append(strD);
        n nVarG = this.f5951c.g();
        if (nVarG != null) {
            Enumeration enumerationI = nVarG.i();
            if (enumerationI.hasMoreElements()) {
                String str = "   crlEntryExtensions:";
                loop0: while (true) {
                    stringBuffer.append(str);
                    while (true) {
                        stringBuffer.append(strD);
                        while (enumerationI.hasMoreElements()) {
                            g.a.a.m mVar = (g.a.a.m) enumerationI.nextElement();
                            m mVarG = nVarG.g(mVar);
                            if (mVarG.i() != null) {
                                g.a.a.i iVar = new g.a.a.i(mVarG.i().p());
                                stringBuffer.append("                       critical(");
                                stringBuffer.append(mVarG.l());
                                stringBuffer.append(") ");
                                try {
                                    if (mVar.equals(m.H3)) {
                                        objG = f.g(g.o(iVar.M()));
                                    } else if (mVar.equals(m.M3)) {
                                        stringBuffer.append("Certificate issuer: ");
                                        objG = p.g(iVar.M());
                                    } else {
                                        stringBuffer.append(mVar.r());
                                        stringBuffer.append(" value = ");
                                        stringBuffer.append(a.c(iVar.M()));
                                        stringBuffer.append(strD);
                                    }
                                    stringBuffer.append(objG);
                                    stringBuffer.append(strD);
                                } catch (Exception unused) {
                                    stringBuffer.append(mVar.r());
                                    stringBuffer.append(" value = ");
                                    str = "*****";
                                }
                            }
                        }
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
