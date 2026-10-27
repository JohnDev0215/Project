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

    BYDAutoTBoxDevice(Context con) {
        super(con);
        Log.d(TAG, "BYDAutoTBoxDevice: conn = " + con);
        this.mOtaManager = OtaManagerImpl.getInstance(con);
    }

    public static synchronized BYDAutoTBoxDevice getInstance(Context con) {
        if (mBydAutoTboxDevice == null) {
            mBydAutoTboxDevice = new BYDAutoTBoxDevice(con);
        }
        return mBydAutoTboxDevice;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return 0;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return null;
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return null;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int getType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoDevice
    public int[] getFeatureList() {
        return new int[0];
    }

    public void registerListener(AbsBYDTBoxListener listener) {
        this.mOtaManager.registerListener(listener);
    }

    public void unregisterListener(AbsBYDTBoxListener listener) {
        this.mOtaManager.unregisterListener(listener);
    }

    public void setMobileNetworkState(boolean state) {
        this.mOtaManager.setMobileNetworkState(state);
    }

    public int notifyTBoxTrafficResult(NetWorkQueryType result) {
        return this.mOtaManager.notifyTBoxTrafficResult(result.getValue());
    }

    public int notifyTBoxCmdResult(int cmd, int result) {
        return this.mOtaManager.notifyTBoxCmdResult(cmd, result);
    }

    public int uploadCDR(int length, String cdrStr) {
        return this.mOtaManager.uploadCDR(length, cdrStr);
    }

    public int setTcpDumpState(boolean state) {
        return this.mOtaManager.setTcpDumpState(state);
    }

    public int clearTboxLog() {
        return this.mOtaManager.clearTboxLog();
    }

    public int setAPN3State(boolean state) {
        return this.mOtaManager.setApn3State(state);
    }

    public int setQxdmLogState(int logState, int logType) {
        return this.mOtaManager.setQxdmLogState(logState, logType);
    }

    public int enableSimcardNetwork(int simCardType) {
        return this.mOtaManager.enableSimcardNetwork(simCardType);
    }

    public int syncApnParam(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
        return this.mOtaManager.syncApnParam(apn, username, pwd, apnType, apnProtocol, proxy, port);
    }

    public int getTrafficFromTBox(int apnType, String iccid) {
        return this.mOtaManager.getTrafficFromTbox(apnType, iccid);
    }

    public int setNonWhiteListNetworkEnable(boolean enableFlag) {
        return this.mOtaManager.setNonWhiteListNetworkEnable(enableFlag);
    }

    public int getApnParamFromTBox() {
        return this.mOtaManager.getApnParamFromTbox();
    }

    public int getTBoxNetworkState(int apnType) {
        return this.mOtaManager.getTBoxNetworkState(apnType);
    }
}
