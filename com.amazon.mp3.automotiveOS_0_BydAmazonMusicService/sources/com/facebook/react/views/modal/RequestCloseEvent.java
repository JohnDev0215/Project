package com.facebook.react.views.modal;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
class RequestCloseEvent extends Event<RequestCloseEvent> {
    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topRequestClose";
    }

    protected RequestCloseEvent(int i, int i2) {
        super(i, i2);
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        return Arguments.createMap();
    }
}
