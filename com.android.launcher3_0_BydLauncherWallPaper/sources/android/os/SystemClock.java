package android.os;

import android.app.IAlarmManager;
import android.location.ILocationManager;
import android.location.LocationTime;
import android.util.Slog;
import android.util.TimeUtils;
import dalvik.annotation.optimization.CriticalNative;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.ZoneOffset;

/* JADX INFO: loaded from: classes2.dex */
public final class SystemClock {
    private static final String TAG = "SystemClock";

    @CriticalNative
    public static native long currentThreadTimeMicro();

    @CriticalNative
    public static native long currentThreadTimeMillis();

    @CriticalNative
    public static native long currentTimeMicro();

    @CriticalNative
    public static native long elapsedRealtime();

    @CriticalNative
    public static native long elapsedRealtimeNanos();

    @CriticalNative
    public static native long uptimeMillis();

    private SystemClock() {
    }

    public static void sleep(long j) {
        long jUptimeMillis = uptimeMillis();
        boolean z = false;
        long jUptimeMillis2 = j;
        do {
            try {
                Thread.sleep(jUptimeMillis2);
            } catch (InterruptedException unused) {
                z = true;
            }
            jUptimeMillis2 = (jUptimeMillis + j) - uptimeMillis();
        } while (jUptimeMillis2 > 0);
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean setCurrentTimeMillis(long j) {
        IAlarmManager iAlarmManagerAsInterface = IAlarmManager.Stub.asInterface(ServiceManager.getService("alarm"));
        if (iAlarmManagerAsInterface == null) {
            Slog.e(TAG, "Unable to set RTC: mgr == null");
            return false;
        }
        try {
            return iAlarmManagerAsInterface.setTime(j);
        } catch (RemoteException e) {
            Slog.e(TAG, "Unable to set RTC", e);
            return false;
        } catch (SecurityException e2) {
            Slog.e(TAG, "Unable to set RTC", e2);
            return false;
        }
    }

    public static Clock uptimeClock() {
        return new SimpleClock(ZoneOffset.UTC) { // from class: android.os.SystemClock.1
            @Override // android.os.SimpleClock, java.time.Clock, java.time.InstantSource
            public long millis() {
                return SystemClock.uptimeMillis();
            }
        };
    }

    public static Clock elapsedRealtimeClock() {
        return new SimpleClock(ZoneOffset.UTC) { // from class: android.os.SystemClock.2
            @Override // android.os.SimpleClock, java.time.Clock, java.time.InstantSource
            public long millis() {
                return SystemClock.elapsedRealtime();
            }
        };
    }

    public static long currentNetworkTimeMillis() throws Throwable {
        IAlarmManager iAlarmManagerAsInterface = IAlarmManager.Stub.asInterface(ServiceManager.getService("alarm"));
        if (iAlarmManagerAsInterface != null) {
            try {
                return iAlarmManagerAsInterface.currentNetworkTimeMillis();
            } catch (ParcelableException e) {
                e.maybeRethrow(DateTimeException.class);
                throw new RuntimeException(e);
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }
        throw new RuntimeException(new DeadSystemException());
    }

    public static Clock currentNetworkTimeClock() {
        return new SimpleClock(ZoneOffset.UTC) { // from class: android.os.SystemClock.3
            @Override // android.os.SimpleClock, java.time.Clock, java.time.InstantSource
            public long millis() {
                return SystemClock.currentNetworkTimeMillis();
            }
        };
    }

    public static Clock currentGnssTimeClock() {
        return new SimpleClock(ZoneOffset.UTC) { // from class: android.os.SystemClock.4
            private final ILocationManager mMgr = ILocationManager.Stub.asInterface(ServiceManager.getService("location"));

            @Override // android.os.SimpleClock, java.time.Clock, java.time.InstantSource
            public long millis() {
                try {
                    LocationTime gnssTimeMillis = this.mMgr.getGnssTimeMillis();
                    if (gnssTimeMillis == null) {
                        throw new DateTimeException("Gnss based time is not available.");
                    }
                    return gnssTimeMillis.getTime() + ((SystemClock.elapsedRealtimeNanos() - gnssTimeMillis.getElapsedRealtimeNanos()) / TimeUtils.NANOS_PER_MS);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                    return 0L;
                }
            }
        };
    }
}
