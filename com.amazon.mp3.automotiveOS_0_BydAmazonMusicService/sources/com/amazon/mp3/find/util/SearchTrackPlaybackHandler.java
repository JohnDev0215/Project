package com.amazon.mp3.find.util;

import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchTrackPlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0011B!\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/find/util/SearchTrackPlaybackHandler;", "Lcom/amazon/mp3/util/ContentAccessUtil$CatalogActionListener;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "Lcom/amazon/mp3/find/util/BaseSearchTrackPlaybackHandler;", "mFragment", "Landroidx/fragment/app/Fragment;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "shouldEnableCircularQueue", "", "getShouldEnableCircularQueue", "()Z", "shouldShowTrackDetailsPage", "getShouldShowTrackDetailsPage", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchTrackPlaybackHandler extends BaseSearchTrackPlaybackHandler {
    private static final List<SearchExperienceMode> modesSupportingCircularQueue = CollectionsKt__CollectionsKt.listOf((Object[]) new SearchExperienceMode[]{SearchExperienceMode.SEARCH_RESULT, SearchExperienceMode.SEE_ALL});
    private final Fragment mFragment;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public SearchTrackPlaybackHandler(Fragment mFragment, PageType pageType, PlaybackPageType playbackPageType) {
        super(mFragment, pageType, playbackPageType);
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.mFragment = mFragment;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getShouldEnableCircularQueue() {
        Fragment fragment = this.mFragment;
        return (fragment instanceof SearchBrushFragment) && modesSupportingCircularQueue.contains(((SearchBrushFragment) fragment).getCurrentMode()) && !AmazonApplication.getCapabilities().isRelatedEntitySearchEnabled();
    }

    @Override // com.amazon.mp3.find.util.BaseSearchTrackPlaybackHandler
    public boolean getShouldShowTrackDetailsPage() {
        Boolean boolValueOf = null;
        if (getMTrackMetadata() != null) {
            EligibilityUtil eligibilityUtil = new EligibilityUtil(null, 1, null);
            TrackMetadata mTrackMetadata = getMTrackMetadata();
            if (mTrackMetadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.platform.providers.EntityEligibilitiesProvider");
            }
            boolValueOf = Boolean.valueOf(!Intrinsics.areEqual(eligibilityUtil.getOnDemand(mTrackMetadata), Boolean.TRUE) && AmazonApplication.getCapabilities().isShowTrackDetailsEnabled());
        }
        if (boolValueOf == null) {
            return false;
        }
        return boolValueOf.booleanValue();
    }
}
