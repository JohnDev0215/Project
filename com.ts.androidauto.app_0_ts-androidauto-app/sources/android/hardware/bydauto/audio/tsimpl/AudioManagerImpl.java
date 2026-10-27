package android.hardware.bydauto.audio.tsimpl;

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

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    protected void onServiceConnected(final Car car) {
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
        if (this.mCarEnhancedAudioManager != null && this.mCarAudioManager != null) {
            this.mCarAudioControl = new CarAudioControl(this.mCar, this.mCarEnhancedAudioManager);
            this.mCarEqualizer = new CarEqualizer(this.mCar, this.mCarEnhancedAudioManager);
            Log.d(TAG, "onServiceConnected mCarAudioControl: " + this.mCarAudioControl + " mCarEqualizer : " + this.mCarEqualizer + " mCarEnhancedAudioManager :" + this.mCarEnhancedAudioManager);
            this.mCarAudioManager.registerCarVolumeCallback(this.mCarVolumeCallback);
            List<Integer> zoneIdList = this.mCarAudioManager.getAudioZoneIds();
            int[] zoneIdArray = zoneIdList.stream().mapToInt(new ToIntFunction() { // from class: android.hardware.bydauto.audio.tsimpl.-$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((Integer) obj).intValue();
                }
            }).toArray();
            this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, zoneIdArray);
            this.mCarEnhancedAudioManager.registerCarBalanceCallback(this.mCarBalanceCallback);
            this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
            this.mCarEqualizer.registerCarEqualizerCallback(this.mCarEqualizerCallback);
            this.mCarAudioControl.setCarAudioControlCallback(this.mCarAudioControlCallback);
            for (int index = 0; index <= 40; index++) {
                this.mCarAudioControl.checkAndUpdateCarAudioControlCallback(index, true);
                Log.d(TAG, "car audio control register index : " + index);
            }
            this.lastAllMuteStatus = getAllMute();
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    protected void onServiceDied() {
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

    AudioManagerImpl(Context con) {
        super(con);
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
            public void onGroupVolumeChanged(int zoneId, int groupId, int flags) {
                Log.d(AudioManagerImpl.TAG, "group volume change zoneId : " + zoneId + " greoupID : " + groupId + "flags : " + flags);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    AudioManagerImpl.this.notifyAudioVolumeChanged(listener, groupId, zoneId, flags);
                }
            }
        };
        this.mCarBalanceCallback = new CarEnhancedAudioManager.CarBalanceCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.2
            public void onBalanceTowardRightLevelChanged() {
                int[] iArr = new int[2];
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                Log.d(AudioManagerImpl.TAG, "balance value change balance : " + balanceFadePosition[0] + " fade : " + balanceFadePosition[1]);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener.onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }

            public void onFadeTowardFrontLevelChanged() {
                int[] iArr = new int[2];
                int[] balanceFadePosition = AudioManagerImpl.this.getBalanceFadePosition();
                Log.d(AudioManagerImpl.TAG, "fade value change balance : " + balanceFadePosition[0] + "fade : " + balanceFadePosition[1]);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener.onSoundFieldChanged(balanceFadePosition[0], balanceFadePosition[1]);
                }
            }
        };
        this.mCarGroupMutingCallback = new CarEnhancedAudioManager.CarGroupMutingCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.3
            public void onGroupMuteChanged(int zoneId, int groupId, int flags) {
                int allMuteStatus = AudioManagerImpl.this.getAllMute();
                Log.w(AudioManagerImpl.TAG, "audio all mute state is " + allMuteStatus + ", lastAllMuteStatus is " + AudioManagerImpl.this.lastAllMuteStatus);
                if (allMuteStatus != AudioManagerImpl.this.lastAllMuteStatus) {
                    for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                        listener.onAllMuteState(allMuteStatus);
                    }
                    AudioManagerImpl.this.lastAllMuteStatus = allMuteStatus;
                }
                if (groupId != AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 1)) {
                    if (groupId == AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 12)) {
                        Log.w(AudioManagerImpl.TAG, "audio Nav mute state is " + AudioManagerImpl.this.getNaviMuteState());
                        for (AbsBYDAutoAudioListener listener2 : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                            listener2.onNaviMuteStateChanged(AudioManagerImpl.this.getNaviMuteState());
                        }
                        return;
                    }
                    return;
                }
                Log.w(AudioManagerImpl.TAG, "audio Media mute state is " + AudioManagerImpl.this.getMuteState());
                for (AbsBYDAutoAudioListener listener3 : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener3.onMuteStateChanged(AudioManagerImpl.this.getMuteState());
                }
            }
        };
        this.mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.4
            public void onFocusChanged(int audioZoneId, List<AudioFocusInfo> focusHolders) {
                Log.d(AudioManagerImpl.TAG, "focus change focus holder list : " + focusHolders.toString());
                for (AudioFocusInfo audioFocusInfo : focusHolders) {
                    Log.v(AudioManagerImpl.TAG, "focus change focus holder usage : " + audioFocusInfo.getAttributes().getUsage());
                    int usage = audioFocusInfo.getAttributes().getUsage();
                    if (usage == 0 || usage == 1 || usage == 14 || usage == 24 || usage == 33) {
                        AudioManagerImpl.this.lastMediaPackage = audioFocusInfo.getPackageName();
                    }
                }
                Log.d(AudioManagerImpl.TAG, "focus change getPackageName is :" + AudioManagerImpl.this.lastMediaPackage);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener.onAudioFocusChanged(audioZoneId, focusHolders);
                }
            }
        };
        this.mCarEqualizerCallback = new CarEqualizer.CarEqualizerCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.5
            public void onPresetChanged() {
                int effect = AudioManagerImpl.this.getSoundEffect();
                Log.d(AudioManagerImpl.TAG, "preset Changed effect is : " + effect);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener.onSoundEffectChanged(effect);
                }
            }

            public void onBandLevelChanged(int band) {
                if (band == 0) {
                    band = 17;
                } else if (band == 1) {
                    band = 18;
                } else if (band == 2) {
                    band = 19;
                } else if (band == 3) {
                    band = 20;
                } else if (band == 4) {
                    band = 21;
                }
                int value = AudioManagerImpl.this.getEqualizer(band);
                Log.d(AudioManagerImpl.TAG, "band level changed band is : " + band + " value : " + value);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    listener.onEqualizerChanged(band, value);
                }
            }
        };
        this.mCarAudioControlCallback = new CarAudioControl.CarAudioControlCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.6
            public void onAudioControlChanged(int index) {
                Log.d(AudioManagerImpl.TAG, "audio control index is : " + index);
                for (AbsBYDAutoAudioListener listener : AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER) {
                    AudioManagerImpl.this.notifyAudioControlChanged(listener, index);
                }
            }
        };
        this.mContext = con;
        this.mTsManagerImpl = TsManagerImpl.getInstance(con);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioControlChanged(AbsBYDAutoAudioListener listener, int index) {
        Log.d(TAG, "notify Audio Control Changed index is : " + index);
        if (index == 3) {
            int type = getSoundFieldPositionType();
            Log.d(TAG, "audio control change INDEX_DYNA_SOUND_FIELD_FOCUS type : " + type);
            listener.onSoundFieldPositionChanged(type);
        }
        if (index == 18) {
            int vspState = this.mCarAudioControl.getIntegerValue(18, 0);
            Log.d(TAG, "audio control change INDEX_AVAS_STATE vspState : " + vspState);
            listener.onVSPStateChanged(vspState);
            return;
        }
        if (index == 5) {
            int manufacturer = sAudioManagerImpl.getAudioDeviceManufacturer();
            int gear = this.mCarAudioControl.getIntegerValue(5, 4);
            Log.d(TAG, "audio control change INDEX_DYNA_VSC manufacturer : " + manufacturer + "gear : " + gear);
            listener.onGearSpeedChanged(manufacturer, gear);
            return;
        }
        if (index == 6) {
            int value = this.mCarAudioControl.getIntegerValue(6, 1);
            Log.d(TAG, "audio control change INDEX_NAV_MIX_MEDIA value : " + value);
            listener.onNavigationMediaStateChanged(value);
            return;
        }
        if (index == 10) {
            int ludnessType = this.mCarAudioControl.getIntegerValue(10, 0);
            Log.d(TAG, "audio control change INDEX_DIRAC_LIVE_LOUDNESS ludnessType : " + ludnessType);
            listener.onLudnessTypeChange(ludnessType);
            return;
        }
        if (index == 11) {
            int micMuteState = this.engineSoundType;
            Log.d(TAG, "audio control change INDEX_AVAS_MODE avasTypeValue : " + micMuteState);
            listener.onEngineSoundTypeChanged(micMuteState);
            return;
        }
        if (index == 14) {
            int micMuteState2 = this.mCarAudioControl.getIntegerValue(14, 0);
            Log.d(TAG, "audio control change INDEX_MIC_MUTE_TYPE micMuteState : " + micMuteState2);
            listener.onBtCall_MicMuteChanged(micMuteState2);
            return;
        }
        if (index == 15) {
            int powerMuteState = this.mCarAudioControl.getIntegerValue(15, 0);
            Log.d(TAG, "audio control change INDEX_POWER_MUTE_TYPE powerMuteState : " + powerMuteState);
            listener.onPowerMuteStateChange(powerMuteState);
            return;
        }
        switch (index) {
            case 23:
                Log.d(TAG, "audio control change chimesVoiceState: " + this.chimesVoiceState);
                listener.onCarTipsVoiceStateChanged(0, this.chimesVoiceState);
                break;
            case 24:
                Log.d(TAG, "audio control change radarVoiceState: " + this.radarVoiceState);
                listener.onCarTipsVoiceStateChanged(1, this.radarVoiceState);
                break;
            case 25:
                Log.d(TAG, "audio control change adasVoiceState: " + this.adasVoiceState);
                listener.onCarTipsVoiceStateChanged(2, this.adasVoiceState);
                break;
            case 26:
                Log.d(TAG, "audio control change chimesVolGain: " + index + ", value = " + this.chimesVolGain);
                listener.onChimesVolGainChanged(0, this.chimesVolGain);
                break;
            case 27:
                Log.d(TAG, "audio control change radarVolGain: " + index + ", value = " + this.radarVolGain);
                listener.onChimesVolGainChanged(1, this.radarVolGain);
                break;
            case 28:
                Log.d(TAG, "audio control change adasVolGain: " + index + ", value = " + this.adasVolGain);
                listener.onChimesVolGainChanged(2, this.adasVolGain);
                break;
            default:
                switch (index) {
                    case 33:
                        int loudnessState = this.mCarAudioControl.getIntegerValue(33, 0);
                        Log.d(TAG, "audio control change loudnessState: " + index + ", value = " + loudnessState);
                        listener.onLoudnessStateChanged(loudnessState);
                        break;
                    case 34:
                    case 35:
                        int balance = this.mCarAudioControl.getIntegerValue(34, 0);
                        int fade = this.mCarAudioControl.getIntegerValue(35, 0);
                        Log.d(TAG, "audio control change soundField: " + index + ", value balance = " + balance + ", fade = " + fade);
                        listener.onGeneralSoundFieldChanged(this.soundFieldManufactor, balance, fade);
                        break;
                    case 36:
                        int equalizer0Value = this.mCarAudioControl.getIntegerValue(36, 0);
                        Log.d(TAG, "audio control change equalizer: " + index + ", value = " + equalizer0Value);
                        listener.onGeneralEqualizerChanged(this.equalizerManufactor, 0, equalizer0Value);
                        break;
                    case 37:
                        int equalizer1Value = this.mCarAudioControl.getIntegerValue(37, 0);
                        Log.d(TAG, "audio control change equalizer: " + index + ", value = " + equalizer1Value);
                        listener.onGeneralEqualizerChanged(this.equalizerManufactor, 1, equalizer1Value);
                        break;
                    case 38:
                        int equalizer2Value = this.mCarAudioControl.getIntegerValue(38, 0);
                        Log.d(TAG, "audio control change equalizer: " + index + ", value = " + equalizer2Value);
                        listener.onGeneralEqualizerChanged(this.equalizerManufactor, 2, equalizer2Value);
                        break;
                    case 39:
                        int equalizer3Value = this.mCarAudioControl.getIntegerValue(39, 0);
                        Log.d(TAG, "audio control change equalizer: " + index + ", value = " + equalizer3Value);
                        listener.onGeneralEqualizerChanged(this.equalizerManufactor, 3, equalizer3Value);
                        break;
                    case 40:
                        int equalizer4Value = this.mCarAudioControl.getIntegerValue(40, 0);
                        Log.d(TAG, "audio control change equalizer: " + index + ", value = " + equalizer4Value);
                        listener.onGeneralEqualizerChanged(this.equalizerManufactor, 4, equalizer4Value);
                        break;
                    default:
                        Log.e(TAG, "notifyAudioControlChanged index is invalid");
                        break;
                }
                break;
        }
    }

    public void notifyAudioVolumeChanged(AbsBYDAutoAudioListener listener, int groupId, int zoneId, int flags) {
        int value = this.mCarAudioManager.getGroupVolume(zoneId, groupId);
        int mediaId = this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 1);
        int navId = this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 12);
        int voiceId = this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 11);
        int phoneId = this.mCarAudioManager.getVolumeGroupIdForUsage(zoneId, 2);
        if (groupId == mediaId) {
            listener.onVolumeChanged(value, flags);
            return;
        }
        if (groupId == navId) {
            listener.onNaviVolumeChanged(value);
        } else if (groupId == voiceId) {
            listener.onVoiceAssistVolume(value);
        } else if (groupId == phoneId) {
            listener.onPhoneVolume(value);
        }
    }

    public static AudioManagerImpl getInstance(Context con) {
        if (sAudioManagerImpl == null) {
            synchronized (AudioManagerImpl.class) {
                if (sAudioManagerImpl == null) {
                    Log.d(TAG, "AudioManagerImpl equal null ");
                    sAudioManagerImpl = new AudioManagerImpl(con);
                }
            }
        }
        Log.d(TAG, "getInstance to do ");
        return sAudioManagerImpl;
    }

    public int getDevicetype() {
        Log.d(TAG, "getDevicetype tigger ");
        return 1002;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission tigger ");
        return BYDAutoAudioDevice.AUDIO_SET_PERM;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission tigger");
        return BYDAutoAudioDevice.AUDIO_GET_PERM;
    }

    public int setVolume(int value) {
        Log.d(TAG, "media setVolume tigger volume is : " + value);
        if (this.mCarAudioManager != null && value >= 0 && value <= 39) {
            Log.d(TAG, "setVolume conditions met");
            int mediaGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(1);
            this.mCarAudioManager.setGroupVolume(mediaGroupId, value, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getVolume() {
        Log.d(TAG, "media getVolume tigger ");
        if (this.mCarAudioManager != null) {
            Log.d(TAG, "getVolume conditions met");
            int mediaGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(1);
            int volume = this.mCarAudioManager.getGroupVolume(mediaGroupId);
            return volume;
        }
        return 0;
    }

    public int setNaviVolume(int value) {
        Log.d(TAG, "nav setNaviVolume tigger volume is : " + value);
        if (this.mCarAudioManager != null && value >= 0 && value <= 10) {
            Log.d(TAG, "setNaviVolume conditions met");
            int naviGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(12);
            this.mCarAudioManager.setGroupVolume(naviGroupId, value, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getNaviVolume() {
        Log.d(TAG, "nav getNaviVolume tigger ");
        if (this.mCarAudioManager != null) {
            Log.d(TAG, "getNaviVolume conditions met");
            int naviGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(12);
            int volume = this.mCarAudioManager.getGroupVolume(naviGroupId);
            return volume;
        }
        return 0;
    }

    public int setMuteState(int state) {
        Log.d(TAG, "media setMuteState tigger state is : " + state + "getPowerMute is : " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || state < 0) {
            return -2147482648;
        }
        if (state <= 1) {
            Log.d(TAG, "setMuteState conditions met");
            int mediaGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(1);
            this.mCarEnhancedAudioManager.setVolumeGroupMute(0, mediaGroupId, state == 1, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getMuteState() {
        Log.d(TAG, "media getMuteState tigger ");
        if (this.mCarEnhancedAudioManager != null) {
            Log.d(TAG, "getMuteState conditions met");
            return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1)) ? 1 : 0;
        }
        return 0;
    }

    public int setNaviMuteState(int state) {
        Log.d(TAG, "setNaviMuteState tigger state is : " + state + "getPowerMute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager != null && getPowerMute() == 10 && state >= 2 && state <= 3) {
            Log.d(TAG, "setNaviMuteState conditions met");
            int naviGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(12);
            this.mCarEnhancedAudioManager.setVolumeGroupMute(0, naviGroupId, state == 2, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getNaviMuteState() {
        Log.d(TAG, "getNaviMuteState tigger ");
        if (this.mCarEnhancedAudioManager != null) {
            Log.d(TAG, "getNaviMuteState conditions met");
            int naviGroupId = this.mCarAudioManager.getVolumeGroupIdForUsage(12);
            boolean state = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, naviGroupId);
            return state ? 2 : 3;
        }
        return 3;
    }

    public int setVolumeSettingsource() {
        Log.d(TAG, "setVolumeSettingsource tigger ");
        return 0;
    }

    public int getVolumeSettingSource() {
        Log.d(TAG, "getVolumeSettingsource tigger");
        return 0;
    }

    public int setSoundField(int x, int y) {
        Log.d(TAG, "setSoundField tigger x is : " + x + " y :" + y);
        int balanceLevel = convertMb(x, 100);
        int fadeLevel = convertMb(y, 100);
        if (this.mCarEnhancedAudioManager != null && x >= -9 && x <= 9 && y >= -9 && y <= 9) {
            Log.d(TAG, "setSoundField conditions met");
            this.mCarEnhancedAudioManager.setBalanceTowardRightLevel(balanceLevel);
            this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(fadeLevel);
            return 0;
        }
        return -2147482648;
    }

    public int[] getSoundField() {
        Log.d(TAG, "getSoundField tigger ");
        int[] balanceFadePosition = new int[2];
        if (this.mCarEnhancedAudioManager != null) {
            Log.d(TAG, "setSoundField conditions met");
            int balanceLevel = this.mCarEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            balanceFadePosition[0] = convertDb(balanceLevel, 100);
            balanceFadePosition[1] = convertDb(fadeLevel, 100);
            Log.d(TAG, "BYDAudio getSoundField balance is : " + balanceFadePosition[0]);
            Log.d(TAG, "BYDAudio getSoundField fade is : " + balanceFadePosition[1]);
        }
        return balanceFadePosition;
    }

    public int setSoundEffect(int effect) {
        int effect2;
        Log.d(TAG, "setSoundEffect tigger effect : " + effect);
        if (this.mCarEqualizer == null || effect < 18 || effect > 22) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundEffect conditions met");
        switch (effect) {
            case 18:
                effect2 = 0;
                break;
            case 19:
                effect2 = 1;
                break;
            case 20:
                effect2 = 2;
                break;
            case 21:
                effect2 = 3;
                break;
            case 22:
                effect2 = 4;
                break;
            default:
                Log.d(TAG, "set effect is invalid" + effect);
                return -2147482648;
        }
        this.mCarEqualizer.usePreset(effect2);
        return 0;
    }

    public int getSoundEffect() {
        Log.d(TAG, "getSoundEffect tigger");
        int effect = 0;
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer != null) {
            int soundEffect = carEqualizer.getCurrentPreset();
            Log.d(TAG, "getSoundEffect conditions met" + soundEffect);
            if (soundEffect == 0) {
                effect = 18;
            } else if (soundEffect == 1) {
                effect = 19;
            } else if (soundEffect == 2) {
                effect = 20;
            } else if (soundEffect == 3) {
                effect = 21;
            } else if (soundEffect == 4) {
                effect = 22;
            } else {
                Log.d(TAG, "get effect is invalid0");
                return 0;
            }
        }
        Log.d(TAG, "getSoundEffect success : " + effect);
        return effect;
    }

    public int setDiracLiveState(int state) {
        Log.d(TAG, "setDiracLiveState tigger ");
        return 0;
    }

    public int getDiracLiveState() {
        Log.d(TAG, "getDiracLiveState tigger ");
        return 0;
    }

    public int setLoudnessState(int state) {
        Log.d(TAG, "setLoudnessState tigger state is : " + state);
        if (this.mCarAudioControl != null && state >= 0 && state <= 1) {
            Log.d(TAG, "setLoudnessState conditions met");
            this.mCarAudioControl.setIntegerValue(33, state);
            return 0;
        }
        return -2147482648;
    }

    public int getLoudnessState() {
        Log.d(TAG, "getLoudnessState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int loudnessState = carAudioControl.getIntegerValue(33, 0);
        Log.d(TAG, "getLoudnessState loudnessState: " + loudnessState);
        return loudnessState;
    }

    public int setEqualizer(int frequencyNum, int value) {
        int frequencyNum2;
        Log.d(TAG, "setEqualizer tigger frequencyNum : " + frequencyNum + " value: " + value);
        if (this.mCarEqualizer == null || frequencyNum < 17 || frequencyNum > 21 || value < -9 || value > 9) {
            return -2147482648;
        }
        switch (frequencyNum) {
            case 17:
                frequencyNum2 = 0;
                break;
            case 18:
                frequencyNum2 = 1;
                break;
            case 19:
                frequencyNum2 = 2;
                break;
            case 20:
                frequencyNum2 = 3;
                break;
            case 21:
                frequencyNum2 = 4;
                break;
            default:
                Log.d(TAG, "set frequencyNum is not founded" + frequencyNum);
                return -2147482648;
        }
        int bmtLevel = convertMb(value, 100);
        Log.d(TAG, "setEqualizer successed  frequencyNum :" + frequencyNum2 + "value : " + bmtLevel);
        this.mCarEqualizer.setBandLevel(frequencyNum2, bmtLevel);
        return 0;
    }

    public int getEqualizer(int frequencyNum) {
        int frequencyNum2;
        Log.d(TAG, "getEqualizer tigger frequencyNum is : " + frequencyNum);
        int bmtLevel = 0;
        if (this.mCarEqualizer != null && frequencyNum >= 17 && frequencyNum <= 21) {
            switch (frequencyNum) {
                case 17:
                    frequencyNum2 = 0;
                    break;
                case 18:
                    frequencyNum2 = 1;
                    break;
                case 19:
                    frequencyNum2 = 2;
                    break;
                case 20:
                    frequencyNum2 = 3;
                    break;
                case 21:
                    frequencyNum2 = 4;
                    break;
                default:
                    Log.d(TAG, "get frequencyNum is not founded" + frequencyNum);
                    return 0;
            }
            int dynaEqualizertype = this.mCarEqualizer.getBandLevel(frequencyNum2);
            bmtLevel = convertDb(dynaEqualizertype, 100);
        }
        Log.d(TAG, "getEqualizer successed : " + bmtLevel);
        return bmtLevel;
    }

    public int getLoudspeakerNum() {
        Log.d(TAG, "getLoudspeakerNum tigger ");
        return 0;
    }

    public int setAudioMicAmpGain(int value) {
        Log.d(TAG, "setAudioMicAmpGain tigger ");
        return 0;
    }

    public int getAudioMicAmpGain() {
        Log.d(TAG, "getAudioMicAmpGain tigger ");
        return 0;
    }

    public int getMusicChangeSource() {
        Log.d(TAG, "getMusicChangeSource tigger");
        return 1;
    }

    public int setMusicChangeSource(int source) {
        Log.d(TAG, "setMusicChangeSource tigger");
        return 0;
    }

    public int reset(int item, int value) {
        CarEnhancedAudioManager carEnhancedAudioManager;
        Log.d(TAG, "reset tigger item is : " + item + " value : " + value);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer == null || (carEnhancedAudioManager = this.mCarEnhancedAudioManager) == null || item < 0 || item > 2 || value < 0 || value > 1) {
            return -2147482648;
        }
        if (value != 1) {
            return value == 0 ? -2147482645 : -2147482648;
        }
        if (item == 0) {
            for (int band = 0; band < this.mCarEqualizer.getNumberOfBands(); band++) {
                this.mCarEqualizer.setBandLevel(band, 0);
            }
        } else if (item == 1) {
            carEqualizer.usePreset(1);
        } else if (item == 2) {
            carEnhancedAudioManager.setBalanceTowardRightLevel(0);
            this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(0);
        }
        return 0;
    }

    public int getDiracLiveMode() {
        Log.d(TAG, "getDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int diracLiveMode = carAudioControl.getIntegerValue(2, 2);
        return diracLiveMode;
    }

    public int setDiracLiveMode(int mode) {
        Log.d(TAG, "setDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null && mode >= 1 && mode <= 3) {
            carAudioControl.setIntegerValue(2, mode);
            return 0;
        }
        return -2147482648;
    }

    public boolean postEvent(int device_type, int event_type, int value, Object data) {
        Log.d(TAG, "postEvent tigger ");
        return true;
    }

    public int getType() {
        Log.d(TAG, "getType tigger ");
        return 1002;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList tigger ");
        return new int[0];
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener tigger");
        Objects.requireNonNull(absBYDAutoAudioListener);
        synchronized (BYD_AUTO_AUDIO_LISTENER) {
            if (!BYD_AUTO_AUDIO_LISTENER.contains(absBYDAutoAudioListener)) {
                BYD_AUTO_AUDIO_LISTENER.add(absBYDAutoAudioListener);
            }
        }
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] featureIDs) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener featureIDs tigger");
    }

    public void unregisterListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Log.d(TAG, "unregisterListener tigger");
        Objects.requireNonNull(absBYDAutoAudioListener);
        synchronized (BYD_AUTO_AUDIO_LISTENER) {
            if (BYD_AUTO_AUDIO_LISTENER.contains(absBYDAutoAudioListener)) {
                BYD_AUTO_AUDIO_LISTENER.remove(absBYDAutoAudioListener);
            }
        }
    }

    public int getAudioDeviceManufacturer() {
        Log.d(TAG, "getAudioDeviceManufacturer tigger");
        return 2;
    }

    public int getAudioDeviceConfig() {
        Log.d(TAG, "getAudioDeviceConfig tigger");
        int dviceConfig = 0;
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            if (carAudioControl.getIntegerValue(4, 0) == 0) {
                dviceConfig = 17;
            }
            Log.d(TAG, "getAudioDeviceConfig successed :" + dviceConfig);
        }
        return dviceConfig;
    }

    public int getDiracPerformanceMode() {
        Log.d(TAG, "getDiracPerformanceMode tigger");
        int diracPerformanceMode = 0;
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            if (carAudioControl.getIntegerValue(1, 1) == 1) {
                diracPerformanceMode = 1;
            }
            Log.d(TAG, "getDiracPerformanceMode successed :" + diracPerformanceMode);
        }
        return diracPerformanceMode;
    }

    public int setDiracPerformanceMode(int mode) {
        Log.d(TAG, "setDiracPerformanceMode tigger mode is : " + mode);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null && mode >= 1 && mode <= 3) {
            carAudioControl.setIntegerValue(1, mode);
            return 0;
        }
        return -2147482648;
    }

    public int getGearBySpeed() {
        Log.d(TAG, "getGearBySpeed tigger");
        int gearValue = 0;
        if (this.mCarAudioControl != null) {
            int speedType = sAudioManagerImpl.getAudioDeviceManufacturer();
            if (speedType == 2) {
                gearValue = this.mCarAudioControl.getIntegerValue(5, 4);
            }
        }
        Log.d(TAG, "getGearBySpeed successed : " + gearValue);
        return gearValue;
    }

    public int setGearBySpeed(int gear) {
        Log.d(TAG, "setGearBySpeed tigger");
        if (this.mCarAudioControl == null || gear < 0 || gear > 7) {
            return -2147482648;
        }
        int speedType = sAudioManagerImpl.getAudioDeviceManufacturer();
        if (speedType != 2) {
            Log.d(TAG, "speedType is not founded");
            return -2147482648;
        }
        this.mCarAudioControl.setIntegerValue(5, gear);
        return 0;
    }

    public int getSoundFieldPositionType() {
        Log.d(TAG, "getSoundFieldPositionType tigger");
        int type = 1;
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            int soundFieldType = carAudioControl.getIntegerValue(3, 1);
            if (soundFieldType == 0) {
                type = 1;
            } else if (soundFieldType == 1) {
                type = 2;
            } else if (soundFieldType != 2) {
                if (soundFieldType == 3) {
                    type = 4;
                } else if (soundFieldType == 4) {
                    type = 5;
                } else if (soundFieldType == 5) {
                    type = 6;
                }
            } else {
                type = 3;
            }
        }
        Log.d(TAG, "getSoundFieldPositionType success :" + type);
        return type;
    }

    public int setSoundFieldPosition(int type) {
        int type2;
        Log.d(TAG, "setSoundFieldPosition tigger type is : " + type);
        if (this.mCarAudioControl == null || type < 1 || type > 6) {
            return -2147482648;
        }
        switch (type) {
            case 1:
                type2 = 0;
                break;
            case 2:
                type2 = 1;
                break;
            case 3:
                type2 = 2;
                break;
            case 4:
                type2 = 3;
                break;
            case 5:
                type2 = 4;
                break;
            case 6:
                type2 = 5;
                break;
            default:
                Log.d(TAG, "type is not found");
                return -2147482648;
        }
        this.mCarAudioControl.setIntegerValue(3, type2);
        return 0;
    }

    public int[] getBalanceFadePosition() {
        Log.d(TAG, "getBalanceFadePosition tigger ");
        int[] balanceFadePosition = new int[2];
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null) {
            int balanceLevel = carEnhancedAudioManager.getBalanceTowardRightLevel();
            int fadeLevel = this.mCarEnhancedAudioManager.getFadeTowardFrontLevel();
            balanceFadePosition[0] = convertDb(balanceLevel, 100);
            balanceFadePosition[1] = convertDb(fadeLevel, 100);
            Log.d(TAG, "BYDAudio getBalanceFadePosition balance is : " + balanceFadePosition[0]);
            Log.d(TAG, "BYDAudio getBalanceFadePosition fade is : " + balanceFadePosition[1]);
        }
        return balanceFadePosition;
    }

    public int setBalanceFadePosition(int balance, int fade) {
        Log.d(TAG, "setBalanceFadePosition tigger balance : " + balance + " fade : " + fade);
        int balanceLevel = convertMb(balance, 100);
        int fadeLevel = convertMb(fade, 100);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null && balanceLevel >= -9 && balanceLevel <= 9 && fadeLevel >= -9 && fadeLevel <= 9) {
            carEnhancedAudioManager.setBalanceTowardRightLevel(balanceLevel);
            this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(fadeLevel);
            return 0;
        }
        return -2147482648;
    }

    public int getNavigationMediaState() {
        Log.d(TAG, "getNavigationMediaState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 1;
        }
        int navigationMediaState = carAudioControl.getIntegerValue(6, 1);
        return navigationMediaState;
    }

    public int setNavigationMediaState(int value) {
        Log.d(TAG, "setNavigationMediaState tigger value is " + value);
        if (this.mCarAudioControl != null && value >= 0 && value <= 3) {
            if (value >= 0 || value <= 3) {
                this.mCarAudioControl.setIntegerValue(6, value);
                return 0;
            }
            return -2147482648;
        }
        return -2147482648;
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo(int zoneId) {
        Log.d(TAG, "getCurAudioFocusInfo tigger zoneId : " + zoneId);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager != null && zoneId == 0) {
            try {
                return carEnhancedAudioManager.getAudioFocusHoldersForZoneId(zoneId);
            } catch (IllegalArgumentException e) {
                Log.d(TAG, "zoneId is out of range ");
                return null;
            }
        }
        return null;
    }

    public int setVoiceAssistVolume(int volume) {
        Log.d(TAG, "setVoiceAssistVolume tigger volume : " + volume);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null && volume >= 5 && volume <= 24) {
            int voiceAssistId = carAudioManager.getVolumeGroupIdForUsage(11);
            this.mCarAudioManager.setGroupVolume(voiceAssistId, volume, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getVoiceAssistVolume() {
        Log.d(TAG, "getVoiceAssistVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        int voiceAssistId = carAudioManager.getVolumeGroupIdForUsage(11);
        int volume = this.mCarAudioManager.getGroupVolume(voiceAssistId);
        return volume;
    }

    public int setPhoneVolume(int volume) {
        Log.d(TAG, "setPhoneVolume tigger volume : " + volume);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager != null && volume >= 5 && volume <= 39) {
            int phoneId = carAudioManager.getVolumeGroupIdForUsage(2);
            this.mCarAudioManager.setGroupVolume(phoneId, volume, 0);
            return 0;
        }
        return -2147482648;
    }

    public int getPhoneVolume() {
        Log.d(TAG, "getPhoneVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        int phoneId = carAudioManager.getVolumeGroupIdForUsage(2);
        int volume = this.mCarAudioManager.getGroupVolume(phoneId);
        return volume;
    }

    public int setEngineSoundType(int value) {
        Log.d(TAG, "setEngineSoundType tigger volume : " + value);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null && value >= 0 && value <= 6) {
            this.engineSoundType = value;
            carAudioControl.setIntegerValue(11, value);
            return 0;
        }
        return -2147482648;
    }

    public int getEngineSoundType() {
        Log.d(TAG, "getEngineSoundType tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 2;
        }
        int engineSoundType = carAudioControl.getIntegerValue(11, 2);
        return engineSoundType;
    }

    public int setVSPState(int state) {
        Log.d(TAG, "setVSPState tigger value : " + state);
        if (this.mCarAudioControl != null) {
            if (state == 1 || state == 0) {
                Log.d(TAG, "setVSPState start");
                this.mCarAudioControl.setIntegerValue(18, state);
                return 0;
            }
            return -2147482648;
        }
        return -2147482648;
    }

    public int getVSPState() {
        Log.d(TAG, "getVSPState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int vspState = carAudioControl.getIntegerValue(18, 0);
        Log.d(TAG, "getVSPState tigger success : " + vspState);
        return vspState;
    }

    public int setKtvMode(int mode) {
        Log.d(TAG, "setKTVMode tigger mode : " + mode);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null && mode >= 0 && mode <= 1) {
            carAudioControl.setIntegerValue(12, mode);
            return 0;
        }
        return -2147482648;
    }

    public int getKtvMode() {
        Log.d(TAG, "getKTVMode tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int ktvMode = carAudioControl.getIntegerValue(12, 0);
        return ktvMode;
    }

    public String getLastMediaPackage() {
        Log.d(TAG, "getLastMediaPackage tigger ");
        String str = this.lastMediaPackage;
        if (str != null) {
            return str;
        }
        return "lastMediaPackage is null";
    }

    public int setAllMute(int state) {
        Log.d(TAG, "setAllMute tigger state : " + state + "getpowermute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager != null && getPowerMute() == 10 && state >= 0 && state <= 1) {
            int maxAllMuteId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
            if (state == 0) {
                for (int groupIndex = 0; groupIndex < maxAllMuteId; groupIndex++) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex, false, 0);
                    Log.d(TAG, "BYDAudio setAllMute MUTE_DISABLE muteId = " + groupIndex);
                }
            } else if (state == 1) {
                for (int groupIndex2 = 0; groupIndex2 < maxAllMuteId; groupIndex2++) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, groupIndex2, true, 0);
                    Log.d(TAG, "BYDAudio setAllMute MUTE_ENABLE muteId = " + groupIndex2);
                }
            }
            return 0;
        }
        return -2147482648;
    }

    public int getAllMute() {
        Log.d(TAG, "getAllMute tigger ");
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return 0;
        }
        ArrayList<Integer> getAllMuteGroupArray = new ArrayList<>();
        int maxAllMuteId = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        for (int groupIndex = 0; groupIndex < maxAllMuteId; groupIndex++) {
            boolean getAllMuetState = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, groupIndex);
            Log.d(TAG, "getAllMute groupIndex is : " + groupIndex + " state is :" + getAllMuetState);
            if (getAllMuetState) {
                Log.d(TAG, "getAllMute state is enable");
                getAllMuteGroupArray.add(1);
            } else {
                Log.d(TAG, "getAllMute state is disable");
                getAllMuteGroupArray.add(0);
            }
        }
        if (getAllMuteGroupArray.contains(0)) {
            Log.d(TAG, "getAllMuteGroupArray contains disable");
            return 0;
        }
        Log.d(TAG, "getAllMuteGroupArray contains enable");
        return 1;
    }

    public void requestEepromAmpType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            carAudioControl.setIntegerValue(13, 0);
            int ampTypeValue = this.mCarAudioControl.getIntegerValue(13, 0);
            Log.d(TAG, "requestEepromAmpType tigger get is : " + ampTypeValue);
            for (AbsBYDAutoAudioListener listener : BYD_AUTO_AUDIO_LISTENER) {
                listener.onEepromAmpTypeChanged(ampTypeValue);
            }
        }
    }

    public void setLoudness(int type) {
        if (this.mCarAudioControl != null && type >= 0 && type <= 8) {
            Log.d(TAG, "setludness tigger type :  " + type);
            this.mCarAudioControl.setIntegerValue(10, type);
        }
    }

    public int getLoudness() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int loudnessType = carAudioControl.getIntegerValue(10, 0);
        Log.d(TAG, "getloudnessType successed :  " + loudnessType);
        return loudnessType;
    }

    public void setBtCall_MicMute(int state) {
        if (this.mCarAudioControl != null && state >= 0 && state <= 1) {
            Log.d(TAG, "set set Bt Call MicMute" + state);
            this.mCarAudioControl.setIntegerValue(14, state);
        }
    }

    public int getBtCall_MicMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int micState = carAudioControl.getIntegerValue(14, 0);
        Log.d(TAG, "getmicState tigger state :  " + micState);
        return micState;
    }

    public void setPowerMute(int muteState) {
        if (this.mCarAudioControl != null && muteState >= 0 && muteState <= 12) {
            Log.d(TAG, "setPowerMute tigger muteState :  " + muteState);
            this.mCarAudioControl.setIntegerValue(15, muteState);
        }
    }

    public int getPowerMute() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null) {
            return 0;
        }
        int muteState = carAudioControl.getIntegerValue(15, 0);
        Log.d(TAG, "getmuteState tigger state :  " + muteState);
        return muteState;
    }

    public void setCarPlayAltActive(boolean z) {
        if (this.mCarAudioControl != null && getAllMute() == 0) {
            Log.d(TAG, "setCarPlayAltActive tigger state :  " + z);
            this.mCarAudioControl.setIntegerValue(16, z ? 1 : 0);
        }
        Log.d(TAG, "setCarPlayAltActive setall mute state is true :  " + getAllMute());
    }

    public void setCallMuteState(int state) {
        Log.d(TAG, "setCallMuteState state:  " + state + "getPowerMute is " + getPowerMute());
        if (this.mCarAudioControl != null && getPowerMute() == 10 && state >= 0 && state <= 1) {
            Log.d(TAG, "setCallMuteState tigger state :  " + state);
            this.mCarAudioControl.setIntegerValue(17, state);
        }
    }

    public int getCallMuteState() {
        if (this.mCarAudioControl == null) {
            return 0;
        }
        Log.d(TAG, "getCallMuteState tigger state is " + this.mCarAudioControl.getIntegerValue(17, 0));
        int callMute = this.mCarAudioControl.getIntegerValue(17, 0);
        return callMute;
    }

    private void enforcePermission(String permissionName) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(permissionName) != 0) {
            throw new SecurityException("requires permission " + permissionName);
        }
    }

    private int convertMb(int value, int step) {
        return value * step;
    }

    private int convertDb(int value, int step) {
        return value / step;
    }

    public int setKtvResource(int state) {
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "setKtvResource tiggert");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setKtvResource(state);
    }

    public int getGroupMinVolume(int groupId) {
        if (this.mCarAudioManager != null && groupId >= 0 && groupId <= 5) {
            Log.d(TAG, "sgetGroupMinVolume tigger groupID : " + groupId);
            return this.mCarAudioManager.getGroupMinVolume(groupId);
        }
        Log.e(TAG, "set getGroupMinVolume failed");
        return 0;
    }

    public int getGroupMaxVolume(int groupId) {
        if (this.mCarAudioManager != null && groupId >= 0 && groupId <= 5) {
            Log.d(TAG, "getGroupMaxVolume groupID : " + groupId);
            return this.mCarAudioManager.getGroupMaxVolume(groupId);
        }
        Log.e(TAG, "set getGroupMaxVolume failed");
        return 0;
    }

    public int getVolumeGroupIdForUsage(int usage) {
        if (this.mCarAudioManager != null && usage >= 0 && usage <= 36) {
            Log.d(TAG, "getVolumeGroupIdForUsage usage : " + usage);
            return this.mCarAudioManager.getVolumeGroupIdForUsage(usage);
        }
        Log.e(TAG, "set getVolumeGroupIdForUsage failed");
        return 0;
    }

    public int setAudioParam(int dspMic12Gain, int dspMic34Gain, int dspMic56Gain, int dspMicRefGain) {
        Log.d(TAG, "setAudioParam dspMIC12Gain = " + dspMic12Gain + "dspMIC34Gain = " + dspMic34Gain + ", dspMIC56Gain = " + dspMic56Gain + ", dspMICXFRefGain = " + dspMicRefGain);
        if (this.mCarAudioControl != null && dspMic12Gain >= -32 && dspMic12Gain <= 32 && dspMic34Gain >= -32 && dspMic34Gain <= 32 && dspMic56Gain >= -32 && dspMic56Gain <= 32 && dspMicRefGain >= -32 && dspMicRefGain <= 32) {
            Log.d(TAG, "setAudioParam tigger");
            this.mCarAudioControl.setIntegerValue(19, dspMic12Gain);
            this.mCarAudioControl.setIntegerValue(20, dspMic34Gain);
            this.mCarAudioControl.setIntegerValue(21, dspMic56Gain);
            this.mCarAudioControl.setIntegerValue(22, dspMicRefGain);
            return 0;
        }
        return -2147482648;
    }

    public int setCarTipsVoiceState(int type, int state) {
        Log.d(TAG, "setCarTipsVoiceState type = " + type + ", state = " + state);
        if (this.mCarAudioControl != null && state >= 0 && state <= 3 && type >= 0 && type <= 2) {
            Log.d(TAG, "setCarTipsVoiceState tigger");
            if (type == 0) {
                type = 23;
                this.chimesVoiceState = state;
            } else if (type == 1) {
                type = 24;
                this.radarVoiceState = state;
            } else if (type == 2) {
                type = 25;
                this.adasVoiceState = state;
            }
            this.mCarAudioControl.setIntegerValue(type, state);
            return 0;
        }
        return -2147482648;
    }

    public int getCarTipsVoiceState(int type) {
        Log.d(TAG, "getCarTipsVoiceState type = " + type);
        if (this.mCarAudioControl != null && type >= 0 && type <= 2) {
            Log.d(TAG, "getCarTipsVoiceState tigger");
            if (type == 0) {
                type = 23;
            } else if (type == 1) {
                type = 24;
            } else if (type == 2) {
                type = 25;
            }
            return this.mCarAudioControl.getIntegerValue(type, 0);
        }
        Log.e(TAG, "getCarTipsVoiceState failed");
        return 0;
    }

    public int setChimesVolGain(int chimeType, float value) {
        Log.d(TAG, "setChimesVolGain chimeType = " + chimeType + ", value = " + value);
        if (this.mCarAudioControl != null && chimeType >= 0 && chimeType <= 2 && value >= -40.0f && value <= 40.0f) {
            Log.d(TAG, "setChimesVolGain tigger");
            if (chimeType == 0) {
                chimeType = 26;
                this.chimesVolGain = value;
            } else if (chimeType == 1) {
                chimeType = 27;
                this.radarVolGain = value;
            } else if (chimeType == 2) {
                chimeType = 28;
                this.adasVolGain = value;
            }
            this.mCarAudioControl.setFloatValue(chimeType, value);
            return 0;
        }
        return -2147482648;
    }

    public float getChimesVolGain(int chimeType) {
        Log.d(TAG, "getChimesVolGain chimeType = " + chimeType);
        if (chimeType >= 0 && chimeType <= 2) {
            Log.d(TAG, "getChimesVolGain tigger");
            if (chimeType == 0) {
                return this.chimesVolGain;
            }
            if (chimeType == 1) {
                return this.radarVolGain;
            }
            if (chimeType == 2) {
                return this.adasVolGain;
            }
        }
        Log.e(TAG, "getChimesVolGain failed");
        return 0.0f;
    }

    public int setAncSwitch(int switchNo, int switchState, int startSpd, int endSpd) {
        Log.d(TAG, "setAncSwitch switchNo = " + switchNo + "switchState = " + switchState + ", startSpd = " + startSpd + ", endSpd = " + endSpd);
        if (this.mCarAudioControl != null && switchNo >= 1 && switchNo <= 2 && switchState >= 0 && switchState <= 1 && startSpd >= 0 && startSpd <= 6500 && endSpd >= 0 && endSpd <= 6500 && endSpd > startSpd) {
            Log.d(TAG, "setAncSwitch tigger");
            this.mCarAudioControl.setIntegerValue(29, switchNo);
            this.mCarAudioControl.setIntegerValue(30, switchState);
            this.mCarAudioControl.setIntegerValue(31, startSpd);
            this.mCarAudioControl.setIntegerValue(32, endSpd);
            return 0;
        }
        return -2147482648;
    }

    public int generalReset(int manufactor, int item, int value) {
        Log.d(TAG, "generalReset tigger manufactor : " + manufactor + ", item : " + item + " value : " + value);
        if (this.mCarAudioControl == null || item < 0 || item > 2 || value < 0 || value > 1) {
            return -2147482648;
        }
        if (value != 1) {
            return value == 0 ? -2147482645 : -2147482648;
        }
        if (item == 0) {
            Log.d(TAG, "generalReset RESET_ITEM_EQ");
            for (int band = 0; band <= 4; band++) {
                int index = 36;
                if (band == 0) {
                    index = 36;
                } else if (band == 1) {
                    index = 37;
                } else if (band == 2) {
                    index = 38;
                } else if (band != 3) {
                    if (band == 4) {
                        index = 40;
                    }
                } else {
                    index = 39;
                }
                this.mCarAudioControl.setIntegerValue(index, 0);
            }
        } else if (item == 1) {
            Log.d(TAG, "generalReset RESET_ITEM_BALANCE");
        } else if (item == 2) {
            Log.d(TAG, "generalReset RESET_ITEM_BALANCE_FADE");
            this.mCarAudioControl.setIntegerValue(34, 0);
            this.mCarAudioControl.setIntegerValue(35, 0);
        }
        return 0;
    }

    public int setGeneralSoundField(int manufactor, int balance, int fade) {
        Log.d(TAG, "setGeneralSoundField tigger manufactor : " + manufactor + ", x : " + balance + ", y : " + fade);
        if (this.mCarAudioControl != null && balance >= -10 && balance <= 10 && fade >= -10 && fade <= 10) {
            Log.d(TAG, "setGeneralSoundField conditions met");
            this.soundFieldManufactor = manufactor;
            this.mCarAudioControl.setIntegerValue(34, balance);
            this.mCarAudioControl.setIntegerValue(35, fade);
            return 0;
        }
        return -2147482648;
    }

    public int[] getGeneralSoundField(int manufactor) {
        Log.d(TAG, "getGeneralSoundField tigger manufactor : " + manufactor);
        int[] balanceFadePosition = new int[2];
        if (this.mCarAudioControl != null) {
            Log.d(TAG, "getGeneralSoundField conditions met");
            int balancePosition = this.mCarAudioControl.getIntegerValue(34, 0);
            int fadePosition = this.mCarAudioControl.getIntegerValue(35, 0);
            balanceFadePosition[0] = balancePosition;
            balanceFadePosition[1] = fadePosition;
            Log.d(TAG, "getGeneralSoundField balance is : " + balanceFadePosition[0] + ", fade : " + balanceFadePosition[1]);
        }
        return balanceFadePosition;
    }

    public int setGeneralEqualizer(int manufactor, int frequencyNum, int value) {
        Log.d(TAG, "setGeneralEqualizer tigger manufactor : " + manufactor + ", frequencyNum : " + frequencyNum + " value: " + value);
        if (this.mCarAudioControl != null && frequencyNum >= 0 && frequencyNum <= 4 && value >= -10 && value <= 10) {
            Log.d(TAG, "setGeneralEqualizer conditions met");
            this.equalizerManufactor = manufactor;
            int index = 36;
            if (frequencyNum == 0) {
                index = 36;
            } else if (frequencyNum == 1) {
                index = 37;
            } else if (frequencyNum == 2) {
                index = 38;
            } else if (frequencyNum == 3) {
                index = 39;
            } else if (frequencyNum == 4) {
                index = 40;
            }
            this.mCarAudioControl.setIntegerValue(index, value);
            return 0;
        }
        return -2147482648;
    }

    public int getGeneralEqualizer(int manufactor, int frequencyNum) {
        Log.d(TAG, "getGeneralEqualizer tigger manufactor : " + manufactor + ", frequencyNum : " + frequencyNum);
        if (this.mCarAudioControl == null || frequencyNum < 0 || frequencyNum > 4) {
            return 0;
        }
        Log.d(TAG, "getGeneralEqualizer conditions met");
        int index = 36;
        if (frequencyNum == 0) {
            index = 36;
        } else if (frequencyNum == 1) {
            index = 37;
        } else if (frequencyNum == 2) {
            index = 38;
        } else if (frequencyNum == 3) {
            index = 39;
        } else if (frequencyNum == 4) {
            index = 40;
        }
        int equalizerValue = this.mCarAudioControl.getIntegerValue(index, 0);
        Log.d(TAG, "getGeneralEqualizer equalizerValue : " + equalizerValue);
        return equalizerValue;
    }
}
