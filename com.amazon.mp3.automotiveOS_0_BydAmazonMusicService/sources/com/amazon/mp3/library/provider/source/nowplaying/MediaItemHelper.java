package com.amazon.mp3.library.provider.source.nowplaying;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.cloudqueue.sequencer.InterludeMediaItem;
import com.amazon.mp3.download.HarleyDownloadedItem;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.lyrics.item.LyricsTrackInfo;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.mp3.playback.view.lyrics.LyricsProvider;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.station.InterludeItem;
import com.amazon.mp3.util.AlexaTrackRater;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ThreadUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository;
import com.amazon.mp3.view.stage.util.StageLyricsUtil;
import com.amazon.mp3.voice.AlexaTrack;
import com.amazon.music.ContentAccessType;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackProvider;
import com.amazon.music.media.playback.SequencerPlaybackProvider;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.access.MediaAccessPolicy;
import com.amazon.music.media.playback.ratings.RatingsProvider;
import com.amazon.music.media.playback.sequencer.Sequencer;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.share.TrackShareProvider;
import com.amazon.music.share.VideoShareProvider;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Pair;

/* JADX INFO: loaded from: classes3.dex */
public class MediaItemHelper {
    private static final String TAG = "MediaItemHelper";

    public static boolean canDownloadMediaItem(@Nullable final MediaItem mediaItem) {
        return mediaItem != null;
    }

    public static MediaItem createAlexaMediaItem(final AlexaTrack alexaTrack, final PlaybackMetricInformation metricInformation, final MediaCollectionInfo collectionInfo, final AlexaTrackRater alexaTrackRater, final RatingsProvider.OnRatingsChangedListener listener) {
        return new AlexaMediaItem(alexaTrack, buildInitiationInfo(metricInformation, ControlSource.ALEXA), collectionInfo, alexaTrackRater, listener);
    }

    private static PlaybackInitiationInfo buildInitiationInfo(final PlaybackMetricInformation metricInformation, final ControlSource controlSource) {
        return new PlaybackInitiationInfo(metricInformation == null ? DirectedPlayStatus.UNKNOWN : metricInformation.getIsDirectedPlay(), PlaybackControllerProvider.getController(controlSource).isShuffled(), metricInformation == null ? PlaybackPageType.UNKNOWN : metricInformation.getPageType());
    }

    public static boolean isCurrentlyPlayingItem(final String asin) {
        if (asin == null) {
            return false;
        }
        return asin.equals(getAsin(PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem()));
    }

