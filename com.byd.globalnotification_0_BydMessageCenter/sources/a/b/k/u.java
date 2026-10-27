package a.b.k;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static u f67d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f68a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LocationManager f69b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f70c = new a();

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f71a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f72b;
    }

    public u(Context context, LocationManager locationManager) {
        this.f68a = context;
        this.f69b = locationManager;
    }

    public final Location a(String str) {
        try {
            if (this.f69b.isProviderEnabled(str)) {
                return this.f69b.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e2) {
            Log.d("TwilightManager", "Failed to get last known location", e2);
            return null;
        }
    }
}
