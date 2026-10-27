package com.amazon.music.recents;

import UpsellInterface.v1_0.UpsellType;
import com.amazon.music.ContentAccessType;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.recents.model.ActivityType;
import com.amazon.music.recents.model.ContentQuality;
import com.amazon.music.recents.model.MusicItem;
import com.amazon.music.recents.model.RecentActivityRequest;
import com.amazon.music.recents.model.RecentTrackActivityRequest;
import com.amazon.music.recents.model.RecentUnfilteredTrackActivityRequest;
import com.amazon.music.recents.model.RecentlyPlayedAlbum;
import com.amazon.music.recents.model.RecentlyPlayedArtist;
import com.amazon.music.recents.model.RecentlyPlayedCatalogPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedSharedUserPlaylist;
import com.amazon.music.recents.model.RecentlyPlayedStation;
import com.amazon.music.recents.model.RecentlyPlayedTrack;
import com.amazon.music.recents.model.RecentlyPlayedTracksResponse;
import com.amazon.music.recents.model.RecentlyPlayedUserPlaylist;
import com.amazon.nimblymusicservice.GetRecentActivityRequest;
import com.amazon.nimblymusicservice.GetRecentActivityResponse;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequest;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponse;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequest;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse;
import com.amazon.nimblymusicservice.ParentalControls;
import com.amazon.nimblymusicservice.RecentActivityData;
import com.amazon.nimblymusicservice.RecentAlbumActivity;
import com.amazon.nimblymusicservice.RecentArtistActivity;
import com.amazon.nimblymusicservice.RecentCatalogPlaylistActivity;
import com.amazon.nimblymusicservice.RecentPlayedTrackActivity;
import com.amazon.nimblymusicservice.RecentStationActivity;
import com.amazon.nimblymusicservice.RecentTrackActivityWithToken;
import com.amazon.nimblymusicservice.RecentUserPlaylistActivity;
import com.amazon.nimblymusicservice.SubscriptionTierDetail;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class RecentsController {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) RecentsController.class);
    private static final List<String> entityTypes;
    private static final Set<String> stations;
    private final Configuration configuration;
    private final RecentActivityService service;

    static {
        HashSet hashSet = new HashSet();
        stations = hashSet;
        ArrayList arrayList = new ArrayList();
        entityTypes = arrayList;
        hashSet.add("PRIME_STATION");
        hashSet.add("UNLIMITED_STATION");
        arrayList.add("ALBUM");
        arrayList.add("ARTIST");
        arrayList.add("USER_PLAYLIST");
        arrayList.add("PRIME_PLAYLIST");
        arrayList.add("SHARED_PLAYLIST");
        arrayList.add("UNLIMITED_PLAYLIST");
        arrayList.add("PRIME_STATION");
        arrayList.add("UNLIMITED_STATION");
        arrayList.add("GOLDEN_PLAYLIST");
        arrayList.add("AMF_STATION");
        arrayList.add("AMF_STATION_SEED");
        arrayList.add("PRIME_STATION_SEED");
        arrayList.add("UNLIMITED_STATION_SEED");
    }

    public RecentsController(Configuration configuration) {
        this.configuration = configuration;
        this.service = new HttpRecentActivityService(configuration, new ServiceEnvironment_Prod());
    }

    public List<MusicItem> getRecentActivity(RecentActivityRequest recentActivityRequest) throws RecentsException {
        try {
            return parseResponse(this.service.getRecentActivity(translate(recentActivityRequest)));
        } catch (VolleyError e) {
            throw new RecentsException(e);
        }
    }

    public RecentlyPlayedTracksResponse getRecentTrackActivity(RecentTrackActivityRequest recentTrackActivityRequest) throws RecentsException {
        try {
            return parseResponse(this.service.getRecentTrackActivity(translate(recentTrackActivityRequest)));
        } catch (VolleyError e) {
            throw new RecentsException(e);
        }
    }

    public GetRecentUnfilteredTrackResponse getRecentUnfilteredTrackActivity(RecentUnfilteredTrackActivityRequest recentUnfilteredTrackActivityRequest) throws RecentsException {
        try {
            return this.service.getRecentUnfilteredTrackActivity(translate(recentUnfilteredTrackActivityRequest));
        } catch (VolleyError e) {
            throw new RecentsException(e);
        }
    }

    private GetRecentUnfilteredTrackRequest translate(RecentUnfilteredTrackActivityRequest recentUnfilteredTrackActivityRequest) {
        GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest = new GetRecentUnfilteredTrackRequest();
        getRecentUnfilteredTrackRequest.setDeviceId(recentUnfilteredTrackActivityRequest.getDeviceId());
        getRecentUnfilteredTrackRequest.setDeviceType(recentUnfilteredTrackActivityRequest.getDeviceType());
        getRecentUnfilteredTrackRequest.setMusicTerritory(recentUnfilteredTrackActivityRequest.getMusicTerritory());
        Date startTime = recentUnfilteredTrackActivityRequest.getStartTime();
        Date endTime = recentUnfilteredTrackActivityRequest.getEndTime();
        Boolean filterMoreThanThirtySeconds = recentUnfilteredTrackActivityRequest.getFilterMoreThanThirtySeconds();
        Boolean flatten = recentUnfilteredTrackActivityRequest.getFlatten();
        Integer maxResults = recentUnfilteredTrackActivityRequest.getMaxResults();
        String pageToken = recentUnfilteredTrackActivityRequest.getPageToken();
        if (startTime != null) {
            getRecentUnfilteredTrackRequest.setStartTime(startTime);
        }
        if (endTime != null) {
            getRecentUnfilteredTrackRequest.setEndTime(endTime);
        }
        if (filterMoreThanThirtySeconds != null) {
            getRecentUnfilteredTrackRequest.setFilterMoreThanThirtySeconds(filterMoreThanThirtySeconds.booleanValue());
        }
        if (flatten != null) {
            getRecentUnfilteredTrackRequest.setFlatten(flatten.booleanValue());
        }
        if (maxResults != null) {
            getRecentUnfilteredTrackRequest.setMaxResults(maxResults.intValue());
        }
        if (pageToken != null) {
            getRecentUnfilteredTrackRequest.setPageToken(pageToken);
        }
        return getRecentUnfilteredTrackRequest;
    }

    private GetRecentTrackActivityRequest translate(RecentTrackActivityRequest recentTrackActivityRequest) {
        GetRecentTrackActivityRequest getRecentTrackActivityRequest = new GetRecentTrackActivityRequest();
        getRecentTrackActivityRequest.setDeviceId(recentTrackActivityRequest.getDeviceId());
        getRecentTrackActivityRequest.setDeviceType(recentTrackActivityRequest.getDeviceType());
        getRecentTrackActivityRequest.setPageToken(recentTrackActivityRequest.getPageToken());
        getRecentTrackActivityRequest.setMusicTerritory(recentTrackActivityRequest.getMusicTerritory());
        List<ContentQuality> contentQualities = recentTrackActivityRequest.getContentQualities();
        List<ActivityType> activityTypes = recentTrackActivityRequest.getActivityTypes();
        if (activityTypes == null || activityTypes.isEmpty()) {
            getRecentTrackActivityRequest.setActivityTypeFilters(Arrays.asList(ActivityType.PLAYED.value));
        } else {
            getRecentTrackActivityRequest.setActivityTypeFilters(getActivityTypeFilters(activityTypes));
        }
        if (contentQualities != null && !contentQualities.isEmpty()) {
            getRecentTrackActivityRequest.setFeatures(ContentQuality.toListString(contentQualities));
        } else {
            getRecentTrackActivityRequest.setFeatures(Collections.singletonList(UpsellType.FREE));
        }
        if (recentTrackActivityRequest.getMusicRequestIdentityContextToken() != null) {
            getRecentTrackActivityRequest.setMusicRequestIdentityContextToken(recentTrackActivityRequest.getMusicRequestIdentityContextToken());
        }
        recentTrackActivityRequest.getAllowedParentalControls();
        return getRecentTrackActivityRequest;
    }

    private GetRecentActivityRequest translate(RecentActivityRequest recentActivityRequest) {
        GetRecentActivityRequest getRecentActivityRequest = new GetRecentActivityRequest();
        getRecentActivityRequest.setDeviceId(recentActivityRequest.getDeviceId());
        getRecentActivityRequest.setMaxResultSize(50);
        getRecentActivityRequest.setDeviceType(recentActivityRequest.getDeviceType());
        getRecentActivityRequest.setMusicTerritory(recentActivityRequest.getMusicTerritory());
        List<ContentQuality> contentQualities = recentActivityRequest.getContentQualities();
        List<ActivityType> activityTypes = recentActivityRequest.getActivityTypes();
        if (activityTypes == null || activityTypes.isEmpty()) {
            getRecentActivityRequest.setActivityTypeFilters(Arrays.asList(ActivityType.PLAYED.value));
        } else {
            getRecentActivityRequest.setActivityTypeFilters(getActivityTypeFilters(activityTypes));
        }
        getRecentActivityRequest.setEntityTypeFilters(entityTypes);
        if (contentQualities != null && !contentQualities.isEmpty()) {
            getRecentActivityRequest.setFeatures(ContentQuality.toListString(contentQualities));
        } else {
            getRecentActivityRequest.setFeatures(Collections.singletonList(UpsellType.FREE));
        }
        if (recentActivityRequest.getMusicRequestIdentityContextToken() != null) {
            getRecentActivityRequest.setMusicRequestIdentityContextToken(recentActivityRequest.getMusicRequestIdentityContextToken());
        }
        recentActivityRequest.getAllowedParentalControls();
        return getRecentActivityRequest;
    }

    private RecentlyPlayedTracksResponse parseResponse(GetRecentTrackActivityResponse getRecentTrackActivityResponse) {
        RecentTrackActivityWithToken recentTrackActivityWithToken;
        Map<String, RecentTrackActivityWithToken> recentActivityMap = getRecentTrackActivityResponse.getRecentActivityMap();
        if (recentActivityMap == null || recentActivityMap.size() == 0 || (recentTrackActivityWithToken = recentActivityMap.get("PLAYED")) == null) {
            return null;
        }
        return new RecentlyPlayedTracksResponse.Builder().recentTrackList(translate(recentTrackActivityWithToken.getRecentTrackList())).pageToken(recentTrackActivityWithToken.getNextToken()).build();
    }

    private List<MusicItem> parseResponse(GetRecentActivityResponse getRecentActivityResponse) {
        Map<String, List<RecentActivityData>> recentActivityMap = getRecentActivityResponse.getRecentActivityMap();
        if (recentActivityMap == null || recentActivityMap.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        List<RecentActivityData> list = recentActivityMap.get("PLAYED");
        if (list != null) {
            return convertRecentlyPlayed(list);
        }
        return Collections.EMPTY_LIST;
    }

    private List<RecentlyPlayedTrack> translate(List<RecentPlayedTrackActivity> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<RecentPlayedTrackActivity> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(convertTrack(it.next()));
            }
            return arrayList;
        }
        return Collections.EMPTY_LIST;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private List<MusicItem> convertRecentlyPlayed(List<RecentActivityData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (RecentActivityData recentActivityData : list) {
            String entityType = recentActivityData.getEntityType();
            entityType.hashCode();
            byte b = -1;
            switch (entityType.hashCode()) {
                case -2072461156:
                    if (entityType.equals("PRIME_STATION_SEED")) {
                        b = 0;
                    }
                    break;
                case -1851423856:
                    if (entityType.equals("UNLIMITED_PLAYLIST")) {
                        b = 1;
                    }
                    break;
                case -1384655800:
                    if (entityType.equals("GOLDEN_PLAYLIST")) {
                        b = 2;
                    }
                    break;
                case -1252666426:
                    if (entityType.equals("USER_PLAYLIST")) {
                        b = 3;
                    }
                    break;
                case -1179153132:
                    if (entityType.equals("STATION")) {
                        b = 4;
                    }
                    break;
                case -493466730:
                    if (entityType.equals("UNLIMITED_STATION")) {
                        b = 5;
                    }
                    break;
                case 62359119:
                    if (entityType.equals("ALBUM")) {
                        b = 6;
                    }
                    break;
                case 365417793:
                    if (entityType.equals("AMF_STATION_SEED")) {
                        b = 7;
                    }
                    break;
                case 421918575:
                    if (entityType.equals("AMF_STATION")) {
                        b = 8;
                    }
                    break;
                case 736108340:
                    if (entityType.equals("PRIME_STATION")) {
                        b = 9;
                    }
                    break;
                case 1274610042:
                    if (entityType.equals("UNLIMITED_STATION_SEED")) {
                        b = 10;
                    }
                    break;
                case 1474798700:
                    if (entityType.equals("SHARED_PLAYLIST")) {
                        b = MqttWireMessage.MESSAGE_TYPE_UNSUBACK;
                    }
                    break;
                case 1905664946:
                    if (entityType.equals("PRIME_PLAYLIST")) {
                        b = MqttWireMessage.MESSAGE_TYPE_PINGREQ;
                    }
                    break;
                case 1939198791:
                    if (entityType.equals("ARTIST")) {
                        b = MqttWireMessage.MESSAGE_TYPE_PINGRESP;
                    }
                    break;
            }
            switch (b) {
                case 0:
                case 4:
                case 5:
                case 7:
                case 8:
                case 9:
                case 10:
                    arrayList.add(convertStation((RecentStationActivity) recentActivityData));
                    break;
                case 1:
                case 2:
                case 12:
                    arrayList.add(convertCatalogPlaylist((RecentCatalogPlaylistActivity) recentActivityData));
                    break;
                case 3:
                    arrayList.add(convertUserPlaylist((RecentUserPlaylistActivity) recentActivityData));
                    break;
                case 6:
                    arrayList.add(convertAlbum((RecentAlbumActivity) recentActivityData));
                    break;
                case 11:
                    arrayList.add(convertSharedPlaylist((RecentUserPlaylistActivity) recentActivityData));
                    break;
                case 13:
                    arrayList.add(convertArtist((RecentArtistActivity) recentActivityData));
                    break;
                default:
                    LOG.error("unsupported entity type {}", recentActivityData.getEntityType());
                    break;
            }
        }
        return arrayList;
    }

    private RecentlyPlayedTrack convertTrack(RecentPlayedTrackActivity recentPlayedTrackActivity) {
        return new RecentlyPlayedTrack.Builder().albumAsin(recentPlayedTrackActivity.getAlbumAsin()).albumName(recentPlayedTrackActivity.getAlbumName()).artistAsin(recentPlayedTrackActivity.getArtistAsin()).artistName(recentPlayedTrackActivity.getArtistName()).deviceId(recentPlayedTrackActivity.getDeviceId()).deviceType(recentPlayedTrackActivity.getDeviceType()).displayName(recentPlayedTrackActivity.getDisplayName()).durationSeconds(Integer.valueOf(recentPlayedTrackActivity.getDurationSeconds() != null ? Integer.parseInt(recentPlayedTrackActivity.getDurationSeconds()) : 0)).hasLyrics(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getHasLyrics()))).image(recentPlayedTrackActivity.getImageFull()).isExplicit(Boolean.valueOf(recentPlayedTrackActivity.getParentalControls() == null ? true : Boolean.parseBoolean(recentPlayedTrackActivity.getParentalControls().getHasExplicitLanguage()))).isInLibrary(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsInLibrary()))).isInstantImport(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsInstantImport()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsMusicSubscription()))).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsPrime()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsSonicRushOnDemandPlayable()))).isPurchased(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsPurchased()))).isUploaded(Boolean.valueOf(Boolean.parseBoolean(recentPlayedTrackActivity.getIsUploaded()))).marketPlaceId(recentPlayedTrackActivity.getMarketplaceId()).objectId(recentPlayedTrackActivity.getObjectId()).timestamp(Long.valueOf(recentPlayedTrackActivity.getTimestamp().getTime())).trackAsin(recentPlayedTrackActivity.getAsin()).uid(recentPlayedTrackActivity.getUniqueIdentifier()).contentEncodings(getContentEncodings(recentPlayedTrackActivity)).build();
    }

    private MusicItem convertAlbum(RecentAlbumActivity recentAlbumActivity) {
        return ((RecentlyPlayedAlbum.Builder) new RecentlyPlayedAlbum.Builder().entityId(recentAlbumActivity.getEntityId())).title(recentAlbumActivity.getDisplayName()).timestamp(Long.valueOf(recentAlbumActivity.getTimestamp().getTime())).catalogArtistId(recentAlbumActivity.getArtistAsin()).artistName(recentAlbumActivity.getArtistName()).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentAlbumActivity.getIsSonicRushOnDemandPlayable()))).image(recentAlbumActivity.getImageFull()).trackAsin("ASIN".equals(recentAlbumActivity.getTrackIdType()) ? recentAlbumActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentAlbumActivity.getTrackIdType()) ? recentAlbumActivity.getTrackId() : null).contentEncodings(getContentEncodings(recentAlbumActivity)).build();
    }

    private MusicItem convertStation(RecentStationActivity recentStationActivity) {
        return ((RecentlyPlayedStation.Builder) new RecentlyPlayedStation.Builder().entityId(recentStationActivity.getEntityId())).entityIdType(recentStationActivity.getEntityIdType()).title(recentStationActivity.getDisplayName()).timestamp(Long.valueOf(recentStationActivity.getTimestamp().getTime())).image(recentStationActivity.getImageFull()).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentStationActivity.getIsSonicRushOnDemandPlayable()))).trackAsin("ASIN".equals(recentStationActivity.getTrackIdType()) ? recentStationActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentStationActivity.getTrackIdType()) ? recentStationActivity.getTrackId() : null).isExplicit(isStationExplicit(recentStationActivity)).contentEncodings(getContentEncodings(recentStationActivity)).build();
    }

    private MusicItem convertCatalogPlaylist(RecentCatalogPlaylistActivity recentCatalogPlaylistActivity) {
        return ((RecentlyPlayedCatalogPlaylist.Builder) new RecentlyPlayedCatalogPlaylist.Builder().entityId(recentCatalogPlaylistActivity.getEntityId())).title(recentCatalogPlaylistActivity.getDisplayName()).timestamp(Long.valueOf(recentCatalogPlaylistActivity.getTimestamp().getTime())).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsSonicRushOnDemandPlayable()))).isSonicRushGolden(Boolean.valueOf(Boolean.parseBoolean(recentCatalogPlaylistActivity.getIsSonicRushGolden()))).image(recentCatalogPlaylistActivity.getImageFull()).trackAsin("ASIN".equals(recentCatalogPlaylistActivity.getTrackIdType()) ? recentCatalogPlaylistActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentCatalogPlaylistActivity.getTrackIdType()) ? recentCatalogPlaylistActivity.getTrackId() : null).durationSeconds(Integer.valueOf(Integer.parseInt(recentCatalogPlaylistActivity.getDurationSeconds()))).genre(recentCatalogPlaylistActivity.getGenre()).parentPlaylist(recentCatalogPlaylistActivity.getParentPlaylist()).description(recentCatalogPlaylistActivity.getDescription()).contentEncodings(getContentEncodings(recentCatalogPlaylistActivity)).build();
    }

    private MusicItem convertUserPlaylist(RecentUserPlaylistActivity recentUserPlaylistActivity) {
        return ((RecentlyPlayedUserPlaylist.Builder) new RecentlyPlayedUserPlaylist.Builder().entityId(recentUserPlaylistActivity.getEntityId())).title(recentUserPlaylistActivity.getDisplayName()).timestamp(Long.valueOf(recentUserPlaylistActivity.getTimestamp().getTime())).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsSonicRushOnDemandPlayable()))).image(recentUserPlaylistActivity.getImageFull()).trackAsin("ASIN".equals(recentUserPlaylistActivity.getTrackIdType()) ? recentUserPlaylistActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentUserPlaylistActivity.getTrackIdType()) ? recentUserPlaylistActivity.getTrackId() : null).durationSeconds(Integer.valueOf(Integer.parseInt(recentUserPlaylistActivity.getDurationSeconds()))).trackCount(Integer.valueOf(Integer.parseInt(recentUserPlaylistActivity.getTrackCount()))).contentEncodings(getContentEncodings(recentUserPlaylistActivity)).build();
    }

    private MusicItem convertArtist(RecentArtistActivity recentArtistActivity) {
        return ((RecentlyPlayedArtist.Builder) new RecentlyPlayedArtist.Builder().entityId(recentArtistActivity.getEntityId())).title(recentArtistActivity.getDisplayName()).timestamp(Long.valueOf(recentArtistActivity.getTimestamp().getTime())).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentArtistActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentArtistActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentArtistActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentArtistActivity.getIsFreeOnDemandPlayable()))).image(recentArtistActivity.getImageFull()).trackAsin("ASIN".equals(recentArtistActivity.getTrackIdType()) ? recentArtistActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentArtistActivity.getTrackIdType()) ? recentArtistActivity.getTrackId() : null).trackTitle(recentArtistActivity.getTrackTitle()).contentEncodings(getContentEncodings(recentArtistActivity)).build();
    }

    private MusicItem convertSharedPlaylist(RecentUserPlaylistActivity recentUserPlaylistActivity) {
        return ((RecentlyPlayedSharedUserPlaylist.Builder) new RecentlyPlayedSharedUserPlaylist.Builder().entityId(recentUserPlaylistActivity.getEntityId())).title(recentUserPlaylistActivity.getDisplayName()).timestamp(Long.valueOf(recentUserPlaylistActivity.getTimestamp().getTime())).isPrime(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsPrime()))).isMusicSubscription(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsMusicSubscription()))).isFree(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsFree()))).isFreeOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsFreeOnDemandPlayable()))).isSonicRush(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsSonicRush()))).isSonicRushOnDemandPlayable(Boolean.valueOf(Boolean.parseBoolean(recentUserPlaylistActivity.getIsSonicRushOnDemandPlayable()))).image(recentUserPlaylistActivity.getImageFull()).trackAsin("ASIN".equals(recentUserPlaylistActivity.getTrackIdType()) ? recentUserPlaylistActivity.getTrackId() : null).trackLuid("OBJECT_ID".equals(recentUserPlaylistActivity.getTrackIdType()) ? recentUserPlaylistActivity.getTrackId() : null).durationSeconds(Integer.valueOf(Integer.parseInt(recentUserPlaylistActivity.getDurationSeconds()))).trackCount(Integer.valueOf(Integer.parseInt(recentUserPlaylistActivity.getTrackCount()))).sharingUrl(recentUserPlaylistActivity.getSharingUrl()).contentEncodings(getContentEncodings(recentUserPlaylistActivity)).build();
    }

    private Boolean isStationExplicit(RecentStationActivity recentStationActivity) {
        SubscriptionTierDetail subscriptionTierDetail;
        if (!stations.contains(recentStationActivity.getEntityType())) {
            return Boolean.FALSE;
        }
        Map<String, SubscriptionTierDetail> subscriptionTierToDetails = recentStationActivity.getSubscriptionTierToDetails();
        if (subscriptionTierToDetails == null || subscriptionTierToDetails.isEmpty()) {
            return Boolean.FALSE;
        }
        SubscriptionTierDetail subscriptionTierDetail2 = (ContentAccessType.PRIME.equals(this.configuration.getContentAccessType()) || (subscriptionTierDetail = subscriptionTierToDetails.get("MUSIC_SUBSCRIPTION")) == null) ? subscriptionTierToDetails.get(UpsellType.PRIME) : subscriptionTierDetail;
        if (subscriptionTierDetail2 == null) {
            return Boolean.FALSE;
        }
        ParentalControls parentalControls = subscriptionTierDetail2.getParentalControls();
        if (parentalControls == null) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(Boolean.parseBoolean(parentalControls.getHasExplicitLanguage()));
    }

    private List<String> getActivityTypeFilters(List<ActivityType> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<ActivityType> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().value);
        }
        return arrayList;
    }

    private List<ContentEncoding> getContentEncodings(RecentActivityData recentActivityData) {
        ArrayList arrayList = new ArrayList();
        if ("true".equals(recentActivityData.getIsHdAvailable())) {
            arrayList.add(ContentEncoding.HD);
        }
        if ("true".equals(recentActivityData.getIsHiResAvailable())) {
            arrayList.add(ContentEncoding.UHD);
        }
        if ("true".equals(recentActivityData.getIsThreeDAvailable())) {
            arrayList.add(ContentEncoding.IMMERSIVE);
        }
        if ("true".equals(recentActivityData.getIsAtmosAvailable())) {
            arrayList.add(ContentEncoding.DOLBY_ATMOS);
        }
        if ("true".equals(recentActivityData.getIsRA360Available())) {
            arrayList.add(ContentEncoding.SONY_360);
        }
        return arrayList;
    }

    private List<ContentEncoding> getContentEncodings(RecentPlayedTrackActivity recentPlayedTrackActivity) {
        ArrayList arrayList = new ArrayList();
        if ("true".equals(recentPlayedTrackActivity.getIsHdAvailable())) {
            arrayList.add(ContentEncoding.HD);
        }
        if ("true".equals(recentPlayedTrackActivity.getIsHiResAvailable())) {
            arrayList.add(ContentEncoding.UHD);
        }
        if ("true".equals(recentPlayedTrackActivity.getIsThreeDAvailable())) {
            arrayList.add(ContentEncoding.IMMERSIVE);
        }
        if ("true".equals(recentPlayedTrackActivity.getIsAtmosAvailable())) {
            arrayList.add(ContentEncoding.DOLBY_ATMOS);
        }
        if ("true".equals(recentPlayedTrackActivity.getIsRA360Available())) {
            arrayList.add(ContentEncoding.SONY_360);
        }
        return arrayList;
    }
}
