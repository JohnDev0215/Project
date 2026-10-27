package androidx.activity;

import androidx.lifecycle.Lifecycle;
import java.util.ArrayDeque;
import java.util.Iterator;
import k.b;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f233a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f234b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements d, k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Lifecycle f235d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final b f236e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public k.a f237f;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f235d = lifecycle;
            this.f236e = bVar;
            lifecycle.a(this);
        }

        @Override // k.a
        public void cancel() {
            this.f235d.c(this);
            this.f236e.e(this);
            k.a aVar = this.f237f;
            if (aVar != null) {
                aVar.cancel();
                this.f237f = null;
            }
        }

        @Override // x0.d
        public void onStateChanged(f fVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f237f = OnBackPressedDispatcher.this.b(this.f236e);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                k.a aVar = this.f237f;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    public class a implements k.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b f239d;

        public a(b bVar) {
            this.f239d = bVar;
        }

        @Override // k.a
        public void cancel() {
            OnBackPressedDispatcher.this.f234b.remove(this.f239d);
            this.f239d.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f233a = runnable;
    }

    public void a(f fVar, b bVar) {
        Lifecycle lifecycle = fVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public k.a b(b bVar) {
        this.f234b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f234b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f233a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
