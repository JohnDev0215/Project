package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
public class FragmentStateAdapter$5 implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Handler f1714a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f1715b;

    @Override // t0.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1714a.removeCallbacks(this.f1715b);
            fVar.getLifecycle().c(this);
        }
    }
}
