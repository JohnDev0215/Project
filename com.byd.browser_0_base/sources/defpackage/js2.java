package defpackage;

import android.os.Handler;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: StartupPaintPreviewHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class js2 implements PageLoadMetrics.a {
    public final /* synthetic */ hs2 a;

    public js2(hs2 hs2Var) {
        this.a = hs2Var;
    }

    @Override // org.chromium.chrome.browser.metrics.PageLoadMetrics.a
    public final void g(WebContents webContents) {
        hs2 hs2Var = this.a;
        Tab tab = hs2Var.a;
        if (tab == null || tab.c() != webContents) {
            return;
        }
        hs2Var.h = true;
        oj2.O("Browser.PaintPreview.TabbedPlayer.FirstPaintBeforeTabLoad", hs2Var.b.b);
        if (hs2Var.g != 1) {
            return;
        }
        new Handler().postDelayed(new ql0(24, hs2Var), 0L);
    }
}
