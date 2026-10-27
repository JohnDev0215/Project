package com.facebook.react.views.textinput;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.google.firebase.perf.plugin.util.AsmString;

/* JADX INFO: loaded from: classes6.dex */
public class ReactTextInputEvent extends Event<ReactTextInputEvent> {
    private String mPreviousText;
    private int mRangeEnd;
    private int mRangeStart;
    private String mText;

    @Override // com.facebook.react.uimanager.events.Event
    public boolean canCoalesce() {
        return false;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topTextInput";
    }

    public ReactTextInputEvent(int i, int i2, String str, String str2, int i3, int i4) {
        super(i, i2);
        this.mText = str;
        this.mPreviousText = str2;
        this.mRangeStart = i3;
        this.mRangeEnd = i4;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        writableMapCreateMap2.putDouble(AsmString.METHOD_FIREBASE_PERF_TRACE_START, this.mRangeStart);
        writableMapCreateMap2.putDouble("end", this.mRangeEnd);
        writableMapCreateMap.putString("text", this.mText);
        writableMapCreateMap.putString("previousText", this.mPreviousText);
        writableMapCreateMap.putMap("range", writableMapCreateMap2);
        writableMapCreateMap.putInt("target", getViewTag());
        return writableMapCreateMap;
    }
}
