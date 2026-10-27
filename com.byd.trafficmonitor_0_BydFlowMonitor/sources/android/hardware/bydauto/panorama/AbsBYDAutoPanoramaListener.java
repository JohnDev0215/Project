package android.hardware.bydauto.panorama;

import a.b.c;
import a.b.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements d {
    public static final String TAG = "AbsBYDAutoPanoramaListener";

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z) {
        super.isConnected(z);
    }

    @Override // a.b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z) {
        super.isOptimizationApi(z);
    }

    public void onACUStateChanged(int i2) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onBackLineConfigChanged(int i2) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onCameraStateChanged(int i2, int i3) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onCameraUseChange(String str, boolean z) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onCarInfoChanged(int i2) {
        Log.d(TAG, "onCarInfoChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onDisplayModeChanged(int i2) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int i2) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    public void onError(int i2, String str) {
        Log.d(TAG, "onError: ");
    }

    public void onFeatureChanged(String str, int i2) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onLVDSStateChanged(int i2) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onPanOutputSignalChanged(int i2) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onPanoAPAStateChanged(int i2) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    public void onPanoOutputStateChanged(int i2) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanoRotationChanged(int i2) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onPanoWorkStateChanged(int i2) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoramaOnlineStateChanged(int i2) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onPanoramaTransparenceChanged(int i2) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onRFCameraSwitchStateChanged(int i2) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }
}
