package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import b.m.d;
import b.m.f;

/* JADX INFO: loaded from: classes.dex */
public class FragmentStateAdapter$5 implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Handler f546a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Runnable f547b;

    @Override // b.m.d
    public void d(f fVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f546a.removeCallbacks(this.f547b);
            fVar.getLifecycle().c(this);
        }
    }
}
