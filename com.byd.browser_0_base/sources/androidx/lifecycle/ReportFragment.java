package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.directwriting.IDirectWritingService;
import androidx.lifecycle.d;
import defpackage.b71;
import defpackage.c71;
import defpackage.i21;

/* JADX INFO: compiled from: ReportFragment.kt */
/* JADX INFO: loaded from: classes.dex */
public class ReportFragment extends Fragment {
    public static final /* synthetic */ int f = 0;
    public a e;

    /* JADX INFO: compiled from: ReportFragment.kt */
    public interface a {
        void a();

        void onCreate();

        void onResume();
    }

    /* JADX INFO: compiled from: ReportFragment.kt */
    public static final class b {
        /* JADX WARN: Multi-variable type inference failed */
        public static void a(Activity activity, d.a aVar) {
            i21.e(activity, "activity");
            i21.e(aVar, IDirectWritingService.KEY_BUNDLE_EVENT);
            if (activity instanceof c71) {
                ((c71) activity).B0().e(aVar);
            } else if (activity instanceof b71) {
                g gVarJ1 = ((b71) activity).j1();
                if (gVarJ1 instanceof g) {
                    gVarJ1.e(aVar);
                }
            }
        }

        public static void b(Activity activity) {
            i21.e(activity, "activity");
            c.Companion.getClass();
            activity.registerActivityLifecycleCallbacks(new c());
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragmentManager.beginTransaction().add(new ReportFragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
                fragmentManager.executePendingTransactions();
            }
        }
    }

    /* JADX INFO: compiled from: ReportFragment.kt */
    public static final class c implements Application.ActivityLifecycleCallbacks {
        public static final a Companion = new a();

        /* JADX INFO: compiled from: ReportFragment.kt */
        public static final class a {
        }

        public static final void registerIn(Activity activity) {
            Companion.getClass();
            i21.e(activity, "activity");
            activity.registerActivityLifecycleCallbacks(new c());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            i21.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            i21.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            i21.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(Activity activity, Bundle bundle) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(Activity activity) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(Activity activity) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(Activity activity) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(Activity activity) {
            i21.e(activity, "activity");
            int i = ReportFragment.f;
            b.a(activity, d.a.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            i21.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            i21.e(activity, "activity");
            i21.e(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            i21.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            i21.e(activity, "activity");
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a aVar = this.e;
        if (aVar != null) {
            aVar.onCreate();
        }
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.e = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        a aVar = this.e;
        if (aVar != null) {
            aVar.onResume();
        }
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        a aVar = this.e;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
    }
}
