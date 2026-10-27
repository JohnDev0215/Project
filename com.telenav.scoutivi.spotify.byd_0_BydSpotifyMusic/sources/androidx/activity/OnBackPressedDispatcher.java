package androidx.activity;

import android.annotation.SuppressLint;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Runnable f318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f319b = new ArrayDeque<>();

    private class LifecycleOnBackPressedCancellable implements m, androidx.activity.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final i f320e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final b f321g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private androidx.activity.a f322h;

        LifecycleOnBackPressedCancellable(i iVar, b bVar) {
            this.f320e = iVar;
            this.f321g = bVar;
            iVar.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f320e.c(this);
            this.f321g.e(this);
            androidx.activity.a aVar = this.f322h;
            if (aVar != null) {
                aVar.cancel();
                this.f322h = null;
            }
        }

        @Override // androidx.lifecycle.m
        public void d(o oVar, i.b bVar) {
            if (bVar == i.b.ON_START) {
                this.f322h = OnBackPressedDispatcher.this.b(this.f321g);
                return;
            }
            if (bVar != i.b.ON_STOP) {
                if (bVar == i.b.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.f322h;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final b f324e;

        a(b bVar) {
            this.f324e = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f319b.remove(this.f324e);
            this.f324e.e(this);
        }
    }

    public OnBackPressedDispatcher(Runnable runnable) {
        this.f318a = runnable;
    }

    @SuppressLint({"LambdaLast"})
    public void a(o oVar, b bVar) {
        i iVarA = oVar.a();
        if (iVarA.b() == i.c.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(iVarA, bVar));
    }

    androidx.activity.a b(b bVar) {
        this.f319b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    public boolean c() {
        Iterator<b> itDescendingIterator = this.f319b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            if (itDescendingIterator.next().c()) {
                return true;
            }
        }
        return false;
    }

    public void d() {
        Iterator<b> itDescendingIterator = this.f319b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f318a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
