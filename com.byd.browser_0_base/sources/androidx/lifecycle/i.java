package androidx.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import androidx.lifecycle.d;
import defpackage.i21;
import defpackage.ug0;

/* JADX INFO: compiled from: ProcessLifecycleOwner.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i extends ug0 {
    final /* synthetic */ j this$0;

    /* JADX INFO: compiled from: ProcessLifecycleOwner.kt */
    public static final class a extends ug0 {
        final /* synthetic */ j this$0;

        public a(j jVar) {
            this.this$0 = jVar;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            i21.e(activity, "activity");
            this.this$0.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            i21.e(activity, "activity");
            j jVar = this.this$0;
            int i = jVar.e + 1;
            jVar.e = i;
            if (i == 1 && jVar.h) {
                jVar.j.e(d.a.ON_START);
                jVar.h = false;
            }
        }
    }

    public i(j jVar) {
        this.this$0 = jVar;
    }

    @Override // defpackage.ug0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        i21.e(activity, "activity");
    }

    @Override // defpackage.ug0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        i21.e(activity, "activity");
        j jVar = this.this$0;
        int i = jVar.f - 1;
        jVar.f = i;
        if (i == 0) {
            Handler handler = jVar.i;
            i21.b(handler);
            handler.postDelayed(jVar.k, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        i21.e(activity, "activity");
        activity.registerActivityLifecycleCallbacks(new a(this.this$0));
    }

    @Override // defpackage.ug0, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        i21.e(activity, "activity");
        j jVar = this.this$0;
        int i = jVar.e - 1;
        jVar.e = i;
        if (i == 0 && jVar.g) {
            jVar.j.e(d.a.ON_STOP);
            jVar.h = true;
        }
    }
}
