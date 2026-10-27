package com.amazon.avod.qahooks;

import android.content.Intent;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.google.common.base.Enums;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class QAFailoverFeature implements QATestFeature {
    private PlaybackExperienceController mPlaybackExperienceController;

    private static class SingletonHolder {
        private static final QAFailoverFeature INSTANCE = new QAFailoverFeature();

        private SingletonHolder() {
        }
    }

    public static QAFailoverFeature getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private QAFailoverFeature() {
    }

    public void prepare(@Nonnull PlaybackExperienceController playbackExperienceController) {
        this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "playbackExperienceController");
    }

    @Override // com.amazon.avod.qahooks.QATestFeature
    public void handleIntent(@Nonnull Intent intent) {
        Preconditions.checkNotNull(intent, MAPAccountManager.KEY_INTENT);
        FailoverType failoverTypeForValue = FailoverType.forValue(intent.getStringExtra("action"));
        FailoverMode failoverMode = (FailoverMode) Enums.getIfPresent(FailoverMode.class, Strings.nullToEmpty(intent.getStringExtra("mode"))).orNull();
        DLog.logf("Received intent with action %s. failover type %s, failover mode %s", intent.getAction(), failoverTypeForValue, failoverMode);
        PlaybackExperienceController playbackExperienceController = this.mPlaybackExperienceController;
        if (playbackExperienceController != null) {
            playbackExperienceController.manuallyTriggerFailover(failoverTypeForValue, failoverMode);
        }
    }
}
