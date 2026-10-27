package p;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo$MobileSubtype;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo$NetworkType;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public final class oq implements eo2 {
    public final p01 a;
    public final ConnectivityManager b;
    public final Context c;
    public final URL d;
    public final yt e;
    public final yt f;
    public final int g;

    public oq(Context context, yt ytVar, yt ytVar2) {
        g71 g71Var = new g71();
        zb0.c.m(g71Var);
        g71Var.d = true;
        this.a = new p01(20, g71Var);
        this.c = context;
        this.b = (ConnectivityManager) context.getSystemService("connectivity");
        this.d = b(bp.c);
        this.e = ytVar2;
        this.f = ytVar;
        this.g = 130000;
    }

    public static URL b(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(u4.x("Invalid url: ", str), e);
        }
    }

    public final ai a(ai aiVar) {
        int subtype;
        NetworkInfo activeNetworkInfo = this.b.getActiveNetworkInfo();
        gu0 gu0VarC = aiVar.c();
        int i = Build.VERSION.SDK_INT;
        Map map = (Map) gu0VarC.f;
        if (map == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map.put("sdk-version", String.valueOf(i));
        gu0VarC.a("model", Build.MODEL);
        gu0VarC.a("hardware", Build.HARDWARE);
        gu0VarC.a("device", Build.DEVICE);
        gu0VarC.a("product", Build.PRODUCT);
        gu0VarC.a("os-uild", Build.ID);
        gu0VarC.a("manufacturer", Build.MANUFACTURER);
        gu0VarC.a("fingerprint", Build.FINGERPRINT);
        Calendar.getInstance();
        long offset = TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
        Map map2 = (Map) gu0VarC.f;
        if (map2 == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map2.put("tz-offset", String.valueOf(offset));
        int iA = activeNetworkInfo == null ? NetworkConnectionInfo$NetworkType.NONE.a() : activeNetworkInfo.getType();
        Map map3 = (Map) gu0VarC.f;
        if (map3 == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map3.put("net-type", String.valueOf(iA));
        int i2 = -1;
        if (activeNetworkInfo == null) {
            subtype = NetworkConnectionInfo$MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.a();
        } else {
            subtype = activeNetworkInfo.getSubtype();
            if (subtype == -1) {
                subtype = NetworkConnectionInfo$MobileSubtype.COMBINED.a();
            } else if (((NetworkConnectionInfo$MobileSubtype) NetworkConnectionInfo$MobileSubtype.k.get(subtype)) == null) {
                subtype = 0;
            }
        }
        Map map4 = (Map) gu0VarC.f;
        if (map4 == null) {
            throw new IllegalStateException("Property \"autoMetadata\" has not been set");
        }
        map4.put("mobile-subtype", String.valueOf(subtype));
        gu0VarC.a("country", Locale.getDefault().getCountry());
        gu0VarC.a("locale", Locale.getDefault().getLanguage());
        Context context = this.c;
        gu0VarC.a("mcc_mnc", ((TelephonyManager) context.getSystemService("phone")).getSimOperator());
        try {
            i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            ln1.j("CctTransportBackend", "Unable to find version code for package", e);
        }
        gu0VarC.a("application_build", Integer.toString(i2));
        return gu0VarC.d();
    }
}
