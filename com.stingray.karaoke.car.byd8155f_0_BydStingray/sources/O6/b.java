package O6;

import A7.m;
import H7.InterfaceC0318c;
import I5.g;
import I5.i;
import android.app.Application;
import androidx.lifecycle.k0;
import b2.AbstractC0992b;
import g1.AbstractC1274a;
import h.n;
import l3.P;
import n7.z;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Q6.b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final n f5162m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f5163n;
    public final Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile Q6.a f5164p;

    public b(n nVar, int i7) {
        this.l = i7;
        switch (i7) {
            case 1:
                this.f5163n = new Object();
                this.f5162m = nVar;
                this.o = nVar;
                break;
            default:
                this.f5163n = new Object();
                this.f5162m = nVar;
                this.o = new b(nVar, 1);
                break;
        }
    }

    public g a() {
        String str;
        n nVar = this.f5162m;
        if (nVar.getApplication() instanceof Q6.b) {
            i iVar = (i) ((a) AbstractC1274a.I(a.class, (b) this.o));
            return new g(iVar.f2954a, iVar.f2955b);
        }
        StringBuilder sb = new StringBuilder("Hilt Activity must be attached to an @HiltAndroidApp Application. ");
        if (Application.class.equals(nVar.getApplication().getClass())) {
            str = "Did you forget to specify your Application's class name in your manifest's <application />'s android:name attribute?";
        } else {
            str = "Found: " + nVar.getApplication().getClass();
        }
        sb.append(str);
        throw new IllegalStateException(sb.toString());
    }

    @Override // Q6.b
    public final Object c() {
        switch (this.l) {
            case 0:
                if (((g) this.f5164p) == null) {
                    synchronized (this.f5163n) {
                        try {
                            if (((g) this.f5164p) == null) {
                                this.f5164p = a();
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return (g) this.f5164p;
            default:
                if (((i) this.f5164p) == null) {
                    synchronized (this.f5163n) {
                        if (((i) this.f5164p) == null) {
                            n nVar = this.f5162m;
                            N6.c cVar = new N6.c((n) this.o, 1);
                            k0 k0VarF = nVar.f();
                            AbstractC0992b abstractC0992bE = nVar.e();
                            m.f(abstractC0992bE, "defaultCreationExtras");
                            P p10 = new P(k0VarF, cVar, abstractC0992bE);
                            InterfaceC0318c interfaceC0318cL = z.l(d.class);
                            String strA = interfaceC0318cL.a();
                            if (strA == null) {
                                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                            }
                            this.f5164p = ((d) p10.s(interfaceC0318cL, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA))).f5165b;
                        }
                    }
                }
                return (i) this.f5164p;
        }
    }
}
