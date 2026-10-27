package defpackage;

import defpackage.z2;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ReadingListBackPressHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k92 implements vb, w80 {
    public final aq1<Boolean> e = new aq1<>();
    public final z2 f;
    public final a g;

    /* JADX INFO: compiled from: ReadingListBackPressHandler.java */
    public class a extends z2.b {
        public a(z2 z2Var) {
            super(z2Var, true);
        }

        @Override // z2.b
        public final void y1(Tab tab) {
            k92 k92Var = k92.this;
            Tab tab2 = (Tab) k92Var.f.f;
            k92Var.e.r(Boolean.valueOf(tab2 != null && tab2.getLaunchType() == 18));
        }
    }

    public k92(z2 z2Var) {
        this.f = z2Var;
        this.g = new a(z2Var);
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.g.destroy();
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e;
    }

    @Override // defpackage.vb
    public final int o() {
        Object obj = this.f.f;
        Tab tab = (Tab) obj;
        Tab tab2 = (Tab) obj;
        int i = ((tab2 == null || tab2.getLaunchType() != 18) ? 0 : 1) ^ 1;
        lh.d(null, new BookmarkId(2, 0L), tab.isIncognito());
        WebContents webContentsC = tab.c();
        if (webContentsC != null) {
            webContentsC.I();
        }
        return i;
    }
}
