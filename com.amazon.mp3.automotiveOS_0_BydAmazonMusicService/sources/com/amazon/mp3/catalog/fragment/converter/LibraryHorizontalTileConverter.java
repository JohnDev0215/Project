package com.amazon.mp3.catalog.fragment.converter;

import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.HorizontalTileModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryHorizontalTileConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J$\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\n2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fH\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0013H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u0002H\u0016¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/LibraryHorizontalTileConverter;", "", "Lcom/amazon/mp3/library/item/AbstractItem;", "Lcom/amazon/mp3/library/item/Album;", "album", "Lcom/amazon/music/views/library/models/HorizontalTileModel;", "convertAlbum", "Lcom/amazon/mp3/library/item/Artist;", "artist", "convertArtist", "Lcom/amazon/mp3/library/item/MusicTrack;", "track", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadataList", "convertTrack", "Lcom/amazon/mp3/library/item/Playlist;", Environment.PLAYLIST_PATH, "convertPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "convertCatalogPlaylist", "item", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LibraryHorizontalTileConverter implements SingleBaseModelConverter {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public HorizontalTileModel convert(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof Album) {
            return convertAlbum((Album) item);
        }
        if (item instanceof Artist) {
            return convertArtist((Artist) item);
        }
        if (item instanceof MusicTrack) {
            return convertTrack$default(this, (MusicTrack) item, null, 2, null);
        }
        if (item instanceof Playlist) {
            return convertPlaylist((Playlist) item);
        }
        if (item instanceof CatalogPlaylist) {
            return convertCatalogPlaylist((CatalogPlaylist) item);
        }
        return null;
    }

    private final HorizontalTileModel convertAlbum(Album album) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(album, null, 2, null);
        if (contentMetadataConvert$default == null) {
            return null;
        }
        return new HorizontalTileModel(null, null, album.getTitle(), album.getArtistName(), null, LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, album, contentMetadataConvert$default, null, 4, null), null, false, null, null, false, album.isInLibrary(), ContentEncodingDecoder.parseFrom(album.getContentEncodings()), false, false, null, 0, contentMetadataConvert$default, null, false, false, null, false, null, null, null, false, false, false, null, 1073539027, null);
    }

    private final HorizontalTileModel convertArtist(Artist artist) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(artist, null, 2, null);
        if (contentMetadataConvert$default == null) {
            return null;
        }
        return new HorizontalTileModel(null, null, artist.getName(), null, null, LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, artist, contentMetadataConvert$default, null, 4, null), null, false, null, null, false, artist.isInLibrary(), null, false, false, null, 4, contentMetadataConvert$default, null, false, false, null, false, null, null, null, false, false, false, null, 1073543131, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ HorizontalTileModel convertTrack$default(LibraryHorizontalTileConverter libraryHorizontalTileConverter, MusicTrack musicTrack, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        return libraryHorizontalTileConverter.convertTrack(musicTrack, list);
    }

    private final HorizontalTileModel convertTrack(MusicTrack track, List<? extends ContentMetadata> metadataList) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(track, null, 2, null);
        if (contentMetadataConvert$default == null) {
            return null;
        }
        return new HorizontalTileModel(null, null, track.getTitle(), track.getArtistName(), null, LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, track, contentMetadataConvert$default, null, 4, null), null, false, null, null, false, track.isInLibrary(), ContentEncodingDecoder.parseFrom(track.getContentEncodings()), false, false, null, 2, contentMetadataConvert$default, metadataList, false, false, null, false, null, null, null, false, false, false, null, 1073276883, null);
    }

    private final HorizontalTileModel convertPlaylist(Playlist playlist) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(playlist, null, 2, null);
        if (contentMetadataConvert$default == null) {
            return null;
        }
        return new HorizontalTileModel(null, null, playlist.getName(), String.valueOf(playlist.getTrackCount()), null, LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, playlist, contentMetadataConvert$default, null, 4, null), null, false, null, null, false, playlist.isInLibrary(), null, false, false, null, 1, contentMetadataConvert$default, null, false, false, null, false, null, null, null, false, false, false, null, 1073543123, null);
    }

    private final HorizontalTileModel convertCatalogPlaylist(CatalogPlaylist playlist) {
        ContentMetadata contentMetadataConvert$default = LibraryContentMetadataConverter.convert$default(playlist, null, 2, null);
        if (contentMetadataConvert$default == null) {
            return null;
        }
        return new HorizontalTileModel(null, null, playlist.getTitle(), String.valueOf(playlist.getTrackCount()), null, LibraryArtworkFrameConverter.convert$default(LibraryArtworkFrameConverter.INSTANCE, playlist, contentMetadataConvert$default, null, 4, null), null, false, null, null, false, playlist.isInLibrary(), null, false, false, null, 1, contentMetadataConvert$default, null, false, false, null, false, null, null, null, false, false, false, null, 1073543123, null);
    }
}
