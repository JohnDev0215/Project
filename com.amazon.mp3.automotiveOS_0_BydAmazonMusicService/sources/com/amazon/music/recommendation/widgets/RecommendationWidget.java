package com.amazon.music.recommendation.widgets;

import com.amazon.music.recommendation.RecommendationInfo;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendationWidget<T> {
    private final RecommendationInfo info;
    private final List<T> items;
    private final RecommendationWidgetType type;
    private final String widgetId;

    public RecommendationWidget(String str, RecommendationWidgetType recommendationWidgetType, List<T> list, RecommendationInfo recommendationInfo) {
        this.widgetId = str;
        this.type = recommendationWidgetType;
        this.items = list;
        this.info = recommendationInfo;
    }

    public String getWidgetId() {
        return this.widgetId;
    }

    public RecommendationWidgetType getType() {
        return this.type;
    }

    public List<T> getItems() {
        return this.items;
    }

    public RecommendationInfo getInfo() {
        return this.info;
    }
}
