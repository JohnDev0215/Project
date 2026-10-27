package com.android.car.user;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IStopUserCallback;
import android.car.CarOccupantZoneManager;
import android.car.ICarUserService;
import android.car.encryptionrunner.DummyEncryptionRunner;
import android.car.user.CarUserManager;
import android.car.user.UserCreationResult;
import android.car.user.UserIdentificationAssociationResponse;
import android.car.user.UserRemovalResult;
import android.car.user.UserSwitchResult;
import android.car.userlib.CarUserManagerHelper;
import android.car.userlib.HalCallback;
import android.car.userlib.UserHalHelper;
import android.car.userlib.UserHelper;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.hardware.automotive.vehicle.V2_0.CreateUserRequest;
import android.hardware.automotive.vehicle.V2_0.CreateUserResponse;
import android.hardware.automotive.vehicle.V2_0.InitialUserInfoResponse;
import android.hardware.automotive.vehicle.V2_0.RemoveUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserRequest;
import android.hardware.automotive.vehicle.V2_0.SwitchUserResponse;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationGetRequest;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationResponse;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetAssociation;
import android.hardware.automotive.vehicle.V2_0.UserIdentificationSetRequest;
import android.hardware.automotive.vehicle.V2_0.UsersInfo;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IProgressListener;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.support.v4.media.session.PlaybackStateCompat;
import android.sysprop.CarProperties;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.util.SparseArray;
import android.util.TimingsTraceLog;
import com.android.car.CarServiceBase;
import com.android.car.CarServiceUtils;
import com.android.car.R;
import com.android.car.hal.UserHalService;
import com.android.internal.infra.AndroidFuture;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.util.UserIcons;
import com.android.wifitrackerlib.WifiEntry;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class CarUserService extends ICarUserService.Stub implements CarServiceBase {
    public static final String BUNDLE_INITIAL_INFO_ACTION = "initial_info.action";
    public static final String BUNDLE_USER_FLAGS = "user.flags";
    public static final String BUNDLE_USER_ID = "user.id";
    public static final String BUNDLE_USER_LOCALES = "user.locales";
    public static final String BUNDLE_USER_NAME = "user.name";
    private static final String TAG = "CAR.USER";
    public static final String VEHICLE_HAL_NOT_SUPPORTED = "Vehicle Hal not supported.";
    private final IActivityManager mAm;
    private final SparseArray<IResultReceiver> mAppLifecycleListeners;
    private final ArrayList<Integer> mBackgroundUsersRestartedHere;
    private final ArrayList<Integer> mBackgroundUsersToRestart;
    private final CarUserManagerHelper mCarUserManagerHelper;
    private final Context mContext;
    private final boolean mEnablePassengerSupport;
    private final UserHalService mHal;
    private final int mHalTimeoutMs;
    private final Handler mHandler;
    private final HandlerThread mHandlerThread;
    private UserInfo mInitialUser;
    private int mLastPassengerId;
    private final Object mLockHelper;
    private final Object mLockUser;
    private final int mMaxRunningUsers;
    private final CopyOnWriteArrayList<PassengerCallback> mPassengerCallbacks;
    private int mRequestIdForUserSwitchInProcess;
    private final ArrayList<Runnable> mUser0UnlockTasks;
    private boolean mUser0Unlocked;
    private int mUserIdForUserSwitchInProcess;
    private final List<CarUserManager.UserLifecycleListener> mUserLifecycleListeners;
    private final UserManager mUserManager;
    private UserMetrics mUserMetrics;
    private IResultReceiver mUserSwitchUiReceiver;
    private ZoneUserBindingHelper mZoneUserBindingHelper;

    public interface PassengerCallback {
        void onPassengerStarted(int i, int i2);

        void onPassengerStopped(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface UserFilter {
        boolean isEligibleUser(UserInfo userInfo);
    }

    public interface ZoneUserBindingHelper {
        boolean assignUserToOccupantZone(int i, int i2);

        List<CarOccupantZoneManager.OccupantZoneInfo> getOccupantZones(int i);

        boolean isPassengerDisplayAvailable();

        boolean unassignUserFromOccupantZone(int i);
    }

    public CarUserService(Context context, UserHalService hal, CarUserManagerHelper carUserManagerHelper, UserManager userManager, IActivityManager am, int maxRunningUsers) {
        this(context, hal, carUserManagerHelper, userManager, am, maxRunningUsers, new UserMetrics());
    }

    CarUserService(Context context, UserHalService hal, CarUserManagerHelper carUserManagerHelper, UserManager userManager, IActivityManager am, int maxRunningUsers, UserMetrics userMetrics) {
        this.mLockUser = new Object();
        this.mUser0UnlockTasks = new ArrayList<>();
        this.mBackgroundUsersToRestart = new ArrayList<>();
        this.mBackgroundUsersRestartedHere = new ArrayList<>();
        this.mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        this.mUserLifecycleListeners = new ArrayList();
        this.mAppLifecycleListeners = new SparseArray<>();
        this.mUserIdForUserSwitchInProcess = -10000;
        this.mHalTimeoutMs = ((Integer) CarProperties.user_hal_timeout().orElse(Integer.valueOf(WifiEntry.FREQUENCY_5_GHZ))).intValue();
        this.mPassengerCallbacks = new CopyOnWriteArrayList<>();
        this.mLockHelper = new Object();
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "constructed");
        }
        this.mContext = context;
        this.mHal = hal;
        this.mCarUserManagerHelper = carUserManagerHelper;
        this.mAm = am;
        this.mMaxRunningUsers = maxRunningUsers;
        this.mUserManager = userManager;
        this.mLastPassengerId = -10000;
        this.mEnablePassengerSupport = context.getResources().getBoolean(R.bool.enablePassengerSupport);
        this.mUserMetrics = userMetrics;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", DummyEncryptionRunner.INIT);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "release");
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        checkAtLeastOnePermission("dump()", "android.permission.DUMP");
        writer.println("*CarUserService*");
        handleDumpListeners(writer, "  ");
        writer.printf("User switch UI receiver %s\n", this.mUserSwitchUiReceiver);
        synchronized (this.mLockUser) {
            writer.println("User0Unlocked: " + this.mUser0Unlocked);
            writer.println("BackgroundUsersToRestart: " + this.mBackgroundUsersToRestart);
            writer.println("BackgroundUsersRestarted: " + this.mBackgroundUsersRestartedHere);
        }
        writer.println("MaxRunningUsers: " + this.mMaxRunningUsers);
        List<UserInfo> allDrivers = getAllDrivers();
        int driversSize = allDrivers.size();
        writer.println("NumberOfDrivers: " + driversSize);
        for (int i = 0; i < driversSize; i++) {
            int driverId = allDrivers.get(i).id;
            writer.print("  #" + i + ": id=" + driverId);
            List<UserInfo> passengers = getPassengers(driverId);
            int passengersSize = passengers.size();
            StringBuilder sb = new StringBuilder();
            sb.append(" NumberPassengers: ");
            sb.append(passengersSize);
            writer.print(sb.toString());
            if (passengersSize > 0) {
                writer.print(" [");
                for (int j = 0; j < passengersSize; j++) {
                    writer.print(passengers.get(j).id);
                    if (j < passengersSize - 1) {
                        writer.print(" ");
                    }
                }
                writer.print("]");
            }
            writer.println();
        }
        writer.printf("EnablePassengerSupport: %s\n", Boolean.valueOf(this.mEnablePassengerSupport));
        writer.printf("User HAL timeout: %dms\n", Integer.valueOf(this.mHalTimeoutMs));
        writer.printf("Initial user: %s\n", this.mInitialUser);
        writer.println("Relevant overlayable properties");
        Resources res = this.mContext.getResources();
        writer.printf("%sowner_name=%s\n", "  ", res.getString(android.R.string.keyguard_password_wrong_pin_code));
        writer.printf("%sdefault_guest_name=%s\n", "  ", res.getString(R.string.default_guest_name));
        writer.printf("User switch in process=%d\n", Integer.valueOf(this.mUserIdForUserSwitchInProcess));
        writer.printf("Request Id for the user switch in process=%d\n ", Integer.valueOf(this.mRequestIdForUserSwitchInProcess));
        writer.printf("System UI package name=%s\n", getSystemUiPackageName());
        writer.println("Relevant Global settings");
        dumpGlobalProperty(writer, "  ", "android.car.LAST_ACTIVE_USER_ID");
        dumpGlobalProperty(writer, "  ", "android.car.LAST_ACTIVE_PERSISTENT_USER_ID");
        dumpUserMetrics(writer);
    }

    private void dumpGlobalProperty(PrintWriter writer, String indent, String property) {
        String value = Settings.Global.getString(this.mContext.getContentResolver(), property);
        writer.printf("%s%s=%s\n", indent, property, value);
    }

    public void dumpUserMetrics(PrintWriter writer) {
        this.mUserMetrics.dump(writer);
    }

    public void dumpFirstUserUnlockDuration(PrintWriter writer) {
        this.mUserMetrics.dumpFirstUserUnlockDuration(writer);
    }

    private void handleDumpListeners(final PrintWriter writer, final String indent) {
        final CountDownLatch latch = new CountDownLatch(1);
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$SHTUZBl66K-ySIdmmeDiti49P8U
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$handleDumpListeners$0$CarUserService(writer, indent, latch);
            }
        });
        try {
            if (!latch.await(5, TimeUnit.SECONDS)) {
                writer.printf("Handler thread didn't respond in %ds when dumping listeners\n", 5);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            writer.println("Interrupted waiting for handler thread to dump app and user listeners");
        }
    }

    public /* synthetic */ void lambda$handleDumpListeners$0$CarUserService(PrintWriter writer, String indent, CountDownLatch latch) {
        handleDumpServiceLifecycleListeners(writer);
        handleDumpAppLifecycleListeners(writer, indent);
        latch.countDown();
    }

    private void handleDumpServiceLifecycleListeners(PrintWriter writer) {
        if (this.mUserLifecycleListeners.isEmpty()) {
            writer.println("No lifecycle listeners for internal services");
            return;
        }
        int size = this.mUserLifecycleListeners.size();
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(size);
        objArr[1] = size == 1 ? "" : "s";
        writer.printf("%d lifecycle listener%s for services\n", objArr);
        for (CarUserManager.UserLifecycleListener listener : this.mUserLifecycleListeners) {
            writer.printf("%s%s\n", "  ", FunctionalUtils.getLambdaName(listener));
        }
    }

    private void handleDumpAppLifecycleListeners(PrintWriter writer, String indent) {
        int size = this.mAppLifecycleListeners.size();
        if (size == 0) {
            writer.println("No lifecycle listeners for apps");
            return;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(size);
        objArr[1] = size == 1 ? "" : "s";
        writer.printf("%d lifecycle listener%s for apps \n", objArr);
        for (int i = 0; i < size; i++) {
            int uid = this.mAppLifecycleListeners.keyAt(i);
            this.mAppLifecycleListeners.valueAt(i);
            writer.printf("%suid: %d\n", indent, Integer.valueOf(uid));
        }
    }

    public AndroidFuture<UserCreationResult> createDriver(final String name, final boolean admin) {
        int flags;
        checkManageUsersPermission("createDriver");
        Objects.requireNonNull(name, "name cannot be null");
        AndroidFuture<UserCreationResult> future = new AndroidFuture<UserCreationResult>() { // from class: com.android.car.user.CarUserService.1
            /* JADX INFO: Access modifiers changed from: protected */
            public void onCompleted(UserCreationResult result, Throwable err) {
                if (result == null) {
                    Log.w("CAR.USER", "createDriver(" + name + "," + admin + ") failed: " + err);
                } else if (result.getStatus() == 1) {
                    CarUserService.this.assignDefaultIcon(result.getUser());
                }
                super.onCompleted(result, err);
            }
        };
        if (!admin) {
            flags = 0;
        } else {
            if (!this.mUserManager.isAdminUser() && !this.mUserManager.isSystemUser()) {
                Log.e("CAR.USER", "Only admin users and system user can create other admins.");
                sendUserCreationResultFailure(future, 5);
                return future;
            }
            flags = 2;
        }
        createUser(name, UserInfo.getDefaultUserType(flags), flags, this.mHalTimeoutMs, future);
        return future;
    }

    public UserInfo createPassenger(String name, int driverId) {
        checkManageUsersPermission("createPassenger");
        Objects.requireNonNull(name, "name cannot be null");
        UserInfo driver = this.mUserManager.getUserInfo(driverId);
        if (driver == null) {
            Log.w("CAR.USER", "the driver is invalid");
            return null;
        }
        if (driver.isGuest()) {
            Log.w("CAR.USER", "a guest driver cannot create a passenger");
            return null;
        }
        UserInfo user = this.mUserManager.createProfileForUser(name, "android.os.usertype.profile.MANAGED", 0, driverId);
        if (user == null) {
            Log.w("CAR.USER", "can't create a profile for user" + driverId);
            return null;
        }
        this.mCarUserManagerHelper.setDefaultNonAdminRestrictions(user, true);
        assignDefaultIcon(user);
        return user;
    }

    public void switchDriver(int driverId, AndroidFuture<UserSwitchResult> receiver) {
        checkManageUsersPermission("switchDriver");
        if (UserHelper.isHeadlessSystemUser(driverId)) {
            Log.w("CAR.USER", "switching to system user in headless system user mode is not allowed");
            sendUserSwitchResult(receiver, 5);
            return;
        }
        int userSwitchable = this.mUserManager.getUserSwitchability();
        if (userSwitchable != 0) {
            Log.w("CAR.USER", "current process is not allowed to switch user");
            sendUserSwitchResult(receiver, 5);
        } else {
            switchUser(driverId, this.mHalTimeoutMs, receiver);
        }
    }

    public List<UserInfo> getAllDrivers() {
        checkManageUsersOrDumpPermission("getAllDrivers");
        return getUsers(new UserFilter() { // from class: com.android.car.user.-$$Lambda$CarUserService$eYkLf-pbrtcg8yalDuOINiuk6p0
            @Override // com.android.car.user.CarUserService.UserFilter
            public final boolean isEligibleUser(UserInfo userInfo) {
                return CarUserService.lambda$getAllDrivers$1(userInfo);
            }
        });
    }

    static /* synthetic */ boolean lambda$getAllDrivers$1(UserInfo user) {
        return (UserHelper.isHeadlessSystemUser(user.id) || !user.isEnabled() || user.isManagedProfile() || user.isEphemeral()) ? false : true;
    }

    public List<UserInfo> getPassengers(final int driverId) {
        checkManageUsersOrDumpPermission("getPassengers");
        return getUsers(new UserFilter() { // from class: com.android.car.user.-$$Lambda$CarUserService$UAf_fy6FPcrzsF9UTrKBBhtrqgg
            @Override // com.android.car.user.CarUserService.UserFilter
            public final boolean isEligibleUser(UserInfo userInfo) {
                return CarUserService.lambda$getPassengers$2(driverId, userInfo);
            }
        });
    }

    static /* synthetic */ boolean lambda$getPassengers$2(int driverId, UserInfo user) {
        return !UserHelper.isHeadlessSystemUser(user.id) && user.isEnabled() && user.isManagedProfile() && user.profileGroupId == driverId;
    }

    public boolean startPassenger(int passengerId, int zoneId) {
        checkManageUsersPermission("startPassenger");
        synchronized (this.mLockUser) {
            try {
                if (!this.mAm.startUserInBackgroundWithListener(passengerId, (IProgressListener) null)) {
                    Log.w("CAR.USER", "could not start passenger");
                    return false;
                }
                if (!assignUserToOccupantZone(passengerId, zoneId)) {
                    Log.w("CAR.USER", "could not assign passenger to zone");
                    return false;
                }
                this.mLastPassengerId = passengerId;
                for (PassengerCallback callback : this.mPassengerCallbacks) {
                    callback.onPassengerStarted(passengerId, zoneId);
                }
                return true;
            } catch (RemoteException e) {
                Log.w("CAR.USER", "error while starting passenger", e);
                return false;
            }
        }
    }

    public boolean stopPassenger(int passengerId) {
        checkManageUsersPermission("stopPassenger");
        return stopPassengerInternal(passengerId, true);
    }

    private boolean stopPassengerInternal(int passengerId, boolean checkCurrentDriver) {
        synchronized (this.mLockUser) {
            UserInfo passenger = this.mUserManager.getUserInfo(passengerId);
            if (passenger == null) {
                Log.w("CAR.USER", "passenger " + passengerId + " doesn't exist");
                return false;
            }
            if (this.mLastPassengerId != passengerId) {
                Log.w("CAR.USER", "passenger " + passengerId + " hasn't been started");
                return true;
            }
            if (checkCurrentDriver) {
                int currentUser = ActivityManager.getCurrentUser();
                if (passenger.profileGroupId != currentUser) {
                    Log.w("CAR.USER", "passenger " + passengerId + " is not a profile of the current user");
                    return false;
                }
            }
            stopAllTasks(passengerId);
            if (!unassignUserFromOccupantZone(passengerId)) {
                Log.w("CAR.USER", "could not unassign user from occupant zone");
                return false;
            }
            this.mLastPassengerId = -10000;
            for (PassengerCallback callback : this.mPassengerCallbacks) {
                callback.onPassengerStopped(passengerId);
            }
            return true;
        }
    }

    private void stopAllTasks(int userId) {
        try {
            for (ActivityManager.StackInfo info : this.mAm.getAllStackInfos()) {
                for (int i = 0; i < info.taskIds.length; i++) {
                    if (info.taskUserIds[i] == userId) {
                        int taskId = info.taskIds[i];
                        if (!this.mAm.removeTask(taskId)) {
                            Log.w("CAR.USER", "could not remove task " + taskId);
                        }
                    }
                }
            }
        } catch (RemoteException e) {
            Log.e("CAR.USER", "could not get stack info", e);
        }
    }

    public void setLifecycleListenerForUid(final IResultReceiver listener) {
        final int uid = Binder.getCallingUid();
        EventLog.writeEvent(150104, uid);
        checkInteractAcrossUsersPermission("setLifecycleListenerForUid" + uid);
        try {
            listener.asBinder().linkToDeath(new IBinder.DeathRecipient() { // from class: com.android.car.user.-$$Lambda$CarUserService$M8xPrFTkpNg6mQbAP8N5PD2UQ8U
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    this.f$0.lambda$setLifecycleListenerForUid$3$CarUserService(uid);
                }
            }, 0);
        } catch (RemoteException e) {
            Log.wtf("CAR.USER", "Cannot listen to death of " + uid);
        }
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$hmEhlun_swD-KVYnn2SNlGzuSF0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setLifecycleListenerForUid$4$CarUserService(uid, listener);
            }
        });
    }

    public /* synthetic */ void lambda$setLifecycleListenerForUid$4$CarUserService(int uid, IResultReceiver listener) {
        this.mAppLifecycleListeners.append(uid, listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onListenerDeath, reason: merged with bridge method [inline-methods] */
    public void lambda$setLifecycleListenerForUid$3$CarUserService(final int uid) {
        Log.i("CAR.USER", "Removing listeners for uid " + uid + " on binder death");
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$8K5VxDVmjS4ufVGHMC8VsHmahxQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onListenerDeath$5$CarUserService(uid);
            }
        });
    }

    public /* synthetic */ void lambda$onListenerDeath$5$CarUserService(int uid) {
        this.mAppLifecycleListeners.remove(uid);
    }

    public void resetLifecycleListenerForUid() {
        final int uid = Binder.getCallingUid();
        EventLog.writeEvent(150105, uid);
        checkInteractAcrossUsersPermission("resetLifecycleListenerForUid-" + uid);
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$0W7bzfy5hahyE4azmKmE2nebmgU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$resetLifecycleListenerForUid$6$CarUserService(uid);
            }
        });
    }

    public /* synthetic */ void lambda$resetLifecycleListenerForUid$6$CarUserService(int uid) {
        this.mAppLifecycleListeners.remove(uid);
    }

    public void getInitialUserInfo(int requestType, int timeoutMs, final IResultReceiver receiver) {
        EventLog.writeEvent(150100, Integer.valueOf(requestType), Integer.valueOf(timeoutMs));
        Objects.requireNonNull(receiver, "receiver cannot be null");
        checkManageUsersPermission("getInitialInfo");
        if (!isUserHalSupported()) {
            sendResult(receiver, 6, null);
        } else {
            UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            this.mHal.getInitialUserInfo(requestType, timeoutMs, usersInfo, new HalCallback() { // from class: com.android.car.user.-$$Lambda$CarUserService$HHK8OZDzGEgQHzryMJY6ulirro0
                @Override // android.car.userlib.HalCallback
                public final void onResponse(int i, Object obj) {
                    this.f$0.lambda$getInitialUserInfo$7$CarUserService(receiver, i, (InitialUserInfoResponse) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$getInitialUserInfo$7$CarUserService(IResultReceiver receiver, int status, InitialUserInfoResponse resp) {
        Bundle resultData = null;
        if (resp != null) {
            EventLog.writeEvent(150101, Integer.valueOf(status), Integer.valueOf(resp.action), Integer.valueOf(resp.userToSwitchOrCreate.userId), Integer.valueOf(resp.userToSwitchOrCreate.flags), resp.userNameToCreate, resp.userLocales);
            int i = resp.action;
            if (i == 0) {
                resultData = new Bundle();
                resultData.putInt("initial_info.action", resp.action);
            } else if (i == 1) {
                resultData = new Bundle();
                resultData.putInt("initial_info.action", resp.action);
                resultData.putInt("user.id", resp.userToSwitchOrCreate.userId);
            } else if (i == 2) {
                resultData = new Bundle();
                resultData.putInt("initial_info.action", resp.action);
                resultData.putInt("user.flags", resp.userToSwitchOrCreate.flags);
                resultData.putString("user.name", resp.userNameToCreate);
            } else {
                Log.w("CAR.USER", "invalid response action on " + resp);
            }
        } else {
            EventLog.writeEvent(150101, status);
        }
        if (resultData != null && !TextUtils.isEmpty(resp.userLocales)) {
            resultData.putString("user.locales", resp.userLocales);
        }
        sendResult(receiver, status, resultData);
    }

    public UserInfo getInitialUser() {
        UserInfo userInfo;
        checkInteractAcrossUsersPermission("getInitialUser");
        synchronized (this.mLockUser) {
            userInfo = this.mInitialUser;
        }
        return userInfo;
    }

    public void setInitialUser(int userId) {
        UserInfo initialUser = userId == -10000 ? null : this.mUserManager.getUserInfo(userId);
        setInitialUser(initialUser);
    }

    public void setInitialUser(UserInfo user) {
        EventLog.writeEvent(150103, user == null ? -10000 : user.id);
        synchronized (this.mLockUser) {
            this.mInitialUser = user;
        }
        if (user == null) {
            Log.wtf("CAR.USER", "Initial user set to null");
        }
    }

    public void getInitialUserInfo(int requestType, HalCallback<InitialUserInfoResponse> callback) {
        EventLog.writeEvent(150100, Integer.valueOf(requestType), Integer.valueOf(this.mHalTimeoutMs));
        Objects.requireNonNull(callback, "callback cannot be null");
        checkManageUsersPermission("getInitialUserInfo");
        if (!isUserHalSupported()) {
            callback.onResponse(6, null);
        } else {
            UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            this.mHal.getInitialUserInfo(requestType, this.mHalTimeoutMs, usersInfo, callback);
        }
    }

    public void switchUser(final int targetUserId, int timeoutMs, final AndroidFuture<UserSwitchResult> receiver) {
        EventLog.writeEvent(150106, Integer.valueOf(targetUserId), Integer.valueOf(timeoutMs));
        checkManageUsersPermission("switchUser");
        Objects.requireNonNull(receiver);
        final UserInfo targetUser = this.mUserManager.getUserInfo(targetUserId);
        Preconditions.checkArgument(targetUser != null, "Target user doesn't exist");
        int currentUser = ActivityManager.getCurrentUser();
        if (currentUser == targetUserId) {
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "Current user is same as requested target user: " + targetUserId);
            }
            sendUserSwitchResult(receiver, 101);
            return;
        }
        if (!isUserHalSupported()) {
            try {
                if (this.mAm.switchUser(targetUserId)) {
                    sendUserSwitchResult(receiver, 1);
                    return;
                }
            } catch (RemoteException e) {
                Log.w("CAR.USER", "error while switching user " + targetUser.toFullString(), e);
            }
            sendUserSwitchResult(receiver, 2);
            return;
        }
        synchronized (this.mLockUser) {
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "switchUser(" + targetUserId + "): currentuser=" + currentUser + ", mUserIdForUserSwitchInProcess=" + this.mUserIdForUserSwitchInProcess);
            }
            if (this.mUserIdForUserSwitchInProcess == targetUserId) {
                if (Log.isLoggable("CAR.USER", 3)) {
                    Log.d("CAR.USER", "Another user switch request in process for the requested target user: " + targetUserId);
                }
                sendUserSwitchResult(receiver, 102);
                return;
            }
            this.mUserIdForUserSwitchInProcess = targetUserId;
            this.mRequestIdForUserSwitchInProcess = 0;
            UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            SwitchUserRequest request = createUserSwitchRequest(targetUserId, usersInfo);
            this.mHal.switchUser(request, timeoutMs, new HalCallback() { // from class: com.android.car.user.-$$Lambda$CarUserService$VwnP1nHghLumo_uIqJfkumbrctI
                @Override // android.car.userlib.HalCallback
                public final void onResponse(int i, Object obj) {
                    this.f$0.lambda$switchUser$8$CarUserService(receiver, targetUserId, targetUser, i, (SwitchUserResponse) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$switchUser$8$CarUserService(AndroidFuture receiver, int targetUserId, UserInfo targetUser, int status, SwitchUserResponse resp) {
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "switch response: status=" + UserHalHelper.halCallbackStatusToString(status) + ", resp=" + resp);
        }
        int resultStatus = 4;
        synchronized (this.mLockUser) {
            if (status == 1) {
                EventLog.writeEvent(150107, Integer.valueOf(status), Integer.valueOf(resp.status), resp.errorMessage);
                if (this.mUserIdForUserSwitchInProcess != targetUserId) {
                    if (Log.isLoggable("CAR.USER", 3)) {
                        Log.d("CAR.USER", "Another user switch received while HAL responsed. Request abondoned for : " + targetUserId + ". Current user in process: " + this.mUserIdForUserSwitchInProcess);
                    }
                    sendUserSwitchResult(receiver, 103);
                    this.mUserIdForUserSwitchInProcess = -10000;
                    return;
                }
                int i = resp.status;
                if (i == 1) {
                    try {
                        boolean switched = this.mAm.switchUser(targetUserId);
                        if (switched) {
                            sendUserSwitchUiCallback(targetUserId);
                            resultStatus = 1;
                            this.mRequestIdForUserSwitchInProcess = resp.requestId;
                        } else {
                            resultStatus = 2;
                            postSwitchHalResponse(resp.requestId, targetUserId);
                        }
                    } catch (RemoteException e) {
                        Log.w("CAR.USER", "error while switching user " + targetUser.toFullString(), e);
                    }
                } else if (i == 2) {
                    resultStatus = 3;
                } else {
                    Log.wtf("CAR.USER", "Received invalid user switch status from HAL: " + resp);
                }
                if (this.mRequestIdForUserSwitchInProcess == 0) {
                    this.mUserIdForUserSwitchInProcess = -10000;
                }
                sendUserSwitchResult(receiver, resultStatus, resp.errorMessage);
                return;
            }
            EventLog.writeEvent(150107, status);
            Log.w("CAR.USER", "invalid callback status (" + UserHalHelper.halCallbackStatusToString(status) + ") for response " + resp);
            sendUserSwitchResult(receiver, 4);
            this.mUserIdForUserSwitchInProcess = -10000;
        }
    }

    public UserRemovalResult removeUser(int userId) {
        checkManageUsersPermission("removeUser");
        EventLog.writeEvent(150119, userId);
        if (ActivityManager.getCurrentUser() == userId) {
            return logAndGetResults(userId, 101);
        }
        UserInfo userInfo = this.mUserManager.getUserInfo(userId);
        if (userInfo == null) {
            return logAndGetResults(userId, 102);
        }
        android.hardware.automotive.vehicle.V2_0.UserInfo halUser = new android.hardware.automotive.vehicle.V2_0.UserInfo();
        halUser.userId = userInfo.id;
        halUser.flags = UserHalHelper.convertFlags(userInfo);
        UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
        boolean isLastAdmin = false;
        if (UserHalHelper.isAdmin(halUser.flags)) {
            int size = usersInfo.existingUsers.size();
            int totalAdminUsers = 0;
            for (int i = 0; i < size; i++) {
                if (UserHalHelper.isAdmin(usersInfo.existingUsers.get(i).flags)) {
                    totalAdminUsers++;
                }
            }
            if (totalAdminUsers == 1) {
                isLastAdmin = true;
            }
        }
        if (!this.mUserManager.removeUser(userId)) {
            return logAndGetResults(userId, 2);
        }
        if (isUserHalSupported()) {
            RemoveUserRequest request = new RemoveUserRequest();
            request.removedUserInfo = halUser;
            request.usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            this.mHal.removeUser(request);
        }
        if (isLastAdmin) {
            Log.w("CAR.USER", "Last admin user successfully removed. UserId: " + userId);
        }
        return logAndGetResults(userId, isLastAdmin ? 103 : 1);
    }

    private UserRemovalResult logAndGetResults(int userId, int result) {
        EventLog.writeEvent(150120, Integer.valueOf(userId), Integer.valueOf(result));
        return new UserRemovalResult(result);
    }

    private void sendUserSwitchUiCallback(int targetUserId) {
        if (this.mUserSwitchUiReceiver == null) {
            Log.w("CAR.USER", "No User switch UI receiver.");
            return;
        }
        EventLog.writeEvent(150111, targetUserId);
        try {
            this.mUserSwitchUiReceiver.send(targetUserId, (Bundle) null);
        } catch (RemoteException e) {
            Log.e("CAR.USER", "Error calling user switch UI receiver.", e);
        }
    }

    public void createUser(String name, String userType, int flags, int timeoutMs, final AndroidFuture<UserCreationResult> receiver) {
        Objects.requireNonNull(userType, "user type cannot be null");
        Objects.requireNonNull(receiver, "receiver cannot be null");
        checkManageOrCreateUsersPermission("createUser");
        EventLog.writeEvent(150115, UserHelper.safeName(name), userType, Integer.valueOf(flags), Integer.valueOf(timeoutMs));
        try {
            final UserInfo newUser = this.mUserManager.createUser(name, userType, flags);
            if (newUser == null) {
                Log.w("CAR.USER", "um.createUser() returned null for user of type " + userType + " and flags " + UserInfo.flagsToString(flags));
                sendUserCreationResultFailure(receiver, 2);
                return;
            }
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "Created user: " + newUser.toFullString());
            }
            EventLog.writeEvent(150117, Integer.valueOf(newUser.id), UserHelper.safeName(newUser.name), newUser.userType, Integer.valueOf(newUser.flags));
            if (!isUserHalSupported()) {
                sendUserCreationResult(receiver, 1, newUser, null);
                return;
            }
            CreateUserRequest request = new CreateUserRequest();
            request.usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            if (!TextUtils.isEmpty(name)) {
                request.newUserName = name;
            }
            request.newUserInfo.userId = newUser.id;
            request.newUserInfo.flags = UserHalHelper.convertFlags(newUser);
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "Create user request: " + request);
            }
            try {
                this.mHal.createUser(request, timeoutMs, new HalCallback() { // from class: com.android.car.user.-$$Lambda$CarUserService$Sf9WWUqAHAQ73x650gaqtRWAJWE
                    @Override // android.car.userlib.HalCallback
                    public final void onResponse(int i, Object obj) {
                        this.f$0.lambda$createUser$9$CarUserService(newUser, receiver, i, (CreateUserResponse) obj);
                    }
                });
            } catch (Exception e) {
                Log.w("CAR.USER", "mHal.createUser(" + request + ") failed", e);
                removeUser(newUser, "mHal.createUser() failed");
                sendUserCreationResultFailure(receiver, 4);
            }
        } catch (RuntimeException e2) {
            Log.e("CAR.USER", "Error creating user of type " + userType + " and flags" + UserInfo.flagsToString(flags), e2);
            sendUserCreationResultFailure(receiver, 2);
        }
    }

    public /* synthetic */ void lambda$createUser$9$CarUserService(UserInfo newUser, AndroidFuture receiver, int status, CreateUserResponse resp) {
        int resultStatus = 4;
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "createUserResponse: status=" + UserHalHelper.halCallbackStatusToString(status) + ", resp=" + resp);
        }
        UserInfo user = null;
        if (status != 1) {
            Log.w("CAR.USER", "invalid callback status (" + UserHalHelper.halCallbackStatusToString(status) + ") for response " + resp);
            EventLog.writeEvent(150116, Integer.valueOf(status), 4, resp.errorMessage);
            StringBuilder sb = new StringBuilder();
            sb.append("HAL call failed with ");
            sb.append(UserHalHelper.halCallbackStatusToString(status));
            removeUser(newUser, sb.toString());
            sendUserCreationResult(receiver, 4, null, null);
            return;
        }
        int i = resp.status;
        if (i == 1) {
            resultStatus = 1;
            user = newUser;
        } else if (i == 2) {
            resultStatus = 3;
        } else {
            Log.wtf("CAR.USER", "Received invalid user switch status from HAL: " + resp);
        }
        EventLog.writeEvent(150116, Integer.valueOf(status), Integer.valueOf(resultStatus), resp.errorMessage);
        if (user == null) {
            removeUser(newUser, "HAL returned " + UserCreationResult.statusToString(resultStatus));
        }
        sendUserCreationResult(receiver, resultStatus, user, resp.errorMessage);
    }

    private void removeUser(UserInfo user, String reason) {
        EventLog.writeEvent(150118, Integer.valueOf(user.id), reason);
        try {
            if (!this.mUserManager.removeUser(user.id)) {
                Log.w("CAR.USER", "Failed to remove user " + user.toFullString());
            }
        } catch (Exception e) {
            Log.e("CAR.USER", "Failed to remove user " + user.toFullString(), e);
        }
    }

    public UserIdentificationAssociationResponse getUserIdentificationAssociation(int[] types) {
        if (!isUserHalUserAssociationSupported()) {
            return UserIdentificationAssociationResponse.forFailure(VEHICLE_HAL_NOT_SUPPORTED);
        }
        Preconditions.checkArgument(!ArrayUtils.isEmpty(types), "must have at least one type");
        checkManageUsersPermission("getUserIdentificationAssociation");
        int uid = getCallingUid();
        int userId = UserHandle.getUserId(uid);
        EventLog.writeEvent(150176, Integer.valueOf(uid), Integer.valueOf(userId));
        UserIdentificationGetRequest request = new UserIdentificationGetRequest();
        request.userInfo.userId = userId;
        request.userInfo.flags = getHalUserInfoFlags(userId);
        request.numberAssociationTypes = types.length;
        for (int i : types) {
            request.associationTypes.add(Integer.valueOf(i));
        }
        UserIdentificationResponse halResponse = this.mHal.getUserAssociation(request);
        if (halResponse == null) {
            Log.w("CAR.USER", "getUserIdentificationAssociation(): HAL returned null for " + Arrays.toString(types));
            return UserIdentificationAssociationResponse.forFailure();
        }
        int[] values = new int[halResponse.associations.size()];
        for (int i2 = 0; i2 < values.length; i2++) {
            values[i2] = halResponse.associations.get(i2).value;
        }
        EventLog.writeEvent(150177, values.length);
        return UserIdentificationAssociationResponse.forSuccess(values, halResponse.errorMessage);
    }

    public void setUserIdentificationAssociation(int timeoutMs, int[] types, int[] values, final AndroidFuture<UserIdentificationAssociationResponse> result) {
        if (!isUserHalUserAssociationSupported()) {
            result.complete(UserIdentificationAssociationResponse.forFailure(VEHICLE_HAL_NOT_SUPPORTED));
            return;
        }
        Preconditions.checkArgument(!ArrayUtils.isEmpty(types), "must have at least one type");
        Preconditions.checkArgument(!ArrayUtils.isEmpty(values), "must have at least one value");
        if (types.length != values.length) {
            throw new IllegalArgumentException("types (" + Arrays.toString(types) + ") and values (" + Arrays.toString(values) + ") should have the same length");
        }
        checkManageUsersPermission("setUserIdentificationAssociation");
        int uid = getCallingUid();
        int userId = UserHandle.getUserId(uid);
        EventLog.writeEvent(150178, Integer.valueOf(uid), Integer.valueOf(userId), Integer.valueOf(types.length));
        UserIdentificationSetRequest request = new UserIdentificationSetRequest();
        request.userInfo.userId = userId;
        request.userInfo.flags = getHalUserInfoFlags(userId);
        request.numberAssociations = types.length;
        for (int i = 0; i < types.length; i++) {
            UserIdentificationSetAssociation association = new UserIdentificationSetAssociation();
            association.type = types[i];
            association.value = values[i];
            request.associations.add(association);
        }
        this.mHal.setUserAssociation(timeoutMs, request, new HalCallback() { // from class: com.android.car.user.-$$Lambda$CarUserService$VAl0DCI_G_2YvmAq4KO4Y9gI41E
            @Override // android.car.userlib.HalCallback
            public final void onResponse(int i2, Object obj) {
                CarUserService.lambda$setUserIdentificationAssociation$10(result, i2, (UserIdentificationResponse) obj);
            }
        });
    }

    static /* synthetic */ void lambda$setUserIdentificationAssociation$10(AndroidFuture result, int status, UserIdentificationResponse resp) {
        if (status != 1) {
            Log.w("CAR.USER", "setUserIdentificationAssociation(): invalid callback status (" + UserHalHelper.halCallbackStatusToString(status) + ") for response " + resp);
            if (resp == null || TextUtils.isEmpty(resp.errorMessage)) {
                EventLog.writeEvent(150179, 0);
                result.complete(UserIdentificationAssociationResponse.forFailure());
                return;
            } else {
                EventLog.writeEvent(150179, 0, resp.errorMessage);
                result.complete(UserIdentificationAssociationResponse.forFailure(resp.errorMessage));
                return;
            }
        }
        int respSize = resp.associations.size();
        EventLog.writeEvent(150179, Integer.valueOf(respSize), resp.errorMessage);
        int[] responseTypes = new int[respSize];
        for (int i = 0; i < respSize; i++) {
            responseTypes[i] = resp.associations.get(i).value;
        }
        UserIdentificationAssociationResponse response = UserIdentificationAssociationResponse.forSuccess(responseTypes, resp.errorMessage);
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "setUserIdentificationAssociation(): resp= " + resp + ", converted=" + response);
        }
        result.complete(response);
    }

    private int getHalUserInfoFlags(int userId) {
        UserInfo user = this.mUserManager.getUserInfo(userId);
        Preconditions.checkArgument(user != null, "no user for id %d", new Object[]{Integer.valueOf(userId)});
        return UserHalHelper.convertFlags(user);
    }

    private void sendResult(IResultReceiver receiver, int resultCode, Bundle resultData) {
        try {
            receiver.send(resultCode, resultData);
        } catch (RemoteException e) {
            Log.w("CAR.USER", "error while sending results", e);
        }
    }

    private void sendUserSwitchResult(AndroidFuture<UserSwitchResult> receiver, int status) {
        sendUserSwitchResult(receiver, status, null);
    }

    private void sendUserSwitchResult(AndroidFuture<UserSwitchResult> receiver, int status, String errorMessage) {
        receiver.complete(new UserSwitchResult(status, errorMessage));
    }

    private void sendUserCreationResultFailure(AndroidFuture<UserCreationResult> receiver, int status) {
        sendUserCreationResult(receiver, status, null, null);
    }

    private void sendUserCreationResult(AndroidFuture<UserCreationResult> receiver, int status, UserInfo user, String errorMessage) {
        if (TextUtils.isEmpty(errorMessage)) {
            errorMessage = null;
        }
        receiver.complete(new UserCreationResult(status, user, errorMessage));
    }

    public void switchAndroidUserFromHal(int requestId, int targetUserId) {
        EventLog.writeEvent(150112, Integer.valueOf(requestId), Integer.valueOf(targetUserId));
        Log.i("CAR.USER", "User hal requested a user switch. Target user id " + targetUserId);
        try {
            boolean result = this.mAm.switchUser(targetUserId);
            if (result) {
                updateUserSwitchInProcess(requestId, targetUserId);
            } else {
                postSwitchHalResponse(requestId, targetUserId);
            }
        } catch (RemoteException e) {
            Log.w("CAR.USER", "error while switching user " + targetUserId, e);
        }
    }

    private void updateUserSwitchInProcess(int requestId, int targetUserId) {
        synchronized (this.mLockUser) {
            if (this.mUserIdForUserSwitchInProcess != -10000 && Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "User switch for user: " + this.mUserIdForUserSwitchInProcess + " is in process. Abandoning it as a new user switch is requested for the target user: " + targetUserId);
            }
            this.mUserIdForUserSwitchInProcess = targetUserId;
            this.mRequestIdForUserSwitchInProcess = requestId;
        }
    }

    private void postSwitchHalResponse(int requestId, int targetUserId) {
        if (isUserHalSupported()) {
            UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager);
            EventLog.writeEvent(150108, Integer.valueOf(requestId), Integer.valueOf(targetUserId), Integer.valueOf(usersInfo.currentUser.userId));
            SwitchUserRequest request = createUserSwitchRequest(targetUserId, usersInfo);
            request.requestId = requestId;
            this.mHal.postSwitchResponse(request);
        }
    }

    private SwitchUserRequest createUserSwitchRequest(int targetUserId, UsersInfo usersInfo) {
        UserInfo targetUser = this.mUserManager.getUserInfo(targetUserId);
        android.hardware.automotive.vehicle.V2_0.UserInfo halTargetUser = new android.hardware.automotive.vehicle.V2_0.UserInfo();
        halTargetUser.userId = targetUser.id;
        halTargetUser.flags = UserHalHelper.convertFlags(targetUser);
        SwitchUserRequest request = new SwitchUserRequest();
        request.targetUser = halTargetUser;
        request.usersInfo = usersInfo;
        return request;
    }

    public boolean isUserHalSupported() {
        return this.mHal.isSupported();
    }

    public boolean isUserHalUserAssociationSupported() {
        return this.mHal.isUserAssociationSupported();
    }

    public void setUserSwitchUiCallback(IResultReceiver receiver) {
        checkManageUsersPermission("setUserSwitchUiCallback");
        String systemUiPackageName = getSystemUiPackageName();
        if (systemUiPackageName == null) {
            throw new IllegalStateException("System UI package not found.");
        }
        try {
            int systemUiUid = this.mContext.createContextAsUser(UserHandle.SYSTEM, 0).getPackageManager().getPackageUid(systemUiPackageName, 1048576);
            int callerUid = Binder.getCallingUid();
            if (systemUiUid != callerUid) {
                throw new SecurityException("Invalid caller. Only" + systemUiPackageName + " is allowed to make this call");
            }
            this.mUserSwitchUiReceiver = receiver;
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalStateException("Package " + systemUiPackageName + " not found.");
        }
    }

    private String getSystemUiPackageName() {
        try {
            ComponentName componentName = ComponentName.unflattenFromString(this.mContext.getResources().getString(android.R.string.config_defaultSelectToSpeakService));
            return componentName.getPackageName();
        } catch (RuntimeException e) {
            Log.w("CAR.USER", "error while getting system UI package name.", e);
            return null;
        }
    }

    private void updateDefaultUserRestriction() {
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "android.car.DEFAULT_USER_RESTRICTIONS_SET", 0) != 0) {
            return;
        }
        if (UserManager.isHeadlessSystemUserMode()) {
            setSystemUserRestrictions();
        }
        Settings.Global.putInt(this.mContext.getContentResolver(), "android.car.DEFAULT_USER_RESTRICTIONS_SET", 1);
    }

    private boolean isPersistentUser(int userId) {
        return !this.mUserManager.getUserInfo(userId).isEphemeral();
    }

    public void addUserLifecycleListener(final CarUserManager.UserLifecycleListener listener) {
        Objects.requireNonNull(listener, "listener cannot be null");
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$wt888euxVE-AZRfdtcFpfxMLvPA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$addUserLifecycleListener$11$CarUserService(listener);
            }
        });
    }

    public /* synthetic */ void lambda$addUserLifecycleListener$11$CarUserService(CarUserManager.UserLifecycleListener listener) {
        this.mUserLifecycleListeners.add(listener);
    }

    public void removeUserLifecycleListener(final CarUserManager.UserLifecycleListener listener) {
        Objects.requireNonNull(listener, "listener cannot be null");
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$eReR9qcOKSuwX0CEOxPhTYRL2cs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$removeUserLifecycleListener$12$CarUserService(listener);
            }
        });
    }

    public /* synthetic */ void lambda$removeUserLifecycleListener$12$CarUserService(CarUserManager.UserLifecycleListener listener) {
        this.mUserLifecycleListeners.remove(listener);
    }

    public void addPassengerCallback(PassengerCallback callback) {
        Objects.requireNonNull(callback, "callback cannot be null");
        this.mPassengerCallbacks.add(callback);
    }

    public void removePassengerCallback(PassengerCallback callback) {
        Objects.requireNonNull(callback, "callback cannot be null");
        this.mPassengerCallbacks.remove(callback);
    }

    public void setZoneUserBindingHelper(ZoneUserBindingHelper helper) {
        synchronized (this.mLockHelper) {
            this.mZoneUserBindingHelper = helper;
        }
    }

    private void onUserUnlocked(int userId) {
        ArrayList<Runnable> tasks = null;
        synchronized (this.mLockUser) {
            sendPostSwitchToHalLocked(userId);
            if (userId == 0) {
                if (!this.mUser0Unlocked) {
                    updateDefaultUserRestriction();
                    tasks = new ArrayList<>(this.mUser0UnlockTasks);
                    this.mUser0UnlockTasks.clear();
                    this.mUser0Unlocked = true;
                }
            } else {
                Integer user = Integer.valueOf(userId);
                if (isPersistentUser(userId)) {
                    if (userId == ActivityManager.getCurrentUser()) {
                        this.mBackgroundUsersToRestart.remove(user);
                        this.mBackgroundUsersToRestart.add(0, user);
                    }
                    if (this.mBackgroundUsersToRestart.size() > this.mMaxRunningUsers - 1) {
                        int userToDrop = this.mBackgroundUsersToRestart.get(this.mBackgroundUsersToRestart.size() - 1).intValue();
                        Log.i("CAR.USER", "New user unlocked:" + userId + ", dropping least recently user from restart list:" + userToDrop);
                        this.mBackgroundUsersToRestart.remove(this.mBackgroundUsersToRestart.size() - 1);
                    }
                }
            }
        }
        if (tasks != null && tasks.size() > 0) {
            Log.d("CAR.USER", "User0 unlocked, run queued tasks:" + tasks.size());
            for (Runnable r : tasks) {
                r.run();
            }
        }
    }

    public ArrayList<Integer> startAllBackgroundUsers() {
        ArrayList<Integer> users;
        synchronized (this.mLockUser) {
            users = new ArrayList<>(this.mBackgroundUsersToRestart);
            this.mBackgroundUsersRestartedHere.clear();
            this.mBackgroundUsersRestartedHere.addAll(this.mBackgroundUsersToRestart);
        }
        ArrayList<Integer> startedUsers = new ArrayList<>();
        for (Integer user : users) {
            if (user.intValue() != ActivityManager.getCurrentUser()) {
                try {
                    if (this.mAm.startUserInBackground(user.intValue())) {
                        if (this.mUserManager.isUserUnlockingOrUnlocked(user.intValue())) {
                            startedUsers.add(user);
                        } else if (this.mAm.unlockUser(user.intValue(), (byte[]) null, (byte[]) null, (IProgressListener) null)) {
                            startedUsers.add(user);
                        } else {
                            Log.w("CAR.USER", "Background user started but cannot be unlocked:" + user);
                            if (this.mUserManager.isUserRunning(user.intValue())) {
                                startedUsers.add(user);
                            }
                        }
                    }
                } catch (RemoteException e) {
                    Log.w("CAR.USER", "error while starting user in background", e);
                }
            }
        }
        synchronized (this.mLockUser) {
            ArrayList<Integer> usersToRemove = new ArrayList<>();
            for (Integer user2 : this.mBackgroundUsersToRestart) {
                if (!startedUsers.contains(user2)) {
                    usersToRemove.add(user2);
                }
            }
            this.mBackgroundUsersRestartedHere.removeAll(usersToRemove);
        }
        return startedUsers;
    }

    public boolean stopBackgroundUser(int userId) {
        if (userId == 0) {
            return false;
        }
        if (userId == ActivityManager.getCurrentUser()) {
            Log.i("CAR.USER", "stopBackgroundUser, already a FG user:" + userId);
            return false;
        }
        try {
            int r = this.mAm.stopUserWithDelayedLocking(userId, true, (IStopUserCallback) null);
            if (r == 0) {
                synchronized (this.mLockUser) {
                    Integer user = Integer.valueOf(userId);
                    this.mBackgroundUsersRestartedHere.remove(user);
                }
            } else {
                if (r == -2) {
                    return false;
                }
                Log.i("CAR.USER", "stopBackgroundUser failed, user:" + userId + " err:" + r);
                return false;
            }
        } catch (RemoteException e) {
            Log.w("CAR.USER", "error while stopping user", e);
        }
        return true;
    }

    public void onUserLifecycleEvent(int eventType, long timestampMs, int fromUserId, int toUserId) {
        if (eventType == 2) {
            onUserSwitching(fromUserId, toUserId);
        } else if (eventType == 4) {
            onUserUnlocked(toUserId);
        }
        final CarUserManager.UserLifecycleEvent event = new CarUserManager.UserLifecycleEvent(eventType, fromUserId, toUserId);
        this.mHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserService$n4AkY7V934xqVsNEeoEwVQPuf5g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onUserLifecycleEvent$13$CarUserService(event);
            }
        });
        if (timestampMs != 0) {
            this.mUserMetrics.onEvent(eventType, timestampMs, fromUserId, toUserId);
        }
    }

    public /* synthetic */ void lambda$onUserLifecycleEvent$13$CarUserService(CarUserManager.UserLifecycleEvent event) {
        handleNotifyServiceUserLifecycleListeners(event);
        handleNotifyAppUserLifecycleListeners(event);
    }

    public void onFirstUserUnlocked(int userId, long timestampMs, long duration, int halResponseTime) {
        this.mUserMetrics.logFirstUnlockedUser(userId, timestampMs, duration, halResponseTime);
    }

    private void sendPostSwitchToHalLocked(int userId) {
        int i;
        int i2 = this.mUserIdForUserSwitchInProcess;
        if (i2 == -10000 || i2 != userId || (i = this.mRequestIdForUserSwitchInProcess) == 0) {
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "No user switch request Id. No android post switch sent.");
            }
        } else {
            postSwitchHalResponse(i, i2);
            this.mUserIdForUserSwitchInProcess = -10000;
            this.mRequestIdForUserSwitchInProcess = 0;
        }
    }

    private void handleNotifyAppUserLifecycleListeners(CarUserManager.UserLifecycleEvent event) {
        int listenersSize = this.mAppLifecycleListeners.size();
        if (listenersSize == 0) {
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "No app listener to be notified of " + event);
                return;
            }
            return;
        }
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "Notifying " + listenersSize + " app listeners of " + event);
        }
        int userId = event.getUserId();
        TimingsTraceLog t = new TimingsTraceLog("CAR.USER", PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
        int eventType = event.getEventType();
        t.traceBegin("notify-app-listeners-user-" + userId + "-event-" + eventType);
        for (int i = 0; i < listenersSize; i++) {
            int uid = this.mAppLifecycleListeners.keyAt(i);
            IResultReceiver listener = this.mAppLifecycleListeners.valueAt(i);
            Bundle data = new Bundle();
            data.putInt("action", eventType);
            int fromUserId = event.getPreviousUserId();
            if (fromUserId != -10000) {
                data.putInt("previous_user", fromUserId);
            }
            if (Log.isLoggable("CAR.USER", 3)) {
                Log.d("CAR.USER", "Notifying listener for uid " + uid);
            }
            EventLog.writeEvent(150121, Integer.valueOf(uid), Integer.valueOf(eventType), Integer.valueOf(fromUserId), Integer.valueOf(userId));
            try {
                try {
                    t.traceBegin("notify-app-listener-uid-" + uid);
                    listener.send(userId, data);
                } catch (RemoteException e) {
                    Log.e("CAR.USER", "Error calling lifecycle listener", e);
                }
                t.traceEnd();
            } finally {
                t.traceEnd();
            }
        }
    }

    private void handleNotifyServiceUserLifecycleListeners(CarUserManager.UserLifecycleEvent event) {
        TimingsTraceLog t = new TimingsTraceLog("CAR.USER", PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
        if (this.mUserLifecycleListeners.isEmpty()) {
            Log.w("CAR.USER", "Not notifying internal UserLifecycleListeners");
            return;
        }
        if (Log.isLoggable("CAR.USER", 3)) {
            Log.d("CAR.USER", "Notifying " + this.mUserLifecycleListeners.size() + " service listeners of " + event);
        }
        int userId = event.getUserId();
        int eventType = event.getEventType();
        t.traceBegin("notify-listeners-user-" + userId + "-event-" + eventType);
        for (CarUserManager.UserLifecycleListener listener : this.mUserLifecycleListeners) {
            String listenerName = FunctionalUtils.getLambdaName(listener);
            EventLog.writeEvent(150122, listenerName, Integer.valueOf(eventType), Integer.valueOf(event.getPreviousUserId()), Integer.valueOf(userId));
            try {
                try {
                    t.traceBegin("notify-listener-" + listenerName);
                    listener.onEvent(event);
                } catch (RuntimeException e) {
                    Log.e("CAR.USER", "Exception raised when invoking onEvent for " + listenerName, e);
                }
                t.traceEnd();
            } finally {
                t.traceEnd();
            }
        }
    }

    private void onUserSwitching(int fromUserId, int toUserId) {
        Log.i("CAR.USER", "onUserSwitching() callback for user " + toUserId);
        TimingsTraceLog t = new TimingsTraceLog("CAR.USER", PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED);
        t.traceBegin("onUserSwitching-" + toUserId);
        notifyHalLegacySwitch(fromUserId, toUserId);
        this.mCarUserManagerHelper.setLastActiveUser(toUserId);
        int i = this.mLastPassengerId;
        if (i != -10000) {
            stopPassengerInternal(i, false);
        }
        if (this.mEnablePassengerSupport && isPassengerDisplayAvailable()) {
            setupPassengerUser();
            startFirstPassenger(toUserId);
        }
        t.traceEnd();
    }

    private void notifyHalLegacySwitch(int fromUserId, int toUserId) {
        synchronized (this.mLockUser) {
            if (this.mUserIdForUserSwitchInProcess != -10000) {
                if (Log.isLoggable("CAR.USER", 3)) {
                    Log.d("CAR.USER", "notifyHalLegacySwitch(" + fromUserId + ", " + toUserId + "): not needed, normal switch for " + this.mUserIdForUserSwitchInProcess);
                }
                return;
            }
            if (isUserHalSupported()) {
                UsersInfo usersInfo = UserHalHelper.newUsersInfo(this.mUserManager, fromUserId);
                SwitchUserRequest request = createUserSwitchRequest(toUserId, usersInfo);
                this.mHal.legacyUserSwitch(request);
            }
        }
    }

    public void runOnUser0Unlock(Runnable r) {
        Objects.requireNonNull(r, "runnable cannot be null");
        boolean runNow = false;
        synchronized (this.mLockUser) {
            if (this.mUser0Unlocked) {
                runNow = true;
            } else {
                this.mUser0UnlockTasks.add(r);
            }
        }
        if (runNow) {
            r.run();
        }
    }

    ArrayList<Integer> getBackgroundUsersToRestart() {
        ArrayList<Integer> backgroundUsersToRestart;
        synchronized (this.mLockUser) {
            backgroundUsersToRestart = new ArrayList<>(this.mBackgroundUsersToRestart);
        }
        return backgroundUsersToRestart;
    }

    private void setSystemUserRestrictions() {
        LocationManager locationManager = (LocationManager) this.mContext.getSystemService("location");
        locationManager.setLocationEnabledForUser(false, UserHandle.of(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void assignDefaultIcon(UserInfo userInfo) {
        int idForIcon = userInfo.isGuest() ? -10000 : userInfo.id;
        Bitmap bitmap = UserIcons.convertToBitmap(UserIcons.getDefaultUserIcon(this.mContext.getResources(), idForIcon, false));
        this.mUserManager.setUserIcon(userInfo.id, bitmap);
    }

    private List<UserInfo> getUsers(UserFilter filter) {
        List<UserInfo> users = this.mUserManager.getUsers(true);
        Iterator<UserInfo> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserInfo user = iterator.next();
            if (!filter.isEligibleUser(user)) {
                iterator.remove();
            }
        }
        return users;
    }

    private static void checkManageUsersPermission(String message) {
        checkAtLeastOnePermission(message, "android.permission.MANAGE_USERS");
    }

    private static void checkManageOrCreateUsersPermission(String message) {
        checkAtLeastOnePermission(message, "android.permission.MANAGE_USERS", "android.permission.CREATE_USERS");
    }

    private static void checkManageUsersOrDumpPermission(String message) {
        checkAtLeastOnePermission(message, "android.permission.MANAGE_USERS", "android.permission.DUMP");
    }

    private void checkInteractAcrossUsersPermission(String message) {
        checkAtLeastOnePermission(message, "android.permission.INTERACT_ACROSS_USERS", "android.permission.INTERACT_ACROSS_USERS_FULL");
    }

    private static void checkAtLeastOnePermission(String message, String... permissions) {
        int callingUid = Binder.getCallingUid();
        if (!hasAtLeastOnePermissionGranted(callingUid, permissions)) {
            throw new SecurityException("You need one of " + Arrays.toString(permissions) + " to: " + message);
        }
    }

    private static boolean hasAtLeastOnePermissionGranted(int uid, String... permissions) {
        for (String permission : permissions) {
            if (ActivityManager.checkComponentPermission(permission, uid, -1, true) == 0) {
                return true;
            }
        }
        return false;
    }

    private int getNumberOfManagedProfiles(int userId) {
        List<UserInfo> users = this.mUserManager.getUsers(true);
        int managedProfilesCount = 0;
        for (UserInfo user : users) {
            if (user.isManagedProfile() && user.profileGroupId == userId) {
                managedProfilesCount++;
            }
        }
        return managedProfilesCount;
    }

    private boolean startFirstPassenger(int driverId) {
        int zoneId = getAvailablePassengerZone();
        if (zoneId == -1) {
            Log.w("CAR.USER", "passenger occupant zone is not found");
            return false;
        }
        List<UserInfo> passengers = getPassengers(driverId);
        if (passengers.size() < 1) {
            Log.w("CAR.USER", "passenger is not found");
            return false;
        }
        int passengerId = passengers.get(0).id;
        if (startPassenger(passengerId, zoneId)) {
            return true;
        }
        Log.w("CAR.USER", "cannot start passenger " + passengerId);
        return false;
    }

    private int getAvailablePassengerZone() {
        int[] occupantTypes = {1, 2};
        for (int occupantType : occupantTypes) {
            int zoneId = getZoneId(occupantType);
            if (zoneId != -1) {
                return zoneId;
            }
        }
        return -1;
    }

    private void setupPassengerUser() {
        int currentUser = ActivityManager.getCurrentUser();
        int profileCount = getNumberOfManagedProfiles(currentUser);
        if (profileCount > 0) {
            Log.w("CAR.USER", "max profile of user" + currentUser + " is exceeded: current profile count is " + profileCount);
            return;
        }
        UserInfo passenger = createPassenger("Passenger", currentUser);
        if (passenger == null) {
            Log.w("CAR.USER", "cannot create a passenger user");
        }
    }

    private List<CarOccupantZoneManager.OccupantZoneInfo> getOccupantZones(int occupantType) {
        synchronized (this.mLockHelper) {
            if (this.mZoneUserBindingHelper == null) {
                Log.w("CAR.USER", "implementation is not delegated");
                return new ArrayList();
            }
            ZoneUserBindingHelper helper = this.mZoneUserBindingHelper;
            return helper.getOccupantZones(occupantType);
        }
    }

    private boolean assignUserToOccupantZone(int userId, int zoneId) {
        synchronized (this.mLockHelper) {
            if (this.mZoneUserBindingHelper == null) {
                Log.w("CAR.USER", "implementation is not delegated");
                return false;
            }
            ZoneUserBindingHelper helper = this.mZoneUserBindingHelper;
            return helper.assignUserToOccupantZone(userId, zoneId);
        }
    }

    private boolean unassignUserFromOccupantZone(int userId) {
        synchronized (this.mLockHelper) {
            if (this.mZoneUserBindingHelper == null) {
                Log.w("CAR.USER", "implementation is not delegated");
                return false;
            }
            ZoneUserBindingHelper helper = this.mZoneUserBindingHelper;
            return helper.unassignUserFromOccupantZone(userId);
        }
    }

    private boolean isPassengerDisplayAvailable() {
        synchronized (this.mLockHelper) {
            if (this.mZoneUserBindingHelper == null) {
                Log.w("CAR.USER", "implementation is not delegated");
                return false;
            }
            ZoneUserBindingHelper helper = this.mZoneUserBindingHelper;
            return helper.isPassengerDisplayAvailable();
        }
    }

    private int getZoneId(int occupantType) {
        List<CarOccupantZoneManager.OccupantZoneInfo> zoneInfos = getOccupantZones(occupantType);
        if (zoneInfos.size() > 0) {
            return zoneInfos.get(0).zoneId;
        }
        return -1;
    }
}
