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
import h6.n;
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

    @TargetApi(24)
    public AudioManagerImpl(Context context) {
        super(context);
        this.mIgnoreBydPermission = false;
        this.mCarVolumeCallback = new CarAudioManager.CarVolumeCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.1
            @Override // android.car.media.CarAudioManager.CarVolumeCallback
            public void onGroupVolumeChanged(int i7, int i10, int i11) {
                StringBuilder sbL = M5.b.l("group volume change zoneId : ", " greoupID : ", i7, i10, "flags : ");
                sbL.append(i11);
                Log.d(AudioManagerImpl.TAG, sbL.toString());
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioVolumeChanged((AbsBYDAutoAudioListener) it.next(), i10, i7, i11);
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
            public void onGroupMuteChanged(int i7, int i10, int i11) {
                ArrayList arrayList = new ArrayList();
                int volumeGroupCount = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupCount();
                int volumeGroupIdForUsage = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i7, 12);
                int volumeGroupIdForUsage2 = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(i7, 1);
                int volumeGroupIdForUsage3 = AudioManagerImpl.this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
                for (int i12 = 0; i12 < volumeGroupCount; i12++) {
                    if (i12 != volumeGroupIdForUsage3) {
                        if (AudioManagerImpl.this.mCarEnhancedAudioManager.isVolumeGroupMuted(i7, i10)) {
                            Log.d(AudioManagerImpl.TAG, "group all mute state groupID : " + i12);
                            arrayList.add(1);
                        } else {
                            Log.d(AudioManagerImpl.TAG, "group all unmute state groupID : " + i12);
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
                boolean zIsVolumeGroupMuted = AudioManagerImpl.this.mCarEnhancedAudioManager.isVolumeGroupMuted(i7, i10);
                Log.d(AudioManagerImpl.TAG, "media or nav mute state groupId : " + i10 + " mute state is : " + zIsVolumeGroupMuted);
                if (i10 == volumeGroupIdForUsage2) {
                    Iterator it3 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it3.hasNext()) {
                        ((AbsBYDAutoAudioListener) it3.next()).onMuteStateChanged(zIsVolumeGroupMuted ? 1 : 0);
                    }
                } else if (i10 == volumeGroupIdForUsage) {
                    int i13 = zIsVolumeGroupMuted ? 2 : 3;
                    Iterator it4 = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                    while (it4.hasNext()) {
                        ((AbsBYDAutoAudioListener) it4.next()).onNaviMuteStateChanged(i13);
                    }
                }
            }
        };
        this.mCarFocusCallback = new CarEnhancedAudioManager.CarFocusCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.4
            public void onFocusChanged(int i7, List<AudioFocusInfo> list) {
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
                    ((AbsBYDAutoAudioListener) it.next()).onAudioFocusChanged(i7, list);
                }
            }
        };
        this.mCarEqualizerCallback = new CarEqualizer.CarEqualizerCallback() { // from class: android.hardware.bydauto.audio.tsimpl.AudioManagerImpl.5
            public void onBandLevelChanged(int i7) {
                if (i7 == 0) {
                    i7 = 17;
                } else if (i7 == 1) {
                    i7 = 18;
                } else if (i7 == 2) {
                    i7 = 19;
                } else if (i7 == 3) {
                    i7 = 20;
                }
                int equalizer = AudioManagerImpl.this.getEqualizer(i7);
                Log.d(AudioManagerImpl.TAG, "band level changed band is : " + i7 + " value : " + equalizer);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    ((AbsBYDAutoAudioListener) it.next()).onEqualizerChanged(i7, equalizer);
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
            public void onAudioControlChanged(int i7) {
                Log.d(AudioManagerImpl.TAG, "audio control index is : " + i7);
                Iterator it = AudioManagerImpl.BYD_AUTO_AUDIO_LISTENER.iterator();
                while (it.hasNext()) {
                    AudioManagerImpl.this.notifyAudioControlChanged((AbsBYDAutoAudioListener) it.next(), i7);
                }
            }
        };
        this.mContext = context;
        this.mTsManagerImpl = TsManagerImpl.getInstance(context);
    }

    private int convertDb(int i7, int i10) {
        return i7 / i10;
    }

    private int convertMb(int i7, int i10) {
        return i7 * i10;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(M5.b.g("requires permission ", str));
        }
    }

    public static AudioManagerImpl getInstance(Context context) {
        if (sAudioManagerImpl == null) {
            synchronized (AudioManagerImpl.class) {
                try {
                    if (sAudioManagerImpl == null) {
                        Log.d(TAG, "AudioManagerImpl equal null ");
                        sAudioManagerImpl = new AudioManagerImpl(context);
                    }
                } finally {
                }
            }
        }
        Log.d(TAG, "getInstance to do ");
        return sAudioManagerImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyAudioControlChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("notify Audio Control Changed index is : ", i7, TAG);
        if (i7 == 3) {
            int soundFieldPositionType = getSoundFieldPositionType();
            Log.d(TAG, "audio control change INDEX_DYNA_SOUND_FIELD_FOCUS type : " + soundFieldPositionType);
            absBYDAutoAudioListener.onSoundFieldPositionChanged(soundFieldPositionType);
            return;
        }
        if (i7 == 18) {
            int integerValue = this.mCarAudioControl.getIntegerValue(18, 0);
            Log.d(TAG, "audio control change INDEX_AVAS_STATE vspState : " + integerValue);
            absBYDAutoAudioListener.onVSPStateChanged(integerValue);
            return;
        }
        if (i7 == 5) {
            int audioDeviceManufacturer = sAudioManagerImpl.getAudioDeviceManufacturer();
            int integerValue2 = this.mCarAudioControl.getIntegerValue(5, 4);
            Log.d(TAG, "audio control change INDEX_DYNA_VSC manufacturer : " + audioDeviceManufacturer + "gear : " + integerValue2);
            absBYDAutoAudioListener.onGearSpeedChanged(audioDeviceManufacturer, integerValue2);
            return;
        }
        if (i7 == 6) {
            int integerValue3 = this.mCarAudioControl.getIntegerValue(6, 1);
            Log.d(TAG, "audio control change INDEX_NAV_MIX_MEDIA value : " + integerValue3);
            absBYDAutoAudioListener.onNavigationMediaStateChanged(integerValue3);
            return;
        }
        if (i7 == 10) {
            int integerValue4 = this.mCarAudioControl.getIntegerValue(10, 0);
            Log.d(TAG, "audio control change INDEX_DIRAC_LIVE_LOUDNESS ludnessType : " + integerValue4);
            absBYDAutoAudioListener.onLudnessTypeChange(integerValue4);
            return;
        }
        if (i7 == 11) {
            int integerValue5 = this.mCarAudioControl.getIntegerValue(11, 2);
            Log.d(TAG, "audio control change INDEX_AVAS_MODE avasTypeValue : " + integerValue5);
            absBYDAutoAudioListener.onEngineSoundTypeChanged(integerValue5);
            return;
        }
        if (i7 == 14) {
            int integerValue6 = this.mCarAudioControl.getIntegerValue(14, 0);
            Log.d(TAG, "audio control change INDEX_MIC_MUTE_TYPE micMuteState : " + integerValue6);
            absBYDAutoAudioListener.onBtCall_MicMuteChanged(integerValue6);
            return;
        }
        if (i7 != 15) {
            n.j(TAG, "notifyAudioControlChanged index is invalid");
            return;
        }
        int integerValue7 = this.mCarAudioControl.getIntegerValue(15, 0);
        Log.d(TAG, "audio control change INDEX_POWER_MUTE_TYPE powerMuteState : " + integerValue7);
        absBYDAutoAudioListener.onPowerMuteStateChange(integerValue7);
    }

    public int getAllMute() {
        Log.d(TAG, "getAllMute tigger ");
        if (this.mCarEnhancedAudioManager == null || this.mCarAudioManager == null) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        int volumeGroupCount = this.mCarAudioManager.getVolumeGroupCount();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        for (int i7 = 0; i7 < volumeGroupCount; i7++) {
            if (i7 != volumeGroupIdForUsage) {
                boolean zIsVolumeGroupMuted = this.mCarEnhancedAudioManager.isVolumeGroupMuted(0, i7);
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

    public int getAudioDeviceConfig() {
        Log.d(TAG, "getAudioDeviceConfig tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            i = carAudioControl.getIntegerValue(4, 0) == 0 ? 17 : 0;
            android.hardware.bydauto.ac.tsimpl.a.o("getAudioDeviceConfig successed :", i, TAG);
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
        android.hardware.bydauto.ac.tsimpl.a.o("getmicState tigger state :  ", integerValue, TAG);
        return integerValue;
    }

    public int getCallMuteState() {
        if (this.mCarAudioControl == null) {
            return 0;
        }
        Log.d(TAG, "getCallMuteState tigger state is " + this.mCarAudioControl.getIntegerValue(17, 0));
        return this.mCarAudioControl.getIntegerValue(17, 0);
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("getCurAudioFocusInfo tigger zoneId : ", i7, TAG);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || i7 != 0) {
            return null;
        }
        try {
            return carEnhancedAudioManager.getAudioFocusHoldersForZoneId(i7);
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
            android.hardware.bydauto.ac.tsimpl.a.o("getDiracPerformanceMode successed :", i, TAG);
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
    public int getEqualizer(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("getEqualizer tigger frequencyNum is : ", i7, TAG);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int iConvertDb = 0;
        if (carEqualizer != null && i7 >= 17 && i7 <= 20) {
            switch (i7) {
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
                    android.hardware.bydauto.ac.tsimpl.a.o("get frequencyNum is not founded", i7, TAG);
                    return 0;
            }
        }
        android.hardware.bydauto.ac.tsimpl.a.o("getEqualizer successed : ", iConvertDb, TAG);
        return iConvertDb;
    }

    public int[] getFeatureList() {
        Log.d(TAG, "getFeatureList tigger ");
        return new int[0];
    }

    public int getGearBySpeed() {
        Log.d(TAG, "getGearBySpeed tigger");
        int integerValue = (this.mCarAudioControl == null || sAudioManagerImpl.getAudioDeviceManufacturer() != 2) ? 0 : this.mCarAudioControl.getIntegerValue(5, 4);
        android.hardware.bydauto.ac.tsimpl.a.o("getGearBySpeed successed : ", integerValue, TAG);
        return integerValue;
    }

    public String getGetPermission() {
        Log.d(TAG, "getGetPermission tigger");
        return BYDAutoAudioDevice.AUDIO_GET_PERM;
    }

    public int getGroupMaxVolume(int i7) {
        if (this.mCarAudioManager == null || i7 < 0 || i7 > 5) {
            n.j(TAG, "set getGroupMaxVolume failed");
            return 0;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("getGroupMaxVolume groupID : ", i7, TAG);
        return this.mCarAudioManager.getGroupMaxVolume(i7);
    }

    public int getGroupMinVolume(int i7) {
        if (this.mCarAudioManager == null || i7 < 0 || i7 > 5) {
            n.j(TAG, "set getGroupMinVolume failed");
            return 0;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("sgetGroupMinVolume tigger groupID : ", i7, TAG);
        return this.mCarAudioManager.getGroupMinVolume(i7);
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
        android.hardware.bydauto.ac.tsimpl.a.o("getloudnessType successed :  ", integerValue, TAG);
        return integerValue;
    }

    public int getLoudnessState() {
        Log.d(TAG, "getLoudnessState tigger ");
        return 0;
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
        android.hardware.bydauto.ac.tsimpl.a.o("getmuteState tigger state :  ", integerValue, TAG);
        return integerValue;
    }

    public String getSetPermission() {
        Log.d(TAG, "getSetPermission tigger ");
        return BYDAutoAudioDevice.AUDIO_SET_PERM;
    }

    public int getSoundEffect() {
        Log.d(TAG, "getSoundEffect tigger");
        CarEqualizer carEqualizer = this.mCarEqualizer;
        int i7 = 0;
        if (carEqualizer != null) {
            int currentPreset = carEqualizer.getCurrentPreset();
            android.hardware.bydauto.ac.tsimpl.a.o("getSoundEffect conditions met", currentPreset, TAG);
            if (currentPreset == 0) {
                i7 = 18;
            } else if (currentPreset == 1) {
                i7 = 19;
            } else if (currentPreset == 2) {
                i7 = 20;
            } else if (currentPreset == 3) {
                i7 = 21;
            } else {
                if (currentPreset != 4) {
                    Log.d(TAG, "get effect is invalid0");
                    return 0;
                }
                i7 = 22;
            }
        }
        android.hardware.bydauto.ac.tsimpl.a.o("getSoundEffect success : ", i7, TAG);
        return i7;
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

    /* JADX WARN: Removed duplicated region for block: B:16:0x0023 A[PHI: r2
  0x0023: PHI (r2v1 int) = (r2v0 int), (r2v2 int) binds: [B:8:0x0016, B:12:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0025 A[PHI: r3
  0x0025: PHI (r3v1 int) = (r3v0 int), (r3v2 int) binds: [B:7:0x0014, B:10:0x0019] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getSoundFieldPositionType() {
        /*
            r4 = this;
            java.lang.String r0 = "getSoundFieldPositionType tigger"
            java.lang.String r1 = "AudioManagerImpl_0.41"
            android.util.Log.d(r1, r0)
            android.car.media.CarAudioControl r4 = r4.mCarAudioControl
            r0 = 1
            if (r4 == 0) goto L26
            r2 = 3
            int r4 = r4.getIntegerValue(r2, r0)
            if (r4 == 0) goto L26
            r3 = 2
            if (r4 == r0) goto L25
            if (r4 == r3) goto L23
            r3 = 4
            if (r4 == r2) goto L25
            r2 = 5
            if (r4 == r3) goto L23
            if (r4 == r2) goto L21
            goto L26
        L21:
            r0 = 6
            goto L26
        L23:
            r0 = r2
            goto L26
        L25:
            r0 = r3
        L26:
            java.lang.String r4 = "getSoundFieldPositionType success :"
            android.hardware.bydauto.ac.tsimpl.a.o(r4, r0, r1)
            return r0
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
        android.hardware.bydauto.ac.tsimpl.a.o("getVSPState tigger success : ", integerValue, TAG);
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

    public int getVolumeGroupIdForUsage(int i7) {
        if (this.mCarAudioManager == null || i7 < 0 || i7 > 36) {
            n.j(TAG, "set getVolumeGroupIdForUsage failed");
            return 0;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("getVolumeGroupIdForUsage usage : ", i7, TAG);
        return this.mCarAudioManager.getVolumeGroupIdForUsage(i7);
    }

    public int getVolumeSettingSource() {
        Log.d(TAG, "getVolumeSettingsource tigger");
        return 0;
    }

    public void notifyAudioVolumeChanged(AbsBYDAutoAudioListener absBYDAutoAudioListener, int i7, int i10, int i11) {
        int groupVolume = this.mCarAudioManager.getGroupVolume(i10, i7);
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(i10, 1);
        int volumeGroupIdForUsage2 = this.mCarAudioManager.getVolumeGroupIdForUsage(i10, 12);
        int volumeGroupIdForUsage3 = this.mCarAudioManager.getVolumeGroupIdForUsage(i10, 11);
        int volumeGroupIdForUsage4 = this.mCarAudioManager.getVolumeGroupIdForUsage(i10, 2);
        if (i7 == volumeGroupIdForUsage) {
            absBYDAutoAudioListener.onVolumeChanged(groupVolume, i11);
            return;
        }
        if (i7 == volumeGroupIdForUsage2) {
            absBYDAutoAudioListener.onNaviVolumeChanged(groupVolume);
        } else if (i7 == volumeGroupIdForUsage3) {
            absBYDAutoAudioListener.onVoiceAssistVolume(groupVolume);
        } else if (i7 == volumeGroupIdForUsage4) {
            absBYDAutoAudioListener.onPhoneVolume(groupVolume);
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    public void onServiceConnected(Car car) {
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
        this.mCarEnhancedAudioManager.registerCarFocusCallback(this.mCarFocusCallback, this.mCarAudioManager.getAudioZoneIds().stream().mapToInt(new ToIntFunction() { // from class: android.hardware.bydauto.audio.tsimpl.a
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                return ((Integer) obj).intValue();
            }
        }).toArray());
        this.mCarEnhancedAudioManager.registerCarBalanceCallback(this.mCarBalanceCallback);
        this.mCarEnhancedAudioManager.registerCarGroupMutingCallback(this.mCarGroupMutingCallback);
        this.mCarEqualizer.registerCarEqualizerCallback(this.mCarEqualizerCallback);
        this.mCarAudioControl.setCarAudioControlCallback(this.mCarAudioControlCallback);
        for (int i7 = 0; i7 <= 18; i7++) {
            this.mCarAudioControl.checkAndUpdateCarAudioControlCallback(i7, true);
            Log.d(TAG, "car audio control register index : " + i7);
        }
    }

    @Override // android.hardware.bydauto.audio.tsimpl.BYDCarServiceBase
    public void onServiceDied() {
        n.j(TAG, "byd jar car service is died");
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

    public boolean postEvent(int i7, int i10, int i11, Object obj) {
        Log.d(TAG, "postEvent tigger ");
        return true;
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener tigger");
        Objects.requireNonNull(absBYDAutoAudioListener);
        ArrayList<AbsBYDAutoAudioListener> arrayList = BYD_AUTO_AUDIO_LISTENER;
        synchronized (arrayList) {
            try {
                if (!arrayList.contains(absBYDAutoAudioListener)) {
                    arrayList.add(absBYDAutoAudioListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void requestEepromAmpType() {
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            carAudioControl.setIntegerValue(13, 0);
            int integerValue = this.mCarAudioControl.getIntegerValue(13, 0);
            android.hardware.bydauto.ac.tsimpl.a.o("requestEepromAmpType tigger get is : ", integerValue, TAG);
            Iterator<AbsBYDAutoAudioListener> it = BYD_AUTO_AUDIO_LISTENER.iterator();
            while (it.hasNext()) {
                it.next().onEepromAmpTypeChanged(integerValue);
            }
        }
    }

    public int reset(int i7, int i10) {
        CarEnhancedAudioManager carEnhancedAudioManager;
        Log.d(TAG, "reset tigger item is : " + i7 + " value : " + i10);
        CarEqualizer carEqualizer = this.mCarEqualizer;
        if (carEqualizer != null && (carEnhancedAudioManager = this.mCarEnhancedAudioManager) != null && i7 >= 0 && i7 <= 2 && i10 >= 0 && i10 <= 1) {
            if (i10 == 1) {
                if (i7 == 0) {
                    for (int i11 = 0; i11 < this.mCarEqualizer.getNumberOfBands(); i11++) {
                        this.mCarEqualizer.setBandLevel(i11, 0);
                    }
                } else if (i7 == 1) {
                    carEqualizer.usePreset(1);
                } else if (i7 == 2) {
                    carEnhancedAudioManager.setBalanceTowardRightLevel(0);
                    this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(0);
                }
                return 0;
            }
            if (i10 == 0) {
                return -2147482645;
            }
        }
        return -2147482648;
    }

    public int setAllMute(int i7) {
        StringBuilder sbJ = M5.b.j("setAllMute tigger state : ", i7, "getpowermute is ");
        sbJ.append(getPowerMute());
        Log.d(TAG, sbJ.toString());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i7 < 0 || i7 > 1) {
            return -2147482648;
        }
        int volumeGroupCount = this.mCarAudioManager.getVolumeGroupCount();
        int volumeGroupIdForUsage = this.mCarAudioManager.getVolumeGroupIdForUsage(0, 2);
        if (i7 == 0) {
            for (int i10 = 0; i10 < volumeGroupCount; i10++) {
                if (i10 != volumeGroupIdForUsage) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i10, false, 0);
                    android.hardware.bydauto.ac.tsimpl.a.o("BYDAudio setAllMute muteId = ", i10, TAG);
                }
            }
        } else if (i7 == 1) {
            for (int i11 = 0; i11 < volumeGroupCount; i11++) {
                if (i11 != volumeGroupIdForUsage) {
                    this.mCarEnhancedAudioManager.setVolumeGroupMute(0, i11, true, 0);
                    android.hardware.bydauto.ac.tsimpl.a.o("BYDAudio setAllMute muteId = ", i11, TAG);
                }
            }
        }
        return 0;
    }

    public int setAudioMicAmpGain(int i7) {
        Log.d(TAG, "setAudioMicAmpGain tigger ");
        return 0;
    }

    public int setBalanceFadePosition(int i7, int i10) {
        Log.d(TAG, "setBalanceFadePosition tigger balance : " + i7 + " fade : " + i10);
        int iConvertMb = convertMb(i7, 100);
        int iConvertMb2 = convertMb(i10, 100);
        CarEnhancedAudioManager carEnhancedAudioManager = this.mCarEnhancedAudioManager;
        if (carEnhancedAudioManager == null || iConvertMb < -9 || iConvertMb > 9 || iConvertMb2 < -9 || iConvertMb2 > 9) {
            return -2147482648;
        }
        carEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public void setBtCall_MicMute(int i7) {
        if (this.mCarAudioControl == null || i7 < 0 || i7 > 1) {
            return;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("set set Bt Call MicMute", i7, TAG);
        this.mCarAudioControl.setIntegerValue(14, i7);
    }

    public void setCallMuteState(int i7) {
        StringBuilder sbJ = M5.b.j("setCallMuteState state:  ", i7, "getPowerMute is ");
        sbJ.append(getPowerMute());
        Log.d(TAG, sbJ.toString());
        if (this.mCarAudioControl == null || getPowerMute() != 10 || i7 < 0 || i7 > 1) {
            return;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("setCallMuteState tigger state :  ", i7, TAG);
        this.mCarAudioControl.setIntegerValue(17, i7);
    }

    public void setCarPlayAltActive(boolean z6) {
        if (this.mCarAudioControl != null && getAllMute() == 0) {
            Log.d(TAG, "setCarPlayAltActive tigger state :  " + z6);
            this.mCarAudioControl.setIntegerValue(16, z6 ? 1 : 0);
        }
        Log.d(TAG, "setCarPlayAltActive setall mute state is true :  " + getAllMute());
    }

    public int setDiracLiveMode(int i7) {
        Log.d(TAG, "setDiracLiveMode tigger");
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i7 < 1 || i7 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(2, i7);
        return 0;
    }

    public int setDiracLiveState(int i7) {
        Log.d(TAG, "setDiracLiveState tigger ");
        return 0;
    }

    public int setDiracPerformanceMode(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setDiracPerformanceMode tigger mode is : ", i7, TAG);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i7 < 1 || i7 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(1, i7);
        return 0;
    }

    public int setEngineSoundType(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setEngineSoundType tigger volume : ", i7, TAG);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i7 < 0 || i7 > 6) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(11, i7);
        return 0;
    }

    public int setEqualizer(int i7, int i10) {
        int i11;
        Log.d(TAG, "setEqualizer tigger frequencyNum : " + i7 + " value: " + i10);
        if (this.mCarEqualizer == null || i7 < 17 || i7 > 20 || i10 < -9 || i10 > 9) {
            return -2147482648;
        }
        switch (i7) {
            case 17:
                i11 = 0;
                break;
            case 18:
                i11 = 1;
                break;
            case 19:
                i11 = 2;
                break;
            case 20:
                i11 = 3;
                break;
            default:
                android.hardware.bydauto.ac.tsimpl.a.o("set frequencyNum is not founded", i7, TAG);
                return -2147482648;
        }
        int iConvertMb = convertMb(i10, 100);
        Log.d(TAG, "setEqualizer successed  frequencyNum :" + i11 + "value : " + iConvertMb);
        this.mCarEqualizer.setBandLevel(i11, iConvertMb);
        return 0;
    }

    public int setGearBySpeed(int i7) {
        Log.d(TAG, "setGearBySpeed tigger");
        if (this.mCarAudioControl == null || i7 < 0 || i7 > 7) {
            return -2147482648;
        }
        if (sAudioManagerImpl.getAudioDeviceManufacturer() != 2) {
            Log.d(TAG, "speedType is not founded");
            return -2147482648;
        }
        this.mCarAudioControl.setIntegerValue(5, i7);
        return 0;
    }

    public int setKtvMode(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setKTVMode tigger mode : ", i7, TAG);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i7 < 0 || i7 > 1) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(12, i7);
        return 0;
    }

    public int setKtvResource(int i7) {
        if (this.mTsManagerImpl.getGeneralAdapterManager() != null) {
            return this.mTsManagerImpl.getGeneralAdapterManager().setKtvResource(i7);
        }
        n.j(TAG, "setKtvResource tiggert");
        return 0;
    }

    public void setLoudness(int i7) {
        if (this.mCarAudioControl == null || i7 < 0 || i7 > 8) {
            return;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("setludness tigger type :  ", i7, TAG);
        this.mCarAudioControl.setIntegerValue(10, i7);
    }

    public int setLoudnessState(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setLoudnessState tigger state is : ", i7, TAG);
        return -2147482648;
    }

    public int setMusicChangeSource(int i7) {
        Log.d(TAG, "setMusicChangeSource tigger");
        return 0;
    }

    public int setMuteState(int i7) {
        StringBuilder sbJ = M5.b.j("media setMuteState tigger state is : ", i7, "getPowerMute is : ");
        sbJ.append(getPowerMute());
        Log.d(TAG, sbJ.toString());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i7 < 0) {
            return -2147482648;
        }
        if (i7 > 1) {
            return -2147482648;
        }
        Log.d(TAG, "setMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(1), i7 == 1, 0);
        return 0;
    }

    public int setNaviMuteState(int i7) {
        StringBuilder sbJ = M5.b.j("setNaviMuteState tigger state is : ", i7, "getPowerMute is ");
        sbJ.append(getPowerMute());
        Log.d(TAG, sbJ.toString());
        if (this.mCarEnhancedAudioManager == null || getPowerMute() != 10 || i7 < 2 || i7 > 3) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviMuteState conditions met");
        this.mCarEnhancedAudioManager.setVolumeGroupMute(0, this.mCarAudioManager.getVolumeGroupIdForUsage(12), i7 == 2, 0);
        return 0;
    }

    public int setNaviVolume(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("nav setNaviVolume tigger volume is : ", i7, TAG);
        if (this.mCarAudioManager == null || i7 < 0 || i7 > 10) {
            return -2147482648;
        }
        Log.d(TAG, "setNaviVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(12), i7, 0);
        return 0;
    }

    public int setNavigationMediaState(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setNavigationMediaState tigger value is ", i7, TAG);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl == null || i7 < 0 || i7 > 3) {
            return -2147482648;
        }
        if (i7 < 0 && i7 > 3) {
            return -2147482648;
        }
        carAudioControl.setIntegerValue(6, i7);
        return 0;
    }

    public int setPhoneVolume(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setPhoneVolume tigger volume : ", i7, TAG);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i7 < 5 || i7 > 39) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(2), i7, 0);
        return 0;
    }

    public void setPowerMute(int i7) {
        if (this.mCarAudioControl == null || i7 < 0 || i7 > 12) {
            return;
        }
        android.hardware.bydauto.ac.tsimpl.a.o("setPowerMute tigger muteState :  ", i7, TAG);
        this.mCarAudioControl.setIntegerValue(15, i7);
    }

    public int setSoundEffect(int i7) {
        int i10;
        android.hardware.bydauto.ac.tsimpl.a.o("setSoundEffect tigger effect : ", i7, TAG);
        if (this.mCarEqualizer == null || i7 < 18 || i7 > 22) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundEffect conditions met");
        switch (i7) {
            case 18:
                i10 = 0;
                break;
            case 19:
                i10 = 1;
                break;
            case 20:
                i10 = 2;
                break;
            case 21:
                i10 = 3;
                break;
            case 22:
                i10 = 4;
                break;
            default:
                android.hardware.bydauto.ac.tsimpl.a.o("set effect is invalid", i7, TAG);
                return -2147482648;
        }
        this.mCarEqualizer.usePreset(i10);
        return 0;
    }

    public int setSoundField(int i7, int i10) {
        Log.d(TAG, "setSoundField tigger x is : " + i7 + " y :" + i10);
        int iConvertMb = convertMb(i7, 100);
        int iConvertMb2 = convertMb(i10, 100);
        if (this.mCarEnhancedAudioManager == null || i7 < -9 || i7 > 9 || i10 < -9 || i10 > 9) {
            return -2147482648;
        }
        Log.d(TAG, "setSoundField conditions met");
        this.mCarEnhancedAudioManager.setBalanceTowardRightLevel(iConvertMb);
        this.mCarEnhancedAudioManager.setFadeTowardFrontLevel(iConvertMb2);
        return 0;
    }

    public int setSoundFieldPosition(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setSoundFieldPosition tigger type is : ", i7, TAG);
        CarAudioControl carAudioControl = this.mCarAudioControl;
        if (carAudioControl != null) {
            int i10 = 1;
            if (i7 >= 1 && i7 <= 6) {
                switch (i7) {
                    case 1:
                        i10 = 0;
                        break;
                    case 2:
                        break;
                    case 3:
                        i10 = 2;
                        break;
                    case 4:
                        i10 = 3;
                        break;
                    case 5:
                        i10 = 4;
                        break;
                    case 6:
                        i10 = 5;
                        break;
                    default:
                        Log.d(TAG, "type is not found");
                        return -2147482648;
                }
                carAudioControl.setIntegerValue(3, i10);
                return 0;
            }
        }
        return -2147482648;
    }

    public int setVSPState(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setVSPState tigger value : ", i7, TAG);
        if (this.mCarAudioControl == null) {
            return -2147482648;
        }
        if (i7 != 1 && i7 != 0) {
            return -2147482648;
        }
        Log.d(TAG, "setVSPState start");
        this.mCarAudioControl.setIntegerValue(18, i7);
        return 0;
    }

    public int setVoiceAssistVolume(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("setVoiceAssistVolume tigger volume : ", i7, TAG);
        CarAudioManager carAudioManager = this.mCarAudioManager;
        if (carAudioManager == null || i7 < 5 || i7 > 24) {
            return -2147482648;
        }
        this.mCarAudioManager.setGroupVolume(carAudioManager.getVolumeGroupIdForUsage(11), i7, 0);
        return 0;
    }

    public int setVolume(int i7) {
        android.hardware.bydauto.ac.tsimpl.a.o("media setVolume tigger volume is : ", i7, TAG);
        if (this.mCarAudioManager == null || i7 < 0 || i7 > 39) {
            return -2147482648;
        }
        Log.d(TAG, "setVolume conditions met");
        this.mCarAudioManager.setGroupVolume(this.mCarAudioManager.getVolumeGroupIdForUsage(1), i7, 0);
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
            try {
                if (arrayList.contains(absBYDAutoAudioListener)) {
                    arrayList.remove(absBYDAutoAudioListener);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] iArr) {
        Log.d(TAG, "registerListener absBYDAutoAudioListener featureIDs tigger");
    }
}
