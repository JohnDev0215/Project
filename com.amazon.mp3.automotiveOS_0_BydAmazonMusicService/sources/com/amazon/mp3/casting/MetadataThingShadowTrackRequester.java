package com.amazon.mp3.casting;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.factory.LibraryItemFactory;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.util.Log;
import com.amazon.music.casting.session.things.MetadataThingShadow;

/* JADX INFO: loaded from: classes3.dex */
public class MetadataThingShadowTrackRequester extends TrackRequester {
    private static final String TAG = "MetadataThingShadowTrackRequester";
    private final MetadataThingShadow.Track mMetadataThingShadowTrack;

    public MetadataThingShadowTrackRequester(final Context context, final MetadataThingShadow.Track metadataThingShadowTrack) {
        super(context);
        this.mMetadataThingShadowTrack = metadataThingShadowTrack;
    }

    @Override // com.amazon.mp3.prime.TrackRequester
    public MusicTrack getTrackFromAsin(final String asin) {
        LibraryItemFactory libraryItemFactory = AmazonApplication.getLibraryItemFactory();
        if (asin == null) {
            Log.error(TAG, "asin is null");
            return null;
        }
        Uri uriFindCollectionUriByAsin = new QuerySongByAsin(this.mContext).findCollectionUriByAsin(asin);
        if (uriFindCollectionUriByAsin == null) {
            insertTrackIntoCTAPrimeCache(asin);
            uriFindCollectionUriByAsin = new QuerySongByAsin(this.mContext).findCollectionUriByAsin(asin);
            if (uriFindCollectionUriByAsin == null) {
                Log.error(TAG, "Unable to pull a Track for: " + asin);
                return null;
            }
        }
        MusicTrack track = libraryItemFactory.getTrack(uriFindCollectionUriByAsin);
        if (track != null) {
            return track;
        }
        Log.error(TAG, "Found an ASIN in our database but couldn't create track: " + asin);
        return null;
    }

    @Override // com.amazon.mp3.prime.TrackRequester
    protected PrimeTrack getPrimeTrackFromTrackAsin(final String trackAsin) {
        return PrimeItemsTransformationUtil.toPrimeTrack(this.mMetadataThingShadowTrack);
    }
}
