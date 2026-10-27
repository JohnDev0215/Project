package com.amazon.mp3.prime.cta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.prime.CTAPrimeCache;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.data.AsinSubstitutionException;
import com.amazon.mp3.prime.data.AsinSubstitutionHandler;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.EligibilityConverterUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.AlbumLookup;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;

/* JADX INFO: loaded from: classes4.dex */
public class CtaAlbumRequester implements AlbumRequester {
    private static final String TAG = "CtaAlbumRequester";
    private AlbumMetadata albumMetadata;
    private AlbumResponseListener albumResponseListener;
    private final Context mContext;
    private final boolean mForceRefresh;
    private final CTAPrimeCache mPrimeCache;
    private CTAPrimeCache.AlbumMetadataChangeListener metadataChangeListener;
    private static final String[] PROJECTION = {"asin", "artist_asin", "artist", "album_artist", ParserUtil.GENRE_SEGMENT_NAME, "title", "duration", "track_num", "disc_num", "size", "album", "album_asin", "prime_status", "lyrics_state", "is_explicit", "content_encoding", "playlist_track_status", "asset_qualities", "asset_type", "playmode_eligibility", "ownership_status", "album_playmode_eligibility", "catalog_status_tiers"};
    private static Map asinLocksMap = new ConcurrentHashMap();

    public interface AlbumResponseListener {
        void onAlbumRequestComplete(String requestedAlbumAsin);
    }

    public CtaAlbumRequester(Context context, boolean forceRefresh) {
        this(context, forceRefresh, null, null);
    }

    public CtaAlbumRequester(Context context, boolean forceRefresh, CTAPrimeCache.AlbumMetadataChangeListener metadataChangeListener, AlbumResponseListener albumResponseListener) {
        this.mPrimeCache = new CTAPrimeCache(context, 900000, metadataChangeListener);
        this.mForceRefresh = forceRefresh;
        this.mContext = context;
        this.metadataChangeListener = metadataChangeListener;
        this.albumResponseListener = albumResponseListener;
    }

    @Override // com.amazon.mp3.prime.cta.AlbumRequester
    public List<PrimeTrack> requestTracksForAsin(String albumAsin) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException, SQLiteDatabaseLockedException, AsinSubstitutionException {
        if (Feature.update_purchased_track_asin_substitution.isEnabled()) {
            return synchronizedRequestTracksForAsin(albumAsin);
        }
        return performRequestTracksForAsin(albumAsin, null);
    }

    private List<PrimeTrack> synchronizedRequestTracksForAsin(final String albumAsin) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException, SQLiteDatabaseLockedException, AsinSubstitutionException {
        List<PrimeTrack> listPerformRequestTracksForAsin;
        Object obj = asinLocksMap.get(albumAsin);
        if (obj == null) {
            obj = new Object();
            asinLocksMap.putIfAbsent(albumAsin, obj);
        }
        synchronized (obj) {
            listPerformRequestTracksForAsin = performRequestTracksForAsin(albumAsin, obj);
            asinLocksMap.remove(albumAsin, obj);
        }
        return listPerformRequestTracksForAsin;
    }

