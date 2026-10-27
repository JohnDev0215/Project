package com.airbnb.lottie.network;

import android.content.Context;
import com.airbnb.lottie.d;
import com.airbnb.lottie.e;
import com.airbnb.lottie.l;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
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
    private final Context f3036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f3038c;

    private b(Context context, String str) {
        Context applicationContext = context.getApplicationContext();
        this.f3036a = applicationContext;
        this.f3037b = str;
        this.f3038c = new a(applicationContext, str);
    }

    private d a() {
        b.g.j.d<FileExtension, InputStream> dVarA = this.f3038c.a();
        if (dVarA == null) {
            return null;
        }
        FileExtension fileExtension = dVarA.f2292a;
        InputStream inputStream = dVarA.f2293b;
        l<d> lVarN = fileExtension == FileExtension.ZIP ? e.n(new ZipInputStream(inputStream), this.f3037b) : e.g(inputStream, this.f3037b);
        if (lVarN.b() != null) {
            return lVarN.b();
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

    private l c() throws IOException {
        com.airbnb.lottie.u.d.a("Fetching " + this.f3037b);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f3037b).openConnection();
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
            return new l((Throwable) new IllegalArgumentException("Unable to fetch " + this.f3037b + ". Failed with " + httpURLConnection.getResponseCode() + BydAdbHelper.COMMAND_LINE_END + f(httpURLConnection)));
        } catch (Exception e2) {
            return new l((Throwable) e2);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static l<d> e(Context context, String str) {
        return new b(context, str).d();
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
        l<d> lVarG;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip")) {
            com.airbnb.lottie.u.d.a("Handling zip response.");
            fileExtension = FileExtension.ZIP;
            lVarG = e.n(new ZipInputStream(new FileInputStream(this.f3038c.e(httpURLConnection.getInputStream(), fileExtension))), this.f3037b);
        } else {
            com.airbnb.lottie.u.d.a("Received json response.");
            fileExtension = FileExtension.JSON;
            lVarG = e.g(new FileInputStream(new File(this.f3038c.e(httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.f3037b);
        }
        if (lVarG.b() != null) {
            this.f3038c.d(fileExtension);
        }
        return lVarG;
    }

    public l<d> d() {
        d dVarA = a();
        if (dVarA != null) {
            return new l<>(dVarA);
        }
        com.airbnb.lottie.u.d.a("Animation for " + this.f3037b + " not found in cache. Fetching from network.");
        return b();
    }
}
