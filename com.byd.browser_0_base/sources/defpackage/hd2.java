package defpackage;

import android.text.SpannableString;
import android.view.ContextThemeWrapper;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.widget.ChromeBulletSpan;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: SadTab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hd2 extends nh0 implements lk3, j53 {
    public final Tab e;
    public View f;
    public int g;

    public hd2(TabImpl tabImpl) {
        this.e = tabImpl;
        tabImpl.x(this);
    }

    public static SpannableString y1(ContextThemeWrapper contextThemeWrapper, int i) {
        SpannableString spannableString = new SpannableString(contextThemeWrapper.getString(i));
        spannableString.setSpan(new ChromeBulletSpan(contextThemeWrapper), 0, spannableString.length(), 0);
        return spannableString;
    }

    public static boolean z1(Tab tab) {
        hd2 hd2Var;
        return (tab == null || !tab.isInitialized() || (hd2Var = (hd2) tab.B().b(hd2.class)) == null || hd2Var.f == null || !hd2Var.e.o().b(hd2Var)) ? false : true;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        this.g = 0;
        this.e.o().c(this);
        this.f = null;
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
        this.e.o().c(this);
        this.f = null;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g1(Tab tab, GURL gurl) {
        this.e.o().c(this);
        this.f = null;
    }

    @Override // defpackage.j53
    public final View getView() {
        return this.f;
    }

    @Override // defpackage.j53
    public final int q0() {
        return 1;
    }
}
