package h;

import X5.F;
import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.A;
import androidx.lifecycle.EnumC0981t;
import java.util.Iterator;
import java.util.ListIterator;
import n7.C1995j;

/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f15144a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C1995j f15145b = new C1995j();
    public p c;
    public final OnBackInvokedCallback d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OnBackInvokedDispatcher f15146e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f15147f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f15148g;

    public x(Runnable runnable) {
        this.f15144a = runnable;
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 33) {
            this.d = i7 >= 34 ? u.f15140a.a(new q(this, 0), new q(this, 1), new r(this, 0), new r(this, 1)) : s.f15137a.a(new r(this, 2));
        }
    }

    public final void a(A a10, p pVar) {
        A7.m.f(a10, "owner");
        A7.m.f(pVar, "onBackPressedCallback");
        A9.b bVarG = a10.g();
        if (bVarG.O0() == EnumC0981t.l) {
            return;
        }
        pVar.f15132b.add(new v(this, bVarG, pVar));
        e();
        pVar.c = new F(0, this, x.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 6);
    }

    public final void b() {
        Object objPrevious;
        p pVar = this.c;
        if (pVar == null) {
            C1995j c1995j = this.f15145b;
            ListIterator<E> listIterator = c1995j.listIterator(c1995j.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                } else {
                    objPrevious = listIterator.previous();
                    if (((p) objPrevious).f15131a) {
                        break;
                    }
                }
            }
            pVar = (p) objPrevious;
        }
        this.c = null;
        if (pVar != null) {
            pVar.a();
        }
    }

    public final void c() {
        Object objPrevious;
        p pVar = this.c;
        if (pVar == null) {
            C1995j c1995j = this.f15145b;
            ListIterator listIterator = c1995j.listIterator(c1995j.d());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                } else {
                    objPrevious = listIterator.previous();
                    if (((p) objPrevious).f15131a) {
                        break;
                    }
                }
            }
            pVar = (p) objPrevious;
        }
        this.c = null;
        if (pVar != null) {
            pVar.b();
        } else {
            this.f15144a.run();
        }
    }

    public final void d(boolean z6) {
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f15146e;
        OnBackInvokedCallback onBackInvokedCallback = this.d;
        if (onBackInvokedDispatcher == null || onBackInvokedCallback == null) {
            return;
        }
        s sVar = s.f15137a;
        if (z6 && !this.f15147f) {
            sVar.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f15147f = true;
        } else {
            if (z6 || !this.f15147f) {
                return;
            }
            sVar.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f15147f = false;
        }
    }

    public final void e() {
        boolean z6 = this.f15148g;
        boolean z10 = false;
        C1995j c1995j = this.f15145b;
        if (c1995j == null || !c1995j.isEmpty()) {
            Iterator it = c1995j.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((p) it.next()).f15131a) {
                    z10 = true;
                    break;
                }
            }
        }
        this.f15148g = z10;
        if (z10 == z6 || Build.VERSION.SDK_INT < 33) {
            return;
        }
        d(z10);
    }
}
