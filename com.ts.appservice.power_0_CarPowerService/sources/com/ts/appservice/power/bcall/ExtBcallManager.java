package com.ts.appservice.power.bcall;

import android.content.Context;
import android.util.Log;
import com.ts.appservice.power.CarPowerBinder;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtBcallManager {
    private static final String TAG = ExtBcallManager.class.getSimpleName();
    private static volatile ExtBcallManager sInstance;
    private Context mContext;
    private CarPowerBinder mTsCarPower;
    private volatile boolean mBcallMode = false;
    private Object mLock = new Object();
    private boolean mIsBcallWorking = false;

    public static ExtBcallManager getInstance(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "getInstance");
        if (sInstance == null) {
            synchronized (ExtBcallManager.class) {
                if (sInstance == null) {
                    sInstance = new ExtBcallManager(context, tsCarPowerBinder);
                }
            }
        }
        return sInstance;
    }

    private ExtBcallManager(Context context, CarPowerBinder tsCarPowerBinder) {
        LogUtil.info(TAG, "TsBcallManager");
        this.mContext = context;
        this.mTsCarPower = tsCarPowerBinder;
    }

    public void initBcallstate() {
        LogUtil.info(TAG, "initBcallstate");
    }

    public void setBcallMode(int mode) {
        LogUtil.info(TAG, "setBcallMode mode : " + mode);
        if (mode == 6) {
            this.mBcallMode = true;
        }
        LogUtil.info(TAG, "setBcallMode mBcallMode : " + this.mBcallMode);
    }

    public void setBcallState(int state) {
        LogUtil.info(TAG, "setBcallState state : " + state);
        LogUtil.info(TAG, "setBcallState mBcallMode : " + this.mBcallMode);
        if (this.mBcallMode) {
            if (state == 0) {
                try {
                    setIsBcallWorking(false);
                    this.mBcallMode = false;
                    this.mTsCarPower.initWorkModeState();
                    return;
                } catch (Exception exc) {
                    LogUtil.error(TAG, Log.getStackTraceString(exc));
                    return;
                }
            }
            if (state == 1) {
                try {
                    setIsBcallWorking(true);
                    return;
                } catch (Exception exc2) {
                    LogUtil.error(TAG, Log.getStackTraceString(exc2));
                    return;
                }
            }
            LogUtil.error(TAG, "setBcallState state : error");
        }
    }

    public boolean getIsBcallWorking() {
        boolean z;
        synchronized (this.mLock) {
            LogUtil.info(TAG, "getIsBcallWorking mIsBcallWorking : " + this.mIsBcallWorking);
            z = this.mIsBcallWorking;
        }
        return z;
    }

    public void setIsBcallWorking(boolean isBcallWorking) {
        synchronized (this.mLock) {
            LogUtil.info(TAG, "setIsBcallWorking isBcallWorking : " + isBcallWorking);
            this.mIsBcallWorking = isBcallWorking;
        }
    }
}
