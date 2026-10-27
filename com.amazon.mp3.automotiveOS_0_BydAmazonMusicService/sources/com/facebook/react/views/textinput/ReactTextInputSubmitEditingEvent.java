package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
class ReactTextInputSubmitEditingEvent extends Event<ReactTextInputSubmitEditingEvent> {
    private String mText;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topSubmitEditing";
    }

    public ReactTextInputSubmitEditingEvent(int i, int i2, String str) {
        super(i, i2);
        this.mText = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("target", getViewTag());
        writableMapCreateMap.putString("text", this.mText);
        return writableMapCreateMap;
    }
}
