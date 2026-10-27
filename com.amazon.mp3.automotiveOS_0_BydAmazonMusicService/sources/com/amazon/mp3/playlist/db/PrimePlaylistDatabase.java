package com.amazon.mp3.playlist.db;

import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.playlist.CatalogPlaylistTrack;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public interface PrimePlaylistDatabase {
    boolean cacheContainsPrimePlaylist(final String asin);

    void delete(long playlistId);

    void follow(String asin, String luid);

    CatalogPlaylist getPlaylist(String asin, int source);

    CatalogPlaylist getPlaylistByLuid(String luid, int sourceId);

    long getPlaylistIdByCloudId(String luid);

    CatalogPlaylistTrack getTrackByArtworkId(String artworkId, int source);

    void markForExpiry(String asin);

    void save(CatalogPlaylist playlist, int source, boolean updateTracks);

    void save(JSONObject playlist, int source, boolean updateTracks, boolean followed) throws JSONException;

    void setNew(final CatalogPlaylist catalogPlaylist, boolean isNew);
}
