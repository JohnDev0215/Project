package defpackage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ComponentCallbacks2;
import android.os.Bundle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class N4 extends Fragment {
    public M4 s;

    public static void b(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new N4(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public final void a(F4 f4) {
        ComponentCallbacks2 activity = getActivity();
        if (activity instanceof I4) {
            H4 h4B = ((I4) activity).b();
            if (h4B instanceof J4) {
                ((J4) h4B).b(f4);
            }
        }
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(F4.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a(F4.ON_DESTROY);
        this.s = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        a(F4.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        a(F4.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        a(F4.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        a(F4.ON_STOP);
    }
}