    public static boolean isCurrentlyPlayingOfflineOrNonPrimeItem(final String luid) {
        MediaItemId mediaItemId;
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem == null || luid == null || (mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.LUID)) == null) {
            return false;
        }
        return luid.equals(mediaItemId.getId());
    }

    public static boolean isCurrentlyPlayingCollection(Uri uri) {
        Uri currentUri = getCurrentUri();
        if (uri != null && currentUri != null) {
            if (currentUri.equals(uri)) {
                return true;
            }
            if ("tracks".equals(uri.getLastPathSegment())) {
                if (currentUri.buildUpon().appendEncodedPath("tracks").build().equals(uri)) {
                    return true;
                }
            } else if ("tracks".equals(currentUri.getLastPathSegment()) && uri.buildUpon().appendEncodedPath("tracks").build().equals(currentUri)) {
                return true;
            }
        }
        return false;
    }

    public static Uri getCurrentUri() {
        Sequencer sequencer;
        PlaybackProvider playbackProvider = Playback.getInstance().getPlaybackProvider();
        if (!(playbackProvider instanceof SequencerPlaybackProvider) || (sequencer = ((SequencerPlaybackProvider) playbackProvider).getSequencer()) == null) {
            return null;
        }
        if (!CastingUtil.isCastingToAlexa()) {
            if (sequencer instanceof PlayQueueSequencer) {
                return ((PlayQueueSequencer) sequencer).getUri();
            }
            if (sequencer instanceof CloudQueueSequencer) {
                return ((CloudQueueSequencer) sequencer).getContentUri();
            }
        }
        return getCollectionUriFromMediaItem(PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem());
    }

    public static Uri getCollectionUriFromMediaItem(final MediaItem mediaItem) {
        return getUriFromMediaCollectionInfo(mediaItem != null ? mediaItem.getSourceMediaCollectionInfo() : null);
    }

    private static Uri getUriFromMediaCollectionInfo(final MediaCollectionInfo mediaCollectionInfo) {
        MediaCollectionId id;
        if (mediaCollectionInfo == null) {
            return null;
        }
        MediaCollectionId.Type type = MediaCollectionId.Type.STATION_URI;
        if (mediaCollectionInfo.getId(type) != null) {
            id = mediaCollectionInfo.getId(type);
        } else {
            MediaCollectionId.Type type2 = MediaCollectionId.Type.LIBRARY_COLLECTION;
            if (mediaCollectionInfo.getId(type2) != null) {
                id = mediaCollectionInfo.getId(type2);
            } else {
                MediaCollectionId.Type type3 = MediaCollectionId.Type.MPQS;
                id = (mediaCollectionInfo.getId(type3) == null || CastingUtil.isCastingToAlexa()) ? null : mediaCollectionInfo.getId(type3);
            }
        }
        if (id == null) {
            return null;
        }
        String id2 = id.getId();
        if (id2.isEmpty()) {
            return null;
        }
        return Uri.parse(id2);
    }

    public static long getAlbumId(@Nullable final MediaItem mediaItem) {
        MediaCollectionInfo mediaCollectionInfo;
        MediaCollectionId id;
        if (mediaItem == null || (mediaCollectionInfo = mediaItem.getMediaCollectionInfo(MediaCollectionType.ALBUM, null)) == null || (id = mediaCollectionInfo.getId(MediaCollectionId.Type.ALBUM_ART_ID)) == null) {
            return 3L;
        }
        String id2 = id.getId();
        if (id2.isEmpty()) {
            return 3L;
        }
        long j = Long.parseLong(id2);
        if (j != -1) {
            return j;
        }
        return 3L;
    }

    public static boolean isPureCatalog(@Nullable final MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        if ((mediaAccessInfo == null || mediaAccessInfo.isOwned()) ? false : true) {
            return mediaAccessInfo.getContentAccessTypes().contains(ContentAccessType.HAWKFIRE) || mediaAccessInfo.getContentAccessTypes().contains(ContentAccessType.PRIME) || mediaAccessInfo.getContentAccessTypes().contains(ContentAccessType.NIGHTWING) || mediaAccessInfo.getContentAccessTypes().contains(ContentAccessType.NIGHTWING_ONDEMAND_PLAYABLE);
        }
        return false;
    }

    public static String getAsin(@Nullable final MediaItem mediaItem) {
        MediaItemId mediaItemId;
        if (mediaItem == null || (mediaItemId = mediaItem.getMediaItemId(MediaItemId.Type.ASIN)) == null) {
            return null;
        }
        return mediaItemId.getId();
    }

    public static Uri getContentUri(@Nullable final MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.CONTENT_URI, null);
        if (mediaItemIdString != null) {
            return Uri.parse(mediaItemIdString);
        }
        String source = MediaProvider.getSource(getCurrentUri());
        if (TextUtils.isEmpty(source) || source.equals("prime")) {
            source = "cirrus";
        }
        String mediaItemIdString2 = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
        if (mediaItemIdString2 != null) {
            return MediaProvider.Tracks.getPrimeAdditionalTrackContentUri(source, "primeplaylist", mediaItemIdString2);
        }
        String mediaItemIdString3 = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LUID, null);
        if (mediaItemIdString3 != null) {
            return MediaProvider.Tracks.getContentUri(source, mediaItemIdString3);
        }
        return null;
    }

    public static String getAlbumAsin(@Nullable final MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(mediaCollectionType, type);
        MediaCollectionId id = mediaCollectionInfo != null ? mediaCollectionInfo.getId(type) : null;
        if (id != null) {
            return id.getId();
        }
        return null;
    }

    public static String getArtistAsin(@Nullable final MediaItem mediaItem) {
        if (mediaItem == null) {
            return null;
        }
        MediaCollectionType mediaCollectionType = MediaCollectionType.ARTIST;
        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(mediaCollectionType, type);
        MediaCollectionId id = mediaCollectionInfo != null ? mediaCollectionInfo.getId(type) : null;
        if (id != null) {
            return id.getId();
        }
        return null;
    }

    public static boolean canShareSong(final MediaItem mediaItem) {
        return getAsin(mediaItem) != null;
    }

    public static boolean isMCIDNonnull(final MediaCollectionInfo mediaCollectionInfo) {
        return (mediaCollectionInfo == null || mediaCollectionInfo.getId() == null) ? false : true;
    }

    public static boolean isFromCirrus(final MediaItem mediaItem) {
        return "cirrus".equals(getSource(mediaItem));
    }

    public static String getSource(final MediaItem mediaItem) {
        return (mediaItem == null || mediaItem.getMediaItemId(MediaItemId.Type.LOCAL_URI) == null) ? "cirrus" : "cirrus-local";
    }

    public static ContentUnavailableReason getContentUnavailableReason(final MediaItem mediaItem) {
        ContentAccessType contentAccessType;
        HarleyDownloadedItem downloadedItem;
        if (mediaItem == null) {
            return null;
        }
        if (!mediaItem.isOwned() && (downloadedItem = MusicDownloader.getInstance(AmazonApplication.getContext()).getDownloadedItem(mediaItem.getMediaItemId(MediaItemId.Type.ASIN).getId(), true)) != null && downloadedItem.isAvailableOffline() && downloadedItem.isDownloadedInKatana() && !UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION;
        }
        MediaAccessPolicy mediaAccessPolicy = Playback.getInstance().getPlaybackConfig().getMediaAccessPolicy();
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        if (mediaAccessPolicy.getMediaAccess(mediaAccessInfo).canPlay()) {
            return null;
        }
        MediaAccessInfo.Builder builder = new MediaAccessInfo.Builder(mediaAccessInfo);
        if (mediaAccessInfo.isExplicit() && mediaAccessPolicy.getMediaAccess(builder.withExplicit(false).build()).canPlay()) {
            return ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED;
        }
        if (!mediaAccessInfo.isCastable() && mediaAccessPolicy.getMediaAccess(builder.withCastable(true).build()).canPlay()) {
            return ContentUnavailableReason.CONTENT_TYPE_NOT_CASTABLE;
        }
        if (!mediaAccessInfo.isAvailableOffline() && mediaAccessPolicy.getMediaAccess(builder.withAvailableOffline(true).build()).canPlay()) {
            if ((mediaItem instanceof LibraryMediaItem) && ((LibraryMediaItem) mediaItem).isPreWidevine()) {
                return ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE;
            }
            return ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK;
        }
        if (!mediaAccessInfo.isHawkfire()) {
            return ContentUnavailableReason.TRACK_NO_LONGER_AVAILABLE;
        }
        if (mediaAccessInfo.isSonicRush()) {
            contentAccessType = ContentAccessType.SONIC_RUSH;
        } else {
            contentAccessType = mediaAccessInfo.isPrime() ? ContentAccessType.PRIME : ContentAccessType.HAWKFIRE;
        }
        return ContentAccessUtil.getContentUnavailableReason(contentAccessType, ContentAccessUtil.ContentAccessOperation.STREAM);
    }

    public static boolean shareMediaItem(final Context context, final MediaItem mediaItem) {
        return shareMediaItem(context, mediaItem, (List<String>) null, (PublishSubject<Integer>) null, (Bitmap) null);
    }

    public static void shareMediaItem(final Context context, final MediaItem mediaItem, final boolean shareWithLyrics, final PublishSubject<Integer> openLyricsCardSelectionFragmentSubject, final Bitmap blurAlbumArt) {
        if (!shareWithLyrics) {
            shareMediaItem(context, mediaItem, (List<String>) null, (PublishSubject<Integer>) null, (Bitmap) null);
            return;
        }
        LyricsProvider lyricsProvider = new LyricsProvider(context);
        final String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.ASIN, null);
        if (mediaItemIdString != null) {
            lyricsProvider.fetchLyrics(new LyricsTrackInfo() { // from class: com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper.1
                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                /* JADX INFO: renamed from: getAsin */
                public String get$trackAsin() {
                    return mediaItemIdString;
                }

                @Override // com.amazon.mp3.lyrics.item.LyricsTrackInfo
                public String getMarketplaceId() {
                    return AccountDetailUtil.get(context).getHomeMarketPlaceId();
                }
            }, new LyricsProvider.LoadLyricsCallback() { // from class: com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper.2
                @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
                public void onLoadDelay() {
                }

                @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
                public void onLyricsLoaded(@NonNull Lyrics lyrics) {
                    MediaItemHelper.shareMediaItem(context, mediaItem, (List<String>) MediaItemHelper.getLyricsLinesToShare(lyrics), (PublishSubject<Integer>) openLyricsCardSelectionFragmentSubject, blurAlbumArt);
                }

                @Override // com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
                public void onNoLyrics() {
                    MediaItemHelper.shareMediaItem(context, mediaItem, (List<String>) null, (PublishSubject<Integer>) null, (Bitmap) null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> getLyricsLinesToShare(Lyrics lyrics) {
        ArrayList arrayList = new ArrayList();
        Pair<Integer, Integer> pairFindCurrentPlayingLyricsLines = StageLyricsUtil.INSTANCE.findCurrentPlayingLyricsLines(lyrics);
        arrayList.add(lyrics.getLines().get(pairFindCurrentPlayingLyricsLines.getFirst().intValue()).getLine());
        if (!Objects.equals(pairFindCurrentPlayingLyricsLines.getFirst(), pairFindCurrentPlayingLyricsLines.getSecond())) {
            arrayList.add(lyrics.getLines().get(pairFindCurrentPlayingLyricsLines.getSecond().intValue()).getLine());
        }
        return arrayList;
    }

    public static boolean shareMediaItem(final Context context, final MediaItem mediaItem, final List<String> lyrics, final PublishSubject<Integer> openLyricsCardSelectionFragmentSubject, final Bitmap blurAlbumArt) {
        if (context == null || mediaItem == null) {
            return false;
        }
        String musicDomain = EndpointsProvider.get().getMusicDomain();
        if (MediaItemExtensions.isVideo(mediaItem)) {
            new VideoShareProvider(context, musicDomain, getAsin(mediaItem), mediaItem.getName(), mediaItem.getArtistName()).startShare();
            return true;
        }
        TrackShareProvider trackShareProvider = new TrackShareProvider(context, musicDomain, getAlbumAsin(mediaItem), getAsin(mediaItem), mediaItem.getName(), mediaItem.getArtistName(), new CTAPrimeCache(context).getArtworkUrlForAsin(getAlbumAsin(mediaItem)), lyrics, openLyricsCardSelectionFragmentSubject, getLyricsShareCardsBackgroundBitmaps(blurAlbumArt));
        if (isStage()) {
            trackShareProvider.startShare(PageType.NOW_PLAYING_STAGE);
            return true;
        }
        trackShareProvider.startShare(PageType.NOW_PLAYING);
        return true;
    }

    @NonNull
    private static List<Bitmap> getLyricsShareCardsBackgroundBitmaps(Bitmap blurAlbumArt) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(blurAlbumArt);
        LyricsSharingDataRepository lyricsSharingDataRepository = LyricsSharingDataRepository.INSTANCE;
        lyricsSharingDataRepository.getBackgroundBitmapsLiveData().getValue();
        List<Bitmap> value = lyricsSharingDataRepository.getBackgroundBitmapsLiveData().getValue();
        if (value != null) {
            arrayList.addAll(value);
        }
        return arrayList;
    }

    private static boolean isStage() {
        return AmazonApplication.getCapabilities().isYAImmerseEnabled();
    }

    public static boolean isInterludeMediaItem(final MediaItem mediaItem) {
        return ObjectUtils.isInterlude(mediaItem);
    }

    public static boolean isSideLoadedMediaItem(final MediaItem mediaItem) {
        return getSource(mediaItem).equals("cirrus-local") && getAsin(mediaItem) == null;
    }

    public static String getLuidOrOwnedId(final MediaItem mediaItem) {
        String mediaItemIdString = MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.LUID, null);
        return !TextUtils.isEmpty(mediaItemIdString) ? mediaItemIdString : MediaItem.getMediaItemIdString(mediaItem, MediaItemId.Type.OWNED, null);
    }

    public static AbstractItem getAbstractItem(MediaItem mediaItem) {
        ThreadUtil.dumpStacktraceOnMainThread();
        if (mediaItem != null && mediaItem.isInterlude()) {
            return new InterludeItem(mediaItem);
        }
        return AmazonApplication.getLibraryItemFactory().getItem(getContentUri(mediaItem));
    }

    public static AbstractItem getAbstractItem(MediaItem mediaItem, boolean assignParentEligibility, @Nullable final Uri collectionUri) {
        AbstractItem abstractItem = getAbstractItem(mediaItem);
        if (assignParentEligibility && (abstractItem instanceof MusicTrack)) {
            if (collectionUri == null) {
                collectionUri = createCollectionUriFromMediaItem(mediaItem);
            }
            AbstractItem collectionItem = AmazonApplication.getLibraryItemFactory().getCollectionItem(collectionUri);
            if (collectionItem != null) {
                ((MusicTrack) abstractItem).setParentEligibilities(collectionItem);
            } else {
                Log.warning(TAG, "getAbstractItem, collectionItem is null");
            }
        }
        return abstractItem;
    }

    public static Observable<AbstractItem> getAbstractItemObservable(final MediaItem mediaItem) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                MediaItemHelper.lambda$getAbstractItemObservable$0(mediaItem, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$getAbstractItemObservable$0(MediaItem mediaItem, ObservableEmitter observableEmitter) throws Throwable {
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.getCloudQueueSequencer();
        observableEmitter.onNext(getAbstractItem(mediaItem, true, cloudQueueSequencer != null ? MediaProvider.Tracks.getSanitizedUri(cloudQueueSequencer.getContentUri()) : null));
        observableEmitter.onComplete();
    }

    public static Uri createCollectionUriFromMediaItem(final MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getSourceMediaCollectionInfo() == null) {
            return null;
        }
        MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
        MediaCollectionType type = sourceMediaCollectionInfo.getType();
        String asin = getAsin(mediaItem);
        if (type == MediaCollectionType.ALBUM) {
            return getAlbumUri(mediaItem, sourceMediaCollectionInfo);
        }
        if (type == MediaCollectionType.AUTO_PLAYLIST || type == MediaCollectionType.GNO_PRIME_PLAYLIST || type == MediaCollectionType.GNO_UNL_PLAYLIST || type == MediaCollectionType.GOLDEN_PLAYLIST || type == MediaCollectionType.PRIME_PLAYLIST || type == MediaCollectionType.UNLIMITED_PLAYLIST || type == MediaCollectionType.USER_PLAYLIST || type == MediaCollectionType.SHARED_PLAYLIST) {
            return getPlaylistUri(sourceMediaCollectionInfo);
        }
        if (type == MediaCollectionType.AMF_STATION || type == MediaCollectionType.PRIME_STATION || type == MediaCollectionType.UNLIMITED_STATION) {
            return getStationUri(mediaItem, sourceMediaCollectionInfo, false);
        }
        if (type == MediaCollectionType.AMF_STATION_SEED || type == MediaCollectionType.PRIME_STATION_SEED || type == MediaCollectionType.UNLIMITED_STATION_SEED) {
            return getStationUri(mediaItem, sourceMediaCollectionInfo, true);
        }
        if (type == MediaCollectionType.ARTIST) {
            if (asin != null) {
                return ContentPlaybackUtils.INSTANCE.getArtistUri(asin);
            }
            return null;
        }
        if (type == MediaCollectionType.GENRE) {
            return ContentPlaybackUtils.INSTANCE.getGenreUri();
        }
        return null;
    }

    private static Uri getAlbumUri(final MediaItem mediaItem, final MediaCollectionInfo mediaCollectionInfo) {
        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
        MediaCollectionId.Type type = MediaCollectionId.Type.ASIN;
        MediaCollectionInfo mediaCollectionInfo2 = mediaItem.getMediaCollectionInfo(mediaCollectionType, type);
        String id = (mediaCollectionInfo2 == null || mediaCollectionInfo2.getId(type) == null) ? null : mediaCollectionInfo2.getId(type).getId();
        MediaCollectionInfo mediaCollectionInfo3 = mediaItem.getMediaCollectionInfo(MediaCollectionType.ARTIST, type);
        String name = mediaCollectionInfo3 != null ? mediaCollectionInfo3.getName() : null;
        if (name == null || id == null || mediaCollectionInfo.getName() == null) {
            return null;
        }
        return ContentPlaybackUtils.INSTANCE.getAlbumUri(name, mediaCollectionInfo.getName(), id);
    }

    private static Uri getPlaylistUri(final MediaCollectionInfo mediaCollectionInfo) {
        MediaCollectionId.Type type = MediaCollectionId.Type.SELECTION_SOURCE_ID;
        if (mediaCollectionInfo.getId(type) == null) {
            return null;
        }
        String id = mediaCollectionInfo.getId(type).getId();
        if (mediaCollectionInfo.getType() == MediaCollectionType.USER_PLAYLIST) {
            return ContentPlaybackUtils.INSTANCE.getUserPlaylistLocalUriWithoutTrack(AmazonApplication.getContext(), id);
        }
        if (mediaCollectionInfo.getType() == MediaCollectionType.SHARED_PLAYLIST) {
            return ContentPlaybackUtils.INSTANCE.getUserPlaylistUri();
        }
        return ContentPlaybackUtils.INSTANCE.getPlaylistUri(id);
    }

    private static Uri getStationUri(final MediaItem mediaItem, final MediaCollectionInfo mediaCollectionInfo, final boolean createUriWithSeed) {
        String id;
        if (mediaItem instanceof InterludeMediaItem) {
            id = ((InterludeMediaItem) mediaItem).getInterludeId();
        } else {
            MediaCollectionId.Type type = MediaCollectionId.Type.SELECTION_SOURCE_ID;
            if (mediaCollectionInfo.getId(type) != null) {
                id = mediaCollectionInfo.getId(type).getId();
            } else {
                MediaCollectionId.Type type2 = MediaCollectionId.Type.STATION_KEY;
                id = mediaCollectionInfo.getId(type2) != null ? mediaCollectionInfo.getId(type2).getId() : null;
            }
        }
        if (id == null) {
            return null;
        }
        if (createUriWithSeed) {
            return ContentPlaybackUtils.INSTANCE.getStationUriWithSeed(id);
        }
        return ContentPlaybackUtils.INSTANCE.getStationUri(id);
    }
}
