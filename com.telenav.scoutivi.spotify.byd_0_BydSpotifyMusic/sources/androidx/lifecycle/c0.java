package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.b0;

/* JADX INFO: compiled from: ViewModelProviders.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 {
    private static Activity a(Fragment fragment) {
        androidx.fragment.app.d dVarJ = fragment.j();
        if (dVarJ != null) {
            return dVarJ;
        }
        throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
    }

    private static Application b(Activity activity) {
        Application application = activity.getApplication();
        if (application != null) {
            return application;
        }
        throw new IllegalStateException("Your activity/fragment is not yet attached to Application. You can't request ViewModel before onCreate call.");
    }

    public static b0 c(Fragment fragment) {
        return d(fragment, null);
    }

    public static b0 d(Fragment fragment, b0.b bVar) {
        Application applicationB = b(a(fragment));
        if (bVar == null) {
            bVar = b0.a.c(applicationB);
        }
        return new b0(fragment.k(), bVar);
    }

    public static b0 e(androidx.fragment.app.d dVar) {
        return f(dVar, null);
    }

    public static b0 f(androidx.fragment.app.d dVar, b0.b bVar) {
        Application applicationB = b(dVar);
        if (bVar == null) {
            bVar = b0.a.c(applicationB);
        }
        return new b0(dVar.k(), bVar);
    }
}
