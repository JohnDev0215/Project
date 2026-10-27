package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.lifecycle.Lifecycle$State;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import p.gb;
import p.gx0;
import p.l91;
import p.lq2;
import p.mp1;
import p.ox0;
import p.py0;
import p.s91;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final Runnable a;
    public final gb b = new gb();
    public final py0 c;
    public final OnBackInvokedCallback d;
    public OnBackInvokedDispatcher e;
    public boolean f;

    public b(Runnable runnable) {
        this.a = runnable;
        if (Build.VERSION.SDK_INT >= 33) {
            this.c = new py0() { // from class: androidx.activity.OnBackPressedDispatcher$1
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    this.this$0.c();
                    return lq2.a;
                }
            };
            this.d = mp1.a.a(new py0() { // from class: androidx.activity.OnBackPressedDispatcher$2
                {
                    super(0);
                }

                @Override // p.py0
                public final Object invoke() {
                    this.this$0.b();
                    return lq2.a;
                }
            });
        }
    }

    public final void a(s91 s91Var, gx0 gx0Var) {
        v03.j(gx0Var, "onBackPressedCallback");
        l91 l91VarL = s91Var.l();
        if (l91VarL.b() == Lifecycle$State.c) {
            return;
        }
        gx0Var.b.add(new OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(this, l91VarL, gx0Var));
        if (Build.VERSION.SDK_INT >= 33) {
            c();
            gx0Var.c = this.c;
        }
    }

    public final void b() {
        Object objPrevious;
        gb gbVar = this.b;
        ListIterator listIterator = gbVar.listIterator(gbVar.a());
        while (true) {
            if (!listIterator.hasPrevious()) {
                objPrevious = null;
                break;
            } else {
                objPrevious = listIterator.previous();
                if (((gx0) objPrevious).a) {
                    break;
                }
            }
        }
        gx0 gx0Var = (gx0) objPrevious;
        if (gx0Var == null) {
            Runnable runnable = this.a;
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        ox0 ox0Var = gx0Var.d;
        ox0Var.x(true);
        if (ox0Var.h.a) {
            ox0Var.M();
        } else {
            ox0Var.g.b();
        }
    }

    public final void c() {
        boolean z;
        OnBackInvokedCallback onBackInvokedCallback;
        gb gbVar = this.b;
        if ((gbVar instanceof Collection) && gbVar.isEmpty()) {
            z = false;
        } else {
            Iterator it = gbVar.iterator();
            while (it.hasNext()) {
                if (((gx0) it.next()).a) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.e;
        if (onBackInvokedDispatcher == null || (onBackInvokedCallback = this.d) == null) {
            return;
        }
        mp1 mp1Var = mp1.a;
        if (z && !this.f) {
            mp1Var.b(onBackInvokedDispatcher, 0, onBackInvokedCallback);
            this.f = true;
        } else {
            if (z || !this.f) {
                return;
            }
            mp1Var.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f = false;
        }
    }
}
