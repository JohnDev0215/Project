package androidx.viewpager2.adapter;

import android.os.Handler;
import androidx.lifecycle.Lifecycle;
import defpackage.cc;
import defpackage.ec;

/* JADX INFO: loaded from: classes.dex */
public class FragmentStateAdapter$5 implements cc {
    public final /* synthetic */ Handler a;
    public final /* synthetic */ Runnable b;

    @Override // defpackage.cc
    public void d(ec ecVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.a.removeCallbacks(this.b);
            ecVar.getLifecycle().c(this);
        }
    }
}
