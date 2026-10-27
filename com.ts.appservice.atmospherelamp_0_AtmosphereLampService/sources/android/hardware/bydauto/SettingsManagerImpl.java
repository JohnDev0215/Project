package android.hardware.bydauto;

import android.content.Context;
import com.ts.lib.settings.SettingsAdapterManager;
import com.ts.lib.settings.appconfig.AppConfigSettingsManger;
import com.ts.lib.settings.appconfig.OnAppConfigChangedListener;
import com.ts.lib.settings.appconfig.OnCarTypeChangedListener;
import com.ts.lib.settings.boot.AppBootSettingsManager;
import com.ts.lib.settings.cartips.CartipsSettingsManager;
import com.ts.lib.settings.cartips.OnCartipsChangedListener;
import com.ts.lib.settings.cluster.ClusterSettingsManager;
import com.ts.lib.settings.cluster.OnClusterChangedListener;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.container.ContainerSettingManager;
import com.ts.lib.settings.dms.DmsSettingsManager;
import com.ts.lib.settings.dms.OnDmsChangedListener;
import com.ts.lib.settings.interfaces.OnManagerConnChangedListener;
import com.ts.lib.settings.key.KeySettingsManager;
import com.ts.lib.settings.key.OnKeyListener;
import com.ts.lib.settings.language.LanguageSettingsManager;
import com.ts.lib.settings.language.OnLanguageChangedListener;
import com.ts.lib.settings.location.LocationSettingsManager;
import com.ts.lib.settings.location.OnLocationChangedListener;
import com.ts.lib.settings.microphone.MicSettingsManager;
import com.ts.lib.settings.microphone.OnMicChangedListener;
import com.ts.lib.settings.panorama.OnPanoramaChangedListener;
import com.ts.lib.settings.panorama.PanoramaSettingsManager;
import com.ts.lib.settings.property.PropertySettingsManager;
import com.ts.lib.settings.theme.OnThemeChangedListener;
import com.ts.lib.settings.theme.ThemeSettingsManager;
import com.ts.lib.settings.version.VersionSettingsManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsManagerImpl {
    private static final String TAG = SettingsManagerImpl.class.getSimpleName();
    private static SettingsManagerImpl mInstance;
    private AppBootSettingsManager mAppBootSettingsManager;
    private AppConfigSettingsManger mAppConfigSettingsManger;
    private CartipsSettingsManager mCartipsSettingsManager;
    private ClusterSettingsManager mClusterSettingsManager;
    private ContainerSettingManager mContainerSettingManager;
    private Context mContext;
    private DmsSettingsManager mDmsSettingsManager;
    private KeySettingsManager mKeySettingsManager;
    private LanguageSettingsManager mLanguageSettingsManager;
    private LocationSettingsManager mLocationSettingsManager;
    private MicSettingsManager mMicSettingsManager;
    private PanoramaSettingsManager mPanoramaSettingsManager;
    private PropertySettingsManager mPropertySettingsManager;
    private SettingsAdapterManager mSettingsAdapterManager;
    private ThemeSettingsManager mThemeSettingsManager;
    private VersionSettingsManager mVersionSettingsManager;
    private final List<OnKeyListener> mOnKeyListeners = new ArrayList();
    private final List<OnMicChangedListener> mOnMicChangedListeners = new ArrayList();
    private final List<OnClusterChangedListener> mOnClusterChangedListeners = new ArrayList();
    private final List<OnThemeChangedListener> mOnThemeChangedListeners = new ArrayList();
    private final List<OnLanguageChangedListener> mOnLanguageChangedListeners = new ArrayList();
    private final List<OnPanoramaChangedListener> mOnPanoramaChangedListeners = new ArrayList();
    private final List<OnLocationChangedListener> mOnLocationChangedListeners = new ArrayList();
    private final List<OnCartipsChangedListener> mOnCartipsChangedListeners = new ArrayList();
    private final List<OnDmsChangedListener> mOnDmsChangedListeners = new ArrayList();
    private final List<OnAppConfigChangedListener> mOnAppConfigChangedListeners = new ArrayList();
    private final List<OnCarTypeChangedListener> mOnCarTypeChangedListeners = new ArrayList();
    private OnKeyListener mOnKeyListener = new OnKeyListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.2
        public void onIdlerWheel(int value) {
            synchronized (SettingsManagerImpl.this.mOnKeyListeners) {
                for (OnKeyListener listener : SettingsManagerImpl.this.mOnKeyListeners) {
                    listener.onIdlerWheel(value);
                }
            }
        }

        public void onKeyClick(int keyValue, int click) {
            synchronized (SettingsManagerImpl.this.mOnKeyListeners) {
                for (OnKeyListener listener : SettingsManagerImpl.this.mOnKeyListeners) {
                    listener.onKeyClick(keyValue, click);
                }
            }
        }
    };
    private OnMicChangedListener mOnMicChangedListener = new OnMicChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.3
        public void onMicUseChanged(String packageName, boolean status) {
            synchronized (SettingsManagerImpl.this.mOnMicChangedListeners) {
                for (OnMicChangedListener listener : SettingsManagerImpl.this.mOnMicChangedListeners) {
                    listener.onMicUseChanged(packageName, status);
                }
            }
        }
    };
    private OnClusterChangedListener mOnClusterChangedListener = new OnClusterChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.4
        public void onINSAdasCenterDisplayChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                for (OnClusterChangedListener listener : SettingsManagerImpl.this.mOnClusterChangedListeners) {
                    listener.onINSAdasCenterDisplayChanged(value);
                }
            }
        }

        public void onINSCenterDisplay(int value) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                for (OnClusterChangedListener listener : SettingsManagerImpl.this.mOnClusterChangedListeners) {
                    listener.onINSCenterDisplay(value);
                }
            }
        }

        public void onDayNightModeChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                for (OnClusterChangedListener listener : SettingsManagerImpl.this.mOnClusterChangedListeners) {
                    listener.onDayNightModeChanged(mode);
                }
            }
        }
    };
    private OnCartipsChangedListener mOnCartipsChangedListener = new OnCartipsChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.5
        public void onCarTipsSoundTypeChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnCartipsChangedListeners) {
                for (OnCartipsChangedListener listener : SettingsManagerImpl.this.mOnCartipsChangedListeners) {
                    listener.onCarTipsSoundTypeChanged(value);
                }
            }
        }
    };
    private OnThemeChangedListener mOnThemeChangedListener = new OnThemeChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.6
        public void onThemeChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnThemeChangedListeners) {
                for (OnThemeChangedListener listener : SettingsManagerImpl.this.mOnThemeChangedListeners) {
                    listener.onThemeChanged(state);
                }
            }
        }
    };
    private OnLanguageChangedListener mOnLanguageChangedListener = new OnLanguageChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.7
        public void onLanguageChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnLanguageChangedListeners) {
                for (OnLanguageChangedListener listener : SettingsManagerImpl.this.mOnLanguageChangedListeners) {
                    listener.onLanguageChanged(value);
                }
            }
        }
    };
    private OnPanoramaChangedListener mOnPanoramaChangedListener = new OnPanoramaChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.8
        public void onPanoOutputStateChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoOutputStateChanged(mode);
                }
            }
        }

        public void onPanoWorkStateChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoWorkStateChanged(mode);
                }
            }
        }

        public void onPanOutputSignalChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanOutputSignalChanged(value);
                }
            }
        }

        public void onBackLineConfigChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onBackLineConfigChanged(mode);
                }
            }
        }

        public void onPanoramaOnlineStateChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoramaOnlineStateChanged(value);
                }
            }
        }

        public void onRFCameraSwitchStateChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onRFCameraSwitchStateChanged(state);
                }
            }
        }

        public void onPanoRotationChanged(int value) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoRotationChanged(value);
                }
            }
        }

        public void onLVDSStateChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onLVDSStateChanged(state);
                }
            }
        }

        public void onDisplayModeChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onDisplayModeChanged(mode);
                }
            }
        }

        public void onEmergencyButtonStateChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onEmergencyButtonStateChanged(state);
                }
            }
        }

        public void onACUStateChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onACUStateChanged(state);
                }
            }
        }

        public void onCameraUseChange(String packageName, boolean state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onCameraUseChange(packageName, state);
                }
            }
        }

        public void onCarInfoChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onCarInfoChanged(state);
                }
            }
        }

        public void onPanoramaTransparenceChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoramaTransparenceChanged(state);
                }
            }
        }

        public void onPanoAPAStateChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onPanoAPAStateChanged(state);
                }
            }
        }

        public void onCameraStateChanged(int cameraId, int state) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                for (OnPanoramaChangedListener listener : SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                    listener.onCameraStateChanged(cameraId, state);
                }
            }
        }
    };
    private OnLocationChangedListener mOnLocationChangedListener = new OnLocationChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.9
        public void onLocUseChange() {
            synchronized (SettingsManagerImpl.this.mOnLocationChangedListeners) {
                for (OnLocationChangedListener listener : SettingsManagerImpl.this.mOnLocationChangedListeners) {
                    listener.onLocUseChange();
                }
            }
        }
    };
    private OnDmsChangedListener mOnDmsChangedListener = new OnDmsChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.10
        public void onDmsSwitchChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onDmsSwitchChanged(state);
                }
            }
        }

        public void onFatigueSwitchChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onFatigueSwitchChanged(state);
                }
            }
        }

        public void onAttentionSwitchChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onAttentionSwitchChanged(state);
                }
            }
        }

        public void onFatigueDetectChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onFatigueDetectChanged(state);
                }
            }
        }

        public void onAttentionDetectChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onAttentionDetectChanged(state);
                }
            }
        }

        public void onBlockStatusChanged(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onBlockStatusChanged(state);
                }
            }
        }

        public void onDriverNotResponse() {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onDriverNotResponse();
                }
            }
        }

        public void onRestoreFatigueDetect() {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onRestoreFatigueDetect();
                }
            }
        }

        public void onNotifyDmsServerState(int state) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onNotifyDmsServerState(state);
                }
            }
        }

        public void onDmsModeChanged(int mode) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                for (OnDmsChangedListener listener : SettingsManagerImpl.this.mOnDmsChangedListeners) {
                    listener.onDmsModeChanged(mode);
                }
            }
        }
    };
    private OnAppConfigChangedListener mOnAppConfigChangedListener = new OnAppConfigChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.11
        public void onChangedConfigArea(int state) {
            synchronized (SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                for (OnAppConfigChangedListener listener : SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                    listener.onChangedConfigArea(state);
                }
            }
        }

        public void onChangedCpaa(int state) {
            synchronized (SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                for (OnAppConfigChangedListener listener : SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                    listener.onChangedCpaa(state);
                }
            }
        }
    };
    private OnCarTypeChangedListener mOnCarTypeChangedListener = new OnCarTypeChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.12
        public void onStatusChangeCarType(boolean status) {
            synchronized (SettingsManagerImpl.this.mOnCarTypeChangedListeners) {
                for (OnCarTypeChangedListener listener : SettingsManagerImpl.this.mOnCarTypeChangedListeners) {
                    listener.onStatusChangeCarType(status);
                }
            }
        }
    };

    private SettingsManagerImpl(Context context) {
        this.mContext = context;
        SettingsAdapterManager settingsAdapterManager = SettingsAdapterManager.getInstance(context);
        this.mSettingsAdapterManager = settingsAdapterManager;
        settingsAdapterManager.setOnManagerConnChangedListener(new OnManagerConnChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.1
            public void onBinderStateChanged(boolean binderState) {
                SettingsManagerImpl.this.initAdapterManager(binderState);
            }
        });
        this.mSettingsAdapterManager.connect();
    }

    public static SettingsManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SettingsManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new SettingsManagerImpl(context);
                }
            }
        }
        return mInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initAdapterManager(boolean bindState) {
        LogUtil.debug(TAG, "bind state is " + bindState);
        if (bindState) {
            this.mPropertySettingsManager = (PropertySettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("property");
            KeySettingsManager keySettingsManager = (KeySettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("key");
            this.mKeySettingsManager = keySettingsManager;
            keySettingsManager.subscribeKeyCallback(this.mOnKeyListener);
            MicSettingsManager micSettingsManager = (MicSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("mic");
            this.mMicSettingsManager = micSettingsManager;
            micSettingsManager.subscribeMicCallback(this.mOnMicChangedListener);
            this.mAppBootSettingsManager = (AppBootSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("appBoot");
            ClusterSettingsManager clusterSettingsManager = (ClusterSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("cluster");
            this.mClusterSettingsManager = clusterSettingsManager;
            clusterSettingsManager.subscribeClusterCallback(this.mOnClusterChangedListener);
            CartipsSettingsManager cartipsSettingsManager = (CartipsSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("cartips");
            this.mCartipsSettingsManager = cartipsSettingsManager;
            cartipsSettingsManager.subscribeCartipsCallback(this.mOnCartipsChangedListener);
            ThemeSettingsManager themeSettingsManager = (ThemeSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("theme");
            this.mThemeSettingsManager = themeSettingsManager;
            themeSettingsManager.subscribeThemeCallback(this.mOnThemeChangedListener);
            this.mVersionSettingsManager = (VersionSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("version");
            LanguageSettingsManager languageSettingsManager = (LanguageSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("language");
            this.mLanguageSettingsManager = languageSettingsManager;
            languageSettingsManager.subscribeLanguageCallback(this.mOnLanguageChangedListener);
            PanoramaSettingsManager panoramaSettingsManager = (PanoramaSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("panorama");
            this.mPanoramaSettingsManager = panoramaSettingsManager;
            panoramaSettingsManager.subscribePanoramaCallback(this.mOnPanoramaChangedListener);
            this.mContainerSettingManager = (ContainerSettingManager) this.mSettingsAdapterManager.getSettingsAdapterManager("container");
            LocationSettingsManager locationSettingsManager = (LocationSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("location");
            this.mLocationSettingsManager = locationSettingsManager;
            locationSettingsManager.subscribeLocationCallback(this.mOnLocationChangedListener);
            DmsSettingsManager dmsSettingsManager = (DmsSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager("dms");
            this.mDmsSettingsManager = dmsSettingsManager;
            dmsSettingsManager.subscribeDmsCallback(this.mOnDmsChangedListener);
            AppConfigSettingsManger appConfigSettingsManger = (AppConfigSettingsManger) this.mSettingsAdapterManager.getSettingsAdapterManager("appconfig");
            this.mAppConfigSettingsManger = appConfigSettingsManger;
            appConfigSettingsManger.subscribeAppConfigCallback(this.mOnAppConfigChangedListener);
            this.mAppConfigSettingsManger.subscribeCarTypeCallback(this.mOnCarTypeChangedListener);
            return;
        }
        this.mPropertySettingsManager = null;
        this.mKeySettingsManager = null;
        this.mMicSettingsManager = null;
        this.mAppBootSettingsManager = null;
        this.mClusterSettingsManager = null;
        this.mThemeSettingsManager = null;
        this.mVersionSettingsManager = null;
        this.mLanguageSettingsManager = null;
        this.mPanoramaSettingsManager = null;
        this.mContainerSettingManager = null;
        this.mLocationSettingsManager = null;
        this.mCartipsSettingsManager = null;
        this.mDmsSettingsManager = null;
        this.mAppConfigSettingsManger = null;
    }

    public void setOnKeyListener(OnKeyListener onKeyListener) {
        synchronized (this.mOnKeyListeners) {
            if (!this.mOnKeyListeners.contains(onKeyListener) && onKeyListener != null) {
                this.mOnKeyListeners.add(onKeyListener);
                LogUtil.debug(TAG, "setOnKeyListener ok! listener size: " + this.mOnKeyListeners.size());
            }
        }
    }

    public void setOnMicChangedListener(OnMicChangedListener onMicChangedListener) {
        synchronized (this.mOnMicChangedListeners) {
            if (!this.mOnMicChangedListeners.contains(onMicChangedListener) && onMicChangedListener != null) {
                this.mOnMicChangedListeners.add(onMicChangedListener);
                LogUtil.debug(TAG, "setOnMicChangedListener ok! listener size: " + this.mOnMicChangedListeners.size());
            }
        }
    }

    public void setOnClusterSettingChangedListener(OnClusterChangedListener onClusterChangedListener) {
        synchronized (this.mOnClusterChangedListeners) {
            if (!this.mOnClusterChangedListeners.contains(onClusterChangedListener) && onClusterChangedListener != null) {
                this.mOnClusterChangedListeners.add(onClusterChangedListener);
                LogUtil.debug(TAG, "setOnClusterChangedListener ok! listener size: " + this.mOnClusterChangedListeners.size());
            }
        }
    }

    public void setOnThemeChangedListener(OnThemeChangedListener onThemeChangedListener) {
        synchronized (this.mOnThemeChangedListeners) {
            if (!this.mOnThemeChangedListeners.contains(onThemeChangedListener) && onThemeChangedListener != null) {
                this.mOnThemeChangedListeners.add(onThemeChangedListener);
                LogUtil.debug(TAG, "setOnThemeChangedListener ok! listener size: " + this.mOnThemeChangedListeners.size());
            }
        }
    }

    public void setOnLanguageChangedListener(OnLanguageChangedListener onLanguageChangedListener) {
        synchronized (this.mOnLanguageChangedListeners) {
            if (!this.mOnLanguageChangedListeners.contains(onLanguageChangedListener) && onLanguageChangedListener != null) {
                this.mOnLanguageChangedListeners.add(onLanguageChangedListener);
                LogUtil.debug(TAG, "setOnLanguageChangedListener ok! listener size: " + this.mOnLanguageChangedListeners.size());
            }
        }
    }

    public void setOnPanoramaChangedListener(OnPanoramaChangedListener onPanoramaChangedListener) {
        synchronized (this.mOnPanoramaChangedListeners) {
            if (!this.mOnPanoramaChangedListeners.contains(onPanoramaChangedListener) && onPanoramaChangedListener != null) {
                this.mOnPanoramaChangedListeners.add(onPanoramaChangedListener);
                LogUtil.debug(TAG, "setOnPanoramaChangedListener ok! listener size: " + this.mOnPanoramaChangedListeners.size());
            }
        }
    }

    public void setLocationChangedListener(OnLocationChangedListener onLocationChangedListener) {
        synchronized (this.mOnLocationChangedListeners) {
            if (!this.mOnLocationChangedListeners.contains(onLocationChangedListener) && onLocationChangedListener != null) {
                this.mOnLocationChangedListeners.add(onLocationChangedListener);
                LogUtil.debug(TAG, "setLocationChangedListener ok! listener size: " + this.mOnLocationChangedListeners.size());
            }
        }
    }

    public void setCartipsChangedListener(OnCartipsChangedListener onCartipsChangedListener) {
        synchronized (this.mOnCartipsChangedListeners) {
            if (!this.mOnCartipsChangedListeners.contains(onCartipsChangedListener) && onCartipsChangedListener != null) {
                this.mOnCartipsChangedListeners.add(onCartipsChangedListener);
                LogUtil.debug(TAG, "setCartipsChangedListener ok! listener size: " + this.mOnLocationChangedListeners.size());
            }
        }
    }

    public void setDmsChangedListenerListener(OnDmsChangedListener onDmsChangedListener) {
        synchronized (this.mOnDmsChangedListeners) {
            if (!this.mOnDmsChangedListeners.contains(onDmsChangedListener) && onDmsChangedListener != null) {
                this.mOnDmsChangedListeners.add(onDmsChangedListener);
                LogUtil.debug(TAG, "setDmsChangedListenerListener ok! listener size: " + this.mOnDmsChangedListeners.size());
            }
        }
    }

    public void setAppConfigChangedListener(OnAppConfigChangedListener onAppConfigChangedListener) {
        synchronized (this.mOnAppConfigChangedListeners) {
            if (!this.mOnAppConfigChangedListeners.contains(onAppConfigChangedListener) && onAppConfigChangedListener != null) {
                this.mOnAppConfigChangedListeners.add(onAppConfigChangedListener);
                LogUtil.debug(TAG, "setAppConfigChangedListener ok! listener size: " + this.mOnAppConfigChangedListeners.size());
            }
        }
    }

    public void setCarTypeChangedListener(OnCarTypeChangedListener onCarTypeChangedListener) {
        synchronized (this.mOnCarTypeChangedListeners) {
            if (!this.mOnCarTypeChangedListeners.contains(onCarTypeChangedListener) && onCarTypeChangedListener != null) {
                this.mOnCarTypeChangedListeners.add(onCarTypeChangedListener);
                LogUtil.debug(TAG, "setCarTypeChangedListener ok! listener size: " + this.mOnCarTypeChangedListeners.size());
            }
        }
    }

    public PropertySettingsManager getPropertySettingsManager() {
        LogUtil.debug(TAG, "getPropertySettingsManager");
        return this.mPropertySettingsManager;
    }

    public KeySettingsManager getKeySettingsManager() {
        return this.mKeySettingsManager;
    }

    public MicSettingsManager getMicSettingsManager() {
        return this.mMicSettingsManager;
    }

    public AppBootSettingsManager getAppBootSettingsManager() {
        return this.mAppBootSettingsManager;
    }

    public ClusterSettingsManager getClusterSettingsManager() {
        return this.mClusterSettingsManager;
    }

    public ThemeSettingsManager getThemeSettingsManager() {
        return this.mThemeSettingsManager;
    }

    public VersionSettingsManager getVersionSettingsManager() {
        return this.mVersionSettingsManager;
    }

    public LanguageSettingsManager getLanguageSettingsManager() {
        return this.mLanguageSettingsManager;
    }

    public PanoramaSettingsManager getPanoramaSettingsManager() {
        return this.mPanoramaSettingsManager;
    }

    public ContainerSettingManager getContainerSettingManager() {
        return this.mContainerSettingManager;
    }

    public LocationSettingsManager getLocationSettingManager() {
        return this.mLocationSettingsManager;
    }

    public CartipsSettingsManager getCartipsSettingsManager() {
        return this.mCartipsSettingsManager;
    }

    public DmsSettingsManager getDmsSettingsManager() {
        return this.mDmsSettingsManager;
    }

    public AppConfigSettingsManger getAppConfigSettingsManger() {
        return this.mAppConfigSettingsManger;
    }
}
