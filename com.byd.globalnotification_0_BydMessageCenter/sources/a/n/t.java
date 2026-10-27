package a.n;

import a.n.g;
import android.app.Activity;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class t extends c {
    public final /* synthetic */ s this$0;

    public class a extends c {
        public a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            t.this.this$0.b();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            t.this.this$0.e();
        }
    }

    public t(s sVar) {
        this.this$0 = sVar;
    }

    @Override // a.n.c, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // a.n.c, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        s sVar = this.this$0;
        int i = sVar.f1104b - 1;
        sVar.f1104b = i;
        if (i == 0) {
            sVar.f1107e.postDelayed(sVar.g, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        activity.registerActivityLifecycleCallbacks(new a());
    }

    @Override // a.n.c, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        s sVar = this.this$0;
        int i = sVar.f1103a - 1;
        sVar.f1103a = i;
        if (i == 0 && sVar.f1105c) {
            sVar.f1108f.d(g.a.ON_STOP);
            sVar.f1106d = true;
        }
    }
}
