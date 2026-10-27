package com.bumptech.glide.load.i;

import android.text.TextUtils;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import com.bumptech.glide.load.i.d;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/* JADX INFO: compiled from: HttpUrlFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements d<InputStream> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final b f2931b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.bumptech.glide.load.j.g f2932c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f2933d;
    private final b e;
    private HttpURLConnection f;
    private InputStream g;
    private volatile boolean h;

    /* JADX INFO: compiled from: HttpUrlFetcher.java */
    private static class a implements b {
        a() {
        }

        @Override // com.bumptech.glide.load.i.j.b
        public HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    /* JADX INFO: compiled from: HttpUrlFetcher.java */
    interface b {
        HttpURLConnection a(URL url) throws IOException;
    }

    public j(com.bumptech.glide.load.j.g gVar, int i) {
        this(gVar, i, f2931b);
    }

    private InputStream c(HttpURLConnection httpURLConnection) throws IOException {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.g = com.bumptech.glide.m.c.b(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.g = httpURLConnection.getInputStream();
        }
        return this.g;
    }

    private static boolean d(int i) {
        return i / 100 == 2;
    }

    private static boolean g(int i) {
        return i / 100 == 3;
    }

    private InputStream h(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        if (i >= 5) {
            throw new HttpException("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new HttpException("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.f = this.e.a(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.f.addRequestProperty(entry.getKey(), entry.getValue());
        }
        this.f.setConnectTimeout(this.f2933d);
        this.f.setReadTimeout(this.f2933d);
        this.f.setUseCaches(false);
        this.f.setDoInput(true);
        this.f.setInstanceFollowRedirects(false);
        this.f.connect();
        this.g = this.f.getInputStream();
        if (this.h) {
            return null;
        }
        int responseCode = this.f.getResponseCode();
        if (d(responseCode)) {
            return c(this.f);
        }
        if (!g(responseCode)) {
            if (responseCode == -1) {
                throw new HttpException(responseCode);
            }
            throw new HttpException(this.f.getResponseMessage(), responseCode);
        }
        String headerField = this.f.getHeaderField("Location");
        if (TextUtils.isEmpty(headerField)) {
            throw new HttpException("Received empty or null redirect url");
        }
        URL url3 = new URL(url, headerField);
        b();
        return h(url3, i + 1, url, map);
    }

    @Override // com.bumptech.glide.load.i.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // com.bumptech.glide.load.i.d
    public void b() {
        InputStream inputStream = this.g;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.f;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.f = null;
    }

    @Override // com.bumptech.glide.load.i.d
    public void cancel() {
        this.h = true;
    }

    @Override // com.bumptech.glide.load.i.d
    public DataSource e() {
        return DataSource.REMOTE;
    }

    @Override // com.bumptech.glide.load.i.d
    public void f(Priority priority, d.a<? super InputStream> aVar) {
        StringBuilder sb;
        long jB = com.bumptech.glide.m.f.b();
        try {
            try {
                aVar.d(h(this.f2932c.h(), 0, null, this.f2932c.e()));
            } catch (IOException e) {
                if (Log.isLoggable("HttpUrlFetcher", 3)) {
                    Log.d("HttpUrlFetcher", "Failed to load data for url", e);
                }
                aVar.c(e);
                if (!Log.isLoggable("HttpUrlFetcher", 2)) {
                    return;
                } else {
                    sb = new StringBuilder();
                }
            }
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                sb = new StringBuilder();
                sb.append("Finished http url fetcher fetch in ");
                sb.append(com.bumptech.glide.m.f.a(jB));
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                Log.v("HttpUrlFetcher", "Finished http url fetcher fetch in " + com.bumptech.glide.m.f.a(jB));
            }
            throw th;
        }
    }

    j(com.bumptech.glide.load.j.g gVar, int i, b bVar) {
        this.f2932c = gVar;
        this.f2933d = i;
        this.e = bVar;
    }
}
