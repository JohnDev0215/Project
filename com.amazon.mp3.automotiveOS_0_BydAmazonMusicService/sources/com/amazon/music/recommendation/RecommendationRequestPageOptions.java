package com.amazon.music.recommendation;

import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendationRequestPageOptions {
    private final int nextPageToken;
    private final String widgetId;

    public RecommendationRequestPageOptions(String str, int i) {
        Validate.isTrue(i > -1, "nextPageToken should be greater than -1", new Object[0]);
        this.widgetId = str;
        this.nextPageToken = i;
    }

    public RecommendationRequestPageOptions(String str) {
        this(str, 0);
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public int getNextPageToken() {
        return this.nextPageToken;
    }
}
