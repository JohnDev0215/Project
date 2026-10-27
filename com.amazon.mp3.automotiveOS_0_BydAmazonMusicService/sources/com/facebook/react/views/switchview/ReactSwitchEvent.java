package com.facebook.react.views.switchview;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

/* JADX INFO: loaded from: classes6.dex */
class ReactSwitchEvent extends Event<ReactSwitchEvent> {
    private final boolean mIsChecked;

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return "topChange";
    }

    public ReactSwitchEvent(int i, int i2, boolean z) {
        super(i, i2);
        this.mIsChecked = z;
    }

    public boolean getIsChecked() {
        return this.mIsChecked;
    }

    @Override // com.facebook.react.uimanager.events.Event
    @Nullable
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("target", getViewTag());
        writableMapCreateMap.putBoolean("value", getIsChecked());
        return writableMapCreateMap;
    }
}
