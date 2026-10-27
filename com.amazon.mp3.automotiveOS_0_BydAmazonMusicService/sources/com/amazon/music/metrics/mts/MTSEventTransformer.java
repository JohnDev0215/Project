package com.amazon.music.metrics.mts;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.file.FileItemTransformer;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSEventTransformer implements FileItemTransformer<MTSEvent, JSONException> {
    private static final Logger LOG = LoggerFactory.getLogger(MTSEventTransformer.class.getSimpleName());
    private MTSEventDataProvider mDataProvider;

    public MTSEventTransformer(MTSEventDataProvider mTSEventDataProvider) {
        if (mTSEventDataProvider == null) {
            throw new IllegalArgumentException("Data provider cannot be null!");
        }
        this.mDataProvider = mTSEventDataProvider;
    }

    @Override // com.amazon.music.file.FileItemTransformer
    public String transform(MTSEvent mTSEvent) throws JSONException {
        String savableString = getSavableString(mTSEvent, this.mDataProvider);
        LOG.trace("MTSEvent serialized to: {}", savableString);
        return savableString;
    }

    public MTSEvent transform(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        String string = jSONObject.getString("actionName");
        long j = jSONObject.getLong(TetheredMessageKey.timestamp);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("additionalDetails");
        Map<String, String> attributes = jSONObjectOptJSONObject != null ? parseAttributes(jSONObjectOptJSONObject) : null;
        MTSEvent mTSEvent = new MTSEvent(string);
        if (attributes != null) {
            mTSEvent.addAttributes(attributes);
        }
        mTSEvent.setTimestamp(j);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("metricsContext");
        if (jSONObjectOptJSONObject2 != null) {
            mTSEvent.setMetricsContext(parseAttributes(jSONObjectOptJSONObject2));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(ContextMappingConstants.CONTENT_INFO);
        if (jSONArrayOptJSONArray != null) {
            mTSEvent.addContentInfoAttributes(parseContentInfo(jSONArrayOptJSONArray));
        }
        return mTSEvent;
    }

    private List<Map<String, String>> parseContentInfo(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(parseAttributes(jSONArray.getJSONObject(i)));
            } catch (JSONException e) {
                Logger logger = LOG;
                logger.error("Unable to parse mapping for index {}", Integer.valueOf(i));
                logger.trace("Unable to parse mapping", (Throwable) e);
            }
        }
        return arrayList;
    }

    private Map<String, String> parseAttributes(JSONObject jSONObject) {
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                map.put(next, jSONObject.getString(next));
            } catch (JSONException e) {
                Logger logger = LOG;
                logger.error("Unable to parse mapping for {}", next);
                logger.trace("Unable to parse mapping", (Throwable) e);
            }
        }
        return map;
    }

    private String getSavableString(MTSEvent mTSEvent, MTSEventDataProvider mTSEventDataProvider) throws JSONException {
        mTSEvent.addData(mTSEventDataProvider);
        JSONObject jSONObjectMapToJSON = mapToJSON(mTSEvent.getEventAttributes());
        JSONArray jSONArray = new JSONArray();
        Iterator<Map<String, String>> it = mTSEvent.getContentInfoAttributes().iterator();
        while (it.hasNext()) {
            jSONArray.put(new JSONObject((Map<?, ?>) it.next()));
        }
        return getSavableJSON(mTSEvent, jSONObjectMapToJSON, jSONArray).toString();
    }

    private static JSONObject getSavableJSON(MTSEvent mTSEvent, JSONObject jSONObject, JSONArray jSONArray) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("actionName", mTSEvent.getEventName());
        jSONObject2.put("additionalDetails", jSONObject);
        jSONObject2.put(ContextMappingConstants.CONTENT_INFO, jSONArray);
        jSONObject2.put(TetheredMessageKey.timestamp, String.valueOf(mTSEvent.getTimestamp()));
        Map<String, String> metricsContext = mTSEvent.getMetricsContext();
        if (metricsContext != null && metricsContext.size() > 0) {
            jSONObject2.put("metricsContext", mapToJSON(metricsContext));
        }
        return jSONObject2;
    }

    private static JSONObject mapToJSON(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        return jSONObject;
    }
}
