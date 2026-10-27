package defpackage;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.c;
import androidx.lifecycle.d;
import androidx.lifecycle.g;
import androidx.lifecycle.k;
import androidx.savedstate.a;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public final class eq0 implements c, ge2, bn3 {
    public final Fragment e;
    public final an3 f;
    public final Runnable g;
    public g h = null;
    public fe2 i = null;

    public eq0(Fragment fragment, an3 an3Var, bp0 bp0Var) {
        this.e = fragment;
        this.f = an3Var;
        this.g = bp0Var;
    }

    @Override // androidx.lifecycle.c
    public final g10 S() {
        Application application;
        Fragment fragment = this.e;
        Context applicationContext = fragment.x0().getApplicationContext();
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
        xj1 xj1Var = new xj1();
        LinkedHashMap linkedHashMap = xj1Var.a;
        if (application != null) {
            linkedHashMap.put(oj2.q, application);
        }
        linkedHashMap.put(k.a, fragment);
        linkedHashMap.put(k.b, this);
        Bundle bundle = fragment.j;
        if (bundle != null) {
            linkedHashMap.put(k.c, bundle);
        }
        return xj1Var;
    }

    public final void a(d.a aVar) {
        this.h.e(aVar);
    }

    public final void b() {
        if (this.h == null) {
            this.h = new g(this);
            fe2 fe2Var = new fe2(this);
            this.i = fe2Var;
            fe2Var.a();
            this.g.run();
        }
    }

    @Override // defpackage.bn3
    public final an3 h0() {
        b();
        return this.f;
    }

    @Override // defpackage.b71
    public final g j1() {
        b();
        return this.h;
    }

    @Override // defpackage.ge2
    public final a s0() {
        b();
        return this.i.b;
    }
}
