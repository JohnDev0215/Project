package android.hardware.bydauto.panorama;

import android.hardware.IBYDAutoEvent;
import android.hardware.IBYDAutoListener;
import android.hardware.bydauto.BYDAutoEventValue;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbsBYDAutoPanoramaListener implements IBYDAutoListener {
    protected static final String TAG = "AbsBYDAutoPanoramaListener";

    public void onACUStateChanged(int i) {
    }

    public void onBackLineConfigChanged(int i) {
    }

    public void onCarInfoChanged(int i) {
    }

    @Override // android.hardware.IBYDAutoListener
    public final void onDataChanged(IBYDAutoEvent iBYDAutoEvent) {
    }

    public void onDisplayModeChanged(int i) {
    }

    public void onEmergencyButtonStateChanged(int i) {
    }

    public void onLVDSStateChanged(int i) {
    }

    public void onPanOutputSignalChanged(int i) {
    }

    public void onPanoAPAStateChanged(int i) {
    }

    public void onPanoOutputStateChanged(int i) {
    }

    public void onPanoRotationChanged(int i) {
    }

    public void onPanoWorkStateChanged(int i) {
    }

    public void onPanoramaOnlineStateChanged(int i) {
    }

    public void onPanoramaTransparenceChanged(int i) {
    }

    public void onRFCameraSwitchStateChanged(int i) {
    }

    public void onCameraStateChanged(int i, int i2) {
    }

    public void onCameraUseChange(String str, boolean z) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.IBYDAutoListener
    public void onError(int i, String str) {
    }

    public void onFeatureChanged(String str, int i) {
    }
}
