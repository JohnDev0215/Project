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

    public int clearTboxLog() {
        return this.mOtaManager.clearTboxLog();
    }

    public int enableSimcardNetwork(int i2) {
        return this.mOtaManager.enableSimcardNetwork(i2);
    }

    public int getApnParamFromTBox() {
        return this.mOtaManager.getApnParamFromTbox();
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

    public int getTBoxNetworkState(int i2) {
        return this.mOtaManager.getTBoxNetworkState(i2);
    }

    public int getTrafficFromTBox(int i2, String str) {
        return this.mOtaManager.getTrafficFromTbox(i2, str);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return 0;
    }

    public int notifyTBoxCmdResult(int i2, int i3) {
        return this.mOtaManager.notifyTBoxCmdResult(i2, i3);
    }

    public int notifyTBoxTrafficResult(NetWorkQueryType netWorkQueryType) {
        return this.mOtaManager.notifyTBoxTrafficResult(netWorkQueryType.getValue());
    }

    public void registerListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.registerListener(absBYDTBoxListener);
    }

    public int setAPN3State(boolean z) {
        return this.mOtaManager.setApn3State(z);
    }

    public void setMobileNetworkState(boolean z) {
        this.mOtaManager.setMobileNetworkState(z);
    }

    public int setNonWhiteListNetworkEnable(boolean z) {
        return this.mOtaManager.setNonWhiteListNetworkEnable(z);
    }

    public int setQxdmLogState(int i2, int i3) {
        return this.mOtaManager.setQxdmLogState(i2, i3);
    }

    public int setTcpDumpState(boolean z) {
        return this.mOtaManager.setTcpDumpState(z);
    }

    public int syncApnParam(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        return this.mOtaManager.syncApnParam(str, str2, str3, str4, str5, str6, str7);
    }

    public void unregisterListener(AbsBYDTBoxListener absBYDTBoxListener) {
        this.mOtaManager.unregisterListener(absBYDTBoxListener);
    }

    public int uploadCDR(int i2, String str) {
        return this.mOtaManager.uploadCDR(i2, str);
    }
}
