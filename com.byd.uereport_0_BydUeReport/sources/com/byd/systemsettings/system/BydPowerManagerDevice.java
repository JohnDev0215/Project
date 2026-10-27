package com.byd.systemsettings.system;

import android.annotation.SuppressLint;
import android.car.Car;
import android.content.Context;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.light.AbsBYDAutoLightListener;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.ui.dialog.SystemDialog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import m4.d;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydPowerManagerDevice {
    public static final int ACC_ON = 2;
    public static final int BACKLIGHT_OFF = 2;
    public static final int BACKLIGHT_ON = 1;
    public static final int BS = 5;
    public static final int BS_TREBLE = 21;
    public static final int DEVIALET = 4;
    public static final int DIRAC = 1;
    public static final int DIRAC_EQ_MAX = 10;
    public static final int DIRAC_EQ_MIN = -10;
    public static final int DYNAUDIO = 2;
    public static final int DYNA_EQ_MAX = 9;
    public static final int DYNA_EQ_MIN = -9;
    public static final int EQ_FREQ_BAND_16K = 4;
    public static final int EQ_FREQ_BAND_1K = 2;
    public static final int EQ_FREQ_BAND_500 = 1;
    public static final int EQ_FREQ_BAND_5K = 3;
    public static final int EQ_FREQ_BAND_80 = 0;
    public static final int HARMAN = 3;
    public static final int HARMAN_EQ_MAX = 10;
    public static final int HARMAN_EQ_MIN = -10;
    public static final String KEY_CUSTOM_FREQ_BANDS = "general_custom_freq_bands";
    public static final String KEY_CUSTOM_SOUND_FIELD = "general_custom_sound_field";
    public static final String KEY_SOUND_EFFECT_TYPE = "general_sound_effect_type";
    public static final int MANUFACTOR_NON_BRAND = 0;
    public static final int MSG_INIT_BS_BAND = 4;
    public static final int MSG_INIT_DYNA_BAND = 3;
    public static final int MSG_INIT_DYNA_EFFECT_CUSTOM = 5;
    public static final int MSG_INIT_DYNA_EFFECT_ORIGINAL = 6;
    public static final int MSG_INIT_FOR_STR = 2;
    public static final int MSG_INIT_SOUND_EFFECTS = 1;
    public static final int MSG_INIT_TIME = 1000;
    public static final int MSG_INIT_TIME_10 = 10;
    public static final int MSG_INIT_TIME_100 = 100;
    public static final int NON_BRAND = 0;
    public static final int NON_BRAND_EQ_MEDIAN = 10;
    public static final int POWER_STATE_DEGRADED = 3;
    public static final int POWER_STATE_NORMAL = 10;
    public static final String SEPARATOR_FREQ_BANDS = "#";
    public static final String SEPARATOR_K_V = "_";
    public static final int SOUND_EFFECT_CLASSICAL = 3;
    public static final int SOUND_EFFECT_COUNTRY = 4;
    public static final int SOUND_EFFECT_CUSTOM = 1;
    public static final int SOUND_EFFECT_DANCE = 9;
    public static final int SOUND_EFFECT_ELECTRONIC = 10;
    public static final int SOUND_EFFECT_JAZZ = 2;
    public static final int SOUND_EFFECT_OLDIES = 6;
    public static final int SOUND_EFFECT_OPERA = 5;
    public static final int SOUND_EFFECT_POP = 7;
    public static final int SOUND_EFFECT_ROCK = 8;
    private static final String TAG_B = "BydPowerManagerDevice";
    public static final int TYPE_ACC_DEFAULT = 0;
    public static final int TYPE_ACC_OFF = 2;
    public static final int TYPE_ACC_ON = 1;
    private static Map<Integer, Integer[]> soundEffectMap;
    private int ampConfigValue;
    private BYDAutoBodyworkDevice bodywork;
    private BYDAutoLightDevice carLight;

    @SuppressLint({"HandlerLeak"})
    private final Handler coreHandler;
    private volatile boolean degradedPowerMode;
    private volatile boolean isInited;
    private Context mAppContext;
    private BYDAutoAudioDevice mAudio;
    private BYDCarServiceBase mCarServiceBase;
    private AbsBYDAutoLightListener mLightListener;
    private AbsBYDAutoPowerListener mListener;
    private BYDAutoOtaDevice ota;
    private BYDAutoPowerDevice power;
    private volatile boolean serviceConnected;
    private final IServiceConnectionListener serviceConnectionListener;
    private BYDAutoStudy study;
    public static final int[] DYNA_FREQ_ARR = {17, 18, 19, 20};
    public static final int[] BS_FREQ_ARR = {17, 18, 19, 20, 21};
    public static final int[] HARMAN_FREQ_ARR = {33, 34, 35};
    public static final int[] NON_BRAND_FREQ_ARR = {0, 1, 2, 3, 4};

    public static class Singleton {
        private static final BydPowerManagerDevice instance = new BydPowerManagerDevice();

        public static BydPowerManagerDevice get() {
            return instance;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SoundEffect {
    }

    static {
        HashMap map = new HashMap(9);
        soundEffectMap = map;
        map.put(1, new Integer[]{0, 0, 0, 0, 0});
        soundEffectMap.put(2, new Integer[]{-2, 0, 3, 5, 0});
        soundEffectMap.put(3, new Integer[]{3, 0, 0, 3, 5});
        soundEffectMap.put(4, new Integer[]{-2, 1, 3, 1, 0});
        soundEffectMap.put(5, new Integer[]{4, 2, 0, 2, 1});
        soundEffectMap.put(6, new Integer[]{-3, 0, -2, -1, 3});
        soundEffectMap.put(7, new Integer[]{0, 0, 3, 0, 0});
        soundEffectMap.put(8, new Integer[]{6, 1, 0, 3, 6});
        soundEffectMap.put(9, new Integer[]{4, 2, 6, 2, 1});
        soundEffectMap.put(10, new Integer[]{4, 2, 2, 1, 1});
    }

    private static int equalizerIndexToFreqBand(int i8) {
        int i9 = 1;
        if (i8 != 1) {
            i9 = 2;
            if (i8 != 2) {
                i9 = 3;
                if (i8 != 3) {
                    i9 = 4;
                    if (i8 != 4) {
                        return 0;
                    }
                }
            }
        }
        return i9;
    }

    private Map<Integer, Integer> getCustomFreqBands(Context context) {
        String[] strArrSplit;
        if (context == null) {
            return null;
        }
        String string = Settings.System.getString(context.getContentResolver(), KEY_CUSTOM_FREQ_BANDS);
        a.c(TAG_B, "getCustomFreqBands", "freqBands： " + string);
        HashMap map = new HashMap();
        if (string != null && string.length() > 0 && (strArrSplit = string.split(SEPARATOR_FREQ_BANDS)) != null && strArrSplit.length > 0) {
            for (String str : strArrSplit) {
                String[] strArrSplit2 = str.split(SEPARATOR_K_V);
                if (strArrSplit2 != null && strArrSplit2.length > 1) {
                    map.put(Integer.valueOf(Integer.parseInt(strArrSplit2[0])), Integer.valueOf(Integer.parseInt(strArrSplit2[1])));
                }
            }
        }
        a.c(TAG_B, "getCustomFreqBands", "map： " + map);
        return map;
    }

    private int getEffect(Context context) {
        int i8;
        if (context == null) {
            return 0;
        }
        try {
            i8 = Settings.System.getInt(context.getContentResolver(), KEY_SOUND_EFFECT_TYPE);
        } catch (Settings.SettingNotFoundException e8) {
            e8.printStackTrace();
            i8 = 1;
        }
        a.c(TAG_B, "getEffect", "effect： " + i8);
        return i8;
    }

    private Map<Integer, Integer> getEffectFreqBands(int i8) {
        Integer[] soundEffectFreqBandArr = getSoundEffectFreqBandArr(i8);
        HashMap map = new HashMap();
        if (soundEffectFreqBandArr != null && soundEffectFreqBandArr.length > 0) {
            for (int i9 = 0; i9 < soundEffectFreqBandArr.length; i9++) {
                map.put(Integer.valueOf(equalizerIndexToFreqBand(i9)), soundEffectFreqBandArr[i9]);
            }
        }
        return map;
    }

    private Map<Integer, Integer> getSoundEffectCustomFreqBands(Context context, boolean z7) {
        if (context == null) {
            return null;
        }
        Map<Integer, Integer> customFreqBands = getCustomFreqBands(context);
        a.a(TAG_B, "getSoundEffectCustomFreqBands: " + customFreqBands);
        if (customFreqBands == null || customFreqBands.isEmpty()) {
            customFreqBands = getEffectFreqBands(1);
        }
        return z7 ? transformToCustom(customFreqBands) : customFreqBands;
    }

    private Map<Integer, Integer> getSoundEffectFreqBands(Context context, int i8, boolean z7) {
        int iIndexToEffect = indexToEffect(i8);
        Map<Integer, Integer> effectFreqBands = iIndexToEffect != 1 ? getEffectFreqBands(iIndexToEffect) : getSoundEffectCustomFreqBands(context, false);
        a.a(TAG_B, "getSoundEffectFreqBands", "transform: " + z7 + ", map: " + effectFreqBands);
        return z7 ? transformToCustom(effectFreqBands) : effectFreqBands;
    }

    private static int indexToEffect(int i8) {
        a.a(TAG_B, "indexToEffect, index: " + i8);
        switch (i8) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 8;
            case 8:
                return 9;
            case 9:
                return 10;
            default:
                return 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSoundEffects() {
        BYDAutoStudy bYDAutoStudy = this.study;
        if (bYDAutoStudy != null && getAmpConfig(bYDAutoStudy.getAmplifierConfiguration()) == 0) {
            int effect = getEffect(this.mAppContext);
            setSoundEffect(this.mAppContext, effect - 1);
            a.c(TAG_B, "NON_BRAND initSoundEffects effect : " + effect);
            return;
        }
        BYDAutoStudy bYDAutoStudy2 = this.study;
        if (bYDAutoStudy2 != null && getAmpConfig(bYDAutoStudy2.getAmplifierConfiguration()) == 2) {
            int dynaEffect = getDynaEffect();
            a.c(TAG_B, "DYNAUDIO initSoundEffects curEffectsType : " + dynaEffect);
            if (dynaEffect == 18) {
                this.coreHandler.sendEmptyMessageDelayed(6, 100L);
                return;
            } else {
                setDynaEffect(dynaEffect);
                return;
            }
        }
        BYDAutoStudy bYDAutoStudy3 = this.study;
        if (bYDAutoStudy3 == null || getAmpConfig(bYDAutoStudy3.getAmplifierConfiguration()) != 5) {
            return;
        }
        int dynaEffect2 = getDynaEffect();
        setDynaEffect(dynaEffect2);
        a.c(TAG_B, "BS initSoundEffects curEffectsType : " + dynaEffect2);
        if (dynaEffect2 == 18) {
            this.coreHandler.sendEmptyMessageDelayed(4, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDegradedPowerMode() {
        a.a(TAG_B, "---onDegradedPowerMode---degradedPowerMode:" + this.degradedPowerMode);
        this.degradedPowerMode = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNormalPowerMode() {
        if (this.degradedPowerMode) {
            int navibarModeScreensaver = BydSystemSettings.Singleton.get().getNavibarModeScreensaver(this.mAppContext);
            a.a(TAG_B, "---onNormalPowerMode---mode: " + navibarModeScreensaver);
            if (navibarModeScreensaver == 0) {
                g.a().b(new Runnable() { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.5
                    @Override // java.lang.Runnable
                    public void run() {
                        SystemDialog.finishScreensaver();
                    }
                });
            }
        }
        a.a(TAG_B, "---onNormalPowerMode---");
        this.degradedPowerMode = false;
    }

    private void setSoundEffect(Context context, int i8) {
        Map<Integer, Integer> soundEffectFreqBands = getSoundEffectFreqBands(context, i8, false);
        a.a(TAG_B, "setSoundEffect map: " + soundEffectFreqBands);
        if (soundEffectFreqBands == null || soundEffectFreqBands.size() <= 0) {
            return;
        }
        for (Integer num : soundEffectFreqBands.keySet()) {
            setGeneralEqualizer(0, num.intValue(), soundEffectFreqBands.get(num).intValue());
        }
    }

    private Map<Integer, Integer> transformToCustom(Map<Integer, Integer> map) {
        for (Integer num : map.keySet()) {
            map.put(num, Integer.valueOf(map.get(num).intValue() + 10));
        }
        return map;
    }

    public int getAmpConfig(int i8) {
        int i9 = 2;
        switch (i8) {
            case 0:
            default:
                i9 = 0;
                break;
            case 1:
                i9 = 1;
                break;
            case 2:
            case 3:
            case 6:
            case 8:
            case 10:
                break;
            case 4:
            case 5:
                i9 = 3;
                break;
            case 7:
            case 11:
                i9 = 4;
                break;
            case 9:
            case 12:
            case 13:
                i9 = 5;
                break;
        }
        a.a(TAG_B, "getAmpConfig ampValue : " + i8 + ",config : " + i9);
        return i9;
    }

    public int getDynaEffect() {
        if (this.mAudio == null) {
            this.mAudio = BYDAutoAudioDevice.getInstance(this.mAppContext);
        }
        int soundEffect = this.mAudio.getSoundEffect();
        a.a(TAG_B, "getDynaEffect:" + soundEffect);
        return soundEffect;
    }

    public int getDynaFreqBands(int i8) {
        int equalizer = this.mAudio.getEqualizer(i8);
        a.a(TAG_B, "getDynaFreqBands:" + i8 + ",value:" + equalizer);
        return equalizer;
    }

    public int getGeneralEqualizer(int i8, int i9) {
        a.a(TAG_B, "getGeneralEqualizer", "manufactor: " + i8, "frequencyNum: " + i9);
        return this.mAudio.getGeneralEqualizer(i8, i9);
    }

    public Integer[] getSoundEffectFreqBandArr(int i8) {
        return soundEffectMap.get(Integer.valueOf(i8));
    }

    public void init(Context context) {
        a.a(TAG_B, "init", "---init---isInited: " + this.isInited);
        if (this.isInited || context == null) {
            return;
        }
        this.mAppContext = context.getApplicationContext();
        this.isInited = true;
        if (this.ota == null) {
            this.ota = BYDAutoOtaDevice.getInstance(context.getApplicationContext());
        }
        if (this.mCarServiceBase == null) {
            BYDCarServiceBase bYDCarServiceBase = new BYDCarServiceBase(context) { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.2
                @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
                public void onServiceConnected(Car car) {
                    a.a(BydPowerManagerDevice.TAG_B, "init", ", onServiceConnected car:" + car);
                }

                @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
                public void onServiceDied() {
                    a.a(BydPowerManagerDevice.TAG_B, "init", ", onServiceDied:");
                }
            };
            this.mCarServiceBase = bYDCarServiceBase;
            bYDCarServiceBase.registerConnectionListener(this.serviceConnectionListener);
        }
        if (this.bodywork == null) {
            this.bodywork = BYDAutoBodyworkDevice.getInstance(context.getApplicationContext());
        }
        if (this.mListener == null) {
            this.mListener = new AbsBYDAutoPowerListener() { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.3
                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener, b.d
                public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
                    super.isConnected(z7);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener, b.d
                public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
                    super.isOptimizationApi(z7);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onBatteryLowVoltageStateChanged(int i8) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onBatteryLowVoltageStateChanged---state: " + i8);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onBatteryRemainPowerEVChanged(double d8) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onBatteryRemainPowerEVChanged---value: " + d8);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onDataEventChanged(int i8, BYDAutoEventValue bYDAutoEventValue) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onDataEventChanged---eventType: " + i8 + ", eventValue: " + bYDAutoEventValue);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onPowerCtlStatusChanged(int i8, int i9) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onPowerCtlStatusChanged---event_type: " + i8 + ", value: " + i9);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onPowerLevelChanged(int i8) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onPowerLevelChanged---level: " + i8);
                    if (i8 != 2 || BydPowerManagerDevice.this.coreHandler == null || BydPowerManagerDevice.this.coreHandler.hasMessages(2)) {
                        return;
                    }
                    BydPowerManagerDevice.this.coreHandler.sendEmptyMessage(2);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onPowerSTRChanged(int i8) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onPowerSTRChanged---state: " + i8);
                    if (i8 == 3) {
                        BydPowerManagerDevice.this.onDegradedPowerMode();
                        return;
                    }
                    if (i8 == 10) {
                        BydPowerManagerDevice.this.onNormalPowerMode();
                        if (BydPowerManagerDevice.this.coreHandler == null || BydPowerManagerDevice.this.coreHandler.hasMessages(2)) {
                            return;
                        }
                        BydPowerManagerDevice.this.coreHandler.sendEmptyMessage(2);
                    }
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onShutdownInfoChanged(int i8, int i9) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onShutdownInfoChanged---device: " + i8 + ", info: " + i9);
                }

                @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                public void onTftBacklightChanged(int i8) {
                    a.c(BydPowerManagerDevice.TAG_B, "init", "---onTftBacklightChanged---value: " + i8);
                }
            };
        }
        BYDAutoPowerDevice bYDAutoPowerDevice = BYDAutoPowerDevice.getInstance(context.getApplicationContext());
        this.power = bYDAutoPowerDevice;
        if (bYDAutoPowerDevice != null) {
            bYDAutoPowerDevice.registerListener(this.mListener);
        }
        if (this.mLightListener == null) {
            this.mLightListener = new AbsBYDAutoLightListener() { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.4
                @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener, b.d
                public /* bridge */ /* synthetic */ void isConnected(boolean z7) {
                    super.isConnected(z7);
                }

                @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener, b.d
                public /* bridge */ /* synthetic */ void isOptimizationApi(boolean z7) {
                    super.isOptimizationApi(z7);
                }

                @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
                public void onLightOff(int i8) {
                    a.a(BydPowerManagerDevice.TAG_B, "init", "---onLightOff---type: " + i8);
                }

                @Override // android.hardware.bydauto.light.AbsBYDAutoLightListener
                public void onLightOn(int i8) {
                    a.a(BydPowerManagerDevice.TAG_B, "init", "---onLightOn---type: " + i8);
                }
            };
        }
        BYDAutoLightDevice bYDAutoLightDevice = BYDAutoLightDevice.getInstance(context.getApplicationContext());
        this.carLight = bYDAutoLightDevice;
        if (bYDAutoLightDevice != null) {
            bYDAutoLightDevice.registerListener(this.mLightListener);
        }
        this.mAudio = BYDAutoAudioDevice.getInstance(context.getApplicationContext());
        BYDAutoStudy bYDAutoStudy = BYDAutoStudy.getInstance(context.getApplicationContext());
        this.study = bYDAutoStudy;
        this.ampConfigValue = bYDAutoStudy.getAmplifierConfiguration();
        a.a(TAG_B, "init", "---getAmplifierConfiguration---ampConfigValue: " + this.ampConfigValue);
        Handler handler = this.coreHandler;
        if (handler != null) {
            handler.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    public void release() {
        BYDAutoPowerDevice bYDAutoPowerDevice;
        a.a(TAG_B, "---release---");
        AbsBYDAutoPowerListener absBYDAutoPowerListener = this.mListener;
        if (absBYDAutoPowerListener != null && (bYDAutoPowerDevice = this.power) != null) {
            bYDAutoPowerDevice.unregisterListener(absBYDAutoPowerListener);
        }
        BYDCarServiceBase bYDCarServiceBase = this.mCarServiceBase;
        if (bYDCarServiceBase != null) {
            bYDCarServiceBase.unregisterConnectionListener(this.serviceConnectionListener);
        }
        this.isInited = false;
        this.mListener = null;
        this.power = null;
        this.ota = null;
    }

    public void setBacklightState(boolean z7) {
        if (this.bodywork != null && this.ota != null && this.serviceConnected) {
            int powerLevel = this.bodywork.getPowerLevel();
            a.a(TAG_B, "---setBacklightState---powerLevel: " + powerLevel + ", on: " + z7);
            if (powerLevel == 0) {
                return;
            }
            this.ota.setBackLightState(z7 ? 1 : 2, 1);
            return;
        }
        a.c(TAG_B, "---setBacklightState---on: " + z7 + ", serviceConnected: " + this.serviceConnected);
        a.c(TAG_B, "---setBacklightState---bodywork: " + this.bodywork + ", ota: " + this.ota);
    }

    public int setDynaEffect(int i8) {
        a.a(TAG_B, "--setDynaEffect--effect = " + i8);
        return this.mAudio.setSoundEffect(i8);
    }

    public int setDynaEqualizer(int i8, int i9) {
        a.a(TAG_B, "--setDynaEqualizer--freq: " + d.g(i8) + ", value: " + i9);
        if (i8 < 17) {
            return -2147482645;
        }
        if (getAmpConfig(this.ampConfigValue) == 2 && i8 > 20) {
            return -2147482645;
        }
        if (getAmpConfig(this.ampConfigValue) == 5 && i8 > 21) {
            return -2147482645;
        }
        if (i9 > 9) {
            i9 = 9;
        }
        if (i9 < -9) {
            i9 = -9;
        }
        a.a(TAG_B, "--setDynaEqualizer--value: " + i9);
        return this.mAudio.setEqualizer(i8, i9);
    }

    public int setGeneralEqualizer(int i8, int i9, int i10) {
        a.a(TAG_B, "setGeneralEqualizer", "manufactor: " + i8, "frequencyNum: " + i9 + ", value: " + i10);
        return this.mAudio.setGeneralEqualizer(i8, i9, i10);
    }

    private BydPowerManagerDevice() {
        this.isInited = false;
        this.serviceConnected = false;
        this.degradedPowerMode = false;
        this.ampConfigValue = 0;
        this.serviceConnectionListener = new IServiceConnectionListener() { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.1
            @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
            public void onServiceConnected(boolean z7) {
                a.a(BydPowerManagerDevice.TAG_B, "Listener, onServiceConnected:" + z7);
                BydPowerManagerDevice.this.serviceConnected = true;
            }

            @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
            public void onServiceDisconnected(boolean z7) {
                a.a(BydPowerManagerDevice.TAG_B, "onServiceDisconnected:" + z7);
                BydPowerManagerDevice.this.serviceConnected = false;
            }
        };
        this.coreHandler = new Handler() { // from class: com.byd.systemsettings.system.BydPowerManagerDevice.6
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                    case 2:
                        BydPowerManagerDevice.this.initSoundEffects();
                        a.c(BydPowerManagerDevice.TAG_B, "---handleMessage---msg: " + message);
                        break;
                    case 3:
                        BydPowerManagerDevice bydPowerManagerDevice = BydPowerManagerDevice.this;
                        bydPowerManagerDevice.setDynaEqualizer(17, bydPowerManagerDevice.getDynaFreqBands(17));
                        BydPowerManagerDevice bydPowerManagerDevice2 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice2.setDynaEqualizer(18, bydPowerManagerDevice2.getDynaFreqBands(18));
                        BydPowerManagerDevice bydPowerManagerDevice3 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice3.setDynaEqualizer(19, bydPowerManagerDevice3.getDynaFreqBands(19));
                        BydPowerManagerDevice bydPowerManagerDevice4 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice4.setDynaEqualizer(20, bydPowerManagerDevice4.getDynaFreqBands(20));
                        break;
                    case 4:
                        BydPowerManagerDevice bydPowerManagerDevice5 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice5.setDynaEqualizer(17, bydPowerManagerDevice5.getDynaFreqBands(17));
                        BydPowerManagerDevice bydPowerManagerDevice6 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice6.setDynaEqualizer(18, bydPowerManagerDevice6.getDynaFreqBands(18));
                        BydPowerManagerDevice bydPowerManagerDevice7 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice7.setDynaEqualizer(19, bydPowerManagerDevice7.getDynaFreqBands(19));
                        BydPowerManagerDevice bydPowerManagerDevice8 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice8.setDynaEqualizer(20, bydPowerManagerDevice8.getDynaFreqBands(20));
                        BydPowerManagerDevice bydPowerManagerDevice9 = BydPowerManagerDevice.this;
                        bydPowerManagerDevice9.setDynaEqualizer(21, bydPowerManagerDevice9.getDynaFreqBands(21));
                        break;
                    case 5:
                        BydPowerManagerDevice.this.setDynaEffect(18);
                        BydPowerManagerDevice.this.coreHandler.sendEmptyMessageDelayed(3, 10L);
                        break;
                    case 6:
                        BydPowerManagerDevice.this.setDynaEffect(19);
                        BydPowerManagerDevice.this.coreHandler.sendEmptyMessageDelayed(5, 10L);
                        break;
                }
            }
        };
    }
}
