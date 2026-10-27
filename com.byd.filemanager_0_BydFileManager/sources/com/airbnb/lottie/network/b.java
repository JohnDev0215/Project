package com.airbnb.lottie.network;

import android.content.Context;
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
import java.net.URL;
import java.util.zip.ZipInputStream;

/* JADX INFO: compiled from: NetworkFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f2650a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f2652c;

    private b(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        this.f2650a = applicationContext;
        this.f2651b = str;
        if (str2 == null) {
            this.f2652c = null;
        } else {
            this.f2652c = new a(applicationContext);
        }
    }

    private d a() {
        b.g.j.d<FileExtension, InputStream> dVarA;
        a aVar = this.f2652c;
        if (aVar == null || (dVarA = aVar.a(this.f2651b)) == null) {
            return null;
        }
        FileExtension fileExtension = dVarA.f1936a;
        InputStream inputStream = dVarA.f1937b;
        l<d> lVarR = fileExtension == FileExtension.ZIP ? e.r(new ZipInputStream(inputStream), this.f2651b) : e.h(inputStream, this.f2651b);
        if (lVarR.b() != null) {
            return lVarR.b();
        }
        return null;
    }

    private l<d> b() {
        try {
            return c();
        } catch (IOException e2) {
            return new l<>((Throwable) e2);
        }
    }

    private l<d> c() throws IOException {
        com.airbnb.lottie.u.d.a("Fetching " + this.f2651b);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f2651b).openConnection();
        httpURLConnection.setRequestMethod("GET");
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
                l<d> lVarG = g(httpURLConnection);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(lVarG.b() != null);
                com.airbnb.lottie.u.d.a(sb.toString());
                return lVarG;
            }
            return new l<>((Throwable) new IllegalArgumentException("Unable to fetch " + this.f2651b + ". Failed with " + httpURLConnection.getResponseCode() + "\n" + f(httpURLConnection)));
        } catch (Exception e2) {
            return new l<>((Throwable) e2);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static l<d> e(Context context, String str, String str2) {
        return new b(context, str, str2).d();
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
            } catch (Exception e2) {
                throw e2;
            }
        }
        return sb.toString();
    }

    private l<d> g(HttpURLConnection httpURLConnection) throws IOException {
        FileExtension fileExtension;
        l<d> lVarH;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip")) {
            com.airbnb.lottie.u.d.a("Handling zip response.");
            fileExtension = FileExtension.ZIP;
            a aVar = this.f2652c;
            lVarH = aVar == null ? e.r(new ZipInputStream(httpURLConnection.getInputStream()), null) : e.r(new ZipInputStream(new FileInputStream(aVar.f(this.f2651b, httpURLConnection.getInputStream(), fileExtension))), this.f2651b);
        } else {
            com.airbnb.lottie.u.d.a("Received json response.");
            fileExtension = FileExtension.JSON;
            a aVar2 = this.f2652c;
            lVarH = aVar2 == null ? e.h(httpURLConnection.getInputStream(), null) : e.h(new FileInputStream(new File(aVar2.f(this.f2651b, httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.f2651b);
        }
        if (this.f2652c != null && lVarH.b() != null) {
            this.f2652c.e(this.f2651b, fileExtension);
        }
        return lVarH;
    }

    public l<d> d() {
        d dVarA = a();
        if (dVarA != null) {
            return new l<>(dVarA);
        }
        com.airbnb.lottie.u.d.a("Animation for " + this.f2651b + " not found in cache. Fetching from network.");
        return b();
    }
}
