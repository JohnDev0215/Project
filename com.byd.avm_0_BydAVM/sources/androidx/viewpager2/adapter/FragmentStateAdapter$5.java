package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;

/* JADX INFO: loaded from: classes.dex */
class FragmentStateAdapter$5 implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Handler f1360a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Runnable f1361b;

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1360a.removeCallbacks(this.f1361b);
            fVar.getLifecycle().c(this);
        }
    }
}
