package p;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;

/* JADX INFO: loaded from: classes.dex */
public final class j32 extends Fragment {
    public static final /* synthetic */ int e = 0;
    public kx1 c;

    public final void a(Lifecycle$Event lifecycle$Event) {
        if (Build.VERSION.SDK_INT < 29) {
            Activity activity = getActivity();
            v03.i(activity, "activity");
            u11.x(activity, lifecycle$Event);
        }
    }

    @Override // android.app.Fragment
    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(Lifecycle$Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        a(Lifecycle$Event.ON_DESTROY);
        this.c = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        a(Lifecycle$Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        kx1 kx1Var = this.c;
        if (kx1Var != null) {
            kx1Var.a.b();
        }
        a(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public final void onStart() {
        super.onStart();
        kx1 kx1Var = this.c;
        if (kx1Var != null) {
            lx1 lx1Var = kx1Var.a;
            int i = lx1Var.c + 1;
            lx1Var.c = i;
            if (i == 1 && lx1Var.l) {
                lx1Var.n.f(Lifecycle$Event.ON_START);
                lx1Var.l = false;
            }
        }
        a(Lifecycle$Event.ON_START);
    }

    @Override // android.app.Fragment
    public final void onStop() {
        super.onStop();
        a(Lifecycle$Event.ON_STOP);
    }
}
