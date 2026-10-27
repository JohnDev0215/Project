package p;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class jx1 extends sd0 {
    final /* synthetic */ lx1 this$0;

    public jx1(lx1 lx1Var) {
        this.this$0 = lx1Var;
    }

    @Override // p.sd0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        v03.j(activity, "activity");
        if (Build.VERSION.SDK_INT < 29) {
            int i = j32.e;
            Fragment fragmentFindFragmentByTag = activity.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
            v03.g(fragmentFindFragmentByTag, "null cannot be cast to non-null type androidx.lifecycle.ReportFragment");
            ((j32) fragmentFindFragmentByTag).c = this.this$0.f151p;
        }
    }

    @Override // p.sd0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        v03.j(activity, "activity");
        lx1 lx1Var = this.this$0;
        int i = lx1Var.e - 1;
        lx1Var.e = i;
        if (i == 0) {
            Handler handler = lx1Var.m;
            v03.h(handler);
            handler.postDelayed(lx1Var.o, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        v03.j(activity, "activity");
        hx1.a(activity, new ix1(this.this$0));
    }

    @Override // p.sd0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        v03.j(activity, "activity");
        lx1 lx1Var = this.this$0;
        int i = lx1Var.c - 1;
        lx1Var.c = i;
        if (i == 0 && lx1Var.k) {
            lx1Var.n.f(Lifecycle$Event.ON_STOP);
            lx1Var.l = true;
        }
    }
}
