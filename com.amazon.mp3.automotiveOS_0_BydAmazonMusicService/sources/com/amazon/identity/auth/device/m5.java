package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import com.amazon.identity.auth.device.framework.RetryLogic;
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
public class m5 extends HttpsURLConnection {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l5 f429a;
    public final b5 b;
    public final RetryLogic c;

    public m5(URL url, RetryLogic retryLogic, y5 y5Var, Context context) throws IOException {
        super(url);
        b5 b5Var = new b5(url);
        this.b = b5Var;
        this.c = retryLogic;
        this.f429a = new l5(b5Var, retryLogic, y5Var, context);
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.f429a.e.a(str, str2, false);
    }

    @Override // java.net.URLConnection
    public void connect() throws IOException {
        this.f429a.connect();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        this.f429a.disconnect();
    }

    @Override // java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.f429a.getAllowUserInteraction();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public String getCipherSuite() {
        return this.b.g.getCipherSuite();
    }

    @Override // java.net.URLConnection
    public int getConnectTimeout() {
        return this.f429a.getConnectTimeout();
    }

    @Override // java.net.URLConnection
    public Object getContent() throws IOException {
        return this.f429a.getContent();
    }

    @Override // java.net.URLConnection
    public String getContentEncoding() {
        return this.f429a.getContentEncoding();
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        return this.f429a.getContentLength();
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        return this.f429a.getContentType();
    }

    @Override // java.net.URLConnection
    public long getDate() {
        return this.f429a.getDate();
    }

    @Override // java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.f429a.getDefaultUseCaches();
    }

    @Override // java.net.URLConnection
    public boolean getDoInput() {
        return this.f429a.getDoInput();
    }

    @Override // java.net.URLConnection
    public boolean getDoOutput() {
        return this.f429a.getDoOutput();
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        return this.f429a.getErrorStream();
    }

    @Override // java.net.URLConnection
    public long getExpiration() {
        return this.f429a.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        return this.f429a.getHeaderField(i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        return this.f429a.getHeaderFieldDate(str, j);
    }

    @Override // java.net.URLConnection
    public int getHeaderFieldInt(String str, int i) {
        return this.f429a.getHeaderFieldInt(str, i);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderFieldKey(int i) {
        return this.f429a.getHeaderFieldKey(i);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        return this.f429a.getHeaderFields();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public HostnameVerifier getHostnameVerifier() {
        return this.b.g.getHostnameVerifier();
    }

    @Override // java.net.URLConnection
    public long getIfModifiedSince() {
        return this.f429a.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() throws IOException {
        return this.f429a.getInputStream();
    }

    @Override // java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.f429a.getInstanceFollowRedirects();
    }

    @Override // java.net.URLConnection
    public long getLastModified() {
        return this.f429a.getLastModified();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getLocalCertificates() {
        return this.b.g.getLocalCertificates();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getLocalPrincipal() {
        return this.b.g.getLocalPrincipal();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.f429a.e.c;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.b.g.getPeerPrincipal();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() throws IOException {
        return this.f429a.getPermission();
    }

    @Override // java.net.URLConnection
    public int getReadTimeout() {
        return this.f429a.getReadTimeout();
    }

    @Override // java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.f429a.getRequestMethod();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.f429a.getRequestProperties();
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        return this.f429a.getRequestProperty(str);
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() throws IOException {
        return this.f429a.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() throws IOException {
        return this.f429a.getResponseMessage();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public SSLSocketFactory getSSLSocketFactory() {
        return this.b.g.getSSLSocketFactory();
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.b.g.getServerCertificates();
    }

    @Override // java.net.URLConnection
    public URL getURL() {
        return this.f429a.getURL();
    }

    @Override // java.net.URLConnection
    public boolean getUseCaches() {
        return this.f429a.getUseCaches();
    }

    @Override // java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.f429a.e.f171a.setAllowUserInteraction(z);
    }

    @Override // java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.f429a.e.d = Integer.valueOf(i);
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.f429a.e.f171a.setConnectTimeout(i);
    }

    @Override // java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.f429a.e.f171a.setDefaultUseCaches(z);
    }

    @Override // java.net.URLConnection
    public void setDoInput(boolean z) {
        this.f429a.e.f171a.setDoInput(z);
    }

    @Override // java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.f429a.e.f171a.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.f429a.e.e = Long.valueOf(i);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.b.g.setHostnameVerifier(hostnameVerifier);
    }

    @Override // java.net.URLConnection
    public void setIfModifiedSince(long j) {
        this.f429a.e.f171a.setIfModifiedSince(j);
    }

    @Override // java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.f429a.e.f171a.setInstanceFollowRedirects(z);
    }

    @Override // java.net.URLConnection
    public void setReadTimeout(int i) {
        this.f429a.e.f171a.setReadTimeout(i);
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.f429a.e.f171a.setRequestMethod(str);
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.f429a.e.a(str, str2, true);
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.b.g.setSSLSocketFactory(sSLSocketFactory);
    }

    @Override // java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.f429a.e.f171a.setUseCaches(z);
    }

    @Override // java.net.URLConnection
    public String toString() {
        return this.f429a.toString();
    }

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.f429a.usingProxy();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) throws IOException {
        return this.f429a.getContent(clsArr);
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        return this.f429a.getHeaderField(str);
    }

    @Override // java.net.HttpURLConnection
    @TargetApi(19)
    public void setFixedLengthStreamingMode(long j) {
        this.f429a.e.e = Long.valueOf(j);
    }
}
