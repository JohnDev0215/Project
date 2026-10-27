package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import b.n.e;
import b.n.g;

/* JADX INFO: loaded from: classes.dex */
public class FragmentStateAdapter$5 implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Handler f1390a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f1391b;

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1390a.removeCallbacks(this.f1391b);
            gVar.getLifecycle().c(this);
        }
    }
}
