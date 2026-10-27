package com.amazon.mp3.artist.view;

import UpsellInterface.v1_0.UpsellType;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.browse.metadata.RecommendationReason;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.ImageUrlModifier;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.ContentAccessTypeKt;
import com.amazon.music.ContentAccessType;
import com.amazon.music.casting.session.things.MetadataThingShadow;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.metrics.mts.event.types.ContentSubscriptionMode;
import com.amazon.music.platform.data.subscription.SubscriptionType;
import com.amazon.music.station.StationItem;
import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.Justification;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import com.amazon.music.storeservice.model.Review;
import com.amazon.music.storeservice.model.TrackItem;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.CommunityPlaylistMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.musicensembleservice.ParentalControls;
import com.amazon.musicensembleservice.StationSubscriptionTierDetail;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.BooleanUtils;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeItemsTransformationUtil {
    private static final String TAG = "PrimeItemsTransformationUtil";

    public static List<PrimeTrack> convertAlbumTracksToPrimeTracks(final Album album) {
        List<Track> tracks;
        ArrayList arrayList = new ArrayList();
        if (album != null && (tracks = album.getTracks()) != null && !tracks.isEmpty()) {
            for (Track track : tracks) {
                if (track == null || track.getAsin() == null) {
                    Log.warning(TAG, "Discarding track with null asin");
                } else {
                    try {
                        PrimeTrack primeTrack = toPrimeTrack(track, album);
                        if (primeTrack != null) {
                            arrayList.add(primeTrack);
                        }
                    } catch (Exception unused) {
                        Log.error(TAG, "failure to convert track name: " + track.getTitle() + " asin: " + track.getAsin() + " in album: " + album.getTitle() + " to PrimeTrack");
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<PrimeTrack> toPrimeTrackList(final Collection<TrackItem> trackList) {
        if (trackList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(trackList.size());
        for (TrackItem trackItem : trackList) {
            if (trackItem == null || trackItem.getAsin() == null) {
                Log.warning(TAG, "Discarding track with null asin");
            } else {
                arrayList.add(toPrimeTrack(trackItem));
            }
        }
        return arrayList;
    }

    public static List<PrimeTrack> toPrimeTrackListFromRecommendations(final Collection<RecommendedTrackItem> trackList) {
        if (trackList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(trackList.size());
        for (RecommendedTrackItem recommendedTrackItem : trackList) {
            if (recommendedTrackItem == null || recommendedTrackItem.getAsin() == null) {
                Log.warning(TAG, "Discarding track with null asin");
            } else {
                Boolean bool = Boolean.TRUE;
                if (bool.equals(recommendedTrackItem.isIsPrime()) || bool.equals(recommendedTrackItem.isIsMusicSubscription())) {
                    arrayList.add(toPrimeTrackFromRecommendation(recommendedTrackItem));
                }
            }
        }
        return arrayList;
    }

    public static List<PrimeAlbum> toPrimeAlbumList(final Collection<AlbumItem> albumList) {
        if (albumList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(albumList.size());
        for (AlbumItem albumItem : albumList) {
            if (albumItem == null || albumItem.getAsin() == null) {
                Log.warning(TAG, "Discarding album with null asin");
            } else {
                arrayList.add(toPrimeAlbum(albumItem));
            }
        }
        return arrayList;
    }

    public static List<PrimeAlbum> toPrimeAlbumListFromRecommendations(final Collection<RecommendedAlbumItem> albumList) {
        if (albumList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(albumList.size());
        for (RecommendedAlbumItem recommendedAlbumItem : albumList) {
            if (recommendedAlbumItem == null || recommendedAlbumItem.getAsin() == null) {
                Log.warning(TAG, "Discarding album with null asin");
            } else {
                Boolean bool = Boolean.TRUE;
                if (bool.equals(recommendedAlbumItem.isIsPrime()) || bool.equals(recommendedAlbumItem.isIsMusicSubscription())) {
                    arrayList.add(toPrimeAlbumFromRecommendations(recommendedAlbumItem));
                }
            }
        }
        return arrayList;
    }

    public static List<PrimePlaylist> toPrimePlaylistList(Collection<PlaylistItem> playlistList) {
        if (playlistList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(playlistList.size());
        for (PlaylistItem playlistItem : playlistList) {
            if (playlistItem == null || playlistItem.getAsin() == null) {
                Log.warning(TAG, "Discarding playlist with null asin");
            } else {
                arrayList.add(toPrimePlaylist(playlistItem));
            }
        }
        return arrayList;
    }

    public static List<PrimePlaylist> toPrimePlaylistListFromRecommendations(Collection<RecommendedPlaylistItem> playlistList) {
        if (playlistList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(playlistList.size());
        for (RecommendedPlaylistItem recommendedPlaylistItem : playlistList) {
            if (recommendedPlaylistItem == null || recommendedPlaylistItem.getAsin() == null) {
                Log.warning(TAG, "Discarding playlist with null asin");
            } else {
                arrayList.add(toPrimePlaylistFromRecommendations(recommendedPlaylistItem));
            }
        }
        return arrayList;
    }

    public static List<Station> toStationListFromRecommendations(List<RecommendedStationItem> stationList) {
        if (stationList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (RecommendedStationItem recommendedStationItem : stationList) {
            if (recommendedStationItem == null || recommendedStationItem.getStationKey() == null) {
                Log.warning(TAG, "Discarding station with null key");
            } else {
                arrayList.add(toPrimeStation(recommendedStationItem));
            }
        }
        return arrayList;
    }

    public static PrimeTrack toPrimeTrack(TrackItem trackItem) {
        ContentOwnershipStatus contentOwnershipStatus;
        LyricsStatus lyricsStatus;
        Boolean bool = Boolean.TRUE;
        CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(bool.equals(trackItem.isIsPrime()), bool.equals(trackItem.isIsMusicSubscription()), bool.equals(trackItem.isIsFree()), bool.equals(trackItem.isIsFreeOnDemandPlayable()), bool.equals(trackItem.isIsSonicRush()), bool.equals(trackItem.isIsSonicRushOnDemandPlayable()));
        if (BooleanUtils.isTrue(trackItem.isOwned())) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = BooleanUtils.isTrue(trackItem.isIsInLibrary()) ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        ContentOwnershipStatus contentOwnershipStatus2 = contentOwnershipStatus;
        String title = trackItem.getTitle();
        String asin = trackItem.getAsin();
        String image = trackItem.getAlbum().getImage();
        String name = trackItem.getArtist().getName();
        String asin2 = trackItem.getArtist().getAsin();
        String title2 = trackItem.getAlbum().getTitle();
        String asin3 = trackItem.getAlbum().getAsin();
        String albumArtistName = trackItem.getAlbumArtistName();
        PrimePlaylistTracksTable.AssetType assetType = PrimePlaylistTracksTable.AssetType.VIDEO;
        PrimeTrack primeTrack = new PrimeTrack(title, asin, image, name, asin2, title2, asin3, albumArtistName, assetType.toString().equals(trackItem.getAssetType()), contentOwnershipStatus2, catalogStatusTiers);
        primeTrack.setDuration(trackItem.getDuration());
        boolean z = true;
        if (ChildUserUtil.INSTANCE.isChildUser(AmazonApplication.getApplication().getApplicationContext())) {
            primeTrack.setTrackNum(trackItem.getItemIndex().intValue() + 1);
        } else {
            String trackNum = trackItem.getTrackNum();
            if (trackNum == null) {
                primeTrack.setTrackNum(1);
            } else {
                primeTrack.setTrackNum(Integer.parseInt(trackNum));
            }
        }
        Integer discNum = trackItem.getDiscNum();
        if (discNum == null) {
            discNum = 1;
        }
        primeTrack.setDiscNum(discNum.intValue());
        if (bool.equals(trackItem.isHasLyrics())) {
            lyricsStatus = LyricsStatus.AVAILABLE;
        } else {
            lyricsStatus = LyricsStatus.NO_LYRICS;
        }
        primeTrack.setLyricsStatus(lyricsStatus);
        if (!assetType.toString().equals(trackItem.getAssetType()) && !isExplicit(trackItem.getParentalControls())) {
            z = false;
        }
        primeTrack.setIsExplicit(z);
        primeTrack.setPopularity(trackItem.getTransformedPopularity());
        primeTrack.setContentEncoding(ContentEncodingDecoder.loadFrom(trackItem.getContentEncoding()));
        primeTrack.setAssetQualities(BrushConverterUtils.INSTANCE.convertAssetQualityItem(trackItem.getAssetQualities()));
        if (!assetType.toString().equals(trackItem.getAssetType())) {
            assetType = PrimePlaylistTracksTable.AssetType.AUDIO;
        }
        primeTrack.setAssetType(assetType);
        primeTrack.setContentEncoding(ContentEncodingDecoder.loadFrom(trackItem.getContentEncoding()));
        primeTrack.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(trackItem.getPlaymodeEligibility()));
        return primeTrack;
    }

    public static PrimeTrack toPrimeTrack(final MetadataThingShadow.Track track) {
        LyricsStatus lyricsStatus;
        boolean zEquals = ContentSubscriptionMode.PRIME.getAttribute().equals(track.serviceTier);
        boolean zEquals2 = ContentSubscriptionMode.UNLIMITED.getAttribute().equals(track.serviceTier);
        boolean zEquals3 = ContentSubscriptionMode.AMF.getAttribute().equals(track.serviceTier);
        String[] strArr = track.serviceTiers;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < track.serviceTiers.length; i++) {
                if (ContentSubscriptionMode.PRIME.getAttribute().equals(track.serviceTiers[i])) {
                    zEquals = true;
                } else if (ContentSubscriptionMode.UNLIMITED.getAttribute().equals(track.serviceTiers[i])) {
                    zEquals2 = true;
                } else if (ContentSubscriptionMode.AMF.getAttribute().equals(track.serviceTiers[i])) {
                    zEquals3 = true;
                }
            }
        }
        CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(zEquals, zEquals2, zEquals3, false, false, false);
        ContentOwnershipStatus contentOwnershipStatus = ContentOwnershipStatus.NOT_IN_LIBRARY;
        MetadataThingShadow.Track.Artist[] artistArr = track.artists;
        String str = (artistArr == null || artistArr.length <= 0) ? "" : artistArr[0].asin;
        HashMap<String, String> map = track.artUrlMap;
        PrimeTrack primeTrack = new PrimeTrack(track.name, track.asin, map != null ? map.get("FULL") : "", track.artistName, str, track.albumName, track.albumAsin, track.albumArtistName, false, contentOwnershipStatus, catalogStatusTiers);
        Long l = track.durationInMs;
        if (l != null) {
            primeTrack.setDuration(Integer.valueOf((int) TimeUnit.MILLISECONDS.toSeconds(l.longValue())));
        }
        primeTrack.setDiscNum(1);
        if (Boolean.TRUE.equals(track.hasLyrics)) {
            lyricsStatus = LyricsStatus.AVAILABLE;
        } else {
            lyricsStatus = LyricsStatus.NO_LYRICS;
        }
        primeTrack.setLyricsStatus(lyricsStatus);
        primeTrack.setPlaymodeEligibility(null);
        return primeTrack;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0129  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.amazon.mp3.prime.browse.metadata.PrimeTrack toPrimeTrack(final com.amazon.musicensembleservice.brush.Track r16, final com.amazon.musicensembleservice.brush.Album r17) {
        /*
            Method dump skipped, instruction units count: 372
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.artist.view.PrimeItemsTransformationUtil.toPrimeTrack(com.amazon.musicensembleservice.brush.Track, com.amazon.musicensembleservice.brush.Album):com.amazon.mp3.prime.browse.metadata.PrimeTrack");
    }

    public static PrimeTrack toPrimeTrackFromRecommendation(RecommendedTrackItem trackItem) {
        RecommendationReason recommendationReason;
        String albumArtImageUrl = trackItem.getAlbumArtImageUrl();
        String url = !TextUtils.isEmpty(albumArtImageUrl) ? ImageUrlModifier.with(albumArtImageUrl).clearLastModifier().toUrl() : "";
        Boolean bool = Boolean.TRUE;
        PrimeTrack primeTrack = new PrimeTrack(trackItem.getTitle(), trackItem.getAsin(), url, trackItem.getArtistName(), trackItem.getArtistAsin(), trackItem.getAlbumName(), trackItem.getAlbumAsin(), trackItem.getArtistName(), false, ContentOwnershipStatus.NOT_IN_LIBRARY, new CatalogStatusTiers(bool.equals(trackItem.isIsPrime()), bool.equals(trackItem.isIsMusicSubscription()), bool.equals(trackItem.isIsFree()), bool.equals(trackItem.isIsFreeOnDemandPlayable()), bool.equals(trackItem.isIsSonicRush()), bool.equals(trackItem.isIsSonicRushOnDemandPlayable())));
        Justification justification = trackItem.getJustification();
        if (justification != null) {
            recommendationReason = new RecommendationReason(justification.getReason());
        } else {
            recommendationReason = new RecommendationReason("");
        }
        primeTrack.setRecommendationReason(recommendationReason);
        primeTrack.setDuration(trackItem.getDurationSeconds());
        primeTrack.setIsExplicit(isExplicit(trackItem.getParentalControls()));
        primeTrack.setContentEncoding(ContentEncodingDecoder.loadFrom(trackItem.getContentEncoding()));
        primeTrack.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(trackItem.getPlaymodeEligibility()));
        return primeTrack;
    }

    public static PrimeAlbum toPrimeAlbum(AlbumItem albumItem) {
        Boolean bool = Boolean.TRUE;
        PrimeAlbum primeAlbum = new PrimeAlbum(albumItem.getTitle(), albumItem.getAsin(), albumItem.getImage(), albumItem.getArtist().getName(), albumItem.getArtist().getAsin(), albumItem.getTrackCount().intValue(), ContentOwnershipStatus.NOT_IN_LIBRARY, new CatalogStatusTiers(bool.equals(albumItem.isIsPrime()), bool.equals(albumItem.isIsMusicSubscription()), bool.equals(albumItem.isIsFree()), bool.equals(albumItem.isIsFreeOnDemandPlayable()), bool.equals(albumItem.isIsSonicRush()), bool.equals(albumItem.isIsSonicRushOnDemandPlayable())));
        Review reviews = albumItem.getReviews();
        primeAlbum.setRating(getAverageRatingInFloat(reviews.getAverage()));
        primeAlbum.setNumRatings(reviews.getTotal().intValue());
        primeAlbum.setContentEncoding(ContentEncodingDecoder.loadFrom(albumItem.getContentEncoding()));
        primeAlbum.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(albumItem.getPlaymodeEligibility()));
        return primeAlbum;
    }

    public static PrimeAlbum toPrimeAlbumFromRecommendations(RecommendedAlbumItem albumItem) {
        RecommendationReason recommendationReason;
        String albumArtImageUrl = albumItem.getAlbumArtImageUrl();
        String url = !TextUtils.isEmpty(albumArtImageUrl) ? ImageUrlModifier.with(albumArtImageUrl).clearLastModifier().toUrl() : "";
        Boolean bool = Boolean.TRUE;
        PrimeAlbum primeAlbum = new PrimeAlbum(albumItem.getAlbumName(), albumItem.getAsin(), url, albumItem.getArtistName(), albumItem.getArtistAsin(), albumItem.getTotalNumberOfTracks().intValue(), ContentOwnershipStatus.NOT_IN_LIBRARY, new CatalogStatusTiers(bool.equals(albumItem.isIsPrime()), bool.equals(albumItem.isIsMusicSubscription()), bool.equals(albumItem.isIsFree()), bool.equals(albumItem.isIsFreeOnDemandPlayable()), bool.equals(albumItem.isIsSonicRush()), bool.equals(albumItem.isIsSonicRushOnDemandPlayable())));
        primeAlbum.setRating(getAverageRatingInFloat(albumItem.getAverageOverallRating()));
        primeAlbum.setNumRatings(albumItem.getTotalReviewCount().intValue());
        Justification justification = albumItem.getJustification();
        if (justification != null) {
            recommendationReason = new RecommendationReason(justification.getReason());
        } else {
            recommendationReason = new RecommendationReason("");
        }
        primeAlbum.setRecommendationReason(recommendationReason);
        primeAlbum.setContentEncoding(ContentEncodingDecoder.loadFrom(albumItem.getContentEncoding()));
        primeAlbum.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(albumItem.getPlaymodeEligibility()));
        return primeAlbum;
    }

    public static PrimePlaylist toPrimePlaylist(PlaylistItem playlistItem) {
        Review reviews = playlistItem.getReviews();
        Boolean bool = Boolean.TRUE;
        CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(bool.equals(playlistItem.isIsPrime()), bool.equals(playlistItem.isIsMusicSubscription()), bool.equals(playlistItem.isIsFree()), bool.equals(playlistItem.isIsFreeOnDemandPlayable()), bool.equals(playlistItem.isIsSonicRush()), bool.equals(playlistItem.isIsSonicRushOnDemandPlayable()));
        PrimePlaylist primePlaylist = new PrimePlaylist(playlistItem.getTitle(), playlistItem.getAsin(), playlistItem.getImage(), playlistItem.getDescription(), getAverageRatingInFloat(reviews.getAverage()), reviews.getTotal() != null ? reviews.getTotal().intValue() : 0, playlistItem.getTrackCount() != null ? playlistItem.getTrackCount().intValue() : 0, playlistItem.getAuthors(), getAssetTypes(playlistItem.getContentTypes()), catalogStatusTiers);
        primePlaylist.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(playlistItem.getPlaymodeEligibility()));
        return primePlaylist;
    }

    private static List<PrimePlaylistTracksTable.AssetType> getAssetTypes(final List<String> contentTypes) {
        if (contentTypes == null) {
            return null;
        }
        PrimePlaylistTracksTable.AssetType assetType = PrimePlaylistTracksTable.AssetType.VIDEO;
        if (contentTypes.contains(assetType.toString())) {
            return Arrays.asList(assetType);
        }
        return null;
    }

    public static PrimePlaylist toPrimePlaylistFromRecommendations(RecommendedPlaylistItem playlistItem) {
        RecommendationReason recommendationReason;
        Justification justification = playlistItem.getJustification();
        if (justification != null) {
            recommendationReason = new RecommendationReason(justification.getReason());
        } else {
            recommendationReason = new RecommendationReason("");
        }
        Boolean bool = Boolean.TRUE;
        CatalogStatusTiers catalogStatusTiers = new CatalogStatusTiers(bool.equals(playlistItem.isIsPrime()), bool.equals(playlistItem.isIsMusicSubscription()), bool.equals(playlistItem.isIsFree()), bool.equals(playlistItem.isIsFreeOnDemandPlayable()), bool.equals(playlistItem.isIsSonicRush()), bool.equals(playlistItem.isIsSonicRushOnDemandPlayable()));
        PrimePlaylist primePlaylist = new PrimePlaylist(playlistItem.getTitle(), playlistItem.getAsin(), playlistItem.getAlbumArtImageUrl(), playlistItem.getDescription(), getAverageRatingInFloat(playlistItem.getAverageOverallRating()), playlistItem.getTotalReviewCount() != null ? playlistItem.getTotalReviewCount().intValue() : 0, playlistItem.getTrackCount() != null ? playlistItem.getTrackCount().intValue() : 0, playlistItem.getAuthorNames(), null, catalogStatusTiers);
        primePlaylist.setRecommendationReason(recommendationReason);
        primePlaylist.setPlaymodeEligibility(BrushConverterUtils.convertPlaymodeEligibility(playlistItem.getPlaymodeEligibility()));
        return primePlaylist;
    }

    private static float getAverageRatingInFloat(final Double averageRating) {
        if (averageRating != null) {
            return averageRating.floatValue();
        }
        return 0.0f;
    }

    public static Station toPrimeStation(final RecommendedStationItem recommendedStationItem) {
        Justification justification = recommendedStationItem.getJustification();
        if (justification != null) {
            new RecommendationReason(justification.getReason());
        } else {
            new RecommendationReason("");
        }
        return new Station(new CatalogStatusTiers(recommendedStationItem.isIsPrime().booleanValue(), recommendedStationItem.isIsMusicSubscription().booleanValue(), recommendedStationItem.isIsFree().booleanValue(), false), recommendedStationItem.getStationTitle(), recommendedStationItem.getStationImageUrl(), recommendedStationItem.getStationKey(), buildExplicitMap(isStationExplicit(recommendedStationItem.getSubscriptionTierToDetails(), "MUSIC_SUBSCRIPTION"), isStationExplicit(recommendedStationItem.getSubscriptionTierToDetails(), UpsellType.PRIME), isStationExplicit(recommendedStationItem.getSubscriptionTierToDetails(), UpsellType.FREE)));
    }

    public static Station toPrimeStation(final StationItem stationItem) {
        return new Station(CatalogStatusTiers.INSTANCE.from(stationItem.getSupportedTiers()), stationItem.getTitle(), stationItem.getImageUrl(), stationItem.getKey(), stationItem.getExplicitMap(), stationItem.getSFASeed());
    }

    public static PrimeTrack convertToPrimeTrack(final TrackMetadata metadata) {
        MusicTrack trackIncludingPrimePlaylistTrack;
        ContentOwnershipStatus contentOwnershipStatus;
        Set<ContentAccessType> supportedTiers = metadata.getSupportedTiers();
        if ((supportedTiers == null || supportedTiers.isEmpty()) && (trackIncludingPrimePlaylistTrack = AmazonApplication.getLibraryItemFactory().getTrackIncludingPrimePlaylistTrack(metadata.getUri())) != null) {
            supportedTiers = trackIncludingPrimePlaylistTrack.getSupportedTiers();
        }
        if (supportedTiers == null) {
            new HashSet();
        }
        if (BooleanUtils.isTrue(metadata.getIsOwned())) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = BooleanUtils.isTrue(metadata.getIsInLibrary()) ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        PrimeTrack primeTrack = new PrimeTrack(metadata.getTitle(), metadata.getAsin(), metadata.getImageUrl(), metadata.getArtistTitle(), metadata.getArtistAsin(), metadata.getAlbumTitle(), metadata.getAlbumAsin(), metadata.getAlbumArtistTitle(), metadata.getAssetType() != null && PrimePlaylistTracksTable.AssetType.VIDEO.toString().equals(metadata.getAssetType()), contentOwnershipStatus, toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(metadata.getSupportedTiers()), metadata.getOnDemandTiers()));
        primeTrack.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primeTrack.setTags(metadata.getTags() != null ? metadata.getTags() : Collections.emptyList());
        Boolean isExplicit = metadata.getIsExplicit();
        String assetType = metadata.getAssetType();
        if (assetType != null && PrimePlaylistTracksTable.AssetType.containsType(assetType)) {
            if (!PrimePlaylistTracksTable.AssetType.VIDEO.toString().equals(assetType) && (isExplicit == null || !isExplicit.booleanValue())) {
                z = false;
            }
            primeTrack.setIsExplicit(z);
        } else {
            primeTrack.setIsExplicit(isExplicit != null && isExplicit.booleanValue());
        }
        primeTrack.setAssetQualities(metadata.getAssetQuality());
        primeTrack.setDuration(Integer.valueOf(metadata.getDuration()));
        primeTrack.setContentEncoding(ContentEncodingDecoder.loadFrom(metadata.getContentEncodings()));
        primeTrack.setPlaymodeEligibility(metadata.getPlaymodeEligibility());
        primeTrack.setEligibilities(metadata.getEligibilities());
        primeTrack.setParentEligibilities(metadata.getParentEligibilities());
        if (metadata.getDownloadState() != null) {
            primeTrack.setDownloadState(metadata.getDownloadState().intValue());
        }
        return primeTrack;
    }

    public static PrimeTrack convertToPrimeTrack(final TrackMetadata metadata, final List<String> encodings) {
        PrimeTrack primeTrackConvertToPrimeTrack = convertToPrimeTrack(metadata);
        primeTrackConvertToPrimeTrack.setContentEncoding(ContentEncodingDecoder.loadFrom(encodings));
        return primeTrackConvertToPrimeTrack;
    }

    public static PrimeTrack convertToPrimeTrackByMusicTrack(final MusicTrack track) {
        ContentOwnershipStatus contentOwnershipStatus;
        if (BooleanUtils.isTrue(Boolean.valueOf(track.getOwnershipStatus().isOwned()))) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = BooleanUtils.isTrue(Boolean.valueOf(track.isInLibrary())) ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        PrimeTrack primeTrack = new PrimeTrack(track.getTitle(), track.getAsin(), track.getAlbumArtUrl(), track.getArtistName(), track.getArtistAsin(), track.getAlbumName(), track.getAlbumAsin(), track.getAlbumArtistName(), track.getAssetType() != null && PrimePlaylistTracksTable.AssetType.VIDEO.toString().equals(track.getAssetType().toString()), contentOwnershipStatus, toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(track.getSupportedTiers()), track.getOnDemandTiers()));
        primeTrack.setEntityItemIdentifier(track.getEntityItemIdentifier());
        Boolean boolValueOf = Boolean.valueOf(track.isExplicit());
        String strValueOf = String.valueOf(track.getAssetType());
        if (PrimePlaylistTracksTable.AssetType.containsType(strValueOf)) {
            if (!PrimePlaylistTracksTable.AssetType.VIDEO.toString().equals(strValueOf) && (boolValueOf == null || !boolValueOf.booleanValue())) {
                z = false;
            }
            primeTrack.setIsExplicit(z);
        } else {
            primeTrack.setIsExplicit(boolValueOf != null && boolValueOf.booleanValue());
        }
        primeTrack.setAssetQualities(track.getAssetQualities());
        primeTrack.setDuration(Integer.valueOf((int) track.getDuration()));
        primeTrack.setContentEncoding(track.getContentEncodings());
        primeTrack.setPlaymodeEligibility(track.getPlaymodeEligibility());
        primeTrack.setEligibilities(track.getEligibilities());
        return primeTrack;
    }

    public static PrimeAlbum convertToPrimeAlbum(final AlbumMetadata metadata) {
        ContentOwnershipStatus contentOwnershipStatus;
        if (BooleanUtils.isTrue(metadata.getIsOwned())) {
            contentOwnershipStatus = ContentOwnershipStatus.OWNED;
        } else {
            contentOwnershipStatus = BooleanUtils.isTrue(metadata.getIsInLibrary()) ? ContentOwnershipStatus.ADDED : ContentOwnershipStatus.NOT_IN_LIBRARY;
        }
        ContentOwnershipStatus contentOwnershipStatus2 = contentOwnershipStatus;
        PrimeAlbum primeAlbum = new PrimeAlbum(metadata.getTitle(), metadata.getAsin(), metadata.getImageUrl(), metadata.getArtistTitle(), metadata.getArtistAsin(), metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, contentOwnershipStatus2, toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(metadata.getSupportedTiers()), metadata.getOnDemandTiers()));
        primeAlbum.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primeAlbum.setEligibilities(metadata.getEligibilities());
        return primeAlbum;
    }

    public static PrimePlaylist convertToPrimePlaylist(final PlaylistMetadata metadata) {
        PrimePlaylist primePlaylist = new PrimePlaylist(metadata.getTitle(), metadata.getAsin(), metadata.getImageUrl(), metadata.getDescription(), metadata.getRating().floatValue(), metadata.getNumReviews().intValue(), metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, metadata.getAuthors(), getAssetTypes(metadata.getContentTypes()), toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(metadata.getSupportedTiers()), metadata.getOnDemandTiers()));
        primePlaylist.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primePlaylist.setOwnership(metadata.getIsAllOwned());
        primePlaylist.setEligibilities(metadata.getEligibilities());
        return primePlaylist;
    }

    public static PrimePlaylist convertToMusicVideoPlaylist(final PlaylistMetadata metadata) {
        String title = metadata.getTitle();
        String description = metadata.getDescription();
        Float rating = metadata.getRating();
        Integer numReviews = metadata.getNumReviews();
        String str = title == null ? "" : title;
        String str2 = description == null ? "" : description;
        if (rating == null) {
            rating = Float.valueOf(0.0f);
        }
        if (numReviews == null) {
            numReviews = 0;
        }
        PrimePlaylist primePlaylist = new PrimePlaylist(str, metadata.getAsin(), metadata.getImageUrl(), str2, rating.floatValue(), numReviews.intValue(), metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, metadata.getAuthors(), Arrays.asList(PrimePlaylistTracksTable.AssetType.VIDEO), toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(metadata.getSupportedTiers()), metadata.getOnDemandTiers()));
        primePlaylist.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primePlaylist.setOwnership(metadata.getIsAllOwned());
        primePlaylist.setEligibilities(metadata.getEligibilities());
        return primePlaylist;
    }

    public static PrimePlaylist convertToPrimePlaylist(final CommunityPlaylistMetadata metadata) {
        if (metadata == null) {
            return null;
        }
        PrimePlaylist primePlaylist = new PrimePlaylist(metadata.getTitle(), metadata.getImageUrl(), metadata.getDescription(), 0.0f, 0, metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, metadata.getPlaylistId(), metadata.getIsOwned(), metadata.getCurator(), PlaylistType.COMMUNITY_PLAYLIST, toStatusTiers(ContentAccessTypeKt.toSubscriptionTypes(metadata.getContentTiers()), metadata.getOnDemandTiers()));
        primePlaylist.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primePlaylist.setEligibilities(metadata.getEligibilities());
        return primePlaylist;
    }

    public static PrimePlaylist convertToPrimePlaylist(final UserPlaylistMetadata metadata) {
        if (metadata == null) {
            return null;
        }
        PrimePlaylist primePlaylist = new PrimePlaylist(metadata.getTitle(), metadata.getImageUrl(), metadata.getDescription(), 0.0f, 0, metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, metadata.getPlaylistId(), metadata.getIsOwned(), toStatusTiers(metadata.getContentTiers(), metadata.getOnDemandTiers()));
        primePlaylist.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primePlaylist.setEligibilities(metadata.getEligibilities());
        return primePlaylist;
    }

    public static PrimeArtist convertToPrimeArtist(final ArtistMetadata metadata) {
        PrimeArtist primeArtist = new PrimeArtist(metadata.getTitle(), metadata.getAsin(), metadata.getContributorAsin(), metadata.getTrackCount() != null ? metadata.getTrackCount().intValue() : 0, 0, metadata.getImageUrl(), metadata.getIsAllOwned() ? ContentOwnershipStatus.OWNED : ContentOwnershipStatus.NOT_IN_LIBRARY, ContentAccessTypeKt.toCatalogStatusTiers(metadata.getSupportedTiers()));
        primeArtist.setEntityItemIdentifier(metadata.getEntityItemIdentifier());
        primeArtist.setEligibilities(metadata.getEligibilities());
        return primeArtist;
    }

    private static CatalogStatusTiers toStatusTiers(final Set<SubscriptionType> contentTiers, final Set<SubscriptionType> onDemandTiers) {
        return new CatalogStatusTiers(contentTiers != null && contentTiers.contains(SubscriptionType.PRIME), contentTiers != null && contentTiers.contains(SubscriptionType.MUSIC_UNLIMITED), contentTiers != null && contentTiers.contains(SubscriptionType.FREE), onDemandTiers != null && onDemandTiers.contains(SubscriptionType.FREE), contentTiers != null && contentTiers.contains(SubscriptionType.SONIC_RUSH), onDemandTiers != null && onDemandTiers.contains(SubscriptionType.SONIC_RUSH));
    }

    public static Station convertToStation(final StationMetadata metadata) {
        Station station = new Station(CatalogStatusTiers.INSTANCE.from(metadata.getSupportedTiers()), metadata.getTitle(), metadata.getImageUrl(), metadata.getKey(), metadata.getExplicitMap(), null, null, metadata.getToken(), metadata.getQueueId());
        Boolean preferKeyOverToken = metadata.getPreferKeyOverToken();
        if (preferKeyOverToken != null) {
            station.setPreferKeyOverNextToken(preferKeyOverToken.booleanValue());
        }
        return station;
    }

    public static boolean isStationExplicit(final Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails, final String subscriptionType) {
        StationSubscriptionTierDetail stationSubscriptionTierDetail;
        return (subscriptionTierToDetails == null || (stationSubscriptionTierDetail = subscriptionTierToDetails.get(subscriptionType)) == null || !isExplicit(stationSubscriptionTierDetail.getParentalControls())) ? false : true;
    }

    private static boolean isExplicit(final ParentalControls parentalControls) {
        return parentalControls != null && Boolean.TRUE.equals(parentalControls.isHasExplicitLanguage());
    }

    public static Map<ContentAccessType, Boolean> buildExplicitMap(boolean isExplicit) {
        return buildExplicitMap(isExplicit, isExplicit, isExplicit);
    }

    public static Map<ContentAccessType, Boolean> buildExplicitMap(final boolean isHawfireExplicit, final boolean isPrimeExplicit, final boolean isNightwingExplicit) {
        HashMap map = new HashMap();
        if (isHawfireExplicit) {
            map.put(ContentAccessType.HAWKFIRE, Boolean.TRUE);
        }
        if (isPrimeExplicit) {
            map.put(ContentAccessType.PRIME, Boolean.TRUE);
        }
        if (isNightwingExplicit) {
            map.put(ContentAccessType.NIGHTWING, Boolean.TRUE);
        }
        return map;
    }
}
