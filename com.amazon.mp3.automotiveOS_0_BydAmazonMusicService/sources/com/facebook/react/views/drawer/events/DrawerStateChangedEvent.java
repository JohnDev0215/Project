package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent> {
    private final int mDrawerState;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topDrawerStateChanged";
    }

    public DrawerStateChangedEvent(int i, int i2, int i3) {
        super(i, i2);
        this.mDrawerState = i3;
    }

    public int getDrawerState() {
        return this.mDrawerState;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putDouble("drawerState", getDrawerState());
        return writableMapCreateMap;
    }
}
