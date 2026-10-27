package N3;

import S8.C0712i;
import androidx.lifecycle.A;
import androidx.lifecycle.DefaultLifecycleObserver;
import m7.q;

/* JADX INFO: loaded from: classes.dex */
public final class b implements DefaultLifecycleObserver {
    public final /* synthetic */ C0712i l;

    public b(C0712i c0712i) {
        this.l = c0712i;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onStart(A a10) {
        this.l.resumeWith(q.f19267a);
    }
}
