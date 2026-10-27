package android.hardware.bydauto.tbox;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDTBoxListener implements d {
    public static final String TAG = "AbsBYDTBoxListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onDataChanged(c cVar) {
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
    }

    public void onError(int i8, String str) {
    }

    public void onGetApnParamFromTBox(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Log.w(TAG, "onGetApnParamFromTBox");
    }

    public void onGetTBoxNetworkState(int i8, int i9) {
    }

    public void onGetTrafficFromTBox(int i8, String str, String str2, long j8, long j9, long j10, long j11) {
        Log.w(TAG, "onGetTrafficFromTBox");
    }

    public void onSyncApnParamResult(int i8) {
        Log.w(TAG, "onSyncApnParamResult resultCode : " + i8);
    }

    public void onTBoxCmd2IVI(int i8) {
        Log.w(TAG, "onTBoxCmd2IVI cmd : " + i8);
    }

    public void onUpdateTrafficData() {
        Log.w(TAG, "onUpdateTrafficData");
    }
}
