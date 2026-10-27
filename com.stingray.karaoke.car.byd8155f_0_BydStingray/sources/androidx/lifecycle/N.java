package androidx.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public final class N extends AbstractC0974l {
    final /* synthetic */ ProcessLifecycleOwner this$0;

    public static final class a extends AbstractC0974l {
        final /* synthetic */ ProcessLifecycleOwner this$0;

        public a(ProcessLifecycleOwner processLifecycleOwner) {
            this.this$0 = processLifecycleOwner;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            A7.m.f(activity, "activity");
            ProcessLifecycleOwner processLifecycleOwner = this.this$0;
            int i7 = processLifecycleOwner.f11940m + 1;
            processLifecycleOwner.f11940m = i7;
            if (i7 == 1) {
                if (processLifecycleOwner.f11941n) {
                    processLifecycleOwner.f11943q.c1(EnumC0980s.ON_RESUME);
                    processLifecycleOwner.f11941n = false;
                } else {
                    Handler handler = processLifecycleOwner.f11942p;
                    A7.m.c(handler);
                    handler.removeCallbacks(processLifecycleOwner.f11944r);
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            A7.m.f(activity, "activity");
            ProcessLifecycleOwner processLifecycleOwner = this.this$0;
            int i7 = processLifecycleOwner.l + 1;
            processLifecycleOwner.l = i7;
            if (i7 == 1 && processLifecycleOwner.o) {
                processLifecycleOwner.f11943q.c1(EnumC0980s.ON_START);
                processLifecycleOwner.o = false;
            }
        }
    }

    public N(ProcessLifecycleOwner processLifecycleOwner) {
        this.this$0 = processLifecycleOwner;
    }

    @Override // androidx.lifecycle.AbstractC0974l, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        A7.m.f(activity, "activity");
    }

    @Override // androidx.lifecycle.AbstractC0974l, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        A7.m.f(activity, "activity");
        ProcessLifecycleOwner processLifecycleOwner = this.this$0;
        int i7 = processLifecycleOwner.f11940m - 1;
        processLifecycleOwner.f11940m = i7;
        if (i7 == 0) {
            Handler handler = processLifecycleOwner.f11942p;
            A7.m.c(handler);
            handler.postDelayed(processLifecycleOwner.f11944r, 700L);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPreCreated(Activity activity, Bundle bundle) {
        A7.m.f(activity, "activity");
        M.a(activity, new a(this.this$0));
    }

    @Override // androidx.lifecycle.AbstractC0974l, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        A7.m.f(activity, "activity");
        ProcessLifecycleOwner processLifecycleOwner = this.this$0;
        int i7 = processLifecycleOwner.l - 1;
        processLifecycleOwner.l = i7;
        if (i7 == 0 && processLifecycleOwner.f11941n) {
            processLifecycleOwner.f11943q.c1(EnumC0980s.ON_STOP);
            processLifecycleOwner.o = true;
        }
    }
}
