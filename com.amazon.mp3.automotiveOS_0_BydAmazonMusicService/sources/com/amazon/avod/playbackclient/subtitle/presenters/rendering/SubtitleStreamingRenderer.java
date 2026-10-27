package com.amazon.avod.playbackclient.subtitle.presenters.rendering;

import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playbackclient.live.LiveLanguageTransformer;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleStreamingRenderer implements SubtitleRenderer {
    private final SubtitleRenderer mDelegateRenderer;
    private final LiveLanguageTransformer mLiveLanguageTransformer;
    private final PlaybackExperienceController mPlaybackExperienceController;

    public SubtitleStreamingRenderer(@Nonnull SubtitleRenderer subtitleRenderer, @Nonnull PlaybackExperienceController playbackExperienceController) {
        this(subtitleRenderer, playbackExperienceController, new LiveLanguageTransformer());
    }

    SubtitleStreamingRenderer(@Nonnull SubtitleRenderer subtitleRenderer, @Nonnull PlaybackExperienceController playbackExperienceController, @Nonnull LiveLanguageTransformer liveLanguageTransformer) {
        this.mDelegateRenderer = (SubtitleRenderer) Preconditions.checkNotNull(subtitleRenderer, "DelegateRenderer");
        this.mPlaybackExperienceController = (PlaybackExperienceController) Preconditions.checkNotNull(playbackExperienceController, "PlaybackExperienceController");
        this.mLiveLanguageTransformer = (LiveLanguageTransformer) Preconditions.checkNotNull(liveLanguageTransformer, "liveLanguageTransformer");
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void addLanguage(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, Optional<Long> optional2) {
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(subtitleCollection, "subtitleCollection");
        Preconditions.checkNotNull(optional, "startTime");
        Preconditions.checkNotNull(optional2, "endTime");
        this.mDelegateRenderer.addLanguage(subtitleLanguage, subtitleCollection, optional, optional2);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void updateCurrentSubtitleLanguage(@Nonnull SubtitleLanguage subtitleLanguage) {
        Preconditions.checkNotNull(subtitleLanguage, "language");
        this.mDelegateRenderer.updateCurrentSubtitleLanguage(subtitleLanguage);
        String strTransformLanguageCodeToManifestCode = this.mLiveLanguageTransformer.transformLanguageCodeToManifestCode(subtitleLanguage.getLanguageCode());
        if (strTransformLanguageCodeToManifestCode != null) {
            this.mPlaybackExperienceController.startSubtitleDownload(strTransformLanguageCodeToManifestCode);
        }
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void updateSpokenLanguage(@Nullable String str) {
        this.mDelegateRenderer.updateSpokenLanguage(str);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public SubtitleLanguage getCurrentSubtitleLanguage() {
        return this.mDelegateRenderer.getCurrentSubtitleLanguage();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void render() {
        this.mDelegateRenderer.render();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void startRenderingSubtitles() {
        this.mDelegateRenderer.startRenderingSubtitles();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void stopRenderingSubtitles() {
        this.mDelegateRenderer.stopRenderingSubtitles();
        this.mPlaybackExperienceController.stopSubtitleDownload();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void clear() {
        this.mDelegateRenderer.clear();
    }
}
