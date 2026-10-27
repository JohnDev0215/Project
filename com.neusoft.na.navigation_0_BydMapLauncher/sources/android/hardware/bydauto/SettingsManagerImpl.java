package android.hardware.bydauto;

import android.car.Car;
import android.content.Context;
import com.neusoft.na.ocservice.MotsBl;
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
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsManagerImpl {
    private static final String TAG = "SettingsManagerImpl";
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
        public void onIdlerWheel(int i) {
            synchronized (SettingsManagerImpl.this.mOnKeyListeners) {
                Iterator it = SettingsManagerImpl.this.mOnKeyListeners.iterator();
                while (it.hasNext()) {
                    ((OnKeyListener) it.next()).onIdlerWheel(i);
                }
            }
        }

        public void onKeyClick(int i, int i2) {
            synchronized (SettingsManagerImpl.this.mOnKeyListeners) {
                Iterator it = SettingsManagerImpl.this.mOnKeyListeners.iterator();
                while (it.hasNext()) {
                    ((OnKeyListener) it.next()).onKeyClick(i, i2);
                }
            }
        }
    };
    private OnMicChangedListener mOnMicChangedListener = new OnMicChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.3
        public void onMicUseChanged(String str, boolean z) {
            synchronized (SettingsManagerImpl.this.mOnMicChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnMicChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnMicChangedListener) it.next()).onMicUseChanged(str, z);
                }
            }
        }
    };
    private OnClusterChangedListener mOnClusterChangedListener = new OnClusterChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.4
        public void onINSAdasCenterDisplayChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnClusterChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnClusterChangedListener) it.next()).onINSAdasCenterDisplayChanged(i);
                }
            }
        }

        public void onINSCenterDisplay(int i) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnClusterChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnClusterChangedListener) it.next()).onINSCenterDisplay(i);
                }
            }
        }

        public void onDayNightModeChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnClusterChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnClusterChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnClusterChangedListener) it.next()).onDayNightModeChanged(i);
                }
            }
        }
    };
    private OnCartipsChangedListener mOnCartipsChangedListener = new OnCartipsChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.5
        public void onCarTipsSoundTypeChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnCartipsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnCartipsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCartipsChangedListener) it.next()).onCarTipsSoundTypeChanged(i);
                }
            }
        }
    };
    private OnThemeChangedListener mOnThemeChangedListener = new OnThemeChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.6
        public void onThemeChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnThemeChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnThemeChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnThemeChangedListener) it.next()).onThemeChanged(i);
                }
            }
        }
    };
    private OnLanguageChangedListener mOnLanguageChangedListener = new OnLanguageChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.7
        public void onLanguageChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnLanguageChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnLanguageChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnLanguageChangedListener) it.next()).onLanguageChanged(i);
                }
            }
        }
    };
    private OnPanoramaChangedListener mOnPanoramaChangedListener = new OnPanoramaChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.8
        public void onPanoOutputStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoOutputStateChanged(i);
                }
            }
        }

        public void onPanoWorkStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoWorkStateChanged(i);
                }
            }
        }

        public void onPanOutputSignalChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanOutputSignalChanged(i);
                }
            }
        }

        public void onBackLineConfigChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onBackLineConfigChanged(i);
                }
            }
        }

        public void onPanoramaOnlineStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoramaOnlineStateChanged(i);
                }
            }
        }

        public void onRFCameraSwitchStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onRFCameraSwitchStateChanged(i);
                }
            }
        }

        public void onPanoRotationChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoRotationChanged(i);
                }
            }
        }

        public void onLVDSStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onLVDSStateChanged(i);
                }
            }
        }

        public void onDisplayModeChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onDisplayModeChanged(i);
                }
            }
        }

        public void onEmergencyButtonStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onEmergencyButtonStateChanged(i);
                }
            }
        }

        public void onACUStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onACUStateChanged(i);
                }
            }
        }

        public void onCameraUseChange(String str, boolean z) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onCameraUseChange(str, z);
                }
            }
        }

        public void onCarInfoChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onCarInfoChanged(i);
                }
            }
        }

        public void onPanoramaTransparenceChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoramaTransparenceChanged(i);
                }
            }
        }

        public void onPanoAPAStateChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onPanoAPAStateChanged(i);
                }
            }
        }

        public void onCameraStateChanged(int i, int i2) {
            synchronized (SettingsManagerImpl.this.mOnPanoramaChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnPanoramaChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnPanoramaChangedListener) it.next()).onCameraStateChanged(i, i2);
                }
            }
        }
    };
    private OnLocationChangedListener mOnLocationChangedListener = new OnLocationChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.9
        public void onLocUseChange() {
            synchronized (SettingsManagerImpl.this.mOnLocationChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnLocationChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnLocationChangedListener) it.next()).onLocUseChange();
                }
            }
        }
    };
    private OnDmsChangedListener mOnDmsChangedListener = new OnDmsChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.10
        public void onDmsSwitchChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onDmsSwitchChanged(i);
                }
            }
        }

        public void onFatigueSwitchChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onFatigueSwitchChanged(i);
                }
            }
        }

        public void onAttentionSwitchChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onAttentionSwitchChanged(i);
                }
            }
        }

        public void onFatigueDetectChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onFatigueDetectChanged(i);
                }
            }
        }

        public void onAttentionDetectChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onAttentionDetectChanged(i);
                }
            }
        }

        public void onBlockStatusChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onBlockStatusChanged(i);
                }
            }
        }

        public void onDriverNotResponse() {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onDriverNotResponse();
                }
            }
        }

        public void onRestoreFatigueDetect() {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onRestoreFatigueDetect();
                }
            }
        }

        public void onNotifyDmsServerState(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onNotifyDmsServerState(i);
                }
            }
        }

        public void onDmsModeChanged(int i) {
            synchronized (SettingsManagerImpl.this.mOnDmsChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnDmsChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnDmsChangedListener) it.next()).onDmsModeChanged(i);
                }
            }
        }
    };
    private OnAppConfigChangedListener mOnAppConfigChangedListener = new OnAppConfigChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.11
        public void onChangedConfigArea(int i) {
            synchronized (SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnAppConfigChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAppConfigChangedListener) it.next()).onChangedConfigArea(i);
                }
            }
        }

        public void onChangedCpaa(int i) {
            synchronized (SettingsManagerImpl.this.mOnAppConfigChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnAppConfigChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnAppConfigChangedListener) it.next()).onChangedCpaa(i);
                }
            }
        }
    };
    private OnCarTypeChangedListener mOnCarTypeChangedListener = new OnCarTypeChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.12
        public void onStatusChangeCarType(boolean z) {
            synchronized (SettingsManagerImpl.this.mOnCarTypeChangedListeners) {
                Iterator it = SettingsManagerImpl.this.mOnCarTypeChangedListeners.iterator();
                while (it.hasNext()) {
                    ((OnCarTypeChangedListener) it.next()).onStatusChangeCarType(z);
                }
            }
        }
    };

    private SettingsManagerImpl(Context context) {
        this.mContext = context;
        SettingsAdapterManager settingsAdapterManager = SettingsAdapterManager.getInstance(context);
        this.mSettingsAdapterManager = settingsAdapterManager;
        settingsAdapterManager.setOnManagerConnChangedListener(new OnManagerConnChangedListener() { // from class: android.hardware.bydauto.SettingsManagerImpl.1
            public void onBinderStateChanged(boolean z) {
                SettingsManagerImpl.this.initAdapterManager(z);
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
    public void initAdapterManager(boolean z) {
        LogUtil.debug(TAG, "bind state is " + z);
        if (z) {
            this.mPropertySettingsManager = (PropertySettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager(Car.PROPERTY_SERVICE);
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
            LanguageSettingsManager languageSettingsManager = (LanguageSettingsManager) this.mSettingsAdapterManager.getSettingsAdapterManager(MotsBl.JS_PROPERTY_LOCALE_LANGUAGE);
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
