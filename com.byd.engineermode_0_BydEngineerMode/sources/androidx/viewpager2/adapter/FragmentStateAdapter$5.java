package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;

/* JADX INFO: loaded from: classes.dex */
class FragmentStateAdapter$5 implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Handler f1440a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Runnable f1441b;

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1440a.removeCallbacks(this.f1441b);
            fVar.a().c(this);
        }
    }
}
