package com.amazon.mp3.detailpages.album.util;

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
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.providers.AvailabilityState;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NightwingAlbumEnabilityProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\nH\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u000bH\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\fH\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/detailpages/album/util/NightwingAlbumEnabilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "()V", "handleContentAccessUnavailable", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isContentAccessAvailable", "", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "shouldEnableContent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NightwingAlbumEnabilityProvider implements ContentEnabilityProvider {
    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public void handleContentAccessUnavailable(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean shouldEnableContent(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return true;
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
        if (metadata instanceof TrackMetadata) {
            return isContentAccessAvailable((TrackMetadata) metadata);
        }
        if (metadata instanceof StationMetadata) {
            return isContentAccessAvailable((StationMetadata) metadata);
        }
        if (metadata instanceof AlbumMetadata) {
            return isContentAccessAvailable((AlbumMetadata) metadata);
        }
        if (metadata instanceof PlaylistMetadata) {
            return isContentAccessAvailable((PlaylistMetadata) metadata);
        }
        if (metadata instanceof ArtistMetadata) {
            return isContentAccessAvailable((ArtistMetadata) metadata);
        }
        return true;
    }

    private final boolean isContentAccessAvailable(TrackMetadata metadata) {
        if (AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && Intrinsics.areEqual(metadata.getIsExplicit(), Boolean.TRUE)) {
            return false;
        }
        return Intrinsics.areEqual(metadata.getIsOwned(), Boolean.TRUE) || ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeTrack(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean isContentAccessAvailable(StationMetadata metadata) {
        Station stationConvertToStation = PrimeItemsTransformationUtil.convertToStation(metadata);
        return !(AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled() && stationConvertToStation.isExplicit()) && ContentAccessUtil.getCatalogContentUnavailableReason(stationConvertToStation, ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean isContentAccessAvailable(AlbumMetadata metadata) {
        return Intrinsics.areEqual(metadata.getIsOwned(), Boolean.TRUE) || ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeAlbum(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean isContentAccessAvailable(PlaylistMetadata metadata) {
        return ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimePlaylist(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }

    private final boolean isContentAccessAvailable(ArtistMetadata metadata) {
        return ContentAccessUtil.getCatalogContentUnavailableReason(PrimeItemsTransformationUtil.convertToPrimeArtist(metadata), ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
    }
}
