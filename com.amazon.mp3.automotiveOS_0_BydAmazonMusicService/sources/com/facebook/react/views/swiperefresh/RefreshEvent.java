package com.facebook.react.views.swiperefresh;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class RefreshEvent extends Event<RefreshEvent> {
    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRefresh";
    }

    protected RefreshEvent(int i, int i2) {
        super(i, i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
