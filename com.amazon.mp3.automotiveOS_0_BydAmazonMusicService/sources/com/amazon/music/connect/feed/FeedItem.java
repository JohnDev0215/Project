package com.amazon.music.connect.feed;

import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazonaws.util.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringJoiner;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class FeedItem {
    private static final String TAG = "FeedItem";
    public String baseItemId;
    public JSONObject baseItemMetaData;
    public String creationDate;
    public String entityCanonicalId;
    public String entityDescription;
    public JSONObject entityMetaData;
    public String entityName;
    public EntityType entityType;
    public String id;
    public String imageUrl;
    public String linkUrl;
    public JSONArray localizedAttributes;
    public int score;
    public int scoreVersion;
    public String tag;
    public String timeAgo;
    public String title;
    public Type type;
    public int typeId;
    public boolean viewed;

    private FeedItem() {
        this.viewed = false;
    }

    public enum Type {
        ARTIST_NEW_RELEASE,
        FRIEND_FOLLOW,
        FOLLOWED_CUSTOMER_PLAYLIST_UPDATE,
        FULL_CATALOG_NEW_PLAYLIST_NOTIFICATION,
        OTHER;

        public static Type fromInteger(int i) {
            if (i == 1) {
                return ARTIST_NEW_RELEASE;
            }
            if (i == 2) {
                return FRIEND_FOLLOW;
            }
            if (i == 13) {
                return FOLLOWED_CUSTOMER_PLAYLIST_UPDATE;
            }
            if (i == 15) {
                return FULL_CATALOG_NEW_PLAYLIST_NOTIFICATION;
            }
            return OTHER;
        }
    }

    public enum EntityType {
        ARTIST,
        CUSTOMER,
        OTHER;

        public static EntityType fromInteger(int i) {
            if (i == 1) {
                return ARTIST;
            }
            if (i == 2) {
                return CUSTOMER;
            }
            return OTHER;
        }
    }

    public static class Builder {
        private String baseItemId;
        private JSONObject baseItemMetaData;
        private String creationDate;
        private String entityCanonicalId;
        private String entityDescription;
        private JSONObject entityMetaData;
        private String entityName;
        private EntityType entityType;
        private String id;
        private String imageUrl;
        private JSONArray localizedAttributes;
        private int score;
        private int scoreVersion;
        private Type type;
        private int typeId;

        public Builder(String str, String str2, int i, String str3) {
            this.baseItemId = str2;
            this.creationDate = str3;
            this.id = str;
            this.type = Type.fromInteger(i);
            this.typeId = i;
        }

        public Builder withBaseItemMetaData(JSONObject jSONObject) {
            this.baseItemMetaData = jSONObject;
            return this;
        }

        public Builder withLocalizedAttributes(JSONArray jSONArray) {
            this.localizedAttributes = jSONArray;
            return this;
        }

        public Builder withEntityCanonicalId(String str) {
            this.entityCanonicalId = str;
            return this;
        }

        public Builder withEntityMetaData(JSONObject jSONObject) {
            this.entityMetaData = jSONObject;
            return this;
        }

        public Builder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public Builder withImageUrl(String str) {
            this.imageUrl = str;
            return this;
        }

        private void parseGenericMetadata(FeedItem feedItem) {
            JSONObject jSONObject = feedItem.baseItemMetaData;
            if (jSONObject == null) {
                return;
            }
            feedItem.linkUrl = getMetadataAttribute(jSONObject, "linkUrl", null);
            feedItem.tag = getMetadataAttribute(feedItem.baseItemMetaData, ParserUtil.TAG_QUERY_PARAM_NAME, null);
            feedItem.title = getMetadataAttribute(feedItem.baseItemMetaData, "title", null);
        }

        private void parseLocalizedAttributes(FeedItem feedItem) {
            JSONArray jSONArray = feedItem.localizedAttributes;
            if (jSONArray == null || jSONArray.length() == 0) {
                return;
            }
            try {
                JSONObject jSONObject = feedItem.localizedAttributes.getJSONObject(0);
                feedItem.linkUrl = getMetadataAttribute(jSONObject, "linkUrl", feedItem.linkUrl);
                feedItem.tag = getMetadataAttribute(jSONObject, ParserUtil.TAG_QUERY_PARAM_NAME, feedItem.tag);
                feedItem.title = getMetadataAttribute(jSONObject, "title", feedItem.title);
                feedItem.imageUrl = getMetadataAttribute(jSONObject, "imageUrl", feedItem.imageUrl);
            } catch (Exception e) {
                Log.e(FeedItem.TAG, "Encountered JSON exception when parsing localizedAttributes", e);
            }
        }

        private String getMetadataAttribute(JSONObject jSONObject, String str, String str2) {
            String strOptString = jSONObject.optString(str, str2);
            return (strOptString == null || !strOptString.toLowerCase().equals("null")) ? strOptString : str2;
        }

        public FeedItem build() {
            FeedItem feedItem = new FeedItem();
            feedItem.baseItemId = this.baseItemId;
            feedItem.baseItemMetaData = this.baseItemMetaData;
            String str = this.creationDate;
            feedItem.creationDate = str;
            feedItem.entityCanonicalId = this.entityCanonicalId;
            feedItem.entityDescription = this.entityDescription;
            feedItem.entityMetaData = this.entityMetaData;
            feedItem.entityName = this.entityName;
            feedItem.entityType = this.entityType;
            feedItem.localizedAttributes = this.localizedAttributes;
            feedItem.id = this.id;
            feedItem.imageUrl = this.imageUrl;
            feedItem.score = this.score;
            feedItem.scoreVersion = this.scoreVersion;
            feedItem.timeAgo = FeedItem.getTimeAgoString(str);
            feedItem.type = this.type;
            feedItem.typeId = this.typeId;
            parseGenericMetadata(feedItem);
            parseLocalizedAttributes(feedItem);
            return feedItem;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getTimeAgoString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            String string = android.text.format.DateUtils.getRelativeTimeSpanString(simpleDateFormat.parse(str).getTime(), System.currentTimeMillis(), 60000L).toString();
            return !string.toLowerCase().equals("0 minutes ago") ? string.toLowerCase().equals("in 0 minutes") ? "Moments Ago" : string : "Moments Ago";
        } catch (ParseException unused) {
            return null;
        }
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("this.id: " + this.id).add("this.creationDate: " + this.creationDate).add("this.imageUrl: " + this.imageUrl).add("this.entityCanonicalId: " + this.entityCanonicalId).add("this.entityDescription: " + this.entityDescription).add("this.entityName: " + this.entityName).add("this.entityType: " + this.entityType).add("this.scoreVersion: " + this.scoreVersion).add("this.score: " + this.score).add("this.timeAgo: " + this.timeAgo);
        if (this.baseItemMetaData != null) {
            stringJoiner.add("this.baseItemMetaData: " + this.baseItemMetaData.toString());
        } else {
            stringJoiner.add("this.baseItemMetaData: null");
        }
        if (this.entityMetaData != null) {
            stringJoiner.add("this.entityMetaData: " + this.entityMetaData.toString());
        } else {
            stringJoiner.add("this.entityMetaData: null");
        }
        if (this.localizedAttributes != null) {
            stringJoiner.add("this.localizedAttributes: " + this.localizedAttributes.toString());
        } else {
            stringJoiner.add("this.localizedAttributes: null");
        }
        return stringJoiner.toString();
    }
}
