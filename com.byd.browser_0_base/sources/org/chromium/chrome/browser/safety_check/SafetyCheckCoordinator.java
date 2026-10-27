package org.chromium.chrome.browser.safety_check;

import J.N;
import defpackage.a8;
import defpackage.aq1;
import defpackage.b71;
import defpackage.d73;
import defpackage.eu1;
import defpackage.l91;
import defpackage.ld2;
import defpackage.n3;
import defpackage.od2;
import defpackage.oj2;
import defpackage.pd2;
import defpackage.qd2;
import defpackage.rd2;
import defpackage.t70;
import java.lang.ref.WeakReference;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.password_check.e;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class SafetyCheckCoordinator implements t70 {
    public SafetyCheckSettingsFragment e;
    public qd2 f;
    public od2 g;

    public SafetyCheckCoordinator(SafetyCheckSettingsFragment safetyCheckSettingsFragment, rd2 rd2Var, n3 n3Var, a8 a8Var, aq1 aq1Var) {
        this.e = safetyCheckSettingsFragment;
        this.f = rd2Var;
        safetyCheckSettingsFragment.U.d(safetyCheckSettingsFragment, new ld2(this, n3Var, a8Var, aq1Var));
        this.e.S.a(new t70() { // from class: org.chromium.chrome.browser.safety_check.SafetyCheckCoordinator.2
            @Override // defpackage.t70
            public final void g(b71 b71Var) {
                SafetyCheckCoordinator safetyCheckCoordinator = SafetyCheckCoordinator.this;
                if (safetyCheckCoordinator.e.j0) {
                    safetyCheckCoordinator.g.i();
                    return;
                }
                od2 od2Var = safetyCheckCoordinator.g;
                od2Var.getClass();
                if (System.currentTimeMillis() - od2Var.p.e(0L, "Chrome.SafetyCheck.LastRunTimestamp") < 600000) {
                    od2Var.e = true;
                    od2Var.a.n(pd2.c, 1);
                    od2Var.a.n(pd2.d, 1);
                    eu1 eu1Var = new eu1(11, od2Var);
                    Runnable runnable = od2Var.l;
                    if (runnable != null) {
                        od2Var.j.removeCallbacks(runnable);
                    }
                    od2Var.l = eu1Var;
                    od2Var.j.postDelayed(eu1Var, od2Var.h());
                    qd2 qd2Var = od2Var.b;
                    WeakReference weakReference = new WeakReference(od2Var.q);
                    rd2 rd2Var2 = (rd2) qd2Var;
                    rd2Var2.getClass();
                    PostTask.c(d73.h, new l91(rd2Var2, 24, weakReference));
                } else {
                    od2Var.e = false;
                    od2Var.a.n(pd2.c, 0);
                    od2Var.a.n(pd2.d, 0);
                }
                PropertyModel propertyModel = od2Var.a;
                PropertyModel.p pVar = pd2.a;
                propertyModel.n(pVar, 1);
                od2Var.i = 2;
                if (!N.M$fImY7r(Profile.d())) {
                    od2Var.i = 1;
                    od2Var.a.n(pVar, 6);
                    oj2.U(5, 10, "Settings.SafetyCheck.PasswordsResult");
                    od2Var.k();
                }
                od2Var.g = false;
                od2Var.f = false;
                ((e) n3.i0(od2Var.c)).a(od2Var, true);
                if (od2Var.f && od2Var.g) {
                    od2Var.g();
                }
            }
        });
    }

    @Override // defpackage.t70
    public final void f(b71 b71Var) {
        b71Var.j1().b(this);
        od2 od2Var = this.g;
        od2Var.j(null);
        Runnable runnable = od2Var.l;
        if (runnable != null) {
            od2Var.j.removeCallbacks(runnable);
        }
        od2Var.l = null;
        Runnable runnable2 = od2Var.m;
        if (runnable2 != null) {
            od2Var.j.removeCallbacks(runnable2);
        }
        od2Var.m = null;
        e eVar = n3.x;
        if (eVar != null) {
            N.MbiHHiCX(eVar.a.a);
            eVar.b.c(od2Var);
        }
        od2Var.b = null;
        od2Var.a = null;
        od2Var.j = null;
        this.e = null;
        this.f = null;
        this.g = null;
    }
}
