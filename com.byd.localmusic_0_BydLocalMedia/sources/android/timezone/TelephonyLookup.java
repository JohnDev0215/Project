package android.timezone;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class TelephonyLookup {
    private static TelephonyLookup sInstance;
    private static final Object sLock = new Object();
    private final libcore.timezone.TelephonyLookup mDelegate;

    public static TelephonyLookup getInstance() {
        TelephonyLookup telephonyLookup;
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new TelephonyLookup(libcore.timezone.TelephonyLookup.getInstance());
            }
            telephonyLookup = sInstance;
        }
        return telephonyLookup;
    }

    private TelephonyLookup(libcore.timezone.TelephonyLookup telephonyLookup) {
        this.mDelegate = (libcore.timezone.TelephonyLookup) Objects.requireNonNull(telephonyLookup);
    }

    public TelephonyNetworkFinder getTelephonyNetworkFinder() {
        libcore.timezone.TelephonyNetworkFinder telephonyNetworkFinder = this.mDelegate.getTelephonyNetworkFinder();
        if (telephonyNetworkFinder != null) {
            return new TelephonyNetworkFinder(telephonyNetworkFinder);
        }
        return null;
    }
}
