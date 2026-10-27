package ts.car.service.audio;

import android.media.AudioDeviceInfo;
import android.media.AudioFocusInfo;
import java.util.List;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface ICarAudioManager extends IPlatformService {
    public static final String AUDIOFOCUS_EXTRA_RECEIVE_DUCKING_EVENTS = "android.car.media.AUDIOFOCUS_EXTRA_RECEIVE_DUCKING_EVENTS";
    public static final String AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID = "android.car.media.AUDIOFOCUS_EXTRA_REQUEST_ZONE_ID";
    public static final int INVALID_AUDIO_ZONE = -1;
    public static final int INVALID_VOLUME_GROUP_ID = -1;
    public static final String PERMISSION_CAR_CONTROL_AUDIO_SETTINGS = "android.car.permission.CAR_CONTROL_AUDIO_SETTINGS";
    public static final String PERMISSION_CAR_CONTROL_AUDIO_VOLUME = "android.car.permission.CAR_CONTROL_AUDIO_VOLUME";
    public static final String PERMISSION_CAR_CONTROL_ENHANCED_AUDIO = "android.car.permission.CAR_CONTROL_ENHANCED_AUDIO";
    public static final int PRIMARY_AUDIO_ZONE = 0;

    void adjustStartupVolumeAutomatically(int i, boolean z);

    void adjustStartupVolumeAutomatically(boolean z);

    void adjustSuggestedGroupVolume(int i, int i2, int i3);

    void adjustSuggestedGroupVolume(int i, int i2, int i3, int i4);

    void adjustSuggestedGroupVolumeForZoneId(int i, int i2, int i3, int i4);

    void adjustSuggestedGroupVolumeForZoneId(int i, int i2, int i3, int i4, int i5);

    void adjustSuggestedGroupVolumeToMaximum(int i, int i2);

    void adjustSuggestedGroupVolumeToMaximumForZoneId(int i, int i2, int i3);

    void adjustSuggestedGroupVolumeToMinimum(int i, int i2);

    void adjustSuggestedGroupVolumeToMinimumForZoneId(int i, int i2, int i3);

    void adjustVolume(int i, int i2);

    void adjustVolume(int i, int i2, int i3);

    void adjustVolumeForZoneId(int i, int i2, int i3);

    void adjustVolumeForZoneId(int i, int i2, int i3, int i4);

    boolean clearZoneIdForUid(int i);

    int createAudioPatch(String str, int i, int i2);

    void duckAudio(int i, int i2);

    IAudioControl getAudioControlInstance();

    List<AudioFocusInfo> getAudioFocusHoldersForZoneId(int i);

    List<AudioFocusInfo> getAudioFocusLosersForZoneId(int i);

    List<Integer> getAudioZoneIds();

    IBalance getBalanceInstance();

    IEqualizer getEqualizerInstance();

    String[] getExternalSources();

    int getGroupMaxVolume(int i);

    int getGroupMaxVolume(int i, int i2);

    int getGroupMinVolume(int i);

    int getGroupMinVolume(int i, int i2);

    int getGroupVolume(int i);

    int getGroupVolume(int i, int i2);

    List<AudioDeviceInfo> getInputDevicesForZoneId(int i);

    AudioDeviceInfo getOutputDeviceForUsage(int i, int i2);

    String getParameters(String str);

    int[] getUsagesForVolumeGroupId(int i);

    int[] getUsagesForVolumeGroupId(int i, int i2);

    int getVolumeGroupCount();

    int getVolumeGroupCount(int i);

    int getVolumeGroupIdForUsage(int i);

    int getVolumeGroupIdForUsage(int i, int i2);

    int getZoneIdForUid(int i);

    boolean isAdjustStartupVolumeAutomatically();

    boolean isAdjustStartupVolumeAutomatically(int i);

    boolean isDynamicRoutingEnabled();

    boolean isGroupMute(int i);

    boolean isGroupMute(int i, int i2);

    boolean isGroupMuteSupported(int i);

    boolean isGroupMuteSupported(int i, int i2);

    void registerCarMuteCallback(ICarMuteCallback iCarMuteCallback);

    void registerCarVolumeCallback(ICarVolumeCallback iCarVolumeCallback);

    void releaseAudioPatch(int i);

    void setGroupMute(int i, int i2, boolean z, int i3);

    void setGroupMute(int i, boolean z, int i2);

    void setGroupVolume(int i, int i2, int i3);

    void setGroupVolume(int i, int i2, int i3, int i4);

    void setParameters(String str);

    boolean setZoneIdForUid(int i, int i2);

    void unduckAudio(int i);

    void unregisterCarMuteCallback(ICarMuteCallback iCarMuteCallback);

    void unregisterCarVolumeCallback(ICarVolumeCallback iCarVolumeCallback);
}
