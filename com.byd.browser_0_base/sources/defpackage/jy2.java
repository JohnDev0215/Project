package defpackage;

import android.text.TextUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabAssociatedApp.java */
/* JADX INFO: loaded from: classes2.dex */
public final class jy2 extends m53 implements kx0 {
    public String f;

    public jy2(Tab tab) {
        super(tab);
        tab.x(new a());
    }

    public static jy2 d(Tab tab) {
        jy2 jy2Var = (jy2) tab.B().b(jy2.class);
        return jy2Var == null ? (jy2) tab.B().d(jy2.class, new jy2(tab)) : jy2Var;
    }

    public static boolean f(Tab tab) {
        jy2 jy2Var = (jy2) tab.B().b(jy2.class);
        if (jy2Var == null) {
            return false;
        }
        return tab.getLaunchType() == 1 && !TextUtils.equals(jy2Var.f, xz.a.getPackageName());
    }

    @Override // defpackage.m53
    public final void c(WebContents webContents) {
        ImeAdapterImpl.b(webContents).m.add(this);
    }

    @Override // defpackage.kx0
    public final void e() {
        this.f = null;
    }

    @Override // defpackage.m53
    public final void a(WebContents webContents) {
    }

    /* JADX INFO: compiled from: TabAssociatedApp.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            tab.t(this);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
            if ((loadUrlParams.d & 33554432) == 33554432) {
                jy2.this.f = null;
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void y0(String str) {
            if (str != null) {
                jy2.this.f = str;
            }
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
        }
    }
}
