package defpackage;

import android.app.Activity;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;

/* JADX INFO: compiled from: CustomTabSessionHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class d40 implements kr2 {
    public final oj e;
    public final p61<u40> f;
    public final p61<d30> g;
    public final v30 h;
    public final CustomTabsConnection i;
    public final bj2 j;
    public final Activity k;

    public d40(oj ojVar, p61 p61Var, p61 p61Var2, v30 v30Var, CustomTabsConnection customTabsConnection, Activity activity, q2 q2Var, bj2 bj2Var) {
        this.e = ojVar;
        this.f = p61Var;
        this.g = p61Var2;
        this.h = v30Var;
        this.i = customTabsConnection;
        this.k = activity;
        this.j = bj2Var;
        q2Var.f(this);
        bj2Var.b(this);
    }

    @Override // defpackage.kr2
    public final void j() {
        this.j.b(this);
    }

    @Override // defpackage.kr2
    public final void l() {
        bj2 bj2Var = this.j;
        if (bj2Var.c == this) {
            bj2Var.c = null;
        }
    }
}
