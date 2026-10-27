package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.dom_distiller.content.DistillablePageUtils;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabDistillabilityProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class kz2 extends nh0 implements DistillablePageUtils.PageDistillableDelegate, lk3 {
    public final b<a> e = new b<>();
    public Tab f;
    public boolean g;
    public WebContents h;
    public boolean i;
    public boolean j;
    public boolean k;

    /* JADX INFO: compiled from: TabDistillabilityProvider.java */
    public interface a {
        void a(Tab tab, boolean z, boolean z2, boolean z3);
    }

    public kz2(TabImpl tabImpl) {
        this.f = tabImpl;
        y1();
        this.f.x(this);
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            return;
        }
        y1();
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.clear();
        this.f.t(this);
        this.f = null;
        this.h = null;
        y1();
    }

    @Override // org.chromium.components.dom_distiller.content.DistillablePageUtils.PageDistillableDelegate
    public final void n1(boolean z, boolean z2, boolean z3) {
        this.i = z;
        this.j = z2;
        this.k = z3;
        this.g = true;
        Iterator<a> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a) aVar.next()).a(this.f, this.i, this.j, this.k);
            }
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        y1();
    }

    public final void y1() {
        this.g = false;
        this.i = false;
        this.j = false;
        this.k = false;
        Tab tab = this.f;
        if (tab == null || tab.c() == null || this.f.c() == this.h) {
            return;
        }
        WebContents webContentsC = this.f.c();
        this.h = webContentsC;
        N.MFtP575Y(webContentsC, this);
    }
}
