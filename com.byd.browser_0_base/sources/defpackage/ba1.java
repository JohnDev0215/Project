package defpackage;

import android.content.Context;
import android.location.LocationManager;
import android.os.UserManager;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: LocationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class ba1 {
    public static ba1 a;

    public static ba1 a() {
        Object obj = ThreadUtils.a;
        if (a == null) {
            a = new ba1();
        }
        return a;
    }

    public static boolean b() {
        LocationManager locationManager;
        Context context = xz.a;
        return (((UserManager) context.getSystemService("user")).hasUserRestriction("no_share_location") || (locationManager = (LocationManager) context.getSystemService("location")) == null || !locationManager.isLocationEnabled()) ? false : true;
    }
}
