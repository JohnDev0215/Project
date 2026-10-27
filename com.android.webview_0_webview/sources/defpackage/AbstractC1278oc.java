package defpackage;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* JADX INFO: renamed from: oc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1278oc {
    public static final int a;

    static {
        int i = AbstractC1388qc.a;
        a = 20367000;
    }

    @Deprecated
    public abstract Intent a(int i);

    public Intent b(Context context, int i, String str) {
        if (i != 1 && i != 2) {
            if (i != 3) {
                return null;
            }
            return AbstractC0041Cf.c("com.google.android.gms");
        }
        if (context != null && AbstractC0203Lf.b(context)) {
            return AbstractC0041Cf.b();
        }
        StringBuilder sbF = Z7.f("gcore_");
        sbF.append(a);
        sbF.append("-");
        if (!TextUtils.isEmpty(str)) {
            sbF.append(str);
        }
        sbF.append("-");
        if (context != null) {
            sbF.append(context.getPackageName());
        }
        sbF.append("-");
        if (context != null) {
            try {
                C0274Pf c0274PfA = C0292Qf.a(context);
                sbF.append(c0274PfA.a.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return AbstractC0041Cf.a("com.google.android.gms", sbF.toString());
    }

    public PendingIntent c(Context context, int i, int i2, String str) {
        Intent intentB = b(context, i, str);
        if (intentB == null) {
            return null;
        }
        return PendingIntent.getActivity(context, i2, intentB, 134217728);
    }

    public int d(Context context, int i) {
        int iA = AbstractC1388qc.a(context, i);
        if (AbstractC1388qc.b(context, iA)) {
            return 18;
        }
        return iA;
    }
}
