package com.ts.carplay.lib.resourcemanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import com.ts.carplay.lib.common.CpConst;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayLastUserMode {
    private static final int AUDIO_MODE_FLAG = 1;
    private static final String BACKUP_LASTMODE_AUDIO = "Audio";
    private static final String BACKUP_LASTMODE_SCREEN = "Screen";
    public static final int LAST_USER_MODE_STATE_DISABLED = 0;
    public static final int LAST_USER_MODE_STATE_ENABLED = 1;
    private static final int SCREEN_MODE_FLAG = 16;
    private static final int SHIFT_FOUR = 4;
    private static final int SHIFT_HIGH_MASK = 240;
    private static final int SHIFT_LOW_MASK = 15;
    private static final String TAG = "CarPlayLastUserMode";
    private static final int WR_FILE_BUF_SIZE = 10;
    static CarPlayLastUserMode sCarPlayLastUserMode = null;
    private int mAudioLastUserModeState;
    private Context mContext;
    private int mLastUserMode;
    private CarPlayManager mManager;
    private int mScreenLastUserModeState;
    private SharedPreferences mSharedPreferences;

    public CarPlayLastUserMode(CarPlayManager manager) {
        this.mManager = null;
        this.mManager = manager;
        sCarPlayLastUserMode = this;
    }

    public void initLastMode(Context context) {
        this.mContext = context;
        this.mSharedPreferences = context.getSharedPreferences("cpLastUserMode", 0);
        this.mScreenLastUserModeState = readLastModeForScreen();
        this.mAudioLastUserModeState = readLastModeForAudio();
        LogUtil.info(TAG, "CarPlayLastUserMode Construct mScreenLastUserModeState = " + this.mScreenLastUserModeState + " mAudioLastUserModeState = " + this.mAudioLastUserModeState + " mLastUserMode = " + this.mLastUserMode);
    }

    public static CarPlayLastUserMode getInstance() {
        CarPlayLastUserMode carPlayLastUserMode = sCarPlayLastUserMode;
        if (carPlayLastUserMode == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayLastUserMode before constructor was called.");
        }
        return carPlayLastUserMode;
    }

    public void systemAccOff() {
    }

    public void systemAccOn(int screenLastOn, int audioLastOn) {
    }

    public boolean isCarPlay(CpConst.CarPlayResource resourceType) {
        boolean ret = 1 == getLastUserModeState(resourceType);
        LogUtil.info(TAG, "isCarPlay ret:" + ret + " resourceType:" + resourceType);
        return ret;
    }

    public int getLastUserModeState(CpConst.CarPlayResource resourceType) {
        boolean isFirstDev = false;
        if (resourceType == CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN) {
            isFirstDev = this.mManager.checkIsFirstConnCp() | this.mManager.checkIsConnectFromUser() | this.mManager.getIsFromUserSts();
        } else if (resourceType == CpConst.CarPlayResource.CARPLAY_RESOURCE_AUDIO) {
            isFirstDev = this.mManager.checkIsFirstConnCp();
        }
        LogUtil.info(TAG, "getLastUserModeActive isFirstDev= " + isFirstDev + ", resourceType=" + resourceType);
        if (isFirstDev) {
            return 1;
        }
        if (CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN == resourceType) {
            LogUtil.info(TAG, "getLastUserModeActive, mScreenLastUserModeState = " + this.mScreenLastUserModeState);
            return this.mScreenLastUserModeState;
        }
        LogUtil.info(TAG, "getLastUserModeActive, mAudioLastUserModeState = " + this.mAudioLastUserModeState);
        return this.mAudioLastUserModeState;
    }

    public void setLastUserMode(CpConst.CarPlayResource resourceType, int lastmode) {
        LogUtil.info(TAG, "setLastUserMode resourceType = " + resourceType + " lastmode: " + lastmode);
        if (CpConst.CarPlayResource.CARPLAY_RESOURCE_SCREEN == resourceType) {
            this.mScreenLastUserModeState = lastmode;
            writeLastModeForScreen(lastmode);
        } else {
            this.mAudioLastUserModeState = lastmode;
            writeLastModeForAudio(lastmode);
        }
    }

    public boolean isRealAudioLastUserMode() {
        StringBuilder sb = new StringBuilder();
        sb.append("isRealAudioLastUserMode = ");
        sb.append(this.mAudioLastUserModeState == 1);
        LogUtil.info(TAG, sb.toString());
        return this.mAudioLastUserModeState == 1;
    }

    public void writeLastModeForAudio(int lastmode) {
        LogUtil.info(TAG, "writeLastModeForAudio lastmode = " + lastmode);
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(BACKUP_LASTMODE_AUDIO, lastmode);
            edit.apply();
        }
    }

    public void writeLastModeForScreen(int lastmode) {
        LogUtil.info(TAG, "writeLastModeForScreen lastmode = " + lastmode);
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(BACKUP_LASTMODE_SCREEN, lastmode);
            edit.apply();
        }
    }

    public int readLastModeForAudio() {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            return 0;
        }
        int lastmode = sharedPreferences.getInt(BACKUP_LASTMODE_AUDIO, 0);
        LogUtil.info(TAG, "readLastModeForAudio lastUserMode = " + lastmode);
        return lastmode;
    }

    public int readLastModeForScreen() {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            return 0;
        }
        int lastmode = sharedPreferences.getInt(BACKUP_LASTMODE_SCREEN, 0);
        LogUtil.info(TAG, "readLastModeForScreen lastUserMode = " + lastmode);
        return lastmode;
    }
}
