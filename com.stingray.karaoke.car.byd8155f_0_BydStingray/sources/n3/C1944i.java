package n3;

import H7.InterfaceC0318c;
import android.app.Application;
import android.content.Context;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.d0;
import androidx.lifecycle.k0;
import b2.AbstractC0992b;
import r.C2257p;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: n3.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1944i extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19863m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1945j f19864n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1944i(C1945j c1945j, int i7) {
        super(0);
        this.f19863m = i7;
        this.f19864n = c1945j;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f19863m) {
            case 0:
                C1945j c1945j = this.f19864n;
                Context context = c1945j.l;
                Context applicationContext = context != null ? context.getApplicationContext() : null;
                return new d0(applicationContext instanceof Application ? (Application) applicationContext : null, c1945j, c1945j.c());
            default:
                C1945j c1945j2 = this.f19864n;
                if (!c1945j2.f19872u) {
                    throw new IllegalStateException("You cannot access the NavBackStackEntry's SavedStateHandle until it is added to the NavController's back stack (i.e., the Lifecycle of the NavBackStackEntry reaches the CREATED state).");
                }
                androidx.lifecycle.C c = c1945j2.f19870s;
                if (c.f11908p == EnumC0981t.l) {
                    throw new IllegalStateException("You cannot access the NavBackStackEntry's SavedStateHandle after the NavBackStackEntry is destroyed.");
                }
                C1942g c1942g = new C1942g();
                c1942g.f19860a = (C2257p) c1945j2.f19871t.o;
                c1942g.f19861b = c;
                k0 k0VarF = c1945j2.f();
                AbstractC0992b abstractC0992bE = c1945j2.e();
                A7.m.f(abstractC0992bE, "defaultCreationExtras");
                l3.P p10 = new l3.P(k0VarF, c1942g, abstractC0992bE);
                InterfaceC0318c interfaceC0318cL = n7.z.l(C1943h.class);
                String strA = interfaceC0318cL.a();
                if (strA != null) {
                    return ((C1943h) p10.s(interfaceC0318cL, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA))).f19862b;
                }
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
    }
}
