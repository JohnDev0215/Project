package com.amazon.music.destination;

import com.amazon.music.destination.core.Destination;

/* JADX INFO: loaded from: classes4.dex */
public class SettingsDestination extends Destination {
    public static final String DOWNLOAD_AUDIO_QUALITY = "downloadAudioQuality";
    public static final String NOTIFICATIONS = "notifications";
    public static final String STREAMING_AUDIO_QUALITY = "streamingAudioQuality";
    private final SettingTarget target;

    public SettingsDestination(SettingTarget settingTarget, String str, String str2) {
        super(str, str2);
        this.target = settingTarget;
    }

    public SettingTarget getTarget() {
        return this.target;
    }

    public enum SettingTarget {
        STREAMING_AUDIO_QUALITY(SettingsDestination.STREAMING_AUDIO_QUALITY),
        DOWNLOAD_AUDIO_QUALITY(SettingsDestination.DOWNLOAD_AUDIO_QUALITY),
        NOTIFICATIONS(SettingsDestination.NOTIFICATIONS);

        private final String value;

        SettingTarget(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }

        public static SettingTarget fromString(String str) {
            for (SettingTarget settingTarget : values()) {
                if (settingTarget.value.equals(str)) {
                    return settingTarget;
                }
            }
            return null;
        }
    }
}
