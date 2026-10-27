package com.amazon.mp3.cache;

import android.net.Uri;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Artist;
import com.amazon.musicensembleservice.brush.Track;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushAlbumCacheHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bJ\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00042\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u0016\u0010\u0011\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/cache/BrushAlbumCacheHelper;", "", "()V", "buildCacheFromBrushAlbum", "Lcom/amazon/mp3/cache/AlbumCache;", "album", "Lcom/amazon/musicensembleservice/brush/Album;", "checkIfCachedAlbumIsFullyAdded", "", "albumCache", "trackCount", "", "updateCacheWithBrushTrackUris", "", "tracks", "", "Lcom/amazon/musicensembleservice/brush/Track;", "updateCacheWithLibraryAlbum", "libraryAlbum", "Lcom/amazon/mp3/library/item/Album;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushAlbumCacheHelper {
    public static final BrushAlbumCacheHelper INSTANCE = new BrushAlbumCacheHelper();

    private BrushAlbumCacheHelper() {
    }

    public final AlbumCache buildCacheFromBrushAlbum(Album album) {
        Intrinsics.checkNotNullParameter(album, "album");
        ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
        List<Artist> artists = album.getArtists();
        Intrinsics.checkNotNullExpressionValue(artists, "album.artists");
        Artist artist = (Artist) CollectionsKt___CollectionsKt.firstOrNull((List) artists);
        String name = artist == null ? null : artist.getName();
        String title = album.getTitle();
        String asin = album.getAsin();
        Intrinsics.checkNotNullExpressionValue(asin, "album.asin");
        return new AlbumCache(contentPlaybackUtils.getAlbumUri(name, title, asin));
    }

    public final void updateCacheWithLibraryAlbum(AlbumCache albumCache, com.amazon.mp3.library.item.Album libraryAlbum) {
        Intrinsics.checkNotNullParameter(albumCache, "albumCache");
        Intrinsics.checkNotNullParameter(libraryAlbum, "libraryAlbum");
        albumCache.cacheCollectionObject(libraryAlbum);
        Collection<MusicTrack> tracks = libraryAlbum.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "libraryAlbum.tracks");
        albumCache.cacheChildrenItems(tracks);
    }

    public final boolean checkIfCachedAlbumIsFullyAdded(AlbumCache albumCache, int trackCount) {
        Intrinsics.checkNotNullParameter(albumCache, "albumCache");
        return albumCache.getChildrenItemCount() >= trackCount;
    }

    public final void updateCacheWithBrushTrackUris(AlbumCache albumCache, Collection<? extends Track> tracks) {
        Intrinsics.checkNotNullParameter(albumCache, "albumCache");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        for (Track track : tracks) {
            String asin = track.getAsin();
            Intrinsics.checkNotNullExpressionValue(asin, "track.asin");
            if (albumCache.getChildUri(asin) == null) {
                ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
                String asin2 = track.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin2, "track.asin");
                Uri trackUri = contentPlaybackUtils.getTrackUri(asin2);
                String asin3 = track.getAsin();
                Intrinsics.checkNotNullExpressionValue(asin3, "track.asin");
                albumCache.cacheChildUri(asin3, trackUri);
            }
        }
    }
}
