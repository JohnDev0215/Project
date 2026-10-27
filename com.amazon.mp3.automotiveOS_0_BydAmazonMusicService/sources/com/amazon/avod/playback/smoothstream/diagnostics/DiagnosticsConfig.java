package com.amazon.avod.playback.smoothstream.diagnostics;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.core.Framework;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.framework.config.PlaybackConfigKeys;
import com.amazon.cloud9.android.knobs.Knobs;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticsConfig extends MediaConfigBase {
    private final ConfigurationValue<Boolean> mDiagnosticsViewAvailable;
    private final ConfigurationValue<Boolean> mDiagnosticsViewAvailableUser;
    private final ConfigurationValue<Boolean> mIsAmazonPlayerDiagnosticsViewEnabled;
    private final ConfigurationValue<Boolean> mShowTTFFToastMessage;

    private static class SingletonHolder {
        private static final DiagnosticsConfig INSTANCE = new DiagnosticsConfig();

        private SingletonHolder() {
        }
    }

    public static DiagnosticsConfig getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private DiagnosticsConfig() {
        ConfigType configType = ConfigType.SERVER;
        this.mDiagnosticsViewAvailable = newBooleanConfigValue(PlaybackConfigKeys.VIDEO_VIEW_DIAGNOSTIC_VIEW_AVAILABLE, false, configType);
        this.mDiagnosticsViewAvailableUser = newBooleanConfigValue(PlaybackConfigKeys.VIDEO_VIEW_DIAGNOSTIC_VIEW_AVAILABLE_TO_USER, false, ConfigType.ACCOUNT);
        this.mShowTTFFToastMessage = newBooleanConfigValue(PlaybackConfigKeys.VIDEO_VIEW_SHOW_TTFF_TOAST_MESSAGE, false, configType);
        this.mIsAmazonPlayerDiagnosticsViewEnabled = newBooleanConfigValue(PlaybackConfigKeys.IS_AMAZON_PLAYER_DIAGNOSTIC_VIEW_ENABLED, true, configType);
    }

    public boolean isDiagnosticsViewAvailable() {
        return Knobs.get("aiv_diagnostics_enabled", false) || this.mDiagnosticsViewAvailable.getValue().booleanValue() || this.mDiagnosticsViewAvailableUser.getValue().booleanValue() || (MediaSystemSharedDependencies.getInstance().isSDKPlayer() && Framework.isDebugConfigurationEnabled());
    }

    public boolean isAmazonPlayerDiagnosticsViewEnabled() {
        return this.mIsAmazonPlayerDiagnosticsViewEnabled.getValue().booleanValue();
    }
}
