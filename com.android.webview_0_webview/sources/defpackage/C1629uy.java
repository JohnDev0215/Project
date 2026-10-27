package defpackage;

import android.os.SystemClock;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

/* JADX INFO: renamed from: uy, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1629uy {
    public static final Object a = new Object();
    public static C1629uy b;

    public C1519sy a(int i, String str, String str2, String str3) {
        C1519sy c1519sy = new C1519sy();
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                try {
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    HttpURLConnection httpURLConnectionD = d(i, null, str2, null);
                    httpURLConnectionD.setReadTimeout(3000);
                    httpURLConnectionD.setConnectTimeout(1000);
                    httpURLConnectionD.setDoInput(true);
                    httpURLConnectionD.setRequestProperty("A-IM", "gzip");
                    httpURLConnectionD.connect();
                    int responseCode = httpURLConnectionD.getResponseCode();
                    c1519sy.a = responseCode;
                    if (responseCode != 200) {
                        String str4 = "Non-OK response code = " + responseCode;
                        Ts.f("VariationsSeedFetch", str4, new Object[0]);
                        throw new IOException(str4);
                    }
                    It.e("Variations.FirstRun.SeedConnectTime", SystemClock.elapsedRealtime() - jElapsedRealtime);
                    C1574ty c1574ty = new C1574ty();
                    c1574ty.e = c(httpURLConnectionD);
                    c1574ty.a = b(httpURLConnectionD, "X-Seed-Signature");
                    c1574ty.b = b(httpURLConnectionD, "X-Country");
                    c1574ty.c = new Date().getTime();
                    c1574ty.d = b(httpURLConnectionD, "IM").equals("gzip");
                    e(SystemClock.elapsedRealtime() - jElapsedRealtime);
                    c1519sy.b = c1574ty;
                    httpURLConnectionD.disconnect();
                    return c1519sy;
                } catch (SocketTimeoutException e) {
                    c1519sy.a = -2;
                    Ts.f("VariationsSeedFetch", "SocketTimeoutException timeout when fetching variations seed.", e);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    return c1519sy;
                } catch (IOException e2) {
                    c1519sy.a = -1;
                    Ts.f("VariationsSeedFetch", "IOException when fetching variations seed.", e2);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    return c1519sy;
                }
            } catch (UnknownHostException e3) {
                c1519sy.a = -3;
                Ts.f("VariationsSeedFetch", "UnknownHostException unknown host when fetching variations seed.", e3);
                if (0 != 0) {
                    httpURLConnection.disconnect();
                }
                return c1519sy;
            }
        } catch (Throwable unused) {
            if (0 != 0) {
                httpURLConnection.disconnect();
            }
            return c1519sy;
        }
    }

    public final String b(HttpURLConnection httpURLConnection, String str) {
        String headerField = httpURLConnection.getHeaderField(str);
        return headerField == null ? "" : headerField.trim();
    }

    public final byte[] c(HttpURLConnection httpURLConnection) throws Throwable {
        InputStream inputStream;
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Ms.a(inputStream, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (inputStream != null) {
                inputStream.close();
            }
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public HttpURLConnection d(int i, String str, String str2, String str3) {
        String strC = "https://clientservices.googleapis.com/chrome-variations/seed?osname=";
        if (i == 0) {
            strC = Z7.c("https://clientservices.googleapis.com/chrome-variations/seed?osname=", "android");
        } else if (i == 1) {
            strC = Z7.c("https://clientservices.googleapis.com/chrome-variations/seed?osname=", "android_webview");
        }
        if (str != null && !str.isEmpty()) {
            strC = Z7.d(strC, "&restrict=", str);
        }
        if (str2 != null && !str2.isEmpty()) {
            strC = Z7.d(strC, "&milestone=", str2);
        }
        if (str3 != null && !str3.isEmpty()) {
            strC = Z7.d(strC, "&channel=", str3);
        }
        return (HttpURLConnection) new URL(strC).openConnection();
    }

    public final void e(long j) {
        Ts.d("VariationsSeedFetch", "Fetched first run seed in " + j + " ms", new Object[0]);
        It.e("Variations.FirstRun.SeedFetchTime", j);
    }
}
