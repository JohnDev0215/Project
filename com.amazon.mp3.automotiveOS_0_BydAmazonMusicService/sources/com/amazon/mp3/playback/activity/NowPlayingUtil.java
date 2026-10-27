package com.amazon.mp3.playback.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.FreeTierSequencer;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.LibraryMediaItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playback.KatanaNPSBadgeProvider;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.PlaybackAudioQualityToAudioQuality;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.store.activity.HTMLStoreActivityFactory;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackAudioQuality;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.impl.DefaultPlaybackProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.sequencer.impl.StationSequencer;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.voice.VoiceManagerSingleton;
import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingUtil {
    private static final String TAG = "NowPlayingUtil";
    private static final Float SMALL_SCREEN_THRESHOLD_WIDTH_PIXELS = Float.valueOf(720.0f);
    private static final PlaybackController sPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);

    public static boolean isDownloaded(final MediaItem item) {
        return false;
    }

    public static void clearAndFinishNowPlaying(final Context context, final PlayStateMutationReason playStateMutationReason) {
        clearAndFinishNowPlaying(context, true, playStateMutationReason);
    }

    public static void clearAndFinishNowPlaying(final Context context, final boolean clearPlayState, final PlayStateMutationReason playStateMutationReason) {
        Intent intent = new Intent("com.amazon.mp3.playerservicecommand.stop");
        intent.putExtra("com.amazon.mp3.playstate_mutation_reason", playStateMutationReason);
        context.sendBroadcast(intent);
        context.sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.finish"));
        context.sendBroadcast(new Intent("com.amazon.mp3.playback.view.miniplayer.hide"));
        if (clearPlayState) {
            PlayerWidgetController.getInstance().clearWidget(context);
        }
        clear();
    }

    public static void showNowPlaying(final Context context) {
        context.sendBroadcast(new Intent("com.amazon.mp3.library.activity.nowplaying.show"));
        context.sendBroadcast(new Intent("com.amazon.mp3.playback.view.miniplayer.show"));
    }

    public static void clearCollection(final Context context, final Uri uri, final PlayStateMutationReason playStateMutationReason) {
        if (uri.equals(NowPlayingManager.getInstance().getCurrentUri())) {
            clearAndFinishNowPlaying(context, playStateMutationReason);
        }
    }

    public static void clear() {
        Log.debug(TAG, "Clearing now playing list");
        PlaybackControllerProvider.getController(ControlSource.APP_UI).stop(ChangeReason.SOURCE_MODIFIED);
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.mp3.playback.activity.NowPlayingUtil.1
            @Override // java.lang.Runnable
            public void run() {
                PlayQueueSequencer.getInstance().clear();
            }
        });
    }

    public static String getTrackString() {
        MediaItem currentMediaItem = sPlaybackController.getCurrentMediaItem();
        return currentMediaItem == null ? "" : currentMediaItem.getName();
    }

    public static String getArtistName() {
        MediaItem currentMediaItem = sPlaybackController.getCurrentMediaItem();
        if (currentMediaItem != null) {
            return currentMediaItem.getArtistName();
        }
        return null;
    }

    public static Intent getAlbumDetailIntent(final Context context) {
        MediaItem currentMediaItem = sPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return null;
        }
        String source = ((currentMediaItem instanceof LibraryMediaItem) && ((LibraryMediaItem) currentMediaItem).isPreWidevine()) ? "cirrus" : MediaItemHelper.getSource(currentMediaItem);
        String albumName = currentMediaItem.getAlbumName();
        String albumAsin = MediaItemHelper.getAlbumAsin(currentMediaItem);
        long albumId = MediaItemHelper.getAlbumId(currentMediaItem);
        if (albumId <= 0) {
            albumId = IdGenerator.generateAlbumId(albumAsin, albumName);
        }
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(context, MediaProvider.Albums.getContentUri(source, albumId));
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", source);
        return intentForContentUri;
    }

    public static Intent getArtistExploreIntent(final Context context) {
        String artistName = getArtistName();
        if (!ShopLinkUtil.isArtistBlacklisted(artistName)) {
            Uri artistContentUri = getArtistContentUri("artist_id");
            return ShopLinkUtil.getArtistExploreIntent(context, artistContentUri == null ? null : String.valueOf(MediaProvider.Artists.getArtistId(artistContentUri)), artistName, ItemWrapper$ItemType.ARTIST);
        }
        return HTMLStoreActivityFactory.getStartIntent(context);
    }

    public static Uri getArtistContentUri(String column) {
        Uri trackContentUri = getTrackContentUri();
        if (trackContentUri == null) {
            return null;
        }
        long jLongFromCursor = DbUtil.longFromCursor(AmazonApplication.getContext().getContentResolver().query(trackContentUri, new String[]{column}, null, null, null));
        if (jLongFromCursor == -1) {
            return null;
        }
        return MediaProvider.Artists.getContentUri(MediaProvider.getSource(trackContentUri), jLongFromCursor);
    }

    public static Intent getNowPlayingIntent(Uri uri, String sortOrder, int trackPosition, FragmentActivity activity) {
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.NowPlaying.CONTENT_URI);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_URI", uri);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER", sortOrder);
        intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_TRACK_POSITION", trackPosition);
        return intentForContentUri;
    }

    public static Uri getTrackContentUri() {
        MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
        if (currentTrack == null) {
            return null;
        }
        return getTrackContentUri(currentTrack);
    }

    private static Uri getTrackContentUri(MusicTrack track) {
        Uri contentUri = track.getContentUri();
        return (contentUri != null || track.getLuid() == null) ? contentUri : MediaProvider.Tracks.getContentUri("cirrus", track.getLuid());
    }

    public static boolean isSFAStationPlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        if (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null) {
            return false;
        }
        return mediaCollectionInfo.getType().equals(MediaCollectionType.UNLIMITED_STATION_SEED) || mediaCollectionInfo.getType().equals(MediaCollectionType.PRIME_STATION_SEED) || mediaCollectionInfo.getType().equals(MediaCollectionType.AMF_STATION_SEED);
    }

    public static boolean isGoldenPlaylistPlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null || !mediaCollectionInfo.getType().equals(MediaCollectionType.GOLDEN_PLAYLIST)) ? false : true;
    }

    public static boolean isAMFStationPlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null || !mediaCollectionInfo.getType().equals(MediaCollectionType.AMF_STATION)) ? false : true;
    }

    public static boolean isPrimeOrAMUStationPlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null || (!mediaCollectionInfo.getType().equals(MediaCollectionType.UNLIMITED_STATION) && !mediaCollectionInfo.getType().equals(MediaCollectionType.PRIME_STATION))) ? false : true;
    }

    public static boolean isUserPlaylistPlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null || !mediaCollectionInfo.getType().equals(MediaCollectionType.USER_PLAYLIST)) ? false : true;
    }

    public static boolean isStationPlaying() {
        if (AmazonApplication.getCapabilities().isCloudQueuePlaybackEnabled()) {
            SequencerPlaybackProvider sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider();
            if (sequencerPlaybackProvider.getSequencer() instanceof StationSequencer) {
                return true;
            }
            if ((sequencerPlaybackProvider.getSequencer() instanceof FreeTierSequencer) && ((FreeTierSequencer) sequencerPlaybackProvider.getSequencer()).isStationPlayback()) {
                return true;
            }
            return (sequencerPlaybackProvider.getSequencer() instanceof CloudQueueSequencer) && ((CloudQueueSequencer) sequencerPlaybackProvider.getSequencer()).getIsOnlineInteraction();
        }
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getType() == null || (!mediaCollectionInfo.getType().equals(MediaCollectionType.PRIME_STATION) && !mediaCollectionInfo.getType().equals(MediaCollectionType.UNLIMITED_STATION) && !mediaCollectionInfo.getType().equals(MediaCollectionType.UNLIMITED_STATION_SEED) && !mediaCollectionInfo.getType().equals(MediaCollectionType.PRIME_STATION_SEED))) ? false : true;
    }

    public static boolean isCloudStationPlaying() {
        if (Playback.getInstance().getPlaybackProvider() instanceof SequencerPlaybackProvider) {
            return ((SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider()).getSequencer() instanceof FreeTierSequencer;
        }
        return false;
    }

    public static boolean isAlexaPlaying() {
        SequencerPlaybackProvider sequencerPlaybackProvider;
        return (!(Playback.getInstance().getPlaybackProvider() instanceof SequencerPlaybackProvider) || (sequencerPlaybackProvider = (SequencerPlaybackProvider) Playback.getInstance().getPlaybackProvider()) == null || sequencerPlaybackProvider.getSequencer() == null || sequencerPlaybackProvider.getSequencer().getMediaCollectionInfo() == null || sequencerPlaybackProvider.getSequencer().getMediaCollectionInfo().getId() == null || sequencerPlaybackProvider.getSequencer().getMediaCollectionInfo().getId().getId() == null || DefaultUriMatcher.match(Uri.parse(sequencerPlaybackProvider.getSequencer().getMediaCollectionInfo().getId().getId())) != 35 || !VoiceManagerSingleton.isInitialized()) ? false : true;
    }

    public static boolean isVideoPlaying() {
        return MediaItemExtensions.isVideo(Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getCurrentMediaItem());
    }

    public static boolean isSideLoadedTrackPlaying() {
        MediaItem currentMediaItem = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getCurrentMediaItem();
        return currentMediaItem != null && MediaItemHelper.getSource(currentMediaItem).equals("cirrus-local") && MediaItemHelper.getAsin(currentMediaItem) == null;
    }

    public static boolean isStartOfPlayQueue() {
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if (!(playbackProvider instanceof DefaultPlaybackProvider)) {
            return false;
        }
        List<MediaItem> previousMediaItems = ((DefaultPlaybackProvider) playbackProvider).getSequencer().getPreviousMediaItems(1);
        return previousMediaItems == null || previousMediaItems.isEmpty();
    }

    public static boolean isVideoPresentInPlayQueue(final Context context) {
        boolean z;
        Cursor cursorQueryNowPlayingTracks = new NowPlayingQueueHelper(context).queryNowPlayingTracks(null, "");
        while (true) {
            try {
                if (!cursorQueryNowPlayingTracks.moveToNext()) {
                    z = false;
                    break;
                }
                if (PrimePlaylistTracksTable.AssetType.VIDEO == AmazonApplication.getLibraryItemFactory().getTrack(cursorQueryNowPlayingTracks).getAssetType()) {
                    z = true;
                    break;
                }
            } catch (Throwable th) {
                if (cursorQueryNowPlayingTracks != null) {
                    try {
                        cursorQueryNowPlayingTracks.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        cursorQueryNowPlayingTracks.close();
        return z;
    }

    public static boolean isAdjacentItemVideo(boolean checkNext) {
        List<MediaItem> previousMediaItems;
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if (!(playbackProvider instanceof DefaultPlaybackProvider)) {
            return false;
        }
        Sequencer sequencer = ((DefaultPlaybackProvider) playbackProvider).getSequencer();
        if (checkNext) {
            previousMediaItems = sequencer.getUpcomingMediaItems(1);
        } else {
            previousMediaItems = sequencer.getPreviousMediaItems(1);
        }
        return (previousMediaItems == null || previousMediaItems.isEmpty() || !MediaItemExtensions.isVideo(previousMediaItems.get(0))) ? false : true;
    }

    public static boolean isCloudqueuePlaying() {
        MediaCollectionInfo mediaCollectionInfo = sPlaybackController.getMediaCollectionInfo();
        return (mediaCollectionInfo == null || mediaCollectionInfo.getPrimaryType() == null || !MediaCollectionId.Type.MPQS.equals(mediaCollectionInfo.getPrimaryType())) ? false : true;
    }

    public static boolean isExplicitTrackPlaying() {
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        return (currentMediaItem == null || currentMediaItem.getMediaAccessInfo() == null || !currentMediaItem.getMediaAccessInfo().isExplicit()) ? false : true;
    }

    public static boolean isPlayingChanged(final MediaItem savedMediaItem, final boolean savedIsPlaying) {
        PlaybackController playbackController = sPlaybackController;
        return (playbackController.getCurrentMediaItem() == savedMediaItem && playbackController.getPlayStatus().shouldBePlaying() == savedIsPlaying) ? false : true;
    }

    public static boolean isVideoContent(final Uri contentUri) {
        MusicTrack track;
        if (!MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri)) {
            return MediaProvider.Tracks.isTrack(contentUri) && (track = AmazonApplication.getLibraryItemFactory().getTrack(contentUri)) != null && PrimePlaylistTracksTable.AssetType.VIDEO.equals(track.getAssetType());
        }
        CatalogPlaylist primePlaylist = AmazonApplication.getLibraryItemFactory().getPrimePlaylist(contentUri);
        if (primePlaylist == null) {
            return false;
        }
        Collection<CatalogPlaylistTrack> tracks = primePlaylist.getTracks();
        return tracks.size() != 0 && PrimePlaylistTracksTable.AssetType.VIDEO.equals(tracks.iterator().next().getAssetType());
    }

    public static String getCurrentQualityBadgeTitle(final Resources resources) {
        PlaybackAudioQualityToAudioQuality playbackAudioQualityToAudioQuality = new PlaybackAudioQualityToAudioQuality();
        PlaybackAudioQuality currentPlaybackAudioQuality = KatanaNPSBadgeProvider.getCurrentPlaybackAudioQuality();
        if (playbackAudioQualityToAudioQuality.isHD(currentPlaybackAudioQuality)) {
            return resources.getString(R.string.dmusic_nerd_stats_hd_quality);
        }
        if (playbackAudioQualityToAudioQuality.isUHD(currentPlaybackAudioQuality)) {
            return resources.getString(R.string.dmusic_nerd_stats_ultra_hd_quality);
        }
        if (playbackAudioQualityToAudioQuality.isAtmos(currentPlaybackAudioQuality)) {
            return resources.getString(R.string.dmusic_nerd_stats_dolby_atmos_quality);
        }
        if (playbackAudioQualityToAudioQuality.isRa360(currentPlaybackAudioQuality)) {
            return resources.getString(R.string.dmusic_nerd_stats_360_quality);
        }
        if (playbackAudioQualityToAudioQuality.isStandard(currentPlaybackAudioQuality)) {
            return resources.getString(R.string.dmusic_nps_audio_quality_badge_sd_text);
        }
        return null;
    }

    public static boolean isSmallScreen(Context context) {
        return ((float) context.getResources().getDisplayMetrics().widthPixels) <= SMALL_SCREEN_THRESHOLD_WIDTH_PIXELS.floatValue();
    }
}
