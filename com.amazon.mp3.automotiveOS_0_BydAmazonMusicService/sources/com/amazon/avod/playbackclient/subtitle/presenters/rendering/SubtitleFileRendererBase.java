package com.amazon.avod.playbackclient.subtitle.presenters.rendering;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.perf.TraceKey;
import com.amazon.avod.playbackclient.subtitle.internal.CompositeSubtitleElement;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollection;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleCollectionManager;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer;
import com.amazon.avod.playbackclient.subtitle.views.SubtitleTextController;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SubtitleFileRendererBase implements SubtitleRenderer {
    private SubtitleLanguage mCurrentSubtitleLanguage;
    private boolean mShouldRenderSubtitles;
    private String mSpokenLanguage;
    private final SubtitleCollectionManager mSubtitleCollectionManager;
    private final SubtitleTextController mSubtitleTextController;

    protected abstract long getCurrentVideoPosition();

    public SubtitleFileRendererBase(@Nonnull SubtitleTextController subtitleTextController, @Nonnull SubtitleCollectionManager subtitleCollectionManager, @Nullable String str) {
        this.mSubtitleTextController = (SubtitleTextController) Preconditions.checkNotNull(subtitleTextController, "subtitleTextController");
        this.mSubtitleCollectionManager = (SubtitleCollectionManager) Preconditions.checkNotNull(subtitleCollectionManager, "subtitleCollectionManager");
        this.mSpokenLanguage = str;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void addLanguage(@Nonnull SubtitleLanguage subtitleLanguage, @Nonnull SubtitleCollection subtitleCollection, @Nonnull Optional<Long> optional, @Nonnull Optional<Long> optional2) {
        Preconditions.checkNotNull(subtitleLanguage, "language");
        Preconditions.checkNotNull(subtitleCollection, "subtitleCollection");
        Preconditions.checkNotNull(optional, "startTime");
        Preconditions.checkNotNull(optional2, "endTime");
        this.mSubtitleCollectionManager.updateSubtitles(subtitleLanguage, subtitleCollection, optional, optional2);
        if (this.mCurrentSubtitleLanguage != null || subtitleCollection.isForced()) {
            return;
        }
        updateCurrentSubtitleLanguage(subtitleLanguage);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void updateCurrentSubtitleLanguage(@Nonnull SubtitleLanguage subtitleLanguage) {
        this.mCurrentSubtitleLanguage = (SubtitleLanguage) Preconditions.checkNotNull(subtitleLanguage, "languageCode");
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public SubtitleLanguage getCurrentSubtitleLanguage() {
        return this.mCurrentSubtitleLanguage;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void updateSpokenLanguage(@Nullable String str) {
        this.mSpokenLanguage = str;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void render() {
        CompositeSubtitleElement forcedNarratives;
        TraceKey traceKeyBeginTrace = Profiler.beginTrace(Profiler.TraceLevel.VERBOSE, "SUBTITLE_UPDATE");
        long currentVideoPosition = getCurrentVideoPosition();
        if (this.mShouldRenderSubtitles) {
            forcedNarratives = this.mSubtitleCollectionManager.getSubtitles(this.mCurrentSubtitleLanguage, currentVideoPosition);
        } else {
            forcedNarratives = this.mSubtitleCollectionManager.getForcedNarratives(this.mSpokenLanguage, currentVideoPosition);
        }
        this.mSubtitleTextController.setSubtitle(forcedNarratives);
        this.mSubtitleTextController.showSubtitle();
        Profiler.endTrace(traceKeyBeginTrace);
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void startRenderingSubtitles() {
        this.mShouldRenderSubtitles = true;
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void stopRenderingSubtitles() {
        this.mShouldRenderSubtitles = false;
        this.mSubtitleTextController.hideSubtitle();
        this.mSubtitleTextController.hideSample();
    }

    @Override // com.amazon.avod.playbackclient.subtitle.presenters.SubtitleRenderer
    public void clear() {
        this.mShouldRenderSubtitles = false;
        this.mSubtitleTextController.hideSubtitle();
        this.mSubtitleTextController.hideSample();
        this.mSubtitleCollectionManager.clear();
    }
}
