package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import android.util.Log;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.db;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.va;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthenticatedURLConnection extends HttpsURLConnection {
    public static final String b = AuthenticatedURLConnection.class.getName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AuthenticatedHttpsURLConnection f197a;

    /* JADX INFO: compiled from: DCP */
    public static class AccountNeedsRecoveryException extends IOException {
        private final Bundle mAccountRecoverContextBundle;

        @FireOsSdk
        public AccountNeedsRecoveryException(String str, Bundle bundle) {
            super(str);
            this.mAccountRecoverContextBundle = bundle;
        }

        @FireOsSdk
        public final Bundle getAccountRecoverContextBundle() {
            return this.mAccountRecoverContextBundle;
        }

        public final Bundle getExtraBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle("com.amazon.identity.mobi.account.recover.context", this.mAccountRecoverContextBundle);
            return bundle;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class AuthenticationFailureIOException extends IOException {
        @FireOsSdk
        public AuthenticationFailureIOException(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class NoCredentialsException extends IOException {
        private static final long serialVersionUID = 1;

        @FireOsSdk
        public NoCredentialsException(String str) {
            super(str);
        }
    }

    @Deprecated
    public AuthenticatedURLConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        super(url);
        this.f197a = (AuthenticatedHttpsURLConnection) openConnection(url, authenticationMethod);
    }

    @FireOsSdk
    public static HttpURLConnection openConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("Must Specify a URL");
        }
        if (authenticationMethod == null) {
            throw new IllegalArgumentException("Must Specify an Authentication Method");
        }
        URLConnection uRLConnectionA = e.a(url);
        if (uRLConnectionA instanceof HttpURLConnection) {
            return openConnection((HttpURLConnection) uRLConnectionA, authenticationMethod);
        }
        throw new IllegalArgumentException("Url must be an Https or Http Url");
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void addRequestProperty(String str, String str2) {
        this.f197a.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void connect() throws IOException {
        try {
            this.f197a.connect();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                Log.e(ga.a(b), "SSL Handshake fail when establishing connect", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void disconnect() {
        this.f197a.disconnect();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getAllowUserInteraction() {
        return this.f197a.getAllowUserInteraction();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public String getCipherSuite() {
        return this.f197a.getCipherSuite();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getConnectTimeout() {
        return this.f197a.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Object getContent() throws IOException {
        return this.f197a.getContent();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getContentEncoding() {
        return this.f197a.getContentEncoding();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getContentLength() {
        return this.f197a.getContentLength();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getContentType() {
        return this.f197a.getContentType();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getDate() {
        return this.f197a.getDate();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDefaultUseCaches() {
        return this.f197a.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDoInput() {
        return this.f197a.getDoInput();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getDoOutput() {
        return this.f197a.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public InputStream getErrorStream() {
        return this.f197a.getErrorStream();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getExpiration() {
        return this.f197a.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public String getHeaderField(int i) {
        return this.f197a.getHeaderField(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public long getHeaderFieldDate(String str, long j) {
        return this.f197a.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getHeaderFieldInt(String str, int i) {
        return this.f197a.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public String getHeaderFieldKey(int i) {
        return this.f197a.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Map<String, List<String>> getHeaderFields() {
        return this.f197a.getHeaderFields();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public HostnameVerifier getHostnameVerifier() {
        return this.f197a.getHostnameVerifier();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getIfModifiedSince() {
        return this.f197a.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public InputStream getInputStream() throws IOException {
        try {
            return this.f197a.getInputStream();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                Log.e(ga.a(b), "SSL Handshake fail when getInputStream", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public boolean getInstanceFollowRedirects() {
        return this.f197a.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public long getLastModified() {
        return this.f197a.getLastModified();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Certificate[] getLocalCertificates() {
        return this.f197a.getLocalCertificates();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Principal getLocalPrincipal() {
        return this.f197a.getLocalPrincipal();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public OutputStream getOutputStream() throws IOException {
        try {
            return this.f197a.getOutputStream();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                Log.e(ga.a(b), "SSL HandShake fail when getOutputStream", e);
            }
            throw e;
        }
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.f197a.getPeerPrincipal();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    @FireOsSdk
    public Permission getPermission() throws IOException {
        return this.f197a.getPermission();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public int getReadTimeout() {
        return this.f197a.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public String getRequestMethod() {
        return this.f197a.getRequestMethod();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Map<String, List<String>> getRequestProperties() {
        return this.f197a.getRequestProperties();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getRequestProperty(String str) {
        return this.f197a.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public int getResponseCode() throws IOException {
        try {
            return this.f197a.getResponseCode();
        } catch (IOException e) {
            if (e instanceof SSLHandshakeException) {
                Log.e(ga.a(b), "SSL Handshake fail when getResponseCode", e);
            }
            throw e;
        }
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public String getResponseMessage() throws IOException {
        return this.f197a.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public SSLSocketFactory getSSLSocketFactory() {
        return this.f197a.getSSLSocketFactory();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.f197a.getServerCertificates();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public URL getURL() {
        return this.f197a.getURL();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public boolean getUseCaches() {
        return this.f197a.getUseCaches();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setAllowUserInteraction(boolean z) {
        this.f197a.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setChunkedStreamingMode(int i) {
        this.f197a.setChunkedStreamingMode(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setConnectTimeout(int i) {
        this.f197a.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDefaultUseCaches(boolean z) {
        this.f197a.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDoInput(boolean z) {
        this.f197a.setDoInput(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setDoOutput(boolean z) {
        this.f197a.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setFixedLengthStreamingMode(int i) {
        this.f197a.setFixedLengthStreamingMode(i);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f197a.setHostnameVerifier(hostnameVerifier);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setIfModifiedSince(long j) {
        this.f197a.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setInstanceFollowRedirects(boolean z) {
        this.f197a.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setReadTimeout(int i) {
        this.f197a.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public void setRequestMethod(String str) throws ProtocolException {
        this.f197a.setRequestMethod(str);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setRequestProperty(String str, String str2) {
        this.f197a.setRequestProperty(str, str2);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    @FireOsSdk
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f197a.setSSLSocketFactory(sSLSocketFactory);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public void setUseCaches(boolean z) {
        this.f197a.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String toString() {
        return this.f197a.toString();
    }

    @Override // java.net.HttpURLConnection
    @FireOsSdk
    public boolean usingProxy() {
        return this.f197a.usingProxy();
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public Object getContent(Class[] clsArr) throws IOException {
        return this.f197a.getContent(clsArr);
    }

    @Override // java.net.URLConnection
    @FireOsSdk
    public String getHeaderField(String str) {
        return this.f197a.getHeaderField(str);
    }

    @FireOsSdk
    public static HttpURLConnection openConnection(HttpURLConnection httpURLConnection, AuthenticationMethod authenticationMethod) throws IOException {
        if (httpURLConnection == null) {
            throw new IllegalArgumentException("Must Specify a URL");
        }
        if (authenticationMethod != null) {
            AuthenticationType authenticationType = AuthenticationType.parse(authenticationMethod.d);
            if (httpURLConnection instanceof HttpsURLConnection) {
                if (authenticationType == AuthenticationType.OAuth && !va.a()) {
                    authenticationMethod.a(new db(httpURLConnection) { // from class: com.amazon.identity.auth.device.api.AuthenticatedURLConnection.1
                        @Override // com.amazon.identity.auth.device.db
                        public byte[] getBody() {
                            return new byte[0];
                        }
                    });
                    return httpURLConnection;
                }
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                return new AuthenticatedHttpsURLConnection(httpsURLConnection.getURL(), httpsURLConnection, authenticationMethod);
            }
            if (authenticationType != null && authenticationType.requireHttps()) {
                throw new SecurityException(String.format("The authentication type %s can only be used over HTTPS. ", authenticationMethod.d));
            }
            URL url = httpURLConnection.getURL();
            if (url != null) {
                url.getHost();
            }
            Objects.toString(authenticationType);
            return new AuthenticatedHttpURLConnection(httpURLConnection.getURL(), httpURLConnection, authenticationMethod);
        }
        throw new IllegalArgumentException("Must Specify an Authentication Method");
    }
}
