package c.a.d;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.util.Log;
import android.util.TypedValue;

/* JADX INFO: compiled from: CommonUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f1595a = false;

    public static int a(Context context, int i) {
        try {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(i, typedValue, true);
            return typedValue.resourceId;
        } catch (Exception e) {
            Log.e("getAttribute", "getAttribute,error:" + e.getMessage());
            return 0;
        }
    }

    public static String b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("smart_charge", e.getMessage());
            return "1.0.0";
        }
    }

    public static boolean c(Context context) {
        try {
            switch (BYDAutoBodyworkDevice.getInstance(context).getAutoType()) {
                case 0:
                case 14:
                case 39:
                case 40:
                case 41:
                case 44:
                case 45:
                case 49:
                case 51:
                case 58:
                case 59:
                case 60:
                case 62:
                case 69:
                case 70:
                case 72:
                case 84:
                case 92:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 101:
                case 114:
                case 123:
                case 128:
                case 135:
                case 137:
                case 138:
                case 147:
                case 182:
                case 196:
                case 208:
                case 224:
                case 317:
                    break;
            }
            return false;
        } catch (Exception e) {
            Log.e("smart_charge", e.getMessage());
            return false;
        }
    }

    public static boolean d(Context context) {
        try {
            String strA = j.a(j.f1633a, "0");
            Log.i("isRightRudder", "persist.vendor.byd.cfg.huc.steering.wheel.position:" + strA);
            f1595a = "1".equals(strA);
        } catch (Exception e) {
            Log.e("isRightRudder", "isRightRudder,error:" + e.getMessage());
        }
        return f1595a;
    }
}
