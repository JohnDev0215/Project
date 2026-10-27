package androidx.activity;

import androidx.lifecycle.Lifecycle;
import b.a.b;
import b.l.d;
import b.l.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f71a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f72b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements d, b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f73a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f74b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.a.a f75c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f73a = lifecycle;
            this.f74b = bVar;
            lifecycle.a(this);
        }

        @Override // b.a.a
        public void cancel() {
            this.f73a.c(this);
            this.f74b.e(this);
            b.a.a aVar = this.f75c;
            if (aVar != null) {
                aVar.cancel();
                this.f75c = null;
            }
        }

        @Override // b.l.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f75c = OnBackPressedDispatcher.this.b(this.f74b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                b.a.a aVar = this.f75c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    public class a implements b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f77a;

        public a(b bVar) {
            this.f77a = bVar;
        }

        @Override // b.a.a
        public void cancel() {
            OnBackPressedDispatcher.this.f72b.remove(this.f77a);
            this.f77a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f71a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycleA = fVar.a();
        if (lifecycleA.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycleA, bVar));
    }

    public b.a.a b(b bVar) {
        this.f72b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f72b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f71a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
