package androidx.activity;

import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f667b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements e, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f668a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f669b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f670c;

        LifecycleOnBackPressedCancellable(d dVar, b bVar) {
            this.f668a = dVar;
            this.f669b = bVar;
            dVar.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f668a.c(this);
            this.f669b.e(this);
            androidx.activity.a aVar = this.f670c;
            if (aVar != null) {
                aVar.cancel();
                this.f670c = null;
            }
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, d.a aVar) {
            if (aVar == d.a.ON_START) {
                this.f670c = OnBackPressedDispatcher.this.b(this.f669b);
                return;
            }
            if (aVar != d.a.ON_STOP) {
                if (aVar == d.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar2 = this.f670c;
                if (aVar2 != null) {
                    aVar2.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f672a;

        a(b bVar) {
            this.f672a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f667b.remove(this.f672a);
            this.f672a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f666a = runnable;
    }

    public void a(g gVar, b bVar) {
        d dVarA = gVar.a();
        if (dVarA.b() == d.b.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(dVarA, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f667b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f667b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f666a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
