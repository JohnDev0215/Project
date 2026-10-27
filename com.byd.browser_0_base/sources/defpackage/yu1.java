package defpackage;

import android.os.Bundle;
import androidx.browser.customtabs.CustomTabsSessionToken;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PageLoadMetricsObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class yu1 implements PageLoadMetrics.a {
    public final CustomTabsConnection a;
    public final CustomTabsSessionToken b;
    public final Tab c;
    public Long d;

    public yu1(CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken, Tab tab) {
        this.a = customTabsConnection;
        this.b = customTabsSessionToken;
        this.c = tab;
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void a(WebContents webContents, long j, long j2, long j3) {
        if (j(j, webContents)) {
            CustomTabsConnection customTabsConnection = this.a;
            customTabsConnection.getClass();
            Bundle bundle = new Bundle();
            bundle.putLong("firstContentfulPaint", j3);
            bundle.putLong("navigationStart", j2 / 1000);
            customTabsConnection.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void b(WebContents webContents, long j, int i, long j2, long j3) {
        if (j(j, webContents)) {
            Bundle bundle = new Bundle();
            bundle.putLong("effectiveConnectionType", i);
            bundle.putLong("httpRtt", j2);
            bundle.putLong("transportRtt", j3);
            this.a.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void c(WebContents webContents, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        if (j(j, webContents)) {
            Bundle bundle = new Bundle();
            bundle.putLong("domainLookupStart", j2);
            bundle.putLong("domainLookupEnd", j3);
            bundle.putLong("connectStart", j4);
            bundle.putLong("connectEnd", j5);
            bundle.putLong("requestStart", j6);
            bundle.putLong("sendStart", j7);
            bundle.putLong("sendEnd", j8);
            this.a.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void d(WebContents webContents, long j, long j2, long j3) {
        if (j(j, webContents)) {
            CustomTabsConnection customTabsConnection = this.a;
            customTabsConnection.getClass();
            Bundle bundle = new Bundle();
            bundle.putLong("loadEventStart", j3);
            bundle.putLong("navigationStart", j2 / 1000);
            customTabsConnection.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void e(WebContents webContents, long j, long j2) {
        if (j(j, webContents)) {
            Bundle bundle = new Bundle();
            bundle.putLong("firstInputDelay", j2);
            this.a.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void f(WebContents webContents, long j, boolean z) {
        if (z && webContents == this.c.c()) {
            this.d = Long.valueOf(j);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void h(WebContents webContents, long j, float f, float f2) {
        if (j(j, webContents)) {
            Bundle bundle = new Bundle();
            bundle.putFloat("layoutShiftScore", f2);
            bundle.putFloat("layoutShiftScoreBeforeInputOrScroll", f);
            this.a.o(this.b, bundle);
        }
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void i(WebContents webContents, long j, long j2, long j3, long j4) {
        if (j(j, webContents)) {
            CustomTabsConnection customTabsConnection = this.a;
            customTabsConnection.getClass();
            Bundle bundle = new Bundle();
            bundle.putLong("largestContentfulPaint", j3);
            bundle.putLong("navigationStart", j2 / 1000);
            bundle.putLong("largestContentfulPaintSize", j4);
            customTabsConnection.o(this.b, bundle);
        }
    }

    public final boolean j(long j, WebContents webContents) {
        Long l;
        return webContents == this.c.c() && (l = this.d) != null && j == l.longValue();
    }
}
