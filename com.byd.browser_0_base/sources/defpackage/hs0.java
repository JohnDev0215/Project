package defpackage;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import org.chromium.base.TraceEvent;

/* JADX INFO: compiled from: GeolocationTracker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hs0 {
    public static a a;

    public static Location a(Context context) {
        TraceEvent traceEventH0 = TraceEvent.H0("GeolocationTracker.getLastKnownLocation", null);
        try {
            if (!c(context, "android.permission.ACCESS_COARSE_LOCATION")) {
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return null;
            }
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            Location lastKnownLocation = locationManager.getLastKnownLocation("network");
            Location lastKnownLocation2 = c(context, "android.permission.ACCESS_FINE_LOCATION") ? locationManager.getLastKnownLocation("gps") : null;
            if (lastKnownLocation2 != null) {
                if (lastKnownLocation != null) {
                    if (lastKnownLocation.getTime() <= lastKnownLocation2.getTime()) {
                    }
                }
                lastKnownLocation = lastKnownLocation2;
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return lastKnownLocation;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    public static long b(Location location) {
        long jCurrentTimeMillis = System.currentTimeMillis() - location.getTime();
        if (jCurrentTimeMillis >= 0) {
            return jCurrentTimeMillis;
        }
        return Long.MAX_VALUE;
    }

    public static boolean c(Context context, String str) {
        return c6.a(Process.myPid(), Process.myUid(), context, str) == 0;
    }

    /* JADX INFO: compiled from: GeolocationTracker.java */
    public static class a implements LocationListener {
        public final LocationManager a;
        public final Handler b;
        public final RunnableC0058a c;
        public boolean d;

        /* JADX INFO: renamed from: hs0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: GeolocationTracker.java */
        public class RunnableC0058a implements Runnable {
            public RunnableC0058a() {
            }

            @Override // java.lang.Runnable
            public final void run() throws Exception {
                a aVar = a.this;
                try {
                    aVar.a.removeUpdates(aVar);
                } catch (Exception e) {
                    if (!aVar.d) {
                        throw e;
                    }
                }
                hs0.a = null;
            }
        }

        public a(LocationManager locationManager) {
            this.a = locationManager;
            Handler handler = new Handler();
            this.b = handler;
            RunnableC0058a runnableC0058a = new RunnableC0058a();
            this.c = runnableC0058a;
            handler.postDelayed(runnableC0058a, 60000L);
        }

        @Override // android.location.LocationListener
        public final void onLocationChanged(Location location) {
            this.b.removeCallbacks(this.c);
            hs0.a = null;
        }

        @Override // android.location.LocationListener
        public final void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public final void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }
}
