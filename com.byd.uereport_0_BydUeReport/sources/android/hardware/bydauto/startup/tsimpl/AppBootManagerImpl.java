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
    public static final String TAG = "AppBootManagerImpl";
    private static volatile AppBootManagerImpl sInstance;
    private List<BootApp> mBootAppList;
    private SettingsManagerImpl mSettingsManagerImpl;

    private AppBootManagerImpl(Context context) {
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
    }

    public static synchronized AppBootManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppBootManagerImpl(context);
        }
        return sInstance;
    }

    public boolean getStartupAppEnable(String str) {
        String str2 = TAG;
        LogUtil.debug(str2, "getStartupAppEnable pkg : " + str);
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(str2, "SettingsAdapter still connecting, please wait");
        }
        return this.mSettingsManagerImpl.getAppBootSettingsManager().getStartupAppEnable(str);
    }

    public List<StartupApp> getStartupAppList() {
        String str = TAG;
        LogUtil.debug(str, "getStartupAppList");
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(str, "SettingsAdapter still connecting, please wait");
            return null;
        }
        this.mBootAppList = this.mSettingsManagerImpl.getAppBootSettingsManager().getStartupAppList();
        ArrayList arrayList = new ArrayList();
        List<BootApp> list = this.mBootAppList;
        if (list != null && list.size() > 0) {
            for (BootApp bootApp : this.mBootAppList) {
                arrayList.add(new StartupApp(bootApp.getPackageName(), bootApp.getEnable()));
            }
        }
        return arrayList;
    }

    public int setStartupAppEnable(String str, boolean z7) {
        String str2 = TAG;
        LogUtil.debug(str2, "setStartupAppEnable pkg : " + str + " enable : " + z7);
        if (this.mSettingsManagerImpl.getAppBootSettingsManager() == null) {
            LogUtil.error(str2, "SettingsAdapter still connecting, please wait");
        }
        return this.mSettingsManagerImpl.getAppBootSettingsManager().setStartupAppEnable(str, z7);
    }
}
