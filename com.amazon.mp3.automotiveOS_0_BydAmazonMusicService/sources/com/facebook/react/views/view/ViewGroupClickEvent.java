package com.facebook.react.views.view;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class ViewGroupClickEvent extends Event<ViewGroupClickEvent> {
    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topClick";
    }

    public ViewGroupClickEvent(int i, int i2) {
        super(i, i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
