package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StripLayoutHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class wu2 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ fv2 f;
    public final /* synthetic */ Tab g;
    public final /* synthetic */ boolean h;
    public final /* synthetic */ vu2 i;

    public wu2(vu2 vu2Var, boolean z, fv2 fv2Var, Tab tab, boolean z2) {
        this.i = vu2Var;
        this.e = z;
        this.f = fv2Var;
        this.g = tab;
        this.h = z2;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        kw kwVarF;
        boolean z = this.e;
        vu2 vu2Var = this.i;
        if (!z) {
            vu2Var.X = false;
            if (!this.h) {
                vu2Var.q(true);
                return;
            } else {
                vu2Var.d(true, false);
                return;
            }
        }
        vu2Var.g();
        int i = this.f.a;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayListD = vu2Var.d(true, true);
        if (arrayListD != null) {
            arrayList.addAll(arrayListD);
        }
        vu2Var.H();
        vu2Var.e();
        for (fv2 fv2Var : vu2Var.h) {
            arrayList.add(kw.g(((h61) vu2Var.a).G, fv2Var, fv2.F, fv2Var.s, fv2Var.o, 250L));
        }
        if (!bq.f0.a() && (kwVarF = vu2Var.F(true)) != null) {
            arrayList.add(kwVarF);
        }
        vu2Var.x(arrayList, new xu2(vu2Var));
        Tab tab = this.g;
        if (tab != null) {
            PostTask.b(rh3.a, new uu2(vu2Var, i, 0, tab), 150L);
        }
    }
}
