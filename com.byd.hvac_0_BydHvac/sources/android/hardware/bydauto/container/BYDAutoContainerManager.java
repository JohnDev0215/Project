package android.hardware.bydauto.container;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.container.tsimpl.ContainerManagerImpl;
import android.hardware.bydauto.power.tsimpl.PowerManagerImpl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class BYDAutoContainerManager extends AbsBYDAutoDevice {
    private static final String TAG = "BYDAutoContainerManager";
    private static volatile BYDAutoContainerManager mBYDAutoContainerManager;
    private ContainerManagerImpl mContainerManager;
    private PowerManagerImpl mPowerManager;

    public BYDAutoContainerManager(Context context) {
        super(context);
        this.mContainerManager = ContainerManagerImpl.getsInstance(context);
        this.mPowerManager = PowerManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoContainerManager getInstance(Context context) {
        if (mBYDAutoContainerManager == null) {
            synchronized (BYDAutoContainerManager.class) {
                if (mBYDAutoContainerManager == null) {
                    mBYDAutoContainerManager = new BYDAutoContainerManager(context.getApplicationContext());
                }
            }
        }
        return mBYDAutoContainerManager;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public void sendInfo(int i) {
        this.mContainerManager.sendInfo(i);
        this.mPowerManager.sendInfo(i);
    }
}
