package android.hardware.bydauto.tbox;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements d {
    public static final String TAG = "AbsBYDTBoxListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i, String str) {
    }

    public void onGetApnParamFromTBox(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Log.w(TAG, "onGetApnParamFromTBox");
    }

    public void onGetTBoxNetworkState(int i, int i2) {
    }

    public void onGetTrafficFromTBox(int i, String str, String str2, long j, long j2, long j3, long j4) {
        Log.w(TAG, "onGetTrafficFromTBox");
    }

    public void onSyncApnParamResult(int i) {
        Log.w(TAG, "onSyncApnParamResult resultCode : " + i);
    }

    public void onTBoxCmd2IVI(int i) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + i);
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }
}
