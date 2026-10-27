package com.amazon.music.recommendation;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentInfo;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendationInfo {
    private Map<String, String> contentInfoMap;
    private RecommendationType recommendationType;
    private String widgetId;

    public RecommendationInfo(RecommendationType recommendationType, String str, ContentInfo contentInfo) {
        this.recommendationType = recommendationType;
        this.widgetId = str;
        this.contentInfoMap = createContentInfoMap(contentInfo);
    }

    public Map<String, String> getContentInfoMap() {
        return this.contentInfoMap;
    }

    public Map<String, String> createContentInfoMap(ContentInfo contentInfo) {
        if (contentInfo == null) {
            return null;
        }
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, contentInfo.getContentSrc());
        map.put("contentInfoInstId", contentInfo.getContentInfoInstId());
        return map;
    }
}
