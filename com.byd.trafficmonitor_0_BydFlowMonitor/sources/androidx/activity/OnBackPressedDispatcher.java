package androidx.activity;

import android.annotation.SuppressLint;
import androidx.lifecycle.Lifecycle;
import b.a.b;
import b.n.e;
import b.n.g;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f202a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque<b> f203b = new ArrayDeque<>();

    public class LifecycleOnBackPressedCancellable implements e, b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Lifecycle f204a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final b f205b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.a.a f206c;

        public LifecycleOnBackPressedCancellable(Lifecycle lifecycle, b bVar) {
            this.f204a = lifecycle;
            this.f205b = bVar;
            lifecycle.a(this);
        }

        @Override // b.a.a
        public void cancel() {
            this.f204a.c(this);
            this.f205b.e(this);
            b.a.a aVar = this.f206c;
            if (aVar != null) {
                aVar.cancel();
                this.f206c = null;
            }
        }

        @Override // b.n.e
        public void d(g gVar, Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.f206c = OnBackPressedDispatcher.this.b(this.f205b);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                b.a.a aVar = this.f206c;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    public class a implements b.a.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b f208a;

        public a(b bVar) {
            this.f208a = bVar;
        }

        @Override // b.a.a
        public void cancel() {
            OnBackPressedDispatcher.this.f203b.remove(this.f208a);
            this.f208a.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f202a = runnable;
    }

    @SuppressLint({"LambdaLast"})
    public void a(g gVar, b bVar) {
        Lifecycle lifecycle = gVar.getLifecycle();
        if (lifecycle.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycle, bVar));
    }

    public b.a.a b(b bVar) {
        this.f203b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public void c() {
        Iterator<b> itDescendingIterator = this.f203b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f202a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
