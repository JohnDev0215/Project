package android.hardware.bydauto.tbox;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.ota.tsimpl.OtaManagerImpl;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoTBoxDevice extends AbsBYDAutoDevice {
    protected static final String TAG = "BYDAutoTBoxDevice";
    private static BYDAutoTBoxDevice mBydAutoTboxDevice;
    private OtaManagerImpl mOtaManager;

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

    BYDAutoTBoxDevice(Context context) {
        super(context);
        Log.d(TAG, "BYDAutoTBoxDevice: conn = " + context);
        this.mOtaManager = OtaManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoTBoxDevice getInstance(Context context) {
        if (mBydAutoTboxDevice == null) {
            mBydAutoTboxDevice = new BYDAutoTBoxDevice(context);
        }
        return mBydAutoTboxDevice;
    }

    public void registerListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.registerListener(absBYDTBoxListener);
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.unregisterListener(absBYDTBoxListener);
    }

    public void setMobileNetworkState(boolean z) {
        this.mOtaManager.setMobileNetworkState(z);
    }

    public int notifyTBoxTrafficResult(NetWorkQueryType netWorkQueryType) {
        return this.mOtaManager.notifyTBoxTrafficResult(netWorkQueryType.getValue());
    }

    public int notifyTBoxCmdResult(int i, int i2) {
        return this.mOtaManager.notifyTBoxCmdResult(i, i2);
    }

    public int uploadCDR(int i, String str) {
        return this.mOtaManager.uploadCDR(i, str);
    }

    public int setTcpDumpState(boolean z) {
        return this.mOtaManager.setTcpDumpState(z);
    }

    public int clearTboxLog() {
        return this.mOtaManager.clearTboxLog();
    }

    public int setAPN3State(boolean z) {
        return this.mOtaManager.setApn3State(z);
    }
}
