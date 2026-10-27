package com.amazon.identity.auth.device.api;

import android.annotation.TargetApi;
import com.amazon.identity.auth.device.db;
import com.amazon.identity.auth.device.eb;
import com.amazon.identity.auth.device.f6;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthenticatedHttpURLConnection extends f6 {
    public final HttpURLConnection e;
    public final AuthenticationMethod f;
    public final Object[] g;
    public eb h;

    public AuthenticatedHttpURLConnection(URL url, HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) {
        super(url);
        this.g = new Object[0];
        this.f = authenticationMethod;
        this.e = httpURLConnection;
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.e.addRequestProperty(str, str2);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.e.getAllowUserInteraction();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public int getConnectTimeout() {
        return this.e.getConnectTimeout();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.e.getDefaultUseCaches();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDoInput() {
        return this.e.getDoInput();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDoOutput() {
        return this.e.getDoOutput();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public long getIfModifiedSince() {
        return this.e.getIfModifiedSince();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.e.getInstanceFollowRedirects();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        eb ebVar;
        synchronized (this.g) {
            if (this.h == null) {
                this.h = new eb(this.e);
            }
            ebVar = this.h;
        }
        return ebVar;
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public int getReadTimeout() {
        return this.e.getReadTimeout();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.e.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.e.getRequestProperties();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.e.getRequestProperty(str);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public URL getURL() {
        return this.e.getURL();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getUseCaches() {
        return this.e.getUseCaches();
    }

    @Override // com.amazon.identity.auth.device.f6
    public HttpURLConnection performOnConnectionRequested() throws IOException {
        this.f.a(new db(this, this) { // from class: com.amazon.identity.auth.device.api.AuthenticatedHttpURLConnection.1
            @Override // com.amazon.identity.auth.device.db
            public byte[] getBody() {
                byte[] byteArray;
                AuthenticatedHttpURLConnection authenticatedHttpURLConnection = (AuthenticatedHttpURLConnection) getUrlConnection();
                synchronized (authenticatedHttpURLConnection.g) {
                    eb ebVar = authenticatedHttpURLConnection.h;
                    if (ebVar == null) {
                        byteArray = new byte[0];
                    } else {
                        synchronized (ebVar.f300a) {
                            byteArray = ebVar.b.toByteArray();
                        }
                    }
                }
                return byteArray;
            }
        });
        synchronized (this.g) {
            eb ebVar = this.h;
            if (ebVar != null) {
                synchronized (ebVar.f300a) {
                    OutputStream outputStream = ebVar.c.getOutputStream();
                    ebVar.d = outputStream;
                    outputStream.write(ebVar.b.toByteArray());
                    ebVar.flush();
                }
            }
        }
        return this.e;
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.e.setAllowUserInteraction(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.e.setChunkedStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.e.setConnectTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.e.setDefaultUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDoInput(boolean z) {
        this.e.setDoInput(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.e.setDoOutput(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.e.setFixedLengthStreamingMode(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.e.setIfModifiedSince(j);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.e.setInstanceFollowRedirects(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setReadTimeout(int i) {
        this.e.setReadTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.e.setRequestMethod(str);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.e.setRequestProperty(str, str2);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.e.setUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public String toString() {
        return this.e.toString();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.e.usingProxy();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    @TargetApi(19)
    public void setFixedLengthStreamingMode(long j) {
        this.e.setFixedLengthStreamingMode(j);
    }
}
