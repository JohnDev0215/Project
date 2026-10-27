package android.app;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.statusbar.IStatusBarService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class StatusBarManager {
    public static final int CAMERA_LAUNCH_SOURCE_LIFT_TRIGGER = 2;
    public static final int CAMERA_LAUNCH_SOURCE_POWER_DOUBLE_TAP = 1;
    public static final int CAMERA_LAUNCH_SOURCE_WIGGLE = 0;
    public static final int DEFAULT_SETUP_DISABLE2_FLAGS = 16;
    public static final int DEFAULT_SETUP_DISABLE_FLAGS = 61145088;
    private static final int DEFAULT_SIM_LOCKED_DISABLED_FLAGS = 65536;
    public static final int DISABLE2_GLOBAL_ACTIONS = 8;
    public static final int DISABLE2_MASK = 31;
    public static final int DISABLE2_NONE = 0;
    public static final int DISABLE2_NOTIFICATION_SHADE = 4;
    public static final int DISABLE2_QUICK_SETTINGS = 1;
    public static final int DISABLE2_ROTATE_SUGGESTIONS = 16;
    public static final int DISABLE2_SYSTEM_ICONS = 2;
    public static final int DISABLE_BACK = 4194304;
    public static final int DISABLE_CLOCK = 8388608;
    public static final int DISABLE_EXPAND = 65536;
    public static final int DISABLE_HOME = 2097152;
    public static final int DISABLE_MASK = 67043328;

    @Deprecated
    public static final int DISABLE_NAVIGATION = 18874368;
    public static final int DISABLE_NONE = 0;
    public static final int DISABLE_NOTIFICATION_ALERTS = 262144;
    public static final int DISABLE_NOTIFICATION_ICONS = 131072;

    @Deprecated
    public static final int DISABLE_NOTIFICATION_TICKER = 524288;
    public static final int DISABLE_RECENT = 16777216;
    public static final int DISABLE_SEARCH = 33554432;
    public static final int DISABLE_SYSTEM_INFO = 1048576;
    public static final int NAVIGATION_HINT_BACK_ALT = 1;
    public static final int NAVIGATION_HINT_IME_SHOWN = 2;
    public static final int WINDOW_NAVIGATION_BAR = 2;
    public static final int WINDOW_STATE_HIDDEN = 2;
    public static final int WINDOW_STATE_HIDING = 1;
    public static final int WINDOW_STATE_SHOWING = 0;
    public static final int WINDOW_STATUS_BAR = 1;
    private Context mContext;
    private IStatusBarService mService;
    private IBinder mToken = new Binder();

    @Retention(RetentionPolicy.SOURCE)
    public @interface Disable2Flags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisableFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WindowType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WindowVisibleState {
    }

    public static String windowStateToString(int i) {
        return i == 1 ? "WINDOW_STATE_HIDING" : i == 2 ? "WINDOW_STATE_HIDDEN" : i == 0 ? "WINDOW_STATE_SHOWING" : "WINDOW_STATE_UNKNOWN";
    }

    StatusBarManager(Context context) {
        this.mContext = context;
    }

    private synchronized IStatusBarService getService() {
        if (this.mService == null) {
            IStatusBarService iStatusBarServiceAsInterface = IStatusBarService.Stub.asInterface(ServiceManager.getService(Context.STATUS_BAR_SERVICE));
            this.mService = iStatusBarServiceAsInterface;
            if (iStatusBarServiceAsInterface == null) {
                Slog.w("StatusBarManager", "warning: no STATUS_BAR_SERVICE");
            }
        }
        return this.mService;
    }

    public void disable(int i) {
        try {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService service = getService();
            if (service != null) {
                service.disableForUser(i, this.mToken, this.mContext.getPackageName(), identifier);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void disable2(int i) {
        try {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService service = getService();
            if (service != null) {
                service.disable2ForUser(i, this.mToken, this.mContext.getPackageName(), identifier);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void expandNotificationsPanel() {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.expandNotificationsPanel();
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void collapsePanels() {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.collapsePanels();
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void expandSettingsPanel() {
        expandSettingsPanel(null);
    }

    public void expandSettingsPanel(String str) {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.expandSettingsPanel(str);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setIcon(String str, int i, int i2, String str2) {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.setIcon(str, this.mContext.getPackageName(), i, i2, str2);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void removeIcon(String str) {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.removeIcon(str);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setIconVisibility(String str, boolean z) {
        try {
            IStatusBarService service = getService();
            if (service != null) {
                service.setIconVisibility(str, z);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setDisabledForSetup(boolean z) {
        try {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService service = getService();
            if (service != null) {
                service.disableForUser(z ? DEFAULT_SETUP_DISABLE_FLAGS : 0, this.mToken, this.mContext.getPackageName(), identifier);
                service.disable2ForUser(z ? 16 : 0, this.mToken, this.mContext.getPackageName(), identifier);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setDisabledForSimNetworkLock(boolean z) {
        try {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService service = getService();
            if (service != null) {
                service.disableForUser(z ? 65536 : 0, this.mToken, this.mContext.getPackageName(), identifier);
            }
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public DisableInfo getDisableInfo() {
        try {
            int identifier = Binder.getCallingUserHandle().getIdentifier();
            IStatusBarService service = getService();
            int[] disableFlags = {0, 0};
            if (service != null) {
                disableFlags = service.getDisableFlags(this.mToken, identifier);
            }
            return new DisableInfo(disableFlags[0], disableFlags[1]);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public static final class DisableInfo {
        private boolean mClock;
        private boolean mNavigateHome;
        private boolean mNotificationIcons;
        private boolean mNotificationPeeking;
        private boolean mRecents;
        private boolean mSearch;
        private boolean mStatusBarExpansion;
        private boolean mSystemIcons;

        public DisableInfo(int i, int i2) {
            this.mStatusBarExpansion = (65536 & i) != 0;
            this.mNavigateHome = (2097152 & i) != 0;
            this.mNotificationPeeking = (262144 & i) != 0;
            this.mRecents = (16777216 & i) != 0;
            this.mSearch = (33554432 & i) != 0;
            this.mSystemIcons = (1048576 & i) != 0;
            this.mClock = (8388608 & i) != 0;
            this.mNotificationIcons = (i & 131072) != 0;
        }

        public DisableInfo() {
        }

        @SystemApi
        public boolean isStatusBarExpansionDisabled() {
            return this.mStatusBarExpansion;
        }

        public void setStatusBarExpansionDisabled(boolean z) {
            this.mStatusBarExpansion = z;
        }

        @SystemApi
        public boolean isNavigateToHomeDisabled() {
            return this.mNavigateHome;
        }

        public void setNagivationHomeDisabled(boolean z) {
            this.mNavigateHome = z;
        }

        @SystemApi
        public boolean isNotificationPeekingDisabled() {
            return this.mNotificationPeeking;
        }

        public void setNotificationPeekingDisabled(boolean z) {
            this.mNotificationPeeking = z;
        }

        @SystemApi
        public boolean isRecentsDisabled() {
            return this.mRecents;
        }

        public void setRecentsDisabled(boolean z) {
            this.mRecents = z;
        }

        @SystemApi
        public boolean isSearchDisabled() {
            return this.mSearch;
        }

        public void setSearchDisabled(boolean z) {
            this.mSearch = z;
        }

        public boolean areSystemIconsDisabled() {
            return this.mSystemIcons;
        }

        public void setSystemIconsDisabled(boolean z) {
            this.mSystemIcons = z;
        }

        public boolean isClockDisabled() {
            return this.mClock;
        }

        public void setClockDisabled(boolean z) {
            this.mClock = z;
        }

        public boolean areNotificationIconsDisabled() {
            return this.mNotificationIcons;
        }

        public void setNotificationIconsDisabled(boolean z) {
            this.mNotificationIcons = z;
        }

        @SystemApi
        public boolean areAllComponentsEnabled() {
            return (this.mStatusBarExpansion || this.mNavigateHome || this.mNotificationPeeking || this.mRecents || this.mSearch || this.mSystemIcons || this.mClock || this.mNotificationIcons) ? false : true;
        }

        public void setEnableAll() {
            this.mStatusBarExpansion = false;
            this.mNavigateHome = false;
            this.mNotificationPeeking = false;
            this.mRecents = false;
            this.mSearch = false;
            this.mSystemIcons = false;
            this.mClock = false;
            this.mNotificationIcons = false;
        }

        public boolean areAllComponentsDisabled() {
            return this.mStatusBarExpansion && this.mNavigateHome && this.mNotificationPeeking && this.mRecents && this.mSearch && this.mSystemIcons && this.mClock && this.mNotificationIcons;
        }

        public void setDisableAll() {
            this.mStatusBarExpansion = true;
            this.mNavigateHome = true;
            this.mNotificationPeeking = true;
            this.mRecents = true;
            this.mSearch = true;
            this.mSystemIcons = true;
            this.mClock = true;
            this.mNotificationIcons = true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DisableInfo: ");
            sb.append(" mStatusBarExpansion=");
            sb.append(this.mStatusBarExpansion ? "disabled" : "enabled");
            sb.append(" mNavigateHome=");
            sb.append(this.mNavigateHome ? "disabled" : "enabled");
            sb.append(" mNotificationPeeking=");
            sb.append(this.mNotificationPeeking ? "disabled" : "enabled");
            sb.append(" mRecents=");
            sb.append(this.mRecents ? "disabled" : "enabled");
            sb.append(" mSearch=");
            sb.append(this.mSearch ? "disabled" : "enabled");
            sb.append(" mSystemIcons=");
            sb.append(this.mSystemIcons ? "disabled" : "enabled");
            sb.append(" mClock=");
            sb.append(this.mClock ? "disabled" : "enabled");
            sb.append(" mNotificationIcons=");
            sb.append(this.mNotificationIcons ? "disabled" : "enabled");
            return sb.toString();
        }

        public Pair<Integer, Integer> toFlags() {
            int i = this.mStatusBarExpansion ? 65536 : 0;
            if (this.mNavigateHome) {
                i |= 2097152;
            }
            if (this.mNotificationPeeking) {
                i |= 262144;
            }
            if (this.mRecents) {
                i |= 16777216;
            }
            if (this.mSearch) {
                i |= 33554432;
            }
            if (this.mSystemIcons) {
                i |= 1048576;
            }
            if (this.mClock) {
                i |= 8388608;
            }
            if (this.mNotificationIcons) {
                i |= 131072;
            }
            return new Pair<>(Integer.valueOf(i), 0);
        }
    }
}
