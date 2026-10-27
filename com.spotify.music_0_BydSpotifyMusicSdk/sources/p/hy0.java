package p;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class hy0 implements n11, x62, mu2 {
    public final ww0 c;
    public final lu2 e;
    public final Runnable k;
    public androidx.lifecycle.a l = null;
    public w62 m = null;

    public hy0(ww0 ww0Var, lu2 lu2Var, nv nvVar) {
        this.c = ww0Var;
        this.e = lu2Var;
        this.k = nvVar;
    }

    @Override // p.x62
    public final v62 a() {
        c();
        return this.m.b;
    }

    public final void b(Lifecycle$Event lifecycle$Event) {
        this.l.f(lifecycle$Event);
    }

    public final void c() {
        if (this.l == null) {
            this.l = new androidx.lifecycle.a(this);
            w62 w62VarS = zb0.s(this);
            this.m = w62VarS;
            w62VarS.a();
            this.k.run();
        }
    }

    @Override // p.n11
    public final yl1 g() {
        Application application;
        ww0 ww0Var = this.c;
        Context applicationContext = ww0Var.B().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        yl1 yl1Var = new yl1();
        LinkedHashMap linkedHashMap = yl1Var.a;
        if (application != null) {
            linkedHashMap.put(u11.u, application);
        }
        linkedHashMap.put(he1.e, ww0Var);
        linkedHashMap.put(he1.f, this);
        Bundle bundle = ww0Var.n;
        if (bundle != null) {
            linkedHashMap.put(he1.g, bundle);
        }
        return yl1Var;
    }

    @Override // p.mu2
    public final lu2 j() {
        c();
        return this.e;
    }

    @Override // p.s91
    public final l91 l() {
        c();
        return this.l;
    }
}
