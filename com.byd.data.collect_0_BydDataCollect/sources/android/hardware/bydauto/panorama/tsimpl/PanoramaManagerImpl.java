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

    /* JADX INFO: renamed from: android.hardware.bydauto.panorama.tsimpl.PanoramaManagerImpl$1 */
    class AnonymousClass1 implements OnPanoramaChangedListener {
        AnonymousClass1() {
        }

        public void onACUStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onACUStateChanged(i2);
                    }
                }
            }
        }

        public void onBackLineConfigChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onBackLineConfigChanged(i2);
                    }
                }
            }
        }

        public void onCameraStateChanged(int i2, int i3) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onCameraStateChanged(i2, i3);
                    }
                }
            }
        }

        public void onCameraUseChange(String str, boolean z) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onCameraUseChange(str, z);
                    }
                }
            }
        }

        public void onCarInfoChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onCarInfoChanged(i2);
                    }
                }
            }
        }

        public void onDisplayModeChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onDisplayModeChanged(i2);
                    }
                }
            }
        }

        public void onEmergencyButtonStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onEmergencyButtonStateChanged(i2);
                    }
                }
            }
        }

        public void onLVDSStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onLVDSStateChanged(i2);
                    }
                }
            }
        }

        public void onPanOutputSignalChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanOutputSignalChanged(i2);
                    }
                }
            }
        }

        public void onPanoAPAStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoAPAStateChanged(i2);
                    }
                }
            }
        }

        public void onPanoOutputStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoOutputStateChanged(i2);
                    }
                }
            }
        }

        public void onPanoRotationChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoRotationChanged(i2);
                    }
                }
            }
        }

        public void onPanoWorkStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoWorkStateChanged(i2);
                    }
                }
            }
        }

        public void onPanoramaOnlineStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaOnlineStateChanged(i2);
                    }
                }
            }
        }

        public void onPanoramaTransparenceChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaTransparenceChanged(i2);
                    }
                }
            }
        }

        public void onRFCameraSwitchStateChanged(int i2) {
            synchronized (PanoramaManagerImpl.this.mListeners) {
                if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                    Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoPanoramaListener) it.next()).onRFCameraSwitchStateChanged(i2);
                    }
                }
            }
        }
    }

    private PanoramaManagerImpl(Context context) {
        this.mContext = context;
        this.mUiStyleConfigManager = UiStyleConfigManagerImpl.getsInstance(context);
        this.mSettingsManagerImpl = SettingsManagerImpl.getInstance(context);
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        this.mSettingsManagerImpl.setOnPanoramaChangedListener(new OnPanoramaChangedListener() { // from class: android.hardware.bydauto.panorama.tsimpl.PanoramaManagerImpl.1
            AnonymousClass1() {
            }

            public void onACUStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onACUStateChanged(i2);
                        }
                    }
                }
            }

            public void onBackLineConfigChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onBackLineConfigChanged(i2);
                        }
                    }
                }
            }

            public void onCameraStateChanged(int i2, int i3) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCameraStateChanged(i2, i3);
                        }
                    }
                }
            }

            public void onCameraUseChange(String str, boolean z) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCameraUseChange(str, z);
                        }
                    }
                }
            }

            public void onCarInfoChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onCarInfoChanged(i2);
                        }
                    }
                }
            }

            public void onDisplayModeChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onDisplayModeChanged(i2);
                        }
                    }
                }
            }

            public void onEmergencyButtonStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onEmergencyButtonStateChanged(i2);
                        }
                    }
                }
            }

            public void onLVDSStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onLVDSStateChanged(i2);
                        }
                    }
                }
            }

            public void onPanOutputSignalChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanOutputSignalChanged(i2);
                        }
                    }
                }
            }

            public void onPanoAPAStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoAPAStateChanged(i2);
                        }
                    }
                }
            }

            public void onPanoOutputStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoOutputStateChanged(i2);
                        }
                    }
                }
            }

            public void onPanoRotationChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoRotationChanged(i2);
                        }
                    }
                }
            }

            public void onPanoWorkStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoWorkStateChanged(i2);
                        }
                    }
                }
            }

            public void onPanoramaOnlineStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaOnlineStateChanged(i2);
                        }
                    }
                }
            }

            public void onPanoramaTransparenceChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onPanoramaTransparenceChanged(i2);
                        }
                    }
                }
            }

            public void onRFCameraSwitchStateChanged(int i2) {
                synchronized (PanoramaManagerImpl.this.mListeners) {
                    if (PanoramaManagerImpl.this.mListeners != null && PanoramaManagerImpl.this.mListeners.size() > 0) {
                        Iterator it = PanoramaManagerImpl.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((AbsBYDAutoPanoramaListener) it.next()).onRFCameraSwitchStateChanged(i2);
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

    public boolean isValideCamera(int i2) {
        LogUtil.debug(TAG, "getPanoOutputSignal : " + i2);
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_COMMON);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() != null) {
            return this.mSettingsManagerImpl.getPanoramaSettingsManager().isValideCamera(i2);
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

    public void requestCameraState(int i2) {
        LogUtil.debug(TAG, "requestCameraState");
        enforcePermission(BYDAutoPanoramaDevice.PERMISSION_BYDAUTO_PANORAMA_GET);
        if (this.mSettingsManagerImpl.getPanoramaSettingsManager() == null) {
            LogUtil.error(TAG, "SettingsAdapter still connecting, please wait");
        } else {
            this.mSettingsManagerImpl.getPanoramaSettingsManager().requestCameraState(i2);
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
