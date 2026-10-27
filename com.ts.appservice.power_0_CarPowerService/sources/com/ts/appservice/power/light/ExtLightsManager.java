package com.ts.appservice.power.light;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtLightsManager {
    private static final String KEY_MULTIMEDIA = "multimedia_brightness_linkage";
    private static final int MAX = 255;
    private static final int MIN = 10;
    private static final int NUM_THREE = 3;
    private static final String PREFS_KEY_IS_SYNC_DEFAULT_BRIGHTNESS = "isSyncDefaultBrightness";
    private static final int VALUE_MULTIMEDIA_DEFAULT = 1;
    public static final int VALUE_MULTIMEDIA_NO = 0;
    private static final int VALUE_MULTIMEDIA_UNDEFINED = -1;
    public static final int VALUE_MULTIMEDIA_YES = 1;
    private static volatile ExtLightsManager sInstance;
    private CarPowerBinder mCarPowerBinder;
    private ContentResolver mContentResolver;
    private Context mContext;
    private final SharedPreferences mSharedPreferences;
    private static final String PREFS_FILE = ExtLightsManager.class.getSimpleName();
    private static final String TAG = ExtLightsManager.class.getSimpleName();
    private volatile int mCurDisplayState = -1;
    private volatile int mRecoverLightBrightness = -1;
    private Boolean mIsSyncDefaultBrightness = null;
    private final Object mStateLock = new Object();
    private final Object mBrightnessLock = new Object();
    private Uri mUriMultimedia = null;
    private ContentObserver mMultimediaBrightnessLinkageObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.ts.appservice.power.light.ExtLightsManager.2
        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            LogUtil.info(ExtLightsManager.TAG, "mMultimediaBrightnessLinkageObserver selfChange : " + selfChange + " Uri : " + uri);
            if (selfChange) {
                LogUtil.info(ExtLightsManager.TAG, "mMultimediaBrightnessLinkageObserver selfChange : false return");
                return;
            }
            if (ExtLightsManager.this.mUriMultimedia.equals(uri)) {
                int multimedia = Settings.System.getInt(ExtLightsManager.this.mContext.getContentResolver(), ExtLightsManager.KEY_MULTIMEDIA, -1);
                LogUtil.info(ExtLightsManager.TAG, "multimedia_brightness_linkage multimedia : " + multimedia);
                ExtLightsManager.this.mCarPowerBinder.setMultimediaBrightnessLinkage(multimedia);
            }
        }
    };

    public static ExtLightsManager getInstance(Context context, CarPowerBinder carPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtLightsManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtLightsManager(context, carPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private ExtLightsManager(Context context, CarPowerBinder carPowerBinder) {
        LogUtil.info(TAG, "TsLightsManager");
        this.mContext = context;
        this.mCarPowerBinder = carPowerBinder;
        this.mContentResolver = context.getContentResolver();
        this.mSharedPreferences = this.mContext.getSharedPreferences(PREFS_FILE, 0);
        new Thread(new Runnable() { // from class: com.ts.appservice.power.light.ExtLightsManager.1
            @Override // java.lang.Runnable
            public void run() {
                ExtLightsManager.this.initRegisterListener();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRegisterListener() {
        LogUtil.info(TAG, "initRegisterListener");
        if (Settings.System.getString(this.mContentResolver, KEY_MULTIMEDIA) != null) {
            int multimedia = Settings.System.getInt(this.mContext.getContentResolver(), KEY_MULTIMEDIA, -1);
            if (multimedia == -1) {
                Settings.System.putInt(this.mContentResolver, KEY_MULTIMEDIA, 1);
            }
        } else {
            Settings.System.putInt(this.mContentResolver, KEY_MULTIMEDIA, 1);
        }
        Uri uriFor = Settings.System.getUriFor(KEY_MULTIMEDIA);
        this.mUriMultimedia = uriFor;
        this.mContentResolver.registerContentObserver(uriFor, false, this.mMultimediaBrightnessLinkageObserver);
    }

    public int getBrightness(int type) {
        LogUtil.info(TAG, "getBrightness type : " + type);
        if (isBrightnessSupported(type)) {
            return this.mCarPowerBinder.getScreenBrightness(type, -1);
        }
        return -1;
    }

    public void setBrightness(int type, int brightness) {
        LogUtil.info(TAG, "setBrightness type : " + type + " brightness : " + brightness);
        if (isBrightnessSupported(type)) {
            this.mCarPowerBinder.setScreenBrightness(type, brightness);
        }
    }

    public int getBrightnessState(int type) {
        LogUtil.info(TAG, "getBrightnessState type : " + type);
        synchronized (this.mStateLock) {
            if (isBrightnessSupported(type)) {
                return this.mCurDisplayState;
            }
            LogUtil.info(TAG, "getBrightnessState : " + this.mCurDisplayState);
            return -1;
        }
    }

    public boolean setBrightnessState(int type, int state) {
        LogUtil.info(TAG, "setBrightnessState type : " + type + " state : " + state);
        if (isBrightnessSupported(type)) {
            this.mCarPowerBinder.setScreenBrightnessOnOff(type, state);
            setCurDisplayState(state);
            return true;
        }
        return false;
    }

    public boolean isBrightnessSupported(int type) {
        if (type == 0 || type == 1 || type == 2) {
            LogUtil.info(TAG, "isBrightnessSupported : true; type : " + type);
            return true;
        }
        LogUtil.info(TAG, "isBrightnessSupported : false; type : " + type);
        return false;
    }

    public void setCurDisplayState(int state) {
        LogUtil.info(TAG, "setCurDisplayState state : " + state);
        synchronized (this.mStateLock) {
            this.mCurDisplayState = state;
        }
    }

    public int getCurDisplayState() {
        int i;
        LogUtil.info(TAG, "getCurDisplayState : " + this.mCurDisplayState);
        synchronized (this.mStateLock) {
            i = this.mCurDisplayState;
        }
        return i;
    }

    public boolean getIsSyncDefaultBrightness() {
        Boolean bool = this.mIsSyncDefaultBrightness;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean isSyncDefaultBrightness = this.mSharedPreferences.getBoolean(PREFS_KEY_IS_SYNC_DEFAULT_BRIGHTNESS, false);
        LogUtil.info(TAG, "isSyncDefaultBrightness: " + isSyncDefaultBrightness);
        this.mIsSyncDefaultBrightness = Boolean.valueOf(isSyncDefaultBrightness);
        return isSyncDefaultBrightness;
    }

    public void setIsSyncDefaultBrightness(boolean isSyncDefaultBrightness) {
        LogUtil.info(TAG, "isSyncDefaultBrightness: " + isSyncDefaultBrightness);
        this.mIsSyncDefaultBrightness = Boolean.valueOf(isSyncDefaultBrightness);
        this.mSharedPreferences.edit().putBoolean(PREFS_KEY_IS_SYNC_DEFAULT_BRIGHTNESS, isSyncDefaultBrightness).apply();
    }

    public int getRecoverLightBrightness() {
        int i;
        LogUtil.info(TAG, "getRecoverLightBrightness : " + this.mRecoverLightBrightness);
        synchronized (this.mBrightnessLock) {
            i = this.mRecoverLightBrightness;
        }
        return i;
    }

    public void setRecoverLightBrightness(int recoverLightBrightness) {
        LogUtil.info(TAG, "setRecoverLightBrightness recoverLightBrightness : " + recoverLightBrightness);
        synchronized (this.mBrightnessLock) {
            this.mRecoverLightBrightness = recoverLightBrightness;
        }
    }
}
