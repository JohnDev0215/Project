package org.chromium.components.webxr;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import defpackage.fa1;
import defpackage.nx0;
import defpackage.xz;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.webxr.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class ArCoreInstallUtils {
    public static a b;
    public static org.chromium.components.webxr.a c;
    public long a;

    public ArCoreInstallUtils(long j) {
        this.a = j;
    }

    public static org.chromium.components.webxr.a a() {
        org.chromium.components.webxr.a aVar = c;
        if (aVar != null) {
            return aVar;
        }
        try {
            org.chromium.components.webxr.a aVar2 = (org.chromium.components.webxr.a) ArCoreShimImpl.class.newInstance();
            c = aVar2;
            return aVar2;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        }
    }

    @CalledByNative
    public static ArCoreInstallUtils create(long j) {
        return new ArCoreInstallUtils(j);
    }

    @CalledByNative
    public static int getArCoreInstallStatus() {
        try {
            return a().b(xz.a);
        } catch (RuntimeException e) {
            fa1.k("ArCoreInstallUtils", "ARCore availability check failed with error: %s", e.toString());
            return 6;
        }
    }

    @CalledByNative
    public static boolean shouldRequestInstallSupportedArCore() {
        return getArCoreInstallStatus() != 1;
    }

    public final void b(boolean z) {
        long j = this.a;
        if (j != 0) {
            new z7().U(j, z);
        }
    }

    @CalledByNative
    public final void onNativeDestroy() {
        this.a = 0L;
        a aVar = b;
        if (aVar != null) {
            aVar.a = null;
            Application application = aVar.c.get();
            if (application != null) {
                application.unregisterActivityLifecycleCallbacks(aVar);
            }
            b = null;
        }
    }

    @CalledByNative
    public final void requestInstallSupportedArCore(WebContents webContents) {
        WindowAndroid windowAndroidV;
        Activity activity = (webContents == null || (windowAndroidV = webContents.V()) == null) ? null : (Activity) windowAndroidV.k().get();
        if (activity == null) {
            Log.w("cr_ArCoreInstallUtils", "Could not get Activity");
            b(false);
            return;
        }
        try {
            int iA = a().a(activity);
            if (iA == 1) {
                b = new a(this, activity);
            } else if (iA == 0) {
                b(true);
            }
        } catch (a.C0146a e) {
            b = null;
            fa1.k("ArCoreInstallUtils", "ARCore installation request failed with exception: %s", e.toString());
            b(false);
        } catch (a.b unused) {
            b(false);
        }
    }

    public class a implements Application.ActivityLifecycleCallbacks {
        public ArCoreInstallUtils a;
        public final nx0<Activity> b;
        public final nx0<Application> c;

        public a(ArCoreInstallUtils arCoreInstallUtils, Activity activity) {
            this.a = arCoreInstallUtils;
            this.b = new nx0<>(activity);
            Application application = activity.getApplication();
            this.c = new nx0<>(application);
            application.registerActivityLifecycleCallbacks(this);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            ArCoreInstallUtils arCoreInstallUtils;
            if (this.b.get() != activity || (arCoreInstallUtils = this.a) == null) {
                return;
            }
            arCoreInstallUtils.b(ArCoreInstallUtils.getArCoreInstallStatus() == 1);
            a aVar = ArCoreInstallUtils.b;
            aVar.a = null;
            Application application = aVar.c.get();
            if (application != null) {
                application.unregisterActivityLifecycleCallbacks(aVar);
            }
            ArCoreInstallUtils.b = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }
    }
}
