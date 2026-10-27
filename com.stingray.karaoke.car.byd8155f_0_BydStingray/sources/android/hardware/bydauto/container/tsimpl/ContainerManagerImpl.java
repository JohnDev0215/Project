package android.hardware.bydauto.container.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import com.ts.lib.settings.common.utilities.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class ContainerManagerImpl {
    private static final String TAG = "ContainerManagerImpl";
    private static volatile ContainerManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;

    private ContainerManagerImpl(Context context) {
        this.mContext = context;
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
    }

    public static ContainerManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (ContainerManagerImpl.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new ContainerManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        return sInstance;
    }

    public void sendInfo(int i7) {
        LogUtil.debug(TAG, a.e(i7, "sendInfo index :"));
        if (this.mSettingsManagerImpl.getContainerSettingManager() == null) {
            LogUtil.error(TAG, "mContainerSettingManager is null");
        } else {
            this.mSettingsManagerImpl.getContainerSettingManager().sendInfo(i7);
        }
    }
}
