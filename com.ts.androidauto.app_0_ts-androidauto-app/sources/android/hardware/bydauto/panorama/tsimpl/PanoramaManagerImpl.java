package android.hardware.bydauto.panorama.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.panorama.OnPanoramaChangedListener;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PanoramaManagerImpl {
    private static final String TAG = "PanoramaManagerImpl";
    private static volatile PanoramaManagerImpl sInstance;
    private Context mContext;
    private SettingsManagerImpl mSettingsManagerImpl;
    private UiStyleConfigManagerImpl mUiStyleConfigManager;
    private final List<AbsBYDAutoPanoramaListener> mListeners = new ArrayList();
    private boolean mIgnoreBydPermission = false;

    private PanoramaManagerImpl(Context context) {
        this.mContext = context;
        this.mUiStyleConfigManager = UiStyleConfigManagerImpl.getsInstance(context);
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        this.mSettingsManagerImpl.setOnPanoramaChangedListener(new OnPanoramaChangedListener() { // from class: android.hardware.bydauto.panorama.tsimpl.PanoramaManagerImpl.1
            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoOutputStateChanged(int mode) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoOutputStateChanged(mode);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoWorkStateChanged(int mode) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoWorkStateChanged(mode);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanOutputSignalChanged(int value) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanOutputSignalChanged(value);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onBackLineConfigChanged(int mode) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onBackLineConfigChanged(mode);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoramaOnlineStateChanged(int value) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoramaOnlineStateChanged(value);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onRFCameraSwitchStateChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onRFCameraSwitchStateChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoRotationChanged(int value) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoRotationChanged(value);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onLVDSStateChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onLVDSStateChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onDisplayModeChanged(int mode) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onDisplayModeChanged(mode);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onEmergencyButtonStateChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onEmergencyButtonStateChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onACUStateChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onACUStateChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onCameraUseChange(String packageName, boolean state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onCameraUseChange(packageName, state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onCarInfoChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onCarInfoChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoramaTransparenceChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoramaTransparenceChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onPanoAPAStateChanged(int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onPanoAPAStateChanged(state);
                        }
                    }
                }
            }

            @Override // com.ts.lib.settings.panorama.OnPanoramaChangedListener
            public void onCameraStateChanged(int cameraId, int state) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        for (AbsBYDAutoPanoramaListener listener : PanoramaManagerImpl.this.mListeners) {
                            listener.onCameraStateChanged(cameraId, state);
                        }
                    }
                }
            }
        });
    }

    public static PanoramaManagerImpl getsInstance(Context context) {
        if (sInstance == null) {
            synchronized (PanoramaManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new PanoramaManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    public void registerListener(AbsBYDAutoPanoramaListener listener) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
        } else if (!this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
        }
    }

    public void registerListener(AbsBYDAutoPanoramaListener listener, int[] featureIDs) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
    }

    public void unregisterListener(AbsBYDAutoPanoramaListener listener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (listener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(listener)) {
                this.mListeners.remove(listener);
            }
        }
    }

    public int getPanoOutputSignal() {
        LogUtil.debug(TAG, "getPanoOutputSignal");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoOutputSignal();
    }

    public int getPanoWorkState() {
        LogUtil.debug(TAG, "getPanoWorkState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoWorkState();
    }

    public int getBackLineConfig() {
        LogUtil.debug(TAG, "getBackLineConfig");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getBackLineConfig();
    }

    public int getPanoOutputState() {
        LogUtil.debug(TAG, "getPanoOutputState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoOutputState();
    }

    public int getPanoRotation() {
        LogUtil.debug(TAG, "getPanoRotation");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoRotation();
    }

    public int getDisplayMode() {
        LogUtil.debug(TAG, "getDisplayMode");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return -1;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getDisplayMode();
    }

    public int getPanoramaOnlineState() {
        LogUtil.debug(TAG, "getPanoramaOnlineState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        UiStyleConfigManagerImpl uiStyleConfigManagerImpl = this.mUiStyleConfigManager;
        if (uiStyleConfigManagerImpl == null) {
            LogUtil.error(TAG, "UiStyleConfigManager still connecting, please wait");
            return -1;
        }
        return uiStyleConfigManagerImpl.getSupportPanoramaImage();
    }

    public boolean isValideCamera(int cameraId) {
        LogUtil.debug(TAG, "getPanoOutputSignal : " + cameraId);
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return false;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().isValideCamera(cameraId);
    }

    public boolean isPanoramaSetting() {
        LogUtil.debug(TAG, "isPanoramaSetting");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return false;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().isPanoramaSetting();
    }

    public String getCameraUsePackageName() {
        LogUtil.debug(TAG, "getCameraUsePackageName");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
            return null;
        }
        return this.mSettingsManagerImpl.getPanoramaSettingsManager().getCameraUsePackageName();
    }

    public void requestCameraState(int cameraId) {
        LogUtil.debug(TAG, "requestCameraState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPanoramaSettingsManager().requestCameraState(cameraId);
        }
    }
}
