package android.car.user;

import android.annotation.SystemApi;
import android.car.Car;
import android.car.CarManagerBase;
import android.car.ICarUserService;
import android.content.pm.UserInfo;
import android.hardware.bydauto.TsNormalNum;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.sysprop.CarProperties;
import android.util.ArrayMap;
import android.util.EventLog;
import android.util.Log;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.car.EventLogTags;
import com.android.internal.infra.AndroidFuture;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class CarUserManager extends CarManagerBase {
    public static final String BUNDLE_PARAM_ACTION = "action";
    public static final String BUNDLE_PARAM_PREVIOUS_USER_ID = "previous_user";
    private static final boolean DBG = false;
    private static final int HAL_TIMEOUT_MS = ((Integer) CarProperties.user_hal_timeout().orElse(Integer.valueOf(TsNormalNum.THOUSAND_FIVE))).intValue();
    private static final String TAG = "CarUserManager";

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_STARTING = 1;

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_STOPPED = 6;

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_STOPPING = 5;

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_SWITCHING = 2;

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_UNLOCKED = 4;

    @SystemApi
    public static final int USER_LIFECYCLE_EVENT_TYPE_UNLOCKING = 3;

    @GuardedBy({"mLock"})
    private ArrayMap<UserLifecycleListener, Executor> mListeners;
    private final Object mLock;

    @GuardedBy({"mLock"})
    private LifecycleResultReceiver mReceiver;
    private final ICarUserService mService;
    private final UserManager mUserManager;

    /* JADX INFO: Access modifiers changed from: private */
    class LifecycleResultReceiver extends IResultReceiver.Stub {
        private LifecycleResultReceiver() {
        }

        public void send(int i, Bundle bundle) {
            ArrayMap arrayMap;
            if (bundle == null) {
                Log.w(CarUserManager.TAG, "Received result (" + i + ") without data");
                return;
            }
            int i2 = bundle.getInt(CarUserManager.BUNDLE_PARAM_PREVIOUS_USER_ID, -10000);
            int i3 = bundle.getInt(CarUserManager.BUNDLE_PARAM_ACTION);
            final UserLifecycleEvent userLifecycleEvent = new UserLifecycleEvent(i3, i2, i);
            synchronized (CarUserManager.this.mLock) {
                arrayMap = CarUserManager.this.mListeners;
            }
            if (arrayMap == null) {
                Log.w(CarUserManager.TAG, "No listeners for event " + userLifecycleEvent);
                return;
            }
            int size = arrayMap.size();
            EventLog.writeEvent(EventLogTags.CAR_USER_MGR_NOTIFY_LIFECYCLE_LISTENER, Integer.valueOf(size), Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i));
            for (int i4 = 0; i4 < size; i4++) {
                final UserLifecycleListener userLifecycleListener = (UserLifecycleListener) arrayMap.keyAt(i4);
                ((Executor) arrayMap.valueAt(i4)).execute(new Runnable() { // from class: android.car.user.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        userLifecycleListener.onEvent(userLifecycleEvent);
                    }
                });
            }
        }
    }

    @SystemApi
    public static final class UserLifecycleEvent {
        private final int mEventType;
        private final int mPreviousUserId;
        private final int mUserId;

        public UserLifecycleEvent(int i, int i2) {
            this(i, -10000, i2);
        }

        public UserLifecycleEvent(int i, int i2, int i3) {
            this.mEventType = i;
            this.mPreviousUserId = i2;
            this.mUserId = i3;
        }

        public int getEventType() {
            return this.mEventType;
        }

        public UserHandle getPreviousUserHandle() {
            int i = this.mPreviousUserId;
            if (i == -10000) {
                return null;
            }
            return UserHandle.of(i);
        }

        public int getPreviousUserId() {
            return this.mPreviousUserId;
        }

        public UserHandle getUserHandle() {
            return UserHandle.of(this.mUserId);
        }

        public int getUserId() {
            return this.mUserId;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("Event[type=");
            sb.append(CarUserManager.lifecycleEventTypeToString(this.mEventType));
            if (this.mPreviousUserId != -10000) {
                sb.append(",from=");
                sb.append(this.mPreviousUserId);
                str = ",to=";
            } else {
                str = ",user=";
            }
            sb.append(str);
            sb.append(this.mUserId);
            sb.append(']');
            return sb.toString();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UserLifecycleEventType {
    }

    @SystemApi
    public interface UserLifecycleListener {
        void onEvent(UserLifecycleEvent userLifecycleEvent);
    }

    public interface UserSwitchUiCallback {
        void showUserSwitchDialog(int i);
    }

    private final class UserSwitchUiCallbackReceiver extends IResultReceiver.Stub {
        private final UserSwitchUiCallback mUserSwitchUiCallback;

        UserSwitchUiCallbackReceiver(UserSwitchUiCallback userSwitchUiCallback) {
            this.mUserSwitchUiCallback = userSwitchUiCallback;
        }

        public void send(int i, Bundle bundle) {
            this.mUserSwitchUiCallback.showUserSwitchDialog(i);
        }
    }

    @VisibleForTesting
    public CarUserManager(Car car, ICarUserService iCarUserService, UserManager userManager) {
        super(car);
        this.mLock = new Object();
        this.mService = iCarUserService;
        this.mUserManager = userManager;
    }

    public CarUserManager(Car car, IBinder iBinder) {
        this(car, ICarUserService.Stub.asInterface(iBinder), UserManager.get(car.getContext()));
    }

    private boolean isHeadlessSystemUser(int i) {
        return i == 0 && UserManager.isHeadlessSystemUserMode();
    }

    public static String lifecycleEventTypeToString(int i) {
        switch (i) {
            case 1:
                return "STARTING";
            case 2:
                return "SWITCHING";
            case 3:
                return "UNLOCKING";
            case 4:
                return "UNLOCKED";
            case 5:
                return "STOPPING";
            case 6:
                return "STOPPED";
            default:
                return "UNKNOWN-" + i;
        }
    }

    private AndroidFuture<UserSwitchResult> newSwitchResuiltForFailure(int i) {
        AndroidFuture<UserSwitchResult> androidFuture = new AndroidFuture<>();
        androidFuture.complete(new UserSwitchResult(i, null));
        return androidFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGuestCreated(UserInfo userInfo) {
        Settings.Secure.putStringForUser(getContext().getContentResolver(), "skip_first_use_hints", "1", userInfo.id);
    }

    private static String safeName(String str) {
        if (str == null) {
            return str;
        }
        return str.length() + "_chars";
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0047 A[Catch: all -> 0x0055, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0016, B:11:0x0020, B:13:0x0029, B:15:0x0034, B:18:0x0040, B:19:0x0043, B:21:0x0047, B:22:0x004e, B:23:0x0053), top: B:28:0x0011, inners: #1 }] */
    @android.annotation.SystemApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addListener(java.util.concurrent.Executor r6, android.car.user.CarUserManager.UserLifecycleListener r7) {
        /*
            r5 = this;
            java.lang.String r0 = "executor cannot be null"
            java.util.Objects.requireNonNull(r6, r0)
            java.lang.String r0 = "listener cannot be null"
            java.util.Objects.requireNonNull(r7, r0)
            int r0 = android.os.Process.myUid()
            java.lang.Object r1 = r5.mLock
            monitor-enter(r1)
            android.util.ArrayMap<android.car.user.CarUserManager$UserLifecycleListener, java.util.concurrent.Executor> r2 = r5.mListeners     // Catch: java.lang.Throwable -> L55
            r3 = 1
            if (r2 == 0) goto L1f
            boolean r2 = r2.containsKey(r7)     // Catch: java.lang.Throwable -> L55
            if (r2 != 0) goto L1d
            goto L1f
        L1d:
            r2 = 0
            goto L20
        L1f:
            r2 = r3
        L20:
            java.lang.String r4 = "already called for this listener"
            com.android.internal.util.Preconditions.checkState(r2, r4)     // Catch: java.lang.Throwable -> L55
            android.car.user.CarUserManager$LifecycleResultReceiver r2 = r5.mReceiver     // Catch: java.lang.Throwable -> L55
            if (r2 != 0) goto L43
            android.car.user.CarUserManager$LifecycleResultReceiver r2 = new android.car.user.CarUserManager$LifecycleResultReceiver     // Catch: java.lang.Throwable -> L55
            r4 = 0
            r2.<init>()     // Catch: java.lang.Throwable -> L55
            r5.mReceiver = r2     // Catch: java.lang.Throwable -> L55
            r2 = 150171(0x24a9b, float:2.10434E-40)
            android.util.EventLog.writeEvent(r2, r0)     // Catch: android.os.RemoteException -> L3f java.lang.Throwable -> L55
            android.car.ICarUserService r0 = r5.mService     // Catch: android.os.RemoteException -> L3f java.lang.Throwable -> L55
            android.car.user.CarUserManager$LifecycleResultReceiver r2 = r5.mReceiver     // Catch: android.os.RemoteException -> L3f java.lang.Throwable -> L55
            r0.setLifecycleListenerForUid(r2)     // Catch: android.os.RemoteException -> L3f java.lang.Throwable -> L55
            goto L43
        L3f:
            r0 = move-exception
            r5.handleRemoteExceptionFromCarService(r0)     // Catch: java.lang.Throwable -> L55
        L43:
            android.util.ArrayMap<android.car.user.CarUserManager$UserLifecycleListener, java.util.concurrent.Executor> r0 = r5.mListeners     // Catch: java.lang.Throwable -> L55
            if (r0 != 0) goto L4e
            android.util.ArrayMap r0 = new android.util.ArrayMap     // Catch: java.lang.Throwable -> L55
            r0.<init>(r3)     // Catch: java.lang.Throwable -> L55
            r5.mListeners = r0     // Catch: java.lang.Throwable -> L55
        L4e:
            android.util.ArrayMap<android.car.user.CarUserManager$UserLifecycleListener, java.util.concurrent.Executor> r0 = r5.mListeners     // Catch: java.lang.Throwable -> L55
            r0.put(r7, r6)     // Catch: java.lang.Throwable -> L55
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L55
            return
        L55:
            r6 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L55
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.car.user.CarUserManager.addListener(java.util.concurrent.Executor, android.car.user.CarUserManager$UserLifecycleListener):void");
    }

    public AndroidFuture<UserCreationResult> createGuest(String str) {
        return createUser(str, "android.os.usertype.full.GUEST", 0);
    }

    public AndroidFuture<UserCreationResult> createUser(String str, int i) {
        return createUser(str, "android.os.usertype.full.SECONDARY", i);
    }

    public AndroidFuture<UserCreationResult> createUser(String str, final String str2, final int i) {
        final int iMyUid = Process.myUid();
        try {
            AndroidFuture<UserCreationResult> androidFuture = new AndroidFuture<UserCreationResult>() { // from class: android.car.user.CarUserManager.2
                /* JADX INFO: Access modifiers changed from: protected */
                public void onCompleted(UserCreationResult userCreationResult, Throwable th) {
                    if (userCreationResult != null) {
                        EventLog.writeEvent(EventLogTags.CAR_USER_MGR_CREATE_USER_RESP, Integer.valueOf(iMyUid), Integer.valueOf(userCreationResult.getStatus()), userCreationResult.getErrorMessage());
                        UserInfo user = userCreationResult.getUser();
                        if (userCreationResult.isSuccess() && user != null && user.isGuest()) {
                            CarUserManager.this.onGuestCreated(user);
                        }
                    } else {
                        Log.w(CarUserManager.TAG, "createUser(" + str2 + "," + UserInfo.flagsToString(i) + ") failed: " + th);
                    }
                    super.onCompleted(userCreationResult, th);
                }
            };
            EventLog.writeEvent(EventLogTags.CAR_USER_MGR_CREATE_USER_REQ, Integer.valueOf(iMyUid), safeName(str), str2, Integer.valueOf(i));
            this.mService.createUser(str, str2, i, HAL_TIMEOUT_MS, androidFuture);
            return androidFuture;
        } catch (RemoteException e) {
            AndroidFuture androidFuture2 = new AndroidFuture();
            androidFuture2.complete(new UserCreationResult(4, null, null));
            return (AndroidFuture) handleRemoteExceptionFromCarService(e, androidFuture2);
        }
    }

    public UserIdentificationAssociationResponse getUserIdentificationAssociation(int... iArr) {
        Preconditions.checkArgument(!ArrayUtils.isEmpty(iArr), "must have at least one type");
        EventLog.writeEvent(EventLogTags.CAR_USER_MGR_GET_USER_AUTH_REQ, iArr.length);
        try {
            UserIdentificationAssociationResponse userIdentificationAssociation = this.mService.getUserIdentificationAssociation(iArr);
            if (userIdentificationAssociation != null) {
                int[] values = userIdentificationAssociation.getValues();
                EventLog.writeEvent(EventLogTags.CAR_USER_MGR_GET_USER_AUTH_RESP, values != null ? values.length : 0);
            }
            return userIdentificationAssociation;
        } catch (RemoteException e) {
            return (UserIdentificationAssociationResponse) handleRemoteExceptionFromCarService(e, null);
        }
    }

    public boolean isUserHalUserAssociationSupported() {
        try {
            return this.mService.isUserHalUserAssociationSupported();
        } catch (RemoteException e) {
            return ((Boolean) handleRemoteExceptionFromCarService(e, Boolean.FALSE)).booleanValue();
        }
    }

    public boolean isValidUser(int i) {
        List users = this.mUserManager.getUsers(true);
        for (int i2 = 0; i2 < users.size(); i2++) {
            if (((UserInfo) users.get(i2)).id == i && (i != 0 || !UserManager.isHeadlessSystemUserMode())) {
                return true;
            }
        }
        return false;
    }

    ExperimentalCarUserManager newExperimentalCarUserManager() {
        return new ExperimentalCarUserManager(this.mCar, this.mService);
    }

    @Override // android.car.CarManagerBase
    public void onCarDisconnected() {
    }

    @SystemApi
    public void removeListener(UserLifecycleListener userLifecycleListener) {
        Objects.requireNonNull(userLifecycleListener, "listener cannot be null");
        int iMyUid = Process.myUid();
        synchronized (this.mLock) {
            ArrayMap<UserLifecycleListener, Executor> arrayMap = this.mListeners;
            Preconditions.checkState(arrayMap != null && arrayMap.containsKey(userLifecycleListener), "not called for this listener yet");
            this.mListeners.remove(userLifecycleListener);
            if (this.mListeners.isEmpty()) {
                this.mListeners = null;
                if (this.mReceiver == null) {
                    Log.wtf(TAG, "removeListener(): receiver already null");
                    return;
                }
                EventLog.writeEvent(EventLogTags.CAR_USER_MGR_REMOVE_LISTENER, iMyUid);
                try {
                    this.mService.resetLifecycleListenerForUid();
                    this.mReceiver = null;
                } catch (RemoteException e) {
                    handleRemoteExceptionFromCarService(e);
                }
            }
        }
    }

    public UserRemovalResult removeUser(int i) {
        int iMyUid = Process.myUid();
        EventLog.writeEvent(EventLogTags.CAR_USER_MGR_REMOVE_USER_REQ, Integer.valueOf(iMyUid), Integer.valueOf(i));
        try {
            try {
                UserRemovalResult userRemovalResultRemoveUser = this.mService.removeUser(i);
                EventLog.writeEvent(EventLogTags.CAR_USER_MGR_REMOVE_USER_RESP, Integer.valueOf(iMyUid), Integer.valueOf(userRemovalResultRemoveUser.getStatus()));
                return userRemovalResultRemoveUser;
            } catch (RemoteException e) {
                UserRemovalResult userRemovalResult = (UserRemovalResult) handleRemoteExceptionFromCarService(e, new UserRemovalResult(4));
                EventLog.writeEvent(EventLogTags.CAR_USER_MGR_REMOVE_USER_RESP, Integer.valueOf(iMyUid), 4);
                return userRemovalResult;
            }
        } catch (Throwable th) {
            EventLog.writeEvent(EventLogTags.CAR_USER_MGR_REMOVE_USER_RESP, Integer.valueOf(iMyUid), 4);
            throw th;
        }
    }

    public AndroidFuture<UserIdentificationAssociationResponse> setUserIdentificationAssociation(final int[] iArr, final int[] iArr2) {
        Preconditions.checkArgument(!ArrayUtils.isEmpty(iArr), "must have at least one type");
        Preconditions.checkArgument(!ArrayUtils.isEmpty(iArr2), "must have at least one value");
        if (iArr.length != iArr2.length) {
            throw new IllegalArgumentException("types (" + Arrays.toString(iArr) + ") and values (" + Arrays.toString(iArr2) + ") should have the same length");
        }
        Integer[] numArr = new Integer[iArr.length * 2];
        for (int i = 0; i < iArr.length; i++) {
            int i2 = i * 2;
            numArr[i2] = Integer.valueOf(iArr[i]);
            numArr[i2 + 1] = Integer.valueOf(iArr2[i]);
        }
        EventLog.writeEvent(EventLogTags.CAR_USER_MGR_SET_USER_AUTH_REQ, numArr);
        try {
            AndroidFuture<UserIdentificationAssociationResponse> androidFuture = new AndroidFuture<UserIdentificationAssociationResponse>() { // from class: android.car.user.CarUserManager.3
                /* JADX INFO: Access modifiers changed from: protected */
                public void onCompleted(UserIdentificationAssociationResponse userIdentificationAssociationResponse, Throwable th) {
                    if (userIdentificationAssociationResponse != null) {
                        int[] values = userIdentificationAssociationResponse.getValues();
                        if (values != null) {
                            Object[] objArr = new Object[values.length];
                            for (int i3 = 0; i3 < values.length; i3++) {
                                objArr[i3] = Integer.valueOf(values[i3]);
                            }
                            EventLog.writeEvent(EventLogTags.CAR_USER_MGR_SET_USER_AUTH_RESP, objArr);
                        }
                    } else {
                        Log.w(CarUserManager.TAG, "setUserIdentificationAssociation(" + Arrays.toString(iArr) + ", " + Arrays.toString(iArr2) + ") failed: " + th);
                    }
                    super.onCompleted(userIdentificationAssociationResponse, th);
                }
            };
            this.mService.setUserIdentificationAssociation(HAL_TIMEOUT_MS, iArr, iArr2, androidFuture);
            return androidFuture;
        } catch (RemoteException e) {
            AndroidFuture androidFuture2 = new AndroidFuture();
            androidFuture2.complete(UserIdentificationAssociationResponse.forFailure());
            return (AndroidFuture) handleRemoteExceptionFromCarService(e, androidFuture2);
        }
    }

    public void setUserSwitchUiCallback(UserSwitchUiCallback userSwitchUiCallback) {
        Preconditions.checkArgument(userSwitchUiCallback != null, "Null callback");
        try {
            this.mService.setUserSwitchUiCallback(new UserSwitchUiCallbackReceiver(userSwitchUiCallback));
        } catch (RemoteException e) {
            handleRemoteExceptionFromCarService(e);
        }
    }

    public AndroidFuture<UserSwitchResult> switchUser(final int i) {
        final int iMyUid = Process.myUid();
        if (this.mUserManager.getUserSwitchability() != 0) {
            return newSwitchResuiltForFailure(104);
        }
        try {
            AndroidFuture<UserSwitchResult> androidFuture = new AndroidFuture<UserSwitchResult>() { // from class: android.car.user.CarUserManager.1
                /* JADX INFO: Access modifiers changed from: protected */
                public void onCompleted(UserSwitchResult userSwitchResult, Throwable th) {
                    if (userSwitchResult != null) {
                        EventLog.writeEvent(EventLogTags.CAR_USER_MGR_SWITCH_USER_RESP, Integer.valueOf(iMyUid), Integer.valueOf(userSwitchResult.getStatus()), userSwitchResult.getErrorMessage());
                    } else {
                        Log.w(CarUserManager.TAG, "switchUser(" + i + ") failed: " + th);
                    }
                    super.onCompleted(userSwitchResult, th);
                }
            };
            EventLog.writeEvent(EventLogTags.CAR_USER_MGR_SWITCH_USER_REQ, Integer.valueOf(iMyUid), Integer.valueOf(i));
            this.mService.switchUser(i, HAL_TIMEOUT_MS, androidFuture);
            return androidFuture;
        } catch (RemoteException e) {
            return (AndroidFuture) handleRemoteExceptionFromCarService(e, newSwitchResuiltForFailure(4));
        }
    }
}
