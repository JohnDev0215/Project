package b.b.a;

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
    public static k f606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f607b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocationManager f608c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f609d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f610a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f611b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f612c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f613d;
        public long e;
        public long f;
    }

    public k(Context context, LocationManager locationManager) {
        this.f607b = context;
        this.f608c = locationManager;
    }

    public static k a(Context context) {
        if (f606a == null) {
            Context applicationContext = context.getApplicationContext();
            f606a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f606a;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location locationC = b.g.b.b.b(this.f607b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = b.g.b.b.b(this.f607b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        try {
            if (this.f608c.isProviderEnabled(str)) {
                return this.f608c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f609d;
        if (e()) {
            return aVar.f610a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f610a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    public final boolean e() {
        return this.f609d.f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j;
        a aVar = this.f609d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        j jVarB = j.b();
        jVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = jVarB.f603b;
        jVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = jVarB.f605d == 1;
        long j3 = jVarB.f604c;
        long j4 = jVarB.f603b;
        jVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j5 = jVarB.f604c;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f610a = z;
        aVar.f611b = j2;
        aVar.f612c = j3;
        aVar.f613d = j4;
        aVar.e = j5;
        aVar.f = j;
    }
}
