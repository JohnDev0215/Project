package K3;

import S8.W;
import androidx.lifecycle.A;
import androidx.lifecycle.DefaultLifecycleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class a implements DefaultLifecycleObserver {
    public final A9.b l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final W f3509m;

    public a(A9.b bVar, W w10) {
        this.l = bVar;
        this.f3509m = w10;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onDestroy(A a10) {
        this.f3509m.g(null);
    }
}
