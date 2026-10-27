package defpackage;

import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ConnectivityDetector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ix extends i9<Integer> {
    public final /* synthetic */ String h;
    public final /* synthetic */ int i = 5000;
    public final /* synthetic */ Callback j;
    public final /* synthetic */ jx k;

    public ix(jx jxVar, String str, dm0 dm0Var) {
        this.k = jxVar;
        this.h = str;
        this.j = dm0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.net.HttpURLConnection, java.net.URLConnection] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    @Override // defpackage.i9
    public final Integer b() throws Throwable {
        ?? r4;
        int i;
        int responseCode;
        StringBuilder sb;
        int i2 = this.i;
        jx jxVar = this.k;
        String str = this.h;
        ?? r7 = 0;
        ?? r72 = 0;
        try {
            try {
                oj2.R(1, "ConnectivityDetector.SentHttpProbe." + jxVar.g);
                Log.i("cr_OfflineIndicator", "Sending HTTP Probe now to url:" + str);
                URL url = new URL(str);
                int i3 = yl1.b;
                r4 = (HttpURLConnection) url.openConnection();
            } catch (Throwable th) {
                th = th;
                r4 = r7;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            r4.setInstanceFollowRedirects(false);
            r4.setRequestMethod("GET");
            r4.setConnectTimeout(i2);
            r4.setReadTimeout(i2);
            r4.setUseCaches(false);
            r4.setRequestProperty("User-Agent", jxVar.j);
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            r4.connect();
            long jElapsedRealtime2 = SystemClock.elapsedRealtime();
            responseCode = r4.getResponseCode();
            sb = new StringBuilder("Probe ");
            sb.append(str);
            sb.append(" time=");
            sb.append(jElapsedRealtime2 - jElapsedRealtime);
            sb.append("ms ret=");
            sb.append(responseCode);
            sb.append(" headers=");
            sb.append(r4.getHeaderFields());
            Log.i("cr_OfflineIndicator", sb.toString());
        } catch (IOException e2) {
            e = e2;
            r72 = r4;
            Log.i("cr_OfflineIndicator", "Probe " + str + " failed w/ exception " + e);
            i = 0;
            r7 = r72;
            if (r72 != 0) {
                r72.disconnect();
                r7 = r72;
            }
        } catch (Throwable th2) {
            th = th2;
            if (r4 != 0) {
                r4.disconnect();
            }
            throw th;
        }
        if (responseCode == 204) {
            i = 3;
        } else {
            if (responseCode < 400) {
                if (responseCode == 200) {
                    if (r4.getContentLength() == 0) {
                        i = 4;
                    } else if (r4.getContentLength() == -1 && r4.getInputStream().read() == -1) {
                        i = 5;
                    }
                }
                r4.disconnect();
                i = 2;
                r7 = sb;
                return i;
            }
            i = 1;
        }
        r4.disconnect();
        r7 = sb;
        return i;
    }

    @Override // defpackage.i9
    public final void l(Integer num) {
        this.j.onResult(num);
    }
}
