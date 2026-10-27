package androidx.activity;

import android.annotation.SuppressLint;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f72a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f73b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements e, androidx.activity.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f74a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final b f75b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.activity.a f76c;

        LifecycleOnBackPressedCancellable(d dVar, b bVar) {
            this.f74a = dVar;
            this.f75b = bVar;
            dVar.a(this);
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

        @Override // androidx.lifecycle.e
        public void d(g gVar, d.a aVar) {
            if (aVar == d.a.ON_START) {
                this.f76c = OnBackPressedDispatcher.this.b(this.f75b);
                return;
            }
            if (aVar != d.a.ON_STOP) {
                if (aVar == d.a.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar2 = this.f76c;
                if (aVar2 != null) {
                    aVar2.cancel();
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

    @SuppressLint({"LambdaLast"})
    public void a(g gVar, b bVar) {
        d dVarA = gVar.a();
        if (dVarA.b() == d.b.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(dVarA, bVar));
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
