package com.amazon.mp3.library.fragment;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.TrackType;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.PrimeContentUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeBrowseSongsPlaybackLoader extends AsyncTaskLoader<List<MusicTrack>> {
    private final SongsCollection mSongsCollection;

    public interface SongsCollection {
        int getCount();

        PrimeTrack getItem(int position);
    }

    public PrimeBrowseSongsPlaybackLoader(final Context context, final SongsCollection songsCollection) {
        super(context);
        this.mSongsCollection = songsCollection;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public List<MusicTrack> loadInBackground() {
        int count = this.mSongsCollection.getCount();
        String homeMarketPlaceId = AccountDetailUtil.get(getContext()).getHomeMarketPlaceId();
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap(50);
        int i = 0;
        while (i < count) {
            PrimeTrack item = this.mSongsCollection.getItem(i);
            String asin = item.getAsin();
            int iHashCode = (asin.hashCode() * 31) + homeMarketPlaceId.hashCode();
            MusicTrack musicTrack = new MusicTrack();
            musicTrack.setTitle(item.getTitle());
            musicTrack.setArtistName(item.getArtist());
            musicTrack.setAlbumArtistName(item.getAlbumArtist());
            musicTrack.setArtistAsin(item.getArtistAsin());
            musicTrack.setAlbumName(item.getAlbum());
            musicTrack.setAlbumAsin(item.getAlbumAsin());
            musicTrack.setContentUri(MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "primebrowse", asin));
            musicTrack.setAsin(asin);
            musicTrack.setMarketplace(homeMarketPlaceId);
            musicTrack.setMatchHash(iHashCode);
            musicTrack.setAlbumArtUrl(item.getArtworkUri());
            musicTrack.setOwnershipStatus(ContentOwnershipStatus.NOT_IN_LIBRARY);
            musicTrack.setContentCatalogStatus(item.getCatalogStatus());
            musicTrack.setAssetQualities(item.getAssetQualities());
            musicTrack.setAssetType(item.getAssetType());
            musicTrack.setIsExplicit(item.getIsExplicit());
            musicTrack.setContentEncodings(item.getContentEncoding());
            if (item.getDuration() != null) {
                musicTrack.setDuration(TimeUnit.SECONDS.convert(item.getDuration().intValue(), TimeUnit.MILLISECONDS));
            }
            if (item.getArtworkId() != null) {
                musicTrack.setAlbumId(item.getArtworkId().longValue());
                musicTrack.setTrackType(TrackType.LIBRARY);
            } else {
                musicTrack.setTrackType(TrackType.PRIME_BROWSE);
            }
            musicTrack.setCatalogStatusTiers(item.getCatalogStatusTiers());
            musicTrack.setPlaymodeEligibility(item.getPlaymodeEligibility());
            arrayList.add(musicTrack);
            map.put(musicTrack.getAsin(), musicTrack);
            i++;
            if (i % 50 == 0 || i == count) {
                PrimeContentUtil.loadLibraryInfoForTracksMap(getContext(), map);
                map.clear();
            }
        }
        return arrayList;
    }

    @Override // androidx.loader.content.Loader
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
