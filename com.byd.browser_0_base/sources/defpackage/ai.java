package defpackage;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.metrics.UmaSessionStats;
import org.chromium.chrome.browser.tasks.tab_management.a0;
import org.chromium.chrome.browser.tasks.tab_management.c0;
import org.chromium.chrome.browser.tasks.tab_management.t;
import org.chromium.chrome.browser.tasks.tab_management.u;
import org.chromium.chrome.browser.toolbar.bottom.ScrollingBottomViewResourceFrameLayout;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.resources.ResourceManager;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: BottomControlsCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ai implements vb {
    public final bi e;
    public final zh f;

    /* JADX INFO: compiled from: BottomControlsCoordinator.java */
    public interface a {
    }

    @SuppressLint({"CutPasteId"})
    public ai(AppCompatActivity appCompatActivity, WindowAndroid windowAndroid, h61 h61Var, ResourceManager resourceManager, jj jjVar, rq0 rq0Var, ScrollingBottomViewResourceFrameLayout scrollingBottomViewResourceFrameLayout, j03 j03Var, l23 l23Var, aq1 aq1Var, g.a aVar) {
        scrollingBottomViewResourceFrameLayout.setConstraintsSupplier(aVar);
        PropertyModel propertyModel = new PropertyModel(ci.f);
        vf2 vf2Var = new vf2(scrollingBottomViewResourceFrameLayout, scrollingBottomViewResourceFrameLayout.getTopShadowHeight());
        j.b(propertyModel, new di(scrollingBottomViewResourceFrameLayout, vf2Var), new zf(27));
        h61Var.s(propertyModel, vf2Var, new ag(13));
        int i = R$dimen.bottom_controls_height;
        scrollingBottomViewResourceFrameLayout.findViewById(R$id.bottom_container_slot).getLayoutParams().height = scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelOffset(i);
        bi biVar = new bi(windowAndroid, propertyModel, jjVar, rq0Var, l23Var, scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelOffset(i), aq1Var);
        this.e = biVar;
        resourceManager.a().d(scrollingBottomViewResourceFrameLayout.getId(), scrollingBottomViewResourceFrameLayout.getResourceAdapter());
        this.f = j03Var;
        Toast.e = scrollingBottomViewResourceFrameLayout.getResources().getDimensionPixelSize(i);
        biVar.l = false;
        boolean zB = biVar.b();
        propertyModel.l(ci.d, zB);
        jjVar.k(zB ? biVar.j : 0, jjVar.b());
        biVar.d();
        vf2Var.j = biVar.b();
        h61Var.h(vf2Var);
        if (j03Var != null) {
            c01 c01Var = new c01(27, biVar);
            on0 on0Var = new on0(25, scrollingBottomViewResourceFrameLayout);
            u uVar = (u) j03Var;
            TraceEvent traceEventH0 = TraceEvent.H0("TabGroupUiCoordinator.initializeWithNative", null);
            try {
                if (UmaSessionStats.a()) {
                    N.MT4iKtWs("TabGroupsAndroidSyntheticTrial", "Downloaded_Enabled", 0);
                }
                c0 c0Var = new c0(1, uVar.f, uVar.q, null, null, false, null, null, 2, null, null, uVar.j, true, "TabStrip", uVar.p, on0Var, null);
                uVar.y = c0Var;
                c0Var.c(uVar.t.get());
                PropertyModel propertyModel2 = uVar.g;
                uVar.v = j.b(propertyModel2, new p03(uVar.i, uVar.y.f), new zf(23));
                if (!oj2.D(appCompatActivity) || uVar.k == null) {
                    uVar.x = null;
                } else {
                    uVar.x = new t(uVar);
                }
                uVar.z = new a0(uVar.e, c01Var, uVar, propertyModel2, uVar.q, uVar.s, uVar.r, uVar.h, uVar.x, uVar.l);
                if (r03.a == null) {
                    Activity activity = ApplicationStatus.c;
                    if (activity instanceof ChromeTabbedActivity) {
                        r03.a = new s03((a23) ((ChromeTabbedActivity) activity).G2());
                    }
                }
                uVar.q.g(false).j(new k03(uVar));
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        zh zhVar = this.f;
        return zhVar == null ? new aq1() : ((u) zhVar).getHandleBackPressChangedSupplier();
    }

    @Override // defpackage.vb
    public final int o() {
        zh zhVar = this.f;
        if (zhVar != null) {
            return ((u) zhVar).o();
        }
        return 1;
    }
}
