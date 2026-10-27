package defpackage;

import android.app.Activity;
import android.content.Context;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: CustomTabToolbarCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class u40 {
    public final oj a;
    public final a30 b;
    public final CustomTabsConnection c;
    public final Activity d;
    public final Context e;
    public final p61<mj> f;
    public final w20 g;
    public final mu h;
    public final g30 i;
    public final t40 j;
    public g k;
    public int l = -1;
    public boolean m;

    public u40(oj ojVar, a30 a30Var, CustomTabsConnection customTabsConnection, Activity activity, Context context, p61 p61Var, w20 w20Var, mu muVar, g30 g30Var, k30 k30Var, t40 t40Var) {
        this.a = ojVar;
        this.b = a30Var;
        this.c = customTabsConnection;
        this.d = activity;
        this.e = context;
        this.f = p61Var;
        this.g = w20Var;
        this.h = muVar;
        this.i = g30Var;
        this.j = t40Var;
        so soVar = new so(23, this);
        if (k30Var.l) {
            soVar.onResult(k30Var.h.get().getLayoutManager());
        } else {
            k30Var.e.add(soVar);
        }
    }
}
