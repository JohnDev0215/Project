package com.android.internal.car;

import android.app.admin.DevicePolicyManager;
import android.automotive.watchdog.ICarWatchdogMonitor;
import android.car.userlib.CarUserManagerHelper;
import android.car.userlib.InitialUserSetter;
import android.car.userlib.UserHalHelper;
import android.car.userlib.UserHelper;
import android.car.watchdoglib.CarWatchdogDaemonHelper;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.UserInfo;
import android.hardware.automotive.audiocontrol.V1_0.IAudioControl;
import android.hardware.automotive.vehicle.V2_0.IVehicle;
import android.hidl.manager.V1_0.IServiceManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.v4.media.session.PlaybackStateCompat;
import android.sysprop.CarProperties;
import android.util.EventLog;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.TimeUtils;
import com.android.internal.car.CarServiceHelperService;
import com.android.internal.car.ICarServiceHelper;
import com.android.internal.os.IResultReceiver;
import com.android.internal.os.ProcessCpuTracker;
import com.android.internal.util.function.pooled.PooledLambda;
import com.android.server.SystemService;
import com.android.server.Watchdog;
import com.android.server.am.ActivityManagerService;
import com.android.server.utils.TimingsTraceAndSlog;
import com.android.server.wm.CarLaunchParamsModifier;
import com.android.wifitrackerlib.WifiEntry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class CarServiceHelperService extends SystemService {
    private static final List<String> CAR_HAL_INTERFACES_OF_INTEREST = Arrays.asList(IVehicle.kInterfaceName, IAudioControl.kInterfaceName, android.hardware.automotive.audiocontrol.V2_0.IAudioControl.kInterfaceName);
    private static final boolean DBG = true;
    private static final long LIFECYCLE_TIMESTAMP_IGNORE = 0;
    private static final String PROP_RESTART_RUNTIME = "ro.car.recovery.restart_runtime.enabled";
    private static final String TAG = "CarServiceHelper";
    private static final boolean VERBOSE = true;
    private static final int WHAT_HAL_TIMEOUT = 1;
    private static final int WHAT_POST_PROCESS_DUMPING = 2;
    private static final int WHAT_PROCESS_KILL = 3;
    private final int NUMBER_PENDING_OPERATIONS;
    private final CarLaunchParamsModifier mCarLaunchParamsModifier;
    private IBinder mCarService;
    private final ServiceConnection mCarServiceConnection;
    private boolean mCarServiceHasCrashed;
    private final CarUserManagerHelper mCarUserManagerHelper;
    private final CarWatchdogDaemonHelper mCarWatchdogDaemonHelper;
    private final ICarWatchdogMonitorImpl mCarWatchdogMonitor;
    private final CarWatchdogDaemonHelper.OnConnectionChangeListener mConnectionListener;
    private final Context mContext;
    private long mFirstUnlockedUserDuration;
    private final boolean mHalEnabled;
    private int mHalResponseTime;
    private final int mHalTimeoutMs;
    private final Handler mHandler;
    private final ICarServiceHelperImpl mHelper;
    private final InitialUserSetter mInitialUserSetter;
    private boolean mInitialized;
    private int mLastSwitchedUser;
    private final SparseIntArray mLastUserLifecycle;
    private final Object mLock;
    private ArrayList<Runnable> mPendingOperations;
    private final ProcessTerminator mProcessTerminator;
    private final BroadcastReceiver mShutdownEventReceiver;
    private boolean mSystemBootCompleted;
    private final UserManager mUserManager;

    /* JADX INFO: Access modifiers changed from: private */
    public static native int nativeForceSuspend(int i);

    public /* synthetic */ void lambda$new$0$CarServiceHelperService(boolean connected) {
        if (connected) {
            registerMonitorToWatchdogDaemon();
        }
    }

    public CarServiceHelperService(Context context) {
        this(context, new CarUserManagerHelper(context), null, UserManager.get(context), new CarLaunchParamsModifier(context), new CarWatchdogDaemonHelper(TAG), ((Boolean) CarProperties.user_hal_enabled().orElse(false)).booleanValue(), ((Integer) CarProperties.user_hal_timeout().orElse(Integer.valueOf(WifiEntry.FREQUENCY_5_GHZ))).intValue());
    }

    CarServiceHelperService(Context context, CarUserManagerHelper userManagerHelper, InitialUserSetter initialUserSetter, UserManager userManager, CarLaunchParamsModifier carLaunchParamsModifier, CarWatchdogDaemonHelper carWatchdogDaemonHelper, boolean halEnabled, int halTimeoutMs) {
        super(context);
        this.NUMBER_PENDING_OPERATIONS = 5;
        this.mLastSwitchedUser = -10000;
        this.mHelper = new ICarServiceHelperImpl();
        this.mLock = new Object();
        this.mLastUserLifecycle = new SparseIntArray();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mProcessTerminator = new ProcessTerminator();
        this.mCarWatchdogMonitor = new ICarWatchdogMonitorImpl(this);
        this.mConnectionListener = new CarWatchdogDaemonHelper.OnConnectionChangeListener() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$TrlYuq0XTWR5LPJK5b19hg5Erbw
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.OnConnectionChangeListener
            public final void onConnectionChange(boolean z) {
                this.f$0.lambda$new$0$CarServiceHelperService(z);
            }
        };
        this.mCarServiceConnection = new ServiceConnection() { // from class: com.android.internal.car.CarServiceHelperService.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Slog.d(CarServiceHelperService.TAG, "onServiceConnected:" + iBinder);
                CarServiceHelperService.this.handleCarServiceConnection(iBinder);
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                CarServiceHelperService.this.handleCarServiceCrash();
            }
        };
        this.mShutdownEventReceiver = new BroadcastReceiver() { // from class: com.android.internal.car.CarServiceHelperService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ((!intent.getAction().equals("android.intent.action.REBOOT") && !intent.getAction().equals("android.intent.action.ACTION_SHUTDOWN")) || (intent.getFlags() & 268435456) == 0) {
                    return;
                }
                try {
                    CarServiceHelperService.this.mCarWatchdogDaemonHelper.notifySystemStateChange(0, 1, 0);
                    Slog.d(CarServiceHelperService.TAG, "Notified car watchdog daemon a power cycle(1)");
                } catch (RemoteException | RuntimeException e) {
                    Slog.w(CarServiceHelperService.TAG, "Notifying system state change failed: " + e);
                }
            }
        };
        this.mContext = context;
        this.mCarUserManagerHelper = userManagerHelper;
        this.mUserManager = userManager;
        this.mCarLaunchParamsModifier = carLaunchParamsModifier;
        this.mCarWatchdogDaemonHelper = carWatchdogDaemonHelper;
        boolean halValidUserHalSettings = false;
        if (halEnabled) {
            if (halTimeoutMs > 0) {
                Slog.i(TAG, "User HAL enabled with timeout of " + halTimeoutMs + "ms");
                halValidUserHalSettings = true;
            } else {
                Slog.w(TAG, "Not using User HAL due to invalid value on userHalTimeoutMs config: " + halTimeoutMs);
            }
        }
        if (halValidUserHalSettings) {
            this.mHalEnabled = true;
            this.mHalTimeoutMs = halTimeoutMs;
        } else {
            this.mHalEnabled = false;
            this.mHalTimeoutMs = -1;
            Slog.i(TAG, "Not using User HAL");
        }
        if (initialUserSetter == null) {
            this.mInitialUserSetter = new InitialUserSetter(context, new Consumer() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$0je9w9CEqRQEJUZdgwz8oixbAAQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f$0.lambda$new$1$CarServiceHelperService((UserInfo) obj);
                }
            });
        } else {
            this.mInitialUserSetter = initialUserSetter;
        }
    }

    public void onBootPhase(int phase) {
        EventLog.writeEvent(150001, phase);
        Slog.d(TAG, "onBootPhase:" + phase);
        TimingsTraceAndSlog t = newTimingsTraceAndSlog();
        if (phase == 600) {
            t.traceBegin("onBootPhase.3pApps");
            this.mCarLaunchParamsModifier.init();
            checkForCarServiceConnection(t);
            setupAndStartUsers(t);
            checkForCarServiceConnection(t);
            t.traceEnd();
            return;
        }
        if (phase == 1000) {
            t.traceBegin("onBootPhase.completed");
            managePreCreatedUsers();
            synchronized (this.mLock) {
                this.mSystemBootCompleted = true;
            }
            try {
                this.mCarWatchdogDaemonHelper.notifySystemStateChange(2, phase, 0);
            } catch (RemoteException | RuntimeException e) {
                Slog.w(TAG, "Failed to notify boot phase change: " + e);
            }
            t.traceEnd();
        }
    }

    public void onStart() {
        EventLog.writeEvent(150000, this.mHalEnabled ? 1 : 0);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.REBOOT");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        this.mContext.registerReceiverForAllUsers(this.mShutdownEventReceiver, intentFilter, null, null);
        this.mCarWatchdogDaemonHelper.addOnConnectionChangeListener(this.mConnectionListener);
        this.mCarWatchdogDaemonHelper.connect();
        Intent intent = new Intent();
        intent.setPackage("com.android.car");
        intent.setAction("android.car.ICar");
        if (!this.mContext.bindServiceAsUser(intent, this.mCarServiceConnection, 1, UserHandle.SYSTEM)) {
            Slog.wtf(TAG, "cannot start car service");
        }
        loadNativeLibrary();
    }

    public void onUserUnlocking(SystemService.TargetUser user) {
        if (isPreCreated(user, 3)) {
            return;
        }
        EventLog.writeEvent(150004, user.getUserIdentifier());
        Slog.d(TAG, "onUserUnlocking(" + user + ")");
        sendUserLifecycleEvent(3, user);
    }

    public void onUserUnlocked(final SystemService.TargetUser user) {
        if (isPreCreated(user, 4)) {
            return;
        }
        int userId = user.getUserIdentifier();
        EventLog.writeEvent(150005, userId);
        Slog.d(TAG, "onUserUnlocked(" + user + ")");
        if (this.mFirstUnlockedUserDuration == 0 && !UserHelper.isHeadlessSystemUser(userId)) {
            this.mFirstUnlockedUserDuration = SystemClock.elapsedRealtime() - Process.getStartElapsedRealtime();
            Slog.i(TAG, "Time to unlock 1st user(" + user + "): " + TimeUtils.formatDuration(this.mFirstUnlockedUserDuration));
            boolean operationQueued = false;
            synchronized (this.mLock) {
                this.mLastUserLifecycle.put(userId, 4);
                if (this.mCarService == null) {
                    operationQueued = true;
                    Slog.d(TAG, "Queuing first user unlock for user " + user);
                    queueOperationLocked(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$9v5-uemuJ7nDtjQIvSSsvv6mayY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onUserUnlocked$2$CarServiceHelperService(user);
                        }
                    });
                }
            }
            if (!operationQueued) {
                lambda$onUserUnlocked$2$CarServiceHelperService(user);
            }
        }
        sendUserLifecycleEvent(4, user);
    }

    public void onUserStarting(SystemService.TargetUser user) {
        if (isPreCreated(user, 1)) {
            return;
        }
        EventLog.writeEvent(150002, user.getUserIdentifier());
        Slog.d(TAG, "onUserStarting(" + user + ")");
        sendUserLifecycleEvent(1, user);
    }

    public void onUserStopping(SystemService.TargetUser user) {
        if (isPreCreated(user, 5)) {
            return;
        }
        EventLog.writeEvent(150006, user.getUserIdentifier());
        Slog.d(TAG, "onUserStopping(" + user + ")");
        sendUserLifecycleEvent(5, user);
        int userId = user.getUserIdentifier();
        this.mCarLaunchParamsModifier.handleUserStopped(userId);
    }

    public void onUserStopped(SystemService.TargetUser user) {
        if (isPreCreated(user, 6)) {
            return;
        }
        EventLog.writeEvent(150007, user.getUserIdentifier());
        Slog.d(TAG, "onUserStopped(" + user + ")");
        sendUserLifecycleEvent(6, user);
    }

    public void onUserSwitching(SystemService.TargetUser from, SystemService.TargetUser to) {
        if (isPreCreated(to, 2)) {
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(from == null ? -10000 : from.getUserIdentifier());
        objArr[1] = Integer.valueOf(to.getUserIdentifier());
        EventLog.writeEvent(150003, objArr);
        Slog.d(TAG, "onUserSwitching(" + from + ">>" + to + ")");
        sendUserLifecycleEvent(2, from, to);
        int userId = to.getUserIdentifier();
        this.mCarLaunchParamsModifier.handleCurrentUserSwitching(userId);
    }

    void loadNativeLibrary() {
        System.loadLibrary("car-framework-service-jni");
    }

    private boolean isPreCreated(SystemService.TargetUser user, int eventType) {
        UserInfo userInfo = user.getUserInfo();
        if (userInfo == null) {
            Slog.wtf(TAG, "no UserInfo on " + user + " on eventType " + eventType);
            return false;
        }
        if (!userInfo.preCreated) {
            return false;
        }
        Slog.d(TAG, "Ignoring event of type " + eventType + " for pre-created user " + userInfo.toFullString());
        return true;
    }

    private void queueOperationLocked(Runnable operation) {
        if (this.mPendingOperations == null) {
            this.mPendingOperations = new ArrayList<>(5);
        }
        this.mPendingOperations.add(operation);
    }

    private void checkForCarServiceConnection(TimingsTraceAndSlog t) {
        synchronized (this.mLock) {
            if (this.mCarService != null) {
                return;
            }
            t.traceBegin("checkForCarServiceConnection");
            IBinder iBinder = ServiceManager.checkService("car_service");
            if (iBinder != null) {
                Slog.d(TAG, "Car service found through ServiceManager:" + iBinder);
                handleCarServiceConnection(iBinder);
            }
            t.traceEnd();
        }
    }

    int getHalResponseTime() {
        return this.mHalResponseTime;
    }

    void setInitialHalResponseTime() {
        this.mHalResponseTime = -((int) SystemClock.uptimeMillis());
    }

    void setFinalHalResponseTime() {
        this.mHalResponseTime += (int) SystemClock.uptimeMillis();
    }

    void handleCarServiceConnection(IBinder iBinder) {
        boolean user0IsCurrent;
        SparseIntArray lastUserLifecycle = null;
        synchronized (this.mLock) {
            if (this.mCarService == iBinder) {
                return;
            }
            Slog.i(TAG, "car service binder changed, was:" + this.mCarService + " new:" + iBinder);
            this.mCarService = iBinder;
            boolean carServiceHasCrashed = this.mCarServiceHasCrashed;
            this.mCarServiceHasCrashed = false;
            int lastSwitchedUser = this.mLastSwitchedUser;
            ArrayList<Runnable> pendingOperations = this.mPendingOperations;
            this.mPendingOperations = null;
            if (carServiceHasCrashed) {
                lastUserLifecycle = this.mLastUserLifecycle.clone();
            }
            int numberOperations = pendingOperations == null ? 0 : pendingOperations.size();
            EventLog.writeEvent(150008, numberOperations);
            Slog.i(TAG, "**CarService connected**");
            sendSetCarServiceHelperBinderCall();
            if (carServiceHasCrashed) {
                int numUsers = lastUserLifecycle.size();
                TimingsTraceAndSlog t = newTimingsTraceAndSlog();
                t.traceBegin("send-uses-after-reconnect-" + numUsers);
                int user0Lifecycle = lastUserLifecycle.get(0, 1);
                lastUserLifecycle.delete(0);
                if (lastSwitchedUser != 0) {
                    user0IsCurrent = false;
                } else {
                    user0IsCurrent = true;
                }
                sendAllLifecyleToUser(0, user0Lifecycle, user0IsCurrent);
                if (!user0IsCurrent) {
                    int currentUserLifecycle = lastUserLifecycle.get(lastSwitchedUser, 1);
                    lastUserLifecycle.delete(lastSwitchedUser);
                    sendAllLifecyleToUser(lastSwitchedUser, currentUserLifecycle, true);
                }
                for (int i = 0; i < lastUserLifecycle.size(); i++) {
                    int userId = lastUserLifecycle.keyAt(i);
                    int lifecycle = lastUserLifecycle.valueAt(i);
                    sendAllLifecyleToUser(userId, lifecycle, false);
                }
                t.traceEnd();
                return;
            }
            if (pendingOperations != null) {
                Slog.d(TAG, "Running " + numberOperations + " pending operations");
                TimingsTraceAndSlog t2 = newTimingsTraceAndSlog();
                t2.traceBegin("send-pending-ops-" + numberOperations);
                for (int i2 = 0; i2 < numberOperations; i2++) {
                    Runnable operation = pendingOperations.get(i2);
                    try {
                        operation.run();
                    } catch (RuntimeException e) {
                        Slog.w(TAG, "exception running operation #" + i2 + ": " + e);
                    }
                }
                t2.traceEnd();
            }
        }
    }

    private void sendAllLifecyleToUser(int userId, int lifecycle, boolean isCurrentUser) {
        Slog.d(TAG, "sendAllLifecyleToUser, user:" + userId + " lifecycle:" + lifecycle);
        if (lifecycle >= 1) {
            sendUserLifecycleEvent(1, 0L, -10000, userId);
        }
        if (isCurrentUser && userId != 0) {
            sendUserLifecycleEvent(2, 0L, 0, userId);
        }
        if (lifecycle >= 3) {
            sendUserLifecycleEvent(3, 0L, -10000, userId);
        }
        if (lifecycle >= 4) {
            sendUserLifecycleEvent(4, 0L, -10000, userId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimingsTraceAndSlog newTimingsTraceAndSlog() {
        return new TimingsTraceAndSlog(TAG, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
    }

    private void setupAndStartUsers(TimingsTraceAndSlog t) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(DevicePolicyManager.class);
        if (devicePolicyManager != null && devicePolicyManager.getUserProvisioningState() != 0) {
            Slog.i(TAG, "DevicePolicyManager active, skip user unlock/switch");
            return;
        }
        t.traceBegin("setupAndStartUsers");
        if (this.mHalEnabled) {
            Slog.i(TAG, "Delegating initial switching to HAL");
            setupAndStartUsersUsingHal();
        } else {
            setupAndStartUsersDirectly(t, null);
        }
        t.traceEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleHalTimedout() {
        synchronized (this.mLock) {
            if (this.mInitialized) {
                return;
            }
            Slog.w(TAG, "HAL didn't respond in " + this.mHalTimeoutMs + "ms; using default behavior");
            setupAndStartUsersDirectly();
        }
    }

    private void setupAndStartUsersUsingHal() {
        this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$6b7XnI9ijXAQL-uVdytDYlLT1Dk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((CarServiceHelperService) obj).handleHalTimedout();
            }
        }, this).setWhat(1), this.mHalTimeoutMs);
        IResultReceiver.Stub stub = new IResultReceiver.Stub() { // from class: com.android.internal.car.CarServiceHelperService.3
            public void send(int resultCode, Bundle resultData) {
                EventLog.writeEvent(150010, resultCode);
                CarServiceHelperService.this.setFinalHalResponseTime();
                Slog.d(CarServiceHelperService.TAG, "Got result from HAL (" + UserHalHelper.halCallbackStatusToString(resultCode) + ") in " + TimeUtils.formatDuration(CarServiceHelperService.this.mHalResponseTime));
                CarServiceHelperService.this.mHandler.removeMessages(1);
                synchronized (CarServiceHelperService.this.mLock) {
                    if (CarServiceHelperService.this.mInitialized) {
                        Slog.w(CarServiceHelperService.TAG, "Result from HAL came too late, ignoring: " + UserHalHelper.halCallbackStatusToString(resultCode));
                        return;
                    }
                    if (resultCode != 1) {
                        Slog.w(CarServiceHelperService.TAG, "Service returned non-ok status (" + UserHalHelper.halCallbackStatusToString(resultCode) + "); using default behavior");
                        CarServiceHelperService.this.fallbackToDefaultInitialUserBehavior();
                        return;
                    }
                    if (resultData == null) {
                        Slog.w(CarServiceHelperService.TAG, "Service returned null bundle");
                        CarServiceHelperService.this.fallbackToDefaultInitialUserBehavior();
                        return;
                    }
                    int action = resultData.getInt("initial_info.action", 0);
                    String userLocales = resultData.getString("user.locales");
                    if (userLocales != null) {
                        Slog.i(CarServiceHelperService.TAG, "Changing user locales to " + userLocales);
                    }
                    if (action == 0) {
                        EventLog.writeEvent(150011, 0, userLocales);
                        Slog.d(CarServiceHelperService.TAG, "User HAL returned DEFAULT behavior");
                        CarServiceHelperService carServiceHelperService = CarServiceHelperService.this;
                        carServiceHelperService.setupAndStartUsersDirectly(carServiceHelperService.newTimingsTraceAndSlog(), userLocales);
                        return;
                    }
                    if (action != 1) {
                        if (action == 2) {
                            String name = resultData.getString("user.name");
                            int flags = resultData.getInt("user.flags");
                            CarServiceHelperService.this.createUserByHalRequest(name, userLocales, flags);
                            return;
                        } else {
                            Slog.w(CarServiceHelperService.TAG, "Invalid InitialUserInfoResponseAction action: " + action);
                            CarServiceHelperService.this.fallbackToDefaultInitialUserBehavior();
                            return;
                        }
                    }
                    int userId = resultData.getInt("user.id");
                    CarServiceHelperService.this.startUserByHalRequest(userId, userLocales);
                }
            }
        };
        int initialUserInfoRequestType = getInitialUserInfoRequestType();
        EventLog.writeEvent(150009, initialUserInfoRequestType);
        setInitialHalResponseTime();
        sendOrQueueGetInitialUserInfo(initialUserInfoRequestType, stub);
    }

    int getInitialUserInfoRequestType() {
        if (!this.mCarUserManagerHelper.hasInitialUser()) {
            return 1;
        }
        if (this.mContext.getPackageManager().isDeviceUpgrading()) {
            return 2;
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUserByHalRequest(int userId, String userLocales) {
        if (userId <= 0) {
            Slog.w(TAG, "invalid (or missing) user id sent by HAL: " + userId);
            fallbackToDefaultInitialUserBehavior();
            return;
        }
        EventLog.writeEvent(150012, Integer.valueOf(userId), userLocales);
        Slog.d(TAG, "Starting user " + userId + " as requested by HAL");
        this.mInitialUserSetter.set(newInitialUserInfoBuilder(1).setUserLocales(userLocales).setSwitchUserId(userId).build());
    }

    private InitialUserSetter.Builder newInitialUserInfoBuilder(int type) {
        return new InitialUserSetter.Builder(type).setSupportsOverrideUserIdProperty(!((Boolean) CarProperties.user_hal_enabled().orElse(false)).booleanValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createUserByHalRequest(String name, String userLocales, int halFlags) {
        String friendlyName = "user with name '" + UserHelper.safeName(name) + "', locales " + userLocales + ", and flags " + UserHalHelper.userFlagsToString(halFlags);
        EventLog.writeEvent(150013, Integer.valueOf(halFlags), UserHelper.safeName(name), userLocales);
        Slog.d(TAG, "HAL request creation of " + friendlyName);
        this.mInitialUserSetter.set(newInitialUserInfoBuilder(2).setUserLocales(userLocales).setNewUserName(name).setNewUserFlags(halFlags).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fallbackToDefaultInitialUserBehavior() {
        EventLog.writeEvent(150011, 1);
        Slog.d(TAG, "Falling back to DEFAULT initial user behavior");
        setupAndStartUsersDirectly();
    }

    private void setupAndStartUsersDirectly() {
        setupAndStartUsersDirectly(newTimingsTraceAndSlog(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupAndStartUsersDirectly(TimingsTraceAndSlog t, String userLocales) {
        synchronized (this.mLock) {
            if (this.mInitialized) {
                Slog.wtf(TAG, "Already initialized", new Exception());
            } else {
                this.mInitialized = true;
                this.mInitialUserSetter.set(newInitialUserInfoBuilder(0).setUserLocales(userLocales).build());
            }
        }
    }

    void managePreCreatedUsers() {
        int numberRequestedGuests = ((Integer) CarProperties.number_pre_created_guests().orElse(0)).intValue();
        int numberRequestedUsers = ((Integer) CarProperties.number_pre_created_users().orElse(0)).intValue();
        EventLog.writeEvent(150014, Integer.valueOf(numberRequestedUsers), Integer.valueOf(numberRequestedGuests));
        Slog.d(TAG, "managePreCreatedUsers(): OEM asked for " + numberRequestedGuests + " guests and " + numberRequestedUsers + " users");
        if (numberRequestedGuests < 0 || numberRequestedUsers < 0) {
            Slog.w(TAG, "preCreateUsers(): invalid values provided by OEM; number_pre_created_guests=" + numberRequestedGuests + ", number_pre_created_users=" + numberRequestedUsers);
            return;
        }
        List<UserInfo> allUsers = this.mUserManager.getUsers(true, true, false);
        int allUsersSize = allUsers.size();
        Slog.d(TAG, "preCreateUsers: total users size is " + allUsersSize);
        final SparseBooleanArray invalidPreCreatedUsers = new SparseBooleanArray();
        SparseBooleanArray existingPrecreatedUsers = new SparseBooleanArray();
        final List<Integer> extraPreCreatedUsers = new ArrayList<>();
        int numberExistingGuests = 0;
        int numberExistingUsers = 0;
        for (int numberExistingGuests2 = 0; numberExistingGuests2 < allUsersSize; numberExistingGuests2++) {
            UserInfo user = allUsers.get(numberExistingGuests2);
            if (user.preCreated) {
                if (!user.isInitialized()) {
                    Slog.w(TAG, "Found invalid pre-created user that needs to be removed: " + user.toFullString());
                    invalidPreCreatedUsers.append(user.id, true);
                } else {
                    boolean isGuest = user.isGuest();
                    existingPrecreatedUsers.put(user.id, isGuest);
                    if (isGuest) {
                        numberExistingGuests++;
                        if (numberExistingGuests > numberRequestedGuests) {
                            extraPreCreatedUsers.add(Integer.valueOf(user.id));
                        }
                    } else {
                        numberExistingUsers++;
                        if (numberExistingUsers > numberRequestedUsers) {
                            extraPreCreatedUsers.add(Integer.valueOf(user.id));
                        }
                    }
                }
            }
        }
        Slog.d(TAG, "managePreCreatedUsers(): system already has " + numberExistingGuests + " pre-created guests," + numberExistingUsers + " pre-created users, and these invalid users: " + invalidPreCreatedUsers + " extra pre-created users: " + extraPreCreatedUsers);
        final int numberGuestsToAdd = numberRequestedGuests - numberExistingGuests;
        final int numberUsersToAdd = numberRequestedUsers - numberExistingUsers;
        int numberGuestsToRemove = numberExistingGuests - numberRequestedGuests;
        int numberUsersToRemove = numberExistingUsers - numberRequestedUsers;
        final int numberInvalidUsersToRemove = invalidPreCreatedUsers.size();
        EventLog.writeEvent(150015, Integer.valueOf(numberExistingUsers), Integer.valueOf(numberUsersToAdd), Integer.valueOf(numberUsersToRemove), Integer.valueOf(numberExistingGuests), Integer.valueOf(numberGuestsToAdd), Integer.valueOf(numberGuestsToRemove), Integer.valueOf(numberInvalidUsersToRemove));
        if (numberGuestsToAdd != 0 || numberUsersToAdd != 0 || numberInvalidUsersToRemove != 0) {
            runAsync(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$31FoCEI_StksFX0Y_YuG5pUyczw
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$managePreCreatedUsers$3$CarServiceHelperService(numberUsersToAdd, numberGuestsToAdd, extraPreCreatedUsers, numberInvalidUsersToRemove, invalidPreCreatedUsers);
                }
            });
        } else {
            Slog.d(TAG, "managePreCreatedUsers(): everything in sync");
        }
    }

    public /* synthetic */ void lambda$managePreCreatedUsers$3$CarServiceHelperService(int numberUsersToAdd, int numberGuestsToAdd, List extraPreCreatedUsers, int numberInvalidUsersToRemove, SparseBooleanArray invalidPreCreatedUsers) {
        TimingsTraceAndSlog t = new TimingsTraceAndSlog("CarServiceHelperAsync", PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
        t.traceBegin("preCreateUsers");
        if (numberUsersToAdd > 0) {
            preCreateUsers(t, numberUsersToAdd, false);
        }
        if (numberGuestsToAdd > 0) {
            preCreateUsers(t, numberGuestsToAdd, true);
        }
        int totalNumberToRemove = extraPreCreatedUsers.size();
        Slog.d(TAG, "Must delete " + totalNumberToRemove + " pre-created users");
        if (totalNumberToRemove > 0) {
            int[] usersToRemove = new int[totalNumberToRemove];
            for (int i = 0; i < totalNumberToRemove; i++) {
                usersToRemove[i] = ((Integer) extraPreCreatedUsers.get(i)).intValue();
            }
            removePreCreatedUsers(usersToRemove);
        }
        t.traceEnd();
        if (numberInvalidUsersToRemove > 0) {
            t.traceBegin("removeInvalidPreCreatedUsers");
            for (int i2 = 0; i2 < numberInvalidUsersToRemove; i2++) {
                int userId = invalidPreCreatedUsers.keyAt(i2);
                Slog.i(TAG, "removing invalid pre-created user " + userId);
                this.mUserManager.removeUser(userId);
            }
            t.traceEnd();
        }
    }

    private void preCreateUsers(TimingsTraceAndSlog t, int size, boolean isGuest) {
        StringBuilder sb;
        String str;
        if (isGuest) {
            sb = new StringBuilder();
            str = "preCreateGuests-";
        } else {
            sb = new StringBuilder();
            str = "preCreateUsers-";
        }
        sb.append(str);
        sb.append(size);
        String msg = sb.toString();
        Slog.d(TAG, "preCreateUsers: " + msg);
        t.traceBegin(msg);
        for (int i = 1; i <= size; i++) {
            UserInfo preCreated = preCreateUsers(t, isGuest);
            if (preCreated == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Could not pre-create");
                sb2.append(isGuest ? " guest" : "");
                sb2.append(" user #");
                sb2.append(i);
                Slog.w(TAG, sb2.toString());
            }
        }
        t.traceEnd();
    }

    void runAsync(final Runnable r) {
        final String threadName = "CarServiceHelper.AsyncTask";
        Slog.i(TAG, "Starting thread CarServiceHelper.AsyncTask");
        new Thread(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$8Ue_WHBsKdiUtjiEyZcXa0JcXZc
            @Override // java.lang.Runnable
            public final void run() {
                CarServiceHelperService.lambda$runAsync$4(r, threadName);
            }
        }, "CarServiceHelper.AsyncTask").start();
    }

    static /* synthetic */ void lambda$runAsync$4(Runnable r, String threadName) {
        try {
            r.run();
            Slog.i(TAG, "Finishing thread " + threadName);
        } catch (RuntimeException e) {
            Slog.e(TAG, "runAsync() failed", e);
            throw e;
        }
    }

    public UserInfo preCreateUsers(TimingsTraceAndSlog t, boolean isGuest) {
        StringBuilder sb = new StringBuilder();
        sb.append("pre-create");
        sb.append(isGuest ? "-guest" : "-user");
        String traceMsg = sb.toString();
        t.traceBegin(traceMsg);
        String userType = isGuest ? "android.os.usertype.full.GUEST" : "android.os.usertype.full.SECONDARY";
        UserInfo user = null;
        try {
            try {
                user = this.mUserManager.preCreateUser(userType);
                if (user == null) {
                    logPrecreationFailure(traceMsg, null);
                }
            } catch (Exception e) {
                logPrecreationFailure(traceMsg, e);
            }
            return user;
        } finally {
            t.traceEnd();
        }
    }

    private void removePreCreatedUsers(int[] usersToRemove) {
        for (int userId : usersToRemove) {
            Slog.i(TAG, "removing pre-created user with id " + userId);
            this.mUserManager.removeUser(userId);
        }
    }

    void logPrecreationFailure(String operation, Exception cause) {
        int maxNumberUsers = UserManager.getMaxSupportedUsers();
        int currentNumberUsers = this.mUserManager.getUserCount();
        StringBuilder sb = new StringBuilder(operation.length() + 100);
        sb.append(operation);
        sb.append(" failed. Number users: ");
        sb.append(currentNumberUsers);
        sb.append(" Max: ");
        StringBuilder message = sb.append(maxNumberUsers);
        if (cause == null) {
            Slog.w(TAG, message.toString());
        } else {
            Slog.w(TAG, message.toString(), cause);
        }
    }

    private void sendSetCarServiceHelperBinderCall() {
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.car.ICar");
        data.writeStrongBinder(this.mHelper.asBinder());
        sendBinderCallToCarService(data, 0);
    }

    private void sendUserLifecycleEvent(int eventType, SystemService.TargetUser user) {
        sendUserLifecycleEvent(eventType, null, user);
    }

    private void sendUserLifecycleEvent(final int eventType, final SystemService.TargetUser from, final SystemService.TargetUser to) {
        final long now = System.currentTimeMillis();
        synchronized (this.mLock) {
            try {
                if (eventType == 2) {
                    this.mLastSwitchedUser = to.getUserIdentifier();
                } else if (eventType == 5 || eventType == 6) {
                    this.mLastUserLifecycle.delete(to.getUserIdentifier());
                } else {
                    this.mLastUserLifecycle.put(to.getUserIdentifier(), eventType);
                }
                if (this.mCarService == null) {
                    Slog.d(TAG, "Queuing lifecycle event " + eventType + " for user " + to);
                    queueOperationLocked(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$PpEljc_cDjlB6IzP4uBSBlE3mHQ
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$sendUserLifecycleEvent$5$CarServiceHelperService(eventType, now, from, to);
                        }
                    });
                    return;
                }
                TimingsTraceAndSlog t = newTimingsTraceAndSlog();
                t.traceBegin("send-lifecycle-" + eventType + "-" + to.getUserIdentifier());
                lambda$sendUserLifecycleEvent$5$CarServiceHelperService(eventType, now, from, to);
                t.traceEnd();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendUserLifecycleEvent, reason: merged with bridge method [inline-methods] */
    public void lambda$sendUserLifecycleEvent$5$CarServiceHelperService(int eventType, long timestamp, SystemService.TargetUser from, SystemService.TargetUser to) {
        int fromId = from == null ? -10000 : from.getUserIdentifier();
        int toId = to.getUserIdentifier();
        sendUserLifecycleEvent(eventType, timestamp, fromId, toId);
    }

    private void sendUserLifecycleEvent(int eventType, long timestamp, int fromId, int toId) {
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.car.ICar");
        data.writeInt(eventType);
        data.writeLong(timestamp);
        data.writeInt(fromId);
        data.writeInt(toId);
        sendBinderCallToCarService(data, 1);
    }

    private void sendOrQueueGetInitialUserInfo(final int requestType, final IResultReceiver receiver) {
        synchronized (this.mLock) {
            if (this.mCarService == null) {
                Slog.d(TAG, "Queuing GetInitialUserInfo call for type " + requestType);
                queueOperationLocked(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$cu44L5wMIsMi7gYXytqybwpLmio
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$sendOrQueueGetInitialUserInfo$6$CarServiceHelperService(requestType, receiver);
                    }
                });
                return;
            }
            lambda$sendOrQueueGetInitialUserInfo$6$CarServiceHelperService(requestType, receiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendGetInitialUserInfo, reason: merged with bridge method [inline-methods] */
    public void lambda$sendOrQueueGetInitialUserInfo$6$CarServiceHelperService(int requestType, IResultReceiver receiver) {
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.car.ICar");
        data.writeInt(requestType);
        data.writeInt(this.mHalTimeoutMs);
        data.writeStrongBinder(receiver.asBinder());
        sendBinderCallToCarService(data, 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX INFO: renamed from: setInitialUser, reason: merged with bridge method [inline-methods] */
    public void lambda$new$1$CarServiceHelperService(final UserInfo user) {
        synchronized (this.mLock) {
            if (this.mCarService == null) {
                Slog.d(TAG, "Queuing setInitialUser() call");
                queueOperationLocked(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$PnGgHf7cCrhOav_AhO-MKfxIZ08
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setInitialUser$7$CarServiceHelperService(user);
                    }
                });
            } else {
                lambda$setInitialUser$7$CarServiceHelperService(user);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendSetInitialUser, reason: merged with bridge method [inline-methods] */
    public void lambda$setInitialUser$7$CarServiceHelperService(UserInfo user) {
        Slog.d(TAG, "sendSetInitialUser(): " + user);
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.car.ICar");
        data.writeInt(user != null ? user.id : -10000);
        sendBinderCallToCarService(data, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendFirstUserUnlocked, reason: merged with bridge method [inline-methods] */
    public void lambda$onUserUnlocked$2$CarServiceHelperService(SystemService.TargetUser user) {
        long now = System.currentTimeMillis();
        Parcel data = Parcel.obtain();
        data.writeInterfaceToken("android.car.ICar");
        data.writeInt(user.getUserIdentifier());
        data.writeLong(now);
        data.writeLong(this.mFirstUnlockedUserDuration);
        data.writeInt(this.mHalResponseTime);
        sendBinderCallToCarService(data, 2);
    }

    private void sendBinderCallToCarService(Parcel data, int callNumber) {
        IBinder carService;
        synchronized (this.mLock) {
            carService = this.mCarService;
        }
        if (carService == null) {
            Slog.w(TAG, "Not calling txn " + callNumber + " because service is not bound yet", new Exception());
            return;
        }
        int code = callNumber + 1;
        try {
            try {
                Slog.v(TAG, "calling one-way binder transaction with code " + code);
                carService.transact(code, data, null, 1);
                Slog.v(TAG, "finished one-way binder transaction with code " + code);
            } catch (RemoteException e) {
                Slog.w(TAG, "RemoteException from car service", e);
                handleCarServiceCrash();
            } catch (RuntimeException e2) {
                Slog.wtf(TAG, "Exception calling binder transaction " + callNumber + " (real code: " + code + ")", e2);
                throw e2;
            }
        } finally {
            data.recycle();
        }
    }

    private static ArrayList<Integer> getInterestingHalPids() {
        try {
            IServiceManager serviceManager = IServiceManager.getService();
            ArrayList<IServiceManager.InstanceDebugInfo> dump = serviceManager.debugDump();
            HashSet<Integer> pids = new HashSet<>();
            for (IServiceManager.InstanceDebugInfo info : dump) {
                if (info.pid != -1) {
                    if (Watchdog.HAL_INTERFACES_OF_INTEREST.contains(info.interfaceName) || CAR_HAL_INTERFACES_OF_INTEREST.contains(info.interfaceName)) {
                        pids.add(Integer.valueOf(info.pid));
                    }
                }
            }
            return new ArrayList<>(pids);
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ArrayList<Integer> getInterestingNativePids() {
        ArrayList<Integer> pids = getInterestingHalPids();
        int[] nativePids = Process.getPidsForCommands(Watchdog.NATIVE_STACKS_OF_INTEREST);
        if (nativePids != null) {
            pids.ensureCapacity(pids.size() + nativePids.length);
            for (int i : nativePids) {
                pids.add(Integer.valueOf(i));
            }
        }
        return pids;
    }

    private static void dumpServiceStacks() {
        ArrayList<Integer> pids = new ArrayList<>();
        pids.add(Integer.valueOf(Process.myPid()));
        ActivityManagerService.dumpStackTraces(pids, (ProcessCpuTracker) null, (SparseArray) null, getInterestingNativePids(), (StringWriter) null);
    }

    void handleCarServiceCrash() {
        boolean restartOnServiceCrash = SystemProperties.getBoolean(PROP_RESTART_RUNTIME, false);
        if (restartOnServiceCrash) {
            Slog.w(TAG, "*** CARHELPER KILLING SYSTEM PROCESS: CarService crash");
            Slog.w(TAG, "*** GOODBYE!");
            Process.killProcess(Process.myPid());
            System.exit(10);
        } else {
            Slog.w(TAG, "*** CARHELPER ignoring: CarService crash");
        }
        synchronized (this.mLock) {
            this.mCarServiceHasCrashed = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleClientsNotResponding(int[] pids) {
        this.mProcessTerminator.requestTerminateProcess(pids);
    }

    private void registerMonitorToWatchdogDaemon() {
        try {
            this.mCarWatchdogDaemonHelper.registerMonitor(this.mCarWatchdogMonitor);
        } catch (RemoteException | RuntimeException e) {
            Slog.w(TAG, "Cannot register to car watchdog daemon: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void killProcessAndReportToMonitor(int pid) {
        String processName = getProcessName(pid);
        Process.killProcess(pid);
        Slog.w(TAG, "carwatchdog killed " + processName + " (pid: " + pid + ")");
        try {
            this.mCarWatchdogDaemonHelper.tellDumpFinished(this.mCarWatchdogMonitor, pid);
        } catch (RemoteException | RuntimeException e) {
            Slog.w(TAG, "Cannot report monitor result to car watchdog daemon: " + e);
        }
    }

    private static String getProcessName(int pid) {
        String filename = "/proc/" + pid + "/cmdline";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            try {
                String line = reader.readLine().replace((char) 0, ' ').trim();
                int index = line.indexOf(32);
                if (index != -1) {
                    line = line.substring(0, index);
                }
                String string = Paths.get(line, new String[0]).getFileName().toString();
                reader.close();
                return string;
            } finally {
            }
        } catch (IOException e) {
            Slog.w(TAG, "Cannot read " + filename);
            return "unknown process";
        }
    }

    private class ICarServiceHelperImpl extends ICarServiceHelper.Stub {
        private ICarServiceHelperImpl() {
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public int forceSuspend(int timeoutMs) {
            CarServiceHelperService.this.mContext.enforceCallingOrSelfPermission("android.permission.DEVICE_POWER", null);
            long ident = Binder.clearCallingIdentity();
            try {
                int retVal = CarServiceHelperService.nativeForceSuspend(timeoutMs);
                return retVal;
            } finally {
                Binder.restoreCallingIdentity(ident);
            }
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setDisplayWhitelistForUser(int userId, int[] displayIds) {
            CarServiceHelperService.this.mCarLaunchParamsModifier.setDisplayWhitelistForUser(userId, displayIds);
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setPassengerDisplays(int[] displayIdsForPassenger) {
            CarServiceHelperService.this.mCarLaunchParamsModifier.setPassengerDisplays(displayIdsForPassenger);
        }

        @Override // com.android.internal.car.ICarServiceHelper
        public void setSourcePreferredComponents(boolean enableSourcePreferred, List<ComponentName> sourcePreferredComponents) {
            CarServiceHelperService.this.mCarLaunchParamsModifier.setSourcePreferredComponents(enableSourcePreferred, sourcePreferredComponents);
        }
    }

    private class ICarWatchdogMonitorImpl extends ICarWatchdogMonitor.Stub {
        private final WeakReference<CarServiceHelperService> mService;

        private ICarWatchdogMonitorImpl(CarServiceHelperService service) {
            this.mService = new WeakReference<>(service);
        }

        @Override // android.automotive.watchdog.ICarWatchdogMonitor
        public void onClientsNotResponding(int[] pids) {
            CarServiceHelperService service = this.mService.get();
            if (service != null && pids != null && pids.length != 0) {
                service.handleClientsNotResponding(pids);
            }
        }

        @Override // android.automotive.watchdog.ICarWatchdogMonitor
        public int getInterfaceVersion() {
            return 2;
        }

        @Override // android.automotive.watchdog.ICarWatchdogMonitor
        public String getInterfaceHash() {
            return "f7adf2ef96b380c7fde3919f565eb764986bdcdd";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class ProcessTerminator {
        private static final long ONE_SECOND_MS = 1000;
        private ExecutorService mExecutor;
        private final Object mProcessLock;
        private int mQueuedTask;

        private ProcessTerminator() {
            this.mProcessLock = new Object();
        }

        public void requestTerminateProcess(final int[] pids) {
            synchronized (this.mProcessLock) {
                if (this.mExecutor == null) {
                    this.mExecutor = Executors.newSingleThreadExecutor();
                }
                this.mQueuedTask++;
            }
            this.mExecutor.execute(new Runnable() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$ProcessTerminator$FxuWO2vKWYmiccyxN3CnlqdJLQg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$requestTerminateProcess$0$CarServiceHelperService$ProcessTerminator(pids);
                }
            });
        }

        public /* synthetic */ void lambda$requestTerminateProcess$0$CarServiceHelperService$ProcessTerminator(int[] pids) {
            for (int pid : pids) {
                dumpAndKillProcess(pid);
            }
            CarServiceHelperService.this.mHandler.sendMessage(PooledLambda.obtainMessage(new Consumer() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$ProcessTerminator$2Q8oH9YYmykFmFAjr2TNflPJpGg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((CarServiceHelperService.ProcessTerminator) obj).postProcessing();
                }
            }, this).setWhat(2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void postProcessing() {
            synchronized (this.mProcessLock) {
                int i = this.mQueuedTask - 1;
                this.mQueuedTask = i;
                if (i == 0) {
                    this.mExecutor.shutdown();
                    this.mExecutor = null;
                }
            }
        }

        private void dumpAndKillProcess(int pid) {
            Slog.d(CarServiceHelperService.TAG, "Dumping and killing process(pid: " + pid + ")");
            ArrayList<Integer> javaPids = new ArrayList<>(1);
            ArrayList<Integer> nativePids = new ArrayList<>();
            try {
                if (isJavaApp(pid)) {
                    javaPids.add(Integer.valueOf(pid));
                } else {
                    nativePids.add(Integer.valueOf(pid));
                }
                nativePids.addAll(CarServiceHelperService.getInterestingNativePids());
                long startDumpTime = SystemClock.uptimeMillis();
                ActivityManagerService.dumpStackTraces(javaPids, (ProcessCpuTracker) null, (SparseArray) null, nativePids, (StringWriter) null);
                long dumpTime = SystemClock.uptimeMillis() - startDumpTime;
                Slog.d(CarServiceHelperService.TAG, "Dumping process took " + dumpTime + "ms");
                if (dumpTime >= ONE_SECOND_MS) {
                    CarServiceHelperService.this.killProcessAndReportToMonitor(pid);
                } else {
                    CarServiceHelperService.this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.internal.car.-$$Lambda$CarServiceHelperService$ProcessTerminator$3Rvep0464zkGO-RKeFXDjBEy9mw
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ((CarServiceHelperService) obj).killProcessAndReportToMonitor(((Integer) obj2).intValue());
                        }
                    }, CarServiceHelperService.this, Integer.valueOf(pid)).setWhat(3), ONE_SECOND_MS - dumpTime);
                }
            } catch (IOException e) {
                Slog.w(CarServiceHelperService.TAG, "Cannot get process information: " + e);
            }
        }

        private boolean isJavaApp(int pid) throws IOException {
            Path exePath = new File("/proc/" + pid + "/exe").toPath();
            String target = Files.readSymbolicLink(exePath).toString();
            return target == "/system/bin/app_process32" || target == "/system/bin/app_process64";
        }
    }
}
