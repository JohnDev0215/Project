package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import androidx.lifecycle.e0;
import androidx.lifecycle.i;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class v implements androidx.lifecycle.h, androidx.savedstate.c, e0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Fragment f2464e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final d0 f2465g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b0.b f2466h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private androidx.lifecycle.p f2467i = null;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private androidx.savedstate.b f2468j = null;

    v(Fragment fragment, d0 d0Var) {
        this.f2464e = fragment;
        this.f2465g = d0Var;
    }

    @Override // androidx.lifecycle.o
    public androidx.lifecycle.i a() {
        e();
        return this.f2467i;
    }

    void b(i.b bVar) {
        this.f2467i.h(bVar);
    }

    @Override // androidx.savedstate.c
    public SavedStateRegistry d() {
        e();
        return this.f2468j.b();
    }

    void e() {
        if (this.f2467i == null) {
            this.f2467i = new androidx.lifecycle.p(this);
            this.f2468j = androidx.savedstate.b.a(this);
        }
    }

    boolean f() {
        return this.f2467i != null;
    }

    void g(Bundle bundle) {
        this.f2468j.c(bundle);
    }

    void h(Bundle bundle) {
        this.f2468j.d(bundle);
    }

    void i(i.c cVar) {
        this.f2467i.o(cVar);
    }

    @Override // androidx.lifecycle.e0
    public d0 k() {
        e();
        return this.f2465g;
    }

    @Override // androidx.lifecycle.h
    public b0.b m() {
        b0.b bVarM = this.f2464e.m();
        if (!bVarM.equals(this.f2464e.f2105a0)) {
            this.f2466h = bVarM;
            return bVarM;
        }
        if (this.f2466h == null) {
            Application application = null;
            Context applicationContext = this.f2464e.u1().getApplicationContext();
            while (true) {
                if (!(applicationContext instanceof ContextWrapper)) {
                    break;
                }
                if (applicationContext instanceof Application) {
                    application = (Application) applicationContext;
                    break;
                }
                applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
            }
            this.f2466h = new androidx.lifecycle.z(application, this, this.f2464e.q());
        }
        return this.f2466h;
    }
}
