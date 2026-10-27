package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.e;
import g.a.a.i;
import g.a.a.m;
import g.a.a.p2.n;
import g.a.a.p2.w;
import g.a.a.r;
import g.a.a.t;
import g.a.a.w2.g;
import g.a.a.w2.h;
import g.a.a.x;
import g.a.f.k.a;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;

/* JADX INFO: loaded from: classes.dex */
public class CertificateFactory extends CertificateFactorySpi {
    private static final PEMUtil PEM_CERT_PARSER = new PEMUtil("CERTIFICATE");
    private static final PEMUtil PEM_CRL_PARSER = new PEMUtil("CRL");
    private static final PEMUtil PEM_PKCS7_PARSER = new PEMUtil("PKCS7");
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private t sData = null;
    private int sDataObjectCount = 0;
    private InputStream currentStream = null;
    private t sCrlData = null;
    private int sCrlDataObjectCount = 0;
    private InputStream currentCrlStream = null;

    public class ExCertificateException extends CertificateException {
        private Throwable cause;

        public ExCertificateException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        public ExCertificateException(Throwable th) {
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    private CRL getCRL() throws CRLException {
        t tVar = this.sCrlData;
        if (tVar == null || this.sCrlDataObjectCount >= tVar.size()) {
            return null;
        }
        t tVar2 = this.sCrlData;
        int i2 = this.sCrlDataObjectCount;
        this.sCrlDataObjectCount = i2 + 1;
        return createCRL(h.g(tVar2.r(i2)));
    }

    private CRL getCRL(r rVar) throws CRLException {
        if (rVar == null) {
            return null;
        }
        if (rVar.size() <= 1 || !(rVar.q(0) instanceof m) || !rVar.q(0).equals(n.X)) {
            return createCRL(h.g(rVar));
        }
        this.sCrlData = w.i(r.o((x) rVar.q(1), true)).g();
        return getCRL();
    }

    private Certificate getCertificate() throws CertificateParsingException {
        if (this.sData == null) {
            return null;
        }
        while (this.sDataObjectCount < this.sData.size()) {
            t tVar = this.sData;
            int i2 = this.sDataObjectCount;
            this.sDataObjectCount = i2 + 1;
            e eVarR = tVar.r(i2);
            if (eVarR instanceof r) {
                return new X509CertificateObject(this.bcHelper, g.h(eVarR));
            }
        }
        return null;
    }

    private Certificate getCertificate(r rVar) throws CertificateParsingException {
        if (rVar == null) {
            return null;
        }
        if (rVar.size() <= 1 || !(rVar.q(0) instanceof m) || !rVar.q(0).equals(n.X)) {
            return new X509CertificateObject(this.bcHelper, g.h(rVar));
        }
        this.sData = w.i(r.o((x) rVar.q(1), true)).h();
        return getCertificate();
    }

    private CRL readDERCRL(i iVar) throws IOException, CRLException {
        return getCRL(r.n(iVar.M()));
    }

    private Certificate readDERCertificate(i iVar) throws CertificateParsingException, IOException {
        return getCertificate(r.n(iVar.M()));
    }

    private CRL readPEMCRL(InputStream inputStream) throws IOException, CRLException {
        return getCRL(PEM_CRL_PARSER.readPEMObject(inputStream));
    }

    private Certificate readPEMCertificate(InputStream inputStream) throws CertificateParsingException, IOException {
        return getCertificate(PEM_CERT_PARSER.readPEMObject(inputStream));
    }

    public CRL createCRL(h hVar) throws CRLException {
        return new X509CRLObject(this.bcHelper, hVar);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        InputStream inputStream2 = this.currentCrlStream;
        if (inputStream2 == null || inputStream2 != inputStream) {
            this.currentCrlStream = inputStream;
            this.sCrlData = null;
            this.sCrlDataObjectCount = 0;
        }
        try {
            t tVar = this.sCrlData;
            if (tVar != null) {
                if (this.sCrlDataObjectCount != tVar.size()) {
                    return getCRL();
                }
                this.sCrlData = null;
                this.sCrlDataObjectCount = 0;
                return null;
            }
            if (!inputStream.markSupported()) {
                inputStream = new ByteArrayInputStream(a.b(inputStream));
            }
            inputStream.mark(1);
            int i2 = inputStream.read();
            if (i2 == -1) {
                return null;
            }
            inputStream.reset();
            return i2 != 48 ? readPEMCRL(inputStream) : readDERCRL(new i(inputStream, true));
        } catch (CRLException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new CRLException(e3.toString());
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection engineGenerateCRLs(InputStream inputStream) throws CRLException {
        ArrayList arrayList = new ArrayList();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        while (true) {
            CRL crlEngineGenerateCRL = engineGenerateCRL(bufferedInputStream);
            if (crlEngineGenerateCRL == null) {
                return arrayList;
            }
            arrayList.add(crlEngineGenerateCRL);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream) throws CertificateException {
        return engineGenerateCertPath(inputStream, "PkiPath");
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream, String str) throws CertificateException {
        return new PKIXCertPath(inputStream, str);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(List list) throws CertificateException {
        for (Object obj : list) {
            if (obj != null && !(obj instanceof X509Certificate)) {
                throw new CertificateException("list contains non X509Certificate object while creating CertPath\n" + obj.toString());
            }
        }
        return new PKIXCertPath(list);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        InputStream inputStream2 = this.currentStream;
        if (inputStream2 == null || inputStream2 != inputStream) {
            this.currentStream = inputStream;
            this.sData = null;
            this.sDataObjectCount = 0;
        }
        try {
            t tVar = this.sData;
            if (tVar != null) {
                if (this.sDataObjectCount != tVar.size()) {
                    return getCertificate();
                }
                this.sData = null;
                this.sDataObjectCount = 0;
                return null;
            }
            if (!inputStream.markSupported()) {
                inputStream = new ByteArrayInputStream(a.b(inputStream));
            }
            inputStream.mark(1);
            int i2 = inputStream.read();
            if (i2 == -1) {
                return null;
            }
            inputStream.reset();
            return i2 != 48 ? readPEMCertificate(inputStream) : readDERCertificate(new i(inputStream));
        } catch (Exception e2) {
            throw new ExCertificateException("parsing issue: " + e2.getMessage(), e2);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ArrayList arrayList = new ArrayList();
        while (true) {
            Certificate certificateEngineGenerateCertificate = engineGenerateCertificate(bufferedInputStream);
            if (certificateEngineGenerateCertificate == null) {
                return arrayList;
            }
            arrayList.add(certificateEngineGenerateCertificate);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Iterator engineGetCertPathEncodings() {
        return PKIXCertPath.certPathEncodings.iterator();
    }
}
