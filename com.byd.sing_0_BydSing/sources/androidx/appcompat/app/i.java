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
    private static i f695a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f696b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final LocationManager f697c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f698d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f699a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f700b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f701c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f702d;
        long e;
        long f;

        a() {
        }
    }

    i(Context context, LocationManager locationManager) {
        this.f696b = context;
        this.f697c = locationManager;
    }

    static i a(Context context) {
        if (f695a == null) {
            Context applicationContext = context.getApplicationContext();
            f695a = new i(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f695a;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = androidx.core.content.b.b(this.f696b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = androidx.core.content.b.b(this.f696b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.f697c.isProviderEnabled(str)) {
                return this.f697c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    private boolean e() {
        return this.f698d.f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j;
        a aVar = this.f698d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        h hVarB = h.b();
        hVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = hVarB.f692b;
        hVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = hVarB.f694d == 1;
        long j3 = hVarB.f693c;
        long j4 = hVarB.f692b;
        hVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = hVarB.f693c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f699a = z;
        aVar.f700b = j2;
        aVar.f701c = j3;
        aVar.f702d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    boolean d() {
        a aVar = this.f698d;
        if (e()) {
            return aVar.f699a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f699a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
