package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.LiveStreamBarkerElement;
import com.amazon.music.explore.widgets.metadata.ExploreMetadata;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.LivestreamBarkerModel;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreLiveStreamBarkerModel extends ExploreModel {
    private final LiveStreamBarkerElement widget;

    public ExploreLiveStreamBarkerModel(String str, MethodsDispatcher methodsDispatcher, LiveStreamBarkerElement liveStreamBarkerElement) {
        super(str, methodsDispatcher);
        this.widget = liveStreamBarkerElement;
    }

    public LiveStreamBarkerElement getWidget() {
        return this.widget;
    }

    public LivestreamBarkerModel convertToBrushV2Model() {
        return new LivestreamBarkerModel("viewId", this.widget.thumbnailImage(), this.widget.primaryText(), this.widget.secondaryText(), this.widget.tertiaryText(), 9, getExploreMetadata(), this.widget.viewers(), null, this.widget.wordmark());
    }

    private ExploreMetadata getExploreMetadata() {
        return new ExploreMetadata(getOwnerId(), getMethodsDispatcher(), this.widget.onItemSelected());
    }
}
