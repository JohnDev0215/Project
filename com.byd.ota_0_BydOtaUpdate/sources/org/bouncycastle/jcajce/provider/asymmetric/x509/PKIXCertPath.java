package org.bouncycastle.jcajce.provider.asymmetric.x509;

import g.a.a.c1;
import g.a.a.e;
import g.a.a.e1;
import g.a.a.f;
import g.a.a.i;
import g.a.a.j;
import g.a.a.p2.c;
import g.a.a.p2.n;
import g.a.a.p2.w;
import g.a.a.q;
import g.a.a.r;
import g.a.f.k.c.b;
import g.a.f.k.c.d;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;

/* JADX INFO: loaded from: classes.dex */
public class PKIXCertPath extends CertPath {
    public static final List certPathEncodings;
    private List certificates;
    private final JcaJceHelper helper;

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add("PkiPath");
        arrayList.add("PEM");
        arrayList.add("PKCS7");
        certPathEncodings = Collections.unmodifiableList(arrayList);
    }

    public PKIXCertPath(InputStream inputStream, String str) throws CertificateException {
        super("X.509");
        BCJcaJceHelper bCJcaJceHelper = new BCJcaJceHelper();
        this.helper = bCJcaJceHelper;
        try {
            if (!str.equalsIgnoreCase("PkiPath")) {
                if (!str.equalsIgnoreCase("PKCS7") && !str.equalsIgnoreCase("PEM")) {
                    throw new CertificateException("unsupported encoding: " + str);
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                this.certificates = new ArrayList();
                java.security.cert.CertificateFactory certificateFactoryCreateCertificateFactory = bCJcaJceHelper.createCertificateFactory("X.509");
                while (true) {
                    Certificate certificateGenerateCertificate = certificateFactoryCreateCertificateFactory.generateCertificate(bufferedInputStream);
                    if (certificateGenerateCertificate == null) {
                        break;
                    } else {
                        this.certificates.add(certificateGenerateCertificate);
                    }
                }
            } else {
                q qVarM = new i(inputStream).M();
                if (!(qVarM instanceof r)) {
                    throw new CertificateException("input stream does not contain a ASN1 SEQUENCE while reading PkiPath encoded data to load CertPath");
                }
                Enumeration enumerationR = ((r) qVarM).r();
                this.certificates = new ArrayList();
                java.security.cert.CertificateFactory certificateFactoryCreateCertificateFactory2 = bCJcaJceHelper.createCertificateFactory("X.509");
                while (enumerationR.hasMoreElements()) {
                    this.certificates.add(0, certificateFactoryCreateCertificateFactory2.generateCertificate(new ByteArrayInputStream(((e) enumerationR.nextElement()).c().f("DER"))));
                }
            }
            this.certificates = sortCerts(this.certificates);
        } catch (IOException e2) {
            throw new CertificateException("IOException throw while decoding CertPath:\n" + e2.toString());
        } catch (NoSuchProviderException e3) {
            throw new CertificateException("BouncyCastle provider not found while trying to get a CertificateFactory:\n" + e3.toString());
        }
    }

    public PKIXCertPath(List list) {
        super("X.509");
        this.helper = new BCJcaJceHelper();
        this.certificates = sortCerts(new ArrayList(list));
    }

    private List sortCerts(List list) {
        boolean z;
        boolean z2;
        if (list.size() < 2) {
            return list;
        }
        X500Principal issuerX500Principal = ((X509Certificate) list.get(0)).getIssuerX500Principal();
        int i2 = 1;
        while (true) {
            if (i2 == list.size()) {
                z = true;
                break;
            }
            if (!issuerX500Principal.equals(((X509Certificate) list.get(i2)).getSubjectX500Principal())) {
                z = false;
                break;
            }
            issuerX500Principal = ((X509Certificate) list.get(i2)).getIssuerX500Principal();
            i2++;
        }
        if (z) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ArrayList arrayList2 = new ArrayList(list);
        for (int i3 = 0; i3 < list.size(); i3++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i3);
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            int i4 = 0;
            while (true) {
                if (i4 == list.size()) {
                    z2 = false;
                    break;
                }
                if (((X509Certificate) list.get(i4)).getIssuerX500Principal().equals(subjectX500Principal)) {
                    z2 = true;
                    break;
                }
                i4++;
            }
            if (!z2) {
                arrayList.add(x509Certificate);
                list.remove(i3);
            }
        }
        if (arrayList.size() > 1) {
            return arrayList2;
        }
        for (int i5 = 0; i5 != arrayList.size(); i5++) {
            X500Principal issuerX500Principal2 = ((X509Certificate) arrayList.get(i5)).getIssuerX500Principal();
            int i6 = 0;
            while (true) {
                if (i6 < list.size()) {
                    X509Certificate x509Certificate2 = (X509Certificate) list.get(i6);
                    if (issuerX500Principal2.equals(x509Certificate2.getSubjectX500Principal())) {
                        arrayList.add(x509Certificate2);
                        list.remove(i6);
                        break;
                    }
                    i6++;
                }
            }
        }
        return list.size() > 0 ? arrayList2 : arrayList;
    }

    private q toASN1Object(X509Certificate x509Certificate) throws CertificateEncodingException {
        try {
            return new i(x509Certificate.getEncoded()).M();
        } catch (Exception e2) {
            throw new CertificateEncodingException("Exception while encoding certificate: " + e2.toString());
        }
    }

    private byte[] toDEREncoded(e eVar) throws CertificateEncodingException {
        try {
            return eVar.c().f("DER");
        } catch (IOException e2) {
            throw new CertificateEncodingException("Exception thrown: " + e2);
        }
    }

    @Override // java.security.cert.CertPath
    public List getCertificates() {
        return Collections.unmodifiableList(new ArrayList(this.certificates));
    }

    @Override // java.security.cert.CertPath
    public byte[] getEncoded() throws CertificateEncodingException {
        Iterator encodings = getEncodings();
        if (!encodings.hasNext()) {
            return null;
        }
        Object next = encodings.next();
        if (next instanceof String) {
            return getEncoded((String) next);
        }
        return null;
    }

    @Override // java.security.cert.CertPath
    public byte[] getEncoded(String str) throws CertificateEncodingException {
        if (str.equalsIgnoreCase("PkiPath")) {
            f fVar = new f();
            List list = this.certificates;
            ListIterator listIterator = list.listIterator(list.size());
            while (listIterator.hasPrevious()) {
                fVar.a(toASN1Object((X509Certificate) listIterator.previous()));
            }
            return toDEREncoded(new c1(fVar));
        }
        int i2 = 0;
        if (str.equalsIgnoreCase("PKCS7")) {
            c cVar = new c(n.W, null);
            f fVar2 = new f();
            while (i2 != this.certificates.size()) {
                fVar2.a(toASN1Object((X509Certificate) this.certificates.get(i2)));
                i2++;
            }
            return toDEREncoded(new c(n.X, new w(new j(1L), new e1(), cVar, new e1(fVar2), null, new e1())));
        }
        if (!str.equalsIgnoreCase("PEM")) {
            throw new CertificateEncodingException("unsupported encoding: " + str);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        d dVar = new d(new OutputStreamWriter(byteArrayOutputStream));
        while (i2 != this.certificates.size()) {
            try {
                dVar.d(new b("CERTIFICATE", ((X509Certificate) this.certificates.get(i2)).getEncoded()));
                i2++;
            } catch (Exception unused) {
                throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
            }
        }
        dVar.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override // java.security.cert.CertPath
    public Iterator getEncodings() {
        return certPathEncodings.iterator();
    }
}
