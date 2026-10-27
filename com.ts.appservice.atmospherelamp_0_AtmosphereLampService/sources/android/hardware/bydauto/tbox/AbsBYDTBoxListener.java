package android.hardware.bydauto.tbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDTBoxListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent var1) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int var1, String var2) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int var1, BYDAutoEventValue var2) {
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }

    public void onTBoxCmd2IVI(int cmd) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + cmd);
    }

    public void onSyncApnParamResult(int resultCode) {
        Log.w(TAG, "onSyncApnParamResult resultCode : " + resultCode);
    }

    public void onGetApnParamFromTBox(String apn, String username, String pwd, String apnType, String apnProtocol, String proxy, String port) {
        Log.w(TAG, "onGetApnParamFromTBox");
    }

    public void onGetTrafficFromTBox(int apnType, String iccid, String time, long dayUpTraffic, long dayDownTraffic, long monthUpTraffic, long monthDownTraffic) {
        Log.w(TAG, "onGetTrafficFromTBox");
    }

    public void onGetTBoxNetworkState(int apn, int state) {
    }
}
