package com.android.car.audio;

import android.content.ContentResolver;
import android.provider.Settings;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class CarAudioSettings {
    private static final String VOLUME_SETTINGS_KEY_FOR_GROUP_PREFIX = "android.car.VOLUME_GROUP/";
    private static final String VOLUME_SETTINGS_KEY_MASTER_MUTE = "android.car.MASTER_MUTE";
    private final ContentResolver mContentResolver;

    private static String getVolumeSettingsKeyForGroup(int zoneId, int groupId) {
        int maskedGroupId = (zoneId << 8) + groupId;
        return VOLUME_SETTINGS_KEY_FOR_GROUP_PREFIX + maskedGroupId;
    }

    CarAudioSettings(ContentResolver contentResolver) {
        Objects.requireNonNull(contentResolver);
        this.mContentResolver = contentResolver;
    }

    int getStoredVolumeGainIndexForUser(int userId, int zoneId, int id) {
        return Settings.System.getIntForUser(this.mContentResolver, getVolumeSettingsKeyForGroup(zoneId, id), -1, userId);
    }

    void storeVolumeGainIndexForUser(int userId, int zoneId, int id, int gainIndex) {
        Settings.System.putIntForUser(this.mContentResolver, getVolumeSettingsKeyForGroup(zoneId, id), gainIndex, userId);
    }

    void storeMasterMute(Boolean bool) {
        Settings.Global.putInt(this.mContentResolver, VOLUME_SETTINGS_KEY_MASTER_MUTE, bool.booleanValue() ? 1 : 0);
    }

    boolean getMasterMute() {
        return Settings.Global.getInt(this.mContentResolver, VOLUME_SETTINGS_KEY_MASTER_MUTE, 0) != 0;
    }

    public boolean isRejectNavigationOnCallEnabledInSettings(int userId) {
        return Settings.Secure.getIntForUser(this.mContentResolver, "android.car.KEY_AUDIO_FOCUS_NAVIGATION_REJECTED_DURING_CALL", 0, userId) == 1;
    }

    public ContentResolver getContentResolver() {
        return this.mContentResolver;
    }
}
