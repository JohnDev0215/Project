package defpackage;

import java.util.concurrent.Callable;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: FirstMeaningfulPaintObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class an0 implements PageLoadMetrics.a {
    public final Tab a;
    public final b40 b;

    public an0(b40 b40Var, Tab tab) {
        this.b = b40Var;
        this.a = tab;
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void g(WebContents webContents) {
        Tab tab = this.a;
        if (webContents != tab.c()) {
            return;
        }
        final bl1 bl1Var = this.b.h;
        bl1Var.e = true;
        bl1Var.a(tab, new Callable() { // from class: yk1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Boolean.valueOf(bl1Var.d);
            }
        }, 3000L);
    }
}
