package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;

/* JADX INFO: loaded from: classes.dex */
class FragmentStateAdapter$5 implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Handler f1632a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ Runnable f1633b;

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        if (aVar == d.a.ON_DESTROY) {
            this.f1632a.removeCallbacks(this.f1633b);
            gVar.a().c(this);
        }
    }
}
