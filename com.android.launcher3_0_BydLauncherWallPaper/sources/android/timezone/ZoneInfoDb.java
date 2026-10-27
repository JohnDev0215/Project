package android.timezone;

import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ZoneInfoDb {
    private static ZoneInfoDb sInstance;
    private static final Object sLock = new Object();
    private final libcore.timezone.ZoneInfoDb mDelegate;

    public static ZoneInfoDb getInstance() {
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new ZoneInfoDb(libcore.timezone.ZoneInfoDb.getInstance());
            }
        }
        return sInstance;
    }

    private ZoneInfoDb(libcore.timezone.ZoneInfoDb zoneInfoDb) {
        this.mDelegate = (libcore.timezone.ZoneInfoDb) Objects.requireNonNull(zoneInfoDb);
    }

    public String getVersion() {
        return this.mDelegate.getVersion();
    }
}
