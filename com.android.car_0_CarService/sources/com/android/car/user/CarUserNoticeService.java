package com.android.car.user;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.car.CarNotConnectedException;
import android.car.hardware.power.CarPowerManager;
import android.car.user.CarUserManager;
import android.car.user.IUserNotice;
import android.car.user.IUserNoticeUI;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;
import com.android.car.CarLocalServices;
import com.android.car.CarLog;
import com.android.car.CarServiceBase;
import com.android.car.R;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes3.dex */
public final class CarUserNoticeService implements CarServiceBase {
    private static final long KEYGUARD_POLLING_INTERVAL_MS = 100;
    private CarPowerManager mCarPowerManager;
    private final Context mContext;
    private final BroadcastReceiver mDisplayBroadcastReceiver;
    private final IUserNotice.Stub mIUserNotice;
    private int mIgnoreUserId;
    private int mKeyguardPollingCounter;
    private final Runnable mKeyguardPollingRunnable;
    private final Object mLock;
    private final Handler mMainHandler;
    private final CarPowerManager.CarPowerStateListener mPowerStateListener;
    private boolean mServiceBound;
    private final Intent mServiceIntent;
    private IUserNoticeUI mUiService;
    private final ServiceConnection mUiServiceConnection;
    private boolean mUiShown;
    private int mUserId;
    private final CarUserManager.UserLifecycleListener mUserLifecycleListener;

