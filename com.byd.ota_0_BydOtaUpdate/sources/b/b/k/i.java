package b.b.k;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import java.util.Calendar;

/* JADX INFO: compiled from: TwilightManager.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static i f807a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f808b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocationManager f809c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f810d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f811a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f812b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f813c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f814d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f815e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f816f;
    }

    public i(Context context, LocationManager locationManager) {
        this.f808b = context;
        this.f809c = locationManager;
    }

    public static i a(Context context) {
        if (f807a == null) {
            Context applicationContext = context.getApplicationContext();
            f807a = new i(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f807a;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location locationC = b.f.f.b.b(this.f808b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = b.f.f.b.b(this.f808b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        try {
            if (this.f809c.isProviderEnabled(str)) {
                return this.f809c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f810d;
        if (e()) {
            return aVar.f811a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f811a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i2 = Calendar.getInstance().get(11);
        return i2 < 6 || i2 >= 22;
    }

    public final boolean e() {
        return this.f810d.f816f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j;
        a aVar = this.f810d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        h hVarB = h.b();
        hVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = hVarB.f804b;
        hVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = hVarB.f806d == 1;
        long j3 = hVarB.f805c;
        long j4 = hVarB.f804b;
        hVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = hVarB.f805c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f811a = z;
        aVar.f812b = j2;
        aVar.f813c = j3;
        aVar.f814d = j4;
        aVar.f815e = j5;
        aVar.f816f = j;
    }
}
