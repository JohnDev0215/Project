package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f69a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f70b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f71a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f72b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f73c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f71a = lifecycle;
            this.f72b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f71a.c(this);
            this.f72b.e(this);
            androidx.activity.a aVar = this.f73c;
            if (aVar != null) {
                aVar.cancel();
                this.f73c = null;
            }
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f73c = OnBackPressedDispatcher.this.b(this.f72b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f73c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f75a;

        a(b bVar) {
            this.f75a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f70b.remove(this.f75a);
            this.f75a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f69a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycleA = fVar.a();
        if (lifecycleA.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycleA, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f70b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f70b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f69a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
