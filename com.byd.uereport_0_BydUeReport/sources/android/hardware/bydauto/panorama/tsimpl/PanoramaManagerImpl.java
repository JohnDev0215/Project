package android.hardware.bydauto.panorama.tsimpl;

import android.content.Context;
import android.hardware.bydauto.SettingsManagerImpl;
import android.hardware.bydauto.panorama.AbsBYDAutoPanoramaListener;
import android.hardware.bydauto.panorama.BYDAutoPanoramaDevice;
import android.hardware.bydauto.uistyleconfig.tsimpl.UiStyleConfigManagerImpl;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.panorama.OnPanoramaChangedListener;
import java.util.ArrayList;
import java.util.Iterator;
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
            public void onACUStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onACUStateChanged(i8);
                        }
                    }
                }
            }

            public void onBackLineConfigChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onBackLineConfigChanged(i8);
                        }
                    }
                }
            }

            public void onCameraStateChanged(int i8, int i9) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCameraStateChanged(i8, i9);
                        }
                    }
                }
            }

            public void onCameraUseChange(String str, boolean z7) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCameraUseChange(str, z7);
                        }
                    }
                }
            }

            public void onCarInfoChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCarInfoChanged(i8);
                        }
                    }
                }
            }

            public void onDisplayModeChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onDisplayModeChanged(i8);
                        }
                    }
                }
            }

            public void onEmergencyButtonStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onEmergencyButtonStateChanged(i8);
                        }
                    }
                }
            }

            public void onLVDSStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onLVDSStateChanged(i8);
                        }
                    }
                }
            }

            public void onPanOutputSignalChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanOutputSignalChanged(i8);
                        }
                    }
                }
            }

            public void onPanoAPAStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoAPAStateChanged(i8);
                        }
                    }
                }
            }

            public void onPanoOutputStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoOutputStateChanged(i8);
                        }
                    }
                }
            }

            public void onPanoRotationChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoRotationChanged(i8);
                        }
                    }
                }
            }

            public void onPanoWorkStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoWorkStateChanged(i8);
                        }
                    }
                }
            }

            public void onPanoramaOnlineStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaOnlineStateChanged(i8);
                        }
                    }
                }
            }

            public void onPanoramaTransparenceChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaTransparenceChanged(i8);
                        }
                    }
                }
            }

            public void onRFCameraSwitchStateChanged(int i8) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onRFCameraSwitchStateChanged(i8);
                        }
                    }
                }
            }
        });
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
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

    public int getBackLineConfig() {
        LogUtil.debug(TAG, "getBackLineConfig");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getBackLineConfig();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public String getCameraUsePackageName() {
        LogUtil.debug(TAG, "getCameraUsePackageName");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getCameraUsePackageName();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return null;
    }

    public int getDisplayMode() {
        LogUtil.debug(TAG, "getDisplayMode");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getDisplayMode();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getPanoOutputSignal() {
        LogUtil.debug(TAG, "getPanoOutputSignal");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoOutputSignal();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getPanoOutputState() {
        LogUtil.debug(TAG, "getPanoOutputState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoOutputState();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getPanoRotation() {
        LogUtil.debug(TAG, "getPanoRotation");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoRotation();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getPanoWorkState() {
        LogUtil.debug(TAG, "getPanoWorkState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().getPanoWorkState();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return -1;
    }

    public int getPanoramaOnlineState() {
        LogUtil.debug(TAG, "getPanoramaOnlineState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        UiStyleConfigManagerImpl uiStyleConfigManagerImpl = this.mUiStyleConfigManager;
        if (uiStyleConfigManagerImpl != null) {
            return uiStyleConfigManagerImpl.getSupportPanoramaImage();
        }
        LogUtil.error(TAG, "UiStyleConfigManager still connecting, please wait");
        return -1;
    }

    public boolean isPanoramaSetting() {
        LogUtil.debug(TAG, "isPanoramaSetting");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().isPanoramaSetting();
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return false;
    }

    public boolean isValideCamera(int i8) {
        LogUtil.debug(TAG, "getPanoOutputSignal : " + i8);
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().isValideCamera(i8);
        }
        LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        return false;
    }

    public void registerListener(AbsBYDAutoPanoramaListener absBYDAutoPanoramaListener) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (absBYDAutoPanoramaListener == null) {
            LogUtil.error(TAG, "listener is null");
        } else {
            if (this.mListeners.contains(absBYDAutoPanoramaListener)) {
                return;
            }
            this.mListeners.add(absBYDAutoPanoramaListener);
        }
    }

    public void requestCameraState(int i8) {
        LogUtil.debug(TAG, "requestCameraState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPanoramaSettingsManager().requestCameraState(i8);
        }
    }

    public void unregisterListener(AbsBYDAutoPanoramaListener absBYDAutoPanoramaListener) {
        LogUtil.debug(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (absBYDAutoPanoramaListener == null) {
            LogUtil.error(TAG, "listener is null");
            return;
        }
        synchronized (this.mListeners) {
            if (this.mListeners.contains(absBYDAutoPanoramaListener)) {
                this.mListeners.remove(absBYDAutoPanoramaListener);
            }
        }
    }

    public void registerListener(AbsBYDAutoPanoramaListener absBYDAutoPanoramaListener, int[] iArr) {
        LogUtil.debug(TAG, "registerListener is execute");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
    }
}
