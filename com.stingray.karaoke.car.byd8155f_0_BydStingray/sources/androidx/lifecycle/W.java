package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.os.Bundle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"Landroidx/lifecycle/W;", "Landroid/app/Fragment;", "<init>", "()V", "androidx/lifecycle/U", "a", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class W extends Fragment {
    public static final /* synthetic */ int l = 0;

    public static final class a implements Application.ActivityLifecycleCallbacks {
        public static final V Companion = new V();

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            A7.m.f(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new a());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            A7.m.f(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            A7.m.f(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            A7.m.f(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            A7.m.f(activity, "activity");
            int i7 = W.l;
            U.a(activity, EnumC0980s.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            A7.m.f(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            A7.m.f(activity, "activity");
            A7.m.f(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            A7.m.f(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            A7.m.f(activity, "activity");
        }
    }
}
