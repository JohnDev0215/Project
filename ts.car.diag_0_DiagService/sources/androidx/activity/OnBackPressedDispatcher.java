package androidx.activity;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import java.util.ArrayDeque;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class OnBackPressedDispatcher {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final Runnable f76a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayDeque<b> f77b = new ArrayDeque<>();

    /* JADX INFO: compiled from: caradapter-bug */
    private class LifecycleOnBackPressedCancellable implements d, androidx.activity.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Lifecycle f78c;
        private final b d;

        @Nullable
        private androidx.activity.a e;

        LifecycleOnBackPressedCancellable(@NonNull Lifecycle lifecycle, @NonNull b bVar) {
            this.f78c = lifecycle;
            this.d = bVar;
            lifecycle.a(this);
        }

        @Override // androidx.activity.a
        public void cancel() {
            this.f78c.c(this);
            this.d.e(this);
            androidx.activity.a aVar = this.e;
            if (aVar != null) {
                aVar.cancel();
                this.e = null;
            }
        }

        @Override // androidx.lifecycle.d
        public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_START) {
                this.e = OnBackPressedDispatcher.this.b(this.d);
                return;
            }
            if (event != Lifecycle.Event.ON_STOP) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    cancel();
                }
            } else {
                androidx.activity.a aVar = this.e;
                if (aVar != null) {
                    aVar.cancel();
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class a implements androidx.activity.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final b f79c;

        a(b bVar) {
            this.f79c = bVar;
        }

        @Override // androidx.activity.a
        public void cancel() {
            OnBackPressedDispatcher.this.f77b.remove(this.f79c);
            this.f79c.e(this);
        }
    }

    public OnBackPressedDispatcher(@Nullable Runnable runnable) {
        this.f76a = runnable;
    }

    @SuppressLint({"LambdaLast"})
    @MainThread
    public void a(@NonNull f fVar, @NonNull b bVar) {
        Lifecycle lifecycleA = fVar.a();
        if (lifecycleA.b() == Lifecycle.State.DESTROYED) {
            return;
        }
        bVar.a(new LifecycleOnBackPressedCancellable(lifecycleA, bVar));
    }

    @NonNull
    @MainThread
    androidx.activity.a b(@NonNull b bVar) {
        this.f77b.add(bVar);
        a aVar = new a(bVar);
        bVar.a(aVar);
        return aVar;
    }

    @MainThread
    public void c() {
        Iterator<b> itDescendingIterator = this.f77b.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            b next = itDescendingIterator.next();
            if (next.c()) {
                next.b();
                return;
            }
        }
        Runnable runnable = this.f76a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
