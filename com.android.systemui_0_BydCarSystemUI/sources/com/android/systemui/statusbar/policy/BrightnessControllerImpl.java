package com.android.systemui.statusbar.policy;

import android.R;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.provider.Settings;
import com.android.systemui.Dependency;
import com.android.systemui.settings.CurrentUserTracker;
import com.android.systemui.statusbar.policy.BrightnessController;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BrightnessControllerImpl implements BrightnessController {
    private static final int MSG_SET_CHECKED = 2;
    private static final int MSG_UPDATE_SLIDER = 1;
    private volatile boolean mAutomatic;
    private final boolean mAutomaticAvailable;
    private final Handler mBackgroundHandler;
    private final BrightnessObserver mBrightnessObserver;
    private final Context mContext;
    private final int mDefaultBacklight;
    private final DisplayManager mDisplayManager;
    private boolean mExternalChange;
    private final Handler mHandler;
    private final int mMaximumBacklight;
    private final int mMinimumBacklight;
    private final CurrentUserTracker mUserTracker;
    private final ArrayList<BrightnessController.Callback> mCallbacks = new ArrayList<>();
    private final Runnable mStartListeningRunnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.2
        @Override // java.lang.Runnable
        public void run() {
            BrightnessControllerImpl.this.mBrightnessObserver.startObserving();
            BrightnessControllerImpl.this.mUserTracker.startTracking();
            BrightnessControllerImpl.this.mUpdateModeRunnable.run();
            BrightnessControllerImpl.this.mUpdateSliderRunnable.run();
        }
    };
    private final Runnable mStopListeningRunnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.3
        @Override // java.lang.Runnable
        public void run() {
            BrightnessControllerImpl.this.mBrightnessObserver.stopObserving();
            BrightnessControllerImpl.this.mUserTracker.stopTracking();
        }
    };
    private final Runnable mUpdateModeRunnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.4
        @Override // java.lang.Runnable
        public void run() {
            if (BrightnessControllerImpl.this.mAutomaticAvailable) {
                int intForUser = Settings.System.getIntForUser(BrightnessControllerImpl.this.mContext.getContentResolver(), "screen_brightness_mode", 0, -2);
                BrightnessControllerImpl.this.mAutomatic = intForUser != 0;
                return;
            }
            BrightnessControllerImpl.this.mHandler.obtainMessage(2, 0).sendToTarget();
        }
    };
    private final Runnable mUpdateSliderRunnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.5
        @Override // java.lang.Runnable
        public void run() {
            BrightnessControllerImpl.this.mHandler.obtainMessage(1, Settings.System.getIntForUser(BrightnessControllerImpl.this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, BrightnessControllerImpl.this.mDefaultBacklight, -2), 0).sendToTarget();
        }
    };

    @Override // com.android.systemui.Dumpable
    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
    }

    private class BrightnessObserver extends ContentObserver {
        private final Uri BRIGHTNESS_MODE_URI;
        private final Uri BRIGHTNESS_URI;

        public BrightnessObserver(Handler handler) {
            super(handler);
            this.BRIGHTNESS_MODE_URI = Settings.System.getUriFor("screen_brightness_mode");
            this.BRIGHTNESS_URI = Settings.System.getUriFor(BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange) {
            onChange(selfChange, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            BydLogHelper.logD("selfChange:" + selfChange);
            if (selfChange) {
                return;
            }
            if (this.BRIGHTNESS_MODE_URI.equals(uri)) {
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateModeRunnable);
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateSliderRunnable);
            } else if (this.BRIGHTNESS_URI.equals(uri)) {
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateSliderRunnable);
            } else {
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateModeRunnable);
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateSliderRunnable);
            }
        }

        public void startObserving() {
            ContentResolver contentResolver = BrightnessControllerImpl.this.mContext.getContentResolver();
            contentResolver.unregisterContentObserver(this);
            contentResolver.registerContentObserver(this.BRIGHTNESS_MODE_URI, false, this, -1);
            contentResolver.registerContentObserver(this.BRIGHTNESS_URI, false, this, -1);
        }

        public void stopObserving() {
            BrightnessControllerImpl.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }

    public BrightnessControllerImpl(Context context) {
        Handler handler = new Handler() { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.6
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                BrightnessControllerImpl.this.mExternalChange = true;
                try {
                    int i = msg.what;
                    if (i != 1) {
                        if (i == 2) {
                            if (BrightnessControllerImpl.this.mExternalChange) {
                                synchronized (BrightnessControllerImpl.this.mCallbacks) {
                                    for (BrightnessController.Callback callback : BrightnessControllerImpl.this.mCallbacks) {
                                        BydLogHelper.logD("onChange --> MSG_SET_CHECKED = " + msg.arg1);
                                        callback.onToggleModeChanged(msg.arg1 != 0);
                                    }
                                }
                            }
                        } else {
                            super.handleMessage(msg);
                        }
                    } else if (BrightnessControllerImpl.this.mExternalChange) {
                        if (msg.arg1 < 0) {
                            return;
                        }
                        synchronized (BrightnessControllerImpl.this.mCallbacks) {
                            for (BrightnessController.Callback callback2 : BrightnessControllerImpl.this.mCallbacks) {
                                BydLogHelper.logD("onChange --> MSG_UPDATE_SLIDER = " + msg.arg1 + " , mMaximumBacklight : " + BrightnessControllerImpl.this.mMaximumBacklight);
                                double d = ((double) msg.arg1) / ((double) BrightnessControllerImpl.this.mMaximumBacklight);
                                BydLogHelper.logD("onChange --> MSG_UPDATE_SLIDER value = " + d);
                                BydLogHelper.logD("onChange --> MSG_UPDATE_SLIDER brightnessValue = " + ((int) (100.0d * d)));
                                callback2.onBrightnessValueChanged(msg.arg1, BrightnessControllerImpl.this.mMaximumBacklight, BrightnessControllerImpl.this.mMinimumBacklight);
                            }
                        }
                    }
                } finally {
                    BrightnessControllerImpl.this.mExternalChange = false;
                }
            }
        };
        this.mHandler = handler;
        this.mContext = context;
        this.mBackgroundHandler = new Handler((Looper) Dependency.get(Dependency.BG_LOOPER));
        this.mUserTracker = new CurrentUserTracker(context) { // from class: com.android.systemui.statusbar.policy.BrightnessControllerImpl.1
            @Override // com.android.systemui.settings.CurrentUserTracker
            public void onUserSwitched(int newUserId) {
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateModeRunnable);
                BrightnessControllerImpl.this.mBackgroundHandler.post(BrightnessControllerImpl.this.mUpdateSliderRunnable);
            }
        };
        this.mBrightnessObserver = new BrightnessObserver(handler);
        PowerManager powerManager = (PowerManager) context.getSystemService(PowerManager.class);
        int minimumScreenBrightnessSetting = powerManager.getMinimumScreenBrightnessSetting();
        this.mMinimumBacklight = minimumScreenBrightnessSetting;
        int maximumScreenBrightnessSetting = powerManager.getMaximumScreenBrightnessSetting();
        this.mMaximumBacklight = maximumScreenBrightnessSetting;
        int defaultScreenBrightnessSetting = powerManager.getDefaultScreenBrightnessSetting();
        this.mDefaultBacklight = defaultScreenBrightnessSetting;
        BydLogHelper.logD("mMinimumBackLight = " + minimumScreenBrightnessSetting + " , mMaximumBacklight = " + maximumScreenBrightnessSetting + " , mDefaultBacklight = " + defaultScreenBrightnessSetting);
        this.mAutomaticAvailable = context.getResources().getBoolean(R.bool.config_allowTheaterModeWakeFromMotion);
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public void setToggleMode(boolean z) {
        setMode(z ? 1 : 0);
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public void getToggleMode() {
        this.mBackgroundHandler.post(this.mUpdateModeRunnable);
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public void setValue(int brightness, boolean tracking, boolean stopTracking) {
        this.mExternalChange = false;
        BydLogHelper.logD("brightness:" + brightness + " , tracking:" + tracking + " , stopTracking:" + stopTracking);
        boolean z = this.mAutomatic;
        BydLogHelper.logD("mMaximumBacklight:" + this.mMaximumBacklight);
        BydLogHelper.logD("brightnessVal:" + brightness);
        Settings.System.putInt(this.mContext.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, brightness);
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public void getValue() {
        this.mBackgroundHandler.post(this.mUpdateSliderRunnable);
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public int getMinimumBackLight() {
        BydLogHelper.logD("getMinimumBackLight:" + this.mMinimumBacklight);
        return this.mMinimumBacklight;
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public int getMaximumBackLight() {
        BydLogHelper.logD("getMaximumBackLight:" + this.mMaximumBacklight);
        return this.mMaximumBacklight;
    }

    @Override // com.android.systemui.statusbar.policy.BrightnessController
    public int getDefaultBacklight() {
        return this.mDefaultBacklight;
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void addCallback(BrightnessController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback != null) {
                if (!this.mCallbacks.contains(callback)) {
                    this.mBackgroundHandler.post(this.mStartListeningRunnable);
                    this.mCallbacks.add(callback);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public void removeCallback(BrightnessController.Callback callback) {
        synchronized (this.mCallbacks) {
            if (callback == null) {
                return;
            }
            this.mBackgroundHandler.post(this.mStopListeningRunnable);
            this.mCallbacks.remove(callback);
        }
    }

    private void setMode(int mode) {
        BydLogHelper.logD("setMode mode = " + mode);
        Settings.System.putIntForUser(this.mContext.getContentResolver(), "screen_brightness_mode", mode, this.mUserTracker.getCurrentUserId());
    }

    private void setBrightness(int brightness) {
        this.mDisplayManager.setTemporaryBrightness(brightness);
    }
}
