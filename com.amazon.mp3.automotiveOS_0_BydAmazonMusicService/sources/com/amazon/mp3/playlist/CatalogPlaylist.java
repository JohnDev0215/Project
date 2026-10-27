package com.amazon.mp3.playlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.MarketplaceDependentItem;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.refinement.Refinable;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.PrimePlaylistsTable;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.PlaylistLookup;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public class CatalogPlaylist extends MarketplaceDependentItem implements Refinable {
    private static final String TAG = "CatalogPlaylist";
    private double createdDate;
    private boolean hasVideoStory;
    private double lastModifiedDate;
    private String mAsin;
    private String mBannerArtUrl;
    private String mCuratedBy;
    private String mDescription;
    private int mDurationSeconds;
    private boolean mFollowed;
    private int mId;
    private boolean mIsEntityPlaylist;
    private boolean mIsPrime;
    private boolean mIsSharedUserPlaylist;
    private String mLuid;
    private boolean mNew;
    private boolean mOptionalInfoSet;
    private String mProfileId;
    private String mThumbnailArtUrl;
    private String mTitle;
    private int mTrackCount;
    private Collection<CatalogPlaylistTrack> mTracks;
    private String mVersion;
    private MediaProvider.Playlists.VisibilityState mVisibility;
    private int numberOfFollowers;

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getItemTypeName() {
        return "Catalog Playlist";
    }

    public String getVisibilityText() {
        return "";
    }

    @Override // com.amazon.mp3.library.item.AbstractItem, com.amazon.mp3.library.item.CatalogContent
    public boolean isAllOwned() {
        return false;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem, com.amazon.mp3.library.item.CatalogContent
    public boolean isAnyCatalog() {
        return true;
    }

    @Override // com.amazon.mp3.library.item.CatalogContent
    public boolean isExplicit() {
        return false;
    }

    public CatalogPlaylist() {
        super(AmazonApplication.getLibraryItemFactory(), null);
        this.mIsPrime = true;
        this.mIsSharedUserPlaylist = false;
        this.mIsEntityPlaylist = false;
        this.mVisibility = MediaProvider.Playlists.VisibilityState.NO_STATE;
        this.mTracks = new ArrayList();
    }

    public String getCuratedBy() {
        return this.mCuratedBy;
    }

    public void setCuratedBy(String curatedBy) {
        this.mCuratedBy = curatedBy;
    }

    public void setVisibility(MediaProvider.Playlists.VisibilityState visibility) {
        this.mVisibility = visibility;
    }

    public MediaProvider.Playlists.VisibilityState getVisibility() {
        return this.mVisibility;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public void setDatabaseId(int id) {
        this.mId = id;
    }

    public int getDatabaseId() {
        return this.mId;
    }

    public void setLuid(String luid) {
        this.mLuid = luid;
    }

    public String getLuid() {
        return this.mLuid;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setAsin(String asin) {
        this.mAsin = asin;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public String getAsin() {
        return this.mAsin;
    }

    public String getProfileId() {
        return this.mProfileId;
    }

    public void setProfileId(String profileId) {
        this.mProfileId = profileId;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setContentUri(Uri contentUri) {
        super.setContentUri(contentUri);
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTrackCount(int trackCount) {
        this.mTrackCount = trackCount;
    }

    public int getTrackCount() {
        return this.mTrackCount;
    }

    public void setDurationSeconds(int seconds) {
        this.mDurationSeconds = seconds;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public void setBannerArtUrl(String url) {
        this.mBannerArtUrl = url;
    }

    public void setThumbnailArtUrl(String url) {
        this.mThumbnailArtUrl = url;
    }

    public String getThumbnailArtUrl() {
        return this.mThumbnailArtUrl;
    }

    public String getBannerArtUrl() {
        return this.mBannerArtUrl;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setHasVideoStory(final boolean hasVideoStory) {
        this.hasVideoStory = hasVideoStory;
    }

    public boolean getHasVideoStory() {
        return this.hasVideoStory;
    }

    public double getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(double lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public double getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(double createdDate) {
        this.createdDate = createdDate;
    }

    public void setTracks(Collection<CatalogPlaylistTrack> collection) {
        this.mOptionalInfoSet = true;
        this.mTracks = collection;
    }

    public Collection<CatalogPlaylistTrack> getTracks() {
        demandOptionalInfo();
        return this.mTracks;
    }

    public Observable<Collection<CatalogPlaylistTrack>> getTracksObservable() {
        return Observable.create(new Observable.OnSubscribe<Collection<CatalogPlaylistTrack>>() { // from class: com.amazon.mp3.playlist.CatalogPlaylist.1
            @Override // rx.functions.Action1
            public void call(final Subscriber<? super Collection<CatalogPlaylistTrack>> subscriber) {
                synchronized (CatalogPlaylist.this) {
                    CatalogPlaylist.this.demandOptionalInfo();
                    subscriber.onNext(CatalogPlaylist.this.mTracks);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isNew() {
        return this.mNew;
    }

    public void setNew(boolean isNew) {
        this.mNew = isNew;
    }

    public boolean isFollowed() {
        return this.mFollowed;
    }

    public void setFollowed(boolean followed) {
        this.mFollowed = followed;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    public void setCatalogStatusTiers(final CatalogStatusTiers catalogStatusTiers) {
        setIsPrime(catalogStatusTiers != null && catalogStatusTiers.getIsPrime());
        super.setCatalogStatusTiers(catalogStatusTiers);
    }

    public boolean isPrime() {
        return this.mIsPrime && AccountDetailUtil.get().isPrimeMusicSupported();
    }

    public void setIsPrime(boolean isPrime) {
        this.mIsPrime = isPrime;
    }

    public boolean isSharedUserPlaylist() {
        return this.mIsSharedUserPlaylist;
    }

    public void setIsSharedUserPlaylist(boolean sharedUserPlaylist) {
        this.mIsSharedUserPlaylist = sharedUserPlaylist;
    }

    public boolean isEntityPlaylist() {
        return this.mIsEntityPlaylist;
    }

    public void setIsEntityPlaylist(boolean isEntityPlaylist) {
        this.mIsEntityPlaylist = isEntityPlaylist;
    }

    public int getNumberOfFollowers() {
        return this.numberOfFollowers;
    }

    public void setNumberOfFollowers(int numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
    }

    @Override // com.amazon.mp3.library.item.MarketplaceDependentItem
    public Uri getContentUri(String source) {
        return MediaProvider.PrimePlaylists.getContentUri(source, getAsin());
    }

    @Override // com.amazon.mp3.library.item.AbstractItem, com.amazon.mp3.library.item.CatalogContent
    public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
        if (isPrime()) {
            return ContentCatalogStatus.PRIME;
        }
        return ContentCatalogStatus.HAWKFIRE;
    }

    public boolean isGoldenPlaylist() {
        return isFree() && isFreeOnDemand();
    }

    public static CatalogPlaylist fromJSON(JSONObject object) throws JSONException {
        return fromJSON(null, null, object);
    }

    public static CatalogPlaylist fromJSON(String asin, String marketplace, JSONObject object) throws JSONException {
        JSONObject jSONObject = object;
        if (jSONObject.has(Environment.PLAYLIST_PATH)) {
            jSONObject = jSONObject.getJSONObject(Environment.PLAYLIST_PATH);
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
        CatalogPlaylist catalogPlaylistCreatePrimePlaylist = createPrimePlaylist(jSONObject.getJSONArray("tracks"));
        String string = jSONObject2.getString("title");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("bannerArt");
        String string2 = jSONObject3 != null ? jSONObject3.getString(ImagesContract.URL) : null;
        JSONObject jSONObject4 = jSONObject2.getJSONObject("fourSquareArt");
        String string3 = jSONObject4 != null ? jSONObject4.getString(ImagesContract.URL) : null;
        String strOptString = jSONObject2.optString(MediaTrack.ROLE_DESCRIPTION);
        String strOptString2 = jSONObject2.optString("curatedBy");
        boolean z = jSONObject2.optBoolean("isPrime", false) || catalogPlaylistCreatePrimePlaylist.isPlaylistPrime();
        boolean zOptBoolean = jSONObject2.optBoolean("isMusicSubscription", false);
        boolean zOptBoolean2 = jSONObject2.optBoolean("isNightwing", false);
        boolean zOptBoolean3 = jSONObject2.optBoolean("isNightwingOnDemandPlayable", false);
        boolean zOptBoolean4 = jSONObject2.optBoolean("isSonicRush", false);
        boolean zOptBoolean5 = jSONObject2.optBoolean("isSonicRushOnDemandPlayable", false);
        boolean zOptBoolean6 = jSONObject2.optBoolean("isSonicRushGolden", false);
        String strOptString3 = jSONObject2.optString("playmodeEligibility");
        double dOptDouble = jSONObject2.optDouble("lastModifiedDate", 0.0d);
        double dOptDouble2 = jSONObject2.optDouble("createdDate", 0.0d);
        catalogPlaylistCreatePrimePlaylist.setVersion(jSONObject2.optString("version"));
        String strOptString4 = jSONObject2.optString("asin", asin);
        catalogPlaylistCreatePrimePlaylist.setAsin(strOptString4);
        catalogPlaylistCreatePrimePlaylist.setLuid(jSONObject2.optString("playlistId"));
        catalogPlaylistCreatePrimePlaylist.setMarketplace(jSONObject2.optString("marketplaceId", marketplace));
        catalogPlaylistCreatePrimePlaylist.setDescription(strOptString);
        if (string2 != null) {
            catalogPlaylistCreatePrimePlaylist.setBannerArtUrl(string2);
        }
        if (string3 != null) {
            catalogPlaylistCreatePrimePlaylist.setThumbnailArtUrl(string3);
        }
        catalogPlaylistCreatePrimePlaylist.setLastModifiedDate(dOptDouble);
        catalogPlaylistCreatePrimePlaylist.setCreatedDate(dOptDouble2);
        catalogPlaylistCreatePrimePlaylist.setTitle(string);
        catalogPlaylistCreatePrimePlaylist.setCuratedBy(strOptString2);
        catalogPlaylistCreatePrimePlaylist.setCatalogStatusTiers(new CatalogStatusTiers(z, zOptBoolean, zOptBoolean2, zOptBoolean3, zOptBoolean4, zOptBoolean5, zOptBoolean6));
        catalogPlaylistCreatePrimePlaylist.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(strOptString3));
        catalogPlaylistCreatePrimePlaylist.setContentUri(MediaProvider.PrimePlaylists.getContentUri("cirrus", strOptString4));
        catalogPlaylistCreatePrimePlaylist.setProfileId(jSONObject2.optString("profileId"));
        catalogPlaylistCreatePrimePlaylist.setFollowed(jSONObject2.optBoolean("isCustomerFollowing", false));
        catalogPlaylistCreatePrimePlaylist.setVisibility(MediaProvider.Playlists.VisibilityState.fromString(jSONObject2.optString("visibility")));
        catalogPlaylistCreatePrimePlaylist.setNumberOfFollowers(jSONObject2.optInt("numFollowers", 0));
        return catalogPlaylistCreatePrimePlaylist;
    }

    private static CatalogPlaylist createPrimePlaylist(final JSONArray tracks) throws JSONException {
        int length = tracks.length();
        ArrayList arrayList = new ArrayList();
        int durationSeconds = 0;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = tracks.getJSONObject(i);
            if (jSONObject != null) {
                try {
                    CatalogPlaylistTrack catalogPlaylistTrackFromJSON = CatalogPlaylistTrack.fromJSON(jSONObject, i + 1);
                    durationSeconds += catalogPlaylistTrackFromJSON.getDurationSeconds();
                    arrayList.add(catalogPlaylistTrackFromJSON);
                } catch (JSONException unused) {
                    Log.error(TAG, "Unable to add track to playlist: " + jSONObject.toString());
                }
            }
        }
        CatalogPlaylist catalogPlaylist = new CatalogPlaylist();
        catalogPlaylist.setTracks(arrayList);
        catalogPlaylist.setTrackCount(arrayList.size());
        catalogPlaylist.setDurationSeconds(durationSeconds);
        return catalogPlaylist;
    }

    public void setPrimeStatusFromTracks(final Context context) {
        if (this.mIsPrime || context == null) {
            return;
        }
        try {
            this.mIsPrime = isPlaylistPrime(getTracksForPlaylist(context));
        } catch (BrowseException e) {
            Log.error(TAG, "Failed to setPrimeStatusFromTracks", e);
        }
    }

    public void updateTrackVideoMetadata(final Context context) {
        try {
            List<PrimeTrack> tracksForPlaylist = getTracksForPlaylist(context);
            HashMap map = new HashMap();
            for (CatalogPlaylistTrack catalogPlaylistTrack : getTracks()) {
                map.put(catalogPlaylistTrack.getAsin(), catalogPlaylistTrack);
            }
            for (PrimeTrack primeTrack : tracksForPlaylist) {
                CatalogPlaylistTrack catalogPlaylistTrack2 = (CatalogPlaylistTrack) map.get(primeTrack.getAsin());
                if (catalogPlaylistTrack2 != null) {
                    catalogPlaylistTrack2.setAssetQualities(primeTrack.getAssetQualities());
                    catalogPlaylistTrack2.setAssetType(primeTrack.getAssetType());
                }
            }
        } catch (BrowseException e) {
            Log.error(TAG, "Failed to updateTrackVideoMetadata", e);
        }
    }

    private boolean isPlaylistPrime(final List<PrimeTrack> tracks) {
        Iterator<PrimeTrack> it = tracks.iterator();
        while (it.hasNext()) {
            if (it.next().isPrime()) {
                return true;
            }
        }
        return false;
    }

    private boolean isPlaylistPrime() {
        Iterator<CatalogPlaylistTrack> it = this.mTracks.iterator();
        while (it.hasNext()) {
            if (ContentCatalogStatus.PRIME.equals(it.next().getCatalogStatus())) {
                return true;
            }
        }
        return false;
    }

    private List<PrimeTrack> getTracksForPlaylist(final Context context) throws BrowseException {
        return PrimeItemsTransformationUtil.toPrimeTrackList(new PlaylistLookup(BrowseFactory.createBrowseService(context)).get(new PlaylistLookup.Request(this.mAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(context, true), AccountCredentialStorage.get(context).getDeviceId(), AccountCredentialStorage.get(context).getDeviceType(), new BrowseLanguageProvider().getLocale(context), true, AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(context))).playlist.getTracks());
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("luid", getLuid());
        contentValues.put("asin", getAsin());
        contentValues.put("version", getVersion());
        contentValues.put("marketplace_id", getMarketplace());
        contentValues.put(MediaTrack.ROLE_DESCRIPTION, getDescription());
        contentValues.put("title", getTitle());
        contentValues.put("track_count", Integer.valueOf(getTrackCount()));
        contentValues.put("duration", Integer.valueOf(getDurationSeconds()));
        contentValues.put("art_url", getBannerArtUrl());
        contentValues.put("thumbnail_art_url", getThumbnailArtUrl());
        contentValues.put(PageUriUtils.SOURCE_QUERY_PARAM, Integer.valueOf(PrimePlaylistsTable.translateSource(getSource())));
        contentValues.put("is_following", isFollowed() ? "1" : "0");
        contentValues.put("curated_by", getCuratedBy());
        contentValues.put("is_prime", isPrime() ? "1" : "0");
        contentValues.put("is_shared_user_playlist", isSharedUserPlaylist() ? "1" : "0");
        contentValues.put("is_entity_playlist", isEntityPlaylist() ? "1" : "0");
        contentValues.put("is_nightwing", isFree() ? "1" : "0");
        contentValues.put("is_nightwing_on_demand", isFreeOnDemand() ? "1" : "0");
        contentValues.put("is_sonic_rush", isSonicRush() ? "1" : "0");
        contentValues.put("is_sonic_rush_on_demand", isSonicRushOnDemand() ? "1" : "0");
        contentValues.put("is_sonic_rush_golden", isSonicRushGolden() ? "1" : "0");
        contentValues.put("playmode_eligibility", PlaymodeEligibility.INSTANCE.getPlaymodeEligibilityString(getPlaymodeEligibility()));
        contentValues.put("profile_id", getProfileId());
        contentValues.put("last_modified_date", Double.valueOf(getLastModifiedDate()));
        contentValues.put("created_date", Double.valueOf(getCreatedDate()));
        contentValues.put("visibility", Integer.valueOf(getVisibility().getInt()));
        contentValues.put("number_of_followers", Integer.valueOf(getNumberOfFollowers()));
        return contentValues;
    }

    public static CatalogPlaylist fromCursor(Cursor c) throws IllegalArgumentException {
        return fromCursor(c, new CatalogPlaylist());
    }

    public static CatalogPlaylist fromCursor(Cursor c, CatalogPlaylist playlist) throws IllegalArgumentException {
        int columnIndex = c.getColumnIndex("_id");
        int columnIndex2 = c.getColumnIndex("luid");
        int columnIndex3 = c.getColumnIndex("asin");
        int columnIndex4 = c.getColumnIndex("marketplace_id");
        int columnIndex5 = c.getColumnIndex("title");
        int columnIndex6 = c.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM);
        int columnIndex7 = c.getColumnIndex("download_state");
        int columnIndex8 = c.getColumnIndex("is_following");
        int columnIndex9 = c.getColumnIndex(MediaTrack.ROLE_DESCRIPTION);
        int columnIndex10 = c.getColumnIndex("is_new");
        int columnIndex11 = c.getColumnIndex("version");
        int columnIndex12 = c.getColumnIndex("track_count");
        int columnIndex13 = c.getColumnIndex("art_url");
        int columnIndex14 = c.getColumnIndex("thumbnail_art_url");
        int columnIndex15 = c.getColumnIndex("duration");
        int columnIndex16 = c.getColumnIndex("curated_by");
        int columnIndex17 = c.getColumnIndex("is_prime");
        int columnIndex18 = c.getColumnIndex("is_shared_user_playlist");
        int columnIndex19 = c.getColumnIndex("is_entity_playlist");
        int columnIndex20 = c.getColumnIndex("is_nightwing");
        int columnIndex21 = c.getColumnIndex("is_nightwing_on_demand");
        int columnIndex22 = c.getColumnIndex("is_sonic_rush");
        int columnIndex23 = c.getColumnIndex("is_sonic_rush_on_demand");
        int columnIndex24 = c.getColumnIndex("is_sonic_rush_golden");
        int columnIndex25 = c.getColumnIndex("playmode_eligibility");
        int columnIndex26 = c.getColumnIndex("profile_id");
        int columnIndex27 = c.getColumnIndex("visibility");
        int columnIndex28 = c.getColumnIndex("number_of_followers");
        if (columnIndex16 != -1) {
            playlist.setCuratedBy(c.getString(columnIndex16));
        }
        if (columnIndex26 != -1) {
            playlist.setProfileId(c.getString(columnIndex26));
        }
        if (columnIndex != -1) {
            playlist.setDatabaseId(c.getInt(columnIndex));
        }
        if (columnIndex3 != -1) {
            String string = c.getString(columnIndex3);
            playlist.setAsin(string);
            playlist.setId(string);
        }
        if (columnIndex2 != -1) {
            playlist.setLuid(c.getString(columnIndex2));
        }
        if (columnIndex11 != -1) {
            playlist.setVersion(c.getString(columnIndex11));
        }
        if (columnIndex6 != -1) {
            playlist.setSource(PrimePlaylistsTable.translateSource(c.getInt(columnIndex6)));
        }
        if (columnIndex5 != -1) {
            playlist.setTitle(c.getString(columnIndex5));
        }
        if (columnIndex12 != -1) {
            playlist.setTrackCount(c.getInt(columnIndex12));
        }
        if (columnIndex13 != -1) {
            playlist.setBannerArtUrl(c.getString(columnIndex13));
        }
        if (columnIndex14 != -1) {
            playlist.setThumbnailArtUrl(c.getString(columnIndex14));
        }
        if (columnIndex15 != -1) {
            playlist.setDurationSeconds(c.getInt(columnIndex15));
        }
        if (columnIndex10 != -1) {
            playlist.setNew("1".equals(c.getString(columnIndex10)));
        }
        if (columnIndex8 != -1) {
            playlist.setFollowed("1".equals(c.getString(columnIndex8)));
        }
        if (columnIndex9 != -1) {
            playlist.setDescription(c.getString(columnIndex9));
        }
        if (columnIndex4 != -1) {
            playlist.setMarketplace(c.getString(columnIndex4));
        }
        if (columnIndex7 != -1) {
            playlist.setDownloadState(c.getInt(columnIndex7));
        }
        if (columnIndex18 != -1) {
            playlist.setIsSharedUserPlaylist("1".equals(c.getString(columnIndex18)));
        }
        if (columnIndex19 != -1) {
            playlist.setIsEntityPlaylist("1".equals(c.getString(columnIndex19)));
        }
        if (columnIndex27 != -1) {
            playlist.setVisibility(MediaProvider.Playlists.VisibilityState.fromInt(c.getInt(columnIndex27)));
        }
        boolean zEquals = columnIndex17 != -1 ? "1".equals(c.getString(columnIndex17)) : false;
        playlist.setCatalogStatusTiers(new CatalogStatusTiers(zEquals, !zEquals, columnIndex20 != -1 ? "1".equals(c.getString(columnIndex20)) : false, columnIndex21 != -1 ? "1".equals(c.getString(columnIndex21)) : false, columnIndex22 != -1 ? "1".equals(c.getString(columnIndex22)) : false, columnIndex23 != -1 ? "1".equals(c.getString(columnIndex23)) : false, columnIndex24 != -1 ? "1".equals(c.getString(columnIndex24)) : false));
        if (columnIndex25 != -1) {
            playlist.setPlaymodeEligibility(EligibilityConverterUtil.getPlaymodeEligibilityFromJson(c.getString(columnIndex25)));
        }
        playlist.setContentUri(MediaProvider.PrimePlaylists.getContentUri(playlist.getSource(), playlist.getAsin()));
        if (columnIndex28 != -1) {
            playlist.numberOfFollowers = c.getInt(columnIndex28);
        }
        return playlist;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected void demandOptionalInfo() {
        if (this.mOptionalInfoSet) {
            return;
        }
        setTracks(this.mLibItemFactory.getTracksForPrimePlaylist(this));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Luid: ");
        sb.append(this.mLuid);
        sb.append(" Title: ");
        sb.append(this.mTitle);
        sb.append(" Asin: ");
        sb.append(this.mAsin);
        sb.append(" Track count: ");
        sb.append(this.mTrackCount);
        sb.append(" Duration (seconds): ");
        sb.append(this.mDurationSeconds);
        sb.append(" Version: ");
        sb.append(this.mVersion);
        sb.append(" mFollowed: ");
        sb.append(this.mFollowed ? "True" : "False");
        return sb.toString();
    }

    public boolean equalsPlaylist(final CatalogPlaylist playlist) {
        if (playlist == null || !this.mTitle.equals(playlist.getTitle()) || !this.mAsin.equals(playlist.getAsin())) {
            return false;
        }
        if (this.mTrackCount == 0 || this.mTracks.size() != this.mTrackCount) {
            getTracks();
        }
        if (playlist.mTrackCount == 0 || playlist.mTracks.size() != playlist.mTrackCount) {
            playlist.getTracks();
        }
        if (this.mTracks.size() != playlist.mTracks.size() || playlist.isPrime() != isPrime() || playlist.isFree() != isFree() || playlist.isFreeOnDemand() != isFreeOnDemand() || playlist.isSonicRush() != isSonicRush() || playlist.isSonicRushOnDemand() != isSonicRushOnDemand() || playlist.isSonicRushGolden() != isSonicRushGolden()) {
            return false;
        }
        Iterator<CatalogPlaylistTrack> it = this.mTracks.iterator();
        Iterator<CatalogPlaylistTrack> it2 = playlist.getTracks().iterator();
        if (it2 == null) {
            return false;
        }
        while (it.hasNext()) {
            if (!it.next().getAsin().equals(it2.next().getAsin())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazon.mp3.library.item.AbstractItem
    protected EntityItemType getEntityItemType() {
        return CollectionType.Playlist.INSTANCE;
    }
}
