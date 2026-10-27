package androidx.activity;

import androidx.lifecycle.Lifecycle;
import defpackage.cc;
import defpackage.ec;
import defpackage.f0;
import defpackage.g0;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    public final Runnable a;
    public final ArrayDeque<g0> b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements cc, f0 {
        public final Lifecycle a;
        public final g0 b;
        public f0 c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, g0 g0Var) {
            this.a = lifecycle;
            this.b = g0Var;
            lifecycle.a(this);
        }

        @Override // defpackage.f0
        public void cancel() {
            this.a.c(this);
            this.b.e(this);
            f0 f0Var = this.c;
            if (f0Var != null) {
                f0Var.cancel();
                this.c = null;
            }
        }

        @Override // defpackage.cc
        public void d(ec ecVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.c = OnBackPressedDispatcher.this.b(this.b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                f0 f0Var = this.c;
                if (f0Var != null) {
                    f0Var.cancel();
                }
            }
        }
    }

    public class a implements f0 {
        public final g0 a;

        public a(g0 g0Var) {
            this.a = g0Var;
        }

        @Override // defpackage.f0
        public void cancel() {
            OnBackPressedDispatcher.this.b.remove(this.a);
            this.a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.a = runnable;
    }

    public void a(ec ecVar, g0 g0Var) {
        Lifecycle lifecycle = ecVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        g0Var.a(new LifecycleOnBackPressedCancellable(lifecycle, g0Var));
    }

    public f0 b(g0 g0Var) {
        this.b.add(g0Var);
        a aVar = new a(g0Var);
        g0Var.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<g0> itDescendingIterator = this.b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            g0 next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
