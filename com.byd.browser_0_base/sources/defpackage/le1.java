package defpackage;

import android.graphics.Bitmap;
import defpackage.de1;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: MediaSessionTabHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class le1 implements de1.c {
    public Tab a;
    public de1 b;

    public le1(TabImpl tabImpl) {
        a aVar = new a();
        this.a = tabImpl;
        tabImpl.x(aVar);
        a();
    }

    public final void a() {
        de1 de1Var = this.b;
        if (de1Var != null) {
            de1Var.h(this.a.c());
        } else if (this.a.c() != null) {
            this.b = new de1(this.a.c(), this);
        }
    }

    /* JADX INFO: compiled from: MediaSessionTabHelper.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            le1 le1Var = le1.this;
            de1 de1Var = le1Var.b;
            if (de1Var != null) {
                de1Var.b();
                if (de1Var.c != null) {
                    Runnable runnable = de1Var.s;
                    if (runnable != null) {
                        de1Var.r.removeCallbacks(runnable);
                        de1Var.s = null;
                    }
                    de1Var.f();
                    de1Var.k = null;
                }
                de1.b bVar = de1Var.c;
                if (bVar != null) {
                    bVar.destroy();
                }
                de1Var.c = null;
                LargeIconBridge largeIconBridge = de1Var.t;
                if (largeIconBridge != null) {
                    largeIconBridge.a();
                }
                de1Var.t = null;
            }
            le1Var.a.t(this);
            le1Var.a = null;
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void X(Tab tab, Bitmap bitmap, GURL gurl) {
            le1.this.b.j(bitmap);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            le1.this.a();
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
        }
    }
}
