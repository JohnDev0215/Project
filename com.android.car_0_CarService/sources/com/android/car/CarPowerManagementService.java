package com.android.car;

import android.app.ActivityManager;
import android.car.hardware.power.ICarPower;
import android.car.hardware.power.ICarPowerStateListener;
import android.car.userlib.HalCallback;
import android.car.userlib.InitialUserSetter;
import android.car.userlib.UserHalHelper;
import android.car.userlib.UserHelper;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponse;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.sysprop.CarProperties;
import android.util.AtomicFile;
import android.util.Slog;
import com.android.car.Manifest;
import com.android.car.am.ContinuousBlankActivity;
import com.android.car.hal.PowerHalService;
import com.android.car.systeminterface.SystemInterface;
import com.android.car.user.CarUserNoticeService;
import com.android.car.user.CarUserService;
import com.android.internal.app.IVoiceInteractionManagerService;
import com.android.wifitrackerlib.WifiEntry;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class CarPowerManagementService extends ICarPower.Stub implements CarServiceBase, PowerHalService.PowerEventListener {
    private static final String ACTION_PRINT_ALARM = "action_print_all_alarm_batches_log";
    private static final long INITIAL_SUSPEND_RETRY_INTERVAL_MS = 10;
    private static final long MAX_RETRY_INTERVAL_MS = 100;
    private static final long MAX_SUSPEND_WAIT_DURATION_MS = 180000;
    private static final int MIN_MAX_GARAGE_MODE_DURATION_MS = 5000;
    private static final long MIN_SUSPEND_WAIT_DURATION_MS = 0;
    private static final int MSG_LOG_ALARMS = 102;
    private static final int MSG_LOG_WAIT_APP = 101;
    private static final String PROP_MAX_GARAGE_MODE_DURATION_OVERRIDE = "android.car.garagemodeduration";
    private static final int SHUTDOWN_EXTEND_MAX_MS = 5000;
    private static final int SHUTDOWN_POLLING_INTERVAL_MS = 2000;
    private static final String TAG = "M_STR_CAR.POWER";
    public static final String TAG_STR = "M_STR_";
    private static final int TIME_LOG_WAIT_APP = 10000;
    private static final String WIFI_STATE_FILENAME = "wifi_state";
    private static final String WIFI_STATE_MODIFIED = "forcibly_disabled";
    private static final String WIFI_STATE_ORIGINAL = "original";
    AppListerRemoveBroadcast mAppListerRemoveBroadcast;
    private final Context mContext;
    private CpmsState mCurrentState;
    DataLogHandler mDataLogHandler;
    private final boolean mDisableUserSwitchDuringResume;
    private boolean mGarageModeShouldExitImmediately;
    private final PowerHalService mHal;
    private final PowerHandler mHandler;
    private final HandlerThread mHandlerThread;
    private boolean mInSimulatedDeepSleepMode;
    private final InitialUserSetter mInitialUserSetter;
    private boolean mIsBooting;
    private boolean mIsResuming;
    private long mLastSleepEntryTime;
    private final Set<IBinder> mListenersWeAreWaitingFor;
    private final Object mLock;
    private final long mMaxSuspendWaitDurationMs;
    private int mNextWakeupSec;
    private final LinkedList<CpmsState> mPendingPowerStates;
    Map<String, Integer> mPidMap;
    private final PowerManagerCallbackList mPowerManagerListeners;
    private final PowerManagerCallbackList mPowerManagerListenersWithCompletion;
    private long mProcessingStartTime;
    private boolean mRebootAfterGarageMode;
    private boolean mShutdownOnFinish;
    private boolean mShutdownOnNextSuspend;
    private int mShutdownPollingIntervalMs;
    private int mShutdownPrepareTimeMs;
    private final Object mSimulationWaitObject;
    private final boolean mSwitchGuestUserBeforeSleep;
    private final SystemInterface mSystemInterface;
    private Timer mTimer;
    private boolean mTimerActive;
    private final UserManager mUserManager;
    private final CarUserService mUserService;
    private final IVoiceInteractionManagerService mVoiceInteractionManagerService;
    private boolean mWakeFromSimulatedSleep;
    private final WifiManager mWifiManager;
    private final AtomicFile mWifiStateFile;

    private class PowerManagerCallbackList extends RemoteCallbackList<ICarPowerStateListener> {
        private PowerManagerCallbackList() {
        }

        @Override // android.os.RemoteCallbackList
        public void onCallbackDied(ICarPowerStateListener listener) {
            Slog.i(CarPowerManagementService.TAG, "binderDied " + listener.asBinder());
            CarPowerManagementService.this.doUnregisterListener(listener);
        }
    }

    public CarPowerManagementService(Context context, PowerHalService powerHal, SystemInterface systemInterface, final CarUserService carUserService) {
        this(context, context.getResources(), powerHal, systemInterface, UserManager.get(context), carUserService, new InitialUserSetter(context, new Consumer() { // from class: com.android.car.-$$Lambda$CarPowerManagementService$qpkcrKgKp1dVZI5pdMHNOqMxwcM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                carUserService.setInitialUser((UserInfo) obj);
            }
        }, context.getString(R.string.default_guest_name)), IVoiceInteractionManagerService.Stub.asInterface(ServiceManager.getService("voiceinteraction")));
    }

    public CarPowerManagementService(Context context, Resources resources, PowerHalService powerHal, SystemInterface systemInterface, UserManager userManager, CarUserService carUserService, InitialUserSetter initialUserSetter, IVoiceInteractionManagerService voiceInteractionService) {
        this.mLock = new Object();
        this.mSimulationWaitObject = new Object();
        this.mPowerManagerListeners = new PowerManagerCallbackList();
        this.mPowerManagerListenersWithCompletion = new PowerManagerCallbackList();
        this.mListenersWeAreWaitingFor = new HashSet();
        this.mPendingPowerStates = new LinkedList<>();
        this.mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
        this.mHandler = new PowerHandler(this.mHandlerThread.getLooper(), this);
        this.mIsBooting = true;
        this.mShutdownPrepareTimeMs = WifiEntry.FREQUENCY_5_GHZ;
        this.mShutdownPollingIntervalMs = SHUTDOWN_POLLING_INTERVAL_MS;
        this.mPidMap = new HashMap();
        this.mDataLogHandler = new DataLogHandler(this.mHandlerThread.getLooper());
        this.mContext = context;
        this.mHal = powerHal;
        this.mSystemInterface = systemInterface;
        this.mUserManager = userManager;
        this.mDisableUserSwitchDuringResume = resources.getBoolean(R.bool.config_disableUserSwitchDuringResume);
        this.mSwitchGuestUserBeforeSleep = resources.getBoolean(R.bool.config_switchGuestUserBeforeGoingSleep);
        if (this.mShutdownPrepareTimeMs < 5000) {
            Slog.w(TAG, "maxGarageModeRunningDurationInSecs smaller than minimum required, resource:" + this.mShutdownPrepareTimeMs + "(ms) while should exceed:" + WifiEntry.FREQUENCY_5_GHZ + "(ms), Ignore resource.");
            this.mShutdownPrepareTimeMs = WifiEntry.FREQUENCY_5_GHZ;
        }
        Slog.w(TAG, "mShutdownPrepareTimeMs= " + this.mShutdownPrepareTimeMs);
        this.mUserService = carUserService;
        this.mInitialUserSetter = initialUserSetter;
        this.mVoiceInteractionManagerService = voiceInteractionService;
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        this.mWifiStateFile = new AtomicFile(new File(this.mSystemInterface.getSystemCarDir(), WIFI_STATE_FILENAME));
        this.mMaxSuspendWaitDurationMs = Math.max(0L, Math.min(getMaxSuspendWaitDurationConfig(), MAX_SUSPEND_WAIT_DURATION_MS));
    }

    public void setShutdownTimersForTest(int pollingIntervalMs, int shutdownTimeoutMs) {
        synchronized (this.mLock) {
            this.mShutdownPollingIntervalMs = pollingIntervalMs == 0 ? SHUTDOWN_POLLING_INTERVAL_MS : pollingIntervalMs;
            this.mShutdownPrepareTimeMs = shutdownTimeoutMs == 0 ? WifiEntry.FREQUENCY_5_GHZ : shutdownTimeoutMs;
        }
    }

    protected HandlerThread getHandlerThread() {
        return this.mHandlerThread;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        this.mHal.setListener(this);
        if (this.mHal.isPowerStateSupported()) {
            onApPowerStateChange(0, 1);
        } else {
            Slog.w(TAG, "Vehicle hal does not support power state yet.");
            onApPowerStateChange(1, 6);
        }
        this.mSystemInterface.startDisplayStateMonitoring(this);
        registerAppListerBroadcast();
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        Slog.w(TAG, "CPMS release.");
        synchronized (this.mLock) {
            releaseTimerLocked();
            this.mCurrentState = null;
            this.mHandler.cancelAll();
            this.mListenersWeAreWaitingFor.clear();
        }
        this.mSystemInterface.stopDisplayStateMonitoring();
        this.mPowerManagerListeners.kill();
        this.mSystemInterface.releaseAllWakeLocks();
        unregisterAppListerBroadcast();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        synchronized (this.mLock) {
            writer.println("*PowerManagementService*");
            writer.print("mCurrentState:" + this.mCurrentState);
            writer.print(",mProcessingStartTime:" + this.mProcessingStartTime);
            writer.print(",mLastSleepEntryTime:" + this.mLastSleepEntryTime);
            writer.print(",mNextWakeupSec:" + this.mNextWakeupSec);
            writer.print(",mShutdownOnNextSuspend:" + this.mShutdownOnNextSuspend);
            writer.print(",mShutdownOnFinish:" + this.mShutdownOnFinish);
            writer.print(",mShutdownPollingIntervalMs:" + this.mShutdownPollingIntervalMs);
            writer.print(",mShutdownPrepareTimeMs:" + this.mShutdownPrepareTimeMs);
            writer.print(",mDisableUserSwitchDuringResume:" + this.mDisableUserSwitchDuringResume);
            writer.println(",mRebootAfterGarageMode:" + this.mRebootAfterGarageMode);
            writer.println("mSwitchGuestUserBeforeSleep:" + this.mSwitchGuestUserBeforeSleep);
            writer.print("mMaxSuspendWaitDurationMs:" + this.mMaxSuspendWaitDurationMs);
            writer.println(", config_maxSuspendWaitDuration:" + getMaxSuspendWaitDurationConfig());
        }
        this.mInitialUserSetter.dump(writer);
    }

    @Override // com.android.car.hal.PowerHalService.PowerEventListener
    public void onApPowerStateChange(PowerHalService.PowerState state) {
        Slog.w(TAG, " onApPowerStateChange PowerState= " + state);
        synchronized (this.mLock) {
            this.mPendingPowerStates.addFirst(new CpmsState(state));
            this.mLock.notify();
        }
        this.mHandler.handlePowerStateChange();
    }

    void setStateForTesting(boolean isBooting, boolean isResuming) {
        synchronized (this.mLock) {
            Slog.d(TAG, "setStateForTesting(): booting(" + this.mIsBooting + ">" + isBooting + ") resuming(" + this.mIsResuming + ">" + isResuming + ")");
            this.mIsBooting = isBooting;
            this.mIsResuming = isResuming;
        }
    }

    private void onApPowerStateChange(int apState, int carPowerStateListenerState) {
        Slog.w(TAG, "onApPowerStateChange apState= " + apState + " , carPowerStateListenerState= " + carPowerStateListenerState);
        CpmsState newState = new CpmsState(apState, carPowerStateListenerState);
        synchronized (this.mLock) {
            this.mPendingPowerStates.addFirst(newState);
            this.mLock.notify();
        }
        this.mHandler.handlePowerStateChange();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandlePowerStateChange() {
        synchronized (this.mLock) {
            CpmsState state = this.mPendingPowerStates.peekFirst();
            this.mPendingPowerStates.clear();
            if (state == null) {
                Slog.e(TAG, "Null power state was requested");
                return;
            }
            Slog.i(TAG, "doHandlePowerStateChange: newState=" + state.name());
            if (needPowerStateChangeLocked(state)) {
                releaseTimerLocked();
                this.mHandler.cancelProcessingComplete();
                Slog.i(TAG, "doHandlePowerStateChange setCurrentState mLock start " + state.toString());
                CarStatsLogHelper.logPowerState(state.mState);
                this.mCurrentState = state;
                Slog.w(TAG, "doHandlePowerStateChange setCurrentState mLock end " + state.toString());
                int i = state.mState;
                if (i == 0) {
                    handleWaitForVhal(state);
                    return;
                }
                if (i == 1) {
                    handleOn();
                    return;
                }
                if (i == 2) {
                    handleShutdownPrepare(state);
                    return;
                }
                if (i == 3) {
                    handleWaitForFinish(state);
                } else if (i == 4) {
                    handleFinish();
                } else if (i == 5) {
                    simulateShutdownPrepare();
                }
            }
        }
    }

    private void handleWaitForVhal(CpmsState state) {
        Slog.w(TAG, " handleWaitForVhal " + state);
        int carPowerStateListenerState = state.mCarPowerStateListenerState;
        sendPowerManagerEvent(carPowerStateListenerState);
        if (carPowerStateListenerState == 1) {
            Slog.w(TAG, " handleWaitForVhal with WAIT_FOR_VHAL ");
            this.mHal.sendWaitForVhal();
        } else if (carPowerStateListenerState == 3) {
            Slog.w(TAG, " handleWaitForVhal with SUSPEND_EXIT ");
            this.mHal.sendSleepExit();
        } else if (carPowerStateListenerState == 8) {
            Slog.w(TAG, " handleWaitForVhal with SHUTDOWN_CANCELLED ");
            this.mShutdownOnNextSuspend = false;
            this.mHal.sendShutdownCancel();
        }
        restoreWifi();
    }

    private void updateCarUserNoticeServiceIfNecessary() {
        try {
            int currentUserId = ActivityManager.getCurrentUser();
            UserInfo currentUserInfo = this.mUserManager.getUserInfo(currentUserId);
            CarUserNoticeService carUserNoticeService = (CarUserNoticeService) CarLocalServices.getService(CarUserNoticeService.class);
            if (currentUserInfo != null && currentUserInfo.isGuest() && carUserNoticeService != null) {
                Slog.i(TAG, "Car user notice service will ignore all messages before user switch.");
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(this.mContext.getPackageName(), ContinuousBlankActivity.class.getName()));
                intent.addFlags(268435456);
                this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
                carUserNoticeService.ignoreUserNotice(currentUserId);
            }
        } catch (Exception e) {
            Slog.w(TAG, "Cannot ignore user notice for current user", e);
        }
    }

    private void handleOn() {
        boolean allowUserSwitch;
        Slog.w(TAG, " handleOn ");
        if (!this.mSwitchGuestUserBeforeSleep) {
            updateCarUserNoticeServiceIfNecessary();
        }
        synchronized (this.mLock) {
            if (this.mIsBooting) {
                allowUserSwitch = false;
                this.mIsBooting = false;
                this.mIsResuming = false;
                Slog.i(TAG, "User switch disallowed while booting");
            } else {
                allowUserSwitch = !this.mDisableUserSwitchDuringResume;
                this.mIsBooting = false;
                this.mIsResuming = false;
                if (!allowUserSwitch) {
                    Slog.i(TAG, "User switch disallowed while resuming");
                }
            }
        }
        this.mSystemInterface.setDisplayState(true);
        sendPowerManagerEvent(6);
        Slog.w(TAG, " handleOn with sendOn");
        this.mHal.sendOn();
        try {
            switchUserOnResumeIfNecessary(allowUserSwitch);
        } catch (Exception e) {
            Slog.e(TAG, "Could not switch user on resume", e);
        }
        setVoiceInteractionDisabled(false);
    }

    void switchUserOnResumeIfNecessary(boolean allowSwitching) {
        Slog.d(TAG, "switchUserOnResumeIfNecessary(): allowSwitching=" + allowSwitching + ", mSwitchGuestUserBeforeSleep=" + this.mSwitchGuestUserBeforeSleep);
        if (!allowSwitching) {
            if (this.mSwitchGuestUserBeforeSleep) {
                return;
            }
            switchToNewGuestIfNecessary();
        } else if (((Boolean) CarProperties.user_hal_enabled().orElse(false)).booleanValue() && this.mUserService.isUserHalSupported()) {
            switchUserOnResumeIfNecessaryUsingHal();
        } else {
            executeDefaultInitialUserBehavior(!this.mSwitchGuestUserBeforeSleep);
        }
    }

    private void executeDefaultInitialUserBehavior(boolean replaceGuest) {
        this.mInitialUserSetter.set(newInitialUserInfoBuilder(0).setReplaceGuest(replaceGuest).build());
    }

    private void switchToNewGuestIfNecessary() {
        int currentUserId = ActivityManager.getCurrentUser();
        UserInfo currentUser = this.mUserManager.getUserInfo(currentUserId);
        if (this.mInitialUserSetter.canReplaceGuestUser(currentUser)) {
            this.mInitialUserSetter.set(newInitialUserInfoBuilder(3).build());
        }
    }

    private void switchUser(int userId, boolean replaceGuest) {
        this.mInitialUserSetter.set(newInitialUserInfoBuilder(1).setSwitchUserId(userId).setReplaceGuest(replaceGuest).build());
    }

    private InitialUserSetter.Builder newInitialUserInfoBuilder(int type) {
        return new InitialUserSetter.Builder(type).setSupportsOverrideUserIdProperty(!this.mUserService.isUserHalSupported());
    }

    public boolean garageModeShouldExitImmediately() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mGarageModeShouldExitImmediately;
        }
        return z;
    }

    private void switchUserOnResumeIfNecessaryUsingHal() {
        Slog.i(TAG, "Using User HAL to define initial user behavior");
        this.mUserService.getInitialUserInfo(4, new HalCallback() { // from class: com.android.car.-$$Lambda$CarPowerManagementService$TK_e8OvEx31CU8Ak5g9rCd0h3GE
            @Override // android.car.userlib.HalCallback
            public final void onResponse(int i, Object obj) {
                this.f$0.lambda$switchUserOnResumeIfNecessaryUsingHal$1$CarPowerManagementService(i, (InitialUserInfoResponse) obj);
            }
        });
    }

    public /* synthetic */ void lambda$switchUserOnResumeIfNecessaryUsingHal$1$CarPowerManagementService(int status, InitialUserInfoResponse response) {
        switch (status) {
            case 1:
                if (response == null) {
                    switchUserOnResumeUserHalFallback("no response");
                } else {
                    boolean replaceGuest = !this.mSwitchGuestUserBeforeSleep;
                    int i = response.action;
                    if (i == 0) {
                        Slog.i(TAG, "HAL requested default initial user behavior");
                        executeDefaultInitialUserBehavior(replaceGuest);
                    } else if (i == 1) {
                        int userId = response.userToSwitchOrCreate.userId;
                        Slog.i(TAG, "HAL requested switch to user " + userId);
                        switchUser(userId, replaceGuest);
                    } else if (i == 2) {
                        int halFlags = response.userToSwitchOrCreate.flags;
                        String name = response.userNameToCreate;
                        Slog.i(TAG, "HAL requested new user (name=" + UserHelper.safeName(name) + ", flags=" + UserHalHelper.userFlagsToString(halFlags) + ")");
                        this.mInitialUserSetter.set(newInitialUserInfoBuilder(2).setNewUserName(name).setNewUserFlags(halFlags).build());
                    } else {
                        switchUserOnResumeUserHalFallback("invalid response action: " + response.action);
                    }
                }
                break;
            case 2:
            case 3:
                switchUserOnResumeUserHalFallback("timeout");
                break;
            case 4:
                switchUserOnResumeUserHalFallback("wrong response");
                break;
            case 5:
                switchUserOnResumeUserHalFallback("concurrent call");
                break;
            case 6:
                switchUserOnResumeUserHalFallback("Hal not supported");
                break;
            default:
                switchUserOnResumeUserHalFallback("invalid status: " + status);
                break;
        }
    }

    private void switchUserOnResumeUserHalFallback(String reason) {
        Slog.w(TAG, "Failed to set initial user based on User Hal (" + reason + "); falling back to default behavior");
        executeDefaultInitialUserBehavior(this.mSwitchGuestUserBeforeSleep ^ true);
    }

    private void handleShutdownPrepare(CpmsState newState) {
        String str;
        Slog.e(TAG, " handleShutdownPrepare " + newState);
        boolean z = true;
        setVoiceInteractionDisabled(true);
        this.mSystemInterface.setDisplayState(false);
        synchronized (this.mLock) {
            this.mShutdownOnFinish = (!this.mShutdownOnNextSuspend && this.mHal.isDeepSleepAllowed() && this.mSystemInterface.isSystemSupportingDeepSleep() && newState.mCanSleep) ? false : true;
            if (newState.mCanPostpone) {
                z = false;
            }
            this.mGarageModeShouldExitImmediately = z;
        }
        Slog.w(TAG, "mShutdownOnFinish " + this.mShutdownOnFinish + " ,mShutdownOnNextSuspend " + this.mShutdownOnNextSuspend + " ,isDeepSleepAllowed " + this.mHal.isDeepSleepAllowed() + " ,isSystemSupportingDeepSleep " + this.mSystemInterface.isSystemSupportingDeepSleep() + " ,newState.mCanSleep " + newState.mCanSleep);
        StringBuilder sb = new StringBuilder();
        sb.append(" mGarageModeShouldExitImmediately= ");
        sb.append(this.mGarageModeShouldExitImmediately);
        Slog.w(TAG, sb.toString());
        if (newState.mCanPostpone) {
            str = "starting shutdown prepare with Garage Mode";
        } else {
            str = "starting shutdown prepare without Garage Mode";
        }
        Slog.w(TAG, str);
        sendPowerManagerEvent(7);
        this.mHal.sendShutdownPrepare();
        doHandlePreprocessing();
        startPrintAppMonitor();
    }

    private void simulateShutdownPrepare() {
        Slog.w(TAG, "simulateShutdownPrepare");
        this.mSystemInterface.setDisplayState(false);
        Slog.w(TAG, "starting shutdown prepare");
        sendPowerManagerEvent(7);
        this.mHal.sendShutdownPrepare();
        doHandlePreprocessing();
    }

    private void handleWaitForFinish(CpmsState state) {
        int wakeupSec;
        Slog.w(TAG, "handleWaitForFinish " + state + " mGarageModeShouldExitImmediately " + this.mGarageModeShouldExitImmediately);
        sendPowerManagerEvent(state.mCarPowerStateListenerState);
        synchronized (this.mLock) {
            wakeupSec = this.mGarageModeShouldExitImmediately ? 0 : this.mNextWakeupSec;
        }
        int i = state.mCarPowerStateListenerState;
        if (i == 2) {
            Slog.w(TAG, "handleWaitForFinish for sendSleepEntry " + wakeupSec);
            this.mHal.sendSleepEntry(wakeupSec);
            return;
        }
        if (i == 5) {
            Slog.w(TAG, "handleWaitForFinish for sendShutdownStart " + wakeupSec);
            this.mHal.sendShutdownStart(wakeupSec);
        }
    }

    private void handleFinish() {
        boolean simulatedMode;
        boolean mustShutDown;
        boolean forceReboot;
        Slog.w(TAG, "handleFinish");
        synchronized (this.mSimulationWaitObject) {
            simulatedMode = this.mInSimulatedDeepSleepMode;
        }
        synchronized (this.mLock) {
            mustShutDown = this.mShutdownOnFinish && !simulatedMode;
            forceReboot = this.mRebootAfterGarageMode;
            this.mRebootAfterGarageMode = false;
        }
        Slog.w(TAG, "handleFinish forceReboot=" + forceReboot + " mustShutDown= " + mustShutDown + " ,mShutdownOnFinish=" + this.mShutdownOnFinish + " ,simulatedMode=" + simulatedMode);
        if (forceReboot) {
            PowerManager powerManager = (PowerManager) this.mContext.getSystemService(PowerManager.class);
            if (powerManager == null) {
                Slog.wtf(TAG, "No PowerManager. Cannot reboot.");
            } else {
                Slog.i(TAG, "GarageMode has completed. Forcing reboot.");
                powerManager.reboot("GarageModeReboot");
                throw new AssertionError("Should not return from PowerManager.reboot()");
            }
        }
        setVoiceInteractionDisabled(true);
        disableWifi();
        if (mustShutDown) {
            Slog.e(TAG, "handleFinish shutdown ");
            this.mSystemInterface.shutdown();
        } else {
            Slog.e(TAG, "handleFinish doHandleDeepSleep ");
            doHandleDeepSleep(simulatedMode);
        }
        this.mShutdownOnNextSuspend = false;
    }

    private void setVoiceInteractionDisabled(boolean disabled) {
        try {
            this.mVoiceInteractionManagerService.setDisabled(disabled);
        } catch (RemoteException e) {
            Slog.w(TAG, "setVoiceIntefactionDisabled(" + disabled + ") failed", e);
        }
    }

    private void restoreWifi() {
        boolean needToRestore = readWifiModifiedState();
        Slog.w(TAG, "restoreWifi needToRestore= " + needToRestore);
        if (needToRestore) {
            if (!this.mWifiManager.isWifiEnabled()) {
                Slog.i(TAG, "Wifi has been enabled to restore the last setting");
                this.mWifiManager.setWifiEnabled(true);
            }
            saveWifiModifiedState(false);
        }
    }

    private void disableWifi() {
        boolean wifiEnabled = this.mWifiManager.isWifiEnabled();
        boolean wifiModifiedState = readWifiModifiedState();
        Slog.w(TAG, "disableWifi wifiEnabled= " + wifiEnabled + " wifiModifiedState " + wifiModifiedState);
        if (wifiEnabled != wifiModifiedState) {
            saveWifiModifiedState(wifiEnabled);
        }
        if (wifiEnabled) {
            this.mWifiManager.setWifiEnabled(false);
            this.mWifiManager.isWifiEnabled();
            Slog.i(TAG, "Wifi has been disabled and the last setting was saved");
        }
    }

    private void saveWifiModifiedState(boolean forciblyDisabled) {
        try {
            FileOutputStream fos = this.mWifiStateFile.startWrite();
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
                try {
                    writer.write(forciblyDisabled ? WIFI_STATE_MODIFIED : WIFI_STATE_ORIGINAL);
                    writer.newLine();
                    writer.flush();
                    this.mWifiStateFile.finishWrite(fos);
                    writer.close();
                } finally {
                }
            } catch (IOException e) {
                this.mWifiStateFile.failWrite(fos);
                Slog.e(TAG, "Writing wifi_state failed", e);
            }
        } catch (IOException e2) {
            Slog.e(TAG, "Cannot create wifi_state", e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean readWifiModifiedState() {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.io.IOException -> L4e
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L4e
            android.util.AtomicFile r5 = r7.mWifiStateFile     // Catch: java.io.IOException -> L4e
            java.io.FileInputStream r5 = r5.openRead()     // Catch: java.io.IOException -> L4e
            java.nio.charset.Charset r6 = java.nio.charset.StandardCharsets.UTF_8     // Catch: java.io.IOException -> L4e
            r4.<init>(r5, r6)     // Catch: java.io.IOException -> L4e
            r3.<init>(r4)     // Catch: java.io.IOException -> L4e
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Throwable -> L44
            if (r4 != 0) goto L1e
            r0 = 0
            r1 = 1
            goto L38
        L1e:
            java.lang.String r5 = r4.trim()     // Catch: java.lang.Throwable -> L44
            r4 = r5
            java.lang.String r5 = "forcibly_disabled"
            boolean r5 = r5.equals(r4)     // Catch: java.lang.Throwable -> L44
            r0 = r5
            if (r0 != 0) goto L36
            java.lang.String r5 = "original"
            boolean r5 = r5.equals(r4)     // Catch: java.lang.Throwable -> L44
            if (r5 != 0) goto L36
            r5 = 1
            goto L37
        L36:
            r5 = r2
        L37:
            r1 = r5
        L38:
            r3.close()     // Catch: java.io.IOException -> L4e
            if (r1 == 0) goto L43
            android.util.AtomicFile r2 = r7.mWifiStateFile
            r2.delete()
        L43:
            return r0
        L44:
            r4 = move-exception
            r3.close()     // Catch: java.lang.Throwable -> L49
            goto L4d
        L49:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch: java.io.IOException -> L4e
        L4d:
            throw r4     // Catch: java.io.IOException -> L4e
        L4e:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to read wifi_state: "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "M_STR_CAR.POWER"
            android.util.Slog.w(r5, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarPowerManagementService.readWifiModifiedState():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseTimerLocked() {
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
        }
        this.mTimer = null;
        this.mTimerActive = false;
    }

    private void doHandlePreprocessing() {
        int intervalMs;
        int pollingCount;
        int shutdownPrepareTimeOverrideInSecs;
        synchronized (this.mLock) {
            intervalMs = this.mShutdownPollingIntervalMs;
            pollingCount = (this.mShutdownPrepareTimeMs / this.mShutdownPollingIntervalMs) + 1;
        }
        if ((Build.IS_USERDEBUG || Build.IS_ENG) && (shutdownPrepareTimeOverrideInSecs = SystemProperties.getInt(PROP_MAX_GARAGE_MODE_DURATION_OVERRIDE, -1)) >= 0) {
            pollingCount = ((shutdownPrepareTimeOverrideInSecs * 1000) / intervalMs) + 1;
            Slog.i(TAG, "Garage mode duration overridden secs:" + shutdownPrepareTimeOverrideInSecs);
        }
        Slog.i(TAG, "processing before shutdown expected for: " + this.mShutdownPrepareTimeMs + " ms, adding polling:" + pollingCount);
        synchronized (this.mLock) {
            this.mProcessingStartTime = SystemClock.elapsedRealtime();
            releaseTimerLocked();
            Timer timer = new Timer();
            this.mTimer = timer;
            this.mTimerActive = true;
            timer.scheduleAtFixedRate(new ShutdownProcessingTimerTask(pollingCount), 0L, intervalMs);
        }
        if (this.mSwitchGuestUserBeforeSleep) {
            switchToNewGuestIfNecessary();
        }
    }

    private void sendPowerManagerEvent(int newState) {
        Slog.w(TAG, " sendPowerManagerEvent nofity newState= " + newState);
        notifyListeners(this.mPowerManagerListeners, newState);
        boolean allowCompletion = newState == 7;
        boolean haveSomeCompleters = false;
        PowerManagerCallbackList completingListeners = new PowerManagerCallbackList();
        synchronized (this.mLock) {
            this.mListenersWeAreWaitingFor.clear();
            int idx = this.mPowerManagerListenersWithCompletion.beginBroadcast();
            while (true) {
                int idx2 = idx - 1;
                if (idx <= 0) {
                    break;
                }
                ICarPowerStateListener listener = this.mPowerManagerListenersWithCompletion.getBroadcastItem(idx2);
                completingListeners.register(listener);
                if (allowCompletion) {
                    this.mListenersWeAreWaitingFor.add(listener.asBinder());
                    haveSomeCompleters = true;
                }
                idx = idx2;
            }
            this.mPowerManagerListenersWithCompletion.finishBroadcast();
        }
        Slog.w(TAG, "sendPowerManagerEvent allowCompletion= " + allowCompletion + " ,haveSomeCompleters= " + haveSomeCompleters + " ,mListenersWeAreWaitingFor= " + this.mListenersWeAreWaitingFor);
        notifyListeners(completingListeners, newState);
        if (allowCompletion && !haveSomeCompleters) {
            signalComplete();
        }
        alarmLogSwitch(newState);
    }

    private void notifyListeners(PowerManagerCallbackList listenerList, int newState) {
        int idx = listenerList.beginBroadcast();
        while (true) {
            int idx2 = idx - 1;
            if (idx > 0) {
                ICarPowerStateListener listener = listenerList.getBroadcastItem(idx2);
                try {
                    listener.onStateChanged(newState);
                } catch (RemoteException e) {
                    Slog.e(TAG, "onStateChanged() call failed", e);
                }
                idx = idx2;
            } else {
                listenerList.finishBroadcast();
                return;
            }
        }
    }

    private void doHandleDeepSleep(boolean simulatedMode) {
        int nextListenerState;
        Slog.w(TAG, "doHandleDeepSleep simulatedMode " + simulatedMode);
        this.mSystemInterface.switchToPartialWakeLock();
        this.mHandler.cancelProcessingComplete();
        synchronized (this.mLock) {
            this.mLastSleepEntryTime = SystemClock.elapsedRealtime();
        }
        if (simulatedMode) {
            simulateSleepByWaiting();
            nextListenerState = 8;
        } else {
            Slog.w(TAG, "doHandleDeepSleep: suspendWithRetries ");
            boolean sleepSucceeded = suspendWithRetries();
            Slog.w(TAG, "doHandleDeepSleep: sleepSucceeded is " + sleepSucceeded);
            if (!sleepSucceeded) {
                return;
            } else {
                nextListenerState = 3;
            }
        }
        synchronized (this.mLock) {
            this.mIsResuming = true;
            this.mNextWakeupSec = 0;
        }
        Slog.i(TAG, "Resuming after suspending");
        this.mSystemInterface.refreshDisplayBrightness();
        onApPowerStateChange(0, nextListenerState);
    }

    private boolean needPowerStateChangeLocked(CpmsState newState) {
        boolean transitionAllowed;
        CpmsState cpmsState = this.mCurrentState;
        if (cpmsState == null) {
            return true;
        }
        if (cpmsState.equals(newState)) {
            Slog.d(TAG, "Requested state is already in effect: " + newState.name());
            return false;
        }
        int i = this.mCurrentState.mState;
        if (i == 0) {
            if (newState.mState != 1 && newState.mState != 2) {
                z = false;
            }
            transitionAllowed = z;
        } else if (i == 1) {
            if (newState.mState != 2 && newState.mState != 5) {
                z = false;
            }
            transitionAllowed = z;
        } else if (i == 2) {
            if ((newState.mState != 2 || newState.mCanPostpone) && newState.mState != 3 && newState.mState != 0) {
                z = false;
            }
            transitionAllowed = z;
        } else if (i == 3) {
            if (newState.mState != 4 && newState.mState != 0) {
                z = false;
            }
            transitionAllowed = z;
        } else if (i == 4) {
            transitionAllowed = newState.mState == 0;
        } else if (i == 5) {
            transitionAllowed = true;
        } else {
            Slog.e(TAG, "Unexpected current state:  currentState=" + this.mCurrentState.name() + ", newState=" + newState.name());
            transitionAllowed = true;
        }
        if (!transitionAllowed) {
            Slog.e(TAG, "Requested power transition is not allowed: " + this.mCurrentState.name() + " --> " + newState.name());
        }
        return transitionAllowed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandleProcessingComplete() {
        Slog.w(TAG, "doHandleProcessingComplete");
        stopPrintAppMonitor();
        synchronized (this.mLock) {
            releaseTimerLocked();
            if (!this.mShutdownOnFinish && this.mLastSleepEntryTime > this.mProcessingStartTime) {
                Slog.w(TAG, "Duplicate sleep entry request, ignore");
                return;
            }
            int listenerState = this.mShutdownOnFinish ? 5 : 2;
            Slog.w(TAG, "doHandleProcessingComplete listenerState=" + listenerState);
            onApPowerStateChange(3, listenerState);
        }
    }

    @Override // com.android.car.hal.PowerHalService.PowerEventListener
    public void onDisplayBrightnessChange(int brightness) {
        this.mHandler.handleDisplayBrightnessChange(brightness);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandleDisplayBrightnessChange(int brightness) {
        this.mSystemInterface.setDisplayBrightness(brightness);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHandleMainDisplayStateChange(boolean on) {
        Slog.w(TAG, "Unimplemented:  doHandleMainDisplayStateChange() - on = " + on);
    }

    public void handleMainDisplayChanged(boolean on) {
        this.mHandler.handleMainDisplayStateChange(on);
    }

    public void sendDisplayBrightness(int brightness) {
        this.mHal.sendDisplayBrightness(brightness);
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public void registerListener(ICarPowerStateListener listener) {
        Slog.w(TAG, "registerListener " + listener + " ,pid = " + Binder.getCallingPid());
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        this.mPowerManagerListeners.register(listener);
    }

    public void registerListenerWithCompletion(ICarPowerStateListener listener) {
        Slog.w(TAG, "registerListenerWithCompletion " + listener + " ,pid = " + Binder.getCallingPid());
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        ICarImpl.assertCallingFromSystemProcessOrSelf();
        this.mPowerManagerListenersWithCompletion.register(listener);
        Slog.w(TAG, "registerListenerWithCompletion mPowerManagerListenersWithCompletion" + this.mPowerManagerListenersWithCompletion);
        String listenerName = listener.asBinder().toString();
        this.mPidMap.put(listenerName, Integer.valueOf(Binder.getCallingPid()));
        Slog.w(TAG, "mPidMap= " + this.mPidMap.toString());
    }

    public void unregisterListener(ICarPowerStateListener listener) {
        Slog.w(TAG, "unregisterListener " + listener + " ,pid = " + Binder.getCallingPid());
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        doUnregisterListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doUnregisterListener(ICarPowerStateListener listener) {
        Slog.w(TAG, "doUnregisterListener " + listener);
        this.mPowerManagerListeners.unregister(listener);
        boolean found = this.mPowerManagerListenersWithCompletion.unregister(listener);
        Slog.w(TAG, "doUnregisterListener not to check found：" + found);
        finishedImpl(listener.asBinder());
        String listenerName = listener.asBinder().toString();
        this.mPidMap.remove(listenerName);
        Slog.w(TAG, "mPidMap= " + this.mPidMap.toString());
    }

    public void requestShutdownOnNextSuspend() {
        Slog.w(TAG, "requestShutdownOnNextSuspend ,pid = " + Binder.getCallingPid());
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        synchronized (this.mLock) {
            this.mShutdownOnNextSuspend = true;
        }
    }

    public void finished(ICarPowerStateListener listener) {
        Slog.e(TAG, "finished " + listener + ",pid = " + Binder.getCallingPid());
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        ICarImpl.assertCallingFromSystemProcessOrSelf();
        finishedImpl(listener.asBinder());
    }

    public void scheduleNextWakeupTime(int seconds) {
        Slog.w(TAG, "scheduleNextWakeupTime " + seconds);
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        if (seconds < 0) {
            Slog.w(TAG, "Next wake up time is negative. Ignoring!");
            return;
        }
        boolean timedWakeupAllowed = this.mHal.isTimedWakeupAllowed();
        synchronized (this.mLock) {
            if (!timedWakeupAllowed) {
                Slog.w(TAG, "Setting timed wakeups are disabled in HAL. Skipping");
                this.mNextWakeupSec = 0;
            } else {
                if (this.mNextWakeupSec == 0 || this.mNextWakeupSec > seconds) {
                    this.mNextWakeupSec = seconds;
                } else {
                    Slog.d(TAG, "Tried to schedule next wake up, but already had shorter scheduled time");
                }
            }
        }
    }

    public int getPowerState() {
        int i;
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        synchronized (this.mLock) {
            i = this.mCurrentState == null ? 0 : this.mCurrentState.mCarPowerStateListenerState;
        }
        return i;
    }

    private void finishedImpl(IBinder binder) {
        Slog.e(TAG, "finishedImpl " + binder);
        synchronized (this.mLock) {
            Slog.e(TAG, "finishedImpl before, mListenersWeAreWaitingFor " + this.mListenersWeAreWaitingFor);
            if (this.mListenersWeAreWaitingFor.isEmpty()) {
                Slog.e(TAG, "finishedImpl mListenersWeAreWaitingFor has been empty");
                return;
            }
            this.mListenersWeAreWaitingFor.remove(binder);
            boolean allAreComplete = this.mListenersWeAreWaitingFor.isEmpty();
            Slog.e(TAG, "finishedImpl allAreComplete= " + allAreComplete + " ,mListenersWeAreWaitingFor " + this.mListenersWeAreWaitingFor);
            if (allAreComplete) {
                signalComplete();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signalComplete() {
        stopPrintAppMonitor();
        synchronized (this.mLock) {
            Slog.e(TAG, "signalComplete mLock start " + this.mCurrentState);
            if (this.mCurrentState.mState == 2 || this.mCurrentState.mState == 5) {
                if (!this.mShutdownOnFinish && this.mLastSleepEntryTime > this.mProcessingStartTime && this.mLastSleepEntryTime < SystemClock.elapsedRealtime()) {
                    Slog.i(TAG, "signalComplete: Already slept!");
                    return;
                } else {
                    PowerHandler powerHandler = this.mHandler;
                    Slog.w(TAG, "Apps are finished, call handleProcessingComplete()");
                    powerHandler.handleProcessingComplete();
                }
            }
            Slog.e(TAG, "signalComplete mLock end " + this.mCurrentState);
        }
    }

    private static final class PowerHandler extends Handler {
        private static final String TAG = PowerHandler.class.getSimpleName();
        private final long MAIN_DISPLAY_EVENT_DELAY_MS;
        private final int MSG_DISPLAY_BRIGHTNESS_CHANGE;
        private final int MSG_MAIN_DISPLAY_STATE_CHANGE;
        private final int MSG_POWER_STATE_CHANGE;
        private final int MSG_PROCESSING_COMPLETE;
        private final WeakReference<CarPowerManagementService> mService;

        private PowerHandler(Looper looper, CarPowerManagementService service) {
            super(looper);
            this.MSG_POWER_STATE_CHANGE = 0;
            this.MSG_DISPLAY_BRIGHTNESS_CHANGE = 1;
            this.MSG_MAIN_DISPLAY_STATE_CHANGE = 2;
            this.MSG_PROCESSING_COMPLETE = 3;
            this.MAIN_DISPLAY_EVENT_DELAY_MS = 500L;
            this.mService = new WeakReference<>(service);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handlePowerStateChange() {
            Message msg = obtainMessage(0);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleDisplayBrightnessChange(int brightness) {
            Message msg = obtainMessage(1, brightness, 0);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleMainDisplayStateChange(boolean on) {
            removeMessages(2);
            Message msg = obtainMessage(2, Boolean.valueOf(on));
            sendMessageDelayed(msg, 500L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleProcessingComplete() {
            removeMessages(3);
            Message msg = obtainMessage(3);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancelProcessingComplete() {
            removeMessages(3);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void cancelAll() {
            removeMessages(0);
            removeMessages(1);
            removeMessages(2);
            removeMessages(3);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            CarPowerManagementService service = this.mService.get();
            if (service == null) {
                Slog.i(TAG, "handleMessage null service");
                return;
            }
            int i = msg.what;
            if (i == 0) {
                service.doHandlePowerStateChange();
                return;
            }
            if (i == 1) {
                service.doHandleDisplayBrightnessChange(msg.arg1);
            } else if (i == 2) {
                service.doHandleMainDisplayStateChange(((Boolean) msg.obj).booleanValue());
            } else if (i == 3) {
                service.doHandleProcessingComplete();
            }
        }
    }

    private class ShutdownProcessingTimerTask extends TimerTask {
        private int mCurrentCount;
        private final int mExpirationCount;

        private ShutdownProcessingTimerTask(int expirationCount) {
            this.mExpirationCount = expirationCount;
            this.mCurrentCount = 0;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            synchronized (CarPowerManagementService.this.mLock) {
                if (CarPowerManagementService.this.mTimerActive) {
                    this.mCurrentCount++;
                    Slog.w(CarPowerManagementService.TAG, "ShutdownProcessingTimerTask mCurrentCount= " + this.mCurrentCount + " , mExpirationCount= " + this.mExpirationCount);
                    if (this.mCurrentCount > this.mExpirationCount) {
                        CarPowerManagementService.this.releaseTimerLocked();
                        PowerHandler handler = CarPowerManagementService.this.mHandler;
                        Slog.w(CarPowerManagementService.TAG, "ShutdownProcessingTimerTask has beean > maxCount,mShutdownPrepareTimeMs = " + CarPowerManagementService.this.mShutdownPrepareTimeMs);
                        handler.handleProcessingComplete();
                    } else {
                        CarPowerManagementService.this.mHal.sendShutdownPostpone(WifiEntry.FREQUENCY_5_GHZ);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bb A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean suspendWithRetries() {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.CarPowerManagementService.suspendWithRetries():boolean");
    }

    private static class CpmsState {
        public static final int ON = 1;
        public static final int SHUTDOWN_PREPARE = 2;
        public static final int SIMULATE_SLEEP = 5;
        public static final int SUSPEND = 4;
        public static final int WAIT_FOR_FINISH = 3;
        public static final int WAIT_FOR_VHAL = 0;
        public final boolean mCanPostpone;
        public final boolean mCanSleep;
        public final int mCarPowerStateListenerState;
        public final int mState;

        CpmsState(PowerHalService.PowerState halPowerState) {
            int i = halPowerState.mState;
            if (i == 0) {
                this.mCanPostpone = false;
                this.mCanSleep = false;
                this.mCarPowerStateListenerState = cpmsStateToPowerStateListenerState(1);
                this.mState = 1;
                return;
            }
            if (i == 1) {
                this.mCanPostpone = halPowerState.canPostponeShutdown();
                this.mCanSleep = halPowerState.canEnterDeepSleep();
                this.mCarPowerStateListenerState = cpmsStateToPowerStateListenerState(2);
                this.mState = 2;
                return;
            }
            if (i == 2) {
                this.mCanPostpone = false;
                this.mCanSleep = false;
                this.mCarPowerStateListenerState = 8;
                this.mState = 0;
                return;
            }
            if (i == 3) {
                this.mCanPostpone = false;
                this.mCanSleep = false;
                this.mCarPowerStateListenerState = cpmsStateToPowerStateListenerState(4);
                this.mState = 4;
                return;
            }
            this.mCanPostpone = false;
            this.mCanSleep = false;
            this.mCarPowerStateListenerState = 0;
            this.mState = 0;
        }

        CpmsState(int state) {
            this(state, cpmsStateToPowerStateListenerState(state));
        }

        CpmsState(int state, int carPowerStateListenerState) {
            this.mCanPostpone = state == 5;
            this.mCanSleep = state == 5;
            this.mCarPowerStateListenerState = carPowerStateListenerState;
            this.mState = state;
        }

        public String name() {
            String baseName;
            int i = this.mState;
            if (i == 0) {
                baseName = "WAIT_FOR_VHAL";
            } else if (i == 1) {
                baseName = "ON";
            } else if (i == 2) {
                baseName = "SHUTDOWN_PREPARE";
            } else if (i == 3) {
                baseName = "WAIT_FOR_FINISH";
            } else if (i == 4) {
                baseName = "SUSPEND";
            } else if (i == 5) {
                baseName = "SIMULATE_SLEEP";
            } else {
                baseName = "<unknown>";
            }
            return baseName + "(" + this.mState + ")";
        }

        private static int cpmsStateToPowerStateListenerState(int state) {
            if (state == 1) {
                return 6;
            }
            if (state == 2) {
                return 7;
            }
            if (state != 4) {
                return 0;
            }
            return 2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof CpmsState)) {
                return false;
            }
            CpmsState that = (CpmsState) o;
            return this.mState == that.mState && this.mCanSleep == that.mCanSleep && this.mCanPostpone == that.mCanPostpone && this.mCarPowerStateListenerState == that.mCarPowerStateListenerState;
        }

        public String toString() {
            return "CpmsState canSleep:" + this.mCanSleep + ", canPostpone=" + this.mCanPostpone + ", carPowerStateListenerState=" + this.mCarPowerStateListenerState + ", CpmsState=" + name();
        }
    }

    public void forceSimulatedResume() {
        PowerHandler handler;
        synchronized (this.mLock) {
            this.mPendingPowerStates.addFirst(new CpmsState(0, 8));
            this.mLock.notify();
            handler = this.mHandler;
        }
        handler.handlePowerStateChange();
        synchronized (this.mSimulationWaitObject) {
            this.mWakeFromSimulatedSleep = true;
            this.mSimulationWaitObject.notify();
        }
    }

    void forceSuspendAndMaybeReboot(boolean shouldReboot) {
        PowerHandler handler;
        synchronized (this.mSimulationWaitObject) {
            this.mInSimulatedDeepSleepMode = true;
            this.mWakeFromSimulatedSleep = false;
            this.mGarageModeShouldExitImmediately = false;
        }
        synchronized (this.mLock) {
            this.mRebootAfterGarageMode = shouldReboot;
            this.mPendingPowerStates.addFirst(new CpmsState(5, 7));
            handler = this.mHandler;
        }
        handler.handlePowerStateChange();
    }

    public void powerOffFromCommand(boolean skipGarageMode, boolean shutdown) {
        int param;
        int i;
        ICarImpl.assertPermission(this.mContext, Manifest.permission.CAR_POWER);
        if (shutdown) {
            if (skipGarageMode) {
                i = 1;
            } else {
                i = 3;
            }
            param = i;
        } else {
            param = skipGarageMode ? 4 : 2;
        }
        PowerHalService.PowerState state = new PowerHalService.PowerState(1, param);
        synchronized (this.mLock) {
            this.mRebootAfterGarageMode = false;
            this.mPendingPowerStates.addFirst(new CpmsState(state));
            this.mLock.notify();
        }
        this.mHandler.handlePowerStateChange();
    }

    private void simulateSleepByWaiting() {
        Slog.i(TAG, "Starting to simulate Deep Sleep by waiting");
        synchronized (this.mSimulationWaitObject) {
            while (!this.mWakeFromSimulatedSleep) {
                try {
                    this.mSimulationWaitObject.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            this.mInSimulatedDeepSleepMode = false;
        }
        Slog.i(TAG, "Exit Deep Sleep simulation");
    }

    private int getMaxSuspendWaitDurationConfig() {
        return this.mContext.getResources().getInteger(R.integer.config_maxSuspendWaitDuration);
    }

    class DataLogHandler extends Handler {
        public DataLogHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 101) {
                if (i == 102) {
                    Slog.w(CarPowerManagementService.TAG, "print alarms ");
                    CarPowerManagementService.this.mDataLogHandler.removeMessages(102);
                    CarPowerManagementService.this.mContext.sendBroadcast(new Intent(CarPowerManagementService.ACTION_PRINT_ALARM));
                    CarPowerManagementService.this.mDataLogHandler.sendEmptyMessageDelayed(102, 10000L);
                    return;
                }
                return;
            }
            try {
                CarPowerManagementService.this.mDataLogHandler.removeMessages(101);
                synchronized (CarPowerManagementService.this.mLock) {
                    Slog.w(CarPowerManagementService.TAG, "print current mGarageModeShouldExitImmediately : " + CarPowerManagementService.this.mGarageModeShouldExitImmediately);
                    Slog.w(CarPowerManagementService.TAG, "print all wait Apps listener, mListenersWeAreWaitingFor: " + CarPowerManagementService.this.mListenersWeAreWaitingFor);
                    if (CarPowerManagementService.this.mListenersWeAreWaitingFor == null || CarPowerManagementService.this.mListenersWeAreWaitingFor.size() == 0) {
                        Slog.w(CarPowerManagementService.TAG, "current Apps has finished");
                    } else {
                        Slog.e(CarPowerManagementService.TAG, "has Apps not finished，size = " + CarPowerManagementService.this.mListenersWeAreWaitingFor.size());
                        int index = 0;
                        for (IBinder binder : CarPowerManagementService.this.mListenersWeAreWaitingFor) {
                            String binderName = binder.toString();
                            int pid = CarPowerManagementService.this.mPidMap.get(binderName).intValue();
                            String appName = CarPowerManagementService.this.getAppName(pid);
                            Slog.w(CarPowerManagementService.TAG, "binderName " + index + "  = " + binderName + ", pid= " + pid + " appName " + appName);
                            index++;
                        }
                        CarPowerManagementService.this.mDataLogHandler.sendEmptyMessageDelayed(101, 10000L);
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private void startPrintAppMonitor() {
        Slog.w(TAG, "startPrintAppMonitor");
        this.mDataLogHandler.sendEmptyMessage(101);
    }

    private void stopPrintAppMonitor() {
        Slog.w(TAG, "stopPrintAppMonitor");
        this.mDataLogHandler.removeMessages(101);
    }

    private void alarmLogSwitch(int powerState) {
        if (powerState == 3 || powerState == 6) {
            Slog.w(TAG, "stop Print Alarms");
            this.mDataLogHandler.removeMessages(102);
        } else if (powerState == 7) {
            Slog.w(TAG, "start Print Alarms");
            this.mDataLogHandler.sendEmptyMessage(102);
        }
    }

    private void registerAppListerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(AppListerRemoveBroadcast.ACTION_REMOVE_LISTENER);
        intentFilter.addAction(AppListerRemoveBroadcast.ACTION_KILL_LISTENER);
        AppListerRemoveBroadcast appListerRemoveBroadcast = new AppListerRemoveBroadcast();
        this.mAppListerRemoveBroadcast = appListerRemoveBroadcast;
        this.mContext.registerReceiver(appListerRemoveBroadcast, intentFilter);
    }

    private void unregisterAppListerBroadcast() {
        AppListerRemoveBroadcast appListerRemoveBroadcast = this.mAppListerRemoveBroadcast;
        if (appListerRemoveBroadcast != null) {
            this.mContext.unregisterReceiver(appListerRemoveBroadcast);
        }
    }

    class AppListerRemoveBroadcast extends BroadcastReceiver {
        public static final String ACTION_KILL_LISTENER = "CPMS_App_Listener_Kill_All";
        public static final String ACTION_REMOVE_LISTENER = "CPMS_App_Listener_Remove_All";

        AppListerRemoveBroadcast() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            try {
                Slog.w(CarPowerManagementService.TAG, "onReceive " + action);
                if (ACTION_REMOVE_LISTENER.equals(action)) {
                    Slog.w(CarPowerManagementService.TAG, " clear mListenersWeAreWaitingFor");
                    synchronized (CarPowerManagementService.this.mLock) {
                        CarPowerManagementService.this.mListenersWeAreWaitingFor.clear();
                    }
                    CarPowerManagementService.this.signalComplete();
                    return;
                }
                if (ACTION_KILL_LISTENER.equals(action)) {
                    CarPowerManagementService.this.killAllAppListener();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killAllAppListener() {
        try {
            synchronized (this.mLock) {
                for (IBinder binder : this.mListenersWeAreWaitingFor) {
                    Slog.w(TAG, "killAllAppListener " + binder);
                    String binderName = binder.toString();
                    int pid = this.mPidMap.get(binderName).intValue();
                    if (pid != Process.myPid()) {
                        String appName = getAppName(pid);
                        Slog.w(TAG, "kill AppListener: binderName= " + binderName + ", pid= " + pid + " , appName= " + appName);
                        Process.killProcess(pid);
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAppName(int pid) {
        String processName = "";
        try {
            ActivityManager am = (ActivityManager) this.mContext.getSystemService("activity");
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo info : runningAppProcesses) {
                try {
                    if (info.pid == pid) {
                        return info.processName;
                    }
                    continue;
                } catch (Exception e) {
                    processName = "";
                }
            }
            return processName;
        } catch (Exception exception) {
            exception.printStackTrace();
            return processName;
        }
    }
}
