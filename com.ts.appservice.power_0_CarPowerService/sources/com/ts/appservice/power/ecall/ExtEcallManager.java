package com.ts.appservice.power.ecall;

import android.content.Context;
import android.util.Log;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtEcallManager {
    private static final String TAG = ExtEcallManager.class.getSimpleName();
    private static volatile ExtEcallManager sInstance;
    private Context mContext;
    private CarPowerBinder mTsCarPower;
    private volatile boolean mEcallMode = false;
    private Object mLock = new Object();
    private boolean mIsEcallWorking = false;

    public static ExtEcallManager getInstance(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtEcallManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtEcallManager(context, tsCarPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private ExtEcallManager(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "TsEcallManager");
        this.mContext = context;
        this.mTsCarPower = tsCarPowerBinder;
    }

    public void initEcallstate() {
        LogUtil.info(TAG, "initEcallstate");
    }

    public void setEcallMode(int mode) {
        LogUtil.info(TAG, "setEcallMode mode : " + mode);
        if (mode == 4) {
            this.mEcallMode = true;
        }
        LogUtil.info(TAG, "setEcallMode mEcallMode : " + this.mEcallMode);
    }

    public void setEcallState(int state) {
        LogUtil.info(TAG, "setEcallState state : " + state);
        LogUtil.info(TAG, "setEcallState mEcallMode : " + this.mEcallMode);
        if (this.mEcallMode) {
            if (state == 0) {
                try {
                    setIsEcallWorking(false);
                    this.mEcallMode = false;
                    this.mTsCarPower.initWorkModeState();
                    return;
                } catch (Exception exc) {
                    LogUtil.info(TAG, Log.getStackTraceString(exc));
                    return;
                }
            }
            if (state == 1) {
                try {
                    setIsEcallWorking(true);
                    this.mTsCarPower.setHardKeyState(1);
                    return;
                } catch (Exception exc2) {
                    LogUtil.info(TAG, Log.getStackTraceString(exc2));
                    return;
                }
            }
            LogUtil.info(TAG, "setEcallState state : error");
        }
    }

    public boolean getIsEcallWorking() {
        boolean z;
        synchronized (this.mLock) {
            LogUtil.info(TAG, "getIsEcallWorking mIsEcallWorking : " + this.mIsEcallWorking);
            z = this.mIsEcallWorking;
        }
        return z;
    }

    public void setIsEcallWorking(boolean isEcallWorking) {
        synchronized (this.mLock) {
            LogUtil.info(TAG, "setIsEcallWorking isEcallWorking : " + isEcallWorking);
            this.mIsEcallWorking = isEcallWorking;
        }
    }
}
