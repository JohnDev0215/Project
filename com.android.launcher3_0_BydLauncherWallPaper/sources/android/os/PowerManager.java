package android.os;

import android.annotation.SystemApi;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.pm.PackageParser;
import android.media.AudioSystem;
import android.os.IThermalStatusListener;
import android.service.dreams.Sandman;
import android.sysprop.InitProperties;
import android.util.ArrayMap;
import android.util.Log;
import android.util.proto.ProtoOutputStream;
import com.android.internal.R;
import com.android.internal.location.GpsNetInitiatedHandler;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes2.dex */
public final class PowerManager {
    public static final int ACQUIRE_CAUSES_WAKEUP = 268435456;
    public static final String ACTION_DEVICE_IDLE_MODE_CHANGED = "android.os.action.DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_LIGHT_DEVICE_IDLE_MODE_CHANGED = "android.os.action.LIGHT_DEVICE_IDLE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";
    public static final String ACTION_POWER_SAVE_MODE_CHANGED_INTERNAL = "android.os.action.POWER_SAVE_MODE_CHANGED_INTERNAL";

    @Deprecated
    public static final String ACTION_POWER_SAVE_MODE_CHANGING = "android.os.action.POWER_SAVE_MODE_CHANGING";
    public static final String ACTION_POWER_SAVE_TEMP_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_TEMP_WHITELIST_CHANGED";
    public static final String ACTION_POWER_SAVE_WHITELIST_CHANGED = "android.os.action.POWER_SAVE_WHITELIST_CHANGED";
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DEFAULT = 2;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DEFAULT_VR = 7;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DIM = 3;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_DOZE = 4;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MAXIMUM = 1;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MAXIMUM_VR = 6;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MINIMUM = 0;
    public static final int BRIGHTNESS_CONSTRAINT_TYPE_MINIMUM_VR = 5;
    public static final int BRIGHTNESS_DEFAULT = -1;
    public static final int BRIGHTNESS_INVALID = -1;
    public static final float BRIGHTNESS_INVALID_FLOAT = Float.NaN;
    public static final float BRIGHTNESS_MAX = 1.0f;
    public static final float BRIGHTNESS_MIN = 0.0f;
    public static final int BRIGHTNESS_OFF = 0;
    public static final float BRIGHTNESS_OFF_FLOAT = -1.0f;
    public static final int BRIGHTNESS_ON = 255;
    private static final String CACHE_KEY_IS_INTERACTIVE_PROPERTY = "cache_key.is_interactive";
    private static final String CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY = "cache_key.is_power_save_mode";
    public static final int DOZE_WAKE_LOCK = 64;
    public static final int DRAW_WAKE_LOCK = 128;

    @Deprecated
    public static final String EXTRA_POWER_SAVE_MODE = "mode";

    @Deprecated
    public static final int FULL_WAKE_LOCK = 26;
    public static final int GO_TO_SLEEP_FLAG_NO_DOZE = 1;
    public static final int GO_TO_SLEEP_REASON_ACCESSIBILITY = 7;
    public static final int GO_TO_SLEEP_REASON_APPLICATION = 0;
    public static final int GO_TO_SLEEP_REASON_DEVICE_ADMIN = 1;
    public static final int GO_TO_SLEEP_REASON_FORCE_SUSPEND = 8;
    public static final int GO_TO_SLEEP_REASON_HDMI = 5;
    public static final int GO_TO_SLEEP_REASON_INATTENTIVE = 9;
    public static final int GO_TO_SLEEP_REASON_LID_SWITCH = 3;
    public static final int GO_TO_SLEEP_REASON_MAX = 10;
    public static final int GO_TO_SLEEP_REASON_MIN = 0;
    public static final int GO_TO_SLEEP_REASON_POWER_BUTTON = 4;
    public static final int GO_TO_SLEEP_REASON_QUIESCENT = 10;
    public static final int GO_TO_SLEEP_REASON_SLEEP_BUTTON = 6;
    public static final int GO_TO_SLEEP_REASON_TIMEOUT = 2;
    public static final int LOCATION_MODE_ALL_DISABLED_WHEN_SCREEN_OFF = 2;
    public static final int LOCATION_MODE_FOREGROUND_ONLY = 3;
    public static final int LOCATION_MODE_GPS_DISABLED_WHEN_SCREEN_OFF = 1;
    public static final int LOCATION_MODE_NO_CHANGE = 0;
    public static final int LOCATION_MODE_THROTTLE_REQUESTS_WHEN_SCREEN_OFF = 4;
    private static final int MAX_CACHE_ENTRIES = 1;
    public static final int MAX_LOCATION_MODE = 4;
    private static final int MINIMUM_HEADROOM_TIME_MILLIS = 500;
    public static final int MIN_LOCATION_MODE = 0;
    public static final int ON_AFTER_RELEASE = 536870912;
    public static final int PARTIAL_WAKE_LOCK = 1;

