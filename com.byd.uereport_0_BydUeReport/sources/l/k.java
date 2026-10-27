package l;

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
    public static k f5974a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f5975b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocationManager f5976c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f5977d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5978a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f5979b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f5980c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f5981d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f5982e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f5983f;
    }

    public k(Context context, LocationManager locationManager) {
        this.f5975b = context;
        this.f5976c = locationManager;
    }

    public static k a(Context context) {
        if (f5974a == null) {
            Context applicationContext = context.getApplicationContext();
            f5974a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f5974a;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location locationC = e0.b.b(this.f5975b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = e0.b.b(this.f5975b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        try {
            if (this.f5976c.isProviderEnabled(str)) {
                return this.f5976c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e8) {
            Log.d("TwilightManager", "Failed to get last known location", e8);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f5977d;
        if (e()) {
            return aVar.f5978a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f5978a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i8 = Calendar.getInstance().get(11);
        return i8 < 6 || i8 >= 22;
    }

    public final boolean e() {
        return this.f5977d.f5983f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j8;
        a aVar = this.f5977d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        j jVarB = j.b();
        jVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j9 = jVarB.f5971b;
        jVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z7 = jVarB.f5973d == 1;
        long j10 = jVarB.f5972c;
        long j11 = jVarB.f5971b;
        jVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j12 = jVarB.f5972c;
        if (j10 == -1 || j11 == -1) {
            j8 = 43200000 + jCurrentTimeMillis;
        } else {
            j8 = (jCurrentTimeMillis > j11 ? 0 + j12 : jCurrentTimeMillis > j10 ? 0 + j11 : 0 + j10) + 60000;
        }
        aVar.f5978a = z7;
        aVar.f5979b = j9;
        aVar.f5980c = j10;
        aVar.f5981d = j11;
        aVar.f5982e = j12;
        aVar.f5983f = j8;
    }
}
