package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: renamed from: rf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1445rf {
    public static final C1633v1 a = new C1633v1();

    public static String a(Context context) {
        String packageName = context.getPackageName();
        try {
            C0274Pf c0274PfA = C0292Qf.a(context);
            return c0274PfA.a.getPackageManager().getApplicationLabel(c0274PfA.a.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String b(Context context) {
        return context.getResources().getString(AbstractC0566bn.T0);
    }

    public static String c(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(AbstractC0566bn.S0);
            case 2:
                return resources.getString(AbstractC0566bn.Z0);
            case 3:
                return resources.getString(AbstractC0566bn.P0);
            case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
            case C0571bs.DATE_FIELD_NUMBER /* 6 */:
            case 18:
                return null;
            case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return f(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return f(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case 15:
            case 19:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return f(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return f(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String d(Context context, int i) {
        Resources resources = context.getResources();
        String strA = a(context);
        if (i == 1) {
            return resources.getString(AbstractC0566bn.R0, strA);
        }
        if (i == 2) {
            return AbstractC0203Lf.b(context) ? resources.getString(AbstractC0566bn.b1) : resources.getString(AbstractC0566bn.Y0, strA);
        }
        if (i == 3) {
            return resources.getString(AbstractC0566bn.O0, strA);
        }
        if (i == 5) {
            return e(context, "common_google_play_services_invalid_account_text", strA);
        }
        if (i == 7) {
            return e(context, "common_google_play_services_network_error_text", strA);
        }
        if (i == 9) {
            return resources.getString(AbstractC0566bn.W0, strA);
        }
        if (i == 20) {
            return e(context, "common_google_play_services_restricted_profile_text", strA);
        }
        switch (i) {
            case 16:
                return e(context, "common_google_play_services_api_unavailable_text", strA);
            case 17:
                return e(context, "common_google_play_services_sign_in_failed_text", strA);
            case 18:
                return resources.getString(AbstractC0566bn.a1, strA);
            default:
                return resources.getString(AbstractC0566bn.V0, strA);
        }
    }

    public static String e(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String strF = f(context, str);
        if (strF == null) {
            strF = resources.getString(AbstractC0566bn.V0);
        }
        return String.format(resources.getConfiguration().locale, strF, str2);
    }

    public static String f(Context context, String str) {
        C1633v1 c1633v1 = a;
        synchronized (c1633v1) {
            String str2 = (String) c1633v1.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources resourcesE = AbstractC1333pc.e(context);
            if (resourcesE == null) {
                return null;
            }
            int iB = AbstractC1077kw.b(resourcesE, str, "string", "com.google.android.gms");
            if (iB == 0) {
                String strValueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", strValueOf.length() != 0 ? "Missing resource: ".concat(strValueOf) : new String("Missing resource: "));
                return null;
            }
            String string = resourcesE.getString(iB);
            if (!TextUtils.isEmpty(string)) {
                c1633v1.put(str, string);
                return string;
            }
            String strValueOf2 = String.valueOf(str);
            Log.w("GoogleApiAvailability", strValueOf2.length() != 0 ? "Got empty resource: ".concat(strValueOf2) : new String("Got empty resource: "));
            return null;
        }
    }
}
