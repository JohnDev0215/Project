package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static j f211a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f212b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f213c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f214d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f216b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f217c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f218d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f219e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f220f;

        a() {
        }
    }

    j(Context context, LocationManager locationManager) {
        this.f212b = context;
        this.f213c = locationManager;
    }

    static j a(Context context) {
        if (f211a == null) {
            Context applicationContext = context.getApplicationContext();
            f211a = new j(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f211a;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = androidx.core.content.b.b(this.f212b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = androidx.core.content.b.b(this.f212b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.f213c.isProviderEnabled(str)) {
                return this.f213c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    private boolean e() {
        return this.f214d.f220f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j;
        a aVar = this.f214d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        i iVarB = i.b();
        iVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = iVarB.f208b;
        iVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = iVarB.f210d == 1;
        long j3 = iVarB.f209c;
        long j4 = iVarB.f208b;
        iVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = iVarB.f209c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f215a = z;
        aVar.f216b = j2;
        aVar.f217c = j3;
        aVar.f218d = j4;
        aVar.f219e = j5;
        aVar.f220f = j;
    }

    boolean d() {
        a aVar = this.f214d;
        if (e()) {
            return aVar.f215a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f215a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }
}
