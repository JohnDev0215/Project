package android.hardware.bydauto.panorama;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements IBYDAutoListener {
    public static final String TAG = "AbsBYDAutoPanoramaListener";

    public void onACUStateChanged(int i) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onBackLineConfigChanged(int i) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onCameraStateChanged(int i, int i2) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onCameraUseChange(String str, boolean z) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onCarInfoChanged(int i) {
        Log.d(TAG, "onCarInfoChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onDisplayModeChanged(int i) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int i) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
        Log.d(TAG, "onError: ");
    }

    public void onFeatureChanged(String str, int i) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onLVDSStateChanged(int i) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onPanOutputSignalChanged(int i) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onPanoAPAStateChanged(int i) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    public void onPanoOutputStateChanged(int i) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanoRotationChanged(int i) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onPanoWorkStateChanged(int i) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoramaOnlineStateChanged(int i) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onPanoramaTransparenceChanged(int i) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onRFCameraSwitchStateChanged(int i) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }
}
