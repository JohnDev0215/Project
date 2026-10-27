package org.chromium.chrome.browser.metrics;

import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes2.dex */
public class PageLoadMetrics {
    public static final b<a> a = new b<>();
    public static final b<a> b = new b<>();

    @CalledByNative
    public static void onActivation(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).getClass();
            }
        }
    }

    @CalledByNative
    public static void onFirstContentfulPaint(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(webContents, j, j2, j3);
            }
        }
    }

    @CalledByNative
    public static void onFirstInputDelay(WebContents webContents, long j, long j2) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).e(webContents, j, j2);
            }
        }
    }

    @CalledByNative
    public static void onFirstMeaningfulPaint(WebContents webContents, long j, long j2, long j3) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).g(webContents);
            }
        }
    }

    @CalledByNative
    public static void onLargestContentfulPaint(WebContents webContents, long j, long j2, long j3, long j4) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = a.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).i(webContents, j, j2, j3, j4);
            }
        }
    }

    @CalledByNative
    public static void onLayoutShiftScore(WebContents webContents, long j, float f, float f2, boolean z) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = (z ? b : a).iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).h(webContents, j, f, f2);
            }
        }
    }

    @CalledByNative
    public static void onLoadEventStart(WebContents webContents, long j, long j2, long j3, boolean z) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = (z ? b : a).iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).d(webContents, j, j2, j3);
            }
        }
    }

    @CalledByNative
    public static void onLoadedMainResource(WebContents webContents, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, boolean z) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = (z ? b : a).iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).c(webContents, j, j2, j3, j4, j5, j6, j7, j8);
            }
        }
    }

    @CalledByNative
    public static void onNetworkQualityEstimate(WebContents webContents, long j, int i, long j2, long j3, boolean z) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = (z ? b : a).iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).b(webContents, j, i, j2, j3);
            }
        }
    }

    @CalledByNative
    public static void onNewNavigation(WebContents webContents, long j, boolean z, boolean z2) {
        Object obj = ThreadUtils.a;
        Iterator<a> it = (z2 ? b : a).iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).f(webContents, j, z);
            }
        }
    }

    public interface a {
        default void g(WebContents webContents) {
        }

        default void e(WebContents webContents, long j, long j2) {
        }

        default void f(WebContents webContents, long j, boolean z) {
        }

        default void a(WebContents webContents, long j, long j2, long j3) {
        }

        default void d(WebContents webContents, long j, long j2, long j3) {
        }

        default void h(WebContents webContents, long j, float f, float f2) {
        }

        default void b(WebContents webContents, long j, int i, long j2, long j3) {
        }

        default void i(WebContents webContents, long j, long j2, long j3, long j4) {
        }

        default void c(WebContents webContents, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8) {
        }
    }
}
