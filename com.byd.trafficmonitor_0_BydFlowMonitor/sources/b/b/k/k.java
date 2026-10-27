package b.b.k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static k f1471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1472b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocationManager f1473c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f1474d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1475a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f1476b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1477c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f1478d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f1479e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f1480f;
    }

    public k(Context context, LocationManager locationManager) {
        this.f1472b = context;
        this.f1473c = locationManager;
    }

    public static k a(Context context) {
        if (f1471a == null) {
            Context applicationContext = context.getApplicationContext();
            f1471a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f1471a;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location locationC = b.h.e.b.b(this.f1472b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = b.h.e.b.b(this.f1472b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        try {
            if (this.f1473c.isProviderEnabled(str)) {
                return this.f1473c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f1474d;
        if (e()) {
            return aVar.f1475a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f1475a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }

    public final boolean e() {
        return this.f1474d.f1480f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j;
        a aVar = this.f1474d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        j jVarB = j.b();
        jVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = jVarB.f1468b;
        jVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = jVarB.f1470d == 1;
        long j3 = jVarB.f1469c;
        long j4 = jVarB.f1468b;
        jVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = jVarB.f1469c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f1475a = z;
        aVar.f1476b = j2;
        aVar.f1477c = j3;
        aVar.f1478d = j4;
        aVar.f1479e = j5;
        aVar.f1480f = j;
    }
}
