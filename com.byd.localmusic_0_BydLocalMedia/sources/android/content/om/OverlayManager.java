package android.content.om;

import android.annotation.SystemApi;
import android.compat.Compatibility;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public class OverlayManager {
    private static final long THROW_SECURITY_EXCEPTIONS = 147340954;
    private final Context mContext;
    private final IOverlayManager mService;

    public OverlayManager(Context context, IOverlayManager iOverlayManager) {
        this.mContext = context;
        this.mService = iOverlayManager;
    }

    public OverlayManager(Context context) {
        this(context, IOverlayManager.Stub.asInterface(ServiceManager.getService("overlay")));
    }

    @SystemApi
    public void setEnabledExclusiveInCategory(String str, UserHandle userHandle) throws IllegalStateException, SecurityException {
        try {
            if (this.mService.setEnabledExclusiveInCategory(str, userHandle.getIdentifier())) {
            } else {
                throw new IllegalStateException("setEnabledExclusiveInCategory failed");
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SecurityException e2) {
            rethrowSecurityException(e2);
        }
    }

    @SystemApi
    public void setEnabled(String str, boolean z, UserHandle userHandle) throws IllegalStateException, SecurityException {
        try {
            if (this.mService.setEnabled(str, z, userHandle.getIdentifier())) {
            } else {
                throw new IllegalStateException("setEnabled failed");
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SecurityException e2) {
            rethrowSecurityException(e2);
        }
    }

    @SystemApi
    public OverlayInfo getOverlayInfo(String str, UserHandle userHandle) {
        try {
            return this.mService.getOverlayInfo(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<OverlayInfo> getOverlayInfosForTarget(String str, UserHandle userHandle) {
        try {
            return this.mService.getOverlayInfosForTarget(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void invalidateCachesForOverlay(String str, UserHandle userHandle) {
        try {
            this.mService.invalidateCachesForOverlay(str, userHandle.getIdentifier());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void rethrowSecurityException(SecurityException securityException) {
        if (!Compatibility.isChangeEnabled(THROW_SECURITY_EXCEPTIONS)) {
            throw new IllegalStateException(securityException);
        }
        throw securityException;
    }
}
