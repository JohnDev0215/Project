package com.amazon.music.inappmessaging.external.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ContextualTriggerAllowList {
    public static String ALBUM_ASIN_KEY = "albumAsin";
    public static String ARTIST_NAME_KEY = "artistName";
    public static String CAMPAIGN_NAME = "campaignId";
    public static String CATEGORY_NAME = "category";
    public static String ITEM_NAME_KEY = "itemName";
    public static String ITEM_URL_KEY = "itemUrl";
    public static String TRACK_ASIN_QUERY_PARAMETER_KEY = "trackAsinQueryParameter";
    public static String URL_ENCODED_ITEM_NAME_KEY = "urlEncodedItemName";
    public static String URL_ENCODED_ITEM_URL_KEY = "urlEncodedItemUrl";
    private static Map<String, List<String>> eligibleContextualTriggerParams;

    static {
        HashMap map = new HashMap();
        eligibleContextualTriggerParams = map;
        map.put(Trigger.INELIGIBLE_CONTENT_REQUEST.getName(), Arrays.asList(ARTIST_NAME_KEY, ITEM_NAME_KEY, ITEM_URL_KEY, URL_ENCODED_ITEM_NAME_KEY, URL_ENCODED_ITEM_URL_KEY));
        eligibleContextualTriggerParams.put(Trigger.STREAM_UPSELL.getName(), Arrays.asList(ALBUM_ASIN_KEY, ITEM_NAME_KEY, ITEM_URL_KEY, TRACK_ASIN_QUERY_PARAMETER_KEY, URL_ENCODED_ITEM_NAME_KEY, URL_ENCODED_ITEM_URL_KEY));
        eligibleContextualTriggerParams.put(Trigger.TRACK_SHUFFLE_PLAYBACK_UPSELL.getName(), Arrays.asList(ITEM_URL_KEY));
        eligibleContextualTriggerParams.put(Trigger.BROWSE_HOME_WIDGET_UPSELL.getName(), Arrays.asList(ITEM_URL_KEY));
    }

    public static Map<String, List<String>> getEligibleContextualTriggerParams() {
        return eligibleContextualTriggerParams;
    }
}
