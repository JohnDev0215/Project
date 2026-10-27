package com.amazon.avod.media.ads.internal;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdEnabledVideoEventListener;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.internal.state.AdPlanUpdateListener;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoPresentationEventListener;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdEnabledPlaybackManager {
    boolean addAdPlanUpdateListener(@Nonnull AdPlanUpdateListener adPlanUpdateListener);

    @Nonnull
    AdPlaybackStateMachineContext getContext();

    @Nonnull
    AdPlan getPlan();

    void initialize();

    boolean isAdPlayerPrepared();

    void launchPlayback();

    void pause();

    void play();

    void seekTo(TimeSpan timeSpan);

    void setAdEventListener(AdEnabledVideoEventListener adEnabledVideoEventListener);

    void setPresentationEventListener(VideoPresentation videoPresentation, VideoPresentationEventListener videoPresentationEventListener);

    void setRenderingSettings(VideoRenderingSettings videoRenderingSettings);

    void shutdown(boolean z);

    void updateAdPlan(@Nonnull AdPlan adPlan);
}
