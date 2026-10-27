package com.amazon.music.voice;

import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class ShuffleStateEvent extends SuperbowlEvent {
    protected final boolean state;

    private ShuffleStateEvent(Builder builder, boolean z) {
        super(builder);
        this.state = z;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", "SHUFFLE");
        jSONObject.put("action", this.state ? "SELECT" : "DESELECT");
        return jSONObject;
    }

    public String toString() {
        return getClass().getSimpleName() + "{namespace='" + getNamespace() + "', name='" + getName() + "', messageId='" + getMessageId() + "', dialogRequestId='" + getDialogRequestId() + "'}";
    }

    public static class Builder extends SuperbowlEvent.Builder<ShuffleStateEvent> {
        protected boolean state;

        public Builder() {
            super("PlaybackController", "ToggleCommandIssued");
            this.state = false;
        }

        public Builder setState(boolean z) {
            this.state = z;
            return this;
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public ShuffleStateEvent build() {
            return new ShuffleStateEvent(this, this.state);
        }
    }
}
