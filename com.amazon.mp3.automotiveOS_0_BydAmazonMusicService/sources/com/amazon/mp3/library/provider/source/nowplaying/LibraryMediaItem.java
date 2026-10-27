package com.amazon.mp3.library.provider.source.nowplaying;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.harley.LibraryAccessProviderImpl;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.OfflineAvailabilityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackInitiationInfo;
import java.io.File;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryMediaItem extends MediaItem {
    public static int NO_ALBUM_ID;
    private boolean isPreWidevine;
    private TrackType mTrackType;

    /* JADX WARN: Illegal instructions before constructor call */
    public LibraryMediaItem(final MusicTrack track, final PlaybackInitiationInfo playbackInitiationInfo, final MediaCollectionInfo collectionInfo) {
        boolean z;
        MusicTrack musicTrack;
        String title = track.getTitle();
        long millis = TimeUnit.SECONDS.toMillis(track.getDuration());
        MediaItem.Type mediaItemType = getMediaItemType(track);
        if (collectionInfo == null || collectionInfo.getMediaAccessInfo().isAllowedOnline()) {
            z = true;
            musicTrack = track;
        } else {
            musicTrack = track;
            z = false;
        }
        super(null, title, millis, mediaItemType, getMediaAccessInfo(musicTrack, z), null, Playback.getInstance().getPlaybackConfig().getMediaLinkProvider(), Playback.getInstance().getPlaybackConfig().getMediaItemImageUriProvider(), playbackInitiationInfo.getPlaybackPageType(), playbackInitiationInfo.getDirectedPlayStatus().equals(DirectedPlayStatus.DIRECTED), collectionInfo, new MediaCollectionInfo[0]);
        addCollectionId(track.getArtistName(), track.getArtistAsin(), MediaCollectionType.ARTIST, this);
        addCollectionId(track.getAlbumName(), track.getAlbumAsin(), MediaCollectionType.ALBUM, this);
        addAlbumId(Long.toString(track.getAlbumId()), this);
        this.mTrackType = track.getType();
        addAsin(track.getAsin(), this);
        addLuid(track.getLuid(), this);
        if (isTrackOwned(track) && isUserAndTrackKatanaEligible(track) && isCurrentNetworkStreamingSettingInKatana() && (AmazonApplication.getCapabilities().isPurchasedTrackPlaybackInKatanaQualitySupported() || !track.isAvailableOffline())) {
            setOverriddenPrimaryId(new MediaItemId(MediaItemId.Type.ASIN, track.getAsin()));
        }
        addCoid(getCoid(track), this);
        addContentUri(track.getContentUri(), this);
        configureOffline(this, track);
        this.isPreWidevine = OfflineAvailabilityUtil.isPreWidevineAsset(track);
        setContentEncodings(track.getContentEncodings());
        if (track.getAssetQualities() != null) {
            String defaultOrFirstAvailablePID = VideoUtil.INSTANCE.getDefaultOrFirstAvailablePID(track.getAssetQualities());
            if (defaultOrFirstAvailablePID.isEmpty()) {
                return;
            }
            addMediaItemId(new MediaItemId(MediaItemId.Type.PID, defaultOrFirstAvailablePID));
        }
    }

    public TrackType getTrackType() {
        return this.mTrackType;
    }

    public boolean isPreWidevine() {
        return this.isPreWidevine;
    }

    private static MediaItem.Type getMediaItemType(final MusicTrack track) {
        if (track == null) {
            return MediaItem.Type.UNAVAILABLE;
        }
        if (isTrackOwned(track)) {
            return MediaItem.Type.OWNED_CONTENT;
        }
        if (track.getContentCatalogStatus() == null) {
            return MediaItem.Type.UNAVAILABLE;
        }
        if (track.getContentCatalogStatus().isPreviouslyCatalog()) {
            return MediaItem.Type.UNAVAILABLE;
        }
        if (track.getContentCatalogStatus().isPrime()) {
            return MediaItem.Type.PRIME_CONTENT;
        }
        if (track.getContentCatalogStatus().isHawkfire()) {
            return MediaItem.Type.UNLIMITED_CONTENT;
        }
        return MediaItem.Type.UNAVAILABLE;
    }

    private static MediaAccessInfo getMediaAccessInfo(final MusicTrack track, final boolean allowedOnline) {
        if (track == null) {
            return null;
        }
        boolean zIsExplicit = track.isExplicit();
        boolean zIsCastable = track.isCastable();
        boolean z = false;
        boolean zIsAvailableOffline = LibraryAccessProviderImpl.getInstance(Playback.getInstance().getPlaybackConfig().getContext()).isAvailableOffline(track, false);
        boolean zIsTrackOwned = isTrackOwned(track);
        boolean zIsPreviouslyCatalog = track.getContentCatalogStatus().isPreviouslyCatalog();
        boolean z2 = !zIsPreviouslyCatalog && track.getContentCatalogStatus().isPrime();
        boolean z3 = !zIsPreviouslyCatalog && track.getContentCatalogStatus().isSonicRush();
        boolean z4 = !zIsPreviouslyCatalog && track.getContentCatalogStatus().isHawkfire();
        boolean z5 = !zIsPreviouslyCatalog && track.getContentCatalogStatus().isBOP();
        boolean zCanPlayDownloadedKatanaContent = allowedOnline ? canPlayDownloadedKatanaContent(track) : true;
        if (allowedOnline && zCanPlayDownloadedKatanaContent) {
            z = true;
        }
        return MediaAccessInfo.forEligibility(zIsExplicit, zIsCastable, zIsAvailableOffline, z, zIsTrackOwned, z2, z3, z4, z5);
    }

    private static boolean canPlayDownloadedKatanaContent(MusicTrack track) {
        return track.getAsin() == null || ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) != ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION;
    }

    private static boolean isTrackOwned(final MusicTrack track) {
        if (track.getOwnershipStatus() != null) {
            return track.getOwnershipStatus().isOwned();
        }
        return false;
    }

    private static void addCollectionId(final String name, final String asin, final MediaCollectionType collectionType, final MediaItem mediaItem) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        MediaCollectionId mediaCollectionId = !StringUtils.isEmpty(asin) ? new MediaCollectionId(MediaCollectionId.Type.ASIN, asin) : null;
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        mediaItem.addMediaCollectionInfo(new MediaCollectionInfo(name, null, mediaCollectionId, collectionType, MediaAccessInfo.fullAccess(mediaAccessInfo.isAvailableOffline(), mediaAccessInfo.isAllowedOnline()), new MediaCollectionId[0]));
    }

    private static void addAlbumId(final String albumId, final MediaItem mediaItem) {
        if (StringUtils.isEmpty(albumId)) {
            return;
        }
        MediaCollectionId mediaCollectionId = new MediaCollectionId(MediaCollectionId.Type.ALBUM_ART_ID, albumId);
        MediaCollectionInfo mediaCollectionInfo = mediaItem.getMediaCollectionInfo(MediaCollectionType.ALBUM, null);
        if (mediaCollectionInfo != null) {
            mediaCollectionInfo.addId(mediaCollectionId);
        }
    }

    private static void addAsin(final String asin, final MediaItem mediaItem) {
        if (StringUtils.isEmpty(asin)) {
            return;
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.ASIN, asin));
    }

    private static void addLuid(final String luid, final MediaItem mediaItem) {
        if (StringUtils.isEmpty(luid)) {
            return;
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.LUID, luid));
    }

    private static void addCoid(final String coid, final MediaItem mediaItem) {
        if (StringUtils.isEmpty(coid)) {
            return;
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.OWNED, coid));
    }

    private static void addContentUri(final Uri contentUri, final MediaItem mediaItem) {
        if (contentUri == null) {
            return;
        }
        mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.CONTENT_URI, contentUri.toString()));
    }

    private static void configureOffline(final MediaItem mediaItem, final MusicTrack track) {
        String localUri = track.getLocalUri();
        if (StringUtils.isEmpty(localUri)) {
            return;
        }
        if (!(track.isAllOwned() && track.getAsin() != null && ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.PLAY_OFFLINE) == ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK) && canPlayDownloadedKatanaContent(track)) {
            if (track.getLocalUri().startsWith(File.separator)) {
                localUri = Uri.fromFile(new File(localUri)).toString();
            }
            mediaItem.addMediaItemId(new MediaItemId(MediaItemId.Type.LOCAL_URI, localUri));
        }
    }

    private static String getCoid(final MusicTrack track) {
        boolean z = track.isNotOwned() || MediaProvider.Tracks.isPrimeAdditionalTrack(track.getContentUri());
        if ("cirrus-local".equals(track.getSource()) && !z) {
            CirrusSourceLibraryItemFactory cirrusSourceLibraryItemFactory = new CirrusSourceLibraryItemFactory();
            AbstractItem item = cirrusSourceLibraryItemFactory.getItem(cirrusSourceLibraryItemFactory.getUriForAsin(track.getAsin()));
            if (item instanceof MusicTrack) {
                return ((MusicTrack) item).getLuid();
            }
        }
        return track.getLuid();
    }

    private static boolean isUserAndTrackKatanaEligible(final MusicTrack track) {
        return UserSubscriptionUtil.getUserSubscription().isKatana() && ContentEncodingDecoder.getMaxPlaybackAudioQuality(track.getContentEncodings()).isKatanaQuality();
    }

    private static boolean isCurrentNetworkStreamingSettingInKatana() {
        if (!ConnectivityUtil.hasAnyInternetConnection(AmazonApplication.getContext())) {
            return false;
        }
        if (ConnectivityUtil.isWifiConnected(AmazonApplication.getContext())) {
            String multiBitrateStreamingPreferenceForWifi = SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(AmazonApplication.getContext());
            return multiBitrateStreamingPreferenceForWifi.equalsIgnoreCase(StreamingBitrate.KATANA.getEntryValue()) || multiBitrateStreamingPreferenceForWifi.equalsIgnoreCase(StreamingBitrate.AUTO.getEntryValue());
        }
        String multiBitrateStreamingPreferenceForOTA = SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(AmazonApplication.getContext());
        return multiBitrateStreamingPreferenceForOTA.equalsIgnoreCase(StreamingBitrate.KATANA.getEntryValue()) || multiBitrateStreamingPreferenceForOTA.equalsIgnoreCase(StreamingBitrate.AUTO.getEntryValue());
    }
}
