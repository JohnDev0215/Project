package androidx.activity;

import androidx.lifecycle.Lifecycle;
import b.a.b;
import b.m.d;
import b.m.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f62a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f63b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements d, b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f64a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f65b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.a.a f66c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f64a = lifecycle;
            this.f65b = bVar;
            lifecycle.a(this);
        }

        @Override // b.a.a
        public void cancel() {
            this.f64a.c(this);
            this.f65b.e(this);
            b.a.a aVar = this.f66c;
            if (aVar != null) {
                aVar.cancel();
                this.f66c = null;
            }
        }

        @Override // b.m.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f66c = OnBackPressedDispatcher.this.b(this.f65b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                b.a.a aVar = this.f66c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    public class a implements b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f68a;

        public a(b bVar) {
            this.f68a = bVar;
        }

        @Override // b.a.a
        public void cancel() {
            OnBackPressedDispatcher.this.f63b.remove(this.f68a);
            this.f68a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f62a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public b.a.a b(b bVar) {
        this.f63b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f63b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f62a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
