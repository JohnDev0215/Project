package android.hardware.bydauto.audio;

import android.content.Context;
import android.hardware.bydauto.AbsBYDAutoDevice;
import android.hardware.bydauto.audio.tsimpl.AudioManagerImpl;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.media.AudioFocusInfo;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class BYDAutoAudioDevice extends AbsBYDAutoDevice {
    public static final int AM = 0;
    public static final int ARKAMYS_INVALID = 0;
    public static final int ARKAMYS_MODE_ALL = 2;
    public static final int ARKAMYS_MODE_DRIVER = 1;
    public static final int ARKAMYS_MODE_INVALID = 0;
    public static final int ARKAMYS_MODE_OFF = 3;
    public static final int ARKAMYS_OFF = 2;
    public static final int ARKAMYS_ON = 1;
    public static final int AUDIO_COMMAND_BUSY = 0;
    public static final int AUDIO_COMMAND_FAILED = -2147482648;
    public static final int AUDIO_COMMAND_INVALID_VALUE = -2147482645;
    public static final int AUDIO_COMMAND_SUCCESS = 0;
    public static final int AUDIO_COMMAND_TIMEOUT = -2147482646;
    public static final int AUDIO_FUNCTION_OFF = 0;
    public static final int AUDIO_FUNCTION_ON = 1;
    public static final String AUDIO_GET_PERM = "android.permission.BYDAUTO_AUDIO_GET";
    public static final int AUDIO_MIC_AMP_GAIN_0 = 0;
    public static final int AUDIO_MIC_AMP_GAIN_1 = 1;
    public static final int AUDIO_MIC_AMP_GAIN_10 = 10;
    public static final int AUDIO_MIC_AMP_GAIN_2 = 2;
    public static final int AUDIO_MIC_AMP_GAIN_3 = 3;
    public static final int AUDIO_MIC_AMP_GAIN_4 = 4;
    public static final int AUDIO_MIC_AMP_GAIN_5 = 5;
    public static final int AUDIO_MIC_AMP_GAIN_6 = 6;
    public static final int AUDIO_MIC_AMP_GAIN_7 = 7;
    public static final int AUDIO_MIC_AMP_GAIN_8 = 8;
    public static final int AUDIO_MIC_AMP_GAIN_9 = 9;
    public static final int AUDIO_NO_DIRAC = 2;
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
    public static final String AUDIO_SET_PERM = "android.permission.BYDAUTO_AUDIO_SET";
    public static final int AUDIO_TIME_HOUR_MAX = 23;
    public static final int AUDIO_TIME_HOUR_MIN = 0;
    public static final int AUDIO_TIME_MINUTE_MAX = 59;
    public static final int AUDIO_TIME_MINUTE_MIN = 0;
    public static final int AUDIO_TIME_SECOND_MAX = 59;
    public static final int AUDIO_TIME_SECOND_MIN = 0;
    public static final int AUDIO_VOLUME_MAX = 100;
    public static final int AUDIO_VOLUME_MIN = 0;
    public static final int AVAS_MODE_1 = 2;
    public static final int AVAS_MODE_2 = 3;
    public static final int AVAS_MODE_3 = 4;
    public static final int AVAS_MODE_4 = 5;
    public static final int AVAS_MODE_5 = 6;
    public static final int BACK_PAD_VOL_MAX = 39;
    public static final int BACK_PAD_VOL_MIN = 0;
    public static final int BACK_PAD_VOL_NO_ACTION = 63;
    public static final int BEAM_FORM_ALL = 1;
    public static final int BEAM_FORM_AUTO = 4;
    public static final int BEAM_FORM_INVALID = 0;
    public static final int BEAM_FORM_LEFT = 2;
    public static final int BEAM_FORM_RIGHT = 3;
    public static final int BS_TREBLE = 21;
    public static final int BTA = 5;
    public static final int CHANNEL_KARAOKE = 3;
    public static final int CHANNEL_PAD = 2;
    public static final int CHANNEL_RADIO = 1;
    public static final int CONTROL_IFLYTEK_OFF = 0;
    public static final int CONTROL_IFLYTEK_ON = 1;
    public static final int CONTROL_IFLYTEK_RESET = 2;
    public static final int CTRL_SOURCE_KNOB = 1;
    public static final int CTRL_SOURCE_ST = 2;
    public static final int DAB = 2;
    public static final int DIRAC = 1;
    public static final int DIRAC_LIVE = 2;
    public static final int DIRAC_MODE_DYNAMIC = 1;
    public static final int DIRAC_MODE_HIFI_ROOM = 1;
    public static final int DIRAC_MODE_LIVESTAGE = 2;
    public static final int DIRAC_MODE_NICAM = 2;
    public static final int DIRAC_MODE_OFF = 3;
    public static final int DIRAC_PERFORMANCE = 3;
    public static final int DIRAC_PERFORMANCE_CLARITY = 5;
    public static final int DIRAC_PERFORMANCE_DYNAMIC = 6;
    public static final int DIRAC_PERFORMANCE_OFF = 4;
    public static final int DIRAC_SIMPLE = 1;
    public static final int DYNAUDIO = 2;
    public static final int DYNA_1 = 17;
    public static final int DYNA_ALL = 1;
    public static final int DYNA_ALTO = 19;
    public static final int DYNA_BACK_ROW = 4;
    public static final int DYNA_BASS = 18;
    public static final int DYNA_CO_DRIVER = 3;
    public static final int DYNA_CUSTOM = 6;
    public static final int DYNA_EFFECT_CUSTOM = 18;
    public static final int DYNA_EFFECT_DYNAMIC = 20;
    public static final int DYNA_EFFECT_ORIGINAL = 19;
    public static final int DYNA_EFFECT_SOFT = 21;
    public static final int DYNA_EFFECT_SPEECH = 22;
    public static final int DYNA_MAIN_DRIVER = 2;
    public static final int DYNA_MEGA_BASS = 17;
    public static final int DYNA_SURROUND = 5;
    public static final int DYNA_TREBLE = 20;
    public static final int EQUALIZER_FREQUENCY_1 = 1;
    public static final int EQUALIZER_FREQUENCY_2 = 2;
    public static final int EQUALIZER_FREQUENCY_3 = 3;
    public static final int EQUALIZER_FREQUENCY_4 = 4;
    public static final int EQUALIZER_FREQUENCY_5 = 5;
    public static final int EQUALIZER_PERCENT_MAX = 30;
    public static final int EQUALIZER_PERCENT_MIN = 1;
    public static final int FM = 1;
    public static final int HARMAN = 3;
    public static final int HARMAN_1 = 33;
    public static final int HARMAN_ALL = 17;
    public static final int HARMAN_ALTO = 34;
    public static final int HARMAN_BACK_ROW = 19;
    public static final int HARMAN_BASS = 33;
    public static final int HARMAN_FRONT_ROW = 18;
    public static final int HARMAN_TREBLE = 35;
    public static final int IFLYTEK_FUNCTION_MODE_BT_ELIMINATE_ECHO = 3;
    public static final int IFLYTEK_FUNCTION_MODE_OFF = 0;
    public static final int IFLYTEK_FUNCTION_MODE_RECORD = 1;
    public static final int IFLYTEK_FUNCTION_MODE_VOICE_DENOISING = 2;
    public static final int IFLYTEK_FUNCTION_MODE_VOICE_WAKE_UP = 4;
    public static final int IFLYTEK_WORK_MODE_ADDON = 2;
    public static final int IFLYTEK_WORK_MODE_CAR = 3;
    public static final int IFLYTEK_WORK_MODE_TOPLIGHT = 1;
    public static final int KARAOKE_EQUALIZER_1 = 1;
    public static final int KARAOKE_EQUALIZER_2 = 2;
    public static final int KARAOKE_EQUALIZER_3 = 3;
    public static final int KARAOKE_EQUALIZER_VALUE_MAX = 30;
    public static final int KARAOKE_EQUALIZER_VALUE_MIN = 1;
    public static final int KARAOKE_MODE_ENTER = 1;
    public static final int KARAOKE_MODE_EXIT = 2;
    public static final int KTV_MODE_CONNECT = 1;
    public static final int KTV_MODE_DISCONNECT = 0;
    public static final int LOUDSPEAKER_NUM_INVALID = 0;
    public static final int LOUDSPEAKER_NUM_MAX = 16;
    public static final int LOUDSPEAKER_NUM_MIN = 2;
    public static final int MIC_MUTE_DISABLE = 0;
    public static final int MIC_MUTE_ENABLE = 1;
    public static final int MUSIC_CHANGE_SOURCE_ST = 2;
    public static final int MUSIC_CHANGE_SOURCE_VOICE = 1;
    public static final int MUTE_DISABLE = 0;
    public static final int MUTE_ENABLE = 1;
    public static final int NAVI_MUTE_DISABLE = 3;
    public static final int NAVI_MUTE_ENABLE = 2;
    public static final int NAVI_VOLUME_MAX = 10;
    public static final int NAVI_VOLUME_MIN = 0;
    public static final int RESET_INVALID = 0;
    public static final int RESET_ITEM_BALANCE = 1;
    public static final int RESET_ITEM_BALANCE_FADE = 2;
    public static final int RESET_ITEM_EQ = 0;
    public static final int RESET_VALID = 1;
    public static final int SET_INVALID = 0;
    public static final int SET_OFF = 2;
    public static final int SET_ON = 1;
    public static final int SET_RESERVED = 3;
    public static final int SET_VSP_STATE_OFF = 0;
    public static final int SET_VSP_STATE_ON = 1;
    public static final int SIGNAL_INVALID = 0;
    public static final int SIGNAL_MAX = 15;
    public static final int SIGNAL_MIN = 1;
    public static final int SOUND_EFFECT_CLASSICAL = 3;
    public static final int SOUND_EFFECT_COUNTRY = 4;
    public static final int SOUND_EFFECT_CUSTOM = 11;
    public static final int SOUND_EFFECT_DANCE = 9;
    public static final int SOUND_EFFECT_ELECTRONIC = 5;
    public static final int SOUND_EFFECT_JAZZ = 2;
    public static final int SOUND_EFFECT_NONE = 1;
    public static final int SOUND_EFFECT_OLDIES = 6;
    public static final int SOUND_EFFECT_OPERA = 10;
    public static final int SOUND_EFFECT_POP = 7;
    public static final int SOUND_EFFECT_ROCK = 8;
    public static final int SOUND_FIELD_MAX = 14;
    public static final int SOUND_FIELD_MIN = 0;
    public static final String TAG = "BYDAutoAudioDevice";
    public static final int TYPE_ADAS = 2;
    public static final int TYPE_CHIMES = 0;
    public static final int TYPE_RADAR = 1;
    public static final int USB_MUSIC = 3;
    public static final int USB_VIDEO = 4;
    public static final int VOICE_SET_OFF = 1;
    public static final int VOICE_SET_ON = 0;
    public static final int VOLUME_SOURCE_KNOB = 11;
    public static final int VOLUME_SOURCE_PADQUR = 0;
    public static final int VOLUME_SOURCE_PADREQ = 1;
    public static final int VOLUME_SOURCE_WHEEL = 12;
    public static final int VSC_DISABLE = 0;
    public static final int VSC_LVL_1 = 1;
    public static final int VSC_LVL_2 = 2;
    public static final int VSC_LVL_3 = 3;
    public static final int VSC_LVL_4 = 4;
    public static final int VSC_LVL_5 = 5;
    public static final int VSC_LVL_6 = 6;
    public static final int VSC_LVL_7 = 7;
    private static volatile BYDAutoAudioDevice sBYDAutoAudioDevice;
    private AudioManagerImpl mAudioManagerImpl;

    public BYDAutoAudioDevice(Context context) {
        super(context);
        this.mAudioManagerImpl = AudioManagerImpl.getInstance(context);
    }

    public static BYDAutoAudioDevice getInstance(Context context) {
        if (sBYDAutoAudioDevice == null) {
            synchronized (BYDAutoAudioDevice.class) {
                if (sBYDAutoAudioDevice == null) {
                    Log.d(TAG, "BYDAutoAudioDevice get instance 10.27");
                    sBYDAutoAudioDevice = new BYDAutoAudioDevice(context);
                }
            }
        }
        return sBYDAutoAudioDevice;
    }

    public int generalReset(int i8, int i9, int i10) {
        return this.mAudioManagerImpl.generalReset(i8, i9, i10);
    }

    public int getAllMute() {
        return this.mAudioManagerImpl.getAllMute();
    }

    public int getAudioDeviceConfig() {
        return this.mAudioManagerImpl.getAudioDeviceConfig();
    }

    public int getAudioDeviceManufacturer() {
        return this.mAudioManagerImpl.getAudioDeviceManufacturer();
    }

    public int getAudioMicAmpGain() {
        return this.mAudioManagerImpl.getAudioMicAmpGain();
    }

    public int[] getBalanceFadePosition() {
        return this.mAudioManagerImpl.getBalanceFadePosition();
    }

    public int getBtCall_MicMute() {
        return this.mAudioManagerImpl.getBtCall_MicMute();
    }

    public int getCallMuteState() {
        return this.mAudioManagerImpl.getCallMuteState();
    }

    public int getCarTipsVoiceState(int i8) {
        return this.mAudioManagerImpl.getCarTipsVoiceState(i8);
    }

    public float getChimesVolGain(int i8) {
        return this.mAudioManagerImpl.getChimesVolGain(i8);
    }

    public List<AudioFocusInfo> getCurAudioFocusInfo(int i8) {
        return this.mAudioManagerImpl.getCurAudioFocusInfo(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getDevicetype() {
        return this.mAudioManagerImpl.getDevicetype();
    }

    public int getDiracLiveMode() {
        return this.mAudioManagerImpl.getDiracLiveMode();
    }

    public int getDiracLiveState() {
        return this.mAudioManagerImpl.getDiracLiveState();
    }

    public int getDiracPerformanceMode() {
        return this.mAudioManagerImpl.getDiracPerformanceMode();
    }

    public int getEngineSoundType() {
        return this.mAudioManagerImpl.getEngineSoundType();
    }

    public int getEqualizer(int i8) {
        return this.mAudioManagerImpl.getEqualizer(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int[] getFeatureList() {
        return this.mAudioManagerImpl.getFeatureList();
    }

    public int getGearBySpeed() {
        return this.mAudioManagerImpl.getGearBySpeed();
    }

    public int getGeneralEqualizer(int i8, int i9) {
        return this.mAudioManagerImpl.getGeneralEqualizer(i8, i9);
    }

    public int[] getGeneralSoundField(int i8) {
        return this.mAudioManagerImpl.getGeneralSoundField(i8);
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getGetPermission() {
        return this.mAudioManagerImpl.getGetPermission();
    }

    public int getGroupMaxVolume(int i8) {
        return this.mAudioManagerImpl.getGroupMaxVolume(i8);
    }

    public int getGroupMinVolume(int i8) {
        return this.mAudioManagerImpl.getGroupMinVolume(i8);
    }

    public int getKtvMode() {
        return this.mAudioManagerImpl.getKtvMode();
    }

    public String getLastMediaPackage() {
        return this.mAudioManagerImpl.getLastMediaPackage();
    }

    public int getLoudness() {
        return this.mAudioManagerImpl.getLoudness();
    }

    public int getLoudnessState() {
        return this.mAudioManagerImpl.getLoudnessState();
    }

    public int getLoudspeakerNum() {
        return this.mAudioManagerImpl.getLoudspeakerNum();
    }

    public int getMusicChangeSource() {
        return this.mAudioManagerImpl.getMusicChangeSource();
    }

    public int getMuteState() {
        return this.mAudioManagerImpl.getMuteState();
    }

    public int getNaviMuteState() {
        return this.mAudioManagerImpl.getNaviMuteState();
    }

    public int getNaviVolume() {
        return this.mAudioManagerImpl.getNaviVolume();
    }

    public int getNavigationMediaState() {
        return this.mAudioManagerImpl.getNavigationMediaState();
    }

    public int getPhoneVolume() {
        return this.mAudioManagerImpl.getPhoneVolume();
    }

    public int getPowerMute() {
        return this.mAudioManagerImpl.getPowerMute();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public String getSetPermission() {
        return this.mAudioManagerImpl.getSetPermission();
    }

    public int getSoundEffect() {
        return this.mAudioManagerImpl.getSoundEffect();
    }

    public int[] getSoundField() {
        return this.mAudioManagerImpl.getSoundField();
    }

    public int getSoundFieldPositionType() {
        return this.mAudioManagerImpl.getSoundFieldPositionType();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public int getType() {
        return this.mAudioManagerImpl.getType();
    }

    public int getVSPState() {
        return this.mAudioManagerImpl.getVSPState();
    }

    public int getVoiceAssistVolume() {
        return this.mAudioManagerImpl.getVoiceAssistVolume();
    }

    public int getVolume() {
        return this.mAudioManagerImpl.getVolume();
    }

    public int getVolumeGroupIdForUsage(int i8) {
        return this.mAudioManagerImpl.getVolumeGroupIdForUsage(i8);
    }

    public int getVolumeSettingSource() {
        return this.mAudioManagerImpl.getVolumeSettingSource();
    }

    @Override // android.hardware.bydauto.AbsBYDAutoDevice
    public boolean postEvent(int i8, int i9, int i10, Object obj) {
        return this.mAudioManagerImpl.postEvent(i8, i9, i10, obj);
    }

    public boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener) {
        return this.mAudioManagerImpl.registerConnectionListener(iServiceConnectionListener);
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        this.mAudioManagerImpl.registerListener(absBYDAutoAudioListener);
    }

    public void requestEepromAmpType() {
        this.mAudioManagerImpl.requestEepromAmpType();
    }

    public int reset(int i8, int i9) {
        return this.mAudioManagerImpl.reset(i8, i9);
    }

    public int setAllMute(int i8) {
        return this.mAudioManagerImpl.setAllMute(i8);
    }

    public int setAncSwitch(int i8, int i9, int i10, int i11) {
        return this.mAudioManagerImpl.setAncSwitch(i8, i9, i10, i11);
    }

    public int setAudioMicAmpGain(int i8) {
        return this.mAudioManagerImpl.setAudioMicAmpGain(i8);
    }

    public int setAudioParam(int i8, int i9, int i10, int i11) {
        return this.mAudioManagerImpl.setAudioParam(i8, i9, i10, i11);
    }

    public int setBalanceFadePosition(int i8, int i9) {
        return this.mAudioManagerImpl.setBalanceFadePosition(i8, i9);
    }

    public void setBtCall_MicMute(int i8) {
        this.mAudioManagerImpl.setBtCall_MicMute(i8);
    }

    public void setCallMuteState(int i8) {
        this.mAudioManagerImpl.setCallMuteState(i8);
    }

    public void setCarPlayAltActive(boolean z7) {
        this.mAudioManagerImpl.setCarPlayAltActive(z7);
    }

    public int setCarTipsVoiceState(int i8, int i9) {
        return this.mAudioManagerImpl.setCarTipsVoiceState(i8, i9);
    }

    public int setChimesVolGain(int i8, float f8) {
        return this.mAudioManagerImpl.setChimesVolGain(i8, f8);
    }

    public int setDiracLiveMode(int i8) {
        return this.mAudioManagerImpl.setDiracLiveMode(i8);
    }

    public int setDiracLiveState(int i8) {
        return this.mAudioManagerImpl.setDiracLiveState(i8);
    }

    public int setDiracPerformanceMode(int i8) {
        return this.mAudioManagerImpl.setDiracPerformanceMode(i8);
    }

    public int setEngineSoundType(int i8) {
        return this.mAudioManagerImpl.setEngineSoundType(i8);
    }

    public int setEqualizer(int i8, int i9) {
        return this.mAudioManagerImpl.setEqualizer(i8, i9);
    }

    public int setGearBySpeed(int i8) {
        return this.mAudioManagerImpl.setGearBySpeed(i8);
    }

    public int setGeneralEqualizer(int i8, int i9, int i10) {
        return this.mAudioManagerImpl.setGeneralEqualizer(i8, i9, i10);
    }

    public int setGeneralSoundField(int i8, int i9, int i10) {
        return this.mAudioManagerImpl.setGeneralSoundField(i8, i9, i10);
    }

    public int setKtvMode(int i8) {
        return this.mAudioManagerImpl.setKtvMode(i8);
    }

    public int setKtvResource(int i8) {
        return this.mAudioManagerImpl.setKtvResource(i8);
    }

    public void setLoudness(int i8) {
        this.mAudioManagerImpl.setLoudness(i8);
    }

    public int setLoudnessState(int i8) {
        return this.mAudioManagerImpl.setLoudnessState(i8);
    }

    public int setMusicChangeSource(int i8) {
        return this.mAudioManagerImpl.setMusicChangeSource(i8);
    }

    public int setMuteState(int i8) {
        return this.mAudioManagerImpl.setMuteState(i8);
    }

    public int setNaviMuteState(int i8) {
        return this.mAudioManagerImpl.setNaviMuteState(i8);
    }

    public int setNaviVolume(int i8) {
        return this.mAudioManagerImpl.setNaviVolume(i8);
    }

    public int setNavigationMediaState(int i8) {
        return this.mAudioManagerImpl.setNavigationMediaState(i8);
    }

    public int setPhoneVolume(int i8) {
        return this.mAudioManagerImpl.setPhoneVolume(i8);
    }

    public void setPowerMute(int i8) {
        this.mAudioManagerImpl.setPowerMute(i8);
    }

    public int setSoundEffect(int i8) {
        return this.mAudioManagerImpl.setSoundEffect(i8);
    }

    public int setSoundField(int i8, int i9) {
        return this.mAudioManagerImpl.setSoundField(i8, i9);
    }

    public int setSoundFieldPosition(int i8) {
        return this.mAudioManagerImpl.setSoundFieldPosition(i8);
    }

    public int setVSPState(int i8) {
        return this.mAudioManagerImpl.setVSPState(i8);
    }

    public int setVoiceAssistVolume(int i8) {
        return this.mAudioManagerImpl.setVoiceAssistVolume(i8);
    }

    public int setVolume(int i8) {
        return this.mAudioManagerImpl.setVolume(i8);
    }

    public int setVolumeSettingsource() {
        return this.mAudioManagerImpl.setVolumeSettingsource();
    }

    public boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener) {
        return this.mAudioManagerImpl.unregisterConnectionListener(iServiceConnectionListener);
    }

    public void unregisterListener(AbsBYDAutoAudioListener absBYDAutoAudioListener) {
        this.mAudioManagerImpl.unregisterListener(absBYDAutoAudioListener);
    }

    public void registerListener(AbsBYDAutoAudioListener absBYDAutoAudioListener, int[] iArr) {
        this.mAudioManagerImpl.registerListener(absBYDAutoAudioListener, iArr);
    }
}
