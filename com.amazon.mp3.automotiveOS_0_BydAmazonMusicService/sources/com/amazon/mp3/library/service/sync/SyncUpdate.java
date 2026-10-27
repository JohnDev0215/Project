package com.amazon.mp3.library.service.sync;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
class SyncUpdate {
    public String mTrackId;
    public JSONObject mTrackMetadata;
    public UpdateType mUpdateType;

    SyncUpdate() {
    }

    public static SyncUpdate inflateFromJSON(JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        SyncUpdate syncUpdate = new SyncUpdate();
        syncUpdate.mTrackId = jsonObject.getString("trackId");
        UpdateType updateTypeValueOf = UpdateType.valueOf(jsonObject.getString("updateType"));
        syncUpdate.mUpdateType = updateTypeValueOf;
        if (updateTypeValueOf != UpdateType.DELETE) {
            syncUpdate.mTrackMetadata = jsonObject.getJSONObject("trackMetadata");
        }
        return syncUpdate;
    }
}
