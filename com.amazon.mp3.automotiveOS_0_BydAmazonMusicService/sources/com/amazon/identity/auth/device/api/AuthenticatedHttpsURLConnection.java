package com.amazon.identity.auth.device.api;

import android.annotation.TargetApi;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthenticatedHttpsURLConnection extends HttpsURLConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HttpsURLConnection f196a;
    public final AuthenticatedHttpURLConnection b;

    public AuthenticatedHttpsURLConnection(URL url, HttpsURLConnection httpsURLConnection, AuthenticationMethod authenticationMethod) {
        super(url);
        this.f196a = httpsURLConnection;
        this.b = new AuthenticatedHttpURLConnection(url, httpsURLConnection, authenticationMethod);
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

    @Override // javax.net.ssl.HttpsURLConnection
    public String getCipherSuite() {
        return this.f196a.getCipherSuite();
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
        return this.b.getErrorStream();
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

    @Override // javax.net.ssl.HttpsURLConnection
    public HostnameVerifier getHostnameVerifier() {
        return this.f196a.getHostnameVerifier();
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.b.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        return this.b.getInputStream();
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.b.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        return this.b.getLastModified();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getLocalCertificates() {
        return this.f196a.getLocalCertificates();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getLocalPrincipal() {
        return this.f196a.getLocalPrincipal();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.b.getOutputStream();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.f196a.getPeerPrincipal();
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
        return this.b.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public SSLSocketFactory getSSLSocketFactory() {
        return this.f196a.getSSLSocketFactory();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.f196a.getServerCertificates();
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
        this.b.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.b.setFixedLengthStreamingMode(i);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f196a.setHostnameVerifier(hostnameVerifier);
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

    @Override // javax.net.ssl.HttpsURLConnection
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f196a.setSSLSocketFactory(sSLSocketFactory);
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

    @Override // java.net.HttpURLConnection
    @TargetApi(19)
    public void setFixedLengthStreamingMode(long j) {
        this.b.setFixedLengthStreamingMode(j);
    }
}
