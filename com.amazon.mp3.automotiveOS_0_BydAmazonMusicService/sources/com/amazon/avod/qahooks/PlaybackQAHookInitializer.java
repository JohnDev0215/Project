package com.amazon.avod.qahooks;

import android.content.Context;
import com.amazon.avod.drm.QATriggerDrmLicensingFailure;
import com.amazon.avod.media.downloadservice.internal.QATriggerPlaybackBuffer;
import com.amazon.avod.media.events.AloysiusEventHolder;
import com.amazon.avod.media.service.QATriggerManifestAcquisitionFailure;
import com.amazon.avod.media.service.QATriggerPRSError;
import com.amazon.avod.playback.player.QATriggerPlaybackFatal;
import com.amazon.avod.playback.sye.QATriggerSyePlayer;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackQAHookInitializer {
    private static final ImmutableMap<String, Class<? extends QATestFeature>> PLAYBACK_FEATURE_ACTION_MAP = ImmutableMap.builder().put("com.amazon.avod.ALOYSIUS_EVENTS", AloysiusEventHolder.class).put("com.amazon.avod.player.TRIGGER_BUFFER", QATriggerPlaybackBuffer.class).put("com.amazon.avod.player.TRIGGER_FATAL", QATriggerPlaybackFatal.class).put("com.amazon.avod.player.TRIGGER_PRS_ERROR", QATriggerPRSError.class).put("com.amazon.avod.drm.TRIGGER_DRM_LICENSING_FAILURE", QATriggerDrmLicensingFailure.class).put("com.amazon.avod.player.TRIGGER_MANIFEST_ACQUISITION_FAILURE", QATriggerManifestAcquisitionFailure.class).put("com.amazon.avod.FAIL_OVER", QAFailoverFeature.class).put("com.amazon.avod.PROFILER", QAProfilerFeature.class).put("com.amazon.avod.SYE_PLAYER", QATriggerSyePlayer.class).build();
    private final boolean mIsConfigurationAllowed;
    private final PlaybackQASettings mPlaybackQASettings;

    private static class SingletonHolder {
        private static final PlaybackQAHookInitializer INSTANCE = new PlaybackQAHookInitializer();

        private SingletonHolder() {
        }
    }

    public static PlaybackQAHookInitializer getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private PlaybackQAHookInitializer() {
        this.mIsConfigurationAllowed = QaSettings.getInstance().getAppMode().supportsQa();
        this.mPlaybackQASettings = PlaybackQASettings.getInstance();
    }

    public void initializeOverrides(@Nonnull Context context) {
        this.mPlaybackQASettings.initialize(this.mIsConfigurationAllowed, context);
    }

    public void initializeQAHooks(@Nonnull Context context, boolean z) {
        Preconditions.checkNotNull(context, "context");
        if (this.mIsConfigurationAllowed && this.mPlaybackQASettings.isPlaybackQAHooksEnabled()) {
            QAAutomationReceiver qAAutomationReceiver = QAAutomationReceiver.getInstance();
            qAAutomationReceiver.setEnabled(true);
            qAAutomationReceiver.registerFeatureActions(PLAYBACK_FEATURE_ACTION_MAP);
            QAAutomationTestHooks.setEnabled(true);
            QAAutomationTestHooks qAAutomationTestHooks = QAAutomationTestHooks.getInstance();
            qAAutomationTestHooks.activateFeature(AloysiusEventHolder.INSTANCE);
            qAAutomationTestHooks.activateFeature(QATriggerPlaybackBuffer.getInstance());
            qAAutomationTestHooks.activateFeature(QATriggerPlaybackFatal.getInstance());
            qAAutomationTestHooks.activateFeature(QATriggerPRSError.getInstance());
            qAAutomationTestHooks.activateFeature(QATriggerDrmLicensingFailure.getInstance());
            qAAutomationTestHooks.activateFeature(QATriggerManifestAcquisitionFailure.getInstance());
            qAAutomationTestHooks.activateFeature(QAFailoverFeature.getInstance());
            qAAutomationTestHooks.activateFeature(QATriggerSyePlayer.getInstance());
            if (z) {
                qAAutomationTestHooks.activateFeature(new QAProfilerFeature(context));
            }
        }
    }
}
