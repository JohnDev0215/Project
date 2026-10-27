package org.bouncycastle.crypto.tls;

import java.io.InputStream;
import java.util.Vector;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected TlsContext context;
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;

    protected AbstractTlsKeyExchange(int i, Vector vector) {
        this.keyExchange = i;
        this.supportedSignatureAlgorithms = vector;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public byte[] generateServerKeyExchange() throws TlsFatalAlert {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 80);
        }
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(org.bouncycastle.crypto.tls.TlsContext r3) {
        /*
            r2 = this;
            r2.context = r3
            org.bouncycastle.crypto.tls.ProtocolVersion r3 = r3.getClientVersion()
            boolean r0 = org.bouncycastle.crypto.tls.TlsUtils.isSignatureAlgorithmsExtensionAllowed(r3)
            if (r0 == 0) goto L41
            java.util.Vector r3 = r2.supportedSignatureAlgorithms
            if (r3 != 0) goto L45
            int r3 = r2.keyExchange
            r0 = 1
            if (r3 == r0) goto L3a
            r0 = 3
            if (r3 == r0) goto L35
            r0 = 5
            if (r3 == r0) goto L3a
            r0 = 7
            if (r3 == r0) goto L35
            r0 = 9
            if (r3 == r0) goto L3a
            switch(r3) {
                case 13: goto L45;
                case 14: goto L45;
                case 15: goto L3a;
                case 16: goto L30;
                case 17: goto L30;
                case 18: goto L3a;
                case 19: goto L3a;
                default: goto L25;
            }
        L25:
            switch(r3) {
                case 21: goto L45;
                case 22: goto L35;
                case 23: goto L3a;
                case 24: goto L45;
                default: goto L28;
            }
        L28:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "unsupported key exchange algorithm"
            r2.<init>(r3)
            throw r2
        L30:
            java.util.Vector r3 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultECDSASignatureAlgorithms()
            goto L3e
        L35:
            java.util.Vector r3 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultDSSSignatureAlgorithms()
            goto L3e
        L3a:
            java.util.Vector r3 = org.bouncycastle.crypto.tls.TlsUtils.getDefaultRSASignatureAlgorithms()
        L3e:
            r2.supportedSignatureAlgorithms = r3
            goto L45
        L41:
            java.util.Vector r2 = r2.supportedSignatureAlgorithms
            if (r2 != 0) goto L46
        L45:
            return
        L46:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "supported_signature_algorithms not allowed for "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.crypto.tls.AbstractTlsKeyExchange.init(org.bouncycastle.crypto.tls.TlsContext):void");
    }

    protected DigitallySigned parseSignature(InputStream inputStream) throws TlsFatalAlert {
        DigitallySigned digitallySigned = DigitallySigned.parse(this.context, inputStream);
        SignatureAndHashAlgorithm algorithm = digitallySigned.getAlgorithm();
        if (algorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, algorithm);
        }
        return digitallySigned;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientCertificate(Certificate certificate) {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processClientKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        throw new TlsFatalAlert((short) 80);
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCertificate(Certificate certificate) {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerCredentials(TlsCredentials tlsCredentials) {
        processServerCertificate(tlsCredentials.getCertificate());
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void processServerKeyExchange(InputStream inputStream) throws TlsFatalAlert {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public boolean requiresServerKeyExchange() {
        return false;
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipClientCredentials() {
    }

    @Override // org.bouncycastle.crypto.tls.TlsKeyExchange
    public void skipServerKeyExchange() throws TlsFatalAlert {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert((short) 10);
        }
    }
}
