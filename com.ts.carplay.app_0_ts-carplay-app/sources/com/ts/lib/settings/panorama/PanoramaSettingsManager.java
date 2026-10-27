package com.ts.lib.settings.panorama;

import android.os.IBinder;
import android.os.RemoteException;
import com.ts.lib.settings.SettingsAdapterBase;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.panorama.IPanoramaSettingsCallback;
import com.ts.lib.settings.panorama.IPanoramaSettingsService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PanoramaSettingsManager implements SettingsAdapterBase {
    private static final String TAG = PanoramaSettingsManager.class.getSimpleName();
    private List<OnPanoramaChangedListener> mPanoramaChangedListenerList = new ArrayList();
    private final IPanoramaSettingsCallback mPanoramaSettingsCallback = new IPanoramaSettingsCallback.Stub() { // from class: com.ts.lib.settings.panorama.PanoramaSettingsManager.1
        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoOutputStateChanged(int mode) {
            PanoramaSettingsManager.this.callbackPanoOutputStateChanged(mode);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoWorkStateChanged(int mode) {
            PanoramaSettingsManager.this.callbackPanoWorkStateChanged(mode);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanOutputSignalChanged(int value) {
            PanoramaSettingsManager.this.callbackPanOutputSignalChanged(value);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onBackLineConfigChanged(int mode) {
            PanoramaSettingsManager.this.callbackBackLineConfigChanged(mode);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoramaOnlineStateChanged(int value) {
            PanoramaSettingsManager.this.callbackPanoramaOnlineStateChanged(value);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onRFCameraSwitchStateChanged(int state) {
            PanoramaSettingsManager.this.callbackRFCameraSwitchStateChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoRotationChanged(int value) {
            PanoramaSettingsManager.this.callbackPanoRotationChanged(value);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onLVDSStateChanged(int state) {
            PanoramaSettingsManager.this.callbackLVDSStateChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onDisplayModeChanged(int mode) {
            PanoramaSettingsManager.this.callbackDisplayModeChanged(mode);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onEmergencyButtonStateChanged(int state) {
            PanoramaSettingsManager.this.callbackEmergencyButtonStateChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onACUStateChanged(int state) {
            PanoramaSettingsManager.this.callbackACUStateChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCameraUseChange(String packageName, boolean state) {
            PanoramaSettingsManager.this.callbackCameraUseChange(packageName, state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCarInfoChanged(int state) {
            PanoramaSettingsManager.this.callbackCarInfoChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoramaTransparenceChanged(int state) {
            PanoramaSettingsManager.this.callbackPanoramaTransparenceChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onPanoAPAStateChanged(int state) {
            PanoramaSettingsManager.this.callbackPanoAPAStateChanged(state);
        }

        @Override // com.ts.lib.settings.panorama.IPanoramaSettingsCallback
        public void onCameraStateChanged(int cameraId, int state) {
            PanoramaSettingsManager.this.callbackCameraStateChanged(cameraId, state);
        }
    };
    private IPanoramaSettingsService mPanoramaSettingsService;

    public PanoramaSettingsManager(IBinder binder) {
        LogUtil.debug(TAG, "init panorama manager");
        this.mPanoramaSettingsService = IPanoramaSettingsService.Stub.asInterface(binder);
        subscribePanoramaCallback();
    }

    public void subscribePanoramaCallback(OnPanoramaChangedListener listener) {
        synchronized (this.mPanoramaChangedListenerList) {
            if (!this.mPanoramaChangedListenerList.contains(listener) && listener != null) {
                this.mPanoramaChangedListenerList.add(listener);
                LogUtil.debug(TAG, "subscribeLanguageCallback ok --> listener size: " + this.mPanoramaChangedListenerList.size());
            }
        }
    }

    public void unSubscribeLanguageCallback(OnPanoramaChangedListener listener) {
        synchronized (this.mPanoramaChangedListenerList) {
            if (listener != null) {
                if (this.mPanoramaChangedListenerList.contains(listener)) {
                    this.mPanoramaChangedListenerList.remove(listener);
                    LogUtil.debug(TAG, "unSubscribeLanguageCallback ok --> listener size: " + this.mPanoramaChangedListenerList.size());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoOutputStateChanged(int mode) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoOutputStateChanged(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoWorkStateChanged(int mode) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoWorkStateChanged(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanOutputSignalChanged(int value) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanOutputSignalChanged(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackBackLineConfigChanged(int mode) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onBackLineConfigChanged(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoramaOnlineStateChanged(int value) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoramaOnlineStateChanged(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackRFCameraSwitchStateChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onRFCameraSwitchStateChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoRotationChanged(int value) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoRotationChanged(value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackLVDSStateChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onLVDSStateChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackDisplayModeChanged(int mode) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onDisplayModeChanged(mode);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackEmergencyButtonStateChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onEmergencyButtonStateChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackACUStateChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onACUStateChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCameraUseChange(String packageName, boolean state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onCameraUseChange(packageName, state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCarInfoChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onCarInfoChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoramaTransparenceChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoramaTransparenceChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackPanoAPAStateChanged(int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onPanoAPAStateChanged(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackCameraStateChanged(int cameraId, int state) {
        for (OnPanoramaChangedListener listener : this.mPanoramaChangedListenerList) {
            listener.onCameraStateChanged(cameraId, state);
        }
    }

    public void subscribePanoramaCallback() {
        if (this.mPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "subscribePanoramaCallback");
        try {
            this.mPanoramaSettingsService.subscribePanoramaCallback(this.mPanoramaSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "subscribeLanguageCallback error ", ex);
        }
    }

    public void unsubscribePanoramaCallback() {
        if (this.mPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return;
        }
        LogUtil.debug(TAG, "unsubscribePanoramaCallback");
        try {
            this.mPanoramaSettingsService.unsubscribePanoramaCallback(this.mPanoramaSettingsCallback);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "unsubscribeLanguageCallback error ", ex);
        }
    }

    public int getPanoOutputSignal() {
        LogUtil.debug(TAG, "getPanoOutputSignal ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getPanoOutputSignal();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getPanoOutputSignal error: ", ex);
            return -1;
        }
    }

    public int getPanoWorkState() {
        LogUtil.debug(TAG, "getPanoWorkState ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getPanoWorkState();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getPanoWorkState error: ", ex);
            return -1;
        }
    }

    public int getBackLineConfig() {
        LogUtil.debug(TAG, "getBackLineConfig ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getBackLineConfig();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getBackLineConfig error: ", ex);
            return -1;
        }
    }

    public int getPanoOutputState() {
        LogUtil.debug(TAG, "getPanoOutputState ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getPanoOutputState();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getPanoOutputState error: ", ex);
            return -1;
        }
    }

    public int getPanoRotation() {
        LogUtil.debug(TAG, "getPanoRotation ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getPanoRotation();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getPanoRotation error: ", ex);
            return -1;
        }
    }

    public int getDisplayMode() {
        LogUtil.debug(TAG, "getDisplayMode ");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return -1;
        }
        try {
            return iPanoramaSettingsService.getDisplayMode();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getDisplayMode error: ", ex);
            return -1;
        }
    }

    public boolean isValideCamera(int cameraId) {
        LogUtil.debug(TAG, "isValideCamera :" + cameraId);
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return false;
        }
        try {
            return iPanoramaSettingsService.isValideCamera(cameraId);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "isValideCamera error: ", ex);
            return false;
        }
    }

    public boolean isPanoramaSetting() {
        LogUtil.debug(TAG, "isPanoramaSetting :");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return false;
        }
        try {
            return iPanoramaSettingsService.isPanoramaSetting();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "isPanoramaSetting error: ", ex);
            return false;
        }
    }

    public String getCameraUsePackageName() {
        LogUtil.debug(TAG, "getCameraUsePackageName :");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return null;
        }
        try {
            return iPanoramaSettingsService.getCameraUsePackageName();
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getCameraUsePackageName error: ", ex);
            return null;
        }
    }

    public void requestCameraState(int cameraId) {
        LogUtil.debug(TAG, "requestCameraState :");
        IPanoramaSettingsService iPanoramaSettingsService = this.mPanoramaSettingsService;
        if (iPanoramaSettingsService == null) {
            LogUtil.error(TAG, "mPanoramaSettingsService is null");
            return;
        }
        try {
            iPanoramaSettingsService.requestCameraState(cameraId);
        } catch (RemoteException ex) {
            LogUtil.error(TAG, "getCameraUsePackageName error: ", ex);
        }
    }

    @Override // com.ts.lib.settings.SettingsAdapterBase
    public void onSettingsDisconnected() {
        LogUtil.debug(TAG, "onSettingsDisconnected");
        this.mPanoramaSettingsService = null;
        unsubscribePanoramaCallback();
    }
}
