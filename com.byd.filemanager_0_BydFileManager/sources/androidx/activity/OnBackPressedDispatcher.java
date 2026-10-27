package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f73a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f74b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements e, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f75a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f76b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f77c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f75a = lifecycle;
            this.f76b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f75a.c(this);
            this.f76b.e(this);
            androidx.activity.a aVar = this.f77c;
            if (aVar != null) {
                aVar.cancel();
                this.f77c = null;
            }
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f77c = OnBackPressedDispatcher.this.b(this.f76b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f77c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f79a;

        a(b bVar) {
            this.f79a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f74b.remove(this.f79a);
            this.f79a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f73a = runnable;
    }

    public void a(g gVar, b bVar) {
        Lifecycle lifecycleA = gVar.a();
        if (lifecycleA.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycleA, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f74b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f74b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f73a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
