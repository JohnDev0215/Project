package com.amazon.mp3.util;

import android.content.Context;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.work.Data;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.detailpages.playlist.actions.PlaylistSFAAction;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.providers.LibraryTracksPlaybackHandler;
import com.amazon.mp3.search.SearchItemUtils;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: PlaybackUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b<\u0010=J6\u0010\f\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bH\u0007J2\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002JD\u0010\u001a\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007J$\u0010\u001d\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007J\"\u0010#\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\b\b\u0002\u0010\u0019\u001a\u00020\"H\u0017J2\u0010&\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\bH\u0007J0\u0010)\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010(\u001a\u00020'H\u0007J \u0010/\u001a\u00020.2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\rH\u0007J\"\u00102\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u00101\u001a\u0002002\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007J(\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00104\u001a\u0002032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0007J\u0016\u00107\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020*2\u0006\u00106\u001a\u000205R\u001c\u0010:\u001a\n 9*\u0004\u0018\u000108088\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;¨\u0006>"}, d2 = {"Lcom/amazon/mp3/util/PlaybackUtils;", "", "Landroidx/fragment/app/Fragment;", "fragment", "Lcom/amazon/mp3/search/model/Station;", "station", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "", "startFullScreen", "showUpsell", "", "startStationPlayback", "", "asin", "identifierType", "shouldPlayFullscreen", "startSFA", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "tracks", "track", "", "startTime", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "taskType", "startTrackPlayback", "Lcom/amazon/mp3/prime/browse/metadata/PrimeAlbum;", "album", "startAlbumPlayback", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/mp3/prime/browse/metadata/PrimePlaylist;", Environment.PLAYLIST_PATH, "Lcom/amazon/mp3/task/GetPrimePlaylistTracksTask$Task;", "startPlaylistPlayback", "task", "isShuffle", "startCommunityPlaylistPlayback", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "startUserPlaylistPlayback", "Landroid/content/Context;", "context", "isOwned", "playlistId", "Landroid/net/Uri;", "generateUserPlaylistContentPlaybackUri", "Lcom/amazon/mp3/prime/browse/metadata/PrimeArtist;", "artist", "startArtistPlayback", "Lcom/amazon/mp3/library/item/CatalogContent;", "item", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "startLibraryTrackPlayback", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlaybackUtils {
    public static final PlaybackUtils INSTANCE = new PlaybackUtils();
    private static final Logger logger = LoggerFactory.getLogger(PlaybackUtils.class.getName());

    private PlaybackUtils() {
    }

    public static /* synthetic */ void startStationPlayback$default(Fragment fragment, Station station, PlaybackPageType playbackPageType, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            z = true;
        }
        if ((i & 16) != 0) {
            z2 = true;
        }
        startStationPlayback(fragment, station, playbackPageType, z, z2);
    }

    @JvmStatic
    public static final void startStationPlayback(Fragment fragment, Station station, PlaybackPageType playbackPageType, boolean startFullScreen, boolean showUpsell) {
        Intrinsics.checkNotNullParameter(station, "station");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        if (fragment == null) {
            return;
        }
        StationUtils.playStation(fragment.getActivity(), station, playbackPageType);
    }

    private final void startSFA(Fragment fragment, String asin, String identifierType, boolean shouldPlayFullscreen, PlaybackPageType playbackPageType) {
        if (asin == null) {
            return;
        }
        if (SearchItemUtils.userIsNightwingOnly()) {
            FreeTierPlaybackUtil.playCloudStation(fragment.requireContext(), new PlayableEntityIdentifier(asin, PlayableEntityIdentifierType.ASIN), true, false, shouldPlayFullscreen, playbackPageType);
        } else {
            StationUtils.startSFA(fragment.requireContext(), asin, identifierType, true, shouldPlayFullscreen, playbackPageType, null, ControlSource.APP_UI, true);
        }
    }

    @JvmStatic
    public static final void startTrackPlayback(Fragment fragment, List<PrimeTrack> tracks, PrimeTrack track, long startTime, PlaybackPageType playbackPageType, PrimeCollectionTask.Task taskType) {
        LoaderManager supportLoaderManager;
        Intrinsics.checkNotNullParameter(track, "track");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        if (fragment == null || tracks == null) {
            return;
        }
        if (fragment instanceof SearchBrushFragment) {
            ((SearchBrushFragment) fragment).getViewModel().clearTrackPlaybackState();
        }
        int iIndexOf = tracks.indexOf(track);
        if (iIndexOf <= -1 || iIndexOf >= tracks.size()) {
            return;
        }
        PrimeTracksLoadedCallbacks primeTracksLoadedCallbacks = new PrimeTracksLoadedCallbacks(fragment.getActivity(), tracks, iIndexOf, playbackPageType, startTime, taskType);
        FragmentActivity activity = fragment.getActivity();
        if (activity == null || (supportLoaderManager = activity.getSupportLoaderManager()) == null) {
            return;
        }
        supportLoaderManager.initLoader(0, null, primeTracksLoadedCallbacks);
    }

    public final void startLibraryTrackPlayback(Context context, ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        MusicTrack track = new CirrusSourceLibraryItemFactory(context).getTrack(contentMetadata.getUri());
        if (track == null) {
            return;
        }
        List listListOf = CollectionsKt__CollectionsJVMKt.listOf(track);
        LibraryTracksPlaybackHandler.initiatePlayback$default(LibraryTracksPlaybackHandler.INSTANCE, context, track, listListOf, listListOf.size(), 0, false, LibraryTracksFragment.INSTANCE.getUri(), null, null, null, null, false, 3072, null);
    }

    @JvmStatic
    public static final void startAlbumPlayback(Fragment fragment, PrimeAlbum album, PrimeCollectionTask.Task taskType) {
        FragmentActivity activity;
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        if (fragment == null || (activity = fragment.getActivity()) == null) {
            return;
        }
        PrimeCollectionTask.createPlaybackTask(activity, album.getAsin(), PlaybackPageType.PRIME_BROWSE_ALBUMS_LIST, new QueryAlbumByAsin(activity), taskType).execute();
    }

    @JvmStatic
    public static void startPlaylistPlayback(FragmentActivity activity, PrimePlaylist playlist, GetPrimePlaylistTracksTask.Task taskType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        GetPrimePlaylistTracksTask.createPlaybackTask(activity, playlist.getAsin(), 0, PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, taskType).execute(new Void[0]);
    }

    public static /* synthetic */ void startCommunityPlaylistPlayback$default(FragmentActivity fragmentActivity, PrimePlaylist primePlaylist, PlaybackPageType playbackPageType, GetPrimePlaylistTracksTask.Task task, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        startCommunityPlaylistPlayback(fragmentActivity, primePlaylist, playbackPageType, task, z);
    }

    @JvmStatic
    public static final void startCommunityPlaylistPlayback(FragmentActivity activity, PrimePlaylist playlist, PlaybackPageType playbackPageType, GetPrimePlaylistTracksTask.Task task, boolean isShuffle) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(task, "task");
        int trackCount = playlist.getTrackCount();
        GetPrimePlaylistTracksTask getPrimePlaylistTracksTaskCreatePlaybackTask = GetPrimePlaylistTracksTask.createPlaybackTask(activity, playlist.getPlaylistId(), (!isShuffle || trackCount <= 0) ? 0 : RangesKt___RangesKt.random(new IntRange(0, trackCount), Random.INSTANCE), playbackPageType, task);
        getPrimePlaylistTracksTaskCreatePlaybackTask.withShuffleState(isShuffle);
        getPrimePlaylistTracksTaskCreatePlaybackTask.execute(new Void[0]);
    }

    @JvmStatic
    public static final void startUserPlaylistPlayback(FragmentActivity activity, PrimePlaylist playlist, boolean shouldPlayFullscreen, final PlaybackPageType playbackPageType, PageType pageType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(playlist, "playlist");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        String playlistId = playlist.getPlaylistId();
        if (playlistId == null) {
            return;
        }
        if (SearchItemUtils.userIsNightwingOnly()) {
            Uri contentUri = MediaProvider.UdoPlaylists.getContentUriFromLuid(activity, "cirrus", playlistId);
            Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
            new PlaylistSFAAction(activity, contentUri, pageType).startLibraryPlaylistSFA(null, shouldPlayFullscreen);
            return;
        }
        final Uri uriGenerateUserPlaylistContentPlaybackUri = generateUserPlaylistContentPlaybackUri(activity, playlist.isAllOwned(), playlistId);
        String title = playlist.getTitle();
        Uri nonTrackCollectionUri = MediaProvider.Tracks.getNonTrackCollectionUri(uriGenerateUserPlaylistContentPlaybackUri);
        Uri uri = nonTrackCollectionUri == null ? uriGenerateUserPlaylistContentPlaybackUri : nonTrackCollectionUri;
        Intrinsics.checkNotNullExpressionValue(uri, "MediaProvider.Tracks.get…contentUri) ?: contentUri");
        ControlSource APP_UI = ControlSource.APP_UI;
        Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
        StartPlaybackUtil.startCollectionPlayback(activity, (Data.MAX_DATA_BYTES & 2) != 0 ? null : title, uri, null, (Data.MAX_DATA_BYTES & 16) != 0 ? 0 : 0, (Data.MAX_DATA_BYTES & 32) != 0 ? false : false, (Data.MAX_DATA_BYTES & 64) != 0 ? true : shouldPlayFullscreen, (Data.MAX_DATA_BYTES & 128) != 0 ? null : null, playbackPageType, (Data.MAX_DATA_BYTES & 512) != 0 ? false : false, APP_UI, (Data.MAX_DATA_BYTES & 2048) != 0 ? null : null, (Data.MAX_DATA_BYTES & 4096) != 0 ? false : false, (Data.MAX_DATA_BYTES & 8192) != 0 ? null : null, (Data.MAX_DATA_BYTES & 16384) != 0 ? null : null, (Data.MAX_DATA_BYTES & 32768) != 0 ? false : false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.util.PlaybackUtils$startUserPlaylistPlayback$1$1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                Uri uriAppendTracksIfNeeded = ContentPlaybackUtils.INSTANCE.appendTracksIfNeeded(uriGenerateUserPlaylistContentPlaybackUri);
                if (uriAppendTracksIfNeeded == null) {
                    return;
                }
                PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, uriAppendTracksIfNeeded, null, null, null, 0, false, new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackPageType), null, System.currentTimeMillis());
            }
        });
    }

    @JvmStatic
    public static final Uri generateUserPlaylistContentPlaybackUri(Context context, boolean isOwned, String playlistId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playlistId, "playlistId");
        if (isOwned) {
            Uri uriAppendTracksToUri = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.UdoPlaylists.getContentUriFromLuid(context, "cirrus", playlistId));
            Intrinsics.checkNotNullExpressionValue(uriAppendTracksToUri, "{\n            MediaProvi…S, playlistId))\n        }");
            return uriAppendTracksToUri;
        }
        Uri uriAppendTracksToUri2 = MediaProvider.UdoPlaylists.Tracks.appendTracksToUri(MediaProvider.SharedUserPlaylists.getContentUri("cirrus", playlistId));
        Intrinsics.checkNotNullExpressionValue(uriAppendTracksToUri2, "{\n            MediaProvi…S, playlistId))\n        }");
        return uriAppendTracksToUri2;
    }

    @JvmStatic
    public static final void startArtistPlayback(FragmentActivity activity, PrimeArtist artist, PrimeCollectionTask.Task taskType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(taskType, "taskType");
        new PrimeArtistPlaybackHandler(activity).startPlayback(artist, taskType);
    }

    @JvmStatic
    public static final void startSFA(Fragment fragment, CatalogContent item, PlaybackPageType playbackPageType, boolean shouldPlayFullscreen) {
        Unit unit;
        String playlistId;
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        if (item instanceof PrimeTrack) {
            INSTANCE.startSFA(fragment, ((PrimeTrack) item).getAsin(), "TRACK_SEED", shouldPlayFullscreen, playbackPageType);
            return;
        }
        if (item instanceof PrimeAlbum) {
            INSTANCE.startSFA(fragment, ((PrimeAlbum) item).getAsin(), "ALBUM_SEED", shouldPlayFullscreen, playbackPageType);
            return;
        }
        if (!(item instanceof PrimePlaylist)) {
            if (item instanceof PrimeArtist) {
                INSTANCE.startSFA(fragment, ((PrimeArtist) item).getAsin(), "ARTIST_SEED", shouldPlayFullscreen, playbackPageType);
                return;
            } else {
                logger.warn("Did not start SFA. No matching entity type.");
                return;
            }
        }
        PrimePlaylist primePlaylist = (PrimePlaylist) item;
        String asin = primePlaylist.getAsin();
        if (asin == null) {
            unit = null;
        } else {
            INSTANCE.startSFA(fragment, asin, "PLAYLIST_SEED", shouldPlayFullscreen, playbackPageType);
            unit = Unit.INSTANCE;
        }
        if (unit != null || (playlistId = primePlaylist.getPlaylistId()) == null) {
            return;
        }
        INSTANCE.startSFA(fragment, playlistId, "PLAYLIST_ID", shouldPlayFullscreen, playbackPageType);
    }
}
