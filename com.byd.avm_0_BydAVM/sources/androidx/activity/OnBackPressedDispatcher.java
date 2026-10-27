package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f69b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f70a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f71b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f72c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f70a = lifecycle;
            this.f71b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f70a.c(this);
            this.f71b.e(this);
            androidx.activity.a aVar = this.f72c;
            if (aVar != null) {
                aVar.cancel();
                this.f72c = null;
            }
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f72c = OnBackPressedDispatcher.this.b(this.f71b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f72c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f74a;

        a(b bVar) {
            this.f74a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f69b.remove(this.f74a);
            this.f74a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f68a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f69b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f69b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f68a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