    private List<PrimeTrack> performRequestTracksForAsin(final String asin, final Object lock) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException, SQLiteDatabaseLockedException, AsinSubstitutionException {
        Map<String, String> mapEmptyMap;
        List<PrimeTrack> listEmptyList = Collections.emptyList();
        if (lock != null) {
            asinLocksMap.putIfAbsent(asin, lock);
        }
        int i = 0;
        if (!this.mPrimeCache.containsAsin(asin) || this.mForceRefresh) {
            listEmptyList = getTracksForAlbumAsin(asin);
            String albumAsin = listEmptyList.size() > 0 ? listEmptyList.get(0).getAlbumAsin() : null;
            if (Feature.update_purchased_track_asin_substitution.isEnabled() && albumAsin != null && !asin.equals(albumAsin)) {
                if (lock != null) {
                    asinLocksMap.putIfAbsent(albumAsin, lock);
                }
                Map<String, String> mapHandleAlbumAsinSubstitution = new AsinSubstitutionHandler().handleAlbumAsinSubstitution(albumAsin, asin, listEmptyList);
                CTAPrimeCache.AlbumMetadataChangeListener albumMetadataChangeListener = this.metadataChangeListener;
                if (albumMetadataChangeListener != null) {
                    albumMetadataChangeListener.onAlbumAsinChanged(albumAsin);
                }
                mapEmptyMap = mapHandleAlbumAsinSubstitution;
                asin = albumAsin;
            } else {
                mapEmptyMap = Collections.emptyMap();
            }
            List<PrimeTrack> accessibleCatalogTracks = getAccessibleCatalogTracks(listEmptyList);
            if (!accessibleCatalogTracks.isEmpty()) {
                this.mPrimeCache.insertPrimeTracks(asin, null, null, accessibleCatalogTracks, mapEmptyMap);
            } else {
                this.mPrimeCache.insertNonPrimeTracks(asin, listEmptyList);
            }
        } else {
            QueryAlbumByAsin queryAlbumByAsin = new QueryAlbumByAsin(this.mContext);
            AlbumResponseListener albumResponseListener = this.albumResponseListener;
            if (albumResponseListener != null) {
                albumResponseListener.onAlbumRequestComplete(asin);
            }
            Uri uriFindCollectionUriByAsin = queryAlbumByAsin.findCollectionUriByAsin(asin);
            if (uriFindCollectionUriByAsin == null) {
                Log.error(TAG, "Collection uri is null for albumAsin: " + asin);
                return Collections.emptyList();
            }
            Uri uriConvertCollectionUriToTracksUri = queryAlbumByAsin.convertCollectionUriToTracksUri(uriFindCollectionUriByAsin);
            String artworkUrlForAsin = this.mPrimeCache.getArtworkUrlForAsin(asin);
            Cursor cursorQuery = MediaProvider.getInstance().query(uriConvertCollectionUriToTracksUri, PROJECTION, null, null, "track_num ASC");
            if (cursorQuery != null) {
                try {
                    listEmptyList = new ArrayList<>(cursorQuery.getCount());
                    cursorQuery.moveToFirst();
                    while (!cursorQuery.isAfterLast()) {
                        PrimeTrack trackFromCursor = parseTrackFromCursor(cursorQuery, artworkUrlForAsin);
                        trackFromCursor.setTrackNum(i);
                        listEmptyList.add(trackFromCursor);
                        i++;
                        cursorQuery.moveToNext();
                    }
                } finally {
                    cursorQuery.close();
                }
            }
        }
        asinLocksMap.remove(asin);
        return listEmptyList;
    }

    @Override // com.amazon.mp3.prime.cta.AlbumRequester
    public AlbumMetadata requestAlbumRemoteMetadataForAsin(final String albumAsin) throws JSONException, ServiceException, BrowseException, AbstractHttpClient.HttpClientException {
        if (this.albumMetadata == null) {
            AlbumLookup.Response albumLookupResponse = getAlbumLookupResponse(albumAsin);
            this.albumMetadata = new AlbumMetadata(albumLookupResponse.album.getProductDetails() != null ? albumLookupResponse.album.getProductDetails().getCopyright() : null, ContentEncodingDecoder.loadFrom(albumLookupResponse.album.getContentEncoding()), getTracksMetadata(albumLookupResponse.album.getTracks()), BrushConverterUtils.convertPlaymodeEligibility(albumLookupResponse.album.getPlaymodeEligibility()));
        }
        return this.albumMetadata;
    }

