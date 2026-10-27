package com.amazon.podcast.metrics.ui.UIContentViewCache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public enum UIContentViewWidgetIndexCache {
    INSTANCE;

    private static final String TAG = "UIContentViewWidgetIndexCache";
    private final Logger logger = LoggerFactory.getLogger(TAG);
    private Map<String, UIContentViewCacheItem> cache = new ConcurrentHashMap();

    UIContentViewWidgetIndexCache() {
    }

    public void addWidget(final String widgetId) {
        if (this.cache.containsKey(widgetId)) {
            return;
        }
        this.cache.put(widgetId, new UIContentViewCacheItem());
    }

    public UIContentViewCacheItem getWidget(final String widgetId) {
        if (StringUtils.isBlank(widgetId)) {
            return null;
        }
        UIContentViewCacheItem uIContentViewCacheItem = this.cache.get(widgetId);
        if (uIContentViewCacheItem != null) {
            return uIContentViewCacheItem;
        }
        this.logger.warn("getWidget: Widget not found for id: " + widgetId);
        return null;
    }

    public void updateViewedIndices(final String widgetId, final List<Integer> viewedIndices) {
        UIContentViewCacheItem uIContentViewCacheItem = this.cache.get(widgetId);
        if (uIContentViewCacheItem == null) {
            this.logger.warn("updateViewedIndices: Widget not found for id: " + widgetId);
            return;
        }
        uIContentViewCacheItem.setViewedIndices(viewedIndices);
    }

    public void updateReportedIndices(final String widgetId, final List<Integer> reportedIndices) {
        UIContentViewCacheItem uIContentViewCacheItem = this.cache.get(widgetId);
        if (uIContentViewCacheItem == null) {
            this.logger.warn("updateReportedIndices: Widget not found for id: " + widgetId);
            return;
        }
        uIContentViewCacheItem.setReportedIndices(reportedIndices);
    }

    public void updateNumItemsAccessible(final String widgetId, final int numItemsAccessible) {
        UIContentViewCacheItem uIContentViewCacheItem = this.cache.get(widgetId);
        if (uIContentViewCacheItem == null) {
            this.logger.warn("updateNumItemsAccessible: Widget not found for id: " + widgetId);
            return;
        }
        uIContentViewCacheItem.setNumItemsAccessible(numItemsAccessible);
    }

    public void clearCache() {
        this.cache.clear();
    }

    public boolean isEmpty() {
        return this.cache.isEmpty();
    }
}
