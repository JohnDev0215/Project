package com.amazon.mp3.prime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.prime.browse.BrowseContentSelectionProvider;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.browse.BrowseContentSelector;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.TrackLookup;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class TrackRequester {
    private static final String TAG = "TrackRequester";
    protected final Context mContext;
    private String trackAsin;

    public TrackRequester(Context context) {
        this.mContext = context;
    }

    public MusicTrack getTrackFromAsin(final String asin) {
        LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
        String str = TAG;
        Log.debug(str, "getTrackFromAsin: %s", asin);
        if (asin == null) {
            Log.error(str, "asin is null");
            return null;
        }
        this.trackAsin = asin;
        if (new CTAPrimeCache(this.mContext).getArtworkUrlForAsin(asin) == null) {
            insertTrackIntoCTAPrimeCache(asin);
        }
        Uri uriFindCollectionUriByAsin = new QuerySongByAsin(this.mContext).findCollectionUriByAsin(this.trackAsin);
        if (uriFindCollectionUriByAsin == null) {
            Log.error(str, "Unable to pull a Track for: " + asin);
            return null;
        }
        MusicTrack track = libraryItemFactory.getTrack(uriFindCollectionUriByAsin);
        if (track != null) {
            return track;
        }
        Log.error(str, "Found an ASIN in our database but couldn't create track: " + asin);
        return null;
    }

    protected int insertTrackIntoCTAPrimeCache(final String asin) {
        PrimeTrack primeTrackFromTrackAsin;
        if (AmazonApplication.getCapabilities().isTrackLookupBrowseContentSelectorAllStreamable()) {
            primeTrackFromTrackAsin = getPrimeTrackFromTrackAsinWithoutRestrictions(asin);
        } else {
            primeTrackFromTrackAsin = getPrimeTrackFromTrackAsin(asin);
        }
        if (primeTrackFromTrackAsin == null) {
            Log.error(TAG, "Failed to create PrimeTrack with ASIN: " + asin);
            return 0;
        }
        this.trackAsin = primeTrackFromTrackAsin.getAsin();
        try {
            int iInsertPrimeTracks = new CTAPrimeCache(this.mContext).insertPrimeTracks(primeTrackFromTrackAsin.getAlbumAsin(), primeTrackFromTrackAsin.getAlbum(), primeTrackFromTrackAsin.getAlbumArtist(), Collections.singletonList(primeTrackFromTrackAsin));
            if (iInsertPrimeTracks == 0) {
                Log.error(TAG, "Failed to insert a track to a cache db: album asin: %s, album: %s, artist: %s", primeTrackFromTrackAsin.getAlbumAsin(), primeTrackFromTrackAsin.getAlbum(), primeTrackFromTrackAsin.getAlbumArtist());
            }
            return iInsertPrimeTracks;
        } catch (SQLiteDatabaseLockedException e) {
            Log.error(TAG, "Failed to insert a track to a cache db: album asin: %s, album: %s, artist: %s due to SQLiteDatabaseLockedException with message: " + e.getMessage(), primeTrackFromTrackAsin.getAlbumAsin(), primeTrackFromTrackAsin.getAlbum(), primeTrackFromTrackAsin.getAlbumArtist());
            return 0;
        }
    }

    private PrimeTrack getPrimeTrackFromTrackAsinWithoutRestrictions(final String trackAsin) {
        try {
            List<TrackItem> list = new TrackLookup(BrowseFactory.createBrowseService(this.mContext)).get(new TrackLookup.Request(trackAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), BrowseContentSelector.ALL_STREAMABLE, AccountCredentialStorage.get(this.mContext).getDeviceId(), AccountCredentialStorage.get(this.mContext).getDeviceType(), new BrowseLanguageProvider().getLocale(this.mContext), AmazonApplication.getCapabilities().isVideosEnabled(), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.mContext))).tracks;
            TrackItem trackItem = list != null ? list.get(0) : null;
            if (trackItem == null) {
                return null;
            }
            return PrimeItemsTransformationUtil.toPrimeTrack(trackItem);
        } catch (BrowseException e) {
            Log.error(TAG, "Failed to lookup track (without restrictions) with ASIN: " + trackAsin, e);
            return null;
        }
    }

    protected PrimeTrack getPrimeTrackFromTrackAsin(final String trackAsin) {
        try {
            List<TrackItem> list = new TrackLookup(BrowseFactory.createBrowseService(this.mContext)).get(new TrackLookup.Request(trackAsin, AccountDetailUtil.getMusicTerritoryOfResidence(), new BrowseContentSelectionProvider().getBrowseContentSelection(this.mContext), AccountCredentialStorage.get(this.mContext).getDeviceId(), AccountCredentialStorage.get(this.mContext).getDeviceType(), new BrowseLanguageProvider().getLocale(this.mContext), AmazonApplication.getCapabilities().isVideosEnabled(), AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.mContext))).tracks;
            TrackItem trackItem = list != null ? list.get(0) : null;
            if (trackItem == null) {
                return null;
            }
            return PrimeItemsTransformationUtil.toPrimeTrack(trackItem);
        } catch (BrowseException e) {
            Log.error(TAG, "Failed to lookup track with ASIN: " + trackAsin, e);
            return null;
        }
    }

    @Nullable
    public List<TrackItem> getTracksFromAsins(final List<String> trackAsins, final boolean requestAudioVideo) {
        ArrayList arrayList = new ArrayList();
        for (String str : trackAsins) {
            if (PrimePlaylistUtil.isAsin(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            Log.error(TAG, "getTrackFromTrackAsins: no valid ASINs=" + trackAsins);
            return null;
        }
        try {
            String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
            Locale locale = new BrowseLanguageProvider().getLocale(this.mContext);
            AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(this.mContext);
            return new TrackLookup(BrowseFactory.createBrowseService(this.mContext)).get(new TrackLookup.Request(arrayList, musicTerritoryOfResidence, BrowseContentSelector.ALL_STREAMABLE, accountCredentialStorage.getDeviceId(), accountCredentialStorage.getDeviceType(), locale, AmazonApplication.getCapabilities().isVideosEnabled(), requestAudioVideo, AmazonApplication.getCapabilities().isExplicitLanguageFilterEnabled(), ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(this.mContext))).tracks;
        } catch (BrowseException e) {
            Log.error(TAG, "getTracksFromAsins: Failed to lookup track with ASINs=" + trackAsins, e);
            return null;
        }
    }
}
