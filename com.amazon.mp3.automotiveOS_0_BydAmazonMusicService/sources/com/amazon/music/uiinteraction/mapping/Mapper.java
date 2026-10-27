package com.amazon.music.uiinteraction.mapping;

import com.amazon.music.events.ContentViewedAppEvent;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;

/* JADX INFO: loaded from: classes4.dex */
public interface Mapper {
    UiClickEvent mapUiClick(UserInteractionAppEvent userInteractionAppEvent);

    UiContentViewEvent mapUiContentView(ContentViewedAppEvent contentViewedAppEvent);

    UiPageViewEvent mapUiPageView(NavigationAppEvent navigationAppEvent);
}
