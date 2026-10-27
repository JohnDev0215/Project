package com.amazon.identity.auth.device;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLHandshakeException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class f6 extends HttpURLConnection {
    public static final String d = f6.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HttpURLConnection f310a;
    public boolean b;
    public IOException c;

    public f6(URL url) {
        super(url);
        this.b = false;
        this.c = null;
    }

    public final synchronized void a() throws IOException {
        if (this.b) {
            return;
        }
        if (this.c != null) {
            Log.i(ga.a(d), "The first connection attempt ended in IOException so throwing the same");
            throw this.c;
        }
        try {
            HttpURLConnection httpURLConnectionPerformOnConnectionRequested = performOnConnectionRequested();
            this.f310a = httpURLConnectionPerformOnConnectionRequested;
            if (httpURLConnectionPerformOnConnectionRequested == null) {
                throw new IOException("Connection could not be established");
            }
            this.b = true;
        } catch (IOException e) {
            this.c = e;
            if (e instanceof SSLHandshakeException) {
                Log.e(ga.a(d), "SSL Handshake fail when performOnConnectionRequested", e);
            }
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public abstract void addRequestProperty(String str, String str2);

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        try {
            a();
            this.f310a.connect();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public synchronized void disconnect() {
        if (this.b) {
            this.f310a.disconnect();
        }
    }

    @Override // java.net.URLConnection
    public abstract boolean getAllowUserInteraction();

    @Override // java.net.URLConnection
    public abstract int getConnectTimeout();

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        try {
            a();
            return this.f310a.getContent();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        try {
            a();
            return this.f310a.getContentEncoding();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get Content Encoding", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        try {
            a();
            return this.f310a.getContentLength();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get Content Length", e);
            return 0;
        }
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        try {
            a();
            return this.f310a.getContentType();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get Content Type", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public long getDate() {
        try {
            a();
            return this.f310a.getDate();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get Date", e);
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public abstract boolean getDefaultUseCaches();

    @Override // java.net.URLConnection
    public abstract boolean getDoInput();

    @Override // java.net.URLConnection
    public abstract boolean getDoOutput();

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        HttpURLConnection httpURLConnection = this.f310a;
        if (httpURLConnection != null) {
            return httpURLConnection.getErrorStream();
        }
        return null;
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        try {
            a();
            return this.f310a.getExpiration();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get expiration", e);
            return 0L;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        try {
            a();
            return this.f310a.getHeaderField(i);
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header field", e);
            return null;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        try {
            a();
            return this.f310a.getHeaderFieldDate(str, j);
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header field date", e);
            return j;
        }
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        try {
            a();
            return this.f310a.getHeaderFieldInt(str, i);
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header field int", e);
            return i;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        try {
            a();
            return this.f310a.getHeaderFieldKey(i);
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header field key", e);
            return null;
        }
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        try {
            a();
            return this.f310a.getHeaderFields();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header fields", e);
            return new HashMap();
        }
    }

    @Override // java.net.URLConnection
    public abstract long getIfModifiedSince();

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        try {
            a();
            return this.f310a.getInputStream();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public abstract boolean getInstanceFollowRedirects();

    @Override // java.net.URLConnection
    public long getLastModified() {
        try {
            a();
            return this.f310a.getLastModified();
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get last modified date", e);
            return 0L;
        }
    }

    @Override // java.net.URLConnection
    public abstract OutputStream getOutputStream() throws IOException;

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        try {
            a();
            return this.f310a.getPermission();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public abstract int getReadTimeout();

    @Override // java.net.HttpURLConnection
    public abstract String getRequestMethod();

    @Override // java.net.URLConnection
    public abstract Map<String, List<String>> getRequestProperties();

    @Override // java.net.URLConnection
    public abstract String getRequestProperty(String str);

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        try {
            a();
            return this.f310a.getResponseCode();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        try {
            a();
            return this.f310a.getResponseMessage();
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public abstract URL getURL();

    @Override // java.net.URLConnection
    public abstract boolean getUseCaches();

    public abstract HttpURLConnection performOnConnectionRequested() throws IOException;

    @Override // java.net.URLConnection
    public abstract void setAllowUserInteraction(boolean z);

    @Override // java.net.HttpURLConnection
    public abstract void setChunkedStreamingMode(int i);

    @Override // java.net.URLConnection
    public abstract void setConnectTimeout(int i);

    @Override // java.net.URLConnection
    public abstract void setDefaultUseCaches(boolean z);

    @Override // java.net.URLConnection
    public abstract void setDoInput(boolean z);

    @Override // java.net.URLConnection
    public abstract void setDoOutput(boolean z);

    @Override // java.net.HttpURLConnection
    public abstract void setFixedLengthStreamingMode(int i);

    @Override // java.net.HttpURLConnection
    public abstract void setFixedLengthStreamingMode(long j);

    @Override // java.net.URLConnection
    public abstract void setIfModifiedSince(long j);

    @Override // java.net.HttpURLConnection
    public abstract void setInstanceFollowRedirects(boolean z);

    @Override // java.net.URLConnection
    public abstract void setReadTimeout(int i);

    @Override // java.net.HttpURLConnection
    public abstract void setRequestMethod(String str) throws ProtocolException;

    @Override // java.net.URLConnection
    public abstract void setRequestProperty(String str, String str2);

    @Override // java.net.URLConnection
    public abstract void setUseCaches(boolean z);

    @Override // java.net.URLConnection
    public abstract String toString();

    @Override // java.net.HttpURLConnection
    public abstract boolean usingProxy();

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        try {
            a();
            return this.f310a.getContent(clsArr);
        } catch (IOException e) {
            a(e);
            throw e;
        }
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        try {
            a();
            return this.f310a.getHeaderField(str);
        } catch (IOException e) {
            a(e);
            Log.e(ga.a(d), "Could not get header field", e);
            return null;
        }
    }

    public final void a(IOException iOException) {
        URL url;
        if (!(iOException instanceof SSLHandshakeException) || (url = getURL()) == null) {
            return;
        }
        url.getHost();
    }
}
