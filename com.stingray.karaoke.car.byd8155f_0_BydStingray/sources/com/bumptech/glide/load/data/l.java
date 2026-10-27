package com.bumptech.glide.load.data;

import E5.s;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class l implements e {
    public final k4.g l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f12729m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public HttpURLConnection f12730n;
    public InputStream o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile boolean f12731p;

    public l(k4.g gVar, int i7) {
        this.l = gVar;
        this.f12729m = i7;
    }

    public static int c(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseCode();
        } catch (IOException e10) {
            if (!Log.isLoggable("HttpUrlFetcher", 3)) {
                return -1;
            }
            Log.d("HttpUrlFetcher", "Failed to get a response code", e10);
            return -1;
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        InputStream inputStream = this.o;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.f12730n;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.f12730n = null;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        this.f12731p = true;
    }

    public final InputStream d(URL url, int i7, URL url2, Map map) throws s {
        if (i7 >= 5) {
            throw new s(-1, null, "Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new s(-1, null, "In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            for (Map.Entry entry : map.entrySet()) {
                httpURLConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            int i10 = this.f12729m;
            httpURLConnection.setConnectTimeout(i10);
            httpURLConnection.setReadTimeout(i10);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            this.f12730n = httpURLConnection;
            try {
                httpURLConnection.connect();
                this.o = this.f12730n.getInputStream();
                if (this.f12731p) {
                    return null;
                }
                int iC = c(this.f12730n);
                int i11 = iC / 100;
                if (i11 == 2) {
                    HttpURLConnection httpURLConnection2 = this.f12730n;
                    try {
                        if (TextUtils.isEmpty(httpURLConnection2.getContentEncoding())) {
                            this.o = new A4.e(httpURLConnection2.getInputStream(), httpURLConnection2.getContentLength());
                        } else {
                            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection2.getContentEncoding());
                            }
                            this.o = httpURLConnection2.getInputStream();
                        }
                        return this.o;
                    } catch (IOException e10) {
                        throw new s(c(httpURLConnection2), e10, "Failed to obtain InputStream");
                    }
                }
                if (i11 != 3) {
                    if (iC == -1) {
                        throw new s(iC, null, "Http request failed");
                    }
                    try {
                        throw new s(iC, null, this.f12730n.getResponseMessage());
                    } catch (IOException e11) {
                        throw new s(iC, e11, "Failed to get a response message");
                    }
                }
                String headerField = this.f12730n.getHeaderField("Location");
                if (TextUtils.isEmpty(headerField)) {
                    throw new s(iC, null, "Received empty or null redirect url");
                }
                try {
                    URL url3 = new URL(url, headerField);
                    b();
                    return d(url3, i7 + 1, url, map);
                } catch (MalformedURLException e12) {
                    throw new s(iC, e12, M5.b.g("Bad redirect url: ", headerField));
                }
            } catch (IOException e13) {
                throw new s(c(this.f12730n), e13, "Failed to connect or obtain data");
            }
        } catch (IOException e14) {
            throw new s(0, e14, "URL.openConnection threw");
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        return 2;
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, d dVar) {
        StringBuilder sb;
        k4.g gVar = this.l;
        int i7 = A4.k.f423b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            try {
                dVar.d(d(gVar.d(), 0, null, gVar.f18190b.b()));
            } catch (IOException e10) {
                if (Log.isLoggable("HttpUrlFetcher", 3)) {
                    Log.d("HttpUrlFetcher", "Failed to load data for url", e10);
                }
                dVar.c(e10);
                if (!Log.isLoggable("HttpUrlFetcher", 2)) {
                    return;
                } else {
                    sb = new StringBuilder("Finished http url fetcher fetch in ");
                }
            }
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                sb = new StringBuilder("Finished http url fetcher fetch in ");
                sb.append(A4.k.a(jElapsedRealtimeNanos));
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                Log.v("HttpUrlFetcher", "Finished http url fetcher fetch in " + A4.k.a(jElapsedRealtimeNanos));
            }
            throw th;
        }
    }
}
