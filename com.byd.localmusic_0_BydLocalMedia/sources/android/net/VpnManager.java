package android.net;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.RemoteException;
import com.android.internal.R;
import com.android.internal.util.Preconditions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes2.dex */
public class VpnManager {
    public static final int TYPE_VPN_NONE = -1;
    public static final int TYPE_VPN_PLATFORM = 2;
    public static final int TYPE_VPN_SERVICE = 1;
    private final Context mContext;
    private final IConnectivityManager mService;

    @Retention(RetentionPolicy.SOURCE)
    public @interface VpnType {
    }

    private static Intent getIntentForConfirmation() {
        Intent intent = new Intent();
        intent.setComponent(ComponentName.unflattenFromString(Resources.getSystem().getString(R.string.config_platformVpnConfirmDialogComponent)));
        return intent;
    }

    public VpnManager(Context context, IConnectivityManager iConnectivityManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "missing Context");
        this.mService = (IConnectivityManager) Preconditions.checkNotNull(iConnectivityManager, "missing IConnectivityManager");
    }

    public Intent provisionVpnProfile(PlatformVpnProfile platformVpnProfile) {
        try {
            try {
                if (this.mService.provisionVpnProfile(platformVpnProfile.toVpnProfile(), this.mContext.getOpPackageName())) {
                    return null;
                }
                return getIntentForConfirmation();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } catch (IOException | GeneralSecurityException e2) {
            throw new IllegalArgumentException("Failed to serialize PlatformVpnProfile", e2);
        }
    }

    public void deleteProvisionedVpnProfile() {
        try {
            this.mService.deleteVpnProfile(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void startProvisionedVpnProfile() {
        try {
            this.mService.startVpnProfile(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void stopProvisionedVpnProfile() {
        try {
            this.mService.stopVpnProfile(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
