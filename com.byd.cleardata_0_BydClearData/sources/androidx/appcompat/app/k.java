package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static k f760a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f761b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f762c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f763d = new a();

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f764a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f765b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f766c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f767d;
        long e;
        long f;

        a() {
        }
    }

    k(Context context, LocationManager locationManager) {
        this.f761b = context;
        this.f762c = locationManager;
    }

    static k a(Context context) {
        if (f760a == null) {
            Context applicationContext = context.getApplicationContext();
            f760a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f760a;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = androidx.core.content.b.b(this.f761b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = androidx.core.content.b.b(this.f761b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.f762c.isProviderEnabled(str)) {
                return this.f762c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    private boolean e() {
        return this.f763d.f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j;
        a aVar = this.f763d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        j jVarB = j.b();
        jVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = jVarB.f757b;
        jVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = jVarB.f759d == 1;
        long j3 = jVarB.f758c;
        long j4 = jVarB.f757b;
        jVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = jVarB.f758c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f764a = z;
        aVar.f765b = j2;
        aVar.f766c = j3;
        aVar.f767d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    boolean d() {
        a aVar = this.f763d;
        if (e()) {
            return aVar.f764a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f764a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
