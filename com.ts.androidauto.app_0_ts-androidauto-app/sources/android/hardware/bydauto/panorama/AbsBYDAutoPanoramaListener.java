package android.hardware.bydauto.panorama;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPanoramaListener";

    public void onPanoWorkStateChanged(int mode) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoOutputStateChanged(int mode) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanOutputSignalChanged(int value) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onBackLineConfigChanged(int mode) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onPanoramaOnlineStateChanged(int value) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onRFCameraSwitchStateChanged(int state) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }

    public void onPanoRotationChanged(int value) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onLVDSStateChanged(int state) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onDisplayModeChanged(int mode) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int state) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    public void onACUStateChanged(int state) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onCameraUseChange(String packageName, boolean state) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onFeatureChanged(String feature, int ifHas) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onCarInfoChanged(int state) {
        Log.d(TAG, "onCarInfoChanged");
    }

    public void onPanoramaTransparenceChanged(int state) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onCameraStateChanged(int cameraId, int state) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onPanoAPAStateChanged(int state) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int errCode, String errMessage) {
        Log.d(TAG, "onError: ");
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent event) {
        Log.d(TAG, "onDataChanged");
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int eventType, BYDAutoEventValue eventValue) {
        Log.d(TAG, "onDataEventChanged");
    }
}
