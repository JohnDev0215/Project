package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
class ReactTextInputSelectionEvent extends Event<ReactTextInputSelectionEvent> {
    private int mSelectionEnd;
    private int mSelectionStart;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topSelectionChange";
    }

    public ReactTextInputSelectionEvent(int i, int i2, int i3, int i4) {
        super(i, i2);
        this.mSelectionStart = i3;
        this.mSelectionEnd = i4;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putInt("end", this.mSelectionEnd);
        writableMapCreateMap2.putInt(AsmString.METHOD_FIREBASE_PERF_TRACE_START, this.mSelectionStart);
        writableMapCreateMap.putMap("selection", writableMapCreateMap2);
        return writableMapCreateMap;
    }
}