    @SystemApi
    public static final int POWER_SAVE_MODE_TRIGGER_DYNAMIC = 1;

    @SystemApi
    public static final int POWER_SAVE_MODE_TRIGGER_PERCENTAGE = 0;
    public static final int PRE_IDLE_TIMEOUT_MODE_LONG = 1;
    public static final int PRE_IDLE_TIMEOUT_MODE_NORMAL = 0;
    public static final int PRE_IDLE_TIMEOUT_MODE_SHORT = 2;
    public static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    public static final String REBOOT_QUIESCENT = "quiescent";
    public static final String REBOOT_RECOVERY = "recovery";
    public static final String REBOOT_RECOVERY_UPDATE = "recovery-update";
    public static final String REBOOT_REQUESTED_BY_DEVICE_OWNER = "deviceowner";
    public static final String REBOOT_SAFE_MODE = "safemode";

    @SystemApi
    public static final String REBOOT_USERSPACE = "userspace";
    public static final int RELEASE_FLAG_TIMEOUT = 65536;
    public static final int RELEASE_FLAG_WAIT_FOR_NO_PROXIMITY = 1;

    @Deprecated
    public static final int SCREEN_BRIGHT_WAKE_LOCK = 10;

    @Deprecated
    public static final int SCREEN_DIM_WAKE_LOCK = 6;
    public static final String SHUTDOWN_BATTERY_THERMAL_STATE = "thermal,battery";
    public static final String SHUTDOWN_LOW_BATTERY = "battery";
    public static final int SHUTDOWN_REASON_BATTERY_THERMAL = 6;
    public static final int SHUTDOWN_REASON_LOW_BATTERY = 5;
    public static final int SHUTDOWN_REASON_REBOOT = 2;
    public static final int SHUTDOWN_REASON_SHUTDOWN = 1;
    public static final int SHUTDOWN_REASON_THERMAL_SHUTDOWN = 4;
    public static final int SHUTDOWN_REASON_UNKNOWN = 0;
    public static final int SHUTDOWN_REASON_USER_REQUESTED = 3;
    public static final String SHUTDOWN_THERMAL_STATE = "thermal";
    public static final String SHUTDOWN_USER_REQUESTED = "userrequested";
    private static final String TAG = "PowerManager";
    public static final int THERMAL_STATUS_CRITICAL = 4;
    public static final int THERMAL_STATUS_EMERGENCY = 5;
    public static final int THERMAL_STATUS_LIGHT = 1;
    public static final int THERMAL_STATUS_MODERATE = 2;
    public static final int THERMAL_STATUS_NONE = 0;
    public static final int THERMAL_STATUS_SEVERE = 3;
    public static final int THERMAL_STATUS_SHUTDOWN = 6;
    public static final int UNIMPORTANT_FOR_LOGGING = 1073741824;

    @SystemApi
    public static final int USER_ACTIVITY_EVENT_ACCESSIBILITY = 3;
    public static final int USER_ACTIVITY_EVENT_ATTENTION = 4;

    @SystemApi
    public static final int USER_ACTIVITY_EVENT_BUTTON = 1;

    @SystemApi
    public static final int USER_ACTIVITY_EVENT_OTHER = 0;

    @SystemApi
    public static final int USER_ACTIVITY_EVENT_TOUCH = 2;

    @SystemApi
    public static final int USER_ACTIVITY_FLAG_INDIRECT = 2;

