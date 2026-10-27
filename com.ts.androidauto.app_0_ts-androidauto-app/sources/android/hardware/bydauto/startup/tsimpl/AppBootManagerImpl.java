package android.hardware.bydauto.startup.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.startup.StartupApp;
import com.ts.lib.settings.boot.BootApp;
import com.ts.lib.settings.common.utilities.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppBootManagerImpl {
    public static final String TAG = AppBootManagerImpl.class.getSimpleName();
    private static volatile AppBootManagerImpl sInstance;
    private List<BootApp> mBootAppList;
    private SettingsManagerImpl mSettingsManagerImpl;

    public static synchronized AppBootManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppBootManagerImpl(context);
        }
        return sInstance;
    }

    private AppBootManagerImpl(Context context) {
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
    }

    public List<StartupApp> getStartupAppList() {
        LogUtil.debug(TAG, "getStartupAppList");
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        this.mBootAppList = this.mSettingsManagerImpl.getAppBootSettingsManager().getStartupAppList();
        List<StartupApp> startupAppList = new ArrayList<>();
        List<BootApp> list = this.mBootAppList;
        if (list != null && list.size() > 0) {
            for (BootApp app : this.mBootAppList) {
                StartupApp startupApp = new StartupApp(app.getPackageName(), app.getEnable());
                startupAppList.add(startupApp);
            }
        }
        return startupAppList;
    }

    public boolean getStartupAppEnable(String pkg) {
        LogUtil.debug(TAG, "getStartupAppEnable pkg : " + pkg);
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        }
        return this.mSettingsManagerImpl.getAppBootSettingsManager().getStartupAppEnable(pkg);
    }

    public int setStartupAppEnable(String pkg, boolean enable) {
        LogUtil.debug(TAG, "setStartupAppEnable pkg : " + pkg + " enable : " + enable);
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        }
        return this.mSettingsManagerImpl.getAppBootSettingsManager().setStartupAppEnable(pkg, enable);
    }
}
