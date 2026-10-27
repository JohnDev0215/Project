package androidx.activity;

import androidx.lifecycle.Lifecycle;
import h.b;
import java.util.ArrayDeque;
import java.util.Iterator;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f126a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f127b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements d, h.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f128a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f129b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public h.a f130c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f128a = lifecycle;
            this.f129b = bVar;
            lifecycle.a(this);
        }

        @Override // h.a
        public void cancel() {
            this.f128a.c(this);
            this.f129b.e(this);
            h.a aVar = this.f130c;
            if (aVar != null) {
                aVar.cancel();
                this.f130c = null;
            }
        }

        @Override // t0.d
        public void d(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f130c = OnBackPressedDispatcher.this.b(this.f129b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                h.a aVar = this.f130c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    public class a implements h.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f132a;

        public a(b bVar) {
            this.f132a = bVar;
        }

        @Override // h.a
        public void cancel() {
            OnBackPressedDispatcher.this.f127b.remove(this.f132a);
            this.f132a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f126a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public h.a b(b bVar) {
        this.f127b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f127b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f126a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
