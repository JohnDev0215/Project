package p;

import android.app.Activity;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class ix1 extends sd0 {
    final /* synthetic */ lx1 this$0;

    public ix1(lx1 lx1Var) {
        this.this$0 = lx1Var;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostResumed(Activity activity) {
        v03.j(activity, "activity");
        this.this$0.b();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPostStarted(Activity activity) {
        v03.j(activity, "activity");
        lx1 lx1Var = this.this$0;
        int i = lx1Var.c + 1;
        lx1Var.c = i;
        if (i == 1 && lx1Var.l) {
            lx1Var.n.f(Lifecycle$Event.ON_START);
            lx1Var.l = false;
        }
    }
}
