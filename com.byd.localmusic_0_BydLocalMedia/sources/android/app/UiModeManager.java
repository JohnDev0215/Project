package android.app;

import android.annotation.SystemApi;
import android.app.IUiModeManager;
import android.content.Context;
import android.os.Debug;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.LocalTime;

/* JADX INFO: loaded from: classes.dex */
public class UiModeManager {
    public static String ACTION_ENTER_CAR_MODE = "android.app.action.ENTER_CAR_MODE";

    @SystemApi
    public static final String ACTION_ENTER_CAR_MODE_PRIORITIZED = "android.app.action.ENTER_CAR_MODE_PRIORITIZED";
    public static String ACTION_ENTER_DESK_MODE = "android.app.action.ENTER_DESK_MODE";
    public static String ACTION_EXIT_CAR_MODE = "android.app.action.EXIT_CAR_MODE";

    @SystemApi
    public static final String ACTION_EXIT_CAR_MODE_PRIORITIZED = "android.app.action.EXIT_CAR_MODE_PRIORITIZED";
    public static String ACTION_EXIT_DESK_MODE = "android.app.action.EXIT_DESK_MODE";
    private static final int CALLER_NUM = 20;

    @SystemApi
    public static final int DEFAULT_PRIORITY = 0;
    public static final int DISABLE_CAR_MODE_ALL_PRIORITIES = 2;
    public static final int DISABLE_CAR_MODE_GO_HOME = 1;
    public static final int ENABLE_CAR_MODE_ALLOW_SLEEP = 2;
    public static final int ENABLE_CAR_MODE_GO_CAR_HOME = 1;

    @SystemApi
    public static final String EXTRA_CALLING_PACKAGE = "android.app.extra.CALLING_PACKAGE";

    @SystemApi
    public static final String EXTRA_PRIORITY = "android.app.extra.PRIORITY";
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_CUSTOM = 3;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_YES = 2;
    private static final String TAG = "UiModeManager";
    private Context mContext;
    private IUiModeManager mService;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisableCarMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnableCarMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NightMode {
    }

    UiModeManager() throws ServiceManager.ServiceNotFoundException {
        this(null);
    }

    UiModeManager(Context context) throws ServiceManager.ServiceNotFoundException {
        this.mService = IUiModeManager.Stub.asInterface(ServiceManager.getServiceOrThrow(Context.UI_MODE_SERVICE));
        this.mContext = context;
    }

    public void enableCarMode(int i) {
        enableCarMode(0, i);
    }

    @SystemApi
    public void enableCarMode(int i, int i2) {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                iUiModeManager.enableCarMode(i2, i, this.mContext == null ? null : this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void disableCarMode(int i) {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                iUiModeManager.disableCarModeByCallingPackage(i, this.mContext == null ? null : this.mContext.getOpPackageName());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public int getCurrentModeType() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager == null) {
            return 1;
        }
        try {
            return iUiModeManager.getCurrentModeType();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setNightMode(int i) {
        Log.e(TAG, "setNightMode callers:" + Debug.getCallers(20));
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                iUiModeManager.setNightMode(i);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public int getNightMode() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager == null) {
            return -1;
        }
        try {
            return iUiModeManager.getNightMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isUiModeLocked() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager == null) {
            return true;
        }
        try {
            return iUiModeManager.isUiModeLocked();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isNightModeLocked() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager == null) {
            return true;
        }
        try {
            return iUiModeManager.isNightModeLocked();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean setNightModeActivated(boolean z) {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager == null) {
            return false;
        }
        try {
            return iUiModeManager.setNightModeActivated(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public LocalTime getCustomNightModeStart() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                return LocalTime.ofNanoOfDay(iUiModeManager.getCustomNightModeStart() * 1000);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        return LocalTime.MIDNIGHT;
    }

    public void setCustomNightModeStart(LocalTime localTime) {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                iUiModeManager.setCustomNightModeStart(localTime.toNanoOfDay() / 1000);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public LocalTime getCustomNightModeEnd() {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                return LocalTime.ofNanoOfDay(iUiModeManager.getCustomNightModeEnd() * 1000);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
        return LocalTime.MIDNIGHT;
    }

    public void setCustomNightModeEnd(LocalTime localTime) {
        IUiModeManager iUiModeManager = this.mService;
        if (iUiModeManager != null) {
            try {
                iUiModeManager.setCustomNightModeEnd(localTime.toNanoOfDay() / 1000);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }
}
