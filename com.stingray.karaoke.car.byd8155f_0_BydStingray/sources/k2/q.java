package k2;

import android.net.Uri;
import d5.i0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class q extends AbstractC1602c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f17692p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f17693q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1489n1 f17694r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C1489n1 f17695s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public m f17696t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public HttpURLConnection f17697u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public InputStream f17698v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f17699w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f17700x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public long f17701y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public long f17702z;

    public q(int i7, int i10, C1489n1 c1489n1) {
        super(true);
        this.f17692p = i7;
        this.f17693q = i10;
        this.f17694r = c1489n1;
        this.f17695s = new C1489n1(28);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // k2.h
    public final void close() {
        try {
            InputStream inputStream = this.f17698v;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e10) {
                    int i7 = AbstractC1343A.f15195a;
                    throw new v(2000, 3, e10);
                }
            }
        } finally {
            this.f17698v = null;
            s();
            if (this.f17699w) {
                this.f17699w = false;
                p();
            }
            this.f17697u = null;
            this.f17696t = null;
        }
    }

    @Override // k2.h
    public final Map f() {
        HttpURLConnection httpURLConnection = this.f17697u;
        return httpURLConnection == null ? i0.f13428r : new p(httpURLConnection.getHeaderFields());
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01bf  */
    @Override // k2.h
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long g(k2.m r23) throws k2.v {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: k2.q.g(k2.m):long");
    }

    @Override // k2.h
    public final Uri j() {
        HttpURLConnection httpURLConnection = this.f17697u;
        if (httpURLConnection != null) {
            return Uri.parse(httpURLConnection.getURL().toString());
        }
        m mVar = this.f17696t;
        if (mVar != null) {
            return mVar.f17675a;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0029 A[Catch: IOException -> 0x0034, TRY_LEAVE, TryCatch #0 {IOException -> 0x0034, blocks: (B:4:0x0004, B:6:0x000d, B:9:0x0018, B:10:0x001e, B:13:0x0029), top: B:19:0x0004 }] */
    @Override // e2.InterfaceC1201i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int n(byte[] r7, int r8, int r9) throws k2.v {
        /*
            r6 = this;
            if (r9 != 0) goto L4
            r6 = 0
            goto L33
        L4:
            long r0 = r6.f17701y     // Catch: java.io.IOException -> L34
            r2 = -1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r3 = -1
            if (r2 == 0) goto L1e
            long r4 = r6.f17702z     // Catch: java.io.IOException -> L34
            long r0 = r0 - r4
            r4 = 0
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L18
        L16:
            r6 = r3
            goto L33
        L18:
            long r4 = (long) r9     // Catch: java.io.IOException -> L34
            long r0 = java.lang.Math.min(r4, r0)     // Catch: java.io.IOException -> L34
            int r9 = (int) r0     // Catch: java.io.IOException -> L34
        L1e:
            java.io.InputStream r0 = r6.f17698v     // Catch: java.io.IOException -> L34
            int r1 = h2.AbstractC1343A.f15195a     // Catch: java.io.IOException -> L34
            int r7 = r0.read(r7, r8, r9)     // Catch: java.io.IOException -> L34
            if (r7 != r3) goto L29
            goto L16
        L29:
            long r8 = r6.f17702z     // Catch: java.io.IOException -> L34
            long r0 = (long) r7     // Catch: java.io.IOException -> L34
            long r8 = r8 + r0
            r6.f17702z = r8     // Catch: java.io.IOException -> L34
            r6.e(r7)     // Catch: java.io.IOException -> L34
            r6 = r7
        L33:
            return r6
        L34:
            r6 = move-exception
            int r7 = h2.AbstractC1343A.f15195a
            r7 = 2
            k2.v r6 = k2.v.b(r7, r6)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: k2.q.n(byte[], int, int):int");
    }

    public final void s() {
        HttpURLConnection httpURLConnection = this.f17697u;
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e10) {
                AbstractC1346c.q("DefaultHttpDataSource", "Unexpected error while disconnecting", e10);
            }
        }
    }

    public final HttpURLConnection t(URL url, int i7, byte[] bArr, long j3, long j10, boolean z6, boolean z10, Map map) throws IOException {
        String string;
        String str;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(this.f17692p);
        httpURLConnection.setReadTimeout(this.f17693q);
        HashMap map2 = new HashMap();
        C1489n1 c1489n1 = this.f17694r;
        if (c1489n1 != null) {
            map2.putAll(c1489n1.v());
        }
        map2.putAll(this.f17695s.v());
        map2.putAll(map);
        for (Map.Entry entry : map2.entrySet()) {
            httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
        }
        Pattern pattern = y.f17709a;
        if (j3 == 0 && j10 == -1) {
            string = null;
        } else {
            StringBuilder sb = new StringBuilder("bytes=");
            sb.append(j3);
            sb.append("-");
            if (j10 != -1) {
                sb.append((j3 + j10) - 1);
            }
            string = sb.toString();
        }
        if (string != null) {
            httpURLConnection.setRequestProperty("Range", string);
        }
        httpURLConnection.setRequestProperty("Accept-Encoding", z6 ? "gzip" : "identity");
        httpURLConnection.setInstanceFollowRedirects(z10);
        httpURLConnection.setDoOutput(bArr != null);
        int i10 = m.f17674j;
        if (i7 == 1) {
            str = "GET";
        } else if (i7 == 2) {
            str = "POST";
        } else {
            if (i7 != 3) {
                throw new IllegalStateException();
            }
            str = "HEAD";
        }
        httpURLConnection.setRequestMethod(str);
        if (bArr != null) {
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(bArr);
            outputStream.close();
        } else {
            httpURLConnection.connect();
        }
        return httpURLConnection;
    }

    public final void u(long j3) throws IOException {
        if (j3 == 0) {
            return;
        }
        byte[] bArr = new byte[4096];
        while (j3 > 0) {
            int iMin = (int) Math.min(j3, 4096);
            InputStream inputStream = this.f17698v;
            int i7 = AbstractC1343A.f15195a;
            int i10 = inputStream.read(bArr, 0, iMin);
            if (Thread.currentThread().isInterrupted()) {
                throw new v(2000, 1, new InterruptedIOException());
            }
            if (i10 == -1) {
                throw new v();
            }
            j3 -= (long) i10;
            e(i10);
        }
    }
}
