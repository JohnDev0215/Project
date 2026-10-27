package com.amazon.music.connect.feed;

import android.util.Log;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.connect.feed.FeedItem;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class FeedServiceResponse {
    private static final String TAG = "FeedServiceResponse";
    private final ArrayList<FeedItem> feedItems = new ArrayList<>();
    private final String rawResponse;

    public FeedServiceResponse(String str) throws JSONException {
        this.rawResponse = str;
        interpretRawResponse(str);
    }

    public ArrayList<FeedItem> getFeedItems() {
        return this.feedItems;
    }

    private void interpretRawResponse(String str) throws JSONException {
        JSONObject jSONObject;
        String str2;
        String string;
        FeedItem.EntityType entityType;
        String string2;
        JSONArray jSONArray;
        String string3;
        JSONObject jSONObject2;
        String string4;
        try {
            JSONArray jSONArray2 = new JSONObject(str).getJSONArray("feedItems");
            if (jSONArray2 == null) {
                return;
            }
            for (int i = 0; i < jSONArray2.length(); i++) {
                JSONObject jSONObject3 = jSONArray2.getJSONObject(i);
                int i2 = 1000;
                if (jSONObject3 != null) {
                    string = jSONObject3.getString("baseItemId");
                    String string5 = jSONObject3.getString("id");
                    string2 = jSONObject3.getString("createdAt");
                    int iOptInt = jSONObject3.optInt("feedItemType", 1000);
                    JSONObject jSONObject4 = jSONObject3.getJSONObject("baseItem");
                    if (jSONObject4 != null) {
                        string3 = jSONObject4.getString("imageUrl");
                        JSONObject jSONObject5 = jSONObject4.getJSONObject("entity");
                        try {
                            jSONObject2 = !jSONObject4.isNull("metadata") ? jSONObject4.getJSONObject("metadata") : null;
                        } catch (JSONException e) {
                            Log.e(TAG, "Encountered JSON exception when parsing baseItemMetaData", e);
                            jSONObject2 = null;
                        }
                        try {
                            jSONArray = !jSONObject4.isNull("localizedAttributes") ? jSONObject4.getJSONArray("localizedAttributes") : null;
                        } catch (JSONException e2) {
                            Log.e(TAG, "Encountered JSON exception when parsing localizedAttributes", e2);
                            jSONArray = null;
                        }
                        if (jSONObject5 != null) {
                            string4 = jSONObject5.getString("canonicalId");
                            FeedItem.EntityType entityTypeFromInteger = FeedItem.EntityType.fromInteger(jSONObject5.optInt(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, 1000));
                            try {
                                jSONObject = !jSONObject5.isNull("metadata") ? jSONObject5.getJSONObject("metadata") : null;
                                str2 = string5;
                            } catch (JSONException e3) {
                                Log.e(TAG, "Encountered JSON exception when parsing entityMetaData", e3);
                                str2 = string5;
                                jSONObject = null;
                            }
                            entityType = entityTypeFromInteger;
                            i2 = iOptInt;
                            this.feedItems.add(new FeedItem.Builder(str2, string, i2, string2).withBaseItemMetaData(jSONObject2).withLocalizedAttributes(jSONArray).withEntityCanonicalId(string4).withEntityMetaData(jSONObject).withEntityType(entityType).withImageUrl(string3).build());
                        } else {
                            str2 = string5;
                            i2 = iOptInt;
                            jSONObject = null;
                            entityType = null;
                            string4 = null;
                            this.feedItems.add(new FeedItem.Builder(str2, string, i2, string2).withBaseItemMetaData(jSONObject2).withLocalizedAttributes(jSONArray).withEntityCanonicalId(string4).withEntityMetaData(jSONObject).withEntityType(entityType).withImageUrl(string3).build());
                        }
                    } else {
                        str2 = string5;
                        i2 = iOptInt;
                        jSONObject = null;
                        entityType = null;
                    }
                } else {
                    jSONObject = null;
                    str2 = null;
                    string = null;
                    entityType = null;
                    string2 = null;
                }
                jSONArray = null;
                string3 = null;
                jSONObject2 = null;
                string4 = null;
                this.feedItems.add(new FeedItem.Builder(str2, string, i2, string2).withBaseItemMetaData(jSONObject2).withLocalizedAttributes(jSONArray).withEntityCanonicalId(string4).withEntityMetaData(jSONObject).withEntityType(entityType).withImageUrl(string3).build());
            }
        } catch (JSONException e4) {
            Log.e(TAG, "Encountered JSON exception when parsing feedItems JSON", e4);
        }
    }
}
