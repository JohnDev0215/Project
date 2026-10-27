package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class DrawerClosedEvent extends Event<DrawerClosedEvent> {
    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topDrawerClose";
    }

    public DrawerClosedEvent(int i, int i2) {
        super(i, i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
