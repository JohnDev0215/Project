package com.amazon.mp3.cloudqueue.model;

import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.ResourceType;
import com.amazon.music.metrics.mts.event.types.SelectionSourceType;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MetricsContext {
    static final String TAG = "MetricsContext";
    private Boolean mIsOnDemandQueue;
    private String mPlayQueueId;
    private PlaybackPageType mPlaybackPageType;
    private ResourceType mResourceType;
    private String mSelectionSourceId;
    private String mSelectionSourceSessionId;
    private SelectionSourceType mSelectionSourceType;
    private String mTrackCount;

    private MetricsContext() {
    }

    public static MetricsContext from(final Map<String, String> metricsContextMap) {
        MetricsContext metricsContext = new MetricsContext();
        if (metricsContextMap.containsKey("selectionSourceType")) {
            try {
                metricsContext.setSelectionSourceType(SelectionSourceType.fromValue(metricsContextMap.get("selectionSourceType")));
            } catch (IllegalArgumentException e) {
                Log.error(TAG, "selectionSourceType not match when parsing metrics context", e);
            }
        }
        if (metricsContextMap.get("selectionSourceId") != null) {
            metricsContext.setSelectionSourceId(metricsContextMap.get("selectionSourceId"));
        }
        if (metricsContextMap.get("selectionSourceSessionId") != null) {
            metricsContext.setSelectionSourceSessionId(metricsContextMap.get("selectionSourceSessionId"));
        }
        if (metricsContextMap.get("playQueueId") != null) {
            metricsContext.setPlayQueueId(metricsContextMap.get("playQueueId"));
        }
        if (metricsContextMap.containsKey("resourceType")) {
            try {
                metricsContext.setResourceType((ResourceType) Enum.valueOf(ResourceType.class, metricsContextMap.get("resourceType")));
            } catch (IllegalArgumentException e2) {
                Log.error(TAG, "ResourceType not match when parsing metrics context", e2);
            }
        }
        if (metricsContextMap.get("trackCount") != null) {
            metricsContext.setTrackCount(metricsContextMap.get("trackCount"));
        }
        if (metricsContextMap.containsKey("isOnDemandQueue")) {
            metricsContext.setIsOnDemandQueue(Boolean.valueOf(Boolean.TRUE.toString().equals(metricsContextMap.get("isOnDemandQueue"))));
        }
        return metricsContext;
    }

    public MetricsContext(final String selectionSourceId, final SelectionSourceType selectionSourceType, final PlaybackPageType pageType, final Boolean isOnDemandQueue) {
        setSelectionSourceId(selectionSourceId);
        setSelectionSourceType(selectionSourceType);
        setPageType(pageType);
        setIsOnDemandQueue(isOnDemandQueue);
    }

    public MetricsContext(final String selectionSourceId, final SelectionSourceType selectionSourceType, final PlaybackPageType pageType, final Boolean isOnDemandQueue, final String queueId) {
        setSelectionSourceId(selectionSourceId);
        setSelectionSourceType(selectionSourceType);
        setPageType(pageType);
        setIsOnDemandQueue(isOnDemandQueue);
        setPlayQueueId(queueId);
        setSelectionSourceSessionId(queueId);
    }

    public Map<String, String> toMap() {
        HashMap map = new HashMap();
        if (getSelectionSourceId() != null) {
            map.put("selectionSourceId", getSelectionSourceId());
        }
        if (getSelectionSourceSessionId() != null) {
            map.put("selectionSourceSessionId", getSelectionSourceSessionId());
        }
        if (getPlayQueueId() != null) {
            map.put("playQueueId", getPlayQueueId());
        }
        if (getResourceType() != null) {
            map.put("resourceType", getResourceType().getMetricsValue());
        }
        if (getTrackCount() != null) {
            map.put("trackCount", getTrackCount());
        }
        if (getSelectionSourceType() != null) {
            map.put("selectionSourceType", getSelectionSourceType().getMetricsValue());
        }
        if (getPageType() != null) {
            map.put("pageType", getPageType().getMetricsValue());
        }
        if (getIsOnDemandQueue() != null) {
            map.put("isOnDemandQueue", getIsOnDemandQueue().toString());
        }
        return map;
    }

    public SelectionSourceType getSelectionSourceType() {
        return this.mSelectionSourceType;
    }

    public void setSelectionSourceType(final SelectionSourceType selectionSourceType) {
        this.mSelectionSourceType = selectionSourceType;
    }

    public String getSelectionSourceId() {
        return this.mSelectionSourceId;
    }

    public void setSelectionSourceId(final String selectionSourceId) {
        this.mSelectionSourceId = selectionSourceId;
    }

    public String getSelectionSourceSessionId() {
        return this.mSelectionSourceSessionId;
    }

    public void setSelectionSourceSessionId(final String selectionSourceSessionId) {
        this.mSelectionSourceSessionId = selectionSourceSessionId;
    }

    public String getPlayQueueId() {
        return this.mPlayQueueId;
    }

    public void setPlayQueueId(final String playQueueId) {
        this.mPlayQueueId = playQueueId;
    }

    public ResourceType getResourceType() {
        return this.mResourceType;
    }

    public void setResourceType(final ResourceType resourceType) {
        this.mResourceType = resourceType;
    }

    public String getTrackCount() {
        return this.mTrackCount;
    }

    public void setTrackCount(final String trackCount) {
        this.mTrackCount = trackCount;
    }

    public PlaybackPageType getPageType() {
        return this.mPlaybackPageType;
    }

    public void setPageType(final PlaybackPageType pageType) {
        this.mPlaybackPageType = pageType;
    }

    public Boolean getIsOnDemandQueue() {
        return this.mIsOnDemandQueue;
    }

    public void setIsOnDemandQueue(final Boolean isOnDemandQueue) {
        this.mIsOnDemandQueue = isOnDemandQueue;
    }
}