    @SystemApi
    public static final int USER_ACTIVITY_FLAG_NO_CHANGE_LIGHTS = 1;
    public static final int WAKE_LOCK_LEVEL_MASK = 65535;
    public static final int WAKE_REASON_APPLICATION = 2;
    public static final int WAKE_REASON_CAMERA_LAUNCH = 5;
    public static final int WAKE_REASON_GESTURE = 4;
    public static final int WAKE_REASON_HDMI = 8;
    public static final int WAKE_REASON_LID = 9;
    public static final int WAKE_REASON_PLUGGED_IN = 3;
    public static final int WAKE_REASON_POWER_BUTTON = 1;
    public static final int WAKE_REASON_UNKNOWN = 0;
    public static final int WAKE_REASON_WAKE_KEY = 6;
    public static final int WAKE_REASON_WAKE_MOTION = 7;
    final Context mContext;
    final Handler mHandler;
    private PropertyInvalidatedCache<Void, Boolean> mInteractiveCache;
    private PropertyInvalidatedCache<Void, Boolean> mPowerSaveModeCache;
    private PowerWhitelistManager mPowerWhitelistManager;
    final IPowerManager mService;
    final IThermalService mThermalService;
    private final ArrayMap<OnThermalStatusChangedListener, IThermalStatusListener> mListenerMap = new ArrayMap<>();
    private final AtomicLong mLastHeadroomUpdate = new AtomicLong(0);

    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoPowerSaveModeTriggers {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface BrightnessConstraint {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface LocationPowerSaveMode {
    }

    public interface OnThermalStatusChangedListener {
        void onThermalStatusChanged(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceType {
        public static final int ANIMATION = 3;
        public static final int AOD = 14;
        public static final int BATTERY_STATS = 9;
        public static final int DATA_SAVER = 10;
        public static final int FORCE_ALL_APPS_STANDBY = 11;
        public static final int FORCE_BACKGROUND_CHECK = 12;
        public static final int FULL_BACKUP = 4;
        public static final int KEYVALUE_BACKUP = 5;
        public static final int LOCATION = 1;
        public static final int NETWORK_FIREWALL = 6;
        public static final int NIGHT_MODE = 16;
        public static final int NULL = 0;
        public static final int OPTIONAL_SENSORS = 13;
        public static final int QUICK_DOZE = 15;
        public static final int SCREEN_BRIGHTNESS = 7;
        public static final int SOUND = 8;
        public static final int VIBRATION = 2;
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShutdownReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ThermalStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WakeReason {
    }

    public static String sleepReasonToString(int i) {
        switch (i) {
            case 0:
                return PackageParser.TAG_APPLICATION;
            case 1:
                return "device_admin";
            case 2:
                return GpsNetInitiatedHandler.NI_INTENT_KEY_TIMEOUT;
            case 3:
                return "lid_switch";
            case 4:
                return "power_button";
            case 5:
                return AudioSystem.DEVICE_OUT_HDMI_NAME;
            case 6:
                return "sleep_button";
            case 7:
                return Context.ACCESSIBILITY_SERVICE;
            case 8:
                return "force_suspend";
            case 9:
                return "inattentive";
            default:
                return Integer.toString(i);
        }
    }

    public static String wakeReasonToString(int i) {
        switch (i) {
            case 0:
                return "WAKE_REASON_UNKNOWN";
            case 1:
                return "WAKE_REASON_POWER_BUTTON";
            case 2:
                return "WAKE_REASON_APPLICATION";
            case 3:
                return "WAKE_REASON_PLUGGED_IN";
            case 4:
                return "WAKE_REASON_GESTURE";
            case 5:
                return "WAKE_REASON_CAMERA_LAUNCH";
            case 6:
                return "WAKE_REASON_WAKE_KEY";
            case 7:
                return "WAKE_REASON_WAKE_MOTION";
            case 8:
                return "WAKE_REASON_HDMI";
            case 9:
                return "WAKE_REASON_LID";
            default:
                return Integer.toString(i);
        }
    }

    public static class WakeData {
        public int wakeReason;
        public long wakeTime;

        public WakeData(long j, int i) {
            this.wakeTime = j;
            this.wakeReason = i;
        }
    }

    public static String locationPowerSaveModeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? Integer.toString(i) : "THROTTLE_REQUESTS_WHEN_SCREEN_OFF" : "FOREGROUND_ONLY" : "ALL_DISABLED_WHEN_SCREEN_OFF" : "GPS_DISABLED_WHEN_SCREEN_OFF" : "NO_CHANGE";
    }

    public PowerManager(Context context, IPowerManager iPowerManager, IThermalService iThermalService, Handler handler) {
        int i = 1;
        this.mPowerSaveModeCache = new PropertyInvalidatedCache<Void, Boolean>(i, CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY) { // from class: android.os.PowerManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Boolean recompute(Void r1) {
                try {
                    return Boolean.valueOf(PowerManager.this.mService.isPowerSaveMode());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        };
        this.mInteractiveCache = new PropertyInvalidatedCache<Void, Boolean>(i, CACHE_KEY_IS_INTERACTIVE_PROPERTY) { // from class: android.os.PowerManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Boolean recompute(Void r1) {
                try {
                    return Boolean.valueOf(PowerManager.this.mService.isInteractive());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        };
        this.mContext = context;
        this.mService = iPowerManager;
        this.mThermalService = iThermalService;
        this.mHandler = handler;
    }

    private PowerWhitelistManager getPowerWhitelistManager() {
        if (this.mPowerWhitelistManager == null) {
            this.mPowerWhitelistManager = (PowerWhitelistManager) this.mContext.getSystemService(PowerWhitelistManager.class);
        }
        return this.mPowerWhitelistManager;
    }

    public int getMinimumScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingMinimum);
    }

    public int getMaximumScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingMaximum);
    }

    public int getDefaultScreenBrightnessSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessSettingDefault);
    }

