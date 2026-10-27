package com.amazon.avod.content.urlvending;

import com.amazon.avod.media.downloadservice.DownloadStatistics;
import com.amazon.avod.util.CastUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nonnull;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class LiveOriginStatusManager {
    private AtomicReference<JSONObject> mOriginStatusJSONObject = new AtomicReference<>();

    public void updateOriginStatus(@Nonnull DownloadStatistics downloadStatistics) {
        Preconditions.checkNotNull(downloadStatistics, "downloadStatistics");
        JSONObject jSONObjectExtractOriginMap = extractOriginMap(downloadStatistics);
        if (jSONObjectExtractOriginMap != null) {
            this.mOriginStatusJSONObject.getAndSet(jSONObjectExtractOriginMap);
        }
    }

    @Nonnull
    public List<String> getBlacklistedOrigins() {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        JSONObject jSONObject = this.mOriginStatusJSONObject.get();
        if (jSONObject == null) {
            return arrayListNewArrayList;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                JSONObject jSONObject2 = (JSONObject) CastUtils.castTo(jSONObject.get(next), JSONObject.class);
                if (jSONObject2 != null && "down".equalsIgnoreCase(jSONObject2.optString("currentStatus"))) {
                    arrayListNewArrayList.add(next);
                }
            } catch (JSONException e) {
                DLog.exceptionf(e, "Threw JSONException while trying to parse origin status: " + jSONObject, new Object[0]);
            }
        }
        return arrayListNewArrayList;
    }

    private JSONObject extractOriginMap(@Nonnull DownloadStatistics downloadStatistics) {
        List<String> list;
        Map<String, List<String>> unformattedHeaders = downloadStatistics.getUnformattedHeaders();
        if (unformattedHeaders != null && (list = unformattedHeaders.get("x-amz-live-playback-stream-status")) != null && !list.isEmpty()) {
            try {
                return new JSONObject(list.get(0));
            } catch (JSONException e) {
                DLog.exceptionf(e, "Cannot parse origin map : " + list.get(0), new Object[0]);
            }
        }
        return null;
    }
}
