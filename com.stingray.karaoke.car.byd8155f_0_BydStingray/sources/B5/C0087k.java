package B5;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.util.Map;

/* JADX INFO: renamed from: B5.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0087k extends L {

    /* JADX INFO: renamed from: B5.k$a */
    public static class a extends L {
        private a(Map<String, Object> map) {
            super(map);
        }
    }

    public C0087k(Map<String, Object> map) {
        super(map);
    }

    public static void n(Map map, String str, CharSequence charSequence) {
        if (D5.g.g(charSequence)) {
            map.put(str, "undefined");
        } else {
            map.put(str, charSequence);
        }
    }

    public final void i(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            D5.f fVar = new D5.f();
            n(fVar, "name", packageInfo.applicationInfo.loadLabel(packageManager));
            n(fVar, "version", packageInfo.versionName);
            n(fVar, "namespace", packageInfo.packageName);
            fVar.put("build", String.valueOf(packageInfo.versionCode));
            f(fVar, "app");
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public final void j() {
        a aVar = new a();
        aVar.f("", "id");
        aVar.f(Build.MANUFACTURER, "manufacturer");
        aVar.f(Build.MODEL, "model");
        aVar.f(Build.DEVICE, "name");
        aVar.f("android", "type");
        f(aVar, "device");
    }

    public final void k(Context context) {
        ConnectivityManager connectivityManager;
        D5.f fVar = new D5.f();
        boolean z6 = false;
        if (D5.g.f(0, context, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            fVar.put("wifi", Boolean.valueOf(networkInfo != null && networkInfo.isConnected()));
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(7);
            fVar.put("bluetooth", Boolean.valueOf(networkInfo2 != null && networkInfo2.isConnected()));
            NetworkInfo networkInfo3 = connectivityManager.getNetworkInfo(0);
            if (networkInfo3 != null && networkInfo3.isConnected()) {
                z6 = true;
            }
            fVar.put("cellular", Boolean.valueOf(z6));
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            fVar.put("carrier", telephonyManager.getNetworkOperatorName());
        } else {
            fVar.put("carrier", "unknown");
        }
        f(fVar, "network");
    }

    public final void l(Context context) {
        D5.f fVar = new D5.f();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        fVar.put("density", Float.valueOf(displayMetrics.density));
        fVar.put("height", Integer.valueOf(displayMetrics.heightPixels));
        fVar.put("width", Integer.valueOf(displayMetrics.widthPixels));
        f(fVar, "screen");
    }
}
