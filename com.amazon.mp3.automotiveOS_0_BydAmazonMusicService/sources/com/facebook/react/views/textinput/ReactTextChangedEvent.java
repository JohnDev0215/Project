package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class ReactTextChangedEvent extends Event<ReactTextChangedEvent> {
    private int mEventCount;
    private String mText;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }

    public ReactTextChangedEvent(int i, int i2, String str, int i3) {
        super(i, i2);
        this.mText = str;
        this.mEventCount = i3;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("text", this.mText);
        writableMapCreateMap.putInt("eventCount", this.mEventCount);
        writableMapCreateMap.putInt("target", getViewTag());
        return writableMapCreateMap;
    }
}
