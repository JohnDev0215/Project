package com.amazon.mp3.playlist;

import android.content.ContentValues;
import android.database.Cursor;
import com.amazon.mp3.lyrics.item.LyricsStatus;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.mp3.util.MatchHashUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.models.AssetQuality;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CatalogPlaylistTrack {
    public static final String[] DEFAULT_PROJECTION = {"asin", "title", "duration", "album_art_url_thumbnail", "album_art_url_large", "artist", "artist_asin", "album", "album_asin", "album_artist", "album_artist_asin", ParserUtil.GENRE_SEGMENT_NAME, "catalog_status", "fileSizeKilobytes", "match_hash", "lyrics_state", "artwork_id", "track_source", "is_explicit", "content_encoding", "lyrics_state"};
    private static final Type listType = new TypeToken<List<AssetQuality>>() { // from class: com.amazon.mp3.playlist.CatalogPlaylistTrack.1
    }.getType();
    private String lyricsState;
    private String mAlbumArtistAsin;
    private String mAlbumArtistName;
    private String mAlbumAsin;
    private String mAlbumName;
    private String mArtistAsin;
    private String mArtistName;
    private long mArtworkId;
    private String mAsin;
    private List<AssetQuality> mAssetQualities;
    private PrimePlaylistTracksTable.AssetType mAssetType;
    private ContentCatalogStatus mCatalogStatus;
    private CatalogStatusTiers mCatalogStatusTiers;
    private List<ContentEncoding> mContentEncoding;
    private int mDurationSeconds;
    private boolean mExclusionTagged;
    private int mFileSize;
    private String mGenre;
    private boolean mIsExplicit;
    private String mLargeAlbumArtUrl;
    private long mMatchHash;
    private PlaymodeEligibility mPlaymodeEligibility;
    private int mSource;
    private String mThumbnailAlbumArtUrl;
    private String mTitle;
    private int mTrackNumber;

    public String getLuid() {
        return null;
    }

    public void setArtworkId(long artworkId) {
        this.mArtworkId = artworkId;
    }

    public void setLyricsState(final String lyricsState) {
        this.lyricsState = lyricsState;
    }

    public boolean hasLyrics() {
        return LyricsStatus.fromString(this.lyricsState) == LyricsStatus.AVAILABLE;
    }

    public int getSource() {
        return this.mSource;
    }

    public void setSource(int source) {
        this.mSource = source;
    }

    public String getAsin() {
        return this.mAsin;
    }

    public void setAsin(String asin) {
        this.mAsin = asin;
    }

    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.mTrackNumber = trackNumber;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getAlbumName() {
        return this.mAlbumName;
    }

    public void setAlbumName(String albumName) {
        this.mAlbumName = albumName;
    }

    public String getAlbumAsin() {
        return this.mAlbumAsin;
    }

    public void setAlbumAsin(String albumAsin) {
        this.mAlbumAsin = albumAsin;
    }

    public String getAlbumArtistAsin() {
        return this.mAlbumArtistAsin;
    }

    public void setAlbumArtistAsin(String albumArtistAsin) {
        this.mAlbumArtistAsin = albumArtistAsin;
    }

    public String getAlbumArtistName() {
        return this.mAlbumArtistName;
    }

    public void setAlbumArtistName(String albumArtistName) {
        this.mAlbumArtistName = albumArtistName;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getArtistName() {
        return this.mArtistName;
    }

    public void setArtistName(String artistName) {
        this.mArtistName = artistName;
    }

    public String getArtistAsin() {
        return this.mArtistAsin;
    }

    public void setArtistAsin(String artistAsin) {
        this.mArtistAsin = artistAsin;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.mDurationSeconds = durationSeconds;
    }

    public String getThumbnailAlbumArtUrl() {
        return this.mThumbnailAlbumArtUrl;
    }

    public void setThumbnailAlbumArtUrl(String thumbnailAlbumArtUrl) {
        this.mThumbnailAlbumArtUrl = thumbnailAlbumArtUrl;
    }

    public String getLargeAlbumArtUrl() {
        return this.mLargeAlbumArtUrl;
    }

    public void setLargeAlbumArtUrl(String largeAlbumArtUrl) {
        this.mLargeAlbumArtUrl = largeAlbumArtUrl;
    }

    public void setCatalogStatus(ContentCatalogStatus catalogStatus) {
        CatalogStatusTiers catalogStatusTiers = this.mCatalogStatusTiers;
        if (catalogStatusTiers == null || !(catalogStatusTiers.getIsSonicRush() || this.mCatalogStatusTiers.getIsFree())) {
            setCatalogStatusTiers(CatalogStatusTiers.INSTANCE.from(catalogStatus.getSupportedTiers()));
        }
    }

    public ContentCatalogStatus getCatalogStatus() {
        return this.mCatalogStatus;
    }

    public void setFileSize(int size) {
        this.mFileSize = size;
    }

    public int getFileSize() {
        return this.mFileSize;
    }

    public void setIsExplicit(boolean isExplicit) {
        this.mIsExplicit = isExplicit;
    }

    public boolean isExplicit() {
        return this.mIsExplicit;
    }

    public List<ContentEncoding> getContentEncoding() {
        return this.mContentEncoding;
    }

    public void setContentEncoding(final List<ContentEncoding> contentEncoding) {
        this.mContentEncoding = contentEncoding;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.mAssetQualities;
    }

    public void setAssetQualities(final List<AssetQuality> assetQualities) {
        this.mAssetQualities = assetQualities;
    }

    public PrimePlaylistTracksTable.AssetType getAssetType() {
        return this.mAssetType;
    }

    public void setAssetType(final PrimePlaylistTracksTable.AssetType assetType) {
        this.mAssetType = assetType;
    }

    public boolean isExclusionTagged() {
        return this.mExclusionTagged;
    }

    public void setIsExclusionTagged(boolean exclusionTagged) {
        this.mExclusionTagged = exclusionTagged;
    }

    public void setPlaymodeEligibility(final PlaymodeEligibility playmodeEligibility) {
        this.mPlaymodeEligibility = playmodeEligibility;
    }

    public void setCatalogStatusTiers(CatalogStatusTiers catalogStatusTiers) {
        this.mCatalogStatusTiers = catalogStatusTiers;
        this.mCatalogStatus = ContentCatalogStatus.fromValues(catalogStatusTiers, false, PrimePlaylistTracksTable.AssetType.VIDEO.equals(this.mAssetType));
    }

    public static CatalogPlaylistTrack fromJSON(JSONObject object, int order) throws JSONException {
        CatalogPlaylistTrack catalogPlaylistTrack = new CatalogPlaylistTrack();
        catalogPlaylistTrack.parseTrackMetadata(object, order);
        return catalogPlaylistTrack;
    }

    private void parseTrackMetadata(JSONObject object, int order) throws JSONException {
        JSONObject jSONObject = object.getJSONObject("metadata");
        int iOptInt = object.optInt("trackNumber");
        if (iOptInt != 0) {
            order = iOptInt;
        }
        setTrackNumber(order);
        setAsin(jSONObject.getString("asin"));
        setArtworkId(r9.hashCode());
        setAlbumName(jSONObject.getString("albumName"));
        setAlbumAsin(jSONObject.getString("albumAsin"));
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(ContextMappingConstants.CONTENT_ENCODING);
        if (jSONArrayOptJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.getString(i));
            }
            setContentEncoding(ContentEncodingDecoder.loadFrom(arrayList));
        }
        JSONArray jSONArray = jSONObject.getJSONArray("albumArtists");
        if (jSONArray.length() > 0) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(0);
            setAlbumArtistName(jSONObject2.getString("name"));
            setAlbumArtistAsin(jSONObject2.getString("digitalAsin"));
        }
        if (jSONObject.has("primaryGenre")) {
            setGenre(jSONObject.getString("primaryGenre"));
        } else {
            setGenre("UNKNOWN_GENRE");
        }
        setTitle(jSONObject.getString("title"));
        setDurationSeconds(jSONObject.getInt("durationSeconds"));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("albumArt");
        if (jSONObjectOptJSONObject != null) {
            setThumbnailAlbumArtUrl(jSONObjectOptJSONObject.getString(ImagesContract.URL));
            setLargeAlbumArtUrl(jSONObjectOptJSONObject.getString(ImagesContract.URL));
        }
        setFileSize(jSONObject.getInt("fileSizeKilobytes"));
        setSource(0);
        JSONArray jSONArray2 = jSONObject.getJSONArray("artists");
        if (jSONArray2.length() > 0) {
            JSONObject jSONObject3 = jSONArray2.getJSONObject(0);
            setArtistName(jSONObject3.getString("name"));
            setArtistAsin(jSONObject3.getString("digitalAsin"));
        }
        boolean z = jSONObject.getBoolean("prime");
        boolean z2 = jSONObject.getBoolean("musicSubscription");
        boolean zOptBoolean = jSONObject.optBoolean("free", false);
        boolean zOptBoolean2 = jSONObject.optBoolean("freeOnDemandPlayable", false);
        boolean zOptBoolean3 = jSONObject.optBoolean("sonicRush", false);
        boolean zOptBoolean4 = jSONObject.optBoolean("sonicRushOnDemandPlayable", false);
        if (z) {
            setCatalogStatus(ContentCatalogStatus.PRIME);
        } else if (zOptBoolean3) {
            setCatalogStatus(ContentCatalogStatus.SONIC_RUSH);
        } else if (z2) {
            setCatalogStatus(ContentCatalogStatus.HAWKFIRE);
        } else {
            setCatalogStatus(ContentCatalogStatus.PREVIOUSLY_CATALOG);
        }
        setCatalogStatusTiers(new CatalogStatusTiers(z, z2, zOptBoolean, zOptBoolean2, zOptBoolean3, zOptBoolean4));
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("parentalControls");
        if (jSONObjectOptJSONObject2 != null) {
            setIsExplicit(jSONObjectOptJSONObject2.optBoolean("hasExplicitLanguage", false));
        }
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        String asin = getAsin();
        contentValues.put("asin", asin);
        contentValues.put("artwork_id", Integer.valueOf(asin.hashCode()));
        contentValues.put("title", getTitle());
        contentValues.put("sort_title", StringUtil.normalizeTrackName(getTitle()));
        contentValues.put("duration", Integer.valueOf(getDurationSeconds()));
        contentValues.put("album_art_url_thumbnail", getThumbnailAlbumArtUrl());
        contentValues.put("album_art_url_large", getLargeAlbumArtUrl());
        String artistName = getArtistName();
        contentValues.put("artist", artistName);
        contentValues.put("artist_asin", getArtistAsin());
        contentValues.put("album", getAlbumName());
        contentValues.put("album_asin", getAlbumAsin());
        contentValues.put("album_artist", getAlbumArtistName());
        contentValues.put("album_artist_asin", getAlbumArtistAsin());
        contentValues.put(ParserUtil.GENRE_SEGMENT_NAME, getGenre());
        contentValues.put("catalog_status", Integer.valueOf(getCatalogStatus().getValue()));
        contentValues.put("track_source", Integer.valueOf(getSource()));
        contentValues.put("match_hash", Long.valueOf(MatchHashUtil.generatePrimePlaylistTrackMatchHash(r2, artistName != null ? StringUtil.normalizeArtistName(artistName) : null, asin)));
        contentValues.put("fileSizeKilobytes", Integer.valueOf(getFileSize()));
        contentValues.put("track_num", Integer.valueOf(getTrackNumber()));
        contentValues.put("is_explicit", Integer.valueOf(isExplicit() ? 1 : 0));
        contentValues.put("content_encoding", ContentEncodingDecoder.contentEncodingToString(getContentEncoding()));
        List<AssetQuality> list = this.mAssetQualities;
        if (list != null) {
            contentValues.put("asset_qualities", getAssetQualitiesString(list));
        }
        PrimePlaylistTracksTable.AssetType assetType = this.mAssetType;
        if (assetType != null) {
            contentValues.put("asset_type", assetType.toString());
        }
        contentValues.put("is_exclusion_tagged", Integer.valueOf(isExclusionTagged() ? 1 : 0));
        PlaymodeEligibility playmodeEligibility = this.mPlaymodeEligibility;
        if (playmodeEligibility != null) {
            contentValues.put("playmode_eligibility", PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityString(playmodeEligibility));
        }
        contentValues.put("catalog_status_tiers", Integer.valueOf(this.mCatalogStatusTiers.serialize()));
        return contentValues;
    }

    public static CatalogPlaylistTrack fromCursor(Cursor c) {
        int i;
        int i2;
        String string;
        int columnIndex = c.getColumnIndex("asin");
        int columnIndex2 = c.getColumnIndex("title");
        int columnIndex3 = c.getColumnIndex("duration");
        int columnIndex4 = c.getColumnIndex("album_art_url_thumbnail");
        int columnIndex5 = c.getColumnIndex("album_art_url_large");
        int columnIndex6 = c.getColumnIndex("artist");
        int columnIndex7 = c.getColumnIndex("artist_asin");
        int columnIndex8 = c.getColumnIndex("album");
        int columnIndex9 = c.getColumnIndex("album_asin");
        int columnIndex10 = c.getColumnIndex("album_artist");
        int columnIndex11 = c.getColumnIndex("album_artist_asin");
        int columnIndex12 = c.getColumnIndex(ParserUtil.GENRE_SEGMENT_NAME);
        int columnIndex13 = c.getColumnIndex("catalog_status");
        int columnIndex14 = c.getColumnIndex("match_hash");
        int columnIndex15 = c.getColumnIndex("track_num");
        int columnIndex16 = c.getColumnIndex("artwork_id");
        int columnIndex17 = c.getColumnIndex("track_source");
        int columnIndex18 = c.getColumnIndex("is_explicit");
        int columnIndex19 = c.getColumnIndex("content_encoding");
        int columnIndex20 = c.getColumnIndex("asset_qualities");
        int columnIndex21 = c.getColumnIndex("asset_type");
        int columnIndex22 = c.getColumnIndex("lyrics_state");
        int columnIndex23 = c.getColumnIndex("is_exclusion_tagged");
        int columnIndex24 = c.getColumnIndex("playmode_eligibility");
        int columnIndex25 = c.getColumnIndex("catalog_status_tiers");
        CatalogPlaylistTrack catalogPlaylistTrack = new CatalogPlaylistTrack();
        if (columnIndex16 != -1) {
            i = columnIndex9;
            i2 = columnIndex10;
            catalogPlaylistTrack.setArtworkId(c.getInt(columnIndex16));
        } else {
            i = columnIndex9;
            i2 = columnIndex10;
        }
        if (columnIndex22 != -1) {
            catalogPlaylistTrack.setLyricsState(c.getString(columnIndex22));
        }
        if (columnIndex14 != -1) {
            catalogPlaylistTrack.mMatchHash = c.getLong(columnIndex14);
        }
        if (columnIndex != -1) {
            catalogPlaylistTrack.setAsin(c.getString(columnIndex));
        }
        if (columnIndex2 != -1) {
            catalogPlaylistTrack.setTitle(c.getString(columnIndex2));
        }
        if (columnIndex3 != -1) {
            catalogPlaylistTrack.setDurationSeconds(c.getInt(columnIndex3));
        }
        if (columnIndex4 != -1) {
            catalogPlaylistTrack.setThumbnailAlbumArtUrl(c.getString(columnIndex4));
        }
        if (columnIndex5 != -1) {
            catalogPlaylistTrack.setLargeAlbumArtUrl(c.getString(columnIndex5));
        }
        if (columnIndex6 != -1) {
            catalogPlaylistTrack.setArtistName(c.getString(columnIndex6));
        }
        if (columnIndex7 != -1) {
            catalogPlaylistTrack.setArtistAsin(c.getString(columnIndex7));
        }
        if (columnIndex8 != -1) {
            catalogPlaylistTrack.setAlbumName(c.getString(columnIndex8));
        }
        int i3 = i;
        if (i3 != -1) {
            catalogPlaylistTrack.setAlbumAsin(c.getString(i3));
        }
        int i4 = i2;
        if (i4 != -1) {
            catalogPlaylistTrack.setAlbumArtistName(c.getString(i4));
        }
        if (columnIndex11 != -1) {
            catalogPlaylistTrack.setAlbumArtistAsin(c.getString(columnIndex11));
        }
        if (columnIndex12 != -1) {
            catalogPlaylistTrack.setGenre(c.getString(columnIndex12));
        }
        if (columnIndex13 != -1) {
            catalogPlaylistTrack.setCatalogStatus(ContentCatalogStatus.fromValue(c.getInt(columnIndex13)));
        }
        if (columnIndex15 != -1) {
            catalogPlaylistTrack.setTrackNumber(c.getInt(columnIndex15));
        }
        if (columnIndex17 != -1) {
            catalogPlaylistTrack.setSource(c.getInt(columnIndex17));
        }
        if (columnIndex18 != -1) {
            catalogPlaylistTrack.setIsExplicit(c.getInt(columnIndex18) == 1);
        }
        if (columnIndex19 != -1) {
            catalogPlaylistTrack.setContentEncoding(ContentEncodingDecoder.stringtoContentEncoding(c.getString(columnIndex19)));
        }
        if (columnIndex20 != -1) {
            catalogPlaylistTrack.setAssetQualities(getAssetQualityFromJson(c.getString(columnIndex20)));
        }
        if (columnIndex21 != -1 && (string = c.getString(columnIndex21)) != null && PrimePlaylistTracksTable.AssetType.containsType(string)) {
            catalogPlaylistTrack.setAssetType(PrimePlaylistTracksTable.AssetType.valueOf(string));
        } else {
            catalogPlaylistTrack.setAssetType(PrimePlaylistTracksTable.AssetType.AUDIO);
        }
        if (columnIndex23 != -1) {
            catalogPlaylistTrack.setIsExclusionTagged(c.getInt(columnIndex23) == 1);
        }
        if (columnIndex24 != -1) {
            catalogPlaylistTrack.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(c.getString(columnIndex24)));
        }
        if (columnIndex25 != -1) {
            CatalogStatusTiers catalogStatusTiersDeserialize = CatalogStatusTiers.INSTANCE.deserialize(c.getInt(columnIndex25));
            if (catalogStatusTiersDeserialize.isInCatalog()) {
                catalogPlaylistTrack.setCatalogStatusTiers(catalogStatusTiersDeserialize);
            }
        }
        return catalogPlaylistTrack;
    }

    public String getAlbumArtUrl() {
        return this.mThumbnailAlbumArtUrl;
    }

    public static String getAssetQualitiesString(final List<AssetQuality> assetQualities) {
        if (assetQualities == null || assetQualities.isEmpty()) {
            return null;
        }
        return new Gson().toJson(assetQualities, listType);
    }

    public static List<AssetQuality> getAssetQualityFromJson(final String jsonAssetQualities) {
        if (StringUtil.isNullOrWhiteSpaces(jsonAssetQualities)) {
            return null;
        }
        return (List) new Gson().fromJson(jsonAssetQualities, listType);
    }
}