    public int getMinimumScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingMinimum);
    }

    public int getMaximumScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingMaximum);
    }

    public int getDefaultScreenBrightnessForVrSetting() {
        return this.mContext.getResources().getInteger(R.integer.config_screenBrightnessForVrSettingDefault);
    }

    public float getBrightnessConstraint(int i) {
        try {
            return this.mService.getBrightnessConstraint(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public WakeLock newWakeLock(int i, String str) {
        validateWakeLockParameters(i, str);
        return new WakeLock(i, str, this.mContext.getOpPackageName());
    }

    public static void validateWakeLockParameters(int i, String str) {
        int i2 = i & 65535;
        if (i2 != 1 && i2 != 6 && i2 != 10 && i2 != 26 && i2 != 32 && i2 != 64 && i2 != 128) {
            throw new IllegalArgumentException("Must specify a valid wake lock level.");
        }
        if (str == null) {
            throw new IllegalArgumentException("The tag must not be null.");
        }
    }

    @Deprecated
    public void userActivity(long j, boolean z) {
        userActivity(j, 0, z ? 1 : 0);
    }

    @SystemApi
    public void userActivity(long j, int i, int i2) {
        try {
            this.mService.userActivity(j, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void goToSleep(long j) {
        goToSleep(j, 0, 0);
    }

    public void goToSleep(long j, int i, int i2) {
        try {
            this.mService.goToSleep(j, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void wakeUp(long j) {
        wakeUp(j, 0, "wakeUp");
    }

    @Deprecated
    public void wakeUp(long j, String str) {
        wakeUp(j, 0, str);
    }

    public void wakeUp(long j, int i, String str) {
        try {
            this.mService.wakeUp(j, i, str, this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void nap(long j) {
        try {
            this.mService.nap(j);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void dream(long j) {
        Sandman.startDreamByUserRequest(this.mContext);
    }

    public void boostScreenBrightness(long j) {
        try {
            this.mService.boostScreenBrightness(j);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isWakeLockLevelSupported(int i) {
        try {
            return this.mService.isWakeLockLevelSupported(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public boolean isScreenOn() {
        return isInteractive();
    }

    public boolean isInteractive() {
        return this.mInteractiveCache.query(null).booleanValue();
    }

    public static boolean isRebootingUserspaceSupportedImpl() {
        return InitProperties.is_userspace_reboot_supported().orElse(false).booleanValue();
    }

    public boolean isRebootingUserspaceSupported() {
        return isRebootingUserspaceSupportedImpl();
    }

    public void reboot(String str) {
        if (REBOOT_USERSPACE.equals(str) && !isRebootingUserspaceSupported()) {
            throw new UnsupportedOperationException("Attempted userspace reboot on a device that doesn't support it");
        }
        try {
            this.mService.reboot(false, str, true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void rebootSafeMode() {
        try {
            this.mService.rebootSafeMode(false, true);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isPowerSaveMode() {
        return this.mPowerSaveModeCache.query(null).booleanValue();
    }

    @SystemApi
    public boolean setPowerSaveModeEnabled(boolean z) {
        try {
            return this.mService.setPowerSaveModeEnabled(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setDynamicPowerSaveHint(boolean z, int i) {
        try {
            return this.mService.setDynamicPowerSaveHint(z, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setAdaptivePowerSavePolicy(BatterySaverPolicyConfig batterySaverPolicyConfig) {
        try {
            return this.mService.setAdaptivePowerSavePolicy(batterySaverPolicyConfig);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean setAdaptivePowerSaveEnabled(boolean z) {
        try {
            return this.mService.setAdaptivePowerSaveEnabled(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int getPowerSaveModeTrigger() {
        try {
            return this.mService.getPowerSaveModeTrigger();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public PowerSaveState getPowerSaveState(int i) {
        try {
            return this.mService.getPowerSaveState(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLocationPowerSaveMode() {
        PowerSaveState powerSaveState = getPowerSaveState(1);
        if (powerSaveState.batterySaverEnabled) {
            return powerSaveState.locationMode;
        }
        return 0;
    }

    public boolean isDeviceIdleMode() {
        try {
            return this.mService.isDeviceIdleMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isLightDeviceIdleMode() {
        try {
            return this.mService.isLightDeviceIdleMode();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isIgnoringBatteryOptimizations(String str) {
        return getPowerWhitelistManager().isWhitelisted(str, true);
    }

    public void shutdown(boolean z, String str, boolean z2) {
        try {
            this.mService.shutdown(z, str, z2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isSustainedPerformanceModeSupported() {
        return this.mContext.getResources().getBoolean(R.bool.config_sustainedPerformanceModeSupported);
    }

    public int getCurrentThermalStatus() {
        try {
            return this.mThermalService.getCurrentThermalStatus();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addThermalStatusListener(OnThermalStatusChangedListener onThermalStatusChangedListener) {
        Preconditions.checkNotNull(onThermalStatusChangedListener, "listener cannot be null");
        addThermalStatusListener(this.mContext.getMainExecutor(), onThermalStatusChangedListener);
    }

    public void addThermalStatusListener(Executor executor, OnThermalStatusChangedListener onThermalStatusChangedListener) {
        Preconditions.checkNotNull(onThermalStatusChangedListener, "listener cannot be null");
        Preconditions.checkNotNull(executor, "executor cannot be null");
        Preconditions.checkArgument(!this.mListenerMap.containsKey(onThermalStatusChangedListener), "Listener already registered: " + onThermalStatusChangedListener);
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(executor, onThermalStatusChangedListener);
        try {
            if (this.mThermalService.registerThermalStatusListener(anonymousClass3)) {
                this.mListenerMap.put(onThermalStatusChangedListener, anonymousClass3);
                return;
            }
            throw new RuntimeException("Listener failed to set");
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: renamed from: android.os.PowerManager$3, reason: invalid class name */
    class AnonymousClass3 extends IThermalStatusListener.Stub {
        final /* synthetic */ Executor val$executor;
        final /* synthetic */ OnThermalStatusChangedListener val$listener;

        AnonymousClass3(Executor executor, OnThermalStatusChangedListener onThermalStatusChangedListener) {
            this.val$executor = executor;
            this.val$listener = onThermalStatusChangedListener;
        }

        @Override // android.os.IThermalStatusListener
        public void onStatusChange(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final OnThermalStatusChangedListener onThermalStatusChangedListener = this.val$listener;
                executor.execute(new Runnable() { // from class: android.os.-$$Lambda$PowerManager$3$8snH8Z_XjRb6l2wzhedRFyMgjXo
                    @Override // java.lang.Runnable
                    public final void run() {
                        onThermalStatusChangedListener.onThermalStatusChanged(i);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public void removeThermalStatusListener(OnThermalStatusChangedListener onThermalStatusChangedListener) {
        Preconditions.checkNotNull(onThermalStatusChangedListener, "listener cannot be null");
        IThermalStatusListener iThermalStatusListener = this.mListenerMap.get(onThermalStatusChangedListener);
        Preconditions.checkArgument(iThermalStatusListener != null, "Listener was not added");
        try {
            if (this.mThermalService.unregisterThermalStatusListener(iThermalStatusListener)) {
                this.mListenerMap.remove(onThermalStatusChangedListener);
                return;
            }
            throw new RuntimeException("Listener failed to remove");
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public float getThermalHeadroom(int i) {
        if (SystemClock.elapsedRealtime() - this.mLastHeadroomUpdate.get() < 500) {
            return Float.NaN;
        }
        try {
            float thermalHeadroom = this.mThermalService.getThermalHeadroom(i);
            this.mLastHeadroomUpdate.set(SystemClock.elapsedRealtime());
            return thermalHeadroom;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setDozeAfterScreenOff(boolean z) {
        try {
            this.mService.setDozeAfterScreenOff(z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplayAvailable() {
        try {
            return this.mService.isAmbientDisplayAvailable();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void suppressAmbientDisplay(String str, boolean z) {
        try {
            this.mService.suppressAmbientDisplay(str, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplaySuppressedForToken(String str) {
        try {
            return this.mService.isAmbientDisplaySuppressedForToken(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean isAmbientDisplaySuppressed() {
        try {
            return this.mService.isAmbientDisplaySuppressed();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isAmbientDisplaySuppressedForTokenByApp(String str, int i) {
        try {
            return this.mService.isAmbientDisplaySuppressedForTokenByApp(str, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLastShutdownReason() {
        try {
            return this.mService.getLastShutdownReason();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int getLastSleepReason() {
        try {
            return this.mService.getLastSleepReason();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public boolean forceSuspend() {
        try {
            return this.mService.forceSuspend();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public final class WakeLock {
        private int mExternalCount;
        private int mFlags;
        private boolean mHeld;
        private String mHistoryTag;
        private int mInternalCount;
        private final String mPackageName;
        private String mTag;
        private final String mTraceName;
        private WorkSource mWorkSource;
        private boolean mRefCounted = true;
        private final Runnable mReleaser = new Runnable() { // from class: android.os.PowerManager.WakeLock.1
            @Override // java.lang.Runnable
            public void run() {
                WakeLock.this.release(65536);
            }
        };
        private final IBinder mToken = new Binder();

        WakeLock(int i, String str, String str2) {
            this.mFlags = i;
            this.mTag = str;
            this.mPackageName = str2;
            this.mTraceName = "WakeLock (" + this.mTag + ")";
        }

        protected void finalize() throws Throwable {
            synchronized (this.mToken) {
                if (this.mHeld) {
                    Log.wtf(PowerManager.TAG, "WakeLock finalized while still held: " + this.mTag);
                    Trace.asyncTraceEnd(131072L, this.mTraceName, 0);
                    try {
                        PowerManager.this.mService.releaseWakeLock(this.mToken, 0);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }

        public void setReferenceCounted(boolean z) {
            synchronized (this.mToken) {
                this.mRefCounted = z;
            }
        }

        public void acquire() {
            synchronized (this.mToken) {
                acquireLocked();
            }
        }

        public void acquire(long j) {
            synchronized (this.mToken) {
                acquireLocked();
                PowerManager.this.mHandler.postDelayed(this.mReleaser, j);
            }
        }

        private void acquireLocked() {
            int i = this.mInternalCount + 1;
            this.mInternalCount = i;
            this.mExternalCount++;
            if (!this.mRefCounted || i == 1) {
                PowerManager.this.mHandler.removeCallbacks(this.mReleaser);
                Trace.asyncTraceBegin(131072L, this.mTraceName, 0);
                try {
                    PowerManager.this.mService.acquireWakeLock(this.mToken, this.mFlags, this.mTag, this.mPackageName, this.mWorkSource, this.mHistoryTag);
                    this.mHeld = true;
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }

        public void release() {
            release(0);
        }

        public void release(int i) {
            synchronized (this.mToken) {
                int i2 = this.mInternalCount;
                if (i2 > 0) {
                    this.mInternalCount = i2 - 1;
                }
                if ((65536 & i) == 0) {
                    this.mExternalCount--;
                }
                if (!this.mRefCounted || this.mInternalCount == 0) {
                    PowerManager.this.mHandler.removeCallbacks(this.mReleaser);
                    if (this.mHeld) {
                        Trace.asyncTraceEnd(131072L, this.mTraceName, 0);
                        try {
                            PowerManager.this.mService.releaseWakeLock(this.mToken, i);
                            this.mHeld = false;
                        } catch (RemoteException e) {
                            throw e.rethrowFromSystemServer();
                        }
                    }
                }
                if (this.mRefCounted && this.mExternalCount < 0) {
                    throw new RuntimeException("WakeLock under-locked " + this.mTag);
                }
            }
        }

        public boolean isHeld() {
            boolean z;
            synchronized (this.mToken) {
                z = this.mHeld;
            }
            return z;
        }

        public void setWorkSource(WorkSource workSource) {
            synchronized (this.mToken) {
                if (workSource != null) {
                    try {
                        if (workSource.isEmpty()) {
                            workSource = null;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                boolean zEquals = true;
                if (workSource == null) {
                    if (this.mWorkSource == null) {
                        zEquals = false;
                    }
                    this.mWorkSource = null;
                } else {
                    WorkSource workSource2 = this.mWorkSource;
                    if (workSource2 == null) {
                        this.mWorkSource = new WorkSource(workSource);
                    } else {
                        zEquals = true ^ workSource2.equals(workSource);
                        if (zEquals) {
                            this.mWorkSource.set(workSource);
                        }
                    }
                }
                if (zEquals && this.mHeld) {
                    try {
                        PowerManager.this.mService.updateWakeLockWorkSource(this.mToken, this.mWorkSource, this.mHistoryTag);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }

        public void setTag(String str) {
            this.mTag = str;
        }

        public String getTag() {
            return this.mTag;
        }

        public void setHistoryTag(String str) {
            this.mHistoryTag = str;
        }

        public void setUnimportantForLogging(boolean z) {
            if (z) {
                this.mFlags |= 1073741824;
            } else {
                this.mFlags &= -1073741825;
            }
        }

        public String toString() {
            String str;
            synchronized (this.mToken) {
                str = "WakeLock{" + Integer.toHexString(System.identityHashCode(this)) + " held=" + this.mHeld + ", refCount=" + this.mInternalCount + "}";
            }
            return str;
        }

        public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
            synchronized (this.mToken) {
                long jStart = protoOutputStream.start(j);
                protoOutputStream.write(1138166333441L, this.mTag);
                protoOutputStream.write(1138166333442L, this.mPackageName);
                protoOutputStream.write(1133871366147L, this.mHeld);
                protoOutputStream.write(1120986464260L, this.mInternalCount);
                WorkSource workSource = this.mWorkSource;
                if (workSource != null) {
                    workSource.dumpDebug(protoOutputStream, 1146756268037L);
                }
                protoOutputStream.end(jStart);
            }
        }

        public Runnable wrap(final Runnable runnable) {
            acquire();
            return new Runnable() { // from class: android.os.-$$Lambda$PowerManager$WakeLock$O7_jN0XOhx9ykogC-rDQkEJhJqU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$wrap$0$PowerManager$WakeLock(runnable);
                }
            };
        }

        public /* synthetic */ void lambda$wrap$0$PowerManager$WakeLock(Runnable runnable) {
            try {
                runnable.run();
            } finally {
                release();
            }
        }
    }

    public static void invalidatePowerSaveModeCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_POWER_SAVE_MODE_PROPERTY);
    }

    public static void invalidateIsInteractiveCaches() {
        PropertyInvalidatedCache.invalidateCache(CACHE_KEY_IS_INTERACTIVE_PROPERTY);
    }
}
