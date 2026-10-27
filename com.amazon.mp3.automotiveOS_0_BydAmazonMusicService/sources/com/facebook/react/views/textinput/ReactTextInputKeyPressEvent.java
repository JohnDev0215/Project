package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
public class ReactTextInputKeyPressEvent extends Event<ReactTextInputEvent> {
    private String mKey;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topKeyPress";
    }

    @Deprecated
    ReactTextInputKeyPressEvent(int i, String str) {
        this(-1, i, str);
    }

    ReactTextInputKeyPressEvent(int i, int i2, String str) {
        super(i, i2);
        this.mKey = str;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString("key", this.mKey);
        return writableMapCreateMap;
    }
}
