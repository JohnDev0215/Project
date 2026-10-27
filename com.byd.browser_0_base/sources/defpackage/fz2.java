package defpackage;

import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabCreator.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class fz2 {
    public abstract Tab a(m33 m33Var, ui2 ui2Var, int i, boolean z, int i2);

    public abstract Tab b(int i, Tab tab, LoadUrlParams loadUrlParams);

    public abstract Tab c(LoadUrlParams loadUrlParams, int i);

    public abstract boolean d(Tab tab, WebContents webContents, int i, GURL gurl);

    public abstract boolean e();

    public final void f(int i) {
        try {
            TraceEvent.q("TabCreator.launchNTP", null);
            g(i, "chrome-native://newtab/");
        } finally {
            TraceEvent.C("TabCreator.launchNTP");
        }
    }

    public abstract Tab g(int i, String str);
}
