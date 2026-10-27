package android.hardware.bydauto.panorama;

import a.c;
import a.d;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements d {
    protected static final String TAG = "AbsBYDAutoPanoramaListener";

    @Override // a.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z6) {
        super.isConnected(z6);
    }

    public void onACUStateChanged(int i7) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onBackLineConfigChanged(int i7) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onCameraStateChanged(int i7, int i8) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onCameraUseChange(String str, boolean z6) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onCarInfoChanged(int i7) {
        Log.d(TAG, "onCarInfoChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onDisplayModeChanged(int i7) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int i7) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    public void onError(int i7, String str) {
        Log.d(TAG, "onError: ");
    }

    public void onFeatureChanged(String str, int i7) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onLVDSStateChanged(int i7) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onPanOutputSignalChanged(int i7) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onPanoAPAStateChanged(int i7) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    public void onPanoOutputStateChanged(int i7) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanoRotationChanged(int i7) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onPanoWorkStateChanged(int i7) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoramaOnlineStateChanged(int i7) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onPanoramaTransparenceChanged(int i7) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onRFCameraSwitchStateChanged(int i7) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }
}
