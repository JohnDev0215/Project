package i;

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
    public static k f3180a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f3181b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LocationManager f3182c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f3183d = new a();

    /* JADX INFO: compiled from: TwilightManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3184a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f3185b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f3186c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public long f3187d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f3188e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public long f3189f;
    }

    public k(Context context, LocationManager locationManager) {
        this.f3181b = context;
        this.f3182c = locationManager;
    }

    public static k a(Context context) {
        if (f3180a == null) {
            Context applicationContext = context.getApplicationContext();
            f3180a = new k(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return f3180a;
    }

    @SuppressLint({"MissingPermission"})
    public final Location b() {
        Location locationC = b0.b.b(this.f3181b, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = b0.b.b(this.f3181b, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    public final Location c(String str) {
        try {
            if (this.f3182c.isProviderEnabled(str)) {
                return this.f3182c.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e3) {
            Log.d("TwilightManager", "Failed to get last known location", e3);
            return null;
        }
    }

    public boolean d() {
        a aVar = this.f3183d;
        if (e()) {
            return aVar.f3184a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f3184a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i3 = Calendar.getInstance().get(11);
        return i3 < 6 || i3 >= 22;
    }

    public final boolean e() {
        return this.f3183d.f3189f > System.currentTimeMillis();
    }

    public final void f(Location location) {
        long j3;
        a aVar = this.f3183d;
        long jCurrentTimeMillis = System.currentTimeMillis();
        j jVarB = j.b();
        jVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j4 = jVarB.f3177b;
        jVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z3 = jVarB.f3179d == 1;
        long j5 = jVarB.f3178c;
        long j6 = jVarB.f3177b;
        jVarB.a(86400000 + jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        long j7 = jVarB.f3178c;
        if (j5 == -1 || j6 == -1) {
            j3 = 43200000 + jCurrentTimeMillis;
        } else {
            j3 = (jCurrentTimeMillis > j6 ? 0 + j7 : jCurrentTimeMillis > j5 ? 0 + j6 : 0 + j5) + 60000;
        }
        aVar.f3184a = z3;
        aVar.f3185b = j4;
        aVar.f3186c = j5;
        aVar.f3187d = j6;
        aVar.f3188e = j7;
        aVar.f3189f = j3;
    }
}
