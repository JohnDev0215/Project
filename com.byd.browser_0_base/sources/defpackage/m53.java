package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabWebContentsUserData.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class m53 implements lk3 {
    public WebContents e;

    public m53(Tab tab) {
        tab.x(new a());
    }

    public abstract void a(WebContents webContents);

    public abstract void c(WebContents webContents);

    @Override // defpackage.lk3
    public final void destroy() {
        a(this.e);
        b();
    }

    public void b() {
    }

    /* JADX INFO: compiled from: TabWebContentsUserData.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            tab.t(this);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            m53 m53Var = m53.this;
            if (m53Var.e == tab.c()) {
                return;
            }
            WebContents webContents = m53Var.e;
            if (webContents != null) {
                m53Var.a(webContents);
            }
            WebContents webContentsC = tab.c();
            m53Var.e = webContentsC;
            if (webContentsC != null) {
                m53Var.c(webContentsC);
            }
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
        }
    }
}
