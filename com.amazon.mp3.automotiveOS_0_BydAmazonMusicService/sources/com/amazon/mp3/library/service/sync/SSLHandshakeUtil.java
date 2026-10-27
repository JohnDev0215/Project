package com.amazon.mp3.library.service.sync;

/* JADX INFO: loaded from: classes3.dex */
public class SSLHandshakeUtil {
    private static final String TAG = "SSLHandshakeUtil";

    /* JADX WARN: Removed duplicated region for block: B:12:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void captureAdditionalDebugLogs(final javax.net.ssl.SSLHandshakeException r3, final java.lang.String r4) {
        /*
            java.lang.String r0 = com.amazon.mp3.library.service.sync.SSLHandshakeUtil.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SSLHandshakeException occured while accessing Url = "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            com.amazon.mp3.util.Log.verbose(r0, r4)
            if (r3 == 0) goto L44
            java.lang.Throwable r4 = r3.getCause()
            boolean r4 = r4 instanceof java.security.cert.CertPathValidatorException
            if (r4 == 0) goto L27
            java.lang.Throwable r3 = r3.getCause()
            java.security.cert.CertPathValidatorException r3 = (java.security.cert.CertPathValidatorException) r3
            goto L45
        L27:
            java.lang.Throwable r4 = r3.getCause()
            if (r4 == 0) goto L44
            java.lang.Throwable r4 = r3.getCause()
            java.lang.Throwable r4 = r4.getCause()
            boolean r4 = r4 instanceof java.security.cert.CertPathValidatorException
            if (r4 == 0) goto L44
            java.lang.Throwable r3 = r3.getCause()
            java.lang.Throwable r3 = r3.getCause()
            java.security.cert.CertPathValidatorException r3 = (java.security.cert.CertPathValidatorException) r3
            goto L45
        L44:
            r3 = 0
        L45:
            if (r3 == 0) goto L7f
            java.security.cert.CertPath r4 = r3.getCertPath()
            java.util.List r4 = r4.getCertificates()
            java.lang.String r1 = "---------------------------------------- Capturing Certificate Chain---------------------------------------------------"
            com.amazon.mp3.util.Log.verbose(r0, r1)
            java.util.Iterator r4 = r4.iterator()
        L58:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L6e
            java.lang.Object r0 = r4.next()
            java.security.cert.Certificate r0 = (java.security.cert.Certificate) r0
            java.lang.String r1 = com.amazon.mp3.library.service.sync.SSLHandshakeUtil.TAG
            java.lang.String r0 = r0.toString()
            com.amazon.mp3.util.Log.verbose(r1, r0)
            goto L58
        L6e:
            java.lang.String r4 = com.amazon.mp3.library.service.sync.SSLHandshakeUtil.TAG
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            java.security.cert.CertPathValidatorException$Reason r3 = r3.getReason()
            r0[r1] = r3
            java.lang.String r3 = "Reason for CertPathValidationException : %s"
            com.amazon.mp3.util.Log.verbose(r4, r3, r0)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.service.sync.SSLHandshakeUtil.captureAdditionalDebugLogs(javax.net.ssl.SSLHandshakeException, java.lang.String):void");
    }
}
