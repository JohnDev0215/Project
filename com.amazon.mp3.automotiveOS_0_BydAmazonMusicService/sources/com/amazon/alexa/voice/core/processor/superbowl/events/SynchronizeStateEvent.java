package com.amazon.alexa.voice.core.processor.superbowl.events;

import android.util.ArrayMap;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SynchronizeStateEvent extends SuperbowlEvent {
    private SynchronizeStateEvent(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        return new JSONObject();
    }

    public static final class Builder extends SuperbowlEvent.Builder<SynchronizeStateEvent> {
        final ArrayMap<String, String> keyValues;

        public Builder() {
            super("System", "SynchronizeState");
            this.keyValues = new ArrayMap<>();
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public SynchronizeStateEvent build() {
            return new SynchronizeStateEvent(this);
        }
    }
}
