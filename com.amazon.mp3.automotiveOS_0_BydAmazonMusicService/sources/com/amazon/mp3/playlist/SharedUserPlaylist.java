package com.amazon.mp3.playlist;

import com.amazon.mp3.environment.Environment;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class SharedUserPlaylist extends CatalogPlaylist {
    public static CatalogPlaylist fromJSON(JSONObject object) throws JSONException {
        CatalogPlaylist catalogPlaylistFromJSON = fromJSON(null, null, object);
        catalogPlaylistFromJSON.setAsin(catalogPlaylistFromJSON.getLuid());
        return catalogPlaylistFromJSON;
    }

    public static CatalogPlaylist fromJSON(String asin, String marketplace, JSONObject object) throws JSONException {
        CatalogPlaylist catalogPlaylistFromJSON = CatalogPlaylist.fromJSON(asin, marketplace, object);
        catalogPlaylistFromJSON.setIsSharedUserPlaylist(true);
        return catalogPlaylistFromJSON;
    }

    public static boolean isSharedUserPlaylist(JSONObject playlist) throws JSONException {
        if (playlist.has(Environment.PLAYLIST_PATH)) {
            playlist = playlist.getJSONObject(Environment.PLAYLIST_PATH);
        }
        return !playlist.getJSONObject("metadata").has("asin");
    }
}
