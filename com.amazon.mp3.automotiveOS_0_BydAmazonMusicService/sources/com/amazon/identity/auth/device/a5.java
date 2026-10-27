package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.auth.device.framework.RetryLogic;
import com.google.firebase.perf.FirebasePerformance;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class a5 {
    public static final String f = "com.amazon.identity.auth.device.a5";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpURLConnection f171a;
    public final Map<String, List<String>> b = new HashMap();
    public final ByteArrayOutputStream c = new ByteArrayOutputStream();
    public Integer d = null;
    public Long e = null;

    public a5(URL url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("url cannot be null");
        }
        URLConnection uRLConnectionA = e.a(url);
        if (!(uRLConnectionA instanceof HttpURLConnection)) {
            throw new IllegalArgumentException("url must be at least http");
        }
        this.f171a = (HttpURLConnection) uRLConnectionA;
    }

    public void a(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.setRequestMethod(this.f171a.getRequestMethod());
            Integer num = this.d;
            if (num != null) {
                httpURLConnection.setChunkedStreamingMode(num.intValue());
            }
            Long l = this.e;
            if (l != null) {
                httpURLConnection.setFixedLengthStreamingMode(l.longValue());
            }
            httpURLConnection.setInstanceFollowRedirects(this.f171a.getInstanceFollowRedirects());
            httpURLConnection.setAllowUserInteraction(this.f171a.getAllowUserInteraction());
            httpURLConnection.setConnectTimeout(this.f171a.getConnectTimeout());
            httpURLConnection.setDefaultUseCaches(this.f171a.getDefaultUseCaches());
            httpURLConnection.setDoInput(this.f171a.getDoInput());
            httpURLConnection.setDoOutput(this.f171a.getDoOutput());
            httpURLConnection.setIfModifiedSince(this.f171a.getIfModifiedSince());
            httpURLConnection.setReadTimeout(this.f171a.getReadTimeout());
            httpURLConnection.setUseCaches(this.f171a.getUseCaches());
            for (Map.Entry<String, List<String>> entry : this.b.entrySet()) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    httpURLConnection.addRequestProperty(entry.getKey(), it.next());
                }
            }
        } catch (ProtocolException unused) {
            throw new IllegalStateException("Connection has been already executed");
        }
    }

    public HttpURLConnection b() {
        return this.f171a;
    }

    public URL c() {
        return this.f171a.getURL();
    }

    public String toString() {
        return this.f171a.toString();
    }

    public final HttpURLConnection a() throws IOException {
        HttpURLConnection httpURLConnection;
        OutputStream outputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) e.a(this.f171a.getURL());
        } catch (IOException e) {
            Log.e(ga.a(f), "IOException while cloning connection. Should not happen", e);
            httpURLConnection = null;
        }
        if (httpURLConnection == null) {
            return null;
        }
        a(httpURLConnection);
        RetryLogic.a(httpURLConnection.getURL());
        String requestMethod = httpURLConnection.getRequestMethod();
        if (FirebasePerformance.HttpMethod.POST.equalsIgnoreCase(requestMethod) || FirebasePerformance.HttpMethod.PUT.equalsIgnoreCase(requestMethod)) {
            try {
                try {
                    outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(this.c.toByteArray());
                } catch (SecurityException e2) {
                    Log.e(ga.a(f), "Got a security exception while trying to establish connection. Verify that your app has internet access permission android.permission.INTERNET." + e2.getMessage());
                    throw new IOException(e2.getMessage());
                }
            } finally {
                sa.a(outputStream);
            }
        }
        return httpURLConnection;
    }

    public final void a(String str, String str2, boolean z) {
        List<String> arrayList = this.b.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.b.put(str, arrayList);
        }
        if (z) {
            arrayList.clear();
        }
        arrayList.add(str2);
    }
}