    private PrimeTrack parseTrackFromCursor(Cursor cursor, @Nullable String artworkUri) {
        String string = cursor.getString(cursor.getColumnIndexOrThrow("title"));
        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("asin"));
        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("artist"));
        String string4 = cursor.getString(cursor.getColumnIndexOrThrow("artist_asin"));
        String string5 = cursor.getString(cursor.getColumnIndexOrThrow("album"));
        String string6 = cursor.getString(cursor.getColumnIndexOrThrow("album_asin"));
        ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursor.getInt(cursor.getColumnIndexOrThrow("prime_status")));
        ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursor.getInt(cursor.getColumnIndexOrThrow("ownership_status")));
        PlaymodeEligibility playmodeEligibilityFromJson = EligibilityConverterUtil.getPlaymodeEligibilityFromJson(cursor.getString(cursor.getColumnIndexOrThrow("playmode_eligibility")));
        PlaymodeEligibility playmodeEligibilityFromJson2 = EligibilityConverterUtil.getPlaymodeEligibilityFromJson(cursor.getString(cursor.getColumnIndexOrThrow("album_playmode_eligibility")));
        Integer numValueOf = Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("duration")));
        Integer numValueOf2 = Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("track_num")));
        Integer numValueOf3 = Integer.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow("disc_num")));
        String string7 = cursor.getString(cursor.getColumnIndex("asset_type"));
        CatalogStatusTiers.Companion companion = CatalogStatusTiers.INSTANCE;
        CatalogStatusTiers catalogStatusTiersDeserialize = companion.deserialize(cursor.getInt(cursor.getColumnIndexOrThrow("catalog_status_tiers")));
        PrimeTrack primeTrack = new PrimeTrack(string, string2, artworkUri, string3, string4, string5, string6, string3, PrimePlaylistTracksTable.AssetType.VIDEO.name().equals(string7), contentOwnershipStatusFromValue, catalogStatusTiersDeserialize.isInCatalog() ? catalogStatusTiersDeserialize : companion.from(contentCatalogStatusFromValue.getSupportedTiers()));
        primeTrack.setPlaymodeEligibility(playmodeEligibilityFromJson);
        primeTrack.setAlbumPlaymodeEligibility(playmodeEligibilityFromJson2);
        primeTrack.setDuration(numValueOf);
        primeTrack.setTrackNum(numValueOf2.intValue());
        primeTrack.setDiscNum(numValueOf3.intValue());
        int columnIndex = cursor.getColumnIndex("is_explicit");
        if (columnIndex != -1) {
            primeTrack.setIsExplicit(cursor.getInt(columnIndex) == 1);
        }
        int columnIndex2 = cursor.getColumnIndex("content_encoding");
        if (columnIndex2 != -1) {
            primeTrack.setContentEncoding(ContentEncodingDecoder.stringtoContentEncoding(cursor.getString(columnIndex2)));
        }
        return primeTrack;
    }

    private List<PrimeTrack> getTracksForAlbumAsin(String albumAsin) throws BrowseException {
        AlbumLookup.Response albumLookupResponse = getAlbumLookupResponse(albumAsin);
        String copyright = albumLookupResponse.album.getProductDetails() != null ? albumLookupResponse.album.getProductDetails().getCopyright() : null;
        List<ContentEncoding> listLoadFrom = ContentEncodingDecoder.loadFrom(albumLookupResponse.album.getContentEncoding());
        AlbumMetadata albumMetadata = this.albumMetadata;
        if (albumMetadata == null) {
            this.albumMetadata = new AlbumMetadata(copyright, listLoadFrom, getTracksMetadata(albumLookupResponse.album.getTracks()), BrushConverterUtils.convertPlaymodeEligibility(albumLookupResponse.album.getPlaymodeEligibility()));
        } else {
            albumMetadata.setCopyright(copyright);
            this.albumMetadata.setContentEncoding(listLoadFrom);
        }
        List<PrimeTrack> primeTrackList = PrimeItemsTransformationUtil.toPrimeTrackList(albumLookupResponse.album.getTracks());
        AlbumMetadata albumMetadata2 = this.albumMetadata;
        if (albumMetadata2 != null && albumMetadata2.getPlaymodeEligibility() != null) {
            for (int i = 0; i < primeTrackList.size(); i++) {
                primeTrackList.get(i).setAlbumPlaymodeEligibility(this.albumMetadata.getPlaymodeEligibility());
            }
        }
        return primeTrackList;
    }

    public AlbumLookup.Response getAlbumLookupResponse(final String albumAsin) throws BrowseException {
        BrowseContentSelector browseContentSelectionWithOwnedContent = new BrowseContentSelectionProvider().getBrowseContentSelectionWithOwnedContent(this.mContext, true);
        AccountDetailUtil.get(this.mContext);
        AlbumLookup.Request request = new AlbumLookup.Request(albumAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), browseContentSelectionWithOwnedContent, AccountCredentialStorage.get(this.mContext).getDeviceId(), AccountCredentialStorage.get(this.mContext).getDeviceType(), new BrowseLanguageProvider().getLocale(this.mContext), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.mContext));
        AlbumLookup albumLookup = new AlbumLookup(BrowseFactory.createBrowseService(this.mContext));
        AlbumResponseListener albumResponseListener = this.albumResponseListener;
        if (albumResponseListener != null) {
            albumResponseListener.onAlbumRequestComplete(albumAsin);
        }
        return albumLookup.get(request);
    }

    private Map<String, TrackMetadata> getTracksMetadata(List<TrackItem> trackItems) {
        HashMap map = new HashMap();
        for (TrackItem trackItem : trackItems) {
            map.put(trackItem.getAsin(), new TrackMetadata(ContentEncodingDecoder.loadFrom(trackItem.getContentEncoding())));
        }
        return map;
    }

    private List<PrimeTrack> getAccessibleCatalogTracks(List<PrimeTrack> tracks) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator<PrimeTrack> it = tracks.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PrimeTrack next = it.next();
            boolean z = ContentAccessUtil.getCatalogContentUnavailableReason(next, ContentAccessUtil.ContentAccessOperation.BROWSE) == null;
            boolean zIsAnyCatalog = next.isAnyCatalog();
            if (z && zIsAnyCatalog) {
                arrayList.add(next);
            } else if (!zIsAnyCatalog) {
                arrayList2.add(next.getAsin());
            }
        }
        if (!arrayList2.isEmpty()) {
            Log.verbose(TAG, "tracks: [%s] will be updated to non-catalog", arrayList2.toString());
            this.mPrimeCache.updateTrackStatus(arrayList2, ContentCatalogStatus.NON_CATALOG, null);
            this.mPrimeCache.removeNonPrimeAndNotInLibraryTracks("asin", arrayList2);
        }
        return arrayList;
    }
}
