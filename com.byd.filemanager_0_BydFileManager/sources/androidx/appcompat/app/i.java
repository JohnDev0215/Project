package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static i f178a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f179b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f181d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f182a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f183b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f184c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f185d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f186e;
        long f;

        a() {
        }
    }

    i(Context context, LocationManager locationManager) {
        this.f179b = context;
        this.f180c = locationManager;
    }

    static i a(Context context) {
        if (f178a == null) {
            Context applicationContext = context.getApplicationContext();
            f178a = new i(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f178a;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = androidx.core.content.b.b(this.f179b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = androidx.core.content.b.b(this.f179b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.f180c.isProviderEnabled(str)) {
                return this.f180c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    private boolean e() {
        return this.f181d.f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j;
        a aVar = this.f181d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        h hVarB = h.b();
        hVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = hVarB.f175b;
        hVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = hVarB.f177d == 1;
        long j3 = hVarB.f176c;
        long j4 = hVarB.f175b;
        hVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = hVarB.f176c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f182a = z;
        aVar.f183b = j2;
        aVar.f184c = j3;
        aVar.f185d = j4;
        aVar.f186e = j5;
        aVar.f = j;
    }

    boolean d() {
        a aVar = this.f181d;
        if (e()) {
            return aVar.f182a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f182a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
