package com.amazon.mp3.store.html5.bridge;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class ShowInLibraryBroadcast implements Serializable {
    private final String[] mAddedAsins;
    private final String mCollecionAsin;
    private final CollectionType mCollectionType;

    public ShowInLibraryBroadcast(Message broadcast) {
        try {
            JSONObject options = broadcast.getOptions();
            this.mCollecionAsin = options.optString("collectionAsin");
            this.mCollectionType = CollectionType.getCollectionType(options.optString("collectionType"));
            JSONArray jSONArrayOptJSONArray = options.optJSONArray("addedAsins");
            if (jSONArrayOptJSONArray != null) {
                this.mAddedAsins = new String[jSONArrayOptJSONArray.length()];
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    this.mAddedAsins[i] = jSONArrayOptJSONArray.getString(i);
                }
                return;
            }
            this.mAddedAsins = new String[0];
        } catch (JSONException e) {
            throw new RuntimeException("Unable to parse ShowInLibraryBroadcast broadcast", e);
        }
    }

    public String getCollecionAsin() {
        return this.mCollecionAsin;
    }

    public CollectionType getCollectionType() {
        return this.mCollectionType;
    }
}
