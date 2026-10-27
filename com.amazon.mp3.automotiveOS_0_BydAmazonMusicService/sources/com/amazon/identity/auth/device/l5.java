package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.framework.RetryLogic;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class l5 extends f6 {
    public static final String j = l5.class.getName();
    public static final int k;
    public static final int l;
    public final a5 e;
    public final RetryLogic f;
    public final cd g;
    public final y5 h;
    public final Context i;

    static {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        TimeUnit timeUnit2 = TimeUnit.SECONDS;
        k = (int) timeUnit.convert(1L, timeUnit2);
        l = (int) timeUnit.convert(10L, timeUnit2);
    }

    public l5(a5 a5Var, RetryLogic retryLogic, y5 y5Var, Context context) {
        super(a5Var.c());
        this.e = a5Var;
        this.f = retryLogic;
        this.g = new cd(k, l);
        this.h = y5Var;
        this.i = context;
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void addRequestProperty(String str, String str2) {
        this.e.a(str, str2, false);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getAllowUserInteraction() {
        return this.e.f171a.getAllowUserInteraction();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public int getConnectTimeout() {
        return this.e.f171a.getConnectTimeout();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDefaultUseCaches() {
        return this.e.f171a.getDefaultUseCaches();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDoInput() {
        return this.e.f171a.getDoInput();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getDoOutput() {
        return this.e.f171a.getDoOutput();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public long getIfModifiedSince() {
        return this.e.f171a.getIfModifiedSince();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public boolean getInstanceFollowRedirects() {
        return this.e.f171a.getInstanceFollowRedirects();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public OutputStream getOutputStream() throws IOException {
        return this.e.c;
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public int getReadTimeout() {
        return this.e.f171a.getReadTimeout();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public String getRequestMethod() {
        return this.e.f171a.getRequestMethod();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return Collections.unmodifiableMap(this.e.b);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public String getRequestProperty(String str) {
        List<String> list = this.e.b.get(str);
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public URL getURL() {
        return this.e.f171a.getURL();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public boolean getUseCaches() {
        return this.e.f171a.getUseCaches();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
    @Override // com.amazon.identity.auth.device.f6
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.net.HttpURLConnection performOnConnectionRequested() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.l5.performOnConnectionRequested():java.net.HttpURLConnection");
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setAllowUserInteraction(boolean z) {
        this.e.f171a.setAllowUserInteraction(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setChunkedStreamingMode(int i) {
        this.e.d = Integer.valueOf(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setConnectTimeout(int i) {
        this.e.f171a.setConnectTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDefaultUseCaches(boolean z) {
        this.e.f171a.setDefaultUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDoInput(boolean z) {
        this.e.f171a.setDoInput(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setDoOutput(boolean z) {
        this.e.f171a.setDoOutput(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.e.e = Long.valueOf(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setIfModifiedSince(long j2) {
        this.e.f171a.setIfModifiedSince(j2);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setInstanceFollowRedirects(boolean z) {
        this.e.f171a.setInstanceFollowRedirects(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setReadTimeout(int i) {
        this.e.f171a.setReadTimeout(i);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setRequestMethod(String str) throws ProtocolException {
        this.e.f171a.setRequestMethod(str);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setRequestProperty(String str, String str2) {
        this.e.a(str, str2, true);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public void setUseCaches(boolean z) {
        this.e.f171a.setUseCaches(z);
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.URLConnection
    public String toString() {
        return this.e.f171a.toString();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public boolean usingProxy() {
        return this.e.f171a.usingProxy();
    }

    @Override // com.amazon.identity.auth.device.f6, java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j2) {
        this.e.e = Long.valueOf(j2);
    }
}
