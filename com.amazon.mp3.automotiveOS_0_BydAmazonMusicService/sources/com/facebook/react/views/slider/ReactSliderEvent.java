package com.facebook.react.views.slider;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* JADX INFO: loaded from: classes6.dex */
public class ReactSliderEvent extends Event<ReactSliderEvent> {
    private final boolean mFromUser;
    private final double mValue;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topValueChange";
    }

    public ReactSliderEvent(int i, double d, boolean z) {
        super(i);
        this.mValue = d;
        this.mFromUser = z;
    }

    public double getValue() {
        return this.mValue;
    }

    public boolean isFromUser() {
        return this.mFromUser;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public void dispatch(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("target", getViewTag());
        writableMapCreateMap.putDouble("value", getValue());
        writableMapCreateMap.putBoolean("fromUser", isFromUser());
        return writableMapCreateMap;
    }
}
