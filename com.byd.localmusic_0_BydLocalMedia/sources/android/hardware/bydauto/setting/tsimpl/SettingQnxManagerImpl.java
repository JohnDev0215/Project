package android.hardware.bydauto.setting.tsimpl;

import android.content.Context;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.qnxmanager.QnxManager;

/* JADX INFO: loaded from: classes2.dex */
public class SettingQnxManagerImpl {
    private static final int INSTRUNENT_COMMAND_SUCCESS = 0;
    private static final int SETTING_COMMAND_BUSY = -2147482647;
    private static final int SETTING_COMMAND_FAILED = -2147482648;
    private static final int SETTING_COMMAND_INVALID = -2147482645;
    private static final int SETTING_COMMAND_TIMEOUT = -2147482646;
    private static final String TAG = "SettingClusterManagerImpl";
    private static volatile SettingQnxManagerImpl sInstance;
    private Context mContext;
    private boolean mIgnoreBydPermission = false;
    private QnxManager mQnxManager = QnxManager.getInstance();

    private SettingQnxManagerImpl(Context context) {
        this.mContext = context;
    }

    public static SettingQnxManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SettingQnxManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new SettingQnxManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public int notifyDilinkStarted() {
        LogUtil.debug(TAG, "notifyDilinkStarted start");
        enforcePermission("android.permission.BYDAUTO_SETTING_SET");
        return this.mQnxManager.notifyDiLinkStarted() ? 0 : -2147482648;
    }
}
