package com.amazon.podcast.metrics;

import com.amazon.music.find.metrics.SearchMetricsServiceDefault;

/* JADX INFO: loaded from: classes5.dex */
public enum UiMetricAttributes$ContentSrc {
    CLIENT(SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);

    private final String contentSrc;

    @Override // java.lang.Enum
    public final String toString() {
        return this.contentSrc;
    }

    UiMetricAttributes$ContentSrc(final String contentSrc) {
        this.contentSrc = contentSrc;
    }
}
