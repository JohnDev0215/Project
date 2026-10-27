package android.hardware.bydauto.panorama;

import android.hardware.bydauto.BYDAutoEventValue;
import android.util.Log;
import b.c;
import b.d;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements d {
    public static final String TAG = "AbsBYDAutoPanoramaListener";

    @Override // b.d
    public /* bridge */ /* synthetic */ void isConnected(boolean z3) {
        super.isConnected(z3);
    }

    @Override // b.d
    public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z3) {
        super.isOptimizationApi(z3);
    }

    public void onACUStateChanged(int i3) {
        Log.d(TAG, "onACUStateChanged");
    }

    public void onBackLineConfigChanged(int i3) {
        Log.d(TAG, "onBackLineConfigChanged");
    }

    public void onCameraStateChanged(int i3, int i4) {
        Log.d(TAG, "onCameraStateChanged");
    }

    public void onCameraUseChange(String str, boolean z3) {
        Log.d(TAG, "onCameraUseChange");
    }

    public void onCarInfoChanged(int i3) {
        Log.d(TAG, "onCarInfoChanged");
    }

    public final void onDataChanged(c cVar) {
        Log.d(TAG, "onDataChanged");
    }

    public void onDataEventChanged(int i3, BYDAutoEventValue bYDAutoEventValue) {
        Log.d(TAG, "onDataEventChanged");
    }

    public void onDisplayModeChanged(int i3) {
        Log.d(TAG, "onDisplayModeChanged");
    }

    public void onEmergencyButtonStateChanged(int i3) {
        Log.d(TAG, "onEmergencyButtonStateChanged");
    }

    public void onError(int i3, String str) {
        Log.d(TAG, "onError: ");
    }

    public void onFeatureChanged(String str, int i3) {
        Log.d(TAG, "onFeatureChanged");
    }

    public void onLVDSStateChanged(int i3) {
        Log.d(TAG, "onLVDSStateChanged");
    }

    public void onPanOutputSignalChanged(int i3) {
        Log.d(TAG, "onPanOutputSignalChanged");
    }

    public void onPanoAPAStateChanged(int i3) {
        Log.d(TAG, "onPanoAPAStateChanged");
    }

    public void onPanoOutputStateChanged(int i3) {
        Log.d(TAG, "onPanoOutputStateChanged");
    }

    public void onPanoRotationChanged(int i3) {
        Log.d(TAG, "onPanoRotationChanged");
    }

    public void onPanoWorkStateChanged(int i3) {
        Log.d(TAG, "onPanoWorkStateChanged ");
    }

    public void onPanoramaOnlineStateChanged(int i3) {
        Log.d(TAG, "onPanoramaOnlineStateChanged");
    }

    public void onPanoramaTransparenceChanged(int i3) {
        Log.d(TAG, "onPanoramaTransparenceChanged");
    }

    public void onRFCameraSwitchStateChanged(int i3) {
        Log.d(TAG, "onRFCameraSwitchStateChanged");
    }
}
