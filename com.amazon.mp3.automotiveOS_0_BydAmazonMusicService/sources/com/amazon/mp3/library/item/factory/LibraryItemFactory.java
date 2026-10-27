package com.amazon.mp3.library.item.factory;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.Artist;
import com.amazon.mp3.library.item.Genre;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import java.io.Closeable;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public interface LibraryItemFactory {

    public interface CursorIterator<T> extends Iterator<T>, Iterable<T>, Closeable {
    }

    Album getAlbum(Cursor cursor);

    Album getAlbum(Uri contentUri);

    Album getAlbumForTrack(MusicTrack track);

    String[] getAlbumProjection();

    String[] getAlbumTrackSortProjection();

    Artist getArtist(Cursor cursor);

    Artist getArtist(@Nullable Uri contentUri);

    Artist getArtistForAlbum(Album album);

    CatalogPlaylist getCatalogPlaylist(Uri contentUri);

    AbstractItem getCollectionItem(Uri contentUri);

    Genre getGenre(Uri contentUri);

    String[] getGenreProjection();

    AbstractItem getItem(Uri contentUri);

    Playlist getPlaylist(Cursor playlistCursor, String source);

    Playlist getPlaylist(Uri contentUri);

    CatalogPlaylist getPrimePlaylist(Uri contentUri);

    @Nullable
    Cursor getPrimePlaylistTracksCursor(final Uri[] contentUris, Context context);

    CatalogPlaylist getSharedUserPlaylist(Uri contentUri);

    MusicTrack getTrack(Cursor cursor);

    MusicTrack getTrack(Uri contentUri);

    MusicTrack getTrack(String source, long matchHash);

    @Nullable
    MusicTrack getTrackForAsin(String asin);

    MusicTrack getTrackIncludingPrimePlaylistTrack(Uri contentUri);

    String[] getTrackProjection();

    CursorIterator<MusicTrack> getTracks(String source, Collection<String> luids);

    Collection<MusicTrack> getTracksForAlbum(Album album);

    Collection<MusicTrack> getTracksForAlbum(Album album, String sortOrder);

    Collection<MusicTrack> getTracksForPlaylist(Playlist playlist);

    Collection<CatalogPlaylistTrack> getTracksForPrimePlaylist(CatalogPlaylist playlist);

    Uri getUriForAsin(String asin);

    Album populateAlbum(Cursor cursor, Album album);

    Artist populateArtist(Cursor cursor, Artist artist);

    Genre populateGenre(Cursor genreCursor, Genre genre);

    Playlist populatePlaylist(Cursor cursor, Playlist playlist, String source);

    MusicTrack populateTrack(Cursor cursor, MusicTrack track);

    void updateCounts(Album album);

    void updateCounts(Artist artist);

    void updateCounts(Genre genre);

    AbstractItem updateOptionalInfo(AbstractItem item);
}
