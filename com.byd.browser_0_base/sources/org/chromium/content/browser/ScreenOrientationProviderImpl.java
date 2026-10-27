package org.chromium.content.browser;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Log;
import android.util.Pair;
import defpackage.c8;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.qe2;
import defpackage.ya0;
import java.util.WeakHashMap;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class ScreenOrientationProviderImpl implements ApplicationStatus.c {
    public qe2 e;
    public final WeakHashMap f = new WeakHashMap();
    public final WeakHashMap g = new WeakHashMap();
    public final WeakHashMap h = new WeakHashMap();

    public static class a {
        public static final ScreenOrientationProviderImpl a = new ScreenOrientationProviderImpl();
    }

    public static final class b implements jt3 {
        public final ScreenOrientationProviderImpl e;
        public final kt3 f;
        public final boolean g;
        public final byte h;
        public boolean i;

        public b(ScreenOrientationProviderImpl screenOrientationProviderImpl, kt3 kt3Var, boolean z, byte b) {
            this.e = screenOrientationProviderImpl;
            this.f = kt3Var;
            this.g = z;
            this.h = b;
            kt3Var.b(this);
        }

        @Override // defpackage.jt3
        public final void a(WindowAndroid windowAndroid) {
            if (windowAndroid == null) {
                return;
            }
            boolean z = this.g;
            ScreenOrientationProviderImpl screenOrientationProviderImpl = this.e;
            if (z) {
                screenOrientationProviderImpl.c(windowAndroid, this.h);
            } else {
                screenOrientationProviderImpl.f(windowAndroid);
            }
            this.f.e.c(this);
            this.i = true;
        }
    }

    public static int b(byte b2, WindowAndroid windowAndroid, Activity activity) {
        switch (b2) {
            case 0:
                return -1;
            case 1:
                return 1;
            case 2:
                return 9;
            case 3:
                return 0;
            case 4:
                return 8;
            case 5:
                return 10;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                ya0 ya0VarA = windowAndroid != null ? windowAndroid.h : ya0.a(activity);
                int i = ya0VarA.i;
                if (i == 0 || i == 2) {
                    Point point = ya0VarA.c;
                    return point.y >= point.x ? 1 : 0;
                }
                Point point2 = ya0VarA.c;
                return point2.y < point2.x ? 1 : 0;
            default:
                Log.w("cr_ScreenOrientation", "Trying to lock to unsupported orientation!");
                return -1;
        }
    }

    @CalledByNative
    public static ScreenOrientationProviderImpl getInstance() {
        return a.a;
    }

    public final void a(WebContents webContents, boolean z, byte b2) {
        kt3 kt3VarE = kt3.e(webContents);
        WeakHashMap weakHashMap = this.h;
        b bVar = (b) weakHashMap.get(webContents);
        if (bVar != null && !bVar.i) {
            bVar.f.e.c(bVar);
            bVar.i = true;
        }
        weakHashMap.put(webContents, new b(this, kt3VarE, z, b2));
    }

    public final void c(WindowAndroid windowAndroid, byte b2) {
        Activity activity;
        int iB;
        if (windowAndroid == null || (activity = (Activity) windowAndroid.k().get()) == null || (iB = b(b2, windowAndroid, activity)) == -1) {
            return;
        }
        e(iB, activity, true);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 6) {
            this.g.remove(activity);
        }
    }

    public final void e(int i, Activity activity, boolean z) {
        boolean z2;
        WeakHashMap weakHashMap = this.g;
        if (weakHashMap.containsKey(activity)) {
            weakHashMap.put(activity, Pair.create(Boolean.valueOf(z), Integer.valueOf(i)));
            return;
        }
        qe2 qe2Var = this.e;
        if (qe2Var != null) {
            if (z) {
                return;
            }
            if (!z) {
                c8 c8Var = (c8) qe2Var;
                if (c8Var.f != activity || c8Var.h == null) {
                    z2 = true;
                } else {
                    c8Var.h = Integer.valueOf(i);
                    z2 = false;
                }
                if (!z2) {
                    return;
                }
            }
        }
        activity.setRequestedOrientation(i);
    }

    public final void f(WindowAndroid windowAndroid) {
        Activity activity;
        if (windowAndroid == null || (activity = (Activity) windowAndroid.k().get()) == null) {
            return;
        }
        WeakHashMap weakHashMap = this.f;
        int iB = b(weakHashMap.containsKey(activity) ? ((Byte) weakHashMap.get(activity)).byteValue() : (byte) 0, windowAndroid, activity);
        if (iB == -1) {
            try {
                iB = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 128).screenOrientation;
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                e(iB, activity, false);
                throw th;
            }
        }
        e(iB, activity, false);
    }

    @CalledByNative
    public boolean isOrientationLockEnabled() {
        qe2 qe2Var = this.e;
        if (qe2Var == null) {
            return true;
        }
        qe2Var.getClass();
        return false;
    }

    @CalledByNative
    public final void lockOrientationForWebContents(WebContents webContents, byte b2) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            a(webContents, true, b2);
        } else {
            c(windowAndroidV, b2);
        }
    }

    @CalledByNative
    public final void unlockOrientationForWebContents(WebContents webContents) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            a(webContents, false, (byte) 0);
        } else {
            f(windowAndroidV);
        }
    }
}
