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

    public BYDAutoTBoxDevice(Context context) {
        super(context);
        Log.d(TAG, "BYDAutoTBoxDevice: conn = " + context);
        this.mOtaManager = OtaManagerImpl.getInstance(context);
    }

    public static synchronized BYDAutoTBoxDevice getInstance(Context context) {
        try {
            if (mBydAutoTboxDevice == null) {
                mBydAutoTboxDevice = new BYDAutoTBoxDevice(context);
            }
        } catch (Throwable th) {
            throw th;
        }
        return mBydAutoTboxDevice;
    }

    public int clearTboxLog() {
        return this.mOtaManager.clearTboxLog();
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
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int notifyTBoxCmdResult(int i7, int i10) {
        return this.mOtaManager.notifyTBoxCmdResult(i7, i10);
    }

    public int notifyTBoxTrafficResult(NetWorkQueryType netWorkQueryType) {
        return this.mOtaManager.notifyTBoxTrafficResult(netWorkQueryType.getValue());
    }

    public void registerListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.registerListener(absBYDTBoxListener);
    }

    public int setAPN3State(boolean z6) {
        return this.mOtaManager.setApn3State(z6);
    }

    public void setMobileNetworkState(boolean z6) {
        this.mOtaManager.setMobileNetworkState(z6);
    }

    public int setTcpDumpState(boolean z6) {
        return this.mOtaManager.setTcpDumpState(z6);
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.unregisterListener(absBYDTBoxListener);
    }

    public int uploadCDR(int i7, String str) {
        return this.mOtaManager.uploadCDR(i7, str);
    }
}
