package android.os;

import android.annotation.SystemApi;
import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class PowerWhitelistManager {
    public static final int EVENT_MMS = 2;
    public static final int EVENT_SMS = 1;
    public static final int EVENT_UNSPECIFIED = 0;
    private final Context mContext;
    private final IDeviceIdleController mService;

    @Retention(RetentionPolicy.SOURCE)
    public @interface WhitelistEvent {
    }

    public PowerWhitelistManager(Context context) {
        this.mContext = context;
        this.mService = ((DeviceIdleManager) context.getSystemService(DeviceIdleManager.class)).getService();
    }

    public void addToWhitelist(String str) {
        addToWhitelist(Collections.singletonList(str));
    }

    public void addToWhitelist(List<String> list) {
        try {
            this.mService.addPowerSaveWhitelistApps(list);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int[] getWhitelistedAppIds(boolean z) {
        try {
            if (z) {
                return this.mService.getAppIdWhitelist();
            }
            return this.mService.getAppIdWhitelistExceptIdle();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isWhitelisted(String str, boolean z) {
        try {
            if (z) {
                return this.mService.isPowerSaveWhitelistApp(str);
            }
            return this.mService.isPowerSaveWhitelistExceptIdleApp(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void whitelistAppTemporarily(String str, long j) {
        try {
            this.mService.addPowerSaveTempWhitelistApp(str, j, this.mContext.getUserId(), "from:" + UserHandle.formatUid(Binder.getCallingUid()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public long whitelistAppTemporarilyForEvent(String str, int i, String str2) {
        try {
            if (i == 1) {
                return this.mService.addPowerSaveTempWhitelistAppForSms(str, this.mContext.getUserId(), str2);
            }
            if (i == 2) {
                return this.mService.addPowerSaveTempWhitelistAppForMms(str, this.mContext.getUserId(), str2);
            }
            return this.mService.whitelistAppTemporarily(str, this.mContext.getUserId(), str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
