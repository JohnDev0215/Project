package android.hardware.bydauto.whud.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class WhudSettingManagerImpl {
    private static final int INSTRUNENT_COMMAND_SUCCESS = 0;
    private static final int SETTING_COMMAND_BUSY = -2147482647;
    private static final int SETTING_COMMAND_FAILED = -2147482648;
    private static final int SETTING_COMMAND_INVALID = -2147482645;
    private static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "WhudSettingManagerImpl";
    private static volatile WhudSettingManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;

    private WhudSettingManagerImpl(Context con) {
        this.mContext = con;
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(con);
    }

    public static WhudSettingManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (WhudSettingManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new WhudSettingManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public int setOverseasMapSupplier(int state) {
        LogUtil.info(TAG, "setOverseasMapSupplier : " + state);
        if (this.mSettingsManagerImpl.getClusterSettingsManager() != null) {
            return this.mSettingsManagerImpl.getClusterSettingsManager().setOverseasMapSupplier(state) == 0 ? 0 : -2147482648;
        }
        LogUtil.error(TAG, "mClusterSettingsManager is null");
        return -2147482648;
    }
}
