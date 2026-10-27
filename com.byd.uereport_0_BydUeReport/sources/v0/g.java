package v0;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/* JADX INFO: compiled from: FragmentManager.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e f8364d = new e();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public e f8365e = null;

    /* JADX INFO: compiled from: FragmentManager.java */
    public static abstract class a {
        public void onFragmentActivityCreated(g gVar, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentAttached(g gVar, Fragment fragment, Context context) {
        }

        public void onFragmentCreated(g gVar, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentDestroyed(g gVar, Fragment fragment) {
        }

        public void onFragmentDetached(g gVar, Fragment fragment) {
        }

        public void onFragmentPaused(g gVar, Fragment fragment) {
        }

        public void onFragmentPreAttached(g gVar, Fragment fragment, Context context) {
        }

        public void onFragmentPreCreated(g gVar, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentResumed(g gVar, Fragment fragment) {
        }

        public void onFragmentSaveInstanceState(g gVar, Fragment fragment, Bundle bundle) {
        }

        public void onFragmentStarted(g gVar, Fragment fragment) {
        }

        public void onFragmentStopped(g gVar, Fragment fragment) {
        }

        public abstract void onFragmentViewCreated(g gVar, Fragment fragment, View view, Bundle bundle);

        public void onFragmentViewDestroyed(g gVar, Fragment fragment) {
        }
    }

    /* JADX INFO: compiled from: FragmentManager.java */
    public interface b {
        void a();
    }

    public abstract j a();

    public abstract void b(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean c();

    public abstract Fragment d(String str);

    public abstract Fragment e(Bundle bundle, String str);

    public e f() {
        if (this.f8365e == null) {
            this.f8365e = f8364d;
        }
        return this.f8365e;
    }

    public abstract List<Fragment> g();

    public abstract boolean h();

    public abstract boolean i();

    public abstract boolean j();

    public abstract void k(Bundle bundle, String str, Fragment fragment);

    public abstract void l(a aVar, boolean z7);

    public abstract Fragment.SavedState m(Fragment fragment);

    public void n(e eVar) {
        this.f8365e = eVar;
    }

    public abstract void o(a aVar);
}
