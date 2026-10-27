package com.amazon.mp3.library.service.sync;

import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class IncrementalSyncResponse {
    public String mCheckpoint;
    public ArrayList<SyncUpdate> mUpdateList = new ArrayList<>();

    public static IncrementalSyncResponse inflateFromJSON(JSONObject jsonObject) throws JSONException, ParseException {
        if (jsonObject == null) {
            return null;
        }
        IncrementalSyncResponse incrementalSyncResponse = new IncrementalSyncResponse();
        incrementalSyncResponse.mCheckpoint = jsonObject.getString("checkpoint");
        if (!jsonObject.isNull("updateList")) {
            JSONArray jSONArray = jsonObject.getJSONArray("updateList");
            for (int i = 0; i < jSONArray.length(); i++) {
                incrementalSyncResponse.mUpdateList.add(SyncUpdate.inflateFromJSON(jSONArray.getJSONObject(i)));
            }
        }
        return incrementalSyncResponse;
    }
}
