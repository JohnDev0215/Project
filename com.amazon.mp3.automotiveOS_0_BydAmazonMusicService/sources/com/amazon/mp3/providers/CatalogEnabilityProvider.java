package com.amazon.mp3.providers;

import android.view.View;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.VideoStoryMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.providers.AvailabilityState;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CatalogEnabilityProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\nH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u000bH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\fH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\rH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u000eH\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/providers/CatalogEnabilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "()V", "handleContentAccessUnavailable", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isContentAccessAvailable", "", "shouldEnableContent", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "Lcom/amazon/music/views/library/metadata/VideoStoryMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CatalogEnabilityProvider implements ContentEnabilityProvider {
    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void handleContentAccessUnavailable(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public AvailabilityState getAvailabilityState(ContentMetadata contentMetadata) {
        return ContentEnabilityProvider.DefaultImpls.getAvailabilityState(this, contentMetadata);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public Object isContentAvailable(ContentMetadata contentMetadata, Continuation<? super Boolean> continuation) {
        return ContentEnabilityProvider.DefaultImpls.isContentAvailable(this, contentMetadata, continuation);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void setContentEnabled(View view, BaseViewContentModel baseViewContentModel) {
        ContentEnabilityProvider.DefaultImpls.setContentEnabled(this, view, baseViewContentModel);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void updateViewEnability(View view, boolean z) {
        ContentEnabilityProvider.DefaultImpls.updateViewEnability(this, view, z);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean isContentAccessAvailable(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return shouldEnableContent(metadata);
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean shouldEnableContent(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        if (metadata instanceof TrackMetadata) {
            return shouldEnableContent((TrackMetadata) metadata);
        }
        if (metadata instanceof StationMetadata) {
            return shouldEnableContent((StationMetadata) metadata);
        }
        if (metadata instanceof AlbumMetadata) {
            return shouldEnableContent((AlbumMetadata) metadata);
        }
        if (metadata instanceof PlaylistMetadata) {
            return shouldEnableContent((PlaylistMetadata) metadata);
        }
        if (metadata instanceof ArtistMetadata) {
            return shouldEnableContent((ArtistMetadata) metadata);
        }
        if (metadata instanceof VideoStoryMetadata) {
            return shouldEnableContent((VideoStoryMetadata) metadata);
        }
        return true;
    }

    private final boolean shouldEnableContent(TrackMetadata metadata) {
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && Intrinsics.areEqual(metadata.getIsExplicit(), Boolean.TRUE)) {
            return false;
        }
        return Intrinsics.areEqual(metadata.getIsOwned(), Boolean.TRUE) || ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeTrack(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean shouldEnableContent(StationMetadata metadata) {
        Station stationConvertToStation = PrimeItemsTransformationUtil.convertToStation(metadata);
        return !(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && stationConvertToStation.isExplicit()) && ContentAccessUtil.getCatalogContentUnavailableReason(stationConvertToStation, ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean shouldEnableContent(AlbumMetadata metadata) {
        return Intrinsics.areEqual(metadata.getIsOwned(), Boolean.TRUE) || ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeAlbum(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean shouldEnableContent(PlaylistMetadata metadata) {
        return ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimePlaylist(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean shouldEnableContent(ArtistMetadata metadata) {
        return ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeArtist(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean shouldEnableContent(VideoStoryMetadata metadata) {
        return !AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled();
    }
}
