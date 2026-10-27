package com.amazon.mp3.util;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModeIngressAutoPlayTypes;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackHandlerUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0005\u001a\u00020\u0011J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0011J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0015¨\u0006\u001a"}, d2 = {"Lcom/amazon/mp3/util/PlaybackHandlerUtil;", "", "Landroid/content/Context;", "context", "Lcom/amazon/music/views/library/metadata/UserPlaylistMetadata;", "contentMetadata", "Landroid/net/Uri;", "generateUserPlaylistContentUri", "", "eventName", "eventSource", MediaTrack.ROLE_DESCRIPTION, "", "emitPlaybackFlexEvent", "Lcom/amazon/mp3/auto/carmode/CarModeIngressAutoPlayTypes;", "autoPlayType", "emitAutoPlayFlexEvent", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/mp3/cloudqueue/model/PlayableEntityIdentifier;", "getEntityIdentifier", "getIdentifierType", "Lcom/amazon/music/views/library/metadata/PlaylistMetadata;", "", "isOnDemandPlaylist", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackHandlerUtil {
    public static final PlaybackHandlerUtil INSTANCE = new PlaybackHandlerUtil();

    private PlaybackHandlerUtil() {
    }

    public final Uri generateUserPlaylistContentUri(Context context, UserPlaylistMetadata contentMetadata) {
        Uri contentUriFromLuid;
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(context, contentMetadata.getPlaylistId())) {
            contentUriFromLuid = MediaProvider.SharedUserPlaylists.getContentUri("cirrus", contentMetadata.getPlaylistId());
        } else {
            contentUriFromLuid = context == null ? null : MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", contentMetadata.getPlaylistId());
        }
        if (contentUriFromLuid == null) {
            return null;
        }
        return MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(contentUriFromLuid);
    }

    public final void emitPlaybackFlexEvent(String eventName, String eventSource, String description) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Intrinsics.checkNotNullParameter(eventSource, "eventSource");
        Intrinsics.checkNotNullParameter(description, "description");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder(eventName).withFlexStr1(eventSource).withFlexStr2(description).build());
    }

    public final void emitAutoPlayFlexEvent(CarModeIngressAutoPlayTypes autoPlayType) {
        Intrinsics.checkNotNullParameter(autoPlayType, "autoPlayType");
        MetricsHolder.getManager().handleEvent(FlexEvent.builder("CarModeIngressAutoPlay").withHcFlexStr1(Playback.getInstance().getPlaybackConfig().getPlaybackInstanceId().toString()).withHcFlexStr2(autoPlayType.name()).build());
    }

    public final PlayableEntityIdentifier getEntityIdentifier(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof TrackMetadata) {
            return new PlayableEntityIdentifier(((TrackMetadata) contentMetadata).getAsin(), PlayableEntityIdentifierType.ASIN);
        }
        if (contentMetadata instanceof AlbumMetadata) {
            return new PlayableEntityIdentifier(((AlbumMetadata) contentMetadata).getAsin(), PlayableEntityIdentifierType.ASIN);
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            return new PlayableEntityIdentifier(((PlaylistMetadata) contentMetadata).getAsin(), PlayableEntityIdentifierType.ASIN);
        }
        if (contentMetadata instanceof UserPlaylistMetadata) {
            return new PlayableEntityIdentifier(((UserPlaylistMetadata) contentMetadata).getPlaylistId(), PlayableEntityIdentifierType.PLAYLIST_ID);
        }
        if (contentMetadata instanceof ArtistMetadata) {
            return new PlayableEntityIdentifier(((ArtistMetadata) contentMetadata).getAsin(), PlayableEntityIdentifierType.ASIN);
        }
        if (!(contentMetadata instanceof StationMetadata)) {
            return null;
        }
        if (MediaProvider.Station.isStationFromAnything(contentMetadata.getUri())) {
            return new PlayableEntityIdentifier(((StationMetadata) contentMetadata).getKey(), PlayableEntityIdentifierType.STATION_SEED_ID);
        }
        return new PlayableEntityIdentifier(((StationMetadata) contentMetadata).getKey(), PlayableEntityIdentifierType.STATION_KEY);
    }

    public final String getIdentifierType(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof TrackMetadata) {
            return "TRACK_SEED";
        }
        if (contentMetadata instanceof AlbumMetadata) {
            return "ALBUM_SEED";
        }
        if (contentMetadata instanceof PlaylistMetadata) {
            return "PLAYLIST_SEED";
        }
        if (contentMetadata instanceof UserPlaylistMetadata) {
            return "TRACK_LIST_SEED";
        }
        if (contentMetadata instanceof StationMetadata) {
            return MediaProvider.Station.isStationFromAnything(contentMetadata.getUri()) ? "STATION_SEED_ID" : "STATION_KEY";
        }
        if (contentMetadata instanceof ArtistMetadata) {
            return "ARTIST_SEED";
        }
        return null;
    }

    public final boolean isOnDemandPlaylist(PlaylistMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        return AmazonApplication.getCapabilities().isOnDemandPlaylistsEnabled() && UserSubscriptionUtil.getUserSubscription().isNightwingOnly() && GetOnDemandPlaylistsService.INSTANCE.get().isOnDemandPlaylist(contentMetadata.getAsin());
    }
}
