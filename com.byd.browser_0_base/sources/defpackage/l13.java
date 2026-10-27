package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tasks.tab_management.u;

/* JADX INFO: compiled from: TabManagementDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class l13 implements k13 {
    @Override // defpackage.k13
    public final g03 a(TabModel tabModel) {
        return new g03(tabModel, CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:enable_tab_group_auto_creation"), true));
    }

    @Override // defpackage.k13
    public final u b(AppCompatActivity appCompatActivity, ViewGroup viewGroup, bz0 bz0Var, ef2 ef2Var, zp1 zp1Var, ji jiVar, q2 q2Var, nw2 nw2Var, y13 y13Var, TabContentManager tabContentManager, CompositorViewHolder compositorViewHolder, fs fsVar, gz2 gz2Var, qs1 qs1Var, wp2 wp2Var) {
        return new u(appCompatActivity, viewGroup, bz0Var, ef2Var, zp1Var, jiVar, q2Var, nw2Var, y13Var, tabContentManager, compositorViewHolder, fsVar, gz2Var, qs1Var);
    }

    @Override // defpackage.k13
    public final o1 c(Activity activity, q2 q2Var, y13 y13Var, TabContentManager tabContentManager, kj kjVar, gz2 gz2Var, jf1 jf1Var, ViewGroup viewGroup, tj1 tj1Var, ef2 ef2Var, ViewGroup viewGroup2, nw2 nw2Var, wp2 wp2Var, bi1 bi1Var) {
        return new o1(activity, q2Var, y13Var, tabContentManager, kjVar, gz2Var, jf1Var, viewGroup, tj1Var, ef2Var, 2, viewGroup2, nw2Var, wp2Var, bi1Var, null, null);
    }

    @Override // defpackage.k13
    public final q1 d(Context context, n61 n61Var, j61 j61Var, n1 n1Var, i31 i31Var, ViewGroup viewGroup, ef2 ef2Var) {
        return new q1(context, n61Var, j61Var, n1Var, i31Var, viewGroup, ef2Var);
    }

    @Override // defpackage.k13
    public final o1 e(Activity activity, q2 q2Var, y13 y13Var, TabContentManager tabContentManager, kj kjVar, gz2 gz2Var, jf1 jf1Var, ViewGroup viewGroup, tj1 tj1Var, ef2 ef2Var, ViewGroup viewGroup2, nw2 nw2Var, wp2 wp2Var, bi1 bi1Var, qs1 qs1Var, zb zbVar) {
        int i = 0;
        if (UmaSessionStats.a()) {
            N.MT4iKtWs("TabGridLayoutAndroidSyntheticTrial", "Downloaded_Enabled", 0);
        }
        if (oj2.C(activity) && SysUtils.isLowEndDevice()) {
            i = 3;
        }
        return new o1(activity, q2Var, y13Var, tabContentManager, kjVar, gz2Var, jf1Var, viewGroup, tj1Var, ef2Var, i, viewGroup2, nw2Var, wp2Var, bi1Var, qs1Var, zbVar);
    }
}
