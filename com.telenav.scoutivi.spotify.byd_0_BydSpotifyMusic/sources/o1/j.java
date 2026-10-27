package o1;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import o1.d;

/* JADX INFO: compiled from: HttpUrlFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements d<InputStream> {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    static final b f8655l = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final u1.g f8656e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f8657g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final b f8658h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private HttpURLConnection f8659i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private InputStream f8660j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private volatile boolean f8661k;

    /* JADX INFO: compiled from: HttpUrlFetcher.java */
    private static class a implements b {
        a() {
        }

        @Override // o1.j.b
        public HttpURLConnection a(URL url) {
            return (HttpURLConnection) url.openConnection();
        }
    }

    /* JADX INFO: compiled from: HttpUrlFetcher.java */
    interface b {
        HttpURLConnection a(URL url);
    }

    public j(u1.g gVar, int i7) {
        this(gVar, i7, f8655l);
    }

    private InputStream d(HttpURLConnection httpURLConnection) {
        if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
            this.f8660j = k2.c.z(httpURLConnection.getInputStream(), httpURLConnection.getContentLength());
        } else {
            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection.getContentEncoding());
            }
            this.f8660j = httpURLConnection.getInputStream();
        }
        return this.f8660j;
    }

    private static boolean f(int i7) {
        return i7 / 100 == 2;
    }

    private static boolean g(int i7) {
        return i7 / 100 == 3;
    }

    private InputStream h(URL url, int i7, URL url2, Map<String, String> map) throws IOException {
        if (i7 >= 5) {
            throw new n1.e("Too many (> 5) redirects!");
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new n1.e("In re-direct loop");
                }
            } catch (URISyntaxException unused) {
            }
        }
        this.f8659i = this.f8658h.a(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.f8659i.addRequestProperty(entry.getKey(), entry.getValue());
        }
        this.f8659i.setConnectTimeout(this.f8657g);
        this.f8659i.setReadTimeout(this.f8657g);
        this.f8659i.setUseCaches(false);
        this.f8659i.setDoInput(true);
        this.f8659i.setInstanceFollowRedirects(false);
        this.f8659i.connect();
        this.f8660j = this.f8659i.getInputStream();
        if (this.f8661k) {
            return null;
        }
        int responseCode = this.f8659i.getResponseCode();
        if (f(responseCode)) {
            return d(this.f8659i);
        }
        if (!g(responseCode)) {
            if (responseCode == -1) {
                throw new n1.e(responseCode);
            }
            throw new n1.e(this.f8659i.getResponseMessage(), responseCode);
        }
        String headerField = this.f8659i.getHeaderField("Location");
        if (TextUtils.isEmpty(headerField)) {
            throw new n1.e("Received empty or null redirect url");
        }
        URL url3 = new URL(url, headerField);
        b();
        return h(url3, i7 + 1, url, map);
    }

    @Override // o1.d
    public Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // o1.d
    public void b() {
        InputStream inputStream = this.f8660j;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.f8659i;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.f8659i = null;
    }

    @Override // o1.d
    public void c(com.bumptech.glide.g gVar, d.a<? super InputStream> aVar) {
        StringBuilder sb;
        long jB = k2.f.b();
        try {
            try {
                aVar.f(h(this.f8656e.i(), 0, null, this.f8656e.e()));
            } catch (IOException e7) {
                if (Log.isLoggable("HttpUrlFetcher", 3)) {
                    Log.d("HttpUrlFetcher", "Failed to load data for url", e7);
                }
                aVar.d(e7);
                if (!Log.isLoggable("HttpUrlFetcher", 2)) {
                    return;
                } else {
                    sb = new StringBuilder();
                }
            }
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                sb = new StringBuilder();
                sb.append("Finished http url fetcher fetch in ");
                sb.append(k2.f.a(jB));
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                Log.v("HttpUrlFetcher", "Finished http url fetcher fetch in " + k2.f.a(jB));
            }
            throw th;
        }
    }

    @Override // o1.d
    public void cancel() {
        this.f8661k = true;
    }

    @Override // o1.d
    public n1.a e() {
        return n1.a.REMOTE;
    }

    j(u1.g gVar, int i7, b bVar) {
        this.f8656e = gVar;
        this.f8657g = i7;
        this.f8658h = bVar;
    }
}
