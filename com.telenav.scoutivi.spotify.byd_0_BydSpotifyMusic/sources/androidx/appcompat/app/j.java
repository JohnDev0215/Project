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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static j f529d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LocationManager f531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f532c = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f533a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f534b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f535c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        long f536d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        long f537e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        long f538f;

        a() {
        }
    }

    j(Context context, LocationManager locationManager) {
        this.f530a = context;
        this.f531b = locationManager;
    }

    static j a(Context context) {
        if (f529d == null) {
            Context applicationContext = context.getApplicationContext();
            f529d = new j(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f529d;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = x.b.b(this.f530a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = x.b.b(this.f530a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    private Location c(String str) {
        try {
            if (this.f531b.isProviderEnabled(str)) {
                return this.f531b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e7) {
            Log.d("TwilightManager", "Failed to get last known location", e7);
            return null;
        }
    }

    private boolean e() {
        return this.f532c.f538f > System.currentTimeMillis();
    }

    private void f(Location location) {
        long j7;
        a aVar = this.f532c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        i iVarB = i.b();
        iVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j8 = iVarB.f526a;
        iVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z6 = iVarB.f528c == 1;
        long j9 = iVarB.f527b;
        long j10 = iVarB.f526a;
        iVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j11 = iVarB.f527b;
        if (j9 == -1 || j10 == -1) {
            j7 = 43200000 + jCurrentTimeMillis;
        } else {
            j7 = (jCurrentTimeMillis > j10 ? 0 + j11 : jCurrentTimeMillis > j9 ? 0 + j10 : 0 + j9) + 60000;
        }
        aVar.f533a = z6;
        aVar.f534b = j8;
        aVar.f535c = j9;
        aVar.f536d = j10;
        aVar.f537e = j11;
        aVar.f538f = j7;
    }

    boolean d() {
        a aVar = this.f532c;
        if (e()) {
            return aVar.f533a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f533a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i7 = Calendar.getInstance().get(11);
        return i7 < 6 || i7 >= 22;
    }
}
