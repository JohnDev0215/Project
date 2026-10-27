package android.hardware.bydauto.tbox;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.ota.tsimpl.OtaManagerImpl;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoTBoxDevice extends AbsBYDAutoDevice {
    public static final String TAG = "BYDAutoTBoxDevice";
    private static BYDAutoTBoxDevice mBydAutoTboxDevice;
    private OtaManagerImpl mOtaManager;

    public BYDAutoTBoxDevice(Context context) {
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

    public int notifyTBoxCmdResult(int i3, int i4) {
        return this.mOtaManager.notifyTBoxCmdResult(i3, i4);
    }

    public int notifyTBoxTrafficResult(NetWorkQueryType netWorkQueryType) {
        return this.mOtaManager.notifyTBoxTrafficResult(netWorkQueryType.getValue());
    }

    public void registerListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.registerListener(absBYDTBoxListener);
    }

    public int setAPN3State(boolean z3) {
        return this.mOtaManager.setApn3State(z3);
    }

    public void setMobileNetworkState(boolean z3) {
        this.mOtaManager.setMobileNetworkState(z3);
    }

    public int setQxdmLogState(int i3, int i4) {
        return this.mOtaManager.setQxdmLogState(i3, i4);
    }

    public int setTcpDumpState(boolean z3) {
        return this.mOtaManager.setTcpDumpState(z3);
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.unregisterListener(absBYDTBoxListener);
    }

    public int uploadCDR(int i3, String str) {
        return this.mOtaManager.uploadCDR(i3, str);
    }
}
