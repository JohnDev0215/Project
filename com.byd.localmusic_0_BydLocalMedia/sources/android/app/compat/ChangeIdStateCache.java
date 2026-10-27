package android.app.compat;

import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.internal.compat.IPlatformCompat;

/* JADX INFO: loaded from: classes.dex */
public final class ChangeIdStateCache extends PropertyInvalidatedCache<ChangeIdStateQuery, Boolean> {
    private static final String CACHE_KEY = "cache_key.is_compat_change_enabled";
    private static final int MAX_ENTRIES = 20;
    private static boolean sDisabled = false;

    public ChangeIdStateCache() {
        super(20, CACHE_KEY);
    }

    public static void disable() {
        sDisabled = true;
    }

    public static void invalidate() {
        if (sDisabled) {
            return;
        }
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.PropertyInvalidatedCache
    public Boolean recompute(ChangeIdStateQuery changeIdStateQuery) {
        IPlatformCompat iPlatformCompatAsInterface = IPlatformCompat.Stub.asInterface(ServiceManager.getService(Context.PLATFORM_COMPAT_SERVICE));
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                if (changeIdStateQuery.type == 0) {
                    return Boolean.valueOf(iPlatformCompatAsInterface.isChangeEnabledByPackageName(changeIdStateQuery.changeId, changeIdStateQuery.packageName, changeIdStateQuery.userId));
                }
                if (changeIdStateQuery.type == 1) {
                    return Boolean.valueOf(iPlatformCompatAsInterface.isChangeEnabledByUid(changeIdStateQuery.changeId, changeIdStateQuery.uid));
                }
                throw new IllegalArgumentException("Invalid query type: " + changeIdStateQuery.type);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                throw new IllegalStateException("Could not recompute value!");
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
