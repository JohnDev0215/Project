package android.hardware.bydauto.tbox;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDTBoxListener";

    @Override // android.hardware.IBYDAutoListener
    public void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onGetTBoxNetworkState(int i, int i2) {
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }

    public void onTBoxCmd2IVI(int i) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + i);
    }

    public void onSyncApnParamResult(int i) {
        Log.w(TAG, "onSyncApnParamResult resultCode : " + i);
    }

    public void onGetApnParamFromTBox(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Log.w(TAG, "onGetApnParamFromTBox");
    }

    public void onGetTrafficFromTBox(int i, String str, String str2, long j, long j2, long j3, long j4) {
        Log.w(TAG, "onGetTrafficFromTBox");
    }
}
