package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f599b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f600a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f601b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f602c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f600a = lifecycle;
            this.f601b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f600a.c(this);
            this.f601b.e(this);
            androidx.activity.a aVar = this.f602c;
            if (aVar != null) {
                aVar.cancel();
                this.f602c = null;
            }
        }

        @Override // androidx.lifecycle.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f602c = OnBackPressedDispatcher.this.b(this.f601b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f602c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f604a;

        a(b bVar) {
            this.f604a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f599b.remove(this.f604a);
            this.f604a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f598a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycleA = fVar.a();
        if (lifecycleA.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycleA, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f599b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f599b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f598a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
