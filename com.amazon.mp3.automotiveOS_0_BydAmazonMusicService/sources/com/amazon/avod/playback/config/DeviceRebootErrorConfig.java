package com.amazon.avod.playback.config;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.renderer.tate.OMXILPlaybackException;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceRebootErrorConfig extends MediaConfigBase {
    private final ConfigurationValue<Set<String>> mErrorsToSuggestDeviceReboot;
    private final ConfigurationValue<Boolean> mForwardPlaybackMetadataAvailableEventDuringRestart;

    private static class Holder {
        private static final DeviceRebootErrorConfig INSTANCE = new DeviceRebootErrorConfig();

        private Holder() {
        }
    }

    public static DeviceRebootErrorConfig getInstance() {
        return Holder.INSTANCE;
    }

    private DeviceRebootErrorConfig() {
        this.mErrorsToSuggestDeviceReboot = newSemicolonDelimitedStringSetConfigurationValue("errorsToSuggestDeviceReboot", new String[]{OMXILPlaybackException.OMX_MEDIA_SERVER_DIED, OMXILPlaybackException.ANDROID_UNKNOWN_ERROR, OMXILPlaybackException.OMX_TIMEOUT});
        this.mForwardPlaybackMetadataAvailableEventDuringRestart = newBooleanConfigValue("playback_forwardPlaybackMetadataAvailableEventDuringRestart", true);
    }

    public MediaErrorCode getPlaybackError(MediaException mediaException) {
        return getInstance().getErrorsToSuggestDeviceReboot().contains(Integer.valueOf(mediaException.getIntegerErrorCode())) ? StandardErrorCode.DEVICE_REBOOT_REQUIRED : mediaException.getErrorCode().getExternalCode();
    }

    private Set<Integer> getErrorsToSuggestDeviceReboot() {
        return createIntegerSetFromConfigurationValueStringSet(this.mErrorsToSuggestDeviceReboot);
    }

    public boolean shouldForwardPlaybackMetadataAvailableEventDuringRestart() {
        return this.mForwardPlaybackMetadataAvailableEventDuringRestart.getValue().booleanValue();
    }
}
