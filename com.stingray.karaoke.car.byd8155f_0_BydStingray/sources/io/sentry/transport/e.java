package io.sentry.transport;

import h6.q;
import io.sentry.C1;
import io.sentry.C1489n1;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.K;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Charset f16899e = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Proxy f16900a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f16901b;
    public final F1 c;
    public final m d;

    public e(F1 f12, io.sentry.internal.debugmeta.c cVar, m mVar) {
        Proxy proxy;
        this.f16901b = cVar;
        this.c = f12;
        this.d = mVar;
        C1 proxy2 = f12.getProxy();
        if (proxy2 != null) {
            String str = proxy2.f15824b;
            try {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy2.f15823a, Integer.parseInt(str)));
            } catch (NumberFormatException e10) {
                this.c.getLogger().p(EnumC1495p1.ERROR, e10, android.hardware.bydauto.ac.tsimpl.a.i("Failed to parse Sentry Proxy port: ", str, ". Proxy is ignored"), new Object[0]);
                proxy = null;
            }
        } else {
            proxy = null;
        }
        this.f16900a = proxy;
        if (proxy == null || f12.getProxy() == null) {
            return;
        }
        String str2 = f12.getProxy().c;
        String str3 = f12.getProxy().d;
        if (str2 == null || str3 == null) {
            return;
        }
        Authenticator.setDefault(new j(str2, str3));
    }

    public static void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
        httpURLConnection.disconnect();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(java.net.HttpURLConnection r4) {
        /*
            java.io.InputStream r4 = r4.getErrorStream()     // Catch: java.io.IOException -> L4e
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L38
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L38
            java.nio.charset.Charset r2 = io.sentry.transport.e.f16899e     // Catch: java.lang.Throwable -> L38
            r1.<init>(r4, r2)     // Catch: java.lang.Throwable -> L38
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L38
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L24
            r1.<init>()     // Catch: java.lang.Throwable -> L24
            r2 = 1
        L16:
            java.lang.String r3 = r0.readLine()     // Catch: java.lang.Throwable -> L24
            if (r3 == 0) goto L2b
            if (r2 != 0) goto L26
            java.lang.String r2 = "\n"
            r1.append(r2)     // Catch: java.lang.Throwable -> L24
            goto L26
        L24:
            r1 = move-exception
            goto L3a
        L26:
            r1.append(r3)     // Catch: java.lang.Throwable -> L24
            r2 = 0
            goto L16
        L2b:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L24
            r0.close()     // Catch: java.lang.Throwable -> L38
            if (r4 == 0) goto L37
            r4.close()     // Catch: java.io.IOException -> L4e
        L37:
            return r1
        L38:
            r0 = move-exception
            goto L43
        L3a:
            r0.close()     // Catch: java.lang.Throwable -> L3e
            goto L42
        L3e:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch: java.lang.Throwable -> L38
        L42:
            throw r1     // Catch: java.lang.Throwable -> L38
        L43:
            if (r4 == 0) goto L4d
            r4.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r4 = move-exception
            r0.addSuppressed(r4)     // Catch: java.io.IOException -> L4e
        L4d:
            throw r0     // Catch: java.io.IOException -> L4e
        L4e:
            java.lang.String r4 = "Failed to obtain error message while analyzing send failure."
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.transport.e.b(java.net.HttpURLConnection):java.lang.String");
    }

    public final q c(HttpURLConnection httpURLConnection) {
        F1 f12 = this.c;
        try {
            try {
                int responseCode = httpURLConnection.getResponseCode();
                e(httpURLConnection, responseCode);
                if (responseCode == 200) {
                    f12.getLogger().k(EnumC1495p1.DEBUG, "Envelope sent successfully.", new Object[0]);
                    return p.f16912b;
                }
                K logger = f12.getLogger();
                EnumC1495p1 enumC1495p1 = EnumC1495p1.ERROR;
                logger.k(enumC1495p1, "Request failed, API returned %s", Integer.valueOf(responseCode));
                if (f12.isDebug()) {
                    f12.getLogger().k(enumC1495p1, "%s", b(httpURLConnection));
                }
                return new o(responseCode);
            } catch (IOException e10) {
                f12.getLogger().p(EnumC1495p1.ERROR, e10, "Error reading and logging the response stream", new Object[0]);
                a(httpURLConnection);
                return new o(-1);
            }
        } finally {
            a(httpURLConnection);
        }
    }

    public final q d(C1489n1 c1489n1) {
        io.sentry.internal.debugmeta.c cVar = this.f16901b;
        URL url = (URL) cVar.f16569m;
        Proxy proxy = this.f16900a;
        HttpURLConnection httpURLConnection = (HttpURLConnection) (proxy == null ? url.openConnection() : url.openConnection(proxy));
        for (Map.Entry entry : ((HashMap) cVar.f16570n).entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-sentry-envelope");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Connection", "close");
        F1 f12 = this.c;
        httpURLConnection.setConnectTimeout(f12.getConnectionTimeoutMillis());
        httpURLConnection.setReadTimeout(f12.getReadTimeoutMillis());
        SSLSocketFactory sslSocketFactory = f12.getSslSocketFactory();
        if ((httpURLConnection instanceof HttpsURLConnection) && sslSocketFactory != null) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sslSocketFactory);
        }
        httpURLConnection.connect();
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    f12.getSerializer().e(c1489n1, gZIPOutputStream);
                    gZIPOutputStream.close();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } finally {
                }
            } finally {
            }
        } finally {
            try {
            } finally {
            }
        }
        return c(httpURLConnection);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0131 A[PHI: r1 r2
  0x0131: PHI (r1v7 int) = (r1v5 int), (r1v8 int) binds: [B:18:0x0057, B:96:0x0131] A[DONT_GENERATE, DONT_INLINE]
  0x0131: PHI (r2v6 java.lang.String[]) = (r2v4 java.lang.String[]), (r2v7 java.lang.String[]) binds: [B:18:0x0057, B:96:0x0131] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(java.net.HttpURLConnection r25, int r26) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.transport.e.e(java.net.HttpURLConnection, int):void");
    }
}
