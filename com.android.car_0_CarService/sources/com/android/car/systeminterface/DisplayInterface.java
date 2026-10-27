package com.android.car.systeminterface;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.view.Display;
import android.view.InputDevice;
import androidx.core.view.InputDeviceCompat;
import com.android.car.CarLog;
import com.android.car.CarPowerManagementService;
import com.android.settingslib.display.BrightnessUtils;

/* JADX INFO: loaded from: classes3.dex */
public interface DisplayInterface {
    void refreshDisplayBrightness();

    void setDisplayBrightness(int i);

    void setDisplayState(boolean z);

    void startDisplayStateMonitoring(CarPowerManagementService carPowerManagementService);

    void stopDisplayStateMonitoring();

    public static class DefaultImpl implements DisplayInterface {
        private final ActivityManager mActivityManager;
        private final ContentResolver mContentResolver;
        private final Context mContext;
        private final DisplayManager mDisplayManager;
        private boolean mDisplayStateSet;
        private final InputManager mInputManager;
        private final int mMaximumBacklight;
        private final int mMinimumBacklight;
        private final PowerManager mPowerManager;
        private CarPowerManagementService mService;
        private final WakeLockInterface mWakeLockInterface;
        private final Object mLock = new Object();
        private int mLastBrightnessLevel = -1;
        private final ContentObserver mBrightnessObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.systeminterface.DisplayInterface.DefaultImpl.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                DefaultImpl.this.refreshDisplayBrightness();
            }
        };
        private final DisplayManager.DisplayListener mDisplayListener = new DisplayManager.DisplayListener() { // from class: com.android.car.systeminterface.DisplayInterface.DefaultImpl.2
            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayAdded(int displayId) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayRemoved(int displayId) {
            }

            @Override // android.hardware.display.DisplayManager.DisplayListener
            public void onDisplayChanged(int displayId) {
                if (displayId == 0) {
                    DefaultImpl.this.handleMainDisplayChanged();
                }
            }
        };
        private final BroadcastReceiver mUserChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.systeminterface.DisplayInterface.DefaultImpl.3
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                DefaultImpl.this.onUsersUpdate();
            }
        };

        DefaultImpl(Context context, WakeLockInterface wakeLockInterface) {
            this.mActivityManager = (ActivityManager) context.getSystemService("activity");
            this.mContext = context;
            this.mContentResolver = context.getContentResolver();
            this.mDisplayManager = (DisplayManager) context.getSystemService("display");
            this.mInputManager = (InputManager) this.mContext.getSystemService("input");
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            this.mPowerManager = powerManager;
            this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
            this.mMinimumBacklight = this.mPowerManager.getMinimumScreenBrightnessSetting();
            this.mWakeLockInterface = wakeLockInterface;
            this.mContext.registerReceiverAsUser(this.mUserChangeReceiver, UserHandle.ALL, new IntentFilter("android.intent.action.USER_SWITCHED"), null, null);
        }

        @Override // com.android.car.systeminterface.DisplayInterface
        public void refreshDisplayBrightness() {
            synchronized (this.mLock) {
                if (this.mService == null) {
                    Log.e(CarLog.TAG_POWER, "Could not set brightness: no CarPowerManagementService");
                    return;
                }
                int gamma = 65535;
                try {
                    int linear = Settings.System.getIntForUser(this.mContentResolver, "screen_brightness", ActivityManager.getCurrentUser());
                    gamma = BrightnessUtils.convertLinearToGamma(linear, this.mMinimumBacklight, this.mMaximumBacklight);
                } catch (Settings.SettingNotFoundException e) {
                    Log.e(CarLog.TAG_POWER, "Could not get SCREEN_BRIGHTNESS: " + e);
                }
                int percentBright = ((gamma * 100) + 32768) / 65535;
                this.mService.sendDisplayBrightness(percentBright);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void handleMainDisplayChanged() {
            boolean isOn = isMainDisplayOn();
            synchronized (this.mLock) {
                if (this.mDisplayStateSet == isOn) {
                    return;
                }
                CarPowerManagementService service = this.mService;
                service.handleMainDisplayChanged(isOn);
            }
        }

        private boolean isMainDisplayOn() {
            Display disp = this.mDisplayManager.getDisplay(0);
            return disp.getState() == 2;
        }

        @Override // com.android.car.systeminterface.DisplayInterface
        public void setDisplayBrightness(int percentBright) {
            synchronized (this.mLock) {
                if (percentBright == this.mLastBrightnessLevel) {
                    return;
                }
                this.mLastBrightnessLevel = percentBright;
                int gamma = ((65535 * percentBright) + 50) / 100;
                int linear = BrightnessUtils.convertGammaToLinear(gamma, this.mMinimumBacklight, this.mMaximumBacklight);
                Settings.System.putIntForUser(this.mContentResolver, "screen_brightness", linear, ActivityManager.getCurrentUser());
            }
        }

        @Override // com.android.car.systeminterface.DisplayInterface
        public void startDisplayStateMonitoring(CarPowerManagementService service) {
            synchronized (this.mLock) {
                this.mService = service;
                this.mDisplayStateSet = isMainDisplayOn();
            }
            this.mContentResolver.registerContentObserver(Settings.System.getUriFor("screen_brightness"), false, this.mBrightnessObserver, -1);
            this.mDisplayManager.registerDisplayListener(this.mDisplayListener, service.getHandler());
            refreshDisplayBrightness();
        }

        @Override // com.android.car.systeminterface.DisplayInterface
        public void stopDisplayStateMonitoring() {
            this.mDisplayManager.unregisterDisplayListener(this.mDisplayListener);
            this.mContentResolver.unregisterContentObserver(this.mBrightnessObserver);
        }

        @Override // com.android.car.systeminterface.DisplayInterface
        public void setDisplayState(boolean on) {
            synchronized (this.mLock) {
                this.mDisplayStateSet = on;
            }
            if (on) {
                this.mWakeLockInterface.switchToFullWakeLock();
                Log.i(CarLog.TAG_POWER, "on display");
                this.mPowerManager.wakeUp(SystemClock.uptimeMillis());
            } else {
                this.mWakeLockInterface.switchToPartialWakeLock();
                Log.i(CarLog.TAG_POWER, "off display");
                this.mPowerManager.goToSleep(SystemClock.uptimeMillis());
            }
            for (int deviceId : this.mInputManager.getInputDeviceIds()) {
                InputDevice inputDevice = this.mInputManager.getInputDevice(deviceId);
                if (inputDevice != null && (inputDevice.getSources() & InputDeviceCompat.SOURCE_TOUCHSCREEN) == 4098) {
                    if (on) {
                        this.mInputManager.enableInputDevice(deviceId);
                    } else {
                        this.mInputManager.disableInputDevice(deviceId);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onUsersUpdate() {
            synchronized (this.mLock) {
                if (this.mService == null) {
                    return;
                }
                this.mLastBrightnessLevel = -1;
                refreshDisplayBrightness();
            }
        }
    }
}
