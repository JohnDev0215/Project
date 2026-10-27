package com.amazon.identity.auth.device;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class k5 extends HttpURLConnection {
    public static final String f = k5.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object[] f405a;
    public final HttpURLConnection b;
    public byte[] c;
    public IOException d;
    public byte[] e;

    public k5(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.f405a = new Object[0];
        this.b = httpURLConnection;
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.b.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        this.b.connect();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        this.b.disconnect();
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.b.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.b.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        return this.b.getContent();
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        return this.b.getContentEncoding();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        return this.b.getContentLength();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        return this.b.getContentType();
    }

    @Override // java.net.URLConnection
    public long getDate() {
        return this.b.getDate();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.b.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.b.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.b.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        ByteArrayInputStream byteArrayInputStream;
        synchronized (this.f405a) {
            if (this.e == null) {
                try {
                    try {
                        InputStream errorStream = this.b.getErrorStream();
                        if (errorStream != null) {
                            this.e = sa.a(errorStream);
                        } else {
                            Log.i(ga.a(f), "No Error Stream found");
                            this.e = new byte[0];
                        }
                        sa.a((Closeable) errorStream);
                    } catch (IOException unused) {
                        this.e = new byte[0];
                        sa.a((Closeable) null);
                    }
                } catch (Throwable th) {
                    sa.a((Closeable) null);
                    throw th;
                }
            }
            byteArrayInputStream = new ByteArrayInputStream(this.e);
        }
        return byteArrayInputStream;
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        return this.b.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        return this.b.getHeaderField(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        return this.b.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        return this.b.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        return this.b.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        return this.b.getHeaderFields();
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.b.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        synchronized (this.f405a) {
            if (this.c == null && this.d == null) {
                InputStream inputStream = null;
                try {
                    try {
                        inputStream = this.b.getInputStream();
                        this.c = sa.a(inputStream);
                    } catch (IOException e) {
                        this.c = new byte[0];
                        this.d = e;
                        throw e;
                    }
                } finally {
                    sa.a((Closeable) inputStream);
                }
            }
            IOException iOException = this.d;
            if (iOException != null) {
                throw iOException;
            }
            byteArrayInputStream = new ByteArrayInputStream(this.c);
        }
        return byteArrayInputStream;
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.b.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        return this.b.getLastModified();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.b.getOutputStream();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.b.getPermission();
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.b.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.b.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.b.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.b.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        return this.b.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return this.b.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.b.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.b.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.b.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.b.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.b.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.b.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.b.setDoInput(z);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.b.setDoInput(z);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.b.setFixedLengthStreamingMode(i);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.b.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.b.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int i) {
        this.b.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.b.setRequestMethod(str);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.b.setRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.b.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.b.toString();
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.b.usingProxy();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        return this.b.getContent(clsArr);
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        return this.b.getHeaderField(str);
    }
}