    public /* synthetic */ void lambda$new$1$CarUserNoticeService(final CarUserManager.UserLifecycleEvent event) {
        if (Log.isLoggable(CarLog.TAG_USER, 3)) {
            Log.d(CarLog.TAG_USER, "onEvent(" + event + ")");
        }
        if (2 == event.getEventType()) {
            this.mMainHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$ST6o11-R_Kr0aCIWvaNWyCT6ghg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$new$0$CarUserNoticeService(event);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$0$CarUserNoticeService(CarUserManager.UserLifecycleEvent event) {
        stopUi(true);
        synchronized (this.mLock) {
            this.mUserId = event.getUserId();
        }
        startNoticeUiIfNecessary();
    }

    /* JADX INFO: renamed from: com.android.car.user.CarUserNoticeService$1, reason: invalid class name */
    class AnonymousClass1 implements CarPowerManager.CarPowerStateListener {
        AnonymousClass1() {
        }

        public void onStateChanged(int state) {
            if (state == 7) {
                CarUserNoticeService.this.mMainHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$1$zSN9Fij9jTfIW2o8EOU4fCy3ksA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onStateChanged$0$CarUserNoticeService$1();
                    }
                });
            } else if (state == 6) {
                CarUserNoticeService.this.mMainHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$1$eBAi8uYe46Orfa9fEIbvjOokwzc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onStateChanged$1$CarUserNoticeService$1();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onStateChanged$0$CarUserNoticeService$1() {
            CarUserNoticeService.this.stopUi(true);
        }

        public /* synthetic */ void lambda$onStateChanged$1$CarUserNoticeService$1() {
            CarUserNoticeService.this.startNoticeUiIfNecessary();
        }
    }

    /* JADX INFO: renamed from: com.android.car.user.CarUserNoticeService$3, reason: invalid class name */
    class AnonymousClass3 extends IUserNotice.Stub {
        AnonymousClass3() {
        }

        public /* synthetic */ void lambda$onDialogDismissed$0$CarUserNoticeService$3() {
            CarUserNoticeService.this.stopUi(false);
        }

        public void onDialogDismissed() {
            CarUserNoticeService.this.mMainHandler.post(new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$3$y4lwa4EbWS9amYSPLLd0k2IVIEY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDialogDismissed$0$CarUserNoticeService$3();
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$2$CarUserNoticeService() {
        synchronized (this.mLock) {
            this.mKeyguardPollingCounter++;
        }
        startNoticeUiIfNecessary();
    }

    public CarUserNoticeService(Context context) {
        this(context, new Handler(Looper.getMainLooper()));
    }

    CarUserNoticeService(Context context, Handler handler) {
        this.mLock = new Object();
        this.mServiceBound = false;
        this.mUiShown = false;
        this.mUserId = -10000;
        this.mIgnoreUserId = -10000;
        this.mUserLifecycleListener = new CarUserManager.UserLifecycleListener() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$1Se12MbdB6yxinMmXF3vvbFzApo
            public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
                this.f$0.lambda$new$1$CarUserNoticeService(userLifecycleEvent);
            }
        };
        this.mPowerStateListener = new AnonymousClass1();
        this.mDisplayBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.car.user.CarUserNoticeService.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                    if (CarUserNoticeService.this.isDisplayOn()) {
                        Log.i(CarLog.TAG_USER, "SCREEN_OFF while display is already on");
                        return;
                    } else {
                        Log.i(CarLog.TAG_USER, "Display off, stopping UI");
                        CarUserNoticeService.this.stopUi(true);
                        return;
                    }
                }
                if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                    if (!CarUserNoticeService.this.isDisplayOn()) {
                        Log.i(CarLog.TAG_USER, "SCREEN_ON while display is already off");
                    } else {
                        Log.i(CarLog.TAG_USER, "Display on, starting UI");
                        CarUserNoticeService.this.startNoticeUiIfNecessary();
                    }
                }
            }
        };
        this.mIUserNotice = new AnonymousClass3();
        this.mUiServiceConnection = new ServiceConnection() { // from class: com.android.car.user.CarUserNoticeService.4
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                IUserNoticeUI binder;
                synchronized (CarUserNoticeService.this.mLock) {
                    if (CarUserNoticeService.this.mServiceBound) {
                        IUserNoticeUI binder2 = IUserNoticeUI.Stub.asInterface(service);
                        try {
                            binder2.setCallbackBinder(CarUserNoticeService.this.mIUserNotice);
                            binder = binder2;
                        } catch (RemoteException e) {
                            Log.w(CarLog.TAG_USER, "UserNoticeUI Service died", e);
                            binder = null;
                        }
                        synchronized (CarUserNoticeService.this.mLock) {
                            CarUserNoticeService.this.mUiService = binder;
                        }
                    }
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                CarUserNoticeService.this.stopUi(true);
            }
        };
        this.mKeyguardPollingRunnable = new Runnable() { // from class: com.android.car.user.-$$Lambda$CarUserNoticeService$rZAkpRonj7DK7Rq3NPc_RvvjimE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$2$CarUserNoticeService();
            }
        };
        this.mMainHandler = handler;
        Resources res = context.getResources();
        String componentName = res.getString(R.string.config_userNoticeUiService);
        if (componentName.isEmpty()) {
            this.mContext = null;
            this.mServiceIntent = null;
        } else {
            this.mContext = context;
            Intent intent = new Intent();
            this.mServiceIntent = intent;
            intent.setComponent(ComponentName.unflattenFromString(componentName));
        }
    }

    public void ignoreUserNotice(int userId) {
        synchronized (this.mLock) {
            this.mIgnoreUserId = userId;
        }
    }

    private boolean checkKeyguardLockedWithPolling() {
        this.mMainHandler.removeCallbacks(this.mKeyguardPollingRunnable);
        IWindowManager wm = WindowManagerGlobal.getWindowManagerService();
        boolean locked = true;
        if (wm != null) {
            try {
                locked = wm.isKeyguardLocked();
            } catch (RemoteException e) {
                Log.w(CarLog.TAG_USER, "system server crashed", e);
            }
        }
        if (locked) {
            this.mMainHandler.postDelayed(this.mKeyguardPollingRunnable, KEYGUARD_POLLING_INTERVAL_MS);
        }
        return locked;
    }

    private boolean isNoticeScreenEnabledInSetting(int userId) {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "android.car.ENABLE_INITIAL_NOTICE_SCREEN_TO_USER", 1, userId) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDisplayOn() {
        PowerManager pm = (PowerManager) this.mContext.getSystemService(PowerManager.class);
        if (pm == null) {
            return false;
        }
        return pm.isInteractive();
    }

    private boolean grantSystemAlertWindowPermission(int userId) {
        AppOpsManager appOpsManager = (AppOpsManager) this.mContext.getSystemService(AppOpsManager.class);
        if (appOpsManager == null) {
            Log.w(CarLog.TAG_USER, "AppOpsManager not ready yet");
            return false;
        }
        String packageName = this.mServiceIntent.getComponent().getPackageName();
        try {
            int packageUid = this.mContext.getPackageManager().getPackageUidAsUser(packageName, userId);
            appOpsManager.setMode(24, packageUid, packageName, 0);
            Log.i(CarLog.TAG_USER, "Granted SYSTEM_ALERT_WINDOW permission to package:" + packageName + " package uid:" + packageUid);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.wtf(CarLog.TAG_USER, "Target package for config_userNoticeUiService not found:" + packageName + " userId:" + userId);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startNoticeUiIfNecessary() {
        synchronized (this.mLock) {
            if (!this.mUiShown && !this.mServiceBound) {
                int userId = this.mUserId;
                if (this.mIgnoreUserId == userId) {
                    return;
                }
                this.mIgnoreUserId = -10000;
                if (userId == -10000 || userId == 0 || !isNoticeScreenEnabledInSetting(userId) || userId != ActivityManager.getCurrentUser() || !isDisplayOn() || checkKeyguardLockedWithPolling() || !grantSystemAlertWindowPermission(userId)) {
                    return;
                }
                boolean bound = this.mContext.bindServiceAsUser(this.mServiceIntent, this.mUiServiceConnection, 1, UserHandle.of(userId));
                if (bound) {
                    Log.i(CarLog.TAG_USER, "Bound UserNoticeUI Service Service:" + this.mServiceIntent);
                    synchronized (this.mLock) {
                        this.mServiceBound = true;
                        this.mUiShown = true;
                    }
                    return;
                }
                Log.w(CarLog.TAG_USER, "Cannot bind to UserNoticeUI Service Service" + this.mServiceIntent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopUi(boolean clearUiShown) {
        boolean serviceBound;
        this.mMainHandler.removeCallbacks(this.mKeyguardPollingRunnable);
        synchronized (this.mLock) {
            this.mUiService = null;
            serviceBound = this.mServiceBound;
            this.mServiceBound = false;
            if (clearUiShown) {
                this.mUiShown = false;
            }
        }
        if (serviceBound) {
            Log.i(CarLog.TAG_USER, "Unbound UserNoticeUI Service");
            this.mContext.unbindService(this.mUiServiceConnection);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        CarPowerManager carPowerManager;
        if (this.mServiceIntent == null) {
            return;
        }
        synchronized (this.mLock) {
            carPowerManager = CarLocalServices.createCarPowerManager(this.mContext);
            this.mCarPowerManager = carPowerManager;
        }
        try {
            carPowerManager.setListener(this.mPowerStateListener);
            CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
            userService.addUserLifecycleListener(this.mUserLifecycleListener);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            this.mContext.registerReceiver(this.mDisplayBroadcastReceiver, intentFilter);
        } catch (CarNotConnectedException e) {
            throw new RuntimeException("CarNotConnectedException from CarPowerManager", e);
        }
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        CarPowerManager carPowerManager;
        if (this.mServiceIntent == null) {
            return;
        }
        this.mContext.unregisterReceiver(this.mDisplayBroadcastReceiver);
        CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
        userService.removeUserLifecycleListener(this.mUserLifecycleListener);
        synchronized (this.mLock) {
            carPowerManager = this.mCarPowerManager;
            this.mUserId = -10000;
        }
        carPowerManager.clearListener();
        stopUi(true);
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        synchronized (this.mLock) {
            if (this.mServiceIntent == null) {
                writer.println("*CarUserNoticeService* disabled");
                return;
            }
            if (this.mUserId == -10000) {
                writer.println("*CarUserNoticeService* User not started yet.");
                return;
            }
            writer.println("*CarUserNoticeService* mServiceIntent:" + this.mServiceIntent + ", mUserId:" + this.mUserId + ", mUiShown:" + this.mUiShown + ", mServiceBound:" + this.mServiceBound + ", mKeyguardPollingCounter:" + this.mKeyguardPollingCounter + ", Setting enabled:" + isNoticeScreenEnabledInSetting(this.mUserId) + ", Ignore User: " + this.mIgnoreUserId);
        }
    }
}
