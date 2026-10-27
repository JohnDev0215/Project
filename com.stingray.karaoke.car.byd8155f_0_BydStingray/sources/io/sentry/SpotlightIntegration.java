package io.sentry;

import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

/* JADX INFO: loaded from: classes.dex */
public final class SpotlightIntegration implements Z, InterfaceC1522w1, Closeable {
    public F1 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public K f15954m = C1521w0.l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public S f15955n = C1524x0.o;

    public static void b(HttpURLConnection httpURLConnection) {
        try {
            httpURLConnection.getInputStream().close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            httpURLConnection.disconnect();
            throw th;
        }
        httpURLConnection.disconnect();
    }

    public static HttpURLConnection g(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) URI.create(str).toURL().openConnection();
        httpURLConnection.setReadTimeout(1000);
        httpURLConnection.setConnectTimeout(1000);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/x-sentry-envelope");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Connection", "close");
        httpURLConnection.connect();
        return httpURLConnection;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f15955n.l(0L);
        F1 f12 = this.l;
        if (f12 == null || f12.getBeforeEnvelopeCallback() != this) {
            return;
        }
        this.l.setBeforeEnvelopeCallback(null);
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.l = f12;
        this.f15954m = f12.getLogger();
        if (f12.getBeforeEnvelopeCallback() != null || !f12.isEnableSpotlight()) {
            this.f15954m.k(EnumC1495p1.DEBUG, "SpotlightIntegration is not enabled. BeforeEnvelopeCallback is already set or spotlight is not enabled.", new Object[0]);
            return;
        }
        this.f15955n = new e7.f(13, (byte) 0);
        f12.setBeforeEnvelopeCallback(this);
        this.f15954m.k(EnumC1495p1.DEBUG, "SpotlightIntegration enabled.", new Object[0]);
    }
}
