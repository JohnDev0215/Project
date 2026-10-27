package androidx.appcompat.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class g {
    private static g d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f152a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final LocationManager f153b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f154c = new a();

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f155a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f156b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f157c;
        long d;
        long e;
        long f;

        a() {
        }
    }

    @VisibleForTesting
    g(@NonNull Context context, @NonNull LocationManager locationManager) {
        this.f152a = context;
        this.f153b = locationManager;
    }

    static g a(@NonNull Context context) {
        if (d == null) {
            Context applicationContext = context.getApplicationContext();
            d = new g(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
        }
        return d;
    }

    @SuppressLint({"MissingPermission"})
    private Location b() {
        Location locationC = PermissionChecker.b(this.f152a, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? c("network") : null;
        Location locationC2 = PermissionChecker.b(this.f152a, "android.permission.ACCESS_FINE_LOCATION") == 0 ? c("gps") : null;
        return (locationC2 == null || locationC == null) ? locationC2 != null ? locationC2 : locationC : locationC2.getTime() > locationC.getTime() ? locationC2 : locationC;
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    private Location c(String str) {
        try {
            if (this.f153b.isProviderEnabled(str)) {
                return this.f153b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d("TwilightManager", "Failed to get last known location", e);
            return null;
        }
    }

    private boolean e() {
        return this.f154c.f > System.currentTimeMillis();
    }

    private void f(@NonNull Location location) {
        long j;
        a aVar = this.f154c;
        long jCurrentTimeMillis = System.currentTimeMillis();
        f fVarB = f.b();
        fVarB.a(jCurrentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        long j2 = fVarB.f149a;
        fVarB.a(jCurrentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = fVarB.f151c == 1;
        long j3 = fVarB.f150b;
        long j4 = fVarB.f149a;
        fVarB.a(jCurrentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j5 = fVarB.f150b;
        if (j3 == -1 || j4 == -1) {
            j = 43200000 + jCurrentTimeMillis;
        } else {
            j = (jCurrentTimeMillis > j4 ? 0 + j5 : jCurrentTimeMillis > j3 ? 0 + j4 : 0 + j3) + 60000;
        }
        aVar.f155a = z;
        aVar.f156b = j2;
        aVar.f157c = j3;
        aVar.d = j4;
        aVar.e = j5;
        aVar.f = j;
    }

    @VisibleForTesting
    static void setInstance(g gVar) {
        d = gVar;
    }

    boolean d() {
        a aVar = this.f154c;
        if (e()) {
            return aVar.f155a;
        }
        Location locationB = b();
        if (locationB != null) {
            f(locationB);
            return aVar.f155a;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }
}
