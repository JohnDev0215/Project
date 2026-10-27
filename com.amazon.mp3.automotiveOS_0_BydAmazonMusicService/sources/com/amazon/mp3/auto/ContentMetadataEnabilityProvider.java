package com.amazon.mp3.auto;

import android.view.View;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.base.BaseViewContentModel;
import com.amazon.music.views.library.providers.AvailabilityState;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContentMetadataEnabilityProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\fH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\rH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u000eH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u000fH\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0010H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0011H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/auto/ContentMetadataEnabilityProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "()V", "canStreamContent", "", "content", "Lcom/amazon/mp3/library/item/CatalogContent;", "handleContentAccessUnavailable", "", "metadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "isContentAccessAvailable", "Lcom/amazon/music/views/library/metadata/AlbumMetadata;", "Lcom/amazon/music/views/library/metadata/ArtistMetadata;", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "Lcom/amazon/music/views/library/metadata/PodcastMetadata;", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "Lcom/amazon/music/views/library/metadata/TrackMetadata;", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "shouldEnableContent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContentMetadataEnabilityProvider implements ContentEnabilityProvider {
    private final boolean isContentAccessAvailable(PodcastMetadata metadata) {
        return true;
    }

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
        if (metadata instanceof AlbumMetadata) {
            return isContentAccessAvailable((AlbumMetadata) metadata);
        }
        if (metadata instanceof PlaylistMetadata) {
            return isContentAccessAvailable((PlaylistMetadata) metadata);
        }
        if (metadata instanceof UserPlaylistMetadata) {
            return isContentAccessAvailable((UserPlaylistMetadata) metadata);
        }
        if (metadata instanceof StationMetadata) {
            return isContentAccessAvailable((StationMetadata) metadata);
        }
        if (metadata instanceof ArtistMetadata) {
            return isContentAccessAvailable((ArtistMetadata) metadata);
        }
        if (metadata instanceof TrackMetadata) {
            return isContentAccessAvailable((TrackMetadata) metadata);
        }
        if (metadata instanceof PodcastMetadata) {
            return isContentAccessAvailable((PodcastMetadata) metadata);
        }
        return false;
    }

    @Override // com.amazon.music.views.library.providers.ContentEnabilityProvider
    public boolean shouldEnableContent(ContentMetadata metadata) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        return isContentAccessAvailable(metadata);
    }

    private final boolean isContentAccessAvailable(StationMetadata metadata) {
        Station station = PrimeItemsTransformationUtil.convertToStation(metadata);
        Intrinsics.checkNotNullExpressionValue(station, "station");
        return canStreamContent(station);
    }

    private final boolean isContentAccessAvailable(AlbumMetadata metadata) {
        if (Intrinsics.areEqual(metadata.getIsOwned(), Boolean.TRUE)) {
            return true;
        }
        PrimeAlbum album = PrimeItemsTransformationUtil.convertToPrimeAlbum(metadata);
        Intrinsics.checkNotNullExpressionValue(album, "album");
        return canStreamContent(album);
    }

    private final boolean isContentAccessAvailable(PlaylistMetadata metadata) {
        if (PlaybackHandlerUtil.INSTANCE.isOnDemandPlaylist(metadata)) {
            return true;
        }
        PrimePlaylist playlist = PrimeItemsTransformationUtil.convertToPrimePlaylist(metadata);
        Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
        return canStreamContent(playlist);
    }

    private final boolean isContentAccessAvailable(ArtistMetadata metadata) {
        PrimeArtist artist = PrimeItemsTransformationUtil.convertToPrimeArtist(metadata);
        Intrinsics.checkNotNullExpressionValue(artist, "artist");
        return canStreamContent(artist);
    }

    private final boolean isContentAccessAvailable(TrackMetadata metadata) {
        PrimeTrack track = PrimeItemsTransformationUtil.convertToPrimeTrack(metadata);
        Intrinsics.checkNotNullExpressionValue(track, "track");
        return canStreamContent(track);
    }

    private final boolean isContentAccessAvailable(UserPlaylistMetadata metadata) {
        if (UserSubscriptionUtil.isNightwingOnly() && !Feature.should_migrate_free_to_cq_for_non_station.isEnabled()) {
            return false;
        }
        PrimePlaylist playlist = PrimeItemsTransformationUtil.convertToPrimePlaylist(metadata);
        Intrinsics.checkNotNullExpressionValue(playlist, "playlist");
        return canStreamContent(playlist);
    }

    private final boolean canStreamContent(CatalogContent content) {
        return ContentAccessUtil.getCatalogContentUnavailableReason(content, ContentAccessUtil.ContentAccessOperation.STREAM) == null;
    }
}
