package android.hardware.bydauto.startup;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.startup.tsimpl.AppBootManagerImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoStartupAppDevice extends AbsBYDAutoDevice {
    public static volatile BYDAutoStartupAppDevice sInstane;
    private AppBootManagerImpl mAppBootManagerImpl;

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    private BYDAutoStartupAppDevice(Context context) {
        super(context);
        this.mAppBootManagerImpl = AppBootManagerImpl.getInstance(context.getApplicationContext());
    }

    public static synchronized BYDAutoStartupAppDevice getInstance(Context context) {
        if (sInstane == null) {
            sInstane = new BYDAutoStartupAppDevice(context);
        }
        return sInstane;
    }

    public List<StartupApp> getStartupAppList() {
        return this.mAppBootManagerImpl.getStartupAppList();
    }

    public boolean getStartupAppEnable(String str) {
        return this.mAppBootManagerImpl.getStartupAppEnable(str);
    }

    public int setStartupAppEnable(String str, boolean z) {
        return this.mAppBootManagerImpl.setStartupAppEnable(str, z);
    }
}
