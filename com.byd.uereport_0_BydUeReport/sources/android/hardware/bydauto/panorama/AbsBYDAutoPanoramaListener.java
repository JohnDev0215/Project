package android.hardware.bydauto.panorama;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements d {
    public static final String TAG = "AbsBYDAutoPanoramaListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
        super.isConnected(z7);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
        super.isOptimizationApi(z7);
    }

    public void onACUStateChanged(int i8) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onBackLineConfigChanged(int i8) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onCameraStateChanged(int i8, int i9) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onCameraUseChange(String str, boolean z7) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onCarInfoChanged(int i8) {
        Log.d(TAG, "onCarInfoChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onDisplayModeChanged(int i8) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int i8) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    public void onError(int i8, String str) {
        Log.d(TAG, "onError: ");
    }

    public void onFeatureChanged(String str, int i8) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onLVDSStateChanged(int i8) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onPanOutputSignalChanged(int i8) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onPanoAPAStateChanged(int i8) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    public void onPanoOutputStateChanged(int i8) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanoRotationChanged(int i8) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onPanoWorkStateChanged(int i8) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoramaOnlineStateChanged(int i8) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onPanoramaTransparenceChanged(int i8) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onRFCameraSwitchStateChanged(int i8) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }
}
