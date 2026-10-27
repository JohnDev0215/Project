package android.hardware.bydauto.mic.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.mic.DeviceEventListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.microphone.MicSettingsManager;
import com.ts.lib.settings.microphone.OnMicChangedListener;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class MicManagerImpl {
    private static final String TAG = "MicManagerImpl";
    private static volatile MicManagerImpl sInstance;
    private Context mContext;
    private DeviceEventListener mDeviceEventListener;
    private MicSettingsManager mMicSettingsManager;
    private SettingsManagerImpl mSettingsManagerImpl;

    private MicManagerImpl(Context context) {
        this.mContext = context;
        SettingsManagerImpl settingsManagerImpl = SettingsManagerImpl.getInstance(context);
        this.mSettingsManagerImpl = settingsManagerImpl;
        settingsManagerImpl.setOnMicChangedListener(new OnMicChangedListener() { // from class: android.hardware.bydauto.mic.tsimpl.MicManagerImpl.1
            public void onMicUseChanged(String str, boolean z) {
                MicManagerImpl.this.mDeviceEventListener.onMicUseChange(str, z);
            }
        });
    }

    public static MicManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (MicManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new MicManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    public int getChargeStatus() {
        LogUtil.debug(TAG, "getChargeStatus");
        if (this.mSettingsManagerImpl.getMicSettingsManager() != null) {
            return this.mSettingsManagerImpl.getMicSettingsManager().getChargeStatus();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getDevicetype() {
        LogUtil.debug(TAG, "getDevicetype");
        return 0;
    }

    public String getGetPermission() {
        LogUtil.debug(TAG, "getGetPermission null");
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }

    public String getMicUsePackageName() {
        LogUtil.debug(TAG, "getMicUsePackageName");
        if (this.mSettingsManagerImpl.getMicSettingsManager() != null) {
            return this.mSettingsManagerImpl.getMicSettingsManager().getMicUsePackageName();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public int getPowerStatus() {
        LogUtil.debug(TAG, "getPowerStatus");
        if (this.mSettingsManagerImpl.getMicSettingsManager() != null) {
            return this.mSettingsManagerImpl.getMicSettingsManager().getPowerStatus();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public String getSetPermission() {
        LogUtil.debug(TAG, "getSetPermission");
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }

    public void registerListener(DeviceEventListener deviceEventListener) {
        this.mDeviceEventListener = deviceEventListener;
    }

    public void unregisterListener() {
        this.mDeviceEventListener = null;
    }
}
