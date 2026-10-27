package android.hardware.bydauto.container;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.container.tsimpl.ContainerManagerImpl;
import android.hardware.bydauto.power.tsimpl.PowerManagerImpl;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoContainerManager extends AbsBYDAutoDevice {
    private static final String TAG = "BYDAutoContainerManager";
    private static volatile BYDAutoContainerManager mBYDAutoContainerManager;
    private ContainerManagerImpl mContainerManager;
    private PowerManagerImpl mPowerManager;

    protected BYDAutoContainerManager(Context con) {
        super(con);
        this.mContainerManager = ContainerManagerImpl.getsInstance(con);
        this.mPowerManager = PowerManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoContainerManager getInstance(Context con) {
        if (mBYDAutoContainerManager == null) {
            synchronized (BYDAutoContainerManager.class) {
                if (mBYDAutoContainerManager == null) {
                    mBYDAutoContainerManager = new BYDAutoContainerManager(con.getApplicationContext());
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
    public String getSetPermission() {
        return "";
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return "";
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void sendInfo(int index) {
        this.mContainerManager.sendInfo(index);
        this.mPowerManager.sendInfo(index);
    }
}
