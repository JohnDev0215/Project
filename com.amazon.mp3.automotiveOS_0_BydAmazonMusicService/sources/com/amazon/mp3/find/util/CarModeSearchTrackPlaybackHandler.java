package com.amazon.mp3.find.util;

import androidx.fragment.app.Fragment;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeSearchTrackPlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u0010X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/find/util/CarModeSearchTrackPlaybackHandler;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lcom/amazon/mp3/find/util/BaseSearchTrackPlaybackHandler;", "mFragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "primeCollectionTask", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "getPrimeCollectionTask", "()Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "shouldEnableCircularQueue", "", "getShouldEnableCircularQueue", "()Z", "shouldPlayFullscreen", "getShouldPlayFullscreen", "shouldTrackEntitiesForBlendedSearchHistory", "getShouldTrackEntitiesForBlendedSearchHistory", "useDefaultFallbackHandling", "getUseDefaultFallbackHandling", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeSearchTrackPlaybackHandler extends BaseSearchTrackPlaybackHandler {
    private final Fragment mFragment;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;
    private final PrimeCollectionTask.Task primeCollectionTask;
    private final boolean shouldEnableCircularQueue;
    private final boolean shouldPlayFullscreen;
    private final boolean shouldTrackEntitiesForBlendedSearchHistory;
    private final boolean useDefaultFallbackHandling;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeSearchTrackPlaybackHandler(Fragment mFragment, PageType pageType, PlaybackPageType playbackPageType) {
        super(mFragment, pageType, playbackPageType);
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.mFragment = mFragment;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.primeCollectionTask = PrimeCollectionTask.Task.PLAYBACK;
        this.shouldEnableCircularQueue = true;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getUseDefaultFallbackHandling() {
        return this.useDefaultFallbackHandling;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public PrimeCollectionTask.Task getPrimeCollectionTask() {
        return this.primeCollectionTask;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getShouldEnableCircularQueue() {
        return this.shouldEnableCircularQueue;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getShouldPlayFullscreen() {
        return this.shouldPlayFullscreen;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getShouldTrackEntitiesForBlendedSearchHistory() {
        return this.shouldTrackEntitiesForBlendedSearchHistory;
    }
}
