package com.amazon.mp3.recentlyplayed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.auto.storage.PresetEntityType;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.RecentActivityEventsUtil;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.PlaybackPersistenceManager;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.PlaybackServiceUtil;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.db.PrimePlaylistDatabaseManager;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.station.StationStorageUtil;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.music.ContentAccessType;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.find.data.database.SqliteLibrarySearchDao;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.station.StationItem;
import com.amazon.music.subscription.BrowseMode;
import com.amazon.music.views.library.metadata.RecentsMetadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class RecentlyPlayedManager {
    private static final String TAG = "RecentlyPlayedManager";
    private static SQLiteDatabase mCirrusDatabase;
    private static RecentlyPlayedManager sInstance;
    private final Context mContext;
    private RecentlyPlayedDatabase mDatabase;

    private RecentlyPlayedManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mDatabase = RecentlyPlayedDatabase.getInstance(context);
        mCirrusDatabase = CirrusDatabase.getWritableDatabase(context);
    }

    public static synchronized RecentlyPlayedManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new RecentlyPlayedManager(context);
        }
        return sInstance;
    }

    public static JSONObject getProviderInfo(JSONObject recentActivityEntry) throws JSONException {
        return recentActivityEntry.getJSONObject("provider");
    }

    public void clearAll() {
        this.mDatabase.clear();
        this.mDatabase.clearAlexaRecentlyPlayed();
        this.mDatabase.clearRecentTracks();
    }

    public void saveCurrentlyPlayingCollection(final Uri previousContentUri) throws IllegalArgumentException {
        String string;
        Uri uri;
        CatalogStatusTiers catalogStatusTiersFallbackValues;
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.getController(ControlSource.NONE);
        Uri currentUri = MediaItemHelper.getCurrentUri();
        if (currentUri != null) {
            boolean zIsShuffled = controller.isShuffled();
            JSONObject jSONObject = new JSONObject();
            if (MediaProvider.Casting.isCastingCollection(currentUri) && previousContentUri != null) {
                Log.debug(TAG, "Resetting the contentUri in case of casting to " + previousContentUri);
                uri = previousContentUri;
                string = null;
            } else {
                try {
                    if (!PlaybackPersistenceManager.getInstance().saveCollectionState(jSONObject)) {
                        if (MediaProvider.Station.isStationFromAnything(currentUri)) {
                            return;
                        }
                    }
                    string = jSONObject.toString();
                    uri = currentUri;
                } catch (JSONException e) {
                    Log.error(TAG, "Failed to convert the current playback state into json " + e);
                    return;
                }
            }
            MediaItem currentMediaItem = controller.getCurrentMediaItem();
            if (currentMediaItem != null) {
                MusicTrack musicTrackTranslate = translate(currentMediaItem);
                Log.debug(TAG, "Persisting item = " + uri + " in recents database");
                MediaCollectionInfo mediaCollectionInfo = controller.getMediaCollectionInfo();
                if (mediaCollectionInfo == null) {
                    mediaCollectionInfo = currentMediaItem.getSourceMediaCollectionInfo();
                }
                MediaCollectionInfo mediaCollectionInfo2 = mediaCollectionInfo;
                MediaAccessInfo mediaAccessInfo = mediaCollectionInfo2 != null ? mediaCollectionInfo2.getMediaAccessInfo() : null;
                if (mediaAccessInfo != null) {
                    catalogStatusTiersFallbackValues = CatalogStatusTiers.INSTANCE.from(mediaAccessInfo);
                } else {
                    catalogStatusTiersFallbackValues = CatalogStatusTiers.fallbackValues();
                }
                add(uri, zIsShuffled, string, musicTrackTranslate, catalogStatusTiersFallbackValues, mediaCollectionInfo2);
            }
        }
    }

    private MusicTrack translate(final MediaItem mediaItem) {
        CatalogStatusTiers catalogStatusTiersFallbackValues;
        MusicTrack musicTrack = new MusicTrack();
        musicTrack.setTitle(mediaItem.getName());
        musicTrack.setAlbumName(mediaItem.getAlbumName());
        musicTrack.setArtistName(mediaItem.getArtistName());
        MediaItemId.Type type = MediaItemId.Type.ASIN;
        String id = mediaItem.getMediaItemId(type) != null ? mediaItem.getMediaItemId(type).getId() : null;
        MediaItemId.Type type2 = MediaItemId.Type.LUID;
        String id2 = mediaItem.getMediaItemId(type2) != null ? mediaItem.getMediaItemId(type2).getId() : null;
        MediaItemId.Type type3 = MediaItemId.Type.OWNED;
        String id3 = mediaItem.getMediaItemId(type3) != null ? mediaItem.getMediaItemId(type3).getId() : null;
        musicTrack.setAsin(id);
        musicTrack.setLuid(id2);
        musicTrack.setIsExplicit(mediaItem.getMediaAccessInfo().isExplicit());
        MediaCollectionType mediaCollectionType = MediaCollectionType.ALBUM;
        MediaCollectionId.Type type4 = MediaCollectionId.Type.ASIN;
        String mediaCollectionId = getMediaCollectionId(mediaItem.getMediaCollectionInfo(mediaCollectionType, type4));
        String mediaCollectionId2 = getMediaCollectionId(mediaItem.getMediaCollectionInfo(MediaCollectionType.ARTIST, type4));
        musicTrack.setAlbumAsin(mediaCollectionId);
        musicTrack.setArtistAsin(mediaCollectionId2);
        if (!StringUtil.isNullOrWhiteSpaces(id) || !StringUtil.isNullOrWhiteSpaces(id3)) {
            musicTrack.setSource("cirrus");
        } else {
            musicTrack.setSource("cirrus-local");
        }
        MediaAccessInfo mediaAccessInfo = mediaItem.getMediaAccessInfo();
        if (mediaAccessInfo != null) {
            catalogStatusTiersFallbackValues = CatalogStatusTiers.INSTANCE.from(mediaAccessInfo);
        } else {
            catalogStatusTiersFallbackValues = CatalogStatusTiers.fallbackValues();
        }
        musicTrack.setCatalogStatusTiers(catalogStatusTiersFallbackValues);
        musicTrack.setContentEncodings(mediaItem.getContentEncodings());
        musicTrack.setAssetType(MediaItemExtensions.isVideo(mediaItem) ? PrimePlaylistTracksTable.AssetType.VIDEO : PrimePlaylistTracksTable.AssetType.AUDIO);
        return musicTrack;
    }

    private String getMediaCollectionId(final MediaCollectionInfo mediaCollectionInfo) {
        if (mediaCollectionInfo != null) {
            return mediaCollectionInfo.getId().getId();
        }
        return null;
    }

    public List<PresetIdentifier> queryRecentItems(Integer limit) {
        ArrayList arrayList = new ArrayList();
        this.mDatabase.prune(50);
        Cursor cursorQueryByLastPlayedTimeDesc = this.mDatabase.queryByLastPlayedTimeDesc();
        if (cursorQueryByLastPlayedTimeDesc != null) {
            while (cursorQueryByLastPlayedTimeDesc.moveToNext()) {
                try {
                    if (limit != null && arrayList.size() >= limit.intValue()) {
                        return arrayList;
                    }
                    Uri uri = Uri.parse(getString(cursorQueryByLastPlayedTimeDesc, "content_uri"));
                    String string = getString(cursorQueryByLastPlayedTimeDesc, "collection_id");
                    String string2 = getString(cursorQueryByLastPlayedTimeDesc, "collection_asin");
                    if (MediaProvider.Albums.isAlbum(uri)) {
                        arrayList.add(new PresetIdentifier(string2, PresetEntityType.Album));
                    } else if (MediaProvider.PrimePlaylists.isPrimePlaylist(uri)) {
                        arrayList.add(new PresetIdentifier(string2, PresetEntityType.Playlist));
                    } else if (MediaProvider.Station.isStation(uri)) {
                        arrayList.add(new PresetIdentifier(string, PresetEntityType.Station));
                    } else if (MediaProvider.UdoPlaylists.isUdoPlaylist(uri)) {
                        String luid = MediaProvider.UdoPlaylists.getLuid(this.mContext, string);
                        if (luid == null) {
                            Log.debug(TAG, "Fail to query luid for user playlist with primary id " + string);
                        } else {
                            arrayList.add(new PresetIdentifier(luid, PresetEntityType.UserPlaylist));
                        }
                    }
                } finally {
                    cursorQueryByLastPlayedTimeDesc.close();
                }
            }
        }
        return arrayList;
    }

    public List<RecentlyPlayedItem> queryRecentItems() {
        ArrayList arrayList = new ArrayList();
        this.mDatabase.prune(50);
        Cursor cursorQueryByLastPlayedTimeDesc = this.mDatabase.queryByLastPlayedTimeDesc();
        if (cursorQueryByLastPlayedTimeDesc != null) {
            while (cursorQueryByLastPlayedTimeDesc.moveToNext()) {
                try {
                    RecentlyPlayedItem recentItem = toRecentItem(cursorQueryByLastPlayedTimeDesc);
                    if (recentItem != null && recentItem.getTitle() != null && !NowPlayingUtil.isVideoContent(recentItem.getContentUri())) {
                        arrayList.add(recentItem);
                    } else if (recentItem == null) {
                        Log.warning(TAG, "RecentlyPlayedItem is null");
                    } else {
                        Log.warning(TAG, "RecentlyPlayedItem of type " + recentItem.getItemType() + " has a null title");
                    }
                } finally {
                    cursorQueryByLastPlayedTimeDesc.close();
                }
            }
        }
        boolean zRemoveInvalidItems = removeInvalidItems(arrayList);
        setPrimeStatus(arrayList);
        setContentUnavailableReasons(arrayList);
        setArtworkImageUrls(arrayList);
        if (zRemoveInvalidItems) {
            setItems(arrayList);
        }
        return arrayList;
    }

    public List<RecentTrack> getRecentTracks() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.mContext.getContentResolver().query(MediaProvider.SmartPlaylists.Tracks.getContentUri("cirrus", 3L), getProjection(), null, null, null);
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                try {
                    arrayList.add(toRecentTrack(cursorQuery));
                } finally {
                    DbUtil.closeCursor(cursorQuery);
                }
            }
        }
        setContentUnavailableReasons(arrayList);
        return arrayList;
    }

    private String[] getProjection() {
        String[] strArr = {"title", "artist", "ownership_status", "prime_status", "album_art_id", "download_state", "is_explicit", "content_encoding", "artist_asin", "album", "local_uri", "is_remote", "last_played_time", "recent_album_asin", "recent_track_luid", "recent_track_asin", "album_art_uri", "recent_track_source", "_uid", "catalog_status_tiers"};
        if (!AmazonApplication.getCapabilities().isLyricsSupported()) {
            return strArr;
        }
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, strArr);
        arrayList.add("lyrics_state");
        return (String[]) arrayList.toArray(strArr);
    }

    public List<RecentlyPlayedItem> queryRecentStations() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQueryStationsItemsByLastPlayedTime = this.mDatabase.queryStationsItemsByLastPlayedTime();
        if (cursorQueryStationsItemsByLastPlayedTime != null) {
            while (cursorQueryStationsItemsByLastPlayedTime.moveToNext()) {
                try {
                    RecentlyPlayedItem recentItem = toRecentItem(cursorQueryStationsItemsByLastPlayedTime);
                    if (recentItem != null && recentItem.getTitle() != null) {
                        arrayList.add(recentItem);
                    } else if (recentItem == null) {
                        Log.warning(TAG, "RecentlyPlayedItem is null");
                    } else {
                        Log.warning(TAG, "RecentlyPlayedItem of type " + recentItem.getItemType() + " has a null title");
                    }
                } finally {
                    cursorQueryStationsItemsByLastPlayedTime.close();
                }
            }
        }
        return arrayList;
    }

    public void add(RecentlyPlayedItem item) {
        ContentValues contentValues = toContentValues(item);
        RecentlyPlayedItem.Type itemType = item.getItemType();
        this.mDatabase.insertRecentlyPlayed(contentValues, itemType == RecentlyPlayedItem.Type.ALL_SONGS || itemType == RecentlyPlayedItem.Type.ALEXA_RECENTS || itemType == RecentlyPlayedItem.Type.CASTING_RECENTS, itemType != RecentlyPlayedItem.Type.PLAYLIST, getCollectionTypeToBeDeDuped(item));
    }

    public void pruneRecentTracks(final int maxNumber) {
        this.mDatabase.pruneRecentTracks(maxNumber);
    }

    private String getCollectionTypeToBeDeDuped(final RecentlyPlayedItem recentlyPlayedItem) {
        RecentlyPlayedItem.Type type = RecentlyPlayedItem.Type.PRIME_ARTIST;
        if (type.equals(recentlyPlayedItem.getItemType())) {
            return RecentlyPlayedItem.Type.ARTIST.name();
        }
        if (RecentlyPlayedItem.Type.ARTIST.equals(recentlyPlayedItem.getItemType())) {
            return type.name();
        }
        return null;
    }

    private void add(Uri contentUri, boolean shuffleOn, String trackProviderStateJson, MusicTrack track, final CatalogStatusTiers catalogStatusTiers, final MediaCollectionInfo mediaCollectionInfo) {
        String luid = track.getLuid();
        String asin = track.getAsin();
        boolean zIsExplicit = track.isExplicit();
        add(toRecentTrack(track));
        if (MediaProvider.Albums.isAlbum(contentUri)) {
            addAlbum(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, track.getAlbumAsin(), zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.PrimeArtists.isPrimeArtist(contentUri)) {
            addPrimeArtist(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.Artists.isArtist(contentUri)) {
            addArtist(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, track.getArtistAsin(), zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.Genres.isGenre(contentUri)) {
            addGenre(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, null, zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.PrimePlaylists.isPrimePlaylist(contentUri) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(contentUri)) {
            addPrimePlaylist(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.Playlists.isPlaylist(contentUri)) {
            addPlaylist(contentUri, null, luid, asin, shuffleOn, trackProviderStateJson, zIsExplicit, catalogStatusTiers);
        } else if (MediaProvider.PrimeBrowseTracksCollection.isCollection(contentUri)) {
            addPrimeBrowseSongs(contentUri, track, trackProviderStateJson, catalogStatusTiers);
        } else if (MediaProvider.Tracks.isTrackCollection(contentUri, new String[0])) {
            addAllSongs(contentUri, null, track, shuffleOn, trackProviderStateJson, null, catalogStatusTiers);
        } else if (MediaProvider.Station.isStation(contentUri)) {
            addStation(contentUri, trackProviderStateJson, mediaCollectionInfo);
        } else if (MediaProvider.Tracks.isTrack(contentUri)) {
            addTrack(contentUri, null, track, trackProviderStateJson);
        } else if (MediaProvider.Alexa.isAlexaCollection(contentUri)) {
            addAlexaCollection(track, trackProviderStateJson, catalogStatusTiers);
        } else if (MediaProvider.AlexaRecentlyPlayed.isAlexaRecentlyPlayedCollection(contentUri)) {
            addAlexaCollection(null, trackProviderStateJson, catalogStatusTiers);
        } else if (!MediaProvider.Casting.isCastingCollection(contentUri) && !MediaProvider.CastingRecentlyPlayed.isCastingRecentlyPlayedCollection(contentUri)) {
            throw new IllegalArgumentException("Unable to determine item type from content uri: " + contentUri.toString());
        }
        RecentActivityEventsUtil.INSTANCE.sendEvent(contentUri);
    }

    public void add(final RecentTrack recentlyPlayedTrack) {
        this.mDatabase.insertRecentTrack(toRecentTrackContentValues(recentlyPlayedTrack));
    }

    public void bulkAdd(final List<RecentTrack> recentlyPlayedTracks) {
        ArrayList arrayList = new ArrayList();
        Iterator<RecentTrack> it = recentlyPlayedTracks.iterator();
        while (it.hasNext()) {
            arrayList.add(toRecentTrackContentValues(it.next()));
        }
        this.mDatabase.bulkInsertRecentTracks(arrayList);
    }

    public void bulkUpdateRecentTracksContentEncoding(final List<RecentTrack> recentTracks) {
        HashMap map = new HashMap();
        TrackDataUtil trackDataUtil = new TrackDataUtil(mCirrusDatabase);
        for (RecentTrack recentTrack : recentTracks) {
            map.put(recentTrack.getAsin(), recentTrack.getContentEncodings());
        }
        trackDataUtil.updateTrackContentEncodingByAsins(map);
    }

    private void addTrack(Uri contentUri, String localUri, MusicTrack track, String trackProviderStateJson) {
        add(contentUri, localUri, track.getTitle(), track.getLuid(), track.getAsin(), String.valueOf(track.getAlbumId()), false, trackProviderStateJson, true, track.getAlbumArtUrl(), track.getAlbumAsin(), track.isExplicit(), new CatalogStatusTiers(track.isPrime(), track.isAnyCatalog(), track.isFree(), track.isFreeOnDemand(), track.isSonicRush(), track.isSonicRushOnDemand()), track.getContentEncodings());
    }

    private void add(Uri contentUri, String localUri, String title, String trackLuid, String trackAsin, String collectionId, boolean shuffleOn, String trackProviderStateJson, String albumAsin, boolean isExplicit, CatalogStatusTiers catalogStatusTiers, List<ContentEncoding> contentEncodings) {
        add(contentUri, localUri, title, trackLuid, trackAsin, collectionId, shuffleOn, trackProviderStateJson, true, null, albumAsin, isExplicit, catalogStatusTiers, contentEncodings);
    }

    private void add(Uri contentUri, String localUri, String title, String trackLuid, String trackAsin, String collectionId, boolean shuffleOn, String trackProviderStateJson, boolean synced, String albumArtUrl, String collectionAsin, boolean isExplicit, CatalogStatusTiers catalogStatusTiers, List<ContentEncoding> contentEncodings) {
        RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem(contentUri);
        recentlyPlayedItem.setLocalUri(localUri);
        recentlyPlayedItem.setTitle(title);
        recentlyPlayedItem.setTrackLuid(trackLuid);
        recentlyPlayedItem.setTrackAsin(trackAsin);
        recentlyPlayedItem.setCollectionId(collectionId);
        recentlyPlayedItem.setTrackProviderState(trackProviderStateJson);
        recentlyPlayedItem.setLastPlayedTime(System.currentTimeMillis());
        recentlyPlayedItem.setArtworkImageUrl(albumArtUrl);
        recentlyPlayedItem.setShuffleOn(shuffleOn);
        recentlyPlayedItem.setSynced(synced);
        recentlyPlayedItem.setCollectionAsin(collectionAsin);
        recentlyPlayedItem.setIsExplicit(isExplicit);
        recentlyPlayedItem.setCatalogStatusTiers(catalogStatusTiers);
        recentlyPlayedItem.setContentEncoding(contentEncodings);
        add(recentlyPlayedItem);
    }

    private void addAllSongs(Uri contentUri, String localUri, MusicTrack track, boolean shuffleOn, String trackProviderStateJson, String albumAsin, final CatalogStatusTiers catalogStatusTiers) {
        add(contentUri, localUri, track.getTitle(), track.getLuid(), track.getAsin(), shuffleOn ? "all_songs_shuffle_collection_id" : String.valueOf(track.getAlbumId()), shuffleOn, trackProviderStateJson, true, track.getAlbumArtUrl(), albumAsin, track.isExplicit(), catalogStatusTiers, track.getContentEncodings());
    }

    private void addPlaylist(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        Uri uriRemoveTracksSegment = MediaProvider.removeTracksSegment(contentUri);
        String string = Long.toString(MediaProvider.Playlists.getPlaylistId(uriRemoveTracksSegment));
        Playlist playlist = AmazonApplication.getLibraryItemFactory().getPlaylist(uriRemoveTracksSegment);
        if (playlist != null) {
            add(uriRemoveTracksSegment, localUri, playlist.getName(), trackLuid, trackAsin, playlist.getId(), shuffleOn, trackProviderStateJson, string, isExplicit, catalogStatusTiers, null);
        }
    }

    private void addPrimePlaylist(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        String title;
        boolean z;
        PrimePlaylistDatabaseManager primePlaylistDatabaseManager = new PrimePlaylistDatabaseManager();
        String catalogPlaylistId = MediaProvider.Playlists.getCatalogPlaylistId(contentUri);
        CatalogPlaylist playlist = primePlaylistDatabaseManager.getPlaylist(catalogPlaylistId, PrimePlaylistsTable.translateSource(MediaProvider.getSource(contentUri)));
        if (playlist == null) {
            title = NowPlayingManager.getInstance().getCollectionName();
            z = false;
        } else {
            title = playlist.getTitle();
            z = true;
        }
        add(contentUri, localUri, title, trackLuid, trackAsin, catalogPlaylistId, shuffleOn, trackProviderStateJson, z, null, catalogPlaylistId, isExplicit, catalogStatusTiers, null);
    }

    private void addPrimeArtist(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        String string;
        String str;
        String str2;
        long artistId = MediaProvider.PrimeArtists.getArtistId(contentUri);
        if (Long.MIN_VALUE == artistId) {
            Log.error(TAG, "Failed to persist Prime artist with contentUri = " + contentUri);
            return;
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(contentUri, new String[]{"artist", "artist_asin"}, null, null, null);
        String string2 = "";
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist"));
                    string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist_asin"));
                } else {
                    string = "";
                }
                DbUtil.closeCursor(cursorQuery);
                str = string2;
                str2 = string;
            } catch (Throwable th) {
                DbUtil.closeCursor(cursorQuery);
                throw th;
            }
        } else {
            str = "";
            str2 = str;
        }
        add(contentUri, localUri, str, trackLuid, trackAsin, Long.toString(artistId), shuffleOn, trackProviderStateJson, true, null, str2, isExplicit, catalogStatusTiers, null);
    }

    private void addArtist(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, String albumAsin, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        Uri uriRemoveTracksSegment = MediaProvider.removeTracksSegment(contentUri);
        Artist artist = AmazonApplication.getLibraryItemFactory().getArtist(uriRemoveTracksSegment);
        if (artist == null) {
            Log.error(TAG, "Artist is null. ContentUri: " + uriRemoveTracksSegment);
            return;
        }
        add(uriRemoveTracksSegment, localUri, artist.getName(), trackLuid, trackAsin, artist.getId(), shuffleOn, trackProviderStateJson, albumAsin, isExplicit, catalogStatusTiers, null);
    }

    private void addGenre(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, String albumAsin, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        Uri uriRemoveTracksSegment = MediaProvider.removeTracksSegment(contentUri);
        Genre genre = AmazonApplication.getLibraryItemFactory().getGenre(uriRemoveTracksSegment);
        add(uriRemoveTracksSegment, localUri, genre.getName(), trackLuid, trackAsin, genre.getId(), shuffleOn, trackProviderStateJson, albumAsin, isExplicit, catalogStatusTiers, null);
    }

    private void addStation(Uri contentUri, String trackProviderJson, final MediaCollectionInfo mediaCollectionInfo) {
        MediaCollectionId id;
        StationItem stationItemQueryFromContentUri = StationStorageUtil.queryFromContentUri(this.mContext, contentUri);
        if (stationItemQueryFromContentUri == null) {
            return;
        }
        BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
        if (UserSubscriptionUtil.isNightwingOnly()) {
            browseMode = BrowseMode.Mode.NIGHTWING;
        }
        ContentAccessType contentAccessType = ContentAccessType.HAWKFIRE;
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[browseMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            contentAccessType = ContentAccessType.NIGHTWING;
        }
        String title = stationItemQueryFromContentUri.getTitle();
        if (TextUtils.isEmpty(title)) {
            title = mediaCollectionInfo.getName();
        }
        String str = title;
        String imageUrl = stationItemQueryFromContentUri.getImageUrl();
        if (TextUtils.isEmpty(imageUrl) && (id = mediaCollectionInfo.getId(MediaCollectionId.Type.CLOUD_QUEUE_IMAGE_URL)) != null) {
            imageUrl = id.getId();
        }
        add(contentUri, null, str, null, null, stationItemQueryFromContentUri.getKey(), false, trackProviderJson, true, imageUrl, stationItemQueryFromContentUri.getKey(), stationItemQueryFromContentUri.isExplicit(contentAccessType), CatalogStatusTiers.INSTANCE.from(stationItemQueryFromContentUri.getSupportedTiers()), null);
    }

    private void addAlexaCollection(final MusicTrack track, final String trackProviderStateJson, CatalogStatusTiers catalogStatusTiers) {
        boolean zIsExplicit;
        String albumArtUrl;
        if (track != null) {
            zIsExplicit = track.isExplicit();
            albumArtUrl = track.getAlbumArtUrl();
            this.mDatabase.insertAlexaRecentItem(toAlexaRecentItemContentValues(track));
        } else {
            zIsExplicit = false;
            albumArtUrl = null;
        }
        String str = albumArtUrl;
        add(MediaProvider.AlexaRecentlyPlayed.getContentUri(), null, this.mContext.getResources().getString(R.string.dmusic_recently_played_alexa), null, null, "alexa_recently_played_collection_id", false, trackProviderStateJson, true, str, null, zIsExplicit, catalogStatusTiers, null);
    }

    private void addAlbum(Uri contentUri, String localUri, String trackLuid, String trackAsin, boolean shuffleOn, String trackProviderStateJson, String albumAsin, boolean isExplicit, final CatalogStatusTiers catalogStatusTiers) {
        List<ContentEncoding> contentEncodings;
        boolean z;
        String str;
        Uri uriRemoveTracksSegment = MediaProvider.removeTracksSegment(contentUri);
        Album album = AmazonApplication.getLibraryItemFactory().getAlbum(uriRemoveTracksSegment);
        String strValueOf = String.valueOf(MediaProvider.Albums.getAlbumId(uriRemoveTracksSegment));
        if (album == null) {
            String collectionName = NowPlayingManager.getInstance().getCollectionName();
            z = false;
            contentEncodings = new ArrayList();
            str = collectionName;
        } else {
            String title = album.getTitle();
            contentEncodings = album.getContentEncodings();
            z = true;
            str = title;
        }
        add(uriRemoveTracksSegment, localUri, str, trackLuid, trackAsin, strValueOf, shuffleOn, trackProviderStateJson, z, null, albumAsin, isExplicit, catalogStatusTiers, contentEncodings);
    }

    private void addPrimeBrowseSongs(Uri contentUri, MusicTrack track, String trackProviderStateJson, final CatalogStatusTiers catalogStatusTiers) {
        Uri localUri = PlaybackServiceUtil.getLocalUri(this.mContext, track.getContentUri());
        add(MediaProvider.removeTracksSegment(contentUri), localUri == null ? null : localUri.toString(), track.getTitle(), track.getLuid(), track.getAsin(), String.valueOf(track.getAlbumId()), false, trackProviderStateJson, false, track.getAlbumArtUrl(), track.getAlbumAsin(), track.isExplicit(), catalogStatusTiers, track.getContentEncodings());
    }

    private void setPrimeStatus(final RecentlyPlayedItem item) {
        ContentCatalogStatus contentCatalogStatus;
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[item.getItemType().ordinal()]) {
            case 1:
                setPrimePlaylistContentCatalogStatus(item);
                item.setOwnershipStatus(ContentOwnershipStatus.ADDED);
                return;
            case 2:
                setPrimeArtistContentCatalogStatus(item);
                item.setOwnershipStatus(ContentOwnershipStatus.ADDED);
                return;
            case 3:
                if (AccountDetailUtil.get(this.mContext).isPrimeMusicSupported()) {
                    contentCatalogStatus = ContentCatalogStatus.PRIME;
                } else {
                    contentCatalogStatus = ContentCatalogStatus.HAWKFIRE;
                }
                item.setContentCatalogStatus(contentCatalogStatus);
                item.setOwnershipStatus(ContentOwnershipStatus.ADDED);
                return;
            case 4:
                if (item.getCatalogStatusTiers() != null) {
                    item.setContentCatalogStatus(ContentAccessUtil.setContentAccess(item.getCatalogStatusTiers().getIsMusicSubscription(), item.getCatalogStatusTiers().getIsPrime(), item.getCatalogStatusTiers().getIsSonicRush()));
                }
                item.setOwnershipStatus(ContentOwnershipStatus.ADDED);
                return;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                item.setContentCatalogStatus(ContentCatalogStatus.NON_CATALOG);
                item.setOwnershipStatus(ContentOwnershipStatus.OWNED);
                return;
            default:
                Cursor cursorQuery = this.mContext.getContentResolver().query(item.getContentUri(), new String[]{SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX, SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            int i = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_CATALOG_STATUS_MAX));
                            int i2 = cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow(SqliteLibrarySearchDao.CONTENT_OWNERSHIP_STATUS_MAX));
                            item.setContentCatalogStatus(ContentCatalogStatus.fromValue(i));
                            item.setOwnershipStatus(ContentOwnershipStatus.fromValue(i2));
                        } else {
                            item.setOwnershipStatus(ContentOwnershipStatus.NOT_IN_LIBRARY);
                        }
                        return;
                    } finally {
                        DbUtil.closeCursor(cursorQuery);
                    }
                }
                return;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.recentlyplayed.RecentlyPlayedManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode;

        static {
            int[] iArr = new int[RecentlyPlayedItem.Type.values().length];
            $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type = iArr;
            try {
                iArr[RecentlyPlayedItem.Type.PRIME_PLAYLIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_ARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PRIME_BROWSE_SONGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.STATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.PLAYLIST.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.SMART_PLAYLIST.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALL_SONGS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ARTIST.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALEXA_RECENTS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.CASTING_RECENTS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.ALBUM.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$recentlyplayed$RecentlyPlayedItem$Type[RecentlyPlayedItem.Type.GENRE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            int[] iArr2 = new int[BrowseMode.Mode.values().length];
            $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode = iArr2;
            try {
                iArr2[BrowseMode.Mode.PRIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.HAWKFIRE.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$music$subscription$BrowseMode$Mode[BrowseMode.Mode.NIGHTWING.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    private void setPrimeStatus(List<RecentlyPlayedItem> items) {
        Iterator<RecentlyPlayedItem> it = items.iterator();
        while (it.hasNext()) {
            setPrimeStatus(it.next());
        }
    }

    private void setPrimePlaylistContentCatalogStatus(final RecentlyPlayedItem item) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(item.getContentUri(), null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    item.setContentCatalogStatus(ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("catalog_status"))));
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
    }

    private void setPrimeArtistContentCatalogStatus(final RecentlyPlayedItem item) {
        Cursor cursorQuery = this.mContext.getContentResolver().query(item.getContentUri(), null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    item.setContentCatalogStatus(ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("prime_status"))));
                }
            } finally {
                DbUtil.closeCursor(cursorQuery);
            }
        }
    }

    private void setItems(List<RecentlyPlayedItem> items) {
        ArrayList arrayList = new ArrayList(items.size());
        Iterator<RecentlyPlayedItem> it = items.iterator();
        while (it.hasNext()) {
            arrayList.add(toContentValues(it.next()));
        }
        this.mDatabase.set(arrayList);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0142 A[PHI: r7
  0x0142: PHI (r7v6 boolean) = 
  (r7v4 boolean)
  (r7v4 boolean)
  (r7v4 boolean)
  (r7v4 boolean)
  (r7v4 boolean)
  (r7v3 boolean)
  (r7v3 boolean)
  (r7v3 boolean)
 binds: [B:37:0x0101, B:46:0x012b, B:48:0x0131, B:50:0x013b, B:43:0x0121, B:65:0x018a, B:78:0x01bd, B:80:0x01c3] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x018c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean removeInvalidItems(java.util.List<com.amazon.mp3.recentlyplayed.RecentlyPlayedItem> r18) {
        /*
            Method dump skipped, instruction units count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.recentlyplayed.RecentlyPlayedManager.removeInvalidItems(java.util.List):boolean");
    }

    private boolean isTrackPresentInLibrary(final String trackLuid, final SQLiteDatabase db) {
        return !StringUtil.isNullOrWhiteSpaces(trackLuid) && DatabaseUtils.queryNumEntries(db, "Track", "luid=?", new String[]{trackLuid}) > 0;
    }

    private boolean isRemoteLibraryItem(final RecentlyPlayedItem recentlyPlayedItem) {
        return RecentlyPlayedItem.Type.PLAYLIST.equals(recentlyPlayedItem.getItemType()) || !StringUtil.isNullOrWhiteSpaces(recentlyPlayedItem.getTrackLuid());
    }

    private void setContentUnavailableReasons(List<? extends RecentItem> items) {
        Iterator<? extends RecentItem> it = items.iterator();
        while (it.hasNext()) {
            setContentUnavailableReasons(it.next());
        }
    }

    private void setContentUnavailableReasons(final RecentItem item) {
        item.setContentUnavailableReason(ContentAccessUtil.getPlayCatalogContentUnavailableReason(item));
    }

    private void setArtworkImageUrls(List<RecentlyPlayedItem> items) {
        CTAPrimeCache cTAPrimeCache = new CTAPrimeCache(this.mContext);
        for (RecentlyPlayedItem recentlyPlayedItem : items) {
            if (!recentlyPlayedItem.isRemote() && recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM && recentlyPlayedItem.getOwnershipStatus() == ContentOwnershipStatus.NOT_IN_LIBRARY) {
                recentlyPlayedItem.setArtworkImageUrl(cTAPrimeCache.getArtworkUrlForAlbumId(recentlyPlayedItem.getCollectionId()));
            }
        }
    }

    private static ContentValues toContentValues(RecentlyPlayedItem item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("content_uri", item.getContentUri().toString());
        contentValues.put("local_uri", item.getLocalUri());
        contentValues.put("last_played_time", Long.valueOf(item.getLastPlayedTime()));
        contentValues.put("collection_id", item.getCollectionId());
        contentValues.put("collection_source", item.getSource());
        contentValues.put("collection_type", item.getItemType().toString());
        contentValues.put("track_luid", item.getTrackLuid());
        contentValues.put("track_asin", item.getTrackAsin());
        contentValues.put("shuffle_on", Boolean.valueOf(item.isShuffleOn()));
        contentValues.put("synced", Boolean.valueOf(item.isSynced()));
        contentValues.put("title", item.getTitle());
        contentValues.put("provider_state", item.getTrackProviderState());
        contentValues.put("collection_asin", item.getCollectionAsin());
        contentValues.put("is_explicit", Boolean.valueOf(item.isExplicit()));
        contentValues.put("is_remote", Boolean.valueOf(item.isRemote()));
        contentValues.put("catalog_status_tiers", Integer.valueOf(item.getCatalogStatusTiers().serialize()));
        contentValues.put("prime_status", Integer.valueOf(item.getCatalogStatus().getValue()));
        contentValues.put("content_encoding", ContentEncodingDecoder.contentEncodingToString(item.getContentEncoding()));
        if (item.getArtworkImageUrl() != null) {
            contentValues.put("album_art_uri", item.getArtworkImageUrl());
        }
        return contentValues;
    }

    private static ContentValues toAlexaRecentItemContentValues(final MusicTrack track) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("alexa_asin", TextUtils.isEmpty(track.getAsin()) ? null : track.getAsin());
        contentValues.put("alexa_luid", TextUtils.isEmpty(track.getLuid()) ? null : track.getLuid());
        contentValues.put("alexa_album_asin", TextUtils.isEmpty(track.getAlbumAsin()) ? null : track.getAlbumAsin());
        return contentValues;
    }

    private static ContentValues toRecentTrackContentValues(final RecentTrack track) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("recent_track_asin", TextUtils.isEmpty(track.getAsin()) ? null : track.getAsin());
        contentValues.put("recent_track_luid", TextUtils.isEmpty(track.getLuid()) ? null : track.getLuid());
        contentValues.put("recent_album_asin", TextUtils.isEmpty(track.getAlbumAsin()) ? null : track.getAlbumAsin());
        contentValues.put("is_remote", Boolean.valueOf(track.isRemote()));
        contentValues.put("last_played_time", track.getLastPlayedTime());
        contentValues.put("album_art_uri", track.getArtworkImageUrl());
        contentValues.put("recent_track_source", track.getSource());
        contentValues.put("catalog_status_tiers", Integer.valueOf(track.getCatalogStatusTiers().serialize()));
        return contentValues;
    }

    private static RecentTrack toRecentTrack(final MusicTrack track) {
        RecentTrack recentTrack = new RecentTrack(track.getTitle(), track.getArtistName(), track.getSource());
        recentTrack.setAsin(track.getAsin());
        recentTrack.setLuid(track.getLuid());
        recentTrack.setAlbumAsin(track.getAlbumAsin());
        recentTrack.setLastPlayedTime(Long.valueOf(System.currentTimeMillis()));
        recentTrack.setArtworkImageUrl(track.getAlbumArtUrl());
        recentTrack.setArtworkId(track.getAlbumArtId());
        recentTrack.setRemote(false);
        recentTrack.setCatalogStatus(track.getContentCatalogStatus());
        recentTrack.setOwnershipStatus(track.getOwnershipStatus());
        recentTrack.setDownloadState(track.getDownloadState());
        recentTrack.setCatalogStatusTiers(new CatalogStatusTiers(track.isPrime(), track.isAnyCatalog(), track.isFree(), track.isFreeOnDemand(), track.isSonicRush(), track.isSonicRushOnDemand()));
        return recentTrack;
    }

    public RecentlyPlayedItem toRecentItem(final RecentsMetadata recentsMetadata) {
        return toRecentItem(recentsMetadata, true);
    }

    public RecentlyPlayedItem toRecentItem(final RecentsMetadata recentsMetadata, boolean queryForPrimeStatus) {
        RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem(recentsMetadata.getUri());
        recentlyPlayedItem.setTitle(recentsMetadata.getTitle());
        recentlyPlayedItem.setArtworkImageUrl(recentsMetadata.getImageUrl());
        recentlyPlayedItem.setTrackAsin(recentsMetadata.getTrackAsin());
        recentlyPlayedItem.setCollectionAsin(recentsMetadata.getCollectionAsin());
        recentlyPlayedItem.setCollectionId(recentsMetadata.getCollectionId());
        recentlyPlayedItem.setTrackLuid(recentsMetadata.getTrackLuid());
        recentlyPlayedItem.setIsExplicit(recentsMetadata.getIsExplicit());
        recentlyPlayedItem.setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(recentsMetadata.getSupportedTiers()));
        if (queryForPrimeStatus) {
            setPrimeStatus(recentlyPlayedItem);
        }
        setContentUnavailableReasons(recentlyPlayedItem);
        return recentlyPlayedItem;
    }

    private static RecentlyPlayedItem toRecentItem(Cursor c) {
        RecentlyPlayedItem recentlyPlayedItem = new RecentlyPlayedItem(Uri.parse(getString(c, "content_uri")));
        recentlyPlayedItem.setLocalUri(getString(c, "local_uri"));
        recentlyPlayedItem.setLastPlayedTime(getLong(c, "last_played_time"));
        recentlyPlayedItem.setTrackProviderState(getString(c, "provider_state"));
        if (ChildUserUtil.INSTANCE.isChildUser(AmazonApplication.getApplication().getApplicationContext())) {
            recentlyPlayedItem.setTitle(StringUtil.removeExplicitLabelFromAlbumOrTrackTitle(getString(c, "title")));
        } else {
            recentlyPlayedItem.setTitle(getString(c, "title"));
        }
        recentlyPlayedItem.setCollectionId(getString(c, "collection_id"));
        recentlyPlayedItem.setTrackLuid(getString(c, "track_luid"));
        recentlyPlayedItem.setTrackAsin(getString(c, "track_asin"));
        recentlyPlayedItem.setShuffleOn(c.getInt(c.getColumnIndexOrThrow("shuffle_on")) > 0);
        recentlyPlayedItem.setSynced(c.getInt(c.getColumnIndexOrThrow("synced")) > 0);
        recentlyPlayedItem.setCollectionAsin(getString(c, "collection_asin"));
        recentlyPlayedItem.setIsExplicit(c.getInt(c.getColumnIndexOrThrow("is_explicit")) == 1);
        recentlyPlayedItem.setIsRemote(c.getInt(c.getColumnIndexOrThrow("is_remote")) == 1);
        recentlyPlayedItem.setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.deserialize(c.getInt(c.getColumnIndexOrThrow("catalog_status_tiers"))));
        recentlyPlayedItem.setContentEncoding(ContentEncodingDecoder.stringtoContentEncoding(c.getString(c.getColumnIndexOrThrow("content_encoding"))));
        Uri uriBuild = recentlyPlayedItem.getContentUri().buildUpon().appendPath("tracks").build();
        if (MediaProvider.PrimeBrowseTracksCollection.isCollection(uriBuild) || MediaProvider.Station.isStation(uriBuild)) {
            recentlyPlayedItem.setArtworkImageUrl(getString(c, "album_art_uri"));
        }
        if (recentlyPlayedItem.isRemote()) {
            recentlyPlayedItem.setContentCatalogStatus(ContentCatalogStatus.fromValue(c.getInt(c.getColumnIndexOrThrow("prime_status"))));
            recentlyPlayedItem.setArtworkImageUrl(getString(c, "album_art_uri"));
        }
        return recentlyPlayedItem;
    }

    private static RecentTrack toRecentTrack(final Cursor c) {
        RecentTrack recentTrack = new RecentTrack(getString(c, "title"), getString(c, "artist"), getString(c, "recent_track_source"));
        if (AmazonApplication.getCapabilities().isLyricsSupported()) {
            String string = c.getString(c.getColumnIndex("lyrics_state"));
            if (string != null && LyricsStatus.fromString(string) == LyricsStatus.AVAILABLE) {
                recentTrack.setHasLyrics(true);
            } else {
                recentTrack.setHasLyrics(false);
            }
        }
        int columnIndex = c.getColumnIndex("is_explicit");
        if (columnIndex != -1) {
            recentTrack.setExplicit(c.getInt(columnIndex) == 1);
        }
        if (c.getColumnIndex("content_encoding") != -1) {
            recentTrack.setContentEncodings(ContentEncodingDecoder.stringtoContentEncoding(c.getString(columnIndex)));
        }
        int i = c.getInt(c.getColumnIndexOrThrow("ownership_status"));
        int i2 = c.getInt(c.getColumnIndexOrThrow("prime_status"));
        recentTrack.setOwnershipStatus(ContentOwnershipStatus.fromValue(i));
        recentTrack.setDownloadState(c.getInt(c.getColumnIndexOrThrow("download_state")));
        recentTrack.setLocalUri(getString(c, "local_uri"));
        recentTrack.setCatalogStatus(ContentCatalogStatus.fromValue(i2));
        recentTrack.setAlbumName(getString(c, "album"));
        recentTrack.setArtistAsin(getString(c, "artist_asin"));
        recentTrack.setRemote(c.getInt(c.getColumnIndexOrThrow("is_remote")) == 1);
        recentTrack.setArtworkImageUrl(getString(c, "album_art_uri"));
        recentTrack.setArtworkId(getLong(c, "album_art_id"));
        recentTrack.setLastPlayedTime(Long.valueOf(getLong(c, "last_played_time")));
        recentTrack.setAlbumAsin(getString(c, "recent_album_asin"));
        recentTrack.setLuid(getString(c, "recent_track_luid"));
        recentTrack.setAsin(getString(c, "recent_track_asin"));
        recentTrack.setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.deserialize(c.getInt(c.getColumnIndexOrThrow("catalog_status_tiers"))));
        return recentTrack;
    }

    private static String getString(Cursor c, String columnName) {
        return c.getString(c.getColumnIndexOrThrow(columnName));
    }

    private static long getLong(Cursor c, String columnName) {
        return c.getLong(c.getColumnIndexOrThrow(columnName));
    }
}
