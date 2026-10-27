package defpackage;

import android.graphics.Bitmap;
import defpackage.o23;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: CustomTabTaskDescriptionHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class k40 extends o23.b {
    public final /* synthetic */ l40 e;

    public k40(l40 l40Var) {
        this.e = l40Var;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        if (z) {
            l40 l40Var = this.e;
            l40Var.r = null;
            l40Var.c();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void R0(TabImpl tabImpl) {
        if (n3.o0(tabImpl.g) == 5) {
            l40 l40Var = this.e;
            l40Var.r = null;
            l40Var.c();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void X(Tab tab, Bitmap bitmap, GURL gurl) {
        if (bitmap == null) {
            return;
        }
        this.e.b(bitmap);
    }
}
