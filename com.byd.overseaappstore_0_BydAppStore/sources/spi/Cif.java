package spi;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.HttpException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import spi.m5;

/* JADX INFO: renamed from: spi.if, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public final class Cif implements m5<InputStream> {

    @VisibleForTesting
    public static final a g = new a();
    public final sd a;
    public final int b;
    public final b c;
    public HttpURLConnection d;
    public InputStream e;
    public volatile boolean f;

    /* JADX INFO: renamed from: spi.if$a */
    public static class a implements b {
    }

    /* JADX INFO: renamed from: spi.if$b */
    public interface b {
    }

    public Cif(sd sdVar, int i) {
        this(sdVar, i, g);
    }

    @VisibleForTesting
    public Cif(sd sdVar, int i, b bVar) {
        this.a = sdVar;
        this.b = i;
        this.c = bVar;
    }

    public static int c(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getResponseCode();
        } catch (IOException e) {
            if (!Log.isLoggable("HttpUrlFetcher", 3)) {
                return -1;
            }
            Log.d("HttpUrlFetcher", "Failed to get a response code", e);
            return -1;
        }
    }

    @Override // spi.m5
    @NonNull
    public final Class<InputStream> a() {
        return InputStream.class;
    }

    @Override // spi.m5
    public final void b() {
        InputStream inputStream = this.e;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
        HttpURLConnection httpURLConnection = this.d;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        this.d = null;
    }

    @Override // spi.m5
    public final void cancel() {
        this.f = true;
    }

    @Override // spi.m5
    public final void d(@NonNull Priority priority, @NonNull m5.a<? super InputStream> aVar) {
        StringBuilder sb;
        int i = ch.b;
        long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        try {
            try {
                sd sdVar = this.a;
                if (sdVar.f == null) {
                    sdVar.f = new URL(sdVar.d());
                }
                aVar.f(f(sdVar.f, 0, null, this.a.b.a()));
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
                sb.append(ch.a(jElapsedRealtimeNanos));
                Log.v("HttpUrlFetcher", sb.toString());
            }
        } catch (Throwable th) {
            if (Log.isLoggable("HttpUrlFetcher", 2)) {
                StringBuilder sbK = e.k("Finished http url fetcher fetch in ");
                sbK.append(ch.a(jElapsedRealtimeNanos));
                Log.v("HttpUrlFetcher", sbK.toString());
            }
            throw th;
        }
    }

    @Override // spi.m5
    @NonNull
    public final DataSource e() {
        return DataSource.REMOTE;
    }

    public final InputStream f(URL url, int i, URL url2, Map<String, String> map) throws HttpException {
        InputStream inputStream;
        if (i >= 5) {
            throw new HttpException("Too many (> 5) redirects!", -1);
        }
        if (url2 != null) {
            try {
                if (url.toURI().equals(url2.toURI())) {
                    throw new HttpException("In re-direct loop", -1);
                }
            } catch (URISyntaxException unused) {
            }
        }
        try {
            Objects.requireNonNull((a) this.c);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
            }
            httpURLConnection.setConnectTimeout(this.b);
            httpURLConnection.setReadTimeout(this.b);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            this.d = httpURLConnection;
            try {
                httpURLConnection.connect();
                this.e = this.d.getInputStream();
                if (this.f) {
                    return null;
                }
                int iC = c(this.d);
                int i2 = iC / 100;
                if (i2 == 2) {
                    HttpURLConnection httpURLConnection2 = this.d;
                    try {
                        if (TextUtils.isEmpty(httpURLConnection2.getContentEncoding())) {
                            inputStream = l4.B(httpURLConnection2.getInputStream(), httpURLConnection2.getContentLength());
                        } else {
                            if (Log.isLoggable("HttpUrlFetcher", 3)) {
                                Log.d("HttpUrlFetcher", "Got non empty content encoding: " + httpURLConnection2.getContentEncoding());
                            }
                            inputStream = httpURLConnection2.getInputStream();
                        }
                        this.e = inputStream;
                        return inputStream;
                    } catch (IOException e) {
                        throw new HttpException("Failed to obtain InputStream", c(httpURLConnection2), e);
                    }
                }
                if (!(i2 == 3)) {
                    if (iC == -1) {
                        throw new HttpException(iC);
                    }
                    try {
                        throw new HttpException(this.d.getResponseMessage(), iC);
                    } catch (IOException e2) {
                        throw new HttpException("Failed to get a response message", iC, e2);
                    }
                }
                String headerField = this.d.getHeaderField("Location");
                if (TextUtils.isEmpty(headerField)) {
                    throw new HttpException("Received empty or null redirect url", iC);
                }
                try {
                    URL url3 = new URL(url, headerField);
                    b();
                    return f(url3, i + 1, url, map);
                } catch (MalformedURLException e3) {
                    throw new HttpException(d.f("Bad redirect url: ", headerField), iC, e3);
                }
            } catch (IOException e4) {
                throw new HttpException("Failed to connect or obtain data", c(this.d), e4);
            }
        } catch (IOException e5) {
            throw new HttpException("URL.openConnection threw", 0, e5);
        }
    }
}
