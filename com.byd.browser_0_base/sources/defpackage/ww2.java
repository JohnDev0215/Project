package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SuspendedTab.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ww2 extends nh0 implements lk3, j53 {
    public final Tab e;
    public final nw2<TabContentManager> f;
    public View g;
    public String h;

    public ww2(Tab tab, nw2<TabContentManager> nw2Var) {
        this.e = tab;
        this.f = nw2Var;
    }

    public static ww2 z1(Tab tab, nw2<TabContentManager> nw2Var) {
        ww2 ww2Var = (ww2) tab.B().b(ww2.class);
        return ww2Var == null ? (ww2) tab.B().d(ww2.class, new ww2(tab, nw2Var)) : ww2Var;
    }

    public final void A1() {
        Tab tab = this.e;
        ((TextView) this.g.findViewById(R$id.suspended_tab_explanation)).setText(tab.getContext().getString(R$string.usage_stats_site_paused_explanation, this.h));
        this.g.findViewById(R$id.suspended_tab_settings_button).setOnClickListener(new vw2(this, tab.getContext()));
    }

    @Override // defpackage.n23
    public final void P0(Tab tab, WindowAndroid windowAndroid) {
        if (windowAndroid != null) {
            y1();
        } else {
            this.e.o().c(this);
            this.g = null;
        }
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this);
    }

    @Override // defpackage.j53
    public final View getView() {
        return this.g;
    }

    @Override // defpackage.j53
    public final int q0() {
        return 0;
    }

    public final void y1() {
        Tab tab = this.e;
        View viewInflate = LayoutInflater.from(tab.getContext()).inflate(R$layout.suspended_tab, (ViewGroup) null);
        viewInflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.g = viewInflate;
        tab.o().a(this);
        A1();
    }
}
