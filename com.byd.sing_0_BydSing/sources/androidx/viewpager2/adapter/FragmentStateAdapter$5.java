package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;

/* JADX INFO: loaded from: classes.dex */
class FragmentStateAdapter$5 implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Handler f1886a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Runnable f1887b;

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1886a.removeCallbacks(this.f1887b);
            fVar.a().c(this);
        }
    }
}
