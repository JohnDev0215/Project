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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes.dex */
public class AudioManagerImpl extends BYDCarServiceBase {
    public static final int ANC_END_SPEED = 6500;
    public static final int ANC_START_SPEED = 0;
    public static final int ANC_SWITCH_NO_1 = 1;
    public static final int ANC_SWITCH_NO_2 = 2;
    public static final int ANC_SWITCH_STATE_OFF = 0;
    public static final int ANC_SWITCH_STATE_ON = 1;
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
    public static final int BS_TREBLE_INDEX = 4;
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
    public static final int GENERAL_ALC_OFF = 0;
    public static final int GENERAL_ALC_ON = 1;
    public static final int GENERAL_BALANCE_VALUE_MAX = 10;
    public static final int GENERAL_BALANCE_VALUE_MIN = -10;
    public static final int GENERAL_EQUALIZER_BAND_0 = 0;
    public static final int GENERAL_EQUALIZER_BAND_1 = 1;
    public static final int GENERAL_EQUALIZER_BAND_2 = 2;
    public static final int GENERAL_EQUALIZER_BAND_3 = 3;
    public static final int GENERAL_EQUALIZER_BAND_4 = 4;
    public static final int GENERAL_EQUALIZER_BAND_MAX = 4;
    public static final int GENERAL_EQUALIZER_BAND_MIN = 0;
    public static final int GENERAL_EQUALIZER_VALUE_MAX = 10;
    public static final int GENERAL_EQUALIZER_VALUE_MIN = -10;
    public static final int GENERAL_FADE_VALUE_MAX = 10;
    public static final int GENERAL_FADE_VALUE_MIN = -10;
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
    public static final int INDEX_END_SPD = 32;
    public static final int INDEX_EQUALIZER_TYPE_0 = 36;
    public static final int INDEX_EQUALIZER_TYPE_1 = 37;
    public static final int INDEX_EQUALIZER_TYPE_2 = 38;
    public static final int INDEX_EQUALIZER_TYPE_3 = 39;
    public static final int INDEX_EQUALIZER_TYPE_4 = 40;
    public static final int INDEX_INTELLIGENT_BROADCAST = 7;
    public static final int INDEX_KTV_MODE = 12;
    public static final int INDEX_LOUDNESS_STATE = 33;
    public static final int INDEX_MIC_MUTE_TYPE = 14;
    public static final int INDEX_NAV_MIX_MEDIA = 6;
    public static final int INDEX_POWER_MUTE_TYPE = 15;
    public static final int INDEX_RADAR_CARTIPS_VOICE_STATE = 24;
    public static final int INDEX_RADAR_GAIN = 27;
    public static final int INDEX_SOUNDFIELD_BALANCE = 34;
    public static final int INDEX_SOUNDFIELD_FADE = 35;
    public static final int INDEX_START_SPD = 31;
    public static final int INDEX_SWITCH_NO = 29;
    public static final int INDEX_SWITCH_STATE = 30;
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
    private static final String TAG = "AudioManagerImpl_0.47";
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
    private int engineSoundType;
    private int equalizerManufactor;
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
    private int soundFieldManufactor;

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
        this.engineSoundType = 0;
        this.mCarVolumeCallback = new CarAudioManager.CarVolumeCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int i2, int i3, int i4) {
                Log.d(AudioManagerImpl.TAG, "group volume change zoneId : " + i2 + " greoupID : " + i3 + "flags : " + i4);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioVolumeChanged((AbsBYDAutoAudioListener) it.next(), i3, i2, i4);
                }
            }
        };
        this.mCarBalanceCallback = new CarEnhancedAudioManager.CarBalanceCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.2
            public void onBalanceTowardRightLevelChanged() {
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                Log.d(AudioManagerImpl.TAG, "balance value change balance : " + balanceFadePosition[0] + " fade : " + balanceFadePosition[1]);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }

            public void onFadeTowardFrontLevelChanged() {
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                Log.d(AudioManagerImpl.TAG, "fade value change balance : " + balanceFadePosition[0] + "fade : " + balanceFadePosition[1]);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }
        };
        this.mCarGroupMutingCallback = new CarEnhancedAudioManager.CarGroupMutingCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.3
            public void onGroupMuteChanged(int i2, int i3, int i4) {
                int allMute = AudioManagerImpl.this.getAllMute();
                Log.w(AudioManagerImpl.TAG, "audio all mute state is " + allMute + ", lastAllMuteStatus is " + AudioManagerImpl.this.lastAllMuteStatus);
                if (allMute != AudioManagerImpl.this.lastAllMuteStatus) {
                    Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoAudioListener) it.next()).onAllMuteState(allMute);
                    }
                    AudioManagerImpl.this.lastAllMuteStatus = allMute;
                }
                if (i3 == AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 1)) {
                    Log.w(AudioManagerImpl.TAG, "audio Media mute state is " + AudioManagerImpl.this.getMuteState());
                    Iterator it2 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it2.hasNext()) {
                        ((AbsBYDAutoAudioListener) it2.next()).onMuteStateChanged(AudioManagerImpl.this.getMuteState());
                    }
                    return;
                }
                if (i3 == AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i2, 12)) {
                    Log.w(AudioManagerImpl.TAG, "audio Nav mute state is " + AudioManagerImpl.this.getNaviMuteState());
                    Iterator it3 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it3.hasNext()) {
                        ((AbsBYDAutoAudioListener) it3.next()).onNaviMuteStateChanged(AudioManagerImpl.this.getNaviMuteState());
                    }
                }
            }
        };
        this.mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.4
            public void onFocusChanged(int i2, List<AudioFocusInfo> list) {
                Log.d(AudioManagerImpl.TAG, "focus change focus holder list : " + list.toString());
                for (AudioFocusInfo audioFocusInfo : list) {
                    Log.v(AudioManagerImpl.TAG, "focus change focus holder usage : " + audioFocusInfo.getAttributes().getUsage());
                    int usage = audioFocusInfo.getAttributes().getUsage();
                    if (usage == 0 || usage == 1 || usage == 14 || usage == 24 || usage == 33) {
                        AudioManagerImpl.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    }
                }
                Log.d(AudioManagerImpl.TAG, "focus change getPackageName is :" + AudioManagerImpl.this.lastMediaPackage);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onAudioFocusChanged(i2, list);
                }
            }
        };
        this.mCarEqualizerCallback = new CarEqualizer.CarEqualizerCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.5
            public void onBandLevelChanged(int i2) {
                if (i2 == 0) {
                    i2 = 17;
                } else if (i2 == 1) {
                    i2 = 18;
                } else if (i2 == 2) {
                    i2 = 19;
                } else if (i2 == 3) {
                    i2 = 20;
                } else if (i2 == 4) {
                    i2 = 21;
                }
                int equalizer = AudioManagerImpl.this.getEqualizer(i2);
                Log.d(AudioManagerImpl.TAG, "band level changed band is : " + i2 + " value : " + equalizer);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onEqualizerChanged(i2, equalizer);
                }
            }

            public void onPresetChanged() {
                int soundEffect = AudioManagerImpl.this.getSoundEffect();
                Log.d(AudioManagerImpl.TAG, "preset Changed effect is : " + soundEffect);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundEffectChanged(soundEffect);
                }
            }
        };
        this.mCarAudioControlCallback = new CarAudioControl.CarAudioControlCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.6
            public void onAudioControlChanged(int i2) {
                Log.d(AudioManagerImpl.TAG, "audio control index is : " + i2);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioControlChanged((AbsBYDAutoAudioListener) it.next(), i2);
                }
            }
        };
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    private int convertDb(int i2, int i3) {
        return i2 / i3;
    }

    private int convertMb(int i2, int i3) {
        return i2 * i3;
    }

    private void enforcePermission(String str) {
        if (this.mIgnoreBydPermission || this.mContext.checkCallingOrSelfPermission(str) == 0) {
            return;
        }
        throw new SecurityException("requires permission " + str);
    }

    public static AudioManagerImpl getInstance(Context context) {
        if (sAudioManagerImpl == null) {
            synchronized (AudioManagerImpl.class) {
                if (sAudioManagerImpl == null) {
                    Log.d(TAG, "AudioManagerImpl equal null ");
                    sAudioManagerImpl = new AudioManagerImpl(context);
                }
            }
        }
        Log.d(TAG, "getInstance to do ");
        return sAudioManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioControlChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i2) {
        Log.d(TAG, "notify Audio Control Changed index is : " + i2);
        if (i2 == 3) {
            int soundFieldPositionType = getSoundFieldPositionType();
            Log.d(TAG, "audio control change INDEX_DYNA_SOUND_FIELD_FOCUS type : " + soundFieldPositionType);
            absBYDAutoAudioListener.onSoundFieldPositionChanged(soundFieldPositionType);
        }
        if (i2 == 18) {
            int integerValue = this.mCarAudioControl.getIntegerValue(18, 0);
            Log.d(TAG, "audio control change INDEX_AVAS_STATE vspState : " + integerValue);
            absBYDAutoAudioListener.onVSPStateChanged(integerValue);
            return;
        }
        if (i2 == 5) {
            int audioDeviceManufacturer = sAudioManagerImpl.getAudioDeviceManufacturer();
            int integerValue2 = this.mCarAudioControl.getIntegerValue(5, 4);
            Log.d(TAG, "audio control change INDEX_DYNA_VSC manufacturer : " + audioDeviceManufacturer + "gear : " + integerValue2);
            absBYDAutoAudioListener.onGearSpeedChanged(audioDeviceManufacturer, integerValue2);
            return;
        }
        if (i2 == 6) {
            int integerValue3 = this.mCarAudioControl.getIntegerValue(6, 1);
            Log.d(TAG, "audio control change INDEX_NAV_MIX_MEDIA value : " + integerValue3);
            absBYDAutoAudioListener.onNavigationMediaStateChanged(integerValue3);
            return;
        }
        if (i2 == 10) {
            int integerValue4 = this.mCarAudioControl.getIntegerValue(10, 0);
            Log.d(TAG, "audio control change INDEX_DIRAC_LIVE_LOUDNESS ludnessType : " + integerValue4);
            absBYDAutoAudioListener.onLudnessTypeChange(integerValue4);
            return;
        }
        if (i2 == 11) {
            int i3 = this.engineSoundType;
            Log.d(TAG, "audio control change INDEX_AVAS_MODE avasTypeValue : " + i3);
            absBYDAutoAudioListener.onEngineSoundTypeChanged(i3);
            return;
        }
        if (i2 == 14) {
            int integerValue5 = this.mCarAudioControl.getIntegerValue(14, 0);
            Log.d(TAG, "audio control change INDEX_MIC_MUTE_TYPE micMuteState : " + integerValue5);
            absBYDAutoAudioListener.onBtCall_MicMuteChanged(integerValue5);
            return;
        }
        if (i2 == 15) {
            int integerValue6 = this.mCarAudioControl.getIntegerValue(15, 0);
            Log.d(TAG, "audio control change INDEX_POWER_MUTE_TYPE powerMuteState : " + integerValue6);
            absBYDAutoAudioListener.onPowerMuteStateChange(integerValue6);
            return;
        }
        switch (i2) {
            case 23:
                Log.d(TAG, "audio control change chimesVoiceState: " + this.chimesVoiceState);
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(0, this.chimesVoiceState);
                break;
            case 24:
                Log.d(TAG, "audio control change radarVoiceState: " + this.radarVoiceState);
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(1, this.radarVoiceState);
                break;
            case 25:
                Log.d(TAG, "audio control change adasVoiceState: " + this.adasVoiceState);
                absBYDAutoAudioListener.onCarTipsVoiceStateChanged(2, this.adasVoiceState);
                break;
            case 26:
                Log.d(TAG, "audio control change chimesVolGain: " + i2 + ", value = " + this.chimesVolGain);
                absBYDAutoAudioListener.onChimesVolGainChanged(0, this.chimesVolGain);
                break;
            case 27:
                Log.d(TAG, "audio control change radarVolGain: " + i2 + ", value = " + this.radarVolGain);
                absBYDAutoAudioListener.onChimesVolGainChanged(1, this.radarVolGain);
                break;
            case 28:
                Log.d(TAG, "audio control change adasVolGain: " + i2 + ", value = " + this.adasVolGain);
                absBYDAutoAudioListener.onChimesVolGainChanged(2, this.adasVolGain);
                break;
            default:
                switch (i2) {
                    case 33:
                        int integerValue7 = this.mCarAudioControl.getIntegerValue(33, 0);
                        Log.d(TAG, "audio control change loudnessState: " + i2 + ", value = " + integerValue7);
                        absBYDAutoAudioListener.onLoudnessStateChanged(integerValue7);
                        break;
                    case 34:
                    case 35:
                        int integerValue8 = this.mCarAudioControl.getIntegerValue(34, 0);
                        int integerValue9 = this.mCarAudioControl.getIntegerValue(35, 0);
                        Log.d(TAG, "audio control change soundField: " + i2 + ", value balance = " + integerValue8 + ", fade = " + integerValue9);
                        absBYDAutoAudioListener.onGeneralSoundFieldChanged(this.soundFieldManufactor, integerValue8, integerValue9);
                        break;
                    case 36:
                        int integerValue10 = this.mCarAudioControl.getIntegerValue(36, 0);
                        Log.d(TAG, "audio control change equalizer: " + i2 + ", value = " + integerValue10);
                        absBYDAutoAudioListener.onGeneralEqualizerChanged(this.equalizerManufactor, 0, integerValue10);
                        break;
                    case 37:
                        int integerValue11 = this.mCarAudioControl.getIntegerValue(37, 0);
                        Log.d(TAG, "audio control change equalizer: " + i2 + ", value = " + integerValue11);
                        absBYDAutoAudioListener.onGeneralEqualizerChanged(this.equalizerManufactor, 1, integerValue11);
                        break;
                    case 38:
                        int integerValue12 = this.mCarAudioControl.getIntegerValue(38, 0);
                        Log.d(TAG, "audio control change equalizer: " + i2 + ", value = " + integerValue12);
                        absBYDAutoAudioListener.onGeneralEqualizerChanged(this.equalizerManufactor, 2, integerValue12);
                        break;
                    case 39:
                        int integerValue13 = this.mCarAudioControl.getIntegerValue(39, 0);
                        Log.d(TAG, "audio control change equalizer: " + i2 + ", value = " + integerValue13);
                        absBYDAutoAudioListener.onGeneralEqualizerChanged(this.equalizerManufactor, 3, integerValue13);
                        break;
                    case 40:
                        int integerValue14 = this.mCarAudioControl.getIntegerValue(40, 0);
                        Log.d(TAG, "audio control change equalizer: " + i2 + ", value = " + integerValue14);
                        absBYDAutoAudioListener.onGeneralEqualizerChanged(this.equalizerManufactor, 4, integerValue14);
                        break;
                    default:
                        Log.e(TAG, "notifyAudioControlChanged index is invalid");
                        break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serviceConnected(Car car) {
        if (this.mIsConnected) {
            Log.d(TAG, "byd jar car service is connected");
            return;
        }
        this.mIsConnected = true;
        this.mCar = car;
        Log.d(TAG, "onServiceConnected mCar: " + this.mCar);
        this.mCarAudioManager = (CarAudioManager) this.mCar.getCarManager("audio");
        Log.d(TAG, "onServiceConnected mCarAudioManager: " + this.mCarAudioManager);
        this.mCarEnhancedAudioManager = (CarEnhancedAudioManager) this.mCar.getCarManager("enhanced_audio");
        Log.d(TAG, "onServiceConnected mCarEnhancedAudioManager: " + this.mCarEnhancedAudioManager);
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return;
        }
        this.mCarAudioControl = new CarAudioControl(this.mCar, this.mCarEnhancedAudioManager);
        this.mCarEqualizer = new CarEqualizer(this.mCar, this.mCarEnhancedAudioManager);
        Log.d(TAG, "onServiceConnected mCarAudioControl: " + this.mCarAudioControl + " mCarEqualizer : " + this.mCarEqualizer + " mCarEnhancedAudioManager :" + this.mCarEnhancedAudioManager);
        this.mCarAudioManager.registerCarVolumeCallback(this.mCarVolumeCallback);
        this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, this.mCarAudioManager.getAudioZoneIds().stream().mapToInt(new ToIntFunction() { // from class: a.b.e.a.a.e
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray());
        this.mCarEnhancedAudioManager.registerCarBalanceCallback(this.mCarBalanceCallback);
        this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
        this.mCarEqualizer.registerCarEqualizerCallback(this.mCarEqualizerCallback);
        this.mCarAudioControl.setCarAudioControlCallback(this.mCarAudioControlCallback);
        for (int i2 = 0; i2 <= 40; i2++) {
            this.mCarAudioControl.checkAndUpdateCarAudioControlCallback(i2, true);
            Log.d(TAG, "car audio control register index : " + i2);
        }
        this.lastAllMuteStatus = getAllMute();
    }

    public int generalReset(int i2, int i3, int i4) {
        Log.d(TAG, "generalReset tigger manufactor : " + i2 + ", item : " + i3 + " value : " + i4);
        if (this.mCarAudioControl != null && i3 >= 0 && i3 <= 2 && i4 >= 0 && i4 <= 1) {
            if (i4 == 1) {
                if (i3 == 0) {
                    Log.d(TAG, "generalReset RESET_ITEM_EQ");
                    for (int i5 = 0; i5 <= 4; i5++) {
                        int i6 = 36;
                        if (i5 != 0) {
                            if (i5 == 1) {
                                i6 = 37;
                            } else if (i5 == 2) {
                                i6 = 38;
                            } else if (i5 == 3) {
                                i6 = 39;
                            } else if (i5 == 4) {
                                i6 = 40;
                            }
                        }
                        this.mCarAudioControl.setIntegerValue(i6, 0);
                    }
                } else if (i3 == 1) {
                    Log.d(TAG, "generalReset RESET_ITEM_BALANCE");
                } else if (i3 == 2) {
                    Log.d(TAG, "generalReset RESET_ITEM_BALANCE_FADE");
                    this.mCarAudioControl.setIntegerValue(34, 0);
                    this.mCarAudioControl.setIntegerValue(35, 0);
                }
                return 0;
            }
            if (i4 == 0) {
                return -2147482645;
            }
        }
        return -2147482648;
    }

    public int getAllMute() {
        Log.d(TAG, "getAllMute tigger ");
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        for (int i2 = 0; i2 < volumeGroupIdForUsage; i2++) {
            boolean zIsVolumeGroupMuted = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, i2);
            Log.d(TAG, "getAllMute groupIndex is : " + i2 + " state is :" + zIsVolumeGroupMuted);
            if (zIsVolumeGroupMuted) {
                Log.d(TAG, "getAllMute state is enable");
                arrayList.add(1);
            } else {
                Log.d(TAG, "getAllMute state is disable");
                arrayList.add(0);
            }
        }
        if (arrayList.contains(0)) {
            Log.d(TAG, "getAllMuteGroupArray contains disable");
            return 0;
        }
        Log.d(TAG, "getAllMuteGroupArray contains enable");
        return 1;
    }

    public int getAudioDeviceConfig() {
        Log.d(TAG, "getAudioDeviceConfig tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            i = carAudioControl.getIntegerValue(4, 0) == 0 ? 17 : 0;
            Log.d(TAG, "getAudioDeviceConfig successed :" + i);
        }
        return i;
    }

    public int getAudioDeviceManufacturer() {
        Log.d(TAG, "getAudioDeviceManufacturer tigger");
        return 2;
    }

    public int getAudioMicAmpGain() {
        Log.d(TAG, "getAudioMicAmpGain tigger ");
        return 0;
    }

    public int[] getBalanceFadePosition() {
        Log.d(TAG, "getBalanceFadePosition tigger ");
        int[] iArr = new int[2];
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            int balanceTowardRightLevel = carEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeTowardFrontLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            iArr[0] = convertDb(balanceTowardRightLevel, 100);
            iArr[1] = convertDb(fadeTowardFrontLevel, 100);
            Log.d(TAG, "BYDAudio getBalanceFadePosition balance is : " + iArr[0]);
            Log.d(TAG, "BYDAudio getBalanceFadePosition fade is : " + iArr[1]);
        }
        return iArr;
    }

    public int getBtCall_MicMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int integerValue = carAudioControl.getIntegerValue(14, 0);
        Log.d(TAG, "getmicState tigger state :  " + integerValue);
        return integerValue;
    }

    public int getCallMuteState() {
        if (this.mCarAudioControl == null) {
            return 0;
        }
        Log.d(TAG, "getCallMuteState tigger state is " + this.mCarAudioControl.getIntegerValue(17, 0));
        return this.mCarAudioControl.getIntegerValue(17, 0);
    }

    public int getCarTipsVoiceState(int i2) {
        Log.d(TAG, "getCarTipsVoiceState type = " + i2);
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 2) {
            Log.e(TAG, "getCarTipsVoiceState failed");
            return 0;
        }
        Log.d(TAG, "getCarTipsVoiceState tigger");
        if (i2 == 0) {
            i2 = 23;
        } else if (i2 == 1) {
            i2 = 24;
        } else if (i2 == 2) {
            i2 = 25;
        }
        return this.mCarAudioControl.getIntegerValue(i2, 0);
    }

    public float getChimesVolGain(int i2) {
        Log.d(TAG, "getChimesVolGain chimeType = " + i2);
        if (i2 >= 0 && i2 <= 2) {
            Log.d(TAG, "getChimesVolGain tigger");
            if (i2 == 0) {
                return this.chimesVolGain;
            }
            if (i2 == 1) {
                return this.radarVolGain;
            }
            if (i2 == 2) {
                return this.adasVolGain;
            }
        }
        Log.e(TAG, "getChimesVolGain failed");
        return 0.0f;
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo(int i2) {
        Log.d(TAG, "getCurAudioFocusInfo tigger zoneId : " + i2);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || i2 != 0) {
            return null;
        }
        try {
            return carEnhancedAudioManager.getAudioFocusHoldersForZoneId(i2);
        } catch (IllegalArgumentException unused) {
            Log.d(TAG, "zoneId is out of range ");
            return null;
        }
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype tigger ");
        return 1002;
    }

    public int getDiracLiveMode() {
        Log.d(TAG, "getDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(2, 2);
        }
        return 0;
    }

    public int getDiracLiveState() {
        Log.d(TAG, "getDiracLiveState tigger ");
        return 0;
    }

    public int getDiracPerformanceMode() {
        Log.d(TAG, "getDiracPerformanceMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            i = carAudioControl.getIntegerValue(1, 1) == 1 ? 1 : 0;
            Log.d(TAG, "getDiracPerformanceMode successed :" + i);
        }
        return i;
    }

    public int getEngineSoundType() {
        Log.d(TAG, "getEngineSoundType tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(11, 2);
        }
        return 2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getEqualizer(int i2) {
        Log.d(TAG, "getEqualizer tigger frequencyNum is : " + i2);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int iConvertDb = 0;
        if (carEqualizer != null && i2 >= 17 && i2 <= 21) {
            switch (i2) {
                case 17:
                    iConvertDb = convertDb(carEqualizer.getBandLevel(iConvertDb), 100);
                    break;
                case 18:
                    iConvertDb = 1;
                    iConvertDb = convertDb(carEqualizer.getBandLevel(iConvertDb), 100);
                    break;
                case 19:
                    iConvertDb = 2;
                    iConvertDb = convertDb(carEqualizer.getBandLevel(iConvertDb), 100);
                    break;
                case 20:
                    iConvertDb = 3;
                    iConvertDb = convertDb(carEqualizer.getBandLevel(iConvertDb), 100);
                    break;
                case 21:
                    iConvertDb = 4;
                    iConvertDb = convertDb(carEqualizer.getBandLevel(iConvertDb), 100);
                    break;
                default:
                    Log.d(TAG, "get frequencyNum is not founded" + i2);
                    return 0;
            }
        }
        Log.d(TAG, "getEqualizer successed : " + iConvertDb);
        return iConvertDb;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList tigger ");
        return new int[0];
    }

    public int getGearBySpeed() {
        Log.d(TAG, "getGearBySpeed tigger");
        int integerValue = (this.mCarAudioControl == null || sAudioManagerImpl.getAudioDeviceManufacturer() != 2) ? 0 : this.mCarAudioControl.getIntegerValue(5, 4);
        Log.d(TAG, "getGearBySpeed successed : " + integerValue);
        return integerValue;
    }

    public int getGeneralEqualizer(int i2, int i3) {
        Log.d(TAG, "getGeneralEqualizer tigger manufactor : " + i2 + ", frequencyNum : " + i3);
        if (this.mCarAudioControl == null || i3 < 0 || i3 > 4) {
            return 0;
        }
        Log.d(TAG, "getGeneralEqualizer conditions met");
        int i4 = 36;
        if (i3 != 0) {
            if (i3 == 1) {
                i4 = 37;
            } else if (i3 == 2) {
                i4 = 38;
            } else if (i3 == 3) {
                i4 = 39;
            } else if (i3 == 4) {
                i4 = 40;
            }
        }
        int integerValue = this.mCarAudioControl.getIntegerValue(i4, 0);
        Log.d(TAG, "getGeneralEqualizer equalizerValue : " + integerValue);
        return integerValue;
    }

    public int[] getGeneralSoundField(int i2) {
        Log.d(TAG, "getGeneralSoundField tigger manufactor : " + i2);
        int[] iArr = new int[2];
        if (this.mCarAudioControl != null) {
            Log.d(TAG, "getGeneralSoundField conditions met");
            int integerValue = this.mCarAudioControl.getIntegerValue(34, 0);
            int integerValue2 = this.mCarAudioControl.getIntegerValue(35, 0);
            iArr[0] = integerValue;
            iArr[1] = integerValue2;
            Log.d(TAG, "getGeneralSoundField balance is : " + iArr[0] + ", fade : " + iArr[1]);
        }
        return iArr;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission tigger");
        return BYDAutoAudioDevice.AUDIO_GET_PERM;
    }

    public int getGroupMaxVolume(int i2) {
        if (this.mCarAudioManager == null || i2 < 0 || i2 > 5) {
            Log.e(TAG, "set getGroupMaxVolume failed");
            return 0;
        }
        Log.d(TAG, "getGroupMaxVolume groupID : " + i2);
        return this.mCarAudioManager.getGroupMaxVolume(i2);
    }

    public int getGroupMinVolume(int i2) {
        if (this.mCarAudioManager == null || i2 < 0 || i2 > 5) {
            Log.e(TAG, "set getGroupMinVolume failed");
            return 0;
        }
        Log.d(TAG, "sgetGroupMinVolume tigger groupID : " + i2);
        return this.mCarAudioManager.getGroupMinVolume(i2);
    }

    public int getKtvMode() {
        Log.d(TAG, "getKTVMode tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(12, 0);
        }
        return 0;
    }

    public String getLastMediaPackage() {
        Log.d(TAG, "getLastMediaPackage tigger ");
        String str = this.lastMediaPackage;
        return str != null ? str : "lastMediaPackage is null";
    }

    public int getLoudness() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int integerValue = carAudioControl.getIntegerValue(10, 0);
        Log.d(TAG, "getloudnessType successed :  " + integerValue);
        return integerValue;
    }

    public int getLoudnessState() {
        Log.d(TAG, "getLoudnessState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int integerValue = carAudioControl.getIntegerValue(33, 0);
        Log.d(TAG, "getLoudnessState loudnessState: " + integerValue);
        return integerValue;
    }

    public int getLoudspeakerNum() {
        Log.d(TAG, "getLoudspeakerNum tigger ");
        return 0;
    }

    public int getMusicChangeSource() {
        Log.d(TAG, "getMusicChangeSource tigger");
        return 1;
    }

    public int getMuteState() {
        Log.d(TAG, "media getMuteState tigger ");
        if (this.mCarEnhancedAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getMuteState conditions met");
        return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1)) ? 1 : 0;
    }

    public int getNaviMuteState() {
        Log.d(TAG, "getNaviMuteState tigger ");
        if (this.mCarEnhancedAudioManager == null) {
            return 3;
        }
        Log.d(TAG, "getNaviMuteState conditions met");
        return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12)) ? 2 : 3;
    }

    public int getNaviVolume() {
        Log.d(TAG, "nav getNaviVolume tigger ");
        if (this.mCarAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getNaviVolume conditions met");
        return this.mCarAudioManager.getGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(12));
    }

    public int getNavigationMediaState() {
        Log.d(TAG, "getNavigationMediaState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(6, 1);
        }
        return 1;
    }

    public int getPhoneVolume() {
        Log.d(TAG, "getPhoneVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2));
    }

    public int getPowerMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int integerValue = carAudioControl.getIntegerValue(15, 0);
        Log.d(TAG, "getmuteState tigger state :  " + integerValue);
        return integerValue;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission tigger ");
        return BYDAutoAudioDevice.AUDIO_SET_PERM;
    }

    public int getSoundEffect() {
        Log.d(TAG, "getSoundEffect tigger");
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int i2 = 0;
        if (carEqualizer != null) {
            int currentPreset = carEqualizer.getCurrentPreset();
            Log.d(TAG, "getSoundEffect conditions met" + currentPreset);
            if (currentPreset == 0) {
                i2 = 18;
            } else if (currentPreset == 1) {
                i2 = 19;
            } else if (currentPreset == 2) {
                i2 = 20;
            } else if (currentPreset == 3) {
                i2 = 21;
            } else {
                if (currentPreset != 4) {
                    Log.d(TAG, "get effect is invalid0");
                    return 0;
                }
                i2 = 22;
            }
        }
        Log.d(TAG, "getSoundEffect success : " + i2);
        return i2;
    }

    public int[] getSoundField() {
        Log.d(TAG, "getSoundField tigger ");
        int[] iArr = new int[2];
        if (this.mCarEnhancedAudioManager != null) {
            Log.d(TAG, "setSoundField conditions met");
            int balanceTowardRightLevel = this.mCarEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeTowardFrontLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            iArr[0] = convertDb(balanceTowardRightLevel, 100);
            iArr[1] = convertDb(fadeTowardFrontLevel, 100);
            Log.d(TAG, "BYDAudio getSoundField balance is : " + iArr[0]);
            Log.d(TAG, "BYDAudio getSoundField fade is : " + iArr[1]);
        }
        return iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getSoundFieldPositionType() {
        /*
            r7 = this;
            java.lang.String r0 = "AudioManagerImpl_0.47"
            java.lang.String r1 = "getSoundFieldPositionType tigger"
            android.util.Log.d(r0, r1)
            android.car.media.CarAudioControl r1 = r7.mCarAudioControl
            r2 = 5
            r3 = 4
            r4 = 2
            r5 = 3
            r6 = 1
            if (r1 == 0) goto L29
            int r1 = r1.getIntegerValue(r5, r6)
            if (r1 == 0) goto L29
            if (r1 == r6) goto L27
            if (r1 == r4) goto L25
            if (r1 == r5) goto L23
            if (r1 == r3) goto L2a
            if (r1 == r2) goto L21
            goto L29
        L21:
            r2 = 6
            goto L2a
        L23:
            r2 = r3
            goto L2a
        L25:
            r2 = r5
            goto L2a
        L27:
            r2 = r4
            goto L2a
        L29:
            r2 = r6
        L2a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "getSoundFieldPositionType success :"
            r1.append(r3)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.getSoundFieldPositionType():int");
    }

    public int getType() {
        Log.d(TAG, "getType tigger ");
        return 1002;
    }

    public int getVSPState() {
        Log.d(TAG, "getVSPState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int integerValue = carAudioControl.getIntegerValue(18, 0);
        Log.d(TAG, "getVSPState tigger success : " + integerValue);
        return integerValue;
    }

    public int getVoiceAssistVolume() {
        Log.d(TAG, "getVoiceAssistVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11));
    }

    public int getVolume() {
        Log.d(TAG, "media getVolume tigger ");
        if (this.mCarAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getVolume conditions met");
        return this.mCarAudioManager.getGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(1));
    }

    public int getVolumeGroupIdForUsage(int i2) {
        if (this.mCarAudioManager == null || i2 < 0 || i2 > 36) {
            Log.e(TAG, "set getVolumeGroupIdForUsage failed");
            return 0;
        }
        Log.d(TAG, "getVolumeGroupIdForUsage usage : " + i2);
        return this.mCarAudioManager.getVolumeGroupIdForUsage(i2);
    }

    public int getVolumeSettingSource() {
        Log.d(TAG, "getVolumeSettingsource tigger");
        return 0;
    }

    public void notifyAudioVolumeChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i2, int i3, int i4) {
        int groupVolume = this.mCarAudioManager.getGroupVolume(i3, i2);
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(i3, 1);
        int volumeGroupIdForUsage2 = this.mCarAudioManager.getVolumeGroupIdForUsage(i3, 12);
        int volumeGroupIdForUsage3 = this.mCarAudioManager.getVolumeGroupIdForUsage(i3, 11);
        int volumeGroupIdForUsage4 = this.mCarAudioManager.getVolumeGroupIdForUsage(i3, 2);
        if (i2 == volumeGroupIdForUsage) {
            absBYDAutoAudioListener.onVolumeChanged(groupVolume, i4);
            return;
        }
        if (i2 == volumeGroupIdForUsage2) {
            absBYDAutoAudioListener.onNaviVolumeChanged(groupVolume);
        } else if (i2 == volumeGroupIdForUsage3) {
            absBYDAutoAudioListener.onVoiceAssistVolume(groupVolume);
        } else if (i2 == volumeGroupIdForUsage4) {
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
        if (!this.mIsConnected) {
            Log.d(TAG, "byd jar car service is not connected");
            return;
        }
        this.mIsConnected = false;
        this.mCarAudioManager = null;
        this.mCarEnhancedAudioManager = null;
        this.mCarEqualizer = null;
        this.mCarAudioControl = null;
        this.lastAllMuteStatus = 0;
    }

    public boolean postEvent(int i2, int i3, int i4, Object obj) {
        Log.d(TAG, "postEvent tigger ");
        return true;
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener tigger");
        Objects.requireNonNull(absBYDAutoAudioListener);
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            if (!arrayList.contains(absBYDAutoAudioListener)) {
                arrayList.add(absBYDAutoAudioListener);
            }
        }
    }

    public void requestEepromAmpType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            carAudioControl.setIntegerValue(13, 0);
            int integerValue = this.mCarAudioControl.getIntegerValue(13, 0);
            Log.d(TAG, "requestEepromAmpType tigger get is : " + integerValue);
            Iterator<AbsBYDAutoAudioListener> it = BYD_AUTO_AUDIO_LISTENER.iterator();
            while (it.hasNext()) {
                it.next().onEepromAmpTypeChanged(integerValue);
            }
        }
    }

    public int reset(int i2, int i3) {
        CarEnhancedAudioManager carEnhancedAudioManager;
        Log.d(TAG, "reset tigger item is : " + i2 + " value : " + i3);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer != null && (carEnhancedAudioManager = this.mCarEnhancedAudioManager) != null && i2 >= 0 && i2 <= 2 && i3 >= 0 && i3 <= 1) {
            if (i3 == 1) {
                if (i2 == 0) {
                    for (int i4 = 0; i4 < this.mCarEqualizer.getNumberOfBands(); i4++) {
                        this.mCarEqualizer.setBandLevel(i4, 0);
                    }
                } else if (i2 == 1) {
                    carEqualizer.usePreset(1);
                } else if (i2 == 2) {
                    carEnhancedAudioManager.setBalanceTowardRightLevel(0);
                    this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(0);
                }
                return 0;
            }
            if (i3 == 0) {
                return -2147482645;
            }
        }
        return -2147482648;
    }

    public int setAllMute(int i2) {
        Log.d(TAG, "setAllMute tigger state : " + i2 + "getpowermute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i2 < 0 || i2 > 1) {
            return -2147482648;
        }
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        if (i2 == 0) {
            for (int i3 = 0; i3 < volumeGroupIdForUsage; i3++) {
                this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i3, false, 0);
                Log.d(TAG, "BYDAudio setAllMute MUTE_DISABLE muteId = " + i3);
            }
        } else if (i2 == 1) {
            for (int i4 = 0; i4 < volumeGroupIdForUsage; i4++) {
                this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i4, true, 0);
                Log.d(TAG, "BYDAudio setAllMute MUTE_ENABLE muteId = " + i4);
            }
        }
        return 0;
    }

    public int setAncSwitch(int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setAncSwitch switchNo = " + i2 + "switchState = " + i3 + ", startSpd = " + i4 + ", endSpd = " + i5);
        if (this.mCarAudioControl == null || i2 < 1 || i2 > 2 || i3 < 0 || i3 > 1 || i4 < 0 || i4 > 6500 || i5 < 0 || i5 > 6500 || i5 <= i4) {
            return -2147482648;
        }
        Log.d(TAG, "setAncSwitch tigger");
        this.mCarAudioControl.setIntegerValue(29, i2);
        this.mCarAudioControl.setIntegerValue(30, i3);
        this.mCarAudioControl.setIntegerValue(31, i4);
        this.mCarAudioControl.setIntegerValue(32, i5);
        return 0;
    }

    public int setAudioMicAmpGain(int i2) {
        Log.d(TAG, "setAudioMicAmpGain tigger ");
        return 0;
    }

    public int setAudioParam(int i2, int i3, int i4, int i5) {
        Log.d(TAG, "setAudioParam dspMIC12Gain = " + i2 + "dspMIC34Gain = " + i3 + ", dspMIC56Gain = " + i4 + ", dspMICXFRefGain = " + i5);
        if (this.mCarAudioControl == null || i2 < -32 || i2 > 32 || i3 < -32 || i3 > 32 || i4 < -32 || i4 > 32 || i5 < -32 || i5 > 32) {
            return -2147482648;
        }
        Log.d(TAG, "setAudioParam tigger");
        this.mCarAudioControl.setIntegerValue(19, i2);
        this.mCarAudioControl.setIntegerValue(20, i3);
        this.mCarAudioControl.setIntegerValue(21, i4);
        this.mCarAudioControl.setIntegerValue(22, i5);
        return 0;
    }

    public int setBalanceFadePosition(int i2, int i3) {
        Log.d(TAG, "setBalanceFadePosition tigger balance : " + i2 + " fade : " + i3);
        int iConvertMb = convertMb(i2, 100);
        int iConvertMb2 = convertMb(i3, 100);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || iConvertMb < -9 || iConvertMb > 9 || iConvertMb2 < -9 || iConvertMb2 > 9) {
            return -2147482648;
        }
        carEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public void setBtCall_MicMute(int i2) {
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 1) {
            return;
        }
        Log.d(TAG, "set set Bt Call MicMute" + i2);
        this.mCarAudioControl.setIntegerValue(14, i2);
    }

    public void setCallMuteState(int i2) {
        Log.d(TAG, "setCallMuteState state:  " + i2 + "getPowerMute is " + getPowerMute());
        if (this.mCarAudioControl == null || getPowerMute() != 10 || i2 < 0 || i2 > 1) {
            return;
        }
        Log.d(TAG, "setCallMuteState tigger state :  " + i2);
        this.mCarAudioControl.setIntegerValue(17, i2);
    }

    public void setCarPlayAltActive(boolean z) {
        if (this.mCarAudioControl != null && getAllMute() == 0) {
            Log.d(TAG, "setCarPlayAltActive tigger state :  " + z);
            this.mCarAudioControl.setIntegerValue(16, z ? 1 : 0);
        }
        Log.d(TAG, "setCarPlayAltActive setall mute state is true :  " + getAllMute());
    }

    public int setCarTipsVoiceState(int i2, int i3) {
        Log.d(TAG, "setCarTipsVoiceState type = " + i2 + ", state = " + i3);
        if (this.mCarAudioControl == null || i3 < 0 || i3 > 3 || i2 < 0 || i2 > 2) {
            return -2147482648;
        }
        Log.d(TAG, "setCarTipsVoiceState tigger");
        if (i2 == 0) {
            i2 = 23;
            this.chimesVoiceState = i3;
        } else if (i2 == 1) {
            i2 = 24;
            this.radarVoiceState = i3;
        } else if (i2 == 2) {
            i2 = 25;
            this.adasVoiceState = i3;
        }
        this.mCarAudioControl.setIntegerValue(i2, i3);
        return 0;
    }

    public int setChimesVolGain(int i2, float f2) {
        Log.d(TAG, "setChimesVolGain chimeType = " + i2 + ", value = " + f2);
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 2 || f2 < -40.0f || f2 > 40.0f) {
            return -2147482648;
        }
        Log.d(TAG, "setChimesVolGain tigger");
        if (i2 == 0) {
            i2 = 26;
            this.chimesVolGain = f2;
        } else if (i2 == 1) {
            i2 = 27;
            this.radarVolGain = f2;
        } else if (i2 == 2) {
            i2 = 28;
            this.adasVolGain = f2;
        }
        this.mCarAudioControl.setFloatValue(i2, f2);
        return 0;
    }

    public int setDiracLiveMode(int i2) {
        Log.d(TAG, "setDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 1 || i2 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(2, i2);
        return 0;
    }

    public int setDiracLiveState(int i2) {
        Log.d(TAG, "setDiracLiveState tigger ");
        return 0;
    }

    public int setDiracPerformanceMode(int i2) {
        Log.d(TAG, "setDiracPerformanceMode tigger mode is : " + i2);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 1 || i2 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(1, i2);
        return 0;
    }

    public int setEngineSoundType(int i2) {
        Log.d(TAG, "setEngineSoundType tigger volume : " + i2);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 0 || i2 > 6) {
            return -2147482648;
        }
        this.engineSoundType = i2;
        carAudioControl.setIntegerValue(11, i2);
        return 0;
    }

    public int setEqualizer(int i2, int i3) {
        int i4;
        Log.d(TAG, "setEqualizer tigger frequencyNum : " + i2 + " value: " + i3);
        if (this.mCarEqualizer == null || i2 < 17 || i2 > 21 || i3 < -9 || i3 > 9) {
            return -2147482648;
        }
        switch (i2) {
            case 17:
                i4 = 0;
                break;
            case 18:
                i4 = 1;
                break;
            case 19:
                i4 = 2;
                break;
            case 20:
                i4 = 3;
                break;
            case 21:
                i4 = 4;
                break;
            default:
                Log.d(TAG, "set frequencyNum is not founded" + i2);
                return -2147482648;
        }
        int iConvertMb = convertMb(i3, 100);
        Log.d(TAG, "setEqualizer successed  frequencyNum :" + i4 + "value : " + iConvertMb);
        this.mCarEqualizer.setBandLevel(i4, iConvertMb);
        return 0;
    }

    public int setGearBySpeed(int i2) {
        Log.d(TAG, "setGearBySpeed tigger");
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 7) {
            return -2147482648;
        }
        if (sAudioManagerImpl.getAudioDeviceManufacturer() != 2) {
            Log.d(TAG, "speedType is not founded");
            return -2147482648;
        }
        this.mCarAudioControl.setIntegerValue(5, i2);
        return 0;
    }

    public int setGeneralEqualizer(int i2, int i3, int i4) {
        Log.d(TAG, "setGeneralEqualizer tigger manufactor : " + i2 + ", frequencyNum : " + i3 + " value: " + i4);
        if (this.mCarAudioControl == null || i3 < 0 || i3 > 4 || i4 < -10 || i4 > 10) {
            return -2147482648;
        }
        Log.d(TAG, "setGeneralEqualizer conditions met");
        this.equalizerManufactor = i2;
        int i5 = 36;
        if (i3 != 0) {
            if (i3 == 1) {
                i5 = 37;
            } else if (i3 == 2) {
                i5 = 38;
            } else if (i3 == 3) {
                i5 = 39;
            } else if (i3 == 4) {
                i5 = 40;
            }
        }
        this.mCarAudioControl.setIntegerValue(i5, i4);
        return 0;
    }

    public int setGeneralSoundField(int i2, int i3, int i4) {
        Log.d(TAG, "setGeneralSoundField tigger manufactor : " + i2 + ", x : " + i3 + ", y : " + i4);
        if (this.mCarAudioControl == null || i3 < -10 || i3 > 10 || i4 < -10 || i4 > 10) {
            return -2147482648;
        }
        Log.d(TAG, "setGeneralSoundField conditions met");
        this.soundFieldManufactor = i2;
        this.mCarAudioControl.setIntegerValue(34, i3);
        this.mCarAudioControl.setIntegerValue(35, i4);
        return 0;
    }

    public int setKtvMode(int i2) {
        Log.d(TAG, "setKTVMode tigger mode : " + i2);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 0 || i2 > 1) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(12, i2);
        return 0;
    }

    public int setKtvResource(int i2) {
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setKtvResource(i2);
        }
        Log.e(TAG, "setKtvResource tiggert");
        return 0;
    }

    public void setLoudness(int i2) {
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 8) {
            return;
        }
        Log.d(TAG, "setludness tigger type :  " + i2);
        this.mCarAudioControl.setIntegerValue(10, i2);
    }

    public int setLoudnessState(int i2) {
        Log.d(TAG, "setLoudnessState tigger state is : " + i2);
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 1) {
            return -2147482648;
        }
        Log.d(TAG, "setLoudnessState conditions met");
        this.mCarAudioControl.setIntegerValue(33, i2);
        return 0;
    }

    public int setMusicChangeSource(int i2) {
        Log.d(TAG, "setMusicChangeSource tigger");
        return 0;
    }

    public int setMuteState(int i2) {
        Log.d(TAG, "media setMuteState tigger state is : " + i2 + "getPowerMute is : " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i2 < 0) {
            return -2147482648;
        }
        if (i2 > 1) {
            return -2147482648;
        }
        Log.d(TAG, "setMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1), i2 == 1, 0);
        return 0;
    }

    public int setNaviMuteState(int i2) {
        Log.d(TAG, "setNaviMuteState tigger state is : " + i2 + "getPowerMute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i2 < 2 || i2 > 3) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12), i2 == 2, 0);
        return 0;
    }

    public int setNaviVolume(int i2) {
        Log.d(TAG, "nav setNaviVolume tigger volume is : " + i2);
        if (this.mCarAudioManager == null || i2 < 0 || i2 > 10) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(12), i2, 0);
        return 0;
    }

    public int setNavigationMediaState(int i2) {
        Log.d(TAG, "setNavigationMediaState tigger value is " + i2);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i2 < 0 || i2 > 3) {
            return -2147482648;
        }
        if (i2 < 0 && i2 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(6, i2);
        return 0;
    }

    public int setPhoneVolume(int i2) {
        Log.d(TAG, "setPhoneVolume tigger volume : " + i2);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i2 < 5 || i2 > 39) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2), i2, 0);
        return 0;
    }

    public void setPowerMute(int i2) {
        if (this.mCarAudioControl == null || i2 < 0 || i2 > 12) {
            return;
        }
        Log.d(TAG, "setPowerMute tigger muteState :  " + i2);
        this.mCarAudioControl.setIntegerValue(15, i2);
    }

    public int setSoundEffect(int i2) {
        int i3;
        Log.d(TAG, "setSoundEffect tigger effect : " + i2);
        if (this.mCarEqualizer == null || i2 < 18 || i2 > 22) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundEffect conditions met");
        switch (i2) {
            case 18:
                i3 = 0;
                break;
            case 19:
                i3 = 1;
                break;
            case 20:
                i3 = 2;
                break;
            case 21:
                i3 = 3;
                break;
            case 22:
                i3 = 4;
                break;
            default:
                Log.d(TAG, "set effect is invalid" + i2);
                return -2147482648;
        }
        this.mCarEqualizer.usePreset(i3);
        return 0;
    }

    public int setSoundField(int i2, int i3) {
        Log.d(TAG, "setSoundField tigger x is : " + i2 + " y :" + i3);
        int iConvertMb = convertMb(i2, 100);
        int iConvertMb2 = convertMb(i3, 100);
        if (this.mCarEnhancedAudioManager == null || i2 < -9 || i2 > 9 || i3 < -9 || i3 > 9) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundField conditions met");
        this.mCarEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public int setSoundFieldPosition(int i2) {
        Log.d(TAG, "setSoundFieldPosition tigger type is : " + i2);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            int i3 = 1;
            if (i2 >= 1 && i2 <= 6) {
                switch (i2) {
                    case 1:
                        i3 = 0;
                        break;
                    case 2:
                        break;
                    case 3:
                        i3 = 2;
                        break;
                    case 4:
                        i3 = 3;
                        break;
                    case 5:
                        i3 = 4;
                        break;
                    case 6:
                        i3 = 5;
                        break;
                    default:
                        Log.d(TAG, "type is not found");
                        return -2147482648;
                }
                carAudioControl.setIntegerValue(3, i3);
                return 0;
            }
        }
        return -2147482648;
    }

    public int setVSPState(int i2) {
        Log.d(TAG, "setVSPState tigger value : " + i2);
        if (this.mCarAudioControl == null) {
            return -2147482648;
        }
        if (i2 != 1 && i2 != 0) {
            return -2147482648;
        }
        Log.d(TAG, "setVSPState start");
        this.mCarAudioControl.setIntegerValue(18, i2);
        return 0;
    }

    public int setVoiceAssistVolume(int i2) {
        Log.d(TAG, "setVoiceAssistVolume tigger volume : " + i2);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i2 < 5 || i2 > 24) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11), i2, 0);
        return 0;
    }

    public int setVolume(int i2) {
        Log.d(TAG, "media setVolume tigger volume is : " + i2);
        if (this.mCarAudioManager == null || i2 < 0 || i2 > 39) {
            return -2147482648;
        }
        Log.d(TAG, "setVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(1), i2, 0);
        return 0;
    }

    public int setVolumeSettingsource() {
        Log.d(TAG, "setVolumeSettingsource tigger ");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Log.d(TAG, "unregisterListener tigger");
        Objects.requireNonNull(absBYDAutoAudioListener);
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            if (arrayList.contains(absBYDAutoAudioListener)) {
                arrayList.remove(absBYDAutoAudioListener);
            }
        }
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] iArr) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener featureIDs tigger");
    }
}
