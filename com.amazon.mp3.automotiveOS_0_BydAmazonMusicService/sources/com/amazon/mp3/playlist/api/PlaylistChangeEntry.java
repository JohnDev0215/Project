package com.amazon.mp3.playlist.api;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistChangeEntry {
    private String mPlaylistId;
    private PlaylistChangeType mType;
    private String mVersion;

    public enum PlaylistChangeType {
        UPDATED,
        REMOVED
    }

    public String getPlaylistId() {
        return this.mPlaylistId;
    }

    public void setPlaylistId(String playlistId) {
        this.mPlaylistId = playlistId;
    }

    public String getVersion() {
        return this.mVersion;
    }

    public void setVersion(String version) {
        this.mVersion = version;
    }

    public PlaylistChangeType getType() {
        return this.mType;
    }

    public void setType(PlaylistChangeType type) {
        this.mType = type;
    }

    public static PlaylistChangeEntry fromJSON(JSONObject object) throws JSONException {
        PlaylistChangeEntry playlistChangeEntry = new PlaylistChangeEntry();
        playlistChangeEntry.setPlaylistId(object.getString("playlistId"));
        playlistChangeEntry.setType(PlaylistChangeType.valueOf(object.getString("changeType")));
        playlistChangeEntry.setVersion(object.optString("version"));
        return playlistChangeEntry;
    }
}
