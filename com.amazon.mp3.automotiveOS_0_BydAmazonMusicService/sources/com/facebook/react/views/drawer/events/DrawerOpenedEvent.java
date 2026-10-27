package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class DrawerOpenedEvent extends Event<DrawerOpenedEvent> {
    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topDrawerOpen";
    }

    public DrawerOpenedEvent(int i, int i2) {
        super(i, i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
