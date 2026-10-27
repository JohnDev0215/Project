package defpackage;

import J.N;
import android.support.annotation.VisibleForTesting;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: TabStateAttributes.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p33 extends m53 {
    public final org.chromium.base.b<b> f;
    public final Tab g;
    public final CriticalPersistedTabData h;
    public final o33 i;
    public int j;
    public a k;
    public boolean l;

    /* JADX INFO: compiled from: TabStateAttributes.java */
    public class a extends rq3 {
        public a(WebContents webContents) {
            super(webContents);
        }

        @Override // defpackage.rq3
        public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
            p33.this.f(1);
        }

        @Override // defpackage.rq3
        public final void navigationEntriesChanged() {
            p33.this.f(1);
        }
    }

    /* JADX INFO: compiled from: TabStateAttributes.java */
    public interface b {
        void a(int i, Tab tab);
    }

    public p33(TabImpl tabImpl, Integer num) {
        super(tabImpl);
        this.f = new org.chromium.base.b<>();
        this.g = tabImpl;
        if (num == null || num.intValue() == 3) {
            this.j = 2;
        } else if (num.intValue() == 0 || num.intValue() == 1) {
            this.j = 1;
        } else {
            this.j = 0;
        }
        tabImpl.x(new n33(this));
        o33 o33Var = new o33(this);
        this.i = o33Var;
        CriticalPersistedTabData criticalPersistedTabDataM = CriticalPersistedTabData.m(tabImpl);
        this.h = criticalPersistedTabDataM;
        criticalPersistedTabDataM.x.a(o33Var);
    }

    public static p33 d(Tab tab) {
        return (p33) tab.B().b(p33.class);
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
        a aVar = this.k;
        if (aVar != null) {
            aVar.destroy();
            this.k = null;
        }
    }

    @Override // defpackage.m53
    public final void b() {
        this.h.x.c(this.i);
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        this.k = new a(webContents);
    }

    @VisibleForTesting
    public final void f(int i) {
        Tab tab = this.g;
        if (tab.isDestroyed() || i == this.j) {
            return;
        }
        sk skVar = bq.a;
        if (N.M09VlOh_("TabStateV1Optimizations") && tab.r()) {
            return;
        }
        this.j = i;
        if (i == 2) {
            CriticalPersistedTabData.m(tab).z = true;
        }
        Iterator<b> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).a(i, tab);
            }
        }
    }
}
