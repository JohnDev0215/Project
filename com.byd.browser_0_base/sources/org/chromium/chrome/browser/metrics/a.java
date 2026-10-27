package org.chromium.chrome.browser.metrics;

import android.os.SystemClock;
import defpackage.d3;
import defpackage.g23;
import defpackage.ko0;
import defpackage.oj2;
import java.util.concurrent.atomic.AtomicLong;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.components.safe_browsing.SafeBrowsingApiBridge;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ActivityTabStartupMetricsTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public long b;
    public int c;
    public d3 d;
    public C0094a e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public final AtomicLong k = new AtomicLong();
    public final long a = SystemClock.uptimeMillis();

    /* JADX INFO: renamed from: org.chromium.chrome.browser.metrics.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityTabStartupMetricsTracker.java */
    public class C0094a implements PageLoadMetrics.a {
        public long a = -1;
        public boolean b;

        public C0094a() {
        }

        @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
        public final void a(WebContents webContents, long j, long j2, long j3) {
            if (j == this.a && this.b) {
                long j4 = (j2 / 1000) + j3;
                a aVar = a.this;
                if (aVar.b == 0) {
                    return;
                }
                if (UmaUtils.c() && !UmaUtils.b()) {
                    long j5 = j4 - aVar.a;
                    oj2.Y(j5, "Startup.Android.Cold.TimeToFirstContentfulPaint".concat(aVar.c == 0 ? ".Tabbed" : ".WebApk"));
                    if (aVar.c == 0 && !aVar.h) {
                        aVar.h = true;
                        oj2.Y(j5, "Startup.Android.Cold.TimeToVisibleContent");
                    }
                }
                aVar.f = false;
                d3 d3Var = aVar.d;
                if (d3Var != null) {
                    d3Var.destroy();
                    aVar.d = null;
                }
                C0094a c0094a = aVar.e;
                if (c0094a != null) {
                    b<PageLoadMetrics.a> bVar = PageLoadMetrics.a;
                    Object obj = ThreadUtils.a;
                    PageLoadMetrics.a.c(c0094a);
                    PageLoadMetrics.b.c(c0094a);
                    aVar.e = null;
                }
            }
        }

        @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
        public final void f(WebContents webContents, long j, boolean z) {
            if (this.a != -1) {
                return;
            }
            this.a = j;
            this.b = a.this.f;
        }
    }

    public a(g23 g23Var) {
        g23Var.p(new ko0(14, this));
        synchronized (SafeBrowsingApiBridge.a) {
        }
    }
}
