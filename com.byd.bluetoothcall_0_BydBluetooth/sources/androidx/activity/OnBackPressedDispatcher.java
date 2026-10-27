package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f173b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements e, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Lifecycle f174a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f175b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f176c;

        LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f174a = lifecycle;
            this.f175b = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f174a.c(this);
            this.f175b.e(this);
            androidx.activity.a aVar = this.f176c;
            if (aVar != null) {
                aVar.cancel();
                this.f176c = null;
            }
        }

        @Override // androidx.lifecycle.e
        public void d(g gVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f176c = OnBackPressedDispatcher.this.b(this.f175b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f176c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b f178a;

        a(b bVar) {
            this.f178a = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f173b.remove(this.f178a);
            this.f178a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f172a = runnable;
    }

    public void a(g gVar, b bVar) {
        Lifecycle lifecycle = gVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f173b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f173b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f172a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
