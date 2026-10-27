package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.q1;
import org.chromium.chrome.browser.tasks.tab_management.u;

/* JADX INFO: compiled from: TabManagementDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public interface k13 {
    g03 a(TabModel tabModel);

    u b(AppCompatActivity appCompatActivity, ViewGroup viewGroup, bz0 bz0Var, ef2 ef2Var, zp1 zp1Var, ji jiVar, q2 q2Var, nw2 nw2Var, y13 y13Var, TabContentManager tabContentManager, CompositorViewHolder compositorViewHolder, fs fsVar, gz2 gz2Var, qs1 qs1Var, wp2 wp2Var);

    o1 c(Activity activity, q2 q2Var, y13 y13Var, TabContentManager tabContentManager, kj kjVar, gz2 gz2Var, jf1 jf1Var, ViewGroup viewGroup, tj1 tj1Var, ef2 ef2Var, ViewGroup viewGroup2, nw2 nw2Var, wp2 wp2Var, bi1 bi1Var);

    q1 d(Context context, n61 n61Var, j61 j61Var, n1 n1Var, i31 i31Var, ViewGroup viewGroup, ef2 ef2Var);

    o1 e(Activity activity, q2 q2Var, y13 y13Var, TabContentManager tabContentManager, kj kjVar, gz2 gz2Var, jf1 jf1Var, ViewGroup viewGroup, tj1 tj1Var, ef2 ef2Var, ViewGroup viewGroup2, nw2 nw2Var, wp2 wp2Var, bi1 bi1Var, qs1 qs1Var, zb zbVar);
}
