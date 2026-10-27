package org.chromium.content.browser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Pair;
import defpackage.AbstractC0827gO;
import defpackage.Bz;
import defpackage.C0571bs;
import defpackage.Ts;
import java.util.Map;
import java.util.WeakHashMap;
import org.chromium.support_lib_boundary.WebSettingsBoundaryInterface;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ScreenOrientationProviderImpl {
    public Map a = new WeakHashMap();

    public static int a(byte b, WindowAndroid windowAndroid, Context context) {
        switch (b) {
            case WebSettingsBoundaryInterface.ForceDarkBehavior.FORCE_DARK_ONLY /* 0 */:
                return -1;
            case 1:
                return 1;
            case 2:
                return 9;
            case 3:
                return 0;
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                return 8;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                return 10;
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                return 6;
            case 7:
                return 7;
            case 8:
                AbstractC0827gO abstractC0827gO = windowAndroid.t;
                int i = abstractC0827gO.g;
                if (i == 0 || i == 2) {
                    Point point = abstractC0827gO.c;
                    return point.y >= point.x ? 1 : 0;
                }
                Point point2 = abstractC0827gO.c;
                return point2.y < point2.x ? 1 : 0;
            default:
                Ts.f("ScreenOrientation", "Trying to lock to unsupported orientation!", new Object[0]);
                return -1;
        }
    }

    public static ScreenOrientationProviderImpl getInstance() {
        return Bz.a;
    }

    public final void b(Activity activity, boolean z, int i) {
        if (this.a.containsKey(activity)) {
            this.a.put(activity, Pair.create(Boolean.valueOf(z), Integer.valueOf(i)));
        } else {
            activity.setRequestedOrientation(i);
        }
    }

    public boolean isOrientationLockEnabled() {
        return true;
    }

    public void lockOrientation(WindowAndroid windowAndroid, byte b) {
        Activity activity;
        int iA;
        if (windowAndroid == null || (activity = (Activity) windowAndroid.Q().get()) == null || (iA = a(b, windowAndroid, activity)) == -1) {
            return;
        }
        b(activity, true, iA);
    }

    public void unlockOrientation(WindowAndroid windowAndroid) {
        Activity activity;
        if (windowAndroid == null || (activity = (Activity) windowAndroid.Q().get()) == null) {
            return;
        }
        int iA = a((byte) activity.getIntent().getIntExtra("org.chromium.content_public.common.orientation", 0), windowAndroid, activity);
        if (iA == -1) {
            try {
                iA = activity.getPackageManager().getActivityInfo(activity.getComponentName(), 128).screenOrientation;
            } catch (PackageManager.NameNotFoundException unused) {
            } catch (Throwable th) {
                b(activity, false, iA);
                throw th;
            }
        }
        b(activity, false, iA);
    }
}
