package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.browser_ui.modaldialog.AppModalPresenter;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: NewDownloadTab.java */
/* JADX INFO: loaded from: classes.dex */
public final class zl1 extends nh0 implements lk3, zi3, j53 {
    public final Tab e;
    public final Activity f;
    public final cc0 g;

    public zl1(TabImpl tabImpl, dc0 dc0Var, Activity activity) {
        this.e = tabImpl;
        this.f = activity;
        this.g = dc0Var;
        WindowAndroid windowAndroid = tabImpl.e;
        aj3<zl1> aj3Var = am1.a;
        am1.a.a(windowAndroid.r, this);
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        Tab tab2 = this.e;
        if (windowAndroid == null) {
            tab2.o().c(this);
            return;
        }
        tab2.o().a(this);
        Context context = tab.getContext();
        dc0 dc0Var = (dc0) this.g;
        dc0Var.getClass();
        dc0Var.f.g = new bi1(new AppModalPresenter(context));
    }

    @Override // defpackage.j53
    public final void S() {
        this.e.o().c(this);
    }

    @Override // defpackage.lk3
    public final void destroy() {
        Tab tab = this.e;
        tab.t(this);
        tab.o().c(this);
        ((dc0) this.g).destroy();
        am1.a.b(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        if (TextUtils.equals(gurl.e(), "recent-tabs")) {
            destroy();
        }
    }

    @Override // defpackage.j53
    public final View getView() {
        return ((dc0) this.g).e.a;
    }

    @Override // defpackage.j53
    public final int q0() {
        return 3;
    }
}
