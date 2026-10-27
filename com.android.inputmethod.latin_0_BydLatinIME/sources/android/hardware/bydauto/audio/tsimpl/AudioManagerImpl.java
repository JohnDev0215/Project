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
    public static final int INDEX_AMP_MODE = 13;
    public static final int INDEX_AVAS_MODE = 11;
    public static final int INDEX_AVAS_STATE = 18;
    public static final int INDEX_CALL_MUTE = 17;
    public static final int INDEX_CP_ALT_ACTIVE = 16;
    public static final int INDEX_DIRAC_LIVE = 2;
    public static final int INDEX_DIRAC_LIVE_LOUDNESS = 10;
    public static final int INDEX_DIRAC_PERFORMANCE = 1;
    public static final int INDEX_DIRAC_PERFORMANCE_LOUDNESS = 9;
    public static final int INDEX_DYNA_CONFIG = 4;
    public static final int INDEX_DYNA_SOUND_FIELD_FOCUS = 3;
    public static final int INDEX_DYNA_VSC = 5;
    public static final int INDEX_INTELLIGENT_BROADCAST = 7;
    public static final int INDEX_KTV_MODE = 12;
    public static final int INDEX_MIC_MUTE_TYPE = 14;
    public static final int INDEX_NAV_MIX_MEDIA = 6;
    public static final int INDEX_POWER_MUTE_TYPE = 15;
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
    public static final int MIC_MUTE_DISABLE = 0;
    public static final int MIC_MUTE_ENABLE = 1;
    public static final int NAV_VOLUME_MAX = 10;
    public static final int NAV_VOLUME_MIN = 0;
    public static final int PHONE_VOLUME_MAX = 39;
    public static final int PHONE_VOLUME_MIN = 5;
    public static final int POWER_MUTE_STATE_MAX = 12;
    public static final int POWER_MUTE_STATE_MIN = 0;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final String TAG = "AudioManagerImpl_0.41";
    public static final int USAGE_INDEX_MAX = 36;
    public static final int USAGE_INDEX_MIN = 0;
    public static final int VOICE_VOLUME_MAX = 24;
    public static final int VOICE_VOLUME_MIN = 5;
    public static final int VSC_VALUE_MAX = 7;
    public static final int VSC_VALUE_MIN = 0;
    public static final int VSP_STATE_DISABLE = 0;
    public static final int VSP_STATE_ENABLE = 1;
    private static volatile AudioManagerImpl sAudioManagerImpl;
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

    private int convertMb(int i, int i2) {
        return i * i2;
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    protected void onServiceConnected(Car car) {
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
        this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, this.mCarAudioManager.getAudioZoneIds().stream().mapToInt(new ToIntFunction() { // from class: android.hardware.bydauto.audio.tsimpl.-$$Lambda$AudioManagerImpl$uBsCIBAbAW1p84hPJ6ULJah0SPE
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray());
        this.mCarEnhancedAudioManager.registerCarBalanceCallback(this.mCarBalanceCallback);
        this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
        this.mCarEqualizer.registerCarEqualizerCallback(this.mCarEqualizerCallback);
        this.mCarAudioControl.setCarAudioControlCallback(this.mCarAudioControlCallback);
        for (int i = 0; i <= 18; i++) {
            this.mCarAudioControl.checkAndUpdateCarAudioControlCallback(i, true);
            Log.d(TAG, "car audio control register index : " + i);
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
    }

    AudioManagerImpl(Context context) {
        super(context);
        this.mIgnoreBydPermission = false;
        this.mCarVolumeCallback = new CarAudioManager.CarVolumeCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int i, int i2, int i3) {
                Log.d(AudioManagerImpl.TAG, "group volume change zoneId : " + i + " greoupID : " + i2 + "flags : " + i3);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioVolumeChanged((AbsBYDAutoAudioListener) it.next(), i2, i, i3);
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
            public void onGroupMuteChanged(int i, int i2, int i3) {
                ArrayList arrayList = new ArrayList();
                int volumeGroupCount = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupCount();
                int volumeGroupIdForUsage = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i, 12);
                int volumeGroupIdForUsage2 = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i, 1);
                int volumeGroupIdForUsage3 = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                for (int i4 = 0; i4 < volumeGroupCount; i4++) {
                    if (i4 != volumeGroupIdForUsage3) {
                        if (AudioManagerImpl.this.mCarEnhancedAudioManager.isVolumeGroupMuted(i, i2)) {
                            Log.d(AudioManagerImpl.TAG, "group all mute state groupID : " + i4);
                            arrayList.add(1);
                        } else {
                            Log.d(AudioManagerImpl.TAG, "group all unmute state groupID : " + i4);
                            arrayList.add(0);
                        }
                    }
                }
                if (arrayList.contains(0)) {
                    Log.d(AudioManagerImpl.TAG, "all mute state is unmute : 0");
                    Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it.hasNext()) {
                        ((AbsBYDAutoAudioListener) it.next()).onAllMuteState(0);
                    }
                } else {
                    Log.d(AudioManagerImpl.TAG, "all mute state is mute : 1");
                    Iterator it2 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it2.hasNext()) {
                        ((AbsBYDAutoAudioListener) it2.next()).onAllMuteState(1);
                    }
                }
                boolean zIsVolumeGroupMuted = AudioManagerImpl.this.mCarEnhancedAudioManager.isVolumeGroupMuted(i, i2);
                Log.d(AudioManagerImpl.TAG, "media or nav mute state groupId : " + i2 + " mute state is : " + zIsVolumeGroupMuted);
                if (i2 == volumeGroupIdForUsage2) {
                    Iterator it3 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it3.hasNext()) {
                        ((AbsBYDAutoAudioListener) it3.next()).onMuteStateChanged(zIsVolumeGroupMuted ? 1 : 0);
                    }
                } else if (i2 == volumeGroupIdForUsage) {
                    int i5 = zIsVolumeGroupMuted ? 2 : 3;
                    Iterator it4 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it4.hasNext()) {
                        ((AbsBYDAutoAudioListener) it4.next()).onNaviMuteStateChanged(i5);
                    }
                }
            }
        };
        this.mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.4
            public void onFocusChanged(int i, List<AudioFocusInfo> list) {
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
                    ((AbsBYDAutoAudioListener) it.next()).onAudioFocusChanged(i, list);
                }
            }
        };
        this.mCarEqualizerCallback = new CarEqualizer.CarEqualizerCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.5
            public void onPresetChanged() {
                int soundEffect = AudioManagerImpl.this.getSoundEffect();
                Log.d(AudioManagerImpl.TAG, "preset Changed effect is : " + soundEffect);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onSoundEffectChanged(soundEffect);
                }
            }

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
                Log.d(AudioManagerImpl.TAG, "band level changed band is : " + i + " value : " + equalizer);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onEqualizerChanged(i, equalizer);
                }
            }
        };
        this.mCarAudioControlCallback = new CarAudioControl.CarAudioControlCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.6
            public void onAudioControlChanged(int i) {
                Log.d(AudioManagerImpl.TAG, "audio control index is : " + i);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioControlChanged((AbsBYDAutoAudioListener) it.next(), i);
                }
            }
        };
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioControlChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i) {
        Log.d(TAG, "notify Audio Control Changed index is : " + i);
        if (i == 3) {
            int soundFieldPositionType = getSoundFieldPositionType();
            Log.d(TAG, "audio control change INDEX_DYNA_SOUND_FIELD_FOCUS type : " + soundFieldPositionType);
            absBYDAutoAudioListener.onSoundFieldPositionChanged(soundFieldPositionType);
            return;
        }
        if (i == 18) {
            int integerValue = this.mCarAudioControl.getIntegerValue(18, 0);
            Log.d(TAG, "audio control change INDEX_AVAS_STATE vspState : " + integerValue);
            absBYDAutoAudioListener.onVSPStateChanged(integerValue);
            return;
        }
        if (i == 5) {
            int audioDeviceManufacturer = sAudioManagerImpl.getAudioDeviceManufacturer();
            int integerValue2 = this.mCarAudioControl.getIntegerValue(5, 4);
            Log.d(TAG, "audio control change INDEX_DYNA_VSC manufacturer : " + audioDeviceManufacturer + "gear : " + integerValue2);
            absBYDAutoAudioListener.onGearSpeedChanged(audioDeviceManufacturer, integerValue2);
            return;
        }
        if (i == 6) {
            int integerValue3 = this.mCarAudioControl.getIntegerValue(6, 1);
            Log.d(TAG, "audio control change INDEX_NAV_MIX_MEDIA value : " + integerValue3);
            absBYDAutoAudioListener.onNavigationMediaStateChanged(integerValue3);
            return;
        }
        if (i == 10) {
            int integerValue4 = this.mCarAudioControl.getIntegerValue(10, 0);
            Log.d(TAG, "audio control change INDEX_DIRAC_LIVE_LOUDNESS ludnessType : " + integerValue4);
            absBYDAutoAudioListener.onLudnessTypeChange(integerValue4);
            return;
        }
        if (i == 11) {
            int integerValue5 = this.mCarAudioControl.getIntegerValue(11, 2);
            Log.d(TAG, "audio control change INDEX_AVAS_MODE avasTypeValue : " + integerValue5);
            absBYDAutoAudioListener.onEngineSoundTypeChanged(integerValue5);
        } else if (i == 14) {
            int integerValue6 = this.mCarAudioControl.getIntegerValue(14, 0);
            Log.d(TAG, "audio control change INDEX_MIC_MUTE_TYPE micMuteState : " + integerValue6);
            absBYDAutoAudioListener.onBtCall_MicMuteChanged(integerValue6);
        } else {
            if (i == 15) {
                int integerValue7 = this.mCarAudioControl.getIntegerValue(15, 0);
                Log.d(TAG, "audio control change INDEX_POWER_MUTE_TYPE powerMuteState : " + integerValue7);
                absBYDAutoAudioListener.onPowerMuteStateChange(integerValue7);
                return;
            }
            Log.e(TAG, "notifyAudioControlChanged index is invalid");
        }
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

    public int setVolume(int i) {
        Log.d(TAG, "media setVolume tigger volume is : " + i);
        if (this.mCarAudioManager == null || i < 0 || i > 39) {
            return -2147482648;
        }
        Log.d(TAG, "setVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(1), i, 0);
        return 0;
    }

    public int getVolume() {
        Log.d(TAG, "media getVolume tigger ");
        if (this.mCarAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getVolume conditions met");
        return this.mCarAudioManager.getGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(1));
    }

    public int setNaviVolume(int i) {
        Log.d(TAG, "nav setNaviVolume tigger volume is : " + i);
        if (this.mCarAudioManager == null || i < 0 || i > 10) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(12), i, 0);
        return 0;
    }

    public int getNaviVolume() {
        Log.d(TAG, "nav getNaviVolume tigger ");
        if (this.mCarAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getNaviVolume conditions met");
        return this.mCarAudioManager.getGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(12));
    }

    public int setMuteState(int i) {
        Log.d(TAG, "media setMuteState tigger state is : " + i + "getPowerMute is : " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 0) {
            return -2147482648;
        }
        if (i > 1) {
            return -2147482648;
        }
        Log.d(TAG, "setMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1), i == 1, 0);
        return 0;
    }

    public int getMuteState() {
        Log.d(TAG, "media getMuteState tigger ");
        if (this.mCarEnhancedAudioManager == null) {
            return 0;
        }
        Log.d(TAG, "getMuteState conditions met");
        return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1)) ? 1 : 0;
    }

    public int setNaviMuteState(int i) {
        Log.d(TAG, "setNaviMuteState tigger state is : " + i + "getPowerMute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 2 || i > 3) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12), i == 2, 0);
        return 0;
    }

    public int getNaviMuteState() {
        Log.d(TAG, "getNaviMuteState tigger ");
        if (this.mCarEnhancedAudioManager == null) {
            return 3;
        }
        Log.d(TAG, "getNaviMuteState conditions met");
        return this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12)) ? 2 : 3;
    }

    public int setVolumeSettingsource() {
        Log.d(TAG, "setVolumeSettingsource tigger ");
        return 0;
    }

    public int getVolumeSettingSource() {
        Log.d(TAG, "getVolumeSettingsource tigger");
        return 0;
    }

    public int setSoundField(int i, int i2) {
        Log.d(TAG, "setSoundField tigger x is : " + i + " y :" + i2);
        int iConvertMb = convertMb(i, 100);
        int iConvertMb2 = convertMb(i2, 100);
        if (this.mCarEnhancedAudioManager == null || i < -9 || i > 9 || i2 < -9 || i2 > 9) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundField conditions met");
        this.mCarEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
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

    public int setSoundEffect(int i) {
        int i2;
        Log.d(TAG, "setSoundEffect tigger effect : " + i);
        if (this.mCarEqualizer == null || i < 18 || i > 22) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundEffect conditions met");
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
                Log.d(TAG, "set effect is invalid" + i);
                return -2147482648;
        }
        this.mCarEqualizer.usePreset(i2);
        return 0;
    }

    public int getSoundEffect() {
        Log.d(TAG, "getSoundEffect tigger");
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int i = 0;
        if (carEqualizer != null) {
            int currentPreset = carEqualizer.getCurrentPreset();
            Log.d(TAG, "getSoundEffect conditions met" + currentPreset);
            if (currentPreset == 0) {
                i = 18;
            } else if (currentPreset == 1) {
                i = 19;
            } else if (currentPreset == 2) {
                i = 20;
            } else if (currentPreset == 3) {
                i = 21;
            } else {
                if (currentPreset != 4) {
                    Log.d(TAG, "get effect is invalid0");
                    return 0;
                }
                i = 22;
            }
        }
        Log.d(TAG, "getSoundEffect success : " + i);
        return i;
    }

    public int setDiracLiveState(int i) {
        Log.d(TAG, "setDiracLiveState tigger ");
        return 0;
    }

    public int getDiracLiveState() {
        Log.d(TAG, "getDiracLiveState tigger ");
        return 0;
    }

    public int setLoudnessState(int i) {
        Log.d(TAG, "setLoudnessState tigger state is : " + i);
        return -2147482648;
    }

    public int getLoudnessState() {
        Log.d(TAG, "getLoudnessState tigger ");
        return 0;
    }

    public int setEqualizer(int i, int i2) {
        int i3;
        Log.d(TAG, "setEqualizer tigger frequencyNum : " + i + " value: " + i2);
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
                Log.d(TAG, "set frequencyNum is not founded" + i);
                return -2147482648;
        }
        int iConvertMb = convertMb(i2, 100);
        Log.d(TAG, "setEqualizer successed  frequencyNum :" + i3 + "value : " + iConvertMb);
        this.mCarEqualizer.setBandLevel(i3, iConvertMb);
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public int getEqualizer(int i) {
        Log.d(TAG, "getEqualizer tigger frequencyNum is : " + i);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int iConvertDb = 0;
        if (carEqualizer != null && i >= 17 && i <= 20) {
            switch (i) {
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
                default:
                    Log.d(TAG, "get frequencyNum is not founded" + i);
                    return 0;
            }
        }
        Log.d(TAG, "getEqualizer successed : " + iConvertDb);
        return iConvertDb;
    }

    public int getLoudspeakerNum() {
        Log.d(TAG, "getLoudspeakerNum tigger ");
        return 0;
    }

    public int setAudioMicAmpGain(int i) {
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

    public int setMusicChangeSource(int i) {
        Log.d(TAG, "setMusicChangeSource tigger");
        return 0;
    }

    public int reset(int i, int i2) {
        CarEnhancedAudioManager carEnhancedAudioManager;
        Log.d(TAG, "reset tigger item is : " + i + " value : " + i2);
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

    public int getDiracLiveMode() {
        Log.d(TAG, "getDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(2, 2);
        }
        return 0;
    }

    public int setDiracLiveMode(int i) {
        Log.d(TAG, "setDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 1 || i > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(2, i);
        return 0;
    }

    public boolean postEvent(int i, int i2, int i3, Object obj) {
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
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            if (!arrayList.contains(absBYDAutoAudioListener)) {
                arrayList.add(absBYDAutoAudioListener);
            }
        }
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] iArr) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener featureIDs tigger");
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

    public int getAudioDeviceManufacturer() {
        Log.d(TAG, "getAudioDeviceManufacturer tigger");
        return 2;
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

    public int getDiracPerformanceMode() {
        Log.d(TAG, "getDiracPerformanceMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            i = carAudioControl.getIntegerValue(1, 1) == 1 ? 1 : 0;
            Log.d(TAG, "getDiracPerformanceMode successed :" + i);
        }
        return i;
    }

    public int setDiracPerformanceMode(int i) {
        Log.d(TAG, "setDiracPerformanceMode tigger mode is : " + i);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 1 || i > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(1, i);
        return 0;
    }

    public int getGearBySpeed() {
        Log.d(TAG, "getGearBySpeed tigger");
        int integerValue = (this.mCarAudioControl == null || sAudioManagerImpl.getAudioDeviceManufacturer() != 2) ? 0 : this.mCarAudioControl.getIntegerValue(5, 4);
        Log.d(TAG, "getGearBySpeed successed : " + integerValue);
        return integerValue;
    }

    public int setGearBySpeed(int i) {
        Log.d(TAG, "setGearBySpeed tigger");
        if (this.mCarAudioControl == null || i < 0 || i > 7) {
            return -2147482648;
        }
        if (sAudioManagerImpl.getAudioDeviceManufacturer() == 2) {
            this.mCarAudioControl.setIntegerValue(5, i);
            return 0;
        }
        Log.d(TAG, "speedType is not founded");
        return -2147482648;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getSoundFieldPositionType() {
        /*
            r7 = this;
            java.lang.String r0 = "AudioManagerImpl_0.41"
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
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.d(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.getSoundFieldPositionType():int");
    }

    public int setSoundFieldPosition(int i) {
        Log.d(TAG, "setSoundFieldPosition tigger type is : " + i);
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
                        Log.d(TAG, "type is not found");
                        return -2147482648;
                }
                carAudioControl.setIntegerValue(3, i2);
                return 0;
            }
        }
        return -2147482648;
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

    public int setBalanceFadePosition(int i, int i2) {
        Log.d(TAG, "setBalanceFadePosition tigger balance : " + i + " fade : " + i2);
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

    public int getNavigationMediaState() {
        Log.d(TAG, "getNavigationMediaState tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(6, 1);
        }
        return 1;
    }

    public int setNavigationMediaState(int i) {
        Log.d(TAG, "setNavigationMediaState tigger value is " + i);
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

    public List<AudioFocusInfo> getCurAudioFocusInfo(int i) {
        Log.d(TAG, "getCurAudioFocusInfo tigger zoneId : " + i);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || i != 0) {
            return null;
        }
        try {
            return carEnhancedAudioManager.getAudioFocusHoldersForZoneId(i);
        } catch (IllegalArgumentException unused) {
            Log.d(TAG, "zoneId is out of range ");
            return null;
        }
    }

    public int setVoiceAssistVolume(int i) {
        Log.d(TAG, "setVoiceAssistVolume tigger volume : " + i);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 5 || i > 24) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11), i, 0);
        return 0;
    }

    public int getVoiceAssistVolume() {
        Log.d(TAG, "getVoiceAssistVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11));
    }

    public int setPhoneVolume(int i) {
        Log.d(TAG, "setPhoneVolume tigger volume : " + i);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i < 5 || i > 39) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2), i, 0);
        return 0;
    }

    public int getPhoneVolume() {
        Log.d(TAG, "getPhoneVolume tigger ");
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null) {
            return 0;
        }
        return this.mCarAudioManager.getGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2));
    }

    public int setEngineSoundType(int i) {
        Log.d(TAG, "setEngineSoundType tigger volume : " + i);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 6) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(11, i);
        return 0;
    }

    public int getEngineSoundType() {
        Log.d(TAG, "getEngineSoundType tigger ");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            return carAudioControl.getIntegerValue(11, 2);
        }
        return 2;
    }

    public int setVSPState(int i) {
        Log.d(TAG, "setVSPState tigger value : " + i);
        if (this.mCarAudioControl == null) {
            return -2147482648;
        }
        if (i != 1 && i != 0) {
            return -2147482648;
        }
        Log.d(TAG, "setVSPState start");
        this.mCarAudioControl.setIntegerValue(18, i);
        return 0;
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

    public int setKtvMode(int i) {
        Log.d(TAG, "setKTVMode tigger mode : " + i);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i < 0 || i > 1) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(12, i);
        return 0;
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

    public int setAllMute(int i) {
        Log.d(TAG, "setAllMute tigger state : " + i + "getpowermute is " + getPowerMute());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i < 0 || i > 1) {
            return -2147482648;
        }
        int volumeGroupCount = this.mCarAudioManager.getVolumeGroupCount();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        if (i == 0) {
            for (int i2 = 0; i2 < volumeGroupCount; i2++) {
                if (i2 != volumeGroupIdForUsage) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i2, false, 0);
                    Log.d(TAG, "BYDAudio setAllMute muteId = " + i2);
                }
            }
        } else if (i == 1) {
            for (int i3 = 0; i3 < volumeGroupCount; i3++) {
                if (i3 != volumeGroupIdForUsage) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i3, true, 0);
                    Log.d(TAG, "BYDAudio setAllMute muteId = " + i3);
                }
            }
        }
        return 0;
    }

    public int getAllMute() {
        Log.d(TAG, "getAllMute tigger ");
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int volumeGroupCount = this.mCarAudioManager.getVolumeGroupCount();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        for (int i = 0; i < volumeGroupCount; i++) {
            if (i != volumeGroupIdForUsage) {
                boolean zIsVolumeGroupMuted = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, i);
                Log.d(TAG, "getAllMute state is : " + zIsVolumeGroupMuted);
                if (zIsVolumeGroupMuted) {
                    Log.d(TAG, "getAllMute state is enable");
                    arrayList.add(1);
                } else {
                    Log.d(TAG, "getAllMute state is disable");
                    arrayList.add(0);
                }
            }
        }
        if (arrayList.contains(0)) {
            Log.d(TAG, "getAllMuteGroupArray contains enable");
            return 0;
        }
        Log.d(TAG, "getAllMuteGroupArray contains disable");
        return 1;
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

    public void setLoudness(int i) {
        if (this.mCarAudioControl == null || i < 0 || i > 8) {
            return;
        }
        Log.d(TAG, "setludness tigger type :  " + i);
        this.mCarAudioControl.setIntegerValue(10, i);
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

    public void setBtCall_MicMute(int i) {
        if (this.mCarAudioControl == null || i < 0 || i > 1) {
            return;
        }
        Log.d(TAG, "set set Bt Call MicMute" + i);
        this.mCarAudioControl.setIntegerValue(14, i);
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

    public void setPowerMute(int i) {
        if (this.mCarAudioControl == null || i < 0 || i > 12) {
            return;
        }
        Log.d(TAG, "setPowerMute tigger muteState :  " + i);
        this.mCarAudioControl.setIntegerValue(15, i);
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

    public void setCarPlayAltActive(boolean z) {
        if (this.mCarAudioControl != null && getAllMute() == 0) {
            Log.d(TAG, "setCarPlayAltActive tigger state :  " + z);
            this.mCarAudioControl.setIntegerValue(16, z ? 1 : 0);
        }
        Log.d(TAG, "setCarPlayAltActive setall mute state is true :  " + getAllMute());
    }

    public void setCallMuteState(int i) {
        Log.d(TAG, "setCallMuteState state:  " + i + "getPowerMute is " + getPowerMute());
        if (this.mCarAudioControl == null || getPowerMute() != 10 || i < 0 || i > 1) {
            return;
        }
        Log.d(TAG, "setCallMuteState tigger state :  " + i);
        this.mCarAudioControl.setIntegerValue(17, i);
    }

    public int getCallMuteState() {
        if (this.mCarAudioControl == null) {
            return 0;
        }
        Log.d(TAG, "getCallMuteState tigger state is " + this.mCarAudioControl.getIntegerValue(17, 0));
        return this.mCarAudioControl.getIntegerValue(17, 0);
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException("requires permission " + str);
        }
    }

    private int convertDb(int i, int i2) {
        return i / i2;
    }

    public int setKtvResource(int i) {
        if (this.mTsManagerImpl.getGeneralAdapterManager() == null) {
            Log.e(TAG, "setKtvResource tiggert");
            return 0;
        }
        return this.mTsManagerImpl.getGeneralAdapterManager().setKtvResource(i);
    }

    public int getGroupMinVolume(int i) {
        if (this.mCarAudioManager != null && i >= 0 && i <= 5) {
            Log.d(TAG, "sgetGroupMinVolume tigger groupID : " + i);
            return this.mCarAudioManager.getGroupMinVolume(i);
        }
        Log.e(TAG, "set getGroupMinVolume failed");
        return 0;
    }

    public int getGroupMaxVolume(int i) {
        if (this.mCarAudioManager != null && i >= 0 && i <= 5) {
            Log.d(TAG, "getGroupMaxVolume groupID : " + i);
            return this.mCarAudioManager.getGroupMaxVolume(i);
        }
        Log.e(TAG, "set getGroupMaxVolume failed");
        return 0;
    }

    public int getVolumeGroupIdForUsage(int i) {
        if (this.mCarAudioManager != null && i >= 0 && i <= 36) {
            Log.d(TAG, "getVolumeGroupIdForUsage usage : " + i);
            return this.mCarAudioManager.getVolumeGroupIdForUsage(i);
        }
        Log.e(TAG, "set getVolumeGroupIdForUsage failed");
        return 0;
    }
}
