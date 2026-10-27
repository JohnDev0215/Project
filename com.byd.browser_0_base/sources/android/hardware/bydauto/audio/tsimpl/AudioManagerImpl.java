package android.hardware.bydauto.audio.tsimpl;

import android.annotation.TargetApi;
import android.car.Car;
import android.car.media.CarAudioControl;
import android.car.media.CarAudioManager;
import android.car.media.CarEnhancedAudioManager;
import android.car.media.CarEqualizer;
import android.content.Context;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioFocusInfo;
import android.util.Log;
import defpackage.C0175b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class AudioManagerImpl extends BYDCarServiceBase {
    public static final int AUDIO_PM_DEGRADED = 3;
    public static final int AUDIO_PM_DISPLAY_ON = 10;
    public static final int AUDIO_PM_HALF_HOUR_MODE = 11;
    public static final int AUDIO_PM_OFF = 0;
    public static final int AUDIO_PM_PRE_STARTUP = 1;
    public static final int AUDIO_PM_REFLASH = 6;
    public static final int AUDIO_PM_REMOTE_FOTA = 7;
    public static final int AUDIO_PM_SLEEP = 8;
    public static final int AUDIO_PM_STANDBY = 4;
    public static final int AUDIO_PM_STARTUP = 2;
    public static final int AUDIO_PM_STR = 5;
    public static final int AUDIO_PM_STR_SUSPENDING = 9;
    public static final int AUDIO_PM_TOD = 12;
    public static final int BALANCE_MAX = 9;
    public static final int BALANCE_MIN = -9;
    public static final int BTCALLMUTE_DISNABLE = 0;
    public static final int BTCALLMUTE_ENABLE = 1;
    private static final ArrayList<AbsBYDAutoAudioListener> BYD_AUTO_AUDIO_LISTENER = new ArrayList<>();
    public static final int CALL_DEMUTE = 1;
    public static final int CALL_MUTE = 0;
    public static final int CARTIPS_VOICE_STATE_DEFAULT = 0;
    public static final int CARTIPS_VOICE_STATE_MAX = 3;
    public static final int CARTIPS_VOICE_STATE_MIN = 0;
    public static final float CHIMES_GAIN_DEFAULT = 0.0f;
    public static final float CHIMES_GAIN_MAX = 40.0f;
    public static final float CHIMES_GAIN_MIN = -40.0f;
    private static final int CONVERT_BALANCE_FADE_STEP = 100;
    private static final int CONVERT_BMT_STEP = 100;
    public static final int DYNA_ALL_INDEX = 0;
    public static final int DYNA_ALTO_INDEX = 2;
    public static final int DYNA_BACK_ROW_INDEX = 3;
    public static final int DYNA_BASS_INDEX = 1;
    public static final int DYNA_CO_DRIVER_INDEX = 2;
    public static final int DYNA_CUSTOM = 0;
    public static final int DYNA_CUSTOM_INDEX = 5;
    public static final int DYNA_DEVICE_CONFIG_INDEX = 0;
    public static final int DYNA_DEVICE_INDEX = 0;
    public static final int DYNA_MAIN_DRIVER_INDEX = 1;
    public static final int DYNA_MEGA_BASS_INDEX = 0;
    public static final int DYNA_MIC = 2;
    public static final int DYNA_ORIGINAL = 1;
    public static final int DYNA_SOFT = 3;
    public static final int DYNA_SPEECH = 4;
    public static final int DYNA_SURROUND_INDEX = 4;
    public static final int DYNA_TREBLE_INDEX = 3;
    public static final int DYNA_VSC_DEFULT_SPEED = 4;
    public static final int ENGINE_SOUND_TYPE_MAX = 6;
    public static final int ENGINE_SOUND_TYPE_MIN = 0;
    public static final int EQUALIZER_VOLUME_MAX = 9;
    public static final int EQUALIZER_VOLUME_MIN = -9;
    public static final int FADE_MAX = 9;
    public static final int FADE_MIN = -9;
    public static final int GROUP_INDEX_MAX = 5;
    public static final int GROUP_INDEX_MIN = 0;
    public static final int INDEX_ADAS_CARTIPS_VOICE_STATE = 25;
    public static final int INDEX_ADAS_GAIN = 28;
    public static final int INDEX_AMP_MODE = 13;
    public static final int INDEX_AVAS_MODE = 11;
    public static final int INDEX_AVAS_STATE = 18;
    public static final int INDEX_CALL_MUTE = 17;
    public static final int INDEX_CHIMES_CARTIPS_VOICE_STATE = 23;
    public static final int INDEX_CHIME_GAIN = 26;
    public static final int INDEX_CP_ALT_ACTIVE = 16;
    public static final int INDEX_DIRAC_LIVE = 2;
    public static final int INDEX_DIRAC_LIVE_LOUDNESS = 10;
    public static final int INDEX_DIRAC_PERFORMANCE = 1;
    public static final int INDEX_DIRAC_PERFORMANCE_LOUDNESS = 9;
    public static final int INDEX_DSP_MIC12_GAIN = 19;
    public static final int INDEX_DSP_MIC34_GAIN = 20;
    public static final int INDEX_DSP_MIC56_GAIN = 21;
    public static final int INDEX_DSP_MIC_REF_GAIN = 22;
    public static final int INDEX_DYNA_CONFIG = 4;
    public static final int INDEX_DYNA_SOUND_FIELD_FOCUS = 3;
    public static final int INDEX_DYNA_VSC = 5;
    public static final int INDEX_INTELLIGENT_BROADCAST = 7;
    public static final int INDEX_KTV_MODE = 12;
    public static final int INDEX_MIC_MUTE_TYPE = 14;
    public static final int INDEX_NAV_MIX_MEDIA = 6;
    public static final int INDEX_POWER_MUTE_TYPE = 15;
    public static final int INDEX_RADAR_CARTIPS_VOICE_STATE = 24;
    public static final int INDEX_RADAR_GAIN = 27;
    public static final int INDEX_SYSTEM_PROMPT_SOUND_CATEGORY = 8;
    public static final int INDEX_VSC_MODE = 0;
    public static final int KTV_STATE_DISABLE = 0;
    public static final int KTV_STATE_ENABLE = 1;
    public static final int LOUDNESS_TYPE_MAX = 8;
    public static final int LOUDNESS_TYPE_MIN = 0;
    private static final int MAX_CREATE_CAR_MS = 200;
    public static final int MEDIA_VOLUME_MAX = 39;
    public static final int MEDIA_VOLUME_MIN = 0;
    private static final int MESSAGE_LISTENER_ADDED = 3;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CONNECTED = 1;
    private static final int MESSAGE_SERVICE_CREATE_CAR = 4;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    public static final int MIC_GAIN_MAX = 32;
    public static final int MIC_GAIN_MIN = -32;
    public static final int MIC_MUTE_DISABLE = 0;
    public static final int MIC_MUTE_ENABLE = 1;
    public static final int NAV_VOLUME_MAX = 10;
    public static final int NAV_VOLUME_MIN = 0;
    public static final int PHONE_VOLUME_MAX = 39;
    public static final int PHONE_VOLUME_MIN = 5;
    public static final int POWER_MUTE_STATE_MAX = 12;
    public static final int POWER_MUTE_STATE_MIN = 0;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final String TAG = "AudioManagerImpl_0.45";
    public static final int USAGE_INDEX_MAX = 36;
    public static final int USAGE_INDEX_MIN = 0;
    public static final int VOICE_VOLUME_MAX = 24;
    public static final int VOICE_VOLUME_MIN = 5;
    public static final int VSC_VALUE_MAX = 7;
    public static final int VSC_VALUE_MIN = 0;
    public static final int VSP_STATE_DISABLE = 0;
    public static final int VSP_STATE_ENABLE = 1;
    private static volatile AudioManagerImpl sAudioManagerImpl;
    private int adasVoiceState;
    private float adasVolGain;
    private int chimesVoiceState;
    private float chimesVolGain;
    private int lastAllMuteStatus;
    private String lastMediaPackage;
    private Car mCar;
    private CarAudioControl mCarAudioControl;
    private CarAudioControl.CarAudioControlCallback mCarAudioControlCallback;
    private CarAudioManager mCarAudioManager;
    private CarEnhancedAudioManager.CarBalanceCallback mCarBalanceCallback;
    private CarEnhancedAudioManager mCarEnhancedAudioManager;
    private CarEqualizer mCarEqualizer;
    private CarEqualizer.CarEqualizerCallback mCarEqualizerCallback;
    private CarEnhancedAudioManager.CarFocusCallback mCarFocusCallback;
    private CarEnhancedAudioManager.CarGroupMutingCallback mCarGroupMutingCallback;
    private CarAudioManager.CarVolumeCallback mCarVolumeCallback;
    private Context mContext;
    private boolean mIgnoreBydPermission;
    private boolean mIsConnected;
    private TsManagerImpl mTsManagerImpl;
    private int radarVoiceState;
    private float radarVolGain;

    @TargetApi(24)
    public AudioManagerImpl(Context context) {
        super(context);
        this.mIgnoreBydPermission = false;
        this.lastAllMuteStatus = 0;
        this.chimesVoiceState = 0;
        this.radarVoiceState = 0;
        this.adasVoiceState = 0;
        this.chimesVolGain = 0.0f;
        this.radarVolGain = 0.0f;
        this.adasVolGain = 0.0f;
        this.mCarVolumeCallback = new CarAudioManager.CarVolumeCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int i, int i2, int i3) {
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioVolumeChanged((AbsBYDAutoAudioListener) it.next(), i2, i, i3);
                }
            }
        };
        this.mCarBalanceCallback = new CarEnhancedAudioManager.CarBalanceCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.2
            public void onBalanceTowardRightLevelChanged() {
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                int i = balanceFadePosition[0];
                int i2 = balanceFadePosition[1];
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }

            public void onFadeTowardFrontLevelChanged() {
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                int i = balanceFadePosition[0];
                int i2 = balanceFadePosition[1];
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }
        };
        this.mCarGroupMutingCallback = new CarEnhancedAudioManager.CarGroupMutingCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.3
            public void onGroupMuteChanged(int i, int i2, int i3) {
                int allMute = AudioManagerImpl.this.getAllMute();
                StringBuilder sbM = C0175b.m("audio all mute state is ", allMute, ", lastAllMuteStatus is ");
                sbM.append(AudioManagerImpl.this.lastAllMuteStatus);
                Log.w(AudioManagerImpl.TAG, sbM.toString());
                if (allMute != AudioManagerImpl.this.lastAllMuteStatus) {
                    Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoAudioListener) it.next()).onAllMuteState(allMute);
                    }
                    AudioManagerImpl.this.lastAllMuteStatus = allMute;
                }
                if (i2 == AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i, 1)) {
                    Log.w(AudioManagerImpl.TAG, "audio Media mute state is " + AudioManagerImpl.this.getMuteState());
                    Iterator it2 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it2.hasNext()) {
                        ((AbsBYDAutoAudioListener) it2.next()).onMuteStateChanged(AudioManagerImpl.this.getMuteState());
                    }
                    return;
                }
                if (i2 == AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i, 12)) {
                    Log.w(AudioManagerImpl.TAG, "audio Nav mute state is " + AudioManagerImpl.this.getNaviMuteState());
                    Iterator it3 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it3.hasNext()) {
                        ((AbsBYDAutoAudioListener) it3.next()).onNaviMuteStateChanged(AudioManagerImpl.this.getNaviMuteState());
                    }
                }
            }
        };
        this.mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.4
            public void onFocusChanged(int i, List<AudioFocusInfo> list) {
                list.toString();
                for (AudioFocusInfo audioFocusInfo : list) {
                    audioFocusInfo.getAttributes().getUsage();
                    int usage = audioFocusInfo.getAttributes().getUsage();
                    if (usage == 0 || usage == 1 || usage == 14 || usage == 24 || usage == 33) {
                        AudioManagerImpl.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    }
                }
                String unused = AudioManagerImpl.this.lastMediaPackage;
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onAudioFocusChanged(i, list);
                }
            }
        };
        this.mCarEqualizerCallback = new CarEqualizer.CarEqualizerCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.5
            public void onBandLevelChanged(int i) {
                if (i == 0) {
                    i = 17;
                } else if (i == 1) {
                    i = 18;
                } else if (i == 2) {
                    i = 19;
                } else if (i == 3) {
                    i = 20;
                }
                int equalizer = AudioManagerImpl.this.getEqualizer(i);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onEqualizerChanged(i, equalizer);
                }
            }

            public void onPresetChanged() {
                int soundEffect = AudioManagerImpl.this.getSoundEffect();
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundEffectChanged(soundEffect);
                }
            }
        };
        this.mCarAudioControlCallback = new CarAudioControl.CarAudioControlCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.6
            public void onAudioControlChanged(int i) {
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioControlChanged((AbsBYDAutoAudioListener) it.next(), i);
                }
            }
        };
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    private int convertDb(int i, int i2) {
        return i / i2;
    }

    private int convertMb(int i, int i2) {
        return i * i2;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(C0175b.j("requires permission ", str));
        }
    }

    public static AudioManagerImpl getInstance(Context context) {
        if (sAudioManagerImpl == null) {
            synchronized (AudioManagerImpl.class) {
                if (sAudioManagerImpl == null) {
                    sAudioManagerImpl = new AudioManagerImpl(context);
                }
            }
        }
        return sAudioManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioControlChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i) {
        if (i == 3) {
            absBYDAutoAudioListener.onSoundFieldPositionChanged(getSoundFieldPositionType());
        }
        if (i == 18) {
            absBYDAutoAudioListener.onVSPStateChanged(this.mCarAudioControl.getIntegerValue(18, 0));
            return;
        }
        if (i == 5) {
            absBYDAutoAudioListener.onGearSpeedChanged(sAudioManagerImpl.getAudioDeviceManufacturer(), this.mCarAudioControl.getIntegerValue(5, 4));
            return;
        }
        if (i == 6) {
            absBYDAutoAudioListener.onNavigationMediaStateChanged(this.mCarAudioControl.getIntegerValue(6, 1));
            return;
        }
        if (i == 10) {
            absBYDAutoAudioListener.onLudnessTypeChange(this.mCarAudioControl.getIntegerValue(10, 0));
            return;
        }
        if (i == 11) {
            absBYDAutoAudioListener.onEngineSoundTypeChanged(this.mCarAudioControl.getIntegerValue(11, 2));
            return;
        }
        if (i == 14) {
            absBYDAutoAudioListener.onBtCall_MicMuteChanged(this.mCarAudioControl.getIntegerValue(14, 0));
            return;
        }
        if (i == 15) {
            absBYDAutoAudioListener.onPowerMuteStateChange(this.mCarAudioControl.getIntegerValue(15, 0));
            return;
        }
        switch (i) {
            case 23:
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(0, this.chimesVoiceState);
                break;
            case 24:
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(1, this.radarVoiceState);
                break;
            case 25:
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(2, this.adasVoiceState);
                break;
            case 26:
                absBYDAutoAudioListener.onChimesVolGainChanged(0, this.chimesVolGain);
                break;
            case 27:
                absBYDAutoAudioListener.onChimesVolGainChanged(1, this.radarVolGain);
                break;
            case 28:
                absBYDAutoAudioListener.onChimesVolGainChanged(2, this.adasVolGain);
                break;
            default:
                Log.e(TAG, "notifyAudioControlChanged index is invalid");
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serviceConnected(Car car) {
        if (this.mIsConnected) {
            return;
        }
        this.mIsConnected = true;
        this.mCar = car;
        Objects.toString(car);
        CarAudioManager carAudioManager = (CarAudioManager) this.mCar.getCarManager("audio");
        this.mCarAudioManager = carAudioManager;
        Objects.toString(carAudioManager);
        CarEnhancedAudioManager carEnhancedAudioManager = (CarEnhancedAudioManager) this.mCar.getCarManager("enhanced_audio");
        this.mCarEnhancedAudioManager = carEnhancedAudioManager;
        Objects.toString(carEnhancedAudioManager);
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return;
        }
        this.mCarAudioControl = new CarAudioControl(this.mCar, this.mCarEnhancedAudioManager);
        this.mCarEqualizer = new CarEqualizer(this.mCar, this.mCarEnhancedAudioManager);
        Objects.toString(this.mCarAudioControl);
        Objects.toString(this.mCarEqualizer);
        Objects.toString(this.mCarEnhancedAudioManager);
        this.mCarAudioManager.registerCarVolumeCallback(this.mCarVolumeCallback);
        this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, this.mCarAudioManager.getAudioZoneIds().stream().mapToInt(new ToIntFunction() { // from class: n9
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray());
        this.mCarEnhancedAudioManager.registerCarBalanceCallback(this.mCarBalanceCallback);
        this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
        this.mCarEqualizer.registerCarEqualizerCallback(this.mCarEqualizerCallback);
        this.mCarAudioControl.setCarAudioControlCallback(this.mCarAudioControlCallback);
        for (int i = 0; i <= 28; i++) {
            this.mCarAudioControl.checkAndUpdateCarAudioControlCallback(i, true);
        }
        this.lastAllMuteStatus = getAllMute();
    }

    public int getAllMute() {
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        for (int i = 0; i < volumeGroupIdForUsage; i++) {
            if (this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, i)) {
                arrayList.add(1);
            } else {
                arrayList.add(0);
            }
        }
        return arrayList.contains(0) ? 0 : 1;
    }

    public int getAudioDeviceConfig() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        return (carAudioControl == null || carAudioControl.getIntegerValue(4, 0) != 0) ? 0 : 17;
    }

    public int getAudioDeviceManufacturer() {
        return 2;
    }

    public int getAudioMicAmpGain() {
        return 0;
    }

    public int[] getBalanceFadePosition() {
        int[] iArr = new int[2];
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            int balanceTowardRightLevel = carEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeTowardFrontLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            iArr[0] = convertDb(balanceTowardRightLevel, 100);
            iArr[1] = convertDb(fadeTowardFrontLevel, 100);
        }
        return iArr;
    }

    public int getBtCall_MicMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(14, 0);
        }
        return 0;
    }

    public int getCallMuteState() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        carAudioControl.getIntegerValue(17, 0);
        return this.mCarAudioControl.getIntegerValue(17, 0);
    }

    public int getCarTipsVoiceState(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 2) {
            Log.e(TAG, "getCarTipsVoiceState failed");
            return 0;
        }
        if (i == 0) {
            i = 23;
        } else if (i == 1) {
            i = 24;
        } else if (i == 2) {
            i = 25;
        }
        return carAudioControl.getIntegerValue(i, 0);
    }

    public float getChimesVolGain(int i) {
        if (i >= 0 && i <= 2) {
            if (i == 0) {
                return this.chimesVolGain;
            }
            if (i == 1) {
                return this.radarVolGain;
            }
            if (i == 2) {
                return this.adasVolGain;
            }
        }
        Log.e(TAG, "getChimesVolGain failed");
        return 0.0f;
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo(int i) {
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || i != 0) {
            return null;
        }
        try {
            return carEnhancedAudioManager.getAudioFocusHoldersForZoneId(i);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    public int getDevicetype() {
        return 1002;
    }

    public int getDiracLiveMode() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(2, 2);
        }
        return 0;
    }

    public int getDiracLiveState() {
        return 0;
    }

    public int getDiracPerformanceMode() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        return (carAudioControl == null || carAudioControl.getIntegerValue(1, 1) != 1) ? 0 : 1;
    }

    public int getEngineSoundType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(11, 2);
        }
        return 2;
    }

    public int getEqualizer(int i) {
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int i2 = 0;
        if (carEqualizer == null || i < 17 || i > 20) {
            return 0;
        }
        switch (i) {
            case 17:
                break;
            case 18:
                i2 = 1;
                break;
            case 19:
                i2 = 2;
                break;
            case 20:
                i2 = 3;
                break;
            default:
                return 0;
        }
        return convertDb(carEqualizer.getBandLevel(i2), 100);
    }

    public int[] getFeatureList() {
        return new int[0];
    }

    public int getGearBySpeed() {
        if (this.mCarAudioControl == null || sAudioManagerImpl.getAudioDeviceManufacturer() != 2) {
            return 0;
        }
        return this.mCarAudioControl.getIntegerValue(5, 4);
    }

    public String getGetPermission() {
        return BYDAutoAudioDevice.AUDIO_GET_PERM;
    }

    public int getGroupMaxVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null && i >= 0 && i <= 5) {
            return carAudioManager.getGroupMaxVolume(i);
        }
        Log.e(TAG, "set getGroupMaxVolume failed");
        return 0;
    }

    public int getGroupMinVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null && i >= 0 && i <= 5) {
            return carAudioManager.getGroupMinVolume(i);
        }
        Log.e(TAG, "set getGroupMinVolume failed");
        return 0;
    }

    public int getKtvMode() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(12, 0);
        }
        return 0;
    }

    public String getLastMediaPackage() {
        String str = this.lastMediaPackage;
        return str != null ? str : "lastMediaPackage is null";
    }

    public int getLoudness() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(10, 0);
        }
        return 0;
    }

    public int getLoudnessState() {
        return 0;
    }

    public int getLoudspeakerNum() {
        return 0;
    }

    public int getMusicChangeSource() {
        return 1;
    }

    public int getMuteState() {
        if (this.mCarEnhancedAudioManager == null) {
            return 0;
        }
        return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1)) ? 1 : 0;
    }

    public int getNaviMuteState() {
        if (this.mCarEnhancedAudioManager != null) {
            return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12)) ? 2 : 3;
        }
        return 3;
    }

    public int getNaviVolume() {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(12));
    }

    public int getNavigationMediaState() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(6, 1);
        }
        return 1;
    }

    public int getPhoneVolume() {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2));
    }

    public int getPowerMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(15, 0);
        }
        return 0;
    }

    public String getSetPermission() {
        return BYDAutoAudioDevice.AUDIO_SET_PERM;
    }

    public int getSoundEffect() {
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer == null) {
            return 0;
        }
        int currentPreset = carEqualizer.getCurrentPreset();
        if (currentPreset == 0) {
            return 18;
        }
        if (currentPreset == 1) {
            return 19;
        }
        if (currentPreset == 2) {
            return 20;
        }
        if (currentPreset != 3) {
            return currentPreset != 4 ? 0 : 22;
        }
        return 21;
    }

    public int[] getSoundField() {
        int[] iArr = new int[2];
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            int balanceTowardRightLevel = carEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeTowardFrontLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            iArr[0] = convertDb(balanceTowardRightLevel, 100);
            iArr[1] = convertDb(fadeTowardFrontLevel, 100);
        }
        return iArr;
    }

    public int getSoundFieldPositionType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 1;
        }
        int i = 3;
        int integerValue = carAudioControl.getIntegerValue(3, 1);
        if (integerValue == 0) {
            return 1;
        }
        int i2 = 2;
        if (integerValue != 1) {
            if (integerValue != 2) {
                i2 = 4;
                if (integerValue != 3) {
                    i = 5;
                    if (integerValue != 4) {
                        return integerValue != 5 ? 1 : 6;
                    }
                }
            }
            return i;
        }
        return i2;
    }

    public int getType() {
        return 1002;
    }

    public int getVSPState() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(18, 0);
        }
        return 0;
    }

    public int getVoiceAssistVolume() {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11));
    }

    public int getVolume() {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(1));
    }

    public int getVolumeGroupIdForUsage(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null && i >= 0 && i <= 36) {
            return carAudioManager.getVolumeGroupIdForUsage(i);
        }
        Log.e(TAG, "set getVolumeGroupIdForUsage failed");
        return 0;
    }

    public int getVolumeSettingSource() {
        return 0;
    }

    public void notifyAudioVolumeChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i, int i2, int i3) {
        int groupVolume = this.mCarAudioManager.getGroupVolume(i2, i);
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 1);
        int volumeGroupIdForUsage2 = this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 12);
        int volumeGroupIdForUsage3 = this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 11);
        int volumeGroupIdForUsage4 = this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 2);
        if (i == volumeGroupIdForUsage) {
            absBYDAutoAudioListener.onVolumeChanged(groupVolume, i3);
            return;
        }
        if (i == volumeGroupIdForUsage2) {
            absBYDAutoAudioListener.onNaviVolumeChanged(groupVolume);
        } else if (i == volumeGroupIdForUsage3) {
            absBYDAutoAudioListener.onVoiceAssistVolume(groupVolume);
        } else if (i == volumeGroupIdForUsage4) {
            absBYDAutoAudioListener.onPhoneVolume(groupVolume);
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    public void onServiceConnected(final Car car) {
        if (this.mCarVolumeCallback == null) {
            Log.w(TAG, "mCarVolumeCallback is null");
            this.mHandler.postDelayed(new Runnable() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    Log.w(AudioManagerImpl.TAG, "onServiceConnected postDelayed");
                    AudioManagerImpl.this.serviceConnected(car);
                }
            }, 200L);
        } else {
            Log.w(TAG, "mCarVolumeCallback is not null");
            serviceConnected(car);
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    public void onServiceDied() {
        Log.e(TAG, "byd jar car service is died");
        if (this.mIsConnected) {
            this.mIsConnected = false;
            this.mCarAudioManager = null;
            this.mCarEnhancedAudioManager = null;
            this.mCarEqualizer = null;
            this.mCarAudioControl = null;
            this.lastAllMuteStatus = 0;
        }
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
        return true;
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] iArr) {
    }

    public void requestEepromAmpType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            carAudioControl.setIntegerValue(13, 0);
            int integerValue = this.mCarAudioControl.getIntegerValue(13, 0);
            Iterator<AbsBYDAutoAudioListener> it = BYD_AUTO_AUDIO_LISTENER.iterator();
            while (it.hasNext()) {
                it.next().onEepromAmpTypeChanged(integerValue);
            }
        }
    }

    public int reset(int i, int i2) {
        CarEnhancedAudioManager carEnhancedAudioManager;
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer != null && (carEnhancedAudioManager = this.mCarEnhancedAudioManager) != null && i >= 0 && i <= 2 && i2 >= 0 && i2 <= 1) {
            if (i2 == 1) {
                if (i == 0) {
                    for (int i3 = 0; i3 < this.mCarEqualizer.getNumberOfBands(); i3++) {
                        this.mCarEqualizer.setBandLevel(i3, 0);
                    }
                } else if (i == 1) {
                    carEqualizer.usePreset(1);
                } else if (i == 2) {
                    carEnhancedAudioManager.setBalanceTowardRightLevel(0);
                    this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(0);
                }
                return 0;
            }
            if (i2 == 0) {
                return -2147482645;
            }
        }
        return -2147482648;
    }

    public int setAllMute(int i) {
        getPowerMute();
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 0 || i > 1) {
            return -2147482648;
        }
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        if (i == 0) {
            for (int i2 = 0; i2 < volumeGroupIdForUsage; i2++) {
                this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i2, false, 0);
            }
        } else if (i == 1) {
            for (int i3 = 0; i3 < volumeGroupIdForUsage; i3++) {
                this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i3, true, 0);
            }
        }
        return 0;
    }

    public int setAudioMicAmpGain(int i) {
        return 0;
    }

    public int setAudioParam(int i, int i2, int i3, int i4) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < -32 || i > 32 || i2 < -32 || i2 > 32 || i3 < -32 || i3 > 32 || i4 < -32 || i4 > 32) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(19, i);
        this.mCarAudioControl.setIntegerValue(20, i2);
        this.mCarAudioControl.setIntegerValue(21, i3);
        this.mCarAudioControl.setIntegerValue(22, i4);
        return 0;
    }

    public int setBalanceFadePosition(int i, int i2) {
        int iConvertMb = convertMb(i, 100);
        int iConvertMb2 = convertMb(i2, 100);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || iConvertMb < -9 || iConvertMb > 9 || iConvertMb2 < -9 || iConvertMb2 > 9) {
            return -2147482648;
        }
        carEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public void setBtCall_MicMute(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 1) {
            return;
        }
        carAudioControl.setIntegerValue(14, i);
    }

    public void setCallMuteState(int i) {
        getPowerMute();
        if (this.mCarAudioControl == null || getPowerMute() != 10 || i < 0 || i > 1) {
            return;
        }
        this.mCarAudioControl.setIntegerValue(17, i);
    }

    public void setCarPlayAltActive(boolean z) {
        if (this.mCarAudioControl != null && getAllMute() == 0) {
            this.mCarAudioControl.setIntegerValue(16, z ? 1 : 0);
        }
        getAllMute();
    }

    public int setCarTipsVoiceState(int i, int i2) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 0 || i2 > 3 || i < 0 || i > 2) {
            return -2147482648;
        }
        if (i == 0) {
            this.chimesVoiceState = i2;
            i = 23;
        } else if (i == 1) {
            this.radarVoiceState = i2;
            i = 24;
        } else if (i == 2) {
            this.adasVoiceState = i2;
            i = 25;
        }
        carAudioControl.setIntegerValue(i, i2);
        return 0;
    }

    public int setChimesVolGain(int i, float f) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 2 || f < -40.0f || f > 40.0f) {
            return -2147482648;
        }
        if (i == 0) {
            this.chimesVolGain = f;
            i = 26;
        } else if (i == 1) {
            this.radarVolGain = f;
            i = 27;
        } else if (i == 2) {
            this.adasVolGain = f;
            i = 28;
        }
        carAudioControl.setFloatValue(i, f);
        return 0;
    }

    public int setDiracLiveMode(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 1 || i > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(2, i);
        return 0;
    }

    public int setDiracLiveState(int i) {
        return 0;
    }

    public int setDiracPerformanceMode(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 1 || i > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(1, i);
        return 0;
    }

    public int setEngineSoundType(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 6) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(11, i);
        return 0;
    }

    public int setEqualizer(int i, int i2) {
        int i3;
        if (this.mCarEqualizer == null || i < 17 || i > 20 || i2 < -9 || i2 > 9) {
            return -2147482648;
        }
        switch (i) {
            case 17:
                i3 = 0;
                break;
            case 18:
                i3 = 1;
                break;
            case 19:
                i3 = 2;
                break;
            case 20:
                i3 = 3;
                break;
            default:
                return -2147482648;
        }
        this.mCarEqualizer.setBandLevel(i3, convertMb(i2, 100));
        return 0;
    }

    public int setGearBySpeed(int i) {
        if (this.mCarAudioControl == null || i < 0 || i > 7 || sAudioManagerImpl.getAudioDeviceManufacturer() != 2) {
            return -2147482648;
        }
        this.mCarAudioControl.setIntegerValue(5, i);
        return 0;
    }

    public int setKtvMode(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 1) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(12, i);
        return 0;
    }

    public int setKtvResource(int i) {
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setKtvResource(i);
        }
        Log.e(TAG, "setKtvResource tiggert");
        return 0;
    }

    public void setLoudness(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 8) {
            return;
        }
        carAudioControl.setIntegerValue(10, i);
    }

    public int setLoudnessState(int i) {
        return -2147482648;
    }

    public int setMusicChangeSource(int i) {
        return 0;
    }

    public int setMuteState(int i) {
        getPowerMute();
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 0) {
            return -2147482648;
        }
        if (i > 1) {
            return -2147482648;
        }
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1), i == 1, 0);
        return 0;
    }

    public int setNaviMuteState(int i) {
        getPowerMute();
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 2 || i > 3) {
            return -2147482648;
        }
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12), i == 2, 0);
        return 0;
    }

    public int setNaviVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 0 || i > 10) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(12), i, 0);
        return 0;
    }

    public int setNavigationMediaState(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 3) {
            return -2147482648;
        }
        if (i < 0 && i > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(6, i);
        return 0;
    }

    public int setPhoneVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 5 || i > 39) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2), i, 0);
        return 0;
    }

    public void setPowerMute(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 12) {
            return;
        }
        carAudioControl.setIntegerValue(15, i);
    }

    public int setSoundEffect(int i) {
        int i2;
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer == null || i < 18 || i > 22) {
            return -2147482648;
        }
        switch (i) {
            case 18:
                i2 = 0;
                break;
            case 19:
                i2 = 1;
                break;
            case 20:
                i2 = 2;
                break;
            case 21:
                i2 = 3;
                break;
            case 22:
                i2 = 4;
                break;
            default:
                return -2147482648;
        }
        carEqualizer.usePreset(i2);
        return 0;
    }

    public int setSoundField(int i, int i2) {
        int iConvertMb = convertMb(i, 100);
        int iConvertMb2 = convertMb(i2, 100);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || i < -9 || i > 9 || i2 < -9 || i2 > 9) {
            return -2147482648;
        }
        carEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public int setSoundFieldPosition(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            int i2 = 1;
            if (i >= 1 && i <= 6) {
                switch (i) {
                    case 1:
                        i2 = 0;
                        break;
                    case 2:
                        break;
                    case 3:
                        i2 = 2;
                        break;
                    case 4:
                        i2 = 3;
                        break;
                    case 5:
                        i2 = 4;
                        break;
                    case 6:
                        i2 = 5;
                        break;
                    default:
                        return -2147482648;
                }
                carAudioControl.setIntegerValue(3, i2);
                return 0;
            }
        }
        return -2147482648;
    }

    public int setVSPState(int i) {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return -2147482648;
        }
        if (i != 1 && i != 0) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(18, i);
        return 0;
    }

    public int setVoiceAssistVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 5 || i > 24) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11), i, 0);
        return 0;
    }

    public int setVolume(int i) {
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 0 || i > 39) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(1), i, 0);
        return 0;
    }

    public int setVolumeSettingsource() {
        return 0;
    }

    public void unregisterListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Objects.requireNonNull(absBYDAutoAudioListener);
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            if (arrayList.contains(absBYDAutoAudioListener)) {
                arrayList.remove(absBYDAutoAudioListener);
            }
        }
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Objects.requireNonNull(absBYDAutoAudioListener);
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            if (!arrayList.contains(absBYDAutoAudioListener)) {
                arrayList.add(absBYDAutoAudioListener);
            }
        }
    }
}
