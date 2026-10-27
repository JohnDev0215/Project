package com.amazon.mp3.playlist.api.track;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class PlaylistServiceTrack {
    private String mId;

    protected abstract String getIdName();

    protected abstract String getTypeName();

    protected PlaylistServiceTrack(String id) {
        this.mId = id;
    }

    public JSONObject getJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(getIdName(), this.mId);
        jSONObject.put("__type", getTypeName());
        return jSONObject;
    }

    public String getId() {
        return this.mId;
    }
}
