package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f72a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f73b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f74a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f75b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f76c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f74a = lifecycle;
            this.f75b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f74a.c(this);
            this.f75b.e(this);
            androidx.activity.a aVar = this.f76c;
            if (aVar != null) {
                aVar.cancel();
                this.f76c = null;
            }
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f76c = OnBackPressedDispatcher.this.b(this.f75b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f76c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f78a;

        a(b bVar) {
            this.f78a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f73b.remove(this.f78a);
            this.f78a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f72a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f73b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f73b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f72a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
