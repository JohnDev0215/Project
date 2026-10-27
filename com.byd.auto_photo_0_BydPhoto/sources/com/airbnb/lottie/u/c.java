package com.airbnb.lottie.u;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.airbnb.lottie.d;
import com.airbnb.lottie.e;
import com.airbnb.lottie.l;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f906b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final b f907c;

    private c(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        this.f905a = applicationContext;
        this.f906b = str;
        this.f907c = new b(applicationContext, str);
    }

    @Nullable
    @WorkerThread
    private d a() {
        Pair<a, InputStream> pairA = this.f907c.a();
        if (pairA == null) {
            return null;
        }
        a aVar = pairA.first;
        InputStream inputStream = pairA.second;
        l<d> lVarN = aVar == a.ZIP ? e.n(new ZipInputStream(inputStream), this.f906b) : e.g(inputStream, this.f906b);
        if (lVarN.b() != null) {
            return lVarN.b();
        }
        return null;
    }

    @WorkerThread
    private l<d> b() {
        try {
            return c();
        } catch (IOException e) {
            return new l<>((Throwable) e);
        }
    }

    @WorkerThread
    private l c() throws ProtocolException {
        com.airbnb.lottie.w.d.a("Fetching " + this.f906b);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f906b).openConnection();
        httpURLConnection.setRequestMethod("GET");
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
                l<d> lVarG = g(httpURLConnection);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(lVarG.b() != null);
                com.airbnb.lottie.w.d.a(sb.toString());
                return lVarG;
            }
            return new l((Throwable) new IllegalArgumentException("Unable to fetch " + this.f906b + ". Failed with " + httpURLConnection.getResponseCode() + "\n" + f(httpURLConnection)));
        } catch (Exception e) {
            return new l((Throwable) e);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static l<d> e(Context context, String str) {
        return new c(context, str).d();
    }

    private String f(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        sb.append(line);
                        sb.append('\n');
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                } finally {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused2) {
                    }
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return sb.toString();
    }

    @Nullable
    private l<d> g(HttpURLConnection httpURLConnection) {
        a aVar;
        l<d> lVarG;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip")) {
            com.airbnb.lottie.w.d.a("Handling zip response.");
            aVar = a.ZIP;
            lVarG = e.n(new ZipInputStream(new FileInputStream(this.f907c.e(httpURLConnection.getInputStream(), aVar))), this.f906b);
        } else {
            com.airbnb.lottie.w.d.a("Received json response.");
            aVar = a.JSON;
            lVarG = e.g(new FileInputStream(new File(this.f907c.e(httpURLConnection.getInputStream(), aVar).getAbsolutePath())), this.f906b);
        }
        if (lVarG.b() != null) {
            this.f907c.d(aVar);
        }
        return lVarG;
    }

    @WorkerThread
    public l<d> d() {
        d dVarA = a();
        if (dVarA != null) {
            return new l<>(dVarA);
        }
        com.airbnb.lottie.w.d.a("Animation for " + this.f906b + " not found in cache. Fetching from network.");
        return b();
    }
}
